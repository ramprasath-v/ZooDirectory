package com.finalproject.homeworktwo.zoodirectory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ZooInformation extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_zoo);

        TextView zooName = (TextView) findViewById(R.id.textView);
        zooName.setText("Happy Hollow Park & Zoo ");


        TextView zooInformation = (TextView) findViewById(R.id.textView3);
        zooInformation.setText("Happy Hollow Park & Zoo in San Jose has been a part of Bay Area families since it opened its’ gates in 1961. It’s a unique combination of family rides, amusements, a Puppet Theater, play areas and an accredited Zoo with over 140 animals, from endangered lemurs to miniature horses. Year round classes are offered for ages 12 months to adults as well as special events for all seasons. Happy Hollow Park & Zoo offers yearly Memberships for families and individuals. ");
        Button callButton = (Button) findViewById(R.id.button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }
    protected void makeCall(){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:888-8888"));
        try{
            startActivity(phoneIntent);
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ZooInformation.this,
                    "Call failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            return true;
        }
        else if (id == R.id.uninstall) {
            Uri packageURI = Uri.parse("package:com.finalproject.homeworktwo.zoodirectory");
            Intent deleteIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(deleteIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
