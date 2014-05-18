package io.github.androidhawks.whappened;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;


public class AboutWhappened extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_whappened);
		// Show the Up button in the action bar.
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

	@Override
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
