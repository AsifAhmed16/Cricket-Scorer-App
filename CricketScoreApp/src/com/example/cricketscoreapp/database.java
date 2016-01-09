package com.example.cricketscoreapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class database {
		private static final String database_name="ScoreBoard";
		private static final String database_table="Teams";
		
		int score=0;
		String Name="";
		private DbHelper aHelper;
		private final Context aContext;
		private SQLiteDatabase aDatabase;
		private static class DbHelper extends SQLiteOpenHelper
		{
			
			public DbHelper(Context context) 
			{
				super(context, database_name, null, 1);
				// TODO Auto-generated constructor stub
			}

			
			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL("CREATE TABLE "+database_table+"(id INTEGER PRIMARY KEY AUTOINCREMENT,teamname varchar(200),playername varchar(200));");
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				
			}
			
		}
		public database(Context c)
		{
			aContext = c;
		}
		public database open()
		{
			aHelper = new DbHelper(aContext);
			aDatabase = aHelper.getWritableDatabase();
			return this;
		}
		public void close()
		{
			aHelper.close();
		}
		
		public void entry(String teamname, String playername) 
		{
			ContentValues cv = new ContentValues();
			
			cv.put("teamname", teamname);
			cv.put("playername", playername);
			aDatabase.insert(database_table, null, cv);
		}
		
		public int count(String teamname)
		{
			String[] columns = new String[]{"teamname","playername"};
			String name[] = new String[100];
			int index=0;
			Cursor c = aDatabase.query(database_table, columns, "teamname='"+teamname+"'", null, null, null, null);
			int i=c.getColumnIndex("teamname");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				name[index] = c.getString(i);
				index++;
			}
			return index;
		}
		public int getIndex()
		{
			
			String[] columns = new String[]{"teamname","playername"};
			String MY_QUERY = "SELECT DISTINCT teamname FROM Teams";
			String name[] =new String[100];
			int index=0;
			
			Cursor c = aDatabase.rawQuery(MY_QUERY, null);
			
			int i=c.getColumnIndex("teamname");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				name[index] = c.getString(i);
				index++;
			}
			return index;
		}
		public ArrayList<String> getName()
		{
			
			String[] columns = new String[]{"teamname","playername"};
			String MY_QUERY = "SELECT DISTINCT teamname FROM Teams";
			ArrayList<String> names = new ArrayList<String>();
			int index=0;
			
			Cursor c = aDatabase.rawQuery(MY_QUERY, null);
			
			int i=c.getColumnIndex("teamname");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				names.add(c.getString(i));
			}
			return names;
		}
		
		public ArrayList<String> getPlayers(String teamname){
			String[] columns = new String[]{"teamname","playername"};
			String MY_QUERY = "SELECT playername FROM Teams WHERE teamname='"+teamname+"'";
			
			ArrayList<String> names = new ArrayList<String>();
			
			Cursor c = aDatabase.rawQuery(MY_QUERY, null);
			
			int i=c.getColumnIndex("playername");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				names.add(c.getString(i));
			}
			return names; 
		}
		
		public int getId(String teamname,String playername)
		{
			String[] columns = new String[]{"teamname","playername"};
			String MY_QUERY = "SELECT id FROM Teams WHERE teamname='"+teamname+"' AND playername='"+playername+"'";
			int id=0;
			
			Cursor c = aDatabase.rawQuery(MY_QUERY, null);
			
			int i=c.getColumnIndex("id");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				id = c.getInt(i);
			}
			
			return id;
		}
		
		public void editPlayer(int id,String playername)
		{
			
			ContentValues cv = new ContentValues();
			cv.put("playername", playername);
			aDatabase.update(database_table, cv , "id = '"+id+"'" , null);
				
		}
		
		public ArrayList<String> teamValidity()
		{
			String[] columns = new String[]{"teamname","playername"};
			String MY_QUERY = "SELECT DISTINCT(teamname) FROM Teams";
			ArrayList<String> teamnames = new ArrayList<String>();
			
			Cursor c = aDatabase.rawQuery(MY_QUERY, null);
			
			int i=c.getColumnIndex("teamname");
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				teamnames.add(c.getString(i));
			}
			return teamnames;
		}
}
