package com.example.cricketscoreapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.widget.Toast;

public class ServerClass {
	
	
	public void sendingToServer(int runs) throws UnsupportedEncodingException
	{
		 HttpURLConnection connection;
	     OutputStreamWriter request = null;

	         URL url = null;   
	         String response = null;         
	         String parameters="runs="+Integer.toString(runs);   

	         try
	         {
	             url = new URL("http://10.0.2.2/cricketLive/operation.php");
	             connection = (HttpURLConnection) url.openConnection();
	             connection.setDoOutput(true);
	             connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	             connection.setRequestMethod("POST");    

	             request = new OutputStreamWriter(connection.getOutputStream());
	             request.write(parameters);
	             request.flush();
	             request.close();            
	             String line = "";               
	             InputStreamReader isr = new InputStreamReader(connection.getInputStream());
	             BufferedReader reader = new BufferedReader(isr);
	             StringBuilder sb = new StringBuilder();
	             while ((line = reader.readLine()) != null)
	             {
	                 sb.append(line);
	             }
	             // Response from server after login process will be stored in response variable.                
	             response = sb.toString();
	             // You can perform UI operations here
	             
	             
	             
	             
	             
	                          
	             isr.close();
	             reader.close();

	         }
	         catch(IOException e)
	         {
	        	
	         }
	 
		}
	
	
		
	
}
