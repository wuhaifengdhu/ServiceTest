package com.whf.servicetest;

import java.util.List;

import com.androidhive.androidsqlite.Contact;
import com.androidhive.androidsqlite.DatabaseHandler;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Button startService,stopService,sendMail,receiveMail,sqliteTest;
	@Override
	@SuppressLint("NewApi") 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService=(Button)findViewById(R.id.startService);
		stopService=(Button)findViewById(R.id.stopService);
		sendMail=(Button)findViewById(R.id.sendMail);
		receiveMail=(Button)findViewById(R.id.receiveMail);
		sqliteTest=(Button)findViewById(R.id.sqliteTest);
		
		startService.setOnClickListener(listener);
		stopService.setOnClickListener(listener);
		sendMail.setOnClickListener(listener);
		receiveMail.setOnClickListener(listener);
		sqliteTest.setOnClickListener(listener);
		
		  StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()    
          .detectDiskReads()    
          .detectDiskWrites()    
          .detectNetwork()   // or .detectAll() for all detectable problems    
          .penaltyLog()    
          .build());    
          StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()    
          .detectLeakedSqlLiteObjects()    
          .detectLeakedClosableObjects()    
          .penaltyLog()    
          .penaltyDeath()    
          .build());
	}
	
	private OnClickListener listener=new OnClickListener(){
		
		@Override
		public void onClick(View btn) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,MyService.class);
			switch(btn.getId()){
				case R.id.startService: startService(intent);
					break;
				case R.id.stopService: stopService(intent);
					break;	
				case R.id.receiveMail: new Mail163().getEmail();
				    break;
				case R.id.sendMail: new MailStore().sendEmail("mail test吴海峰");
					break;
				case R.id.sqliteTest:dataManipTest();
					break;
			}
		}
		
	};
	public void dataManipTest(){
		DatabaseHandler db = new DatabaseHandler(this);
        
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d(TAG, "Inserting .."); 
        db.addContact(new Contact("Ravi", "9100000000"));        
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
         
        // Reading all contacts
        Log.d(TAG, "Reading all contacts.."); 
        List<Contact> contacts = db.getAllContacts();       
         
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
            Log.d(TAG,"Name: "+log);
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
