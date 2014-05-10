package br.com.androidos.f1feeder.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import br.com.androidos.f1feeder.R;
import br.com.androidos.f1feeder.domain.Driver;

public class DriverDetails extends Activity{
	
	private WebView webView;
	private Driver driver;
	private ProgressDialog progressBar;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.driver_details_webview);

		   webView = (WebView) findViewById(R.id.driverDetailsWebView);
		   
		   WebSettings settings = webView.getSettings();
		    settings.setJavaScriptEnabled(true);
		    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		    progressBar = ProgressDialog.show(DriverDetails.this, "", "Loading...", true);

		   Intent intent = getIntent();
		   driver = (Driver) intent.getSerializableExtra("selectedDriver");
		   
		   webView.setWebViewClient(new WebViewClient() {
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		            view.loadUrl(url);
		            return true;
		        }

		        public void onPageFinished(WebView view, String url) {
		            if (progressBar.isShowing()) {
		                progressBar.dismiss();
		            }
		        }

		        public void onReceivedError(WebView view, int errorCode,
		                String description, String failingUrl) {
		        	
		        	Toast.makeText(getApplicationContext(),"Error retrieving driver bio from WikiPedia!", Toast.LENGTH_LONG).show();
		        	
		        }
		    });

		   webView.loadUrl(driver.getUrl());

		}

}