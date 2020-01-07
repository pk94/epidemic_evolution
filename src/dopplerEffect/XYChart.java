package dopplerEffect;

import java.awt.Dimension;
import java.io.File;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JPanel;
/**
 * This class creates new XY chart with specified series of data.
 * @author Pawe³ Kowaleczko
 *
 */

public class XYChart {
	XYSeries dataSet = new XYSeries("S(t) - Simulation");
	XYSeries dataSet2 = new XYSeries("I(t) - Simulation");
	XYSeries dataSet3 = new XYSeries("R(t) - Simulation");
	XYSeries dataSet4 = new XYSeries("S(t) - Theory");
	XYSeries dataSet5 = new XYSeries("I(t) - Theory");
	XYSeries dataSet6 = new XYSeries("R(t) - Theory");
	XYSeries cleanDataSet = new XYSeries("");
	XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSet);
	XYDataset xyDataset = xySeriesCollection;
	JFreeChart lineGraph = ChartFactory.createXYLineChart("Model SIR", // Title
			"time ", // X-Axis label
			"Number", // Y-Axis label
			xyDataset, // Dataset
			PlotOrientation.VERTICAL, // Plot orientation
			true, // show legend
			true, // Show tooltips
			false // url show
			);
	ChartPanel chartPanel = new ChartPanel(lineGraph);

	public XYChart(JPanel panel) {
		chartPanel.setPreferredSize(new Dimension (492,350));
		panel.add(chartPanel);
		xySeriesCollection.addSeries(dataSet2);
		xySeriesCollection.addSeries(dataSet3);
		xySeriesCollection.addSeries(dataSet4);
		xySeriesCollection.addSeries(dataSet5);
		xySeriesCollection.addSeries(dataSet6);
		
		
	}
	/**
	 * This method saves the graph as a LineGraph.jpg in the folder with .jar file.
	 */
	void saveGraph() {
		try {
			ChartUtilities.saveChartAsJPEG(new File("LineGraph.jpg"),
					lineGraph, 800, 600);
		} catch (Exception e) {
		}
	}
	
	void chartUpdate(int time, int sSimCount, int iSimCount, int rSimCount){//, int sTheoryCount, int iTheoryCount, int rTheoryCount){
		
		dataSet.add(time,sSimCount);
		dataSet2.add(time, iSimCount);
		dataSet3.add(time, rSimCount);
		//dataSet4.add(time, sTheoryCount);
		//dataSet5.add(time, iTheoryCount);
		//dataSet6.add(time, rTheoryCount);
		
	}
}