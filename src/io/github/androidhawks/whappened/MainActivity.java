package io.github.androidhawks.whappened;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
//import android.view.View.OnClickListener;
//import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LocationManager locationManager;
	//private Location currentLocation;
	private TextView txtLatitude;
	private TextView txtLongitude;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get handles to the elements on our android activity page.
        this.txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        this.txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        

        
        
	// Get an instance of the android system LocationManager 
	// so we can access the phone's GPS receiver
	this.locationManager = 
		(LocationManager) getSystemService(Context.LOCATION_SERVICE);

	// Subscribe to the location manager's updates on the current location
		this.locationManager.requestLocationUpdates("gps", (long)30000, (float) 10.0, new LocationListener()
			{
				public void onLocationChanged(Location arg0) 
				{
					handleLocationChanged(arg0);
				}

				public void onProviderDisabled(String arg0) {
					// TODO Auto-generated method stub

				}

				public void onProviderEnabled(String arg0) {
					// TODO Auto-generated method stub

				}

				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
					// TODO Auto-generated method stub
				}
			});
	    }

	private void handleLocationChanged(Location loc) {
		// Save the latest location
		//this.currentLocation = loc;
		// Update the latitude & longitude TextViews
		this.txtLatitude.setText(Double.toString(loc.getLatitude()));
		this.txtLongitude.setText(Double.toString(loc.getLongitude()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_about:
	            Intent about = new Intent(this,AboutWhappened.class);
	            startActivity(about);
	            return true;
	        case R.id.action_user_info:
	        	Intent user_info = new Intent(this,DisplayUserInfoActivity.class);
	            startActivity(user_info);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	public void onNU_Click(View view) {   
	    try{
	      Intent userIntent = new Intent(this,NewUserActivity.class);
	      startActivity(userIntent);
	    }catch(Exception e){
	    		System.out.println(e);
	    }
	}
	public void onNE_Click(View view){   
		   Intent eventIntent = new Intent(this,NewEventActivity.class);
		   startActivity(eventIntent);

	}
	/*
	public void onUC_Click(View view){
		try{
		      Intent checkUserIntent = new Intent(this,DisplayUserInfoActivity.class);
		      startActivity(checkUserIntent);
		}catch(Exception e){
    		System.out.println(e);
		}
	}
	*/
	public void onVE_Click(View view){
		try{
		      Intent checkUserIntent = new Intent(this,ViewEvents.class);
		      startActivity(checkUserIntent);
		}catch(Exception e){
    		System.out.println(e);
		}
	}
	  

}
