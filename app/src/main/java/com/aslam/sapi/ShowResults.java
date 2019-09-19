package com.aslam.sapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
public class ShowResults extends AppCompatActivity {

    String selectedCity;
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    int currentHazarad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);


        getParams();

        getCurrentHazard();

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

//      getCurrentHazard();


        currentHazarad = 53;

        tv =  findViewById(R.id.tv);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus <= currentHazarad && pStatus < 100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tv.setText(currentHazarad + "%");
            }
        }).start();

        Button btnViewPast = findViewById(R.id.btnPast);
        btnViewPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowResults.this, ShowPast.class);
                intent.putExtra("selectedCity", selectedCity);
                startActivity(intent);
            }
        });

    }

    private void getCurrentHazard() {
        //TODO get data from database
//
//        databaseReference.child("readings").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                final List<String> driverIds = new ArrayList<>();
//
//                for(DataSnapshot driverSnapShot: dataSnapshot.getChildren()){
//                    String driverId = driverSnapShot.child("uid").getValue(String.class);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    //get parameters from previous activity
    private void getParams() {
        selectedCity = getIntent().getStringExtra("selectedCity");
    }

}


