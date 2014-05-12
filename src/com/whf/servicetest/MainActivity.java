package com.whf.servicetest;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button startService,stopService,sendMail,receiveMail;
	@Override
	@SuppressLint("NewApi") 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService=(Button)findViewById(R.id.startService);
		stopService=(Button)findViewById(R.id.stopService);
		sendMail=(Button)findViewById(R.id.sendMail);
		receiveMail=(Button)findViewById(R.id.receiveMail);
		
		startService.setOnClickListener(listener);
		stopService.setOnClickListener(listener);
		sendMail.setOnClickListener(listener);
		receiveMail.setOnClickListener(listener);
		
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
			}
		}
		
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
