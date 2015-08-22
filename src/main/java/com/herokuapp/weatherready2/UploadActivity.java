package com.herokuapp.weatherready2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class UploadActivity extends Activity {

    String fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Intent intent = getIntent();
        fileUri = intent.getStringExtra("file");
        Log.d("File:",fileUri);
        UploadFileTask task = new UploadFileTask();
        task.execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class UploadFileTask extends AsyncTask<String, Void, Integer>{

        StringBody stringApi = new StringBody("YOUR_API_KEY", ContentType.TEXT_PLAIN);
        String url = "https://api.idolondemand.com/1/api/async/recognizespeech/v1";
        @Override
        protected Integer doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                File file = new File(fileUri);
                FileBody fileBody = new FileBody(file);
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.addPart("apikey", stringApi);
                builder.addPart("file", fileBody);
                HttpEntity entity = builder.build();
                HttpResponse response = client.execute(post);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                response.getEntity().writeTo(bao);
                Log.d("Response",bao.toString());
            }catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            return;
        }
    }
}
