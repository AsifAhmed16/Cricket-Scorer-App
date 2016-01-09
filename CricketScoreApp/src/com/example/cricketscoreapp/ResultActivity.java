package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends Activity {
	TextView toss,team1final,team1over,team2final,team2over,result;
	String team1,team2;
	int team1Over,team2Over,team1Bowl,team2Bowl;
	int[] team1Runs = new int[100];
	int[] team2Runs = new int[100];
	int team1Total,team2Total,team1Wicket,team2Wicket;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	public void initialize()
	{
		toss = (TextView)findViewById(R.id.txtToss);
		team1final = (TextView)findViewById(R.id.team1Total);
		team2final = (TextView)findViewById(R.id.team2Total);
		team1over = (TextView)findViewById(R.id.team1Over);
		team2over = (TextView)findViewById(R.id.team2Over);
		result = (TextView)findViewById(R.id.txtResult);
		
		
		team1 = getIntent().getExtras().getString("team1");
		team2 = getIntent().getExtras().getString("team2");
		
		team1Over = getIntent().getExtras().getInt("team1Over");
		team2Over = getIntent().getExtras().getInt("team2Over");
		
		team1Bowl = getIntent().getExtras().getInt("team1Bowl");
		team2Bowl = getIntent().getExtras().getInt("team2Bowl");
		
		team1Total = getIntent().getExtras().getInt("team1Total");
		team2Total = getIntent().getExtras().getInt("team2Total");
		
		team1Wicket = getIntent().getExtras().getInt("team1Wicket");
		team2Wicket = getIntent().getExtras().getInt("team2Wicket");
		
		
		loadResult();
	}

    public void loadResult() {
		toss.setText(team1+" Won The Toss");
		team1final.setText(team1+": "+Integer.toString(team1Total)+"/"+Integer.toString(team1Wicket));
		team1over.setText(Integer.toString(team1Over)+"."+Integer.toString(team1Bowl));
		
		team2final.setText(team2+": "+Integer.toString(team2Total)+"/"+Integer.toString(team2Wicket));
		team2over.setText(Integer.toString(team2Over)+"."+Integer.toString(team2Bowl));
		
		if(team1Total>team2Total){
			result.setText(team1+" Won The Match by "+Integer.toString((team1Total-team2Total))+" runs");
		}
		else if(team2Total>team1Total){
			result.setText(team2+" Won The Match by "+Integer.toString(10-team2Wicket));
		}
			
			
		
	}

}
