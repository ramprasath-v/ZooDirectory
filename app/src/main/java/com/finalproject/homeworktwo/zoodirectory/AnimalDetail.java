package com.finalproject.homeworktwo.zoodirectory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import java.io.IOException;
import java.io.InputStream;


public class AnimalDetail extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_detail);

        Bundle bundle = getIntent().getExtras();

        ImageView imageView = (ImageView) findViewById(R.id.animalImage);
        TextView textViewName =(TextView) findViewById(R.id.animalName);
        TextView textViewDesp =(TextView) findViewById(R.id.animalDesp);

        String name = bundle.getString("name");
        String filePath = bundle.getString("filepath");
        String description = bundle.getString("description");
        setTitle(name);


        try {
            InputStream inputStream  = getApplicationContext().getAssets().open(filePath);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textViewName.setText(name);
        textViewDesp.setText(description);
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
            Intent intent = new Intent(this, ZooInformation.class);
            startActivity(intent);
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
