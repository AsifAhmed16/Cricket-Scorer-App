package com.example.cricketscoreapp;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	String team1, team2;
	String players1[] = new String[100];
	ListView lst;
	Spinner Bowlers;
	ArrayAdapter<String> spinnerArrayAdapter;
	List<String> allplayers;

	String players[] = new String[100];
	int index = 0, index1 = 1;
	boolean[] decide = new boolean[20];
	int totalovers;
	String format;
	String selectedTeam;
	TextView tx1, tx2,run,ovr;
	int runs[] = new int[100];
	int wicket=0;
	int bowl = 0, over = 0;
	int Strike;
	String batsman[] = new String[100];
	ScrollView scrl;
	int[] gone = new int[100];
	int goneindex=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		retrieve();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	private void retrieve() {
		team1 = getIntent().getExtras().getString("team1");
		team2 = getIntent().getExtras().getString("team2");
		totalovers = getIntent().getExtras().getInt("overs");
		players = getIntent().getStringArrayExtra("players");
		players1 = getIntent().getStringArrayExtra("players1");
		format = getIntent().getExtras().getString("format");
		selectedTeam = getIntent().getExtras().getString("selected");

		tx1 = (TextView) findViewById(R.id.textView1);
		tx2 = (TextView) findViewById(R.id.textView2);
		
		run = (TextView) findViewById(R.id.txtRuns);
		ovr = (TextView) findViewById(R.id.txtOver);
		
		scrl = (ScrollView)findViewById(R.id.scrollView1);
		
		
		Bowlers = (Spinner) findViewById(R.id.Bowlers);
		Toast.makeText(this, format, Toast.LENGTH_SHORT).show();

		if (selectedTeam.equals(team1) && format.equals("Bat")) {
			batsman = players;
			generatePlayer(players[0], players[1], team2);
		} else if (selectedTeam.equals(team1) && format.equals("Bowl")) {
			batsman = players1;
			generatePlayer(players1[0], players1[1], team1);
		}

		else if (selectedTeam.equals(team2) && format.equals("Bat")) {
			batsman = players1;
			generatePlayer(players1[0], players1[1], team1);
		}

		else if (selectedTeam.equals(team2) && format.equals("Bowl")) {
			batsman = players;
			generatePlayer(players[0], players[1], team2);
		}

	}

	private void generatePlayer(String player1, String player2, String team) {
		List<String> bowlers = new ArrayList<String>();
		tx1.setText(player1);
		tx2.setText(player2);

		database ob1 = new database(this);

		ob1.open();
		bowlers = ob1.getPlayers(team);
		ob1.close();

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, bowlers);

		spinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The
																							// drop
																							// down
																							// view
		Bowlers.setAdapter(spinnerArrayAdapter);

		loader();
	}

	public void loader() {

		runs[index] = 0;
		runs[index1] = 0;

		decide[index] = true;
		decide[index1] = false;

	}

	
	
	
	public void oneClicked(View aview) {
		bowl++;
		
		if (decide[index]) 
		{
			decide[index] = false;
			decide[index1] = true;

			runs[index]++;

			generateScore(index, index1, "yes");
		} else if (decide[index1]) {
			decide[index1] = false;
			decide[index] = true;
			runs[index1]++;
			generateScore(index1, index, "yes");
		}
		overCheck(bowl);
	}

	public void twoClicked(View aview) {
		bowl++;
		
		if (decide[index]) {
			decide[index] = true;
			decide[index1] = false;

			runs[index] = runs[index] + 2;

			generateScore(index, index1, "no");
		} else if (decide[index1]) {
			decide[index1] = true;
			decide[index] = false;
			runs[index1] = runs[index1] + 2;
			generateScore(index1, index, "no");
		}
		overCheck(bowl);

	}

	public void threeClicked(View aview) {
		bowl++;
		
		if (decide[index]) {
			decide[index] = false;
			decide[index1] = true;

			runs[index] = runs[index] + 3;

			generateScore(index, index1, "yes");
		} else if (decide[index1]) {
			decide[index1] = false;
			decide[index] = true;
			runs[index1] = runs[index1] + 3;
			generateScore(index1, index, "yes");
		}
		overCheck(bowl);
	}
	
	public void fourClicked(View aview) {
		bowl++;
		
		if (decide[index]) {
			decide[index] = true;
			decide[index1] = false;

			runs[index] = runs[index] + 4;

			generateScore(index, index1, "no");
		} else if (decide[index1]) {
			decide[index1] = true;
			decide[index] = false;
			runs[index1] = runs[index1] + 4;
			generateScore(index1, index, "no");
		}
		overCheck(bowl);

	}
	
	public void sixClicked(View aview) {
		bowl++;
		
		if (decide[index]) {
			decide[index] = true;
			decide[index1] = false;

			runs[index] = runs[index] + 6;

			generateScore(index, index1, "no");
		} else if (decide[index1]) {
			decide[index1] = true;
			decide[index] = false;
			runs[index1] = runs[index1] + 6;
			generateScore(index1, index, "no");
		}
		generateScore(index, index1, "no");
		overCheck(bowl);

	}
	
	public void dotClicked(View aview)
	{
		bowl++;
		generateScore(index, index1, "no");
		overCheck(bowl);
	}
	
	public void outClicked(View aview)
	{
		bowl++;
		wicket++;
		
		if (decide[index]) {
			
				if(index<index1)
				{
					index = index1+1;
					decide[index] = true;
					decide[index1] = false;
					generateScore(index, index1, "no");
				}
				else
				{
					index++;
					decide[index] = true;
					decide[index1] = false;
					generateScore(index, index1, "no");
				}
			}
				
			
		else if (decide[index1]) {
			if(index1<index)
			{
				index1 = index+1;
				decide[index1] = true;
				decide[index] = false;
				generateScore(index, index1, "no");
			}
			else
			{
				index1++;
				decide[index1] = true;
				decide[index] = false;
				generateScore(index, index1, "no");
			}
		}
	}
	

	private void overCheck(int b) {
		if(b==6){
			over++;
			bowl=0;
			scrl.setVisibility(View.GONE);
			
			
			
			Bowlers.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					scrl.setVisibility(View.VISIBLE);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			
			});
				
			
			if (decide[index]) {
				decide[index] = false;
				decide[index1] = true;
				generateScore(index, index1, "no");
			} 
			
			else if (decide[index1]) {
				decide[index1] = false;
				decide[index] = true;
				generateScore(index1, index, "no");
			}
			
		}
		else {
			
		}
	}

	private void generateScore(int i, int j, String pointer) {

		tx1.setText(batsman[i] + "--------------" + Integer.toString(runs[i]));
		tx2.setText(batsman[j] + "--------------" + Integer.toString(runs[j]));
		
		int total = runs[i]+runs[j];
		run.setText(Integer.toString(total)+"/"+Integer.toString(wicket));
		ovr.setText(Integer.toString(over)+"."+Integer.toString(bowl));

	}

}
