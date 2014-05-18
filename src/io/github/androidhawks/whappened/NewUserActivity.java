package io.github.androidhawks.whappened;


import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class NewUserActivity extends Activity {
	private UserDataSource datasource;
	private EditText first_name_field;
	private EditText last_name_field;
	private EditText email_address_field;
	private EditText password_field;
	private EditText password_confirm_field;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		datasource = new UserDataSource(getApplicationContext());
		datasource.open(getApplicationContext());
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
	
	

	public void createNewUser(View view){
		first_name_field = (EditText)findViewById(R.id.first_name_field);
		last_name_field = (EditText)findViewById(R.id.last_name_field);
		email_address_field = (EditText)findViewById(R.id.email_field);
		password_field = (EditText)findViewById(R.id.password_field);
		password_confirm_field = (EditText)findViewById(R.id.password_confirm_field);
		//Integer number = 1;
		CharSequence first_name = first_name_field.getText();
		CharSequence last_name = last_name_field.getText();
		CharSequence email_address = email_address_field.getText();
		CharSequence password = password_field.getText();
		CharSequence password_confirm = password_confirm_field.getText();
		Date today = new Date();
		String date_created = today.toString();
		String[] parsed_date = date_created.split("[ ]+");
		date_created = parsed_date[1] + " " + parsed_date[2] + " " + parsed_date[5];
		String user_profile = "UserProfile";
		String s_password = password.toString();
		if(s_password.contains(password_confirm)){
			datasource.createUser(first_name.toString(), last_name.toString(), email_address.toString(), password.toString(), date_created, user_profile);
			Intent userIntent = new Intent(this,MainActivity.class);
		    startActivity(userIntent);
		}
		else{
			TextView header = (TextView)findViewById(R.id.new_user_header);
			//String pass_err = (String)findStringById(R.string.password_mismatch);
			header.setText(R.string.password_mismatch);;
			
		}
	}
	
	  @Override
	  protected void onResume() {
	    datasource.open(getApplicationContext());
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
	
	
}