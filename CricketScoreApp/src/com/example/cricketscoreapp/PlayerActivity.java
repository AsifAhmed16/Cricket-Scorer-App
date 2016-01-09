package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerActivity extends Activity {
	String player;
	EditText etPlayer;
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		Initialize();
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		return true;
	}
	
	public void Initialize() {
		player = getIntent().getExtras().getString("playername");
		id = getIntent().getExtras().getInt("id");
		
		
		
		etPlayer = (EditText)findViewById(R.id.editText1);
		etPlayer.setText(player);
		
		
	}
	
	public void editClicked(View aview)
	{
		database ob1 = new database(this);
		ob1.open();
		ob1.editPlayer(id, etPlayer.getText().toString());
		ob1.close();
		
		Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(PlayerActivity.this,EditActivity.class);
		startActivity(intent);
	}

}
