package com.costbear.android.thesexydriver;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.DecimalFormat;
import java.util.Arrays;


public class StatsActivity extends Activity {


    private XYPlot plot;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stats);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);

            setContentView(R.layout.simple_xy_plot_example);

            // initialize our XYPlot reference:
            plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

            // Create a couple arrays of y-values to plot:
            Number[] series1Numbers = {1, 8, 5, 2, 7, 4, 6, 4, 8, 9, 3, 8, 5};

            // Turn the above arrays into XYSeries':
            XYSeries series1 = new SimpleXYSeries(
                    Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                    SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                    "Series1");                             // Set the display title of the series

            // Create a formatter to use for drawing a series using LineAndPointRenderer
            // and configure it from xml:
            LineAndPointFormatter series1Format = new LineAndPointFormatter();
            series1Format.setPointLabelFormatter(new PointLabelFormatter());
            series1Format.configure(getApplicationContext(),
                    R.xml.line_point_formatter_with_plf1);

            // add a new series' to the xyplot:
            plot.addSeries(series1, series1Format);

            // reduce the number of range labels
            plot.setTicksPerRangeLabel(2);
            plot.getGraphWidget().setDomainLabelOrientation(-45);

            plot.getBackgroundPaint().setColor(Color.rgb(200, 227, 183));
            plot.setBorderStyle(XYPlot.BorderStyle.NONE, null, null);
            plot.getGraphWidget().getBackgroundPaint().setColor(Color.rgb(200, 227, 183));
            plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.rgb(200, 227, 183));

            // Domain
            plot.getGraphWidget().setDomainLabelPaint(null);
            plot.getGraphWidget().setDomainOriginLinePaint(null);
            plot.setDomainValueFormat(new DecimalFormat("0"));

            //Range
            plot.getGraphWidget().setRangeOriginLinePaint(null);
            plot.setRangeValueFormat(new DecimalFormat("0"));

            //Remove legend
            plot.getLayoutManager().remove(plot.getLegendWidget());
            plot.getLayoutManager().remove(plot.getDomainLabelWidget());
            plot.getLayoutManager().remove(plot.getRangeLabelWidget());
            plot.getLayoutManager().remove(plot.getTitleWidget());
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
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
