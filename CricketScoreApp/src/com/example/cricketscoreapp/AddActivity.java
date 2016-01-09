package com.example.cricketscoreapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity {
	EditText etTeamName,etPlayerName;
	TextView text;
	ImageView save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	
	public void initialize()
	{
		etTeamName = (EditText)findViewById(R.id.txtTname);
		etPlayerName = (EditText)findViewById(R.id.txtName);
		text = (TextView)findViewById(R.id.saveText);
		save = (ImageView)findViewById(R.id.btnSave);
		
		invisible();
	}
	
	public void invisible() {
		etPlayerName.setVisibility(View.GONE);
		text.setVisibility(View.GONE);
		save.setVisibility(View.GONE);
		
	}

	public void addPlayerClicked(View aview)
	{
		if(etTeamName.getText().toString().equals(""))
		{
			Toast.makeText(this, "Please Enter a Teamname", Toast.LENGTH_LONG).show();
		}
		else
		{
			etPlayerName.setVisibility(View.VISIBLE);
			text.setVisibility(View.VISIBLE);
			save.setVisibility(View.VISIBLE);
		}
	}
	
	public void btnSavePlayer(View aview)
	{
		invisible();
		database ob1 = new database(this);
		ob1.open();
		int x=ob1.count(etTeamName.getText().toString().toUpperCase());
		
		
		if(x<15)
		{
			ob1.entry(etTeamName.getText().toString().toUpperCase(), etPlayerName.getText().toString());
			int ag=ob1.count(etTeamName.getText().toString().toUpperCase());
			int y = 15-ag;
			Toast.makeText(this, "Player Added", Toast.LENGTH_LONG).show();
			Toast.makeText(this, Integer.toString(y)+" Players Remaining to complete the Squad", Toast.LENGTH_LONG).show();
			ob1.close();
		}
		
		else
		{
			Toast.makeText(this, "Squad Fullfiled!!No More Player Can Be Added", Toast.LENGTH_LONG).show();
			ob1.close();
		}
		
		
		
	}

}
