package com.example.cricketscoreapp;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends Activity {
	Spinner spn;
	ListView players;
	database ob;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		spn = (Spinner)findViewById(R.id.listTeam);
		players = (ListView)findViewById(R.id.lst1);
		populateSpinner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}
	
	public void populateSpinner()
	{
		
		database ob1 = new database(this);
		ob1.open();
		List<String> teams = ob1.getName();
		
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, teams);
		
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spn.setAdapter(spinnerArrayAdapter);
		
		ob1.close();
		
		spn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String x=spn.getSelectedItem().toString();
				populateListView(x);
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		    
		});

		
	}
	
	public void populateListView(String teamname)
	{
		database ob1 = new database(this);
		ob1.open();
		
		List<String> allplayers = ob1.getPlayers(teamname);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, allplayers);
		
		
		players.setAdapter(spinnerArrayAdapter);
		
		ob1.close();
		ob= new database(this);
		players.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				String selectedFromList =(String) (players.getItemAtPosition(arg2));
				
				
				ob.open();
				int id=ob.getId(spn.getSelectedItem().toString(),(String) (players.getItemAtPosition(arg2)));
				ob.close();
				
				Intent intent = new Intent(EditActivity.this,PlayerActivity.class);
				intent.putExtra("playername", selectedFromList);
				intent.putExtra("id", id);
				startActivity(intent);
				
			}
		});
	}
	
	
	
	
	
	
	
}
