package br.com.androidos.f1feeder.http.handlers;

import org.apache.http.client.methods.HttpUriRequest;

import android.content.Context;
import br.com.androidos.f1feeder.http.asynctasks.AsyncHttpTask;

public abstract class HttpHandler {
	
	public abstract HttpUriRequest getHttpRequestMethod();

    public abstract void onResponse(String result);

    public void execute(Context context){
        new AsyncHttpTask(this, context).execute();
    } 

}
