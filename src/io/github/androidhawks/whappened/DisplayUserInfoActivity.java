package io.github.androidhawks.whappened;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayUserInfoActivity extends Activity {
	public UserDataSource datasource;
	public TextView user_fn;
	public TextView user_ln;
	public TextView user_email;
	public TextView user_cd;
	UserDAO first_user;
	List<UserDAO> user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			setContentView(R.layout.activity_display_user_info);
		}catch(Exception e){
			System.out.println("in datasource.oncreate @ inflate " +e);
		}
		datasource = new UserDataSource(this);
		//datasource.open();
		System.out.println("On create for DisplayUserActivity" );
		try{
		user = datasource.getAllUsers();
		}catch(Exception e){
			System.out.println("in datasource.oncreate: " +e);
		}
		
		first_user = user.get(0);
		
		user_fn = (TextView) findViewById(R.id.disp_first_name);
		user_ln = (TextView) findViewById(R.id.disp_last_name);
		user_email = (TextView) findViewById(R.id.disp_email);
		user_cd = (TextView) findViewById(R.id.disp_created_date);
		try{
		user_fn.setText((CharSequence)first_user.getFirst_name());
		user_ln.setText((CharSequence)first_user.getLast_name());
		user_email.setText((CharSequence)first_user.getEmail());
		user_cd.setText((CharSequence)first_user.getCreated_date());
		}catch(Exception e){
			System.out.println("in DisplayUserInfoActivity"+e);
		}
		
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


	

}
