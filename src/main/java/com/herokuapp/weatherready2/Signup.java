package com.herokuapp.weatherready2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Signup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences appPref = getSharedPreferences("preference",0);
        String firstName = appPref.getString("firstName", "");
        if(firstName.equals("")==false){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
    public void saveBtnClick(View v){
        SharedPreferences preferences = getSharedPreferences("preference",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstName",((TextView)findViewById(R.id.firstNameEdit)).getText().toString());
        editor.putString("lastName",((TextView)findViewById(R.id.lastNameEdit)).getText().toString());
        editor.putString("location", ((TextView) findViewById(R.id.locationEdit)).getText().toString());
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
