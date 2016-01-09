package com.example.cricketscoreapp;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class StartmatchActivity extends Activity {
	Spinner team1,team2;
	RadioButton rd1,rd2,rd3;
	int overs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startmatch);
		Initialize();
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.startmatch, menu);
		return true;
	}
	
	public void Initialize() {
		team1 = (Spinner)findViewById(R.id.spnFirst);
		team2 = (Spinner)findViewById(R.id.spnSecond);
		rd1 = (RadioButton)findViewById(R.id.rdBat);
		rd2 = (RadioButton)findViewById(R.id.rdBowl);
		rd3 = (RadioButton)findViewById(R.id.radio2);
		
		database ob1 = new database(this);
		
		ob1.open();
		List<String> teamnames=ob1.teamValidity();
		ob1.close();
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, teamnames);
		
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		team1.setAdapter(spinnerArrayAdapter);
		team2.setAdapter(spinnerArrayAdapter);
		
		
		
	}
	
	public void nextClicked(View aview){
		if(team1.getSelectedItem().toString().equals(team2.getSelectedItem().toString())){
			Toast.makeText(this, "Invalid Selection of Team", Toast.LENGTH_SHORT).show();
		}
		else{
			Intent intent = new Intent(StartmatchActivity.this,SquadOne.class);
			intent.putExtra("team1", team1.getSelectedItem().toString());
			intent.putExtra("team2", team2.getSelectedItem().toString());
			
			if(rd1.isChecked())
			{
				overs = 10;
			}
			
			else if(rd2.isChecked())
			{
				overs = 20;
			}
			
			else if(rd3.isChecked())
			{
				overs = 50;
			}
			intent.putExtra("overs", overs);
			
			startActivity(intent);
		}
	}

}
