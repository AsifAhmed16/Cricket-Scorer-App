package com.example.cricketscoreapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class TossActivity extends Activity {
	String team1,team2;
	int overs;
	String players1[]=new String[100];
	ListView lst;
	ArrayAdapter<String> spinnerArrayAdapter;
	List<String> allplayers;
	String players[]=new String[100];
	int index=0;
	RadioButton bat,bowl;
	Spinner teams;
	String format;
	String selectedTeam;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toss);
		retrieve();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toss, menu);
		return true;
	}
	
	private void retrieve() {
		team1 = getIntent().getExtras().getString("team1");
		team2 = getIntent().getExtras().getString("team2");
		overs = getIntent().getExtras().getInt("overs");
		players = getIntent().getStringArrayExtra("players");
		players1 = getIntent().getStringArrayExtra("players1");
		
		teams = (Spinner)findViewById(R.id.teams);
		
		
		ArrayList<String> com = new ArrayList<String>();
		com.add(team1.toString());
		com.add(team2.toString());
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, com);
		
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		teams.setAdapter(spinnerArrayAdapter);
		
		tossResult();
		
		
	}

	public void tossResult() {
		bat = (RadioButton)findViewById(R.id.rdBat);
		bowl = (RadioButton)findViewById(R.id.rdBowl);
	}
	
	public void nextClicked(View aview)
	{
		selectedTeam = teams.getSelectedItem().toString();
		if(bat.isChecked()){
			format = "Bat";
		}
		
		else if(bowl.isChecked()){
			format = "Bowl";
			
		}
		
		
		Intent intent = new Intent(TossActivity.this,Game.class);
		
		intent.putExtra("team1", team1);
		intent.putExtra("team2", team2);
		intent.putExtra("overs", overs);
		intent.putExtra("players", players);
		intent.putExtra("players1", players1);
		intent.putExtra("format", format);
		intent.putExtra("selected", selectedTeam);
		
		startActivity(intent);
		
	}

}
