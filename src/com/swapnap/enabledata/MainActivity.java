package com.swapnap.enabledata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;
import android.content.Context;


public class MainActivity extends Activity {

	ConnectivityManager cM;
	NetworkInfo nI;
	ToggleButton toggle;
	
			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		cM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		nI = cM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		toggle = (ToggleButton) findViewById(R.id.togglebutton);
		
			if(nI.isConnected()){
				toggle.setChecked(true);
			}
			else{
				toggle.setChecked(false);
			}
	    
	     toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				     Method dataMtd = null;
				     try {
						dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				     dataMtd.setAccessible(true);
				     
				     if (isChecked) {
					       try {
							dataMtd.invoke(cM, true);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					       Toast.makeText(MainActivity.this, "Data Enabled", Toast.LENGTH_SHORT).show();
				    } 
					else {
						  try {
							dataMtd.invoke(cM, false);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  Toast.makeText(MainActivity.this, "Data Disabled", Toast.LENGTH_SHORT).show();
				    }
	        }
	    });
    }
 
}
