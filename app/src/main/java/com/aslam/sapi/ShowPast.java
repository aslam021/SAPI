package com.aslam.sapi;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ShowPast extends AppCompatActivity {

    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_past);

        getParams();

        GraphView graph = findViewById(R.id.graph);
        TextView tv = findViewById(R.id.tvInfo);

        tv.setText("Past hazard percentage values of "+ selectedCity);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(1, 1),

                new DataPoint(2, 5),

                new DataPoint(3, 3),

                new DataPoint(4, 2),

                new DataPoint(5, 6)

        });

        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 4),

                new DataPoint(2, 4),

                new DataPoint(3, 4),

                new DataPoint(4, 4),

                new DataPoint(5, 4)
        });

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setPathEffect(new DashPathEffect(new float[]{8,5},0));

        series1.setCustomPaint(paint);
        series1.setAnimated(true);
        series1.setDrawAsPath(true);
        series1.setThickness(2);

        series.isDrawDataPoints();
        series.setDrawDataPoints(true);
        series.setThickness(3);
        series.setAnimated(true);

        graph.addSeries(series);
        graph.addSeries(series1);
    }

    //get parameters from previous activity
    private void getParams() {
        selectedCity = getIntent().getStringExtra("selectedCity");
    }
}
