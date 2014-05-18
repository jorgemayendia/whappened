package io.github.androidhawks.whappened;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;


import android.os.Bundle;

import android.app.ListActivity;
//import android.app.ProgressDialog;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;


public class ViewEvents extends ListActivity {
	String event_list_data;
	EventDataSource datasource;
	List<EventDAO> event;
	EventDAO current_event;
	int listlength;
	String is_rec;
	String loc;
	String d_t;
	String com;
	ArrayList<String> DAO_list;
	//private ProgressDialog pDialog;
	//private static String url = "https://spreadsheets.google.com/feeds/list/107Lkj-QKBKjX2hXaW-XMQvE6b70aEAW87oZtOK84PUM/od6/public/values?alt=json";
	public static final String TABLE_EVENT = "events";
	public static final String EVENT_COLUMN_ID = "_id";
	public static final String EVENT_COLUMN_COMMENT = "event_comment";
	public static final String EVENT_COLUMN_USER_ID = "event_user_id";
	public static final String EVENT_COLUMN_LOCATION = "event_location";
	public static final String EVENT_COLUMN_DATE_TIME = "event_date_time";
	public static final String EVENT_COLUMN_IS_RECOMMENDED = "event_is_recommended";
	
	JSONArray event_array = null;
	ArrayList<HashMap<String, String>> eventList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_events);
		//ArrayList<String> list = new ArrayList<String>();
		/*
		for (int i = 0; i < event_list_data.length; ++i) {
		      list.add(event[i]);
		 }
		*/
		datasource = new EventDataSource(this);
		event = datasource.getAllEvents();
		listlength = event.size();
		System.out.println("listlength = " + listlength);
		ArrayList<String> DAO_list = new ArrayList<String>();
		for(int i = 0; i<listlength;i++){
			current_event = event.get(i);
			is_rec = current_event.getIsRecommended();
			loc = current_event.getLocation();
			d_t = current_event.getDate_time();
			com = current_event.getComment();
			event_list_data = new String(is_rec+"*^*"+loc+"*^*"+d_t+"*^*"+com);	
			DAO_list.add(event_list_data);
		}
		WhappenedArrayAdapter adapter = new WhappenedArrayAdapter(this,DAO_list);
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,event_list_data);
		//ListView listview = (ListView)findViewById(R.id.viewEvents_listView);
		try{
			//new GetEvents().execute();
			//System.out.println("listview to string: " + listview.toString());
			System.out.println("GetEvents.execute()");
			//listview.setAdapter(adapter);
			setListAdapter(adapter);
		}catch(Exception e){
			System.out.println("viewevents.oncreate: "+e);
		}
		setupActionBar();

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	
	/*
	private class GetEvents extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ViewEvents.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
 
            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    event_array = jsonObj.getJSONArray(TABLE_EVENT);
 
	                DAO_list = new ArrayList<String>();
	        		for(int i = 0; i<event_array.length();i++){
	        			JSONObject c = event_array.getJSONObject(i);
	        			
	        			is_rec = c.getString(EVENT_COLUMN_IS_RECOMMENDED);
	        			loc = c.getString(EVENT_COLUMN_LOCATION);
	        			d_t = c.getString(EVENT_COLUMN_DATE_TIME);
	        			com = c.getString(EVENT_COLUMN_COMMENT);
	        			event_list_data = new String(is_rec+"*^*"+loc+"*^*"+d_t+"*^*"+com);	
	        			DAO_list.add(event_list_data);
	        		}
               
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * 
            WhappenedArrayAdapter adapter = new WhappenedArrayAdapter(ViewEvents.this,DAO_list);
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, eventList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
                            TAG_PHONE_MOBILE }, new int[] { R.id.name,
                            R.id.email, R.id.mobile });
 
            setListAdapter(adapter);
        }
 
    }
*/
}
