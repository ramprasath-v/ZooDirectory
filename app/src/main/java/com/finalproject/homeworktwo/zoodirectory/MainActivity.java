package com.finalproject.homeworktwo.zoodirectory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Happy Hollow Park");
        final List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Lion", "lion.jpeg","The lion is one of the five big cats in the genus Panthera and a member of the family Felidae."));
        animals.add(new Animal("Tiger", "tiger.jpeg","The tiger is the largest cat species, reaching a total body length of up to 3.38 m over curves"));
        animals.add(new Animal("Elephant", "elephant.jpeg","Elephants are large mammals of the family Elephantidae and the order Proboscidea."));
        animals.add(new Animal("Bear", "bear.jpeg","Bears are mammals of the family Ursidae. Bears are classified as caniforms"));
        animals.add(new Animal("Rhino", "rhino.jpeg","Rhinoceros, often abbreviated as rhino, is a group of five extant species of odd-toed ungulates in the family Rhinocerotidae. "));
        animals.add(new Animal("Giraffe", "giraffe.jpeg","The giraffe is an African even-toed ungulate mammal, the tallest living terrestrial animal and the largest ruminant.  "));
        animals.add(new Animal("Zebra", "zebra.jpeg","Zebras are several species of African equids (horse family) united by their distinctive black and white striped coats."));

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new CustomAdapter(this, R.layout.custom_row, animals));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Animal animal = animals.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("name", animal.getName());
                bundle.putString("filepath", animal.getFileName());
                bundle.putString("description", animal.getDescription());
                if(position == animals.size()-1)
                {
                    openAlert(view,bundle);
                    return;
                }
                Intent intent = new Intent(MainActivity.this, AnimalDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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

    private void openAlert(View view, final Bundle bundle) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("This animal is very scary. Do you want to continue?");
        alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Intent intent = new Intent(getApplicationContext(),AnimalDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}