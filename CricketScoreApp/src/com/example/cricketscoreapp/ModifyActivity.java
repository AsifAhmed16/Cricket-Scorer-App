package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ModifyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify, menu);
		return true;
	}
	
	public void addClicked(View aview)
	{
		Intent intent = new Intent(ModifyActivity.this,AddActivity.class);
		startActivity(intent);
		
	}
	
	public void editClicked(View aview)
	{
		Intent intent = new Intent(ModifyActivity.this,EditActivity.class);
		startActivity(intent);
		
	}

}
