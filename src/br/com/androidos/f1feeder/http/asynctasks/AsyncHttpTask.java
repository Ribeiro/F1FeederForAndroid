package br.com.androidos.f1feeder.http.asynctasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.androidos.f1feeder.http.handlers.HttpHandler;

public class AsyncHttpTask extends AsyncTask<String, Void, String> {

	private HttpHandler httpHandler;
	
	private ProgressDialog dialog;

	public AsyncHttpTask(HttpHandler httpHandler, Context context) {
		this.httpHandler = httpHandler;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected String doInBackground(String... arg0) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make the http request
			HttpResponse httpResponse = httpclient.execute(httpHandler.getHttpRequestMethod());

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null){
				result = convertInputStreamToString(inputStream);
				
			}else{
				result = "Did not work!";
				
			}

		} catch (Exception e) {
			Log.e("InputStream", e.getLocalizedMessage(), e);
		}
		
		if (dialog.isShowing()) {
            dialog.dismiss();
		}

		return result;
	}
	
	/*@Override
    protected void onPreExecute() {
        dialog.setMessage("Loading Data ...");
        dialog.show();
    }*/

	@Override
	protected void onPostExecute(String result) {
		 httpHandler.onResponse(result);
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}

}
