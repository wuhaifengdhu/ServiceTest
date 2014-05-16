package com.whf.servicetest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class MyService extends Service {
    public static int tabService=0;
    Vibrator vibrator;
    int NOTIFY_ID=0;
    String Oldrid="";
    boolean notificationflag=false;
    int lastlength=0;
    private Timer timer1=new Timer();    

    private static long UPDATE_INTERVAL = 1*5*1000;  //default


    Context ctx;
    private static Timer timer = new Timer(); 
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        _startService();

    }   



    private void _startService()
    {      
        timer.scheduleAtFixedRate(    

                new TimerTask() {

                    public void run() {

                        doServiceWork();

                    }
                }, 1000,UPDATE_INTERVAL);
                Log.i(getClass().getSimpleName(), "FileScannerService Timer started....");
    }



    private void doServiceWork()
    { 
        //do something wotever you want 
        //like reading file or getting data from network 
        try {

        	Log.i(getClass().getSimpleName(), "do Service Work....");

        }
        catch (Exception e) {


        }

    }


    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }

    @Override 
    public void onDestroy() 
    {
        super.onDestroy();

        _shutdownService();

        // if (MAIN_ACTIVITY != null)  Log.d(getClass().getSimpleName(), "FileScannerService stopped");
    }

}
