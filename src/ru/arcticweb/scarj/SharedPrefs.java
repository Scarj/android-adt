package ru.arcticweb.scarj;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{
	
	EditText sharedData;
	TextView dataResults;
	Button save,load;
	SharedPreferences someData;
	
	public static String filename = "MySharedString";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(filename, MODE_PRIVATE);
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		save = (Button)findViewById(R.id.bSave);
		load = (Button)findViewById(R.id.bLoad);
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		String stringData;
		
		switch (v.getId()) {
		case R.id.bSave:
			stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString",stringData);
			editor.commit();
			break;

		case R.id.bLoad:
			someData = getSharedPreferences(filename, MODE_PRIVATE);
			stringData = someData.getString("sharedString", "Couldn't Load Dats");
			dataResults.setText(stringData);
			break;
		}
	}
	
	

}
