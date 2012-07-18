package ru.arcticweb.scarj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	WebView ourBrow;
	EditText url;
	Button go,back,refresh,forward,clearHistory;
	InputMethodManager imm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		ourBrow.setWebViewClient(new ourViewClient());
		try {
			ourBrow.loadUrl("http://ya.ru");	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		go = (Button)findViewById(R.id.bGo);
		back = (Button)findViewById(R.id.bBack);
		refresh = (Button)findViewById(R.id.bRefresh);
		forward = (Button)findViewById(R.id.bForward);
		clearHistory = (Button)findViewById(R.id.bHistory);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
		
		url = (EditText) findViewById(R.id.etURL);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.bGo:
			String theWebsite = url.getText().toString();
			ourBrow.loadUrl(theWebsite);
			imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;

		case R.id.bBack:
			if(ourBrow.canGoBack())
				ourBrow.goBack();
			break;
			
		case R.id.bForward:
			if(ourBrow.canGoForward())
				ourBrow.goForward();
			break;
			
		case R.id.bRefresh:
			ourBrow.reload();
			break;
			
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		}		
	}
}