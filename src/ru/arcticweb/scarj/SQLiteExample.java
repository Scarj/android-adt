package ru.arcticweb.scarj;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView;
	EditText sqlName, sqlHotness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);

		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etHottness);

		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String name;
		String hotness;
		
		switch (v.getId()) {
		case R.id.bSQLUpdate:
			
			boolean didItWork = true;
			
			try {
				name = sqlName.getText().toString();
				hotness = sqlHotness.getText().toString();
				
				HotOrNot entry = new HotOrNot(SQLiteExample.this);
				entry.open();
				entry.createEntry(name,hotness);
				entry.close();
			} catch (Exception e) {
				// TODO: handle exception
				didItWork = false;
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;

		case R.id.bSQLopenView:
			Intent i = new Intent("ru.arcticweb.scarj.SQLVIEW");
			startActivity(i);
			break;
		}
	}
}
