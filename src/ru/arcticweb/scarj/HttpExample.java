package ru.arcticweb.scarj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity{

	TextView httpStuff;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
	}
	

}
