package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void addClicked(View aview)
	{
		Intent intent = new Intent(Home.this,ModifyActivity.class);
		startActivity(intent);
		
	}
	
	public void startclicked(View aview)
	{
		Intent intent = new Intent(Home.this,StartmatchActivity.class);
		startActivity(intent);
	}
	public void exitclicked(View aview)
	{
		Intent intent = new Intent(Home.this,MainActivity.class);
		startActivity(intent);
	}

}
