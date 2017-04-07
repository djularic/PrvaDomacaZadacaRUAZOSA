package com.jularic.dominik.prvadomacazadaca;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.commons.math3.complex.Complex;

import static org.achartengine.ChartFactory.*;

public class GraphActivity extends AppCompatActivity {

    public static final String INTENT_PARAMETER_FIRST_NUMBER = "first_number";
    public static final String INTENT_PARAMETER_SECOND_NUMBER = "second_number";
    public static final String INTENT_PARAMETER_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intet = getIntent();

        Complex first_number = (Complex) getIntent().getSerializableExtra(INTENT_PARAMETER_FIRST_NUMBER);
        Complex second_number = (Complex) getIntent().getSerializableExtra(INTENT_PARAMETER_SECOND_NUMBER);
        Complex result = (Complex) getIntent().getSerializableExtra(INTENT_PARAMETER_RESULT);

        TimeSeries seriesFirstNumber = new TimeSeries("Prvi operand");
        seriesFirstNumber.add(0,0);
        seriesFirstNumber.add(first_number.getReal(),first_number.getImaginary());


        TimeSeries seriesSecondNumber = new TimeSeries("Drugi operand");
        seriesSecondNumber.add(0,0);
        seriesSecondNumber.add(second_number.getReal(),second_number.getImaginary());

        TimeSeries seriesResult = new TimeSeries("Rezultat");
        seriesResult.add(0,0);
        seriesResult.add(result.getReal(),result.getImaginary());

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(seriesFirstNumber);
        dataset.addSeries(seriesSecondNumber);
        dataset.addSeries(seriesResult);
        //dataset.addSeries(series);

        XYSeriesRenderer rendererFirstNumber = new XYSeriesRenderer();
        rendererFirstNumber.setColor(Color.RED); //Set the color of the line to white
        rendererFirstNumber.setPointStyle(PointStyle.SQUARE); //Set the Points to be a square
        rendererFirstNumber.setFillPoints(true); //fill in the points with the color selects
        rendererFirstNumber.setAnnotationsColor(Color.WHITE);
        rendererFirstNumber.setChartValuesTextSize(60);
        rendererFirstNumber.setLineWidth(5);

        XYSeriesRenderer rendererSecondNumber = new XYSeriesRenderer();
        rendererSecondNumber.setColor(Color.BLUE); //Set the color of the line to white
        rendererSecondNumber.setPointStyle(PointStyle.SQUARE); //Set the Points to be a square
        rendererSecondNumber.setFillPoints(true); //fill in the points with the color selects
        rendererSecondNumber.setAnnotationsColor(Color.WHITE);
        rendererSecondNumber.setChartValuesTextSize(60);
        rendererSecondNumber.setLineWidth(5);

        XYSeriesRenderer rendererResult = new XYSeriesRenderer();
        rendererResult.setColor(Color.BLACK); //Set the color of the line to white
        rendererResult.setPointStyle(PointStyle.SQUARE); //Set the Points to be a square
        rendererResult.setFillPoints(true); //fill in the points with the color selects
        rendererResult.setAnnotationsColor(Color.WHITE);
        rendererResult.setChartValuesTextSize(60);
        rendererResult.setLineWidth(5);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(rendererFirstNumber);
        mRenderer.addSeriesRenderer(rendererSecondNumber);
        mRenderer.addSeriesRenderer(rendererResult);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setChartTitleTextSize(40);
        mRenderer.setChartTitle("Kompleksni brojevi");
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setAxisTitleTextSize(40);
        mRenderer.setShowAxes(true);
        mRenderer.setShowGrid(true);
        mRenderer.setGridColor(Color.BLACK);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setLabelsTextSize(35);

        mRenderer.setMarginsColor(Color.WHITE);

        GraphicalView chartView = ChartFactory.
                getLineChartView(this, dataset, mRenderer);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.chart);
        linearLayout.addView(chartView,0);

    }
}
