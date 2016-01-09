package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		threadExecute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void threadExecute()
	{
		Thread splash = new Thread(){
			@Override
			public void run() {
				try
				{
					sleep(5000);
				}catch(InterruptedException e){
					
				} finally{
					finish();
					gotoNextActivity();
				}
				
			}

		};
		splash.start();
	}
	
	private void gotoNextActivity() {
		Intent intent = new Intent(MainActivity.this,Home.class);
		startActivity(intent);
		
	}

}
