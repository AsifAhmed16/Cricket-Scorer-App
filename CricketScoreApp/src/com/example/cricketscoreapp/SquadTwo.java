package com.example.cricketscoreapp;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SquadTwo extends Activity {
	String team1,team2;
	int overs;
	String players1[]=new String[100];
	ListView lst;
	ArrayAdapter<String> spinnerArrayAdapter;
	List<String> allplayers;
	String players[]=new String[100];
	int index=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_squad_two);
		retrieve();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.squad_two, menu);
		return true;
	}
	
	private void retrieve() {
		team1 = getIntent().getExtras().getString("team1");
		team2 = getIntent().getExtras().getString("team2");
		overs = getIntent().getExtras().getInt("overs");
		players = getIntent().getStringArrayExtra("players");
		lst = (ListView)findViewById(R.id.lsttwo);
		populateListView();
		
	}



	private void populateListView() {
		database ob1 = new database(this);
		ob1.open();
		
		allplayers = ob1.getPlayers(team2);
		spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, allplayers);
		
		
		lst.setAdapter(spinnerArrayAdapter);
		
		ob1.close();
		
		lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(index<11)
				{
				players1[index] = (String) (lst.getItemAtPosition(arg2));
				
				
				spinnerArrayAdapter.remove(allplayers.get(arg2));
				spinnerArrayAdapter.notifyDataSetChanged();
				
				showMessage();
				}
				
				else if(index>=11){
				ErrorMessage();
				}
			}

			
		
		});
		
	}
	
	public void showMessage(){
		
		Toast.makeText(this, players1[index]+ " SELECTED" , Toast.LENGTH_SHORT).show();
		index++;
	}
	
	private void ErrorMessage() {
		Toast.makeText(this, "11 players Selected Already" , Toast.LENGTH_SHORT).show();
	}
	
	public void proceedClicked(View aview)
	{
		if(index<11)
		{
			Toast.makeText(this, "More "+Integer.toString(11-index)+" Players Needed" , Toast.LENGTH_SHORT).show();
		}
		else if(index==11){
			Intent intent = new Intent(SquadTwo.this,TossActivity.class);
			
			intent.putExtra("team1", team1);
			intent.putExtra("team2", team2);
			intent.putExtra("overs", overs);
			intent.putExtra("players", players);
			intent.putExtra("players1", players1);
			
			startActivity(intent);
			
		}
	}

}
