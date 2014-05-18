package io.github.androidhawks.whappened;

import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class NewEventActivity extends Activity {
	private EventDataSource datasource;
	String location = "you are here";
	EditText comment_field;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;
	String mCurrentPhotoPath;
	ImageView mImageView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		//setContentView(R.layout.activity_new_event);
		datasource = new EventDataSource(getApplicationContext());
		datasource.open();
		}catch(Exception e){
			System.out.println("neweventactivity:oncreate :" + e);
		}
		setContentView(R.layout.activity_new_event);
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
	
	public void recSelect(View view){
		commitToDB("recommened");
	}
	
	public void negRecSelect(View view){
		commitToDB("not_recommened");
	}
	
	public void commitToDB(String rec){
		comment_field = (EditText) findViewById(R.id.event_comment_editText);
		CharSequence comment = comment_field.getText();
		String user_id = "00000001";
		Date today = new Date();
		String date = today.toString();
		String[] parsed_date = date.split("[ ]+");
		date = parsed_date[0] + " " + parsed_date[3] ; //Parses the Day of the week and Hour from the date object
		try{
			System.out.println(comment.toString() +" ... " + location.toString());
		datasource.createEvent(comment.toString(), user_id, location.toString(), date, rec);
		}catch(Exception e){
			System.out.println("in NewEventActivity.commitToDB: "+e);
		}
		Intent checkUserIntent = new Intent(this,MainActivity.class);
	    startActivity(checkUserIntent);
	}
	
//	public void TakeEventPhoto(View view){
//		dispatchTakePictureIntent();
		
		
//	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	    	try{
	        //Bundle extras = data.getExtras();
	        //Bitmap imageBitmap = (Bitmap) extras.get("data");
	       /* mImageView.setImageURI(mCurrent);*/
	        File f = new File(mCurrentPhotoPath);
	        System.out.println("file: "+f);
		    Uri contentUri = Uri.fromFile(f);
		    System.out.println("Uri: "+contentUri);
		    mImageView = (ImageView)findViewById(R.id.user_event_photo);
			mImageView.setImageURI(contentUri);
	    	}catch(Exception e){
	    		System.out.println("failed onActivityResult"+e);
	    	}
	    }
	}
	
	/*
	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  // prefix
	        ".jpg",         // suffix
	        storageDir      // directory
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	*/

/*
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {
	            // Error occurred while creating the File
	            System.out.println("dispTakePic: "+ex);
	        }
	        // Continue only if the File was successfully created
	        if (photoFile != null) {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
	    }
	}

*/
}
