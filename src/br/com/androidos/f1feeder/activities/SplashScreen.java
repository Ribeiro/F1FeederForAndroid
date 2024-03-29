package br.com.androidos.f1feeder.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.com.androidos.f1feeder.R;

public class SplashScreen extends Activity {

	 private static int SPLASH_TIME_OUT = 1500;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash_screen);
	 
	        new Handler().postDelayed(new Runnable() {
	 
	            @Override
	            public void run() {
	                Intent i = new Intent(SplashScreen.this, ChampionshipStandings.class);
	                startActivity(i);
	 
	                finish();
	            }
	            
	        }, SPLASH_TIME_OUT);
	    }

}
