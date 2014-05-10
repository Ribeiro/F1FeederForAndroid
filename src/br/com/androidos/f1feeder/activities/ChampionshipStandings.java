package br.com.androidos.f1feeder.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.androidos.f1feeder.R;
import br.com.androidos.f1feeder.adapters.DriverAdapter;
import br.com.androidos.f1feeder.domain.Driver;
import br.com.androidos.f1feeder.http.handlers.HttpHandler;

public class ChampionshipStandings extends Activity {

	private JSONArray constructors = null;
	private List<Driver> driversList;
	private ListView listView;
	private Driver driver;
	private ProgressDialog progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_championship_standings);
		listView = (ListView) findViewById(R.id.driversListing);

	}
	
	private void addOnItemClickListenerTo(ListView listView){
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {

				driver = (Driver) adapterView.getItemAtPosition(position);
				Intent intent = new Intent(ChampionshipStandings.this, DriverDetails.class);
				intent.putExtra("selectedDriver", driver);
				startActivity(intent);

			}

		});

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		driversList = new ArrayList<Driver>();
		
		progressBar = ProgressDialog.show(ChampionshipStandings.this, "", "Loading...", true);
		
		addOnItemClickListenerTo(listView);
		
		new HttpHandler() {
			
			final String URL = "http://ergast.com/api/f1/2014/driverStandings.json";
            @Override
            public HttpUriRequest getHttpRequestMethod() {
                return new HttpGet(URL);

            }
            
            @Override
            public void onResponse(String result) {
            	try {
					JSONObject jsonObj = new JSONObject(result);
					constructors = jsonObj.getJSONObject("MRData").getJSONObject("StandingsTable").getJSONArray("StandingsLists").getJSONObject(0).getJSONArray("DriverStandings");
					
					Driver currentDriver = null;
					
                    for (int i = 0; i < constructors.length(); i++) {
                        JSONObject constructor = constructors.getJSONObject(i);
                        JSONObject driver = constructor.getJSONObject("Driver");
                        JSONArray constructorDetails = new JSONArray(constructor.getString("Constructors"));
                        String scuderia = constructorDetails.getJSONObject(0).get("name").toString();
                        Long id = Long.parseLong(driver.getString("permanentNumber"));
                        String code = driver.getString("code");
                        String url = driver.getString("url");
                        String givenName = driver.getString("givenName");
                        String familyName = driver.getString("familyName");
                        String dateOfBirth = driver.getString("dateOfBirth");
                        String nationality = driver.getString("nationality");
                        Integer position = Integer.parseInt(constructor.getString("position")); 
                        Integer points = Integer.parseInt(constructor.getString("points"));
                        Integer wins = Integer.parseInt(constructor.getString("wins"));
                        String driverId = driver.getString("driverId");
                        
                        currentDriver = new Driver(id, code, url, givenName, familyName, dateOfBirth, nationality, position, points, wins, driverId, nationality.toLowerCase(), scuderia);
                        driversList.add(currentDriver);
                        
                    }
					
                    Log.i("MainActivity", "Championship Data retrieved!");
                    
                    DriverAdapter adapter = new DriverAdapter(ChampionshipStandings.this, driversList);
            		listView.setAdapter(adapter);
					
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("MainActivity", e.getMessage(), e);
				}finally {
					if (progressBar.isShowing()) {
		                progressBar.dismiss();
		            }
				}
            	
            }

        }.execute(ChampionshipStandings.this);
        
		
		
	}

}
