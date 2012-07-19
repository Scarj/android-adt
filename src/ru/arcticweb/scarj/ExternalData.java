package ru.arcticweb.scarj;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ExternalData extends Activity implements OnItemSelectedListener{
	
	private TextView canWrite, canRead;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = {"Music", "Pictures", "Downloads"};
	File path = null;

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
			canR = canW = true;
		} else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// read&!write
			canWrite.setText("false");
			canRead.setText("true");
			canR = true; canW = false;
		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canR = false; canW = true;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item,paths);
		
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;

		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;

		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;			
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
