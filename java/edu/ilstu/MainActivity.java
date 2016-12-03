package edu.ilstu;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab, fab1, fab2;
    private Boolean isFabOpen = false;
    private Animation fab1_open, fab2_open, fab_close, rotate_forward, rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1edit);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2send);
        fab1_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab1_open);
        fab2_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab2_open);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        if (fragment == null) {
            fragment = new CardFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();

        }

    }

    public void animateFab() {
        if(isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab2.startAnimation(fab_close);
            fab1.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        }
        else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab1_open);
            fab2.startAnimation(fab2_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                animateFab();
                Log.i("aramsey", "Big fAB tapped");
                break;
            case R.id.fab1edit:
                animateFab();
                Log.i("aramsey", "fab 1 tapped");
                //TODO create an activity/layout that can add a written question
//                Intent i = new Intent(this,AddQuestionActivity.class);
//                startActivity(i);
                break;
            case R.id.fab2send:
                animateFab();
                Log.i("aramsey", "fab 2 tapped");
                sendQuestions(v);
//                i = new Intent(this,CreateTimerActivity.class);
//                startActivity(i);
                break;
            default:
                Log.i("aramsey", "idk what you tapped");
                break;
        }
    }

    // Send the selected questions to a bluetooth devices
    private void sendQuestions(View v) {
        Log.i("blutooth", "bt button tapped");

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Log.i("aramsey", btAdapter.toString());

        Context staticContext = Project3Bluetooth.getAppContext();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String contentToSend = "";







        for(SAQuestion q : CardFragment.itemsToSend) {  // TODO find a way to get the card that was tapped and access that part of the array
            if(q != null) // need to create the logic to assign the CheckBox objects to each question
                contentToSend += q.toString();
        }
        intent.putExtra(Intent.EXTRA_TEXT, contentToSend);

        final PackageManager pm = staticContext.getPackageManager();
        List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

        if(appsList.size() > 0){
            String packageName = null;
            String className = null;
            boolean found = false;

            for(int i = 0; i < appsList.size(); i++){
                // find bluetooth in the list of activities
                packageName = appsList.get(i).activityInfo.packageName;
                if(packageName.equals("com.android.bluetooth")){
                    className = appsList.get(i).activityInfo.name;
                    found = true;
                    break;
                }
            }
            if(! found){
                Toast.makeText(staticContext, R.string.blu_notfound_inlist,
                        Toast.LENGTH_SHORT).show();
//                getActivity().finish();
            }

            intent.setClassName(packageName, className);
            startActivity(intent);
        }
    }
}
