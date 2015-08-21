package com.ruanlopes.vidainc.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.ruanlopes.vidainc.Constant;
import com.ruanlopes.vidainc.Fragments.FragmentOne;
import com.ruanlopes.vidainc.Fragments.FragmentTwo;
import com.ruanlopes.vidainc.R;

public class MainActivity extends AppCompatActivity {

    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    ImageButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FAB = (ImageButton) findViewById(R.id.imageButton);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constant.edited == true) {

                    //MainActivity.this.recreate();
                    startActivity(new Intent(MainActivity.this, EnteredRoom.class));

                } else {

                    startActivity(new Intent(MainActivity.this, EnteredRoom.class));

                    /*new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Alert")
                            .setMessage("You Have to add a room")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();*/
                }

            }
        });

        if (Constant.edited == false){


            fr = new FragmentOne();

            fm = getFragmentManager();
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fr);
            fragmentTransaction.commit();

        } else {

            FAB.setVisibility(View.INVISIBLE);

            fr = new FragmentTwo();

            fm = getFragmentManager();
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fr);
            fragmentTransaction.commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
