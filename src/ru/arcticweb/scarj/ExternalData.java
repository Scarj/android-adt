package ru.arcticweb.scarj;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class ExternalData extends Activity{
	
	private TextView canWrite, canRead;
	private String state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);

		state = Environment.getExternalStorageState();
		
		if(state.equals(Environment.MEDIA_MOUNTED)){
			// read&write
			canWrite.setText("true");
			canRead.setText("true");			
		} else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// read&!write
			canWrite.setText("false");
			canRead.setText("true");
		} else {
			canWrite.setText("false");
			canRead.setText("false");
		}
	}
	
}
