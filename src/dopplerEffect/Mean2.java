package dopplerEffect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class Mean2 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static int wymiar=50;
	int[] xTableInfected=new int[20000];
	int[] yTableInfected=new int[20000];
	int[] xTableRecovered=new int[20000];
	int[] yTableRecovered=new int[20000];
	Ball[][] BallArray = new Ball[wymiar][wymiar];
	Ball[][] NewBallArray = BallArray;
	Timer timer;
	Random generator = new Random();
	private static int initialInfected=1;
	private static int initialRecovered=0;
	private double infectionProbability=0.99; // 1 to 0 xd
	private double recoveryProbability=1;
	static int infectedCounter=initialInfected;
	static int recoveredCounter=initialRecovered;
	static int counter=0;
	int MaxCounter=0;
	int ProgramCounter=0;
	int prevCounter=0;
	boolean initialize=true;
	
	XYSeries dataSet= new XYSeries("S(t)-Mean");
	XYSeries dataSet2= new XYSeries("I(t)-Mean");
	XYSeries dataSet3= new XYSeries("R(t)-Mean");
	XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSet);
	XYDataset xyDataset = xySeriesCollection;
	JFreeChart lineGraph = ChartFactory.createXYLineChart("Model 2 - wyniki srednie", // Title
			"time ", // X-Axis label
			"Number", // Y-Axis label
			xyDataset, // Dataset
			PlotOrientation.VERTICAL, // Plot orientation
			true, // show legend
			true, // Show tooltips
			false // url show
			);
	ChartPanel chartPanel = new ChartPanel(lineGraph);
	JButton startButton = new JButton("start");
	JButton saveButton = new JButton("save");
	JButton stateButton = new JButton("0");
	int[] susceptible = new int[30000];
	int[] recovered = new int[30000];
	int[] infected = new int[30000];
	
	
	public Mean2(){
		
		setMinimumSize(new Dimension(1035, 560)); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		xySeriesCollection.addSeries(dataSet2);
		xySeriesCollection.addSeries(dataSet3);
		chartPanel.setPreferredSize(new Dimension (492,350));
		add(chartPanel);
		startButton.addActionListener(new StartListener());
		add(startButton);
		saveButton.addActionListener(new SaveListener());
		add(saveButton);
		add(stateButton);
		//lineGraph.setBackgroundPaint( new Color(255,255,255) );
		
		final Plot plot = lineGraph.getPlot();
        plot.setBackgroundPaint( new Color(255,255,255,0) );
        XYItemRenderer renderer = lineGraph.getXYPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesPaint(2, Color.black);
		this.setVisible(true);
		
	}

	void chartUpdate()
	{
		
		dataSet.clear();
		dataSet2.clear();
		dataSet3.clear();
		for(int i=0; i<MaxCounter; i++)
		{
			dataSet.add(i,((double)susceptible[i]/ProgramCounter));
			dataSet2.add(i,((double)infected[i]/ProgramCounter));
			dataSet3.add(i,((double)recovered[i]/ProgramCounter));
		}
		
		
		
		
	}
	
	public int getInfectedCount(){
		int count=0;
		for(int ii=0; ii<wymiar; ii++){
			for(int jj=0; jj<wymiar; jj++){
				if(BallArray[ii][jj].getState()==1)
					count++;
			}
			
		}
		
		return count;
	}
	
	public int getRecoveredCount(){
		int count=0;
		for(int ii=0; ii<wymiar; ii++){
			for(int jj=0; jj<wymiar; jj++){
				if(BallArray[ii][jj].getState()==2)
					count++;
			}
			
		}
		
		return count;
	}
	
	public int getSusceptibleCount(){
		int count=0;
		for(int ii=0; ii<wymiar; ii++){
			for(int jj=0; jj<wymiar; jj++){
				if(BallArray[ii][jj].getState()==0)
					count++;
			}
			
		}
		
		return count;
	}
	
	
	 void initializeBallArray() {
	    	
	    	for (int ii=0; ii<50; ii++)
	        	for (int jj=0; jj<50; jj++)
	        	{
	        		Ball temp=new Ball();
	            	temp.setRadius(5);
	        		temp.setX(ii*10+10);
	        		temp.setY(jj*10+20);
	        		temp.setState(0);
	        		this.BallArray[ii][jj]=temp;
	        	}
	    	//System.out.println("I "+getInfectedCount());
			//System.out.println("R "+getRecoveredCount());
	    	for (int ii=0; ii<initialInfected; ii++)
	    	{
	    		int random1 = this.generator.nextInt((wymiar-1));
	    		int random2 = this.generator.nextInt((wymiar-1));
	    		if (this.BallArray[random1][random2].getState()!=1)
	    		{
	    		xTableInfected[ii]=random1;
	    		yTableInfected[ii]=random2;
	    		this.BallArray[random1][random2].setState(1);
	    		}
	    		else
	    			ii--;
	    		
	    	}
	    	//System.out.println("I "+getInfectedCount());
			//System.out.println("R "+getRecoveredCount());
	    	for (int ii=0; ii<initialRecovered; ii++)
	    	{
	    		int random1 = this.generator.nextInt((wymiar-1));
	    		int random2 = this.generator.nextInt((wymiar-1));
	    		if ((this.BallArray[random1][random2].getState()!=1)&&(this.BallArray[random1][random2].getState()!=2))
	    		{
	    			this.BallArray[random1][random2].setState(2);
	    			xTableRecovered[ii]=random1;
	    			yTableRecovered[ii]=random2;
	    		}
	    		else
	    			ii--;
	    	}
	    	//System.out.println("I "+getInfectedCount());
			//System.out.println("R "+getRecoveredCount());
	    }
	 
	 
	 public void animationStarter() {
			timer = new Timer(1, null);
						
			
	    		timer.addActionListener(new TimerListener()); 
	    		timer.start();
		  	
		}
	 
	 
	
	void saveGraph() {
		try {
			ChartUtilities.saveChartAsJPEG(new File("LineGraph.jpg"),
					lineGraph, 800, 600);
		} catch (Exception e) {
		}
	}
	
	
	
	void checkFirstBall ()
    {
    	if (BallArray[0][0].getState()==0)
    	{
    		if ((BallArray[1][0].getState()==1 || BallArray[0][1].getState()==1 || 
    				BallArray[19][0].getState()==1 || BallArray[0][19].getState()==1)
    				&& BallArray[0][0].getInfectionProbability()>infectionProbability)
    			BallArray[0][0].setState(1);
    	}
    	if (BallArray[0][0].getState()==1 && BallArray[0][0].getRecoveryProbability()>recoveryProbability)
    		BallArray[0][0].setState(2);
    	{
    	}
    }
	
	
	
	private class StartListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			animationStarter();
			startButton.setEnabled(false);
			
			
		}
		
	}
	
	
	private class SaveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			saveGraph();
			
			
		}
		
	}
	
	
	
	private class TimerListener implements ActionListener 
    {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(initialize)
			{
				counter=0;
				initializeBallArray();
				initialize=false;
			}
			
			susceptible[counter]+=getSusceptibleCount();
			recovered[counter]+=getRecoveredCount();
			infected[counter]+=getInfectedCount();
			
				//frequencyGraph.chartUpdate(counter, getSusceptibleCount(), getInfectedCount(), getRecoveredCount());
				
			//losowanie czy maja sie zarazic, czy nie
    		for (int ii=0; ii<wymiar; ii++)
    			for (int jj=0; jj<wymiar; jj++)
    			{
    				//BallArray[ii][jj].setInfectionProbability(generator.nextDouble());
    				//BallArray[ii][jj].setRecoveryProbability(generator.nextDouble());
    				if(BallArray[ii][jj].getState()==0)
    				{
    					BallArray[ii][jj].setInfectionProbability(0);
    					BallArray[ii][jj].setRecoveryProbability(0);
    				}
    				
    				if(BallArray[ii][jj].getState()==1)
    				{
    					BallArray[ii][jj].setInfectionProbability(generator.nextDouble());
    					BallArray[ii][jj].setRecoveryProbability(generator.nextDouble());
    				}
    				
    				if(BallArray[ii][jj].getState()==2)
    				{
    					BallArray[ii][jj].setInfectionProbability(0);
    					BallArray[ii][jj].setRecoveryProbability(0);
    				}
    			}
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		for (int ii=0; ii<wymiar; ii++)
    			for (int jj=0; jj<wymiar; jj++)
    			{
    				
    				
    				//losowanie kulek do porownan
    				
    				int[]xy=new int[4];
	        		int[] yki=new int [4];
	        		
	        		
    				for(int z=0; z<4; z++)
	        		{
	        			if(z==0)
	        			{
	        				xy[z]=generator.nextInt(wymiar);
		        			yki[z]=generator.nextInt(wymiar);
		        			
		        			if(xy[z]==ii&&yki[z]==jj)
		        				z--;
		        		}
	        			else
	        			{
	        				xy[z]=generator.nextInt(wymiar);
		        			yki[z]=generator.nextInt(wymiar);
		        			
	        				if(xy[z]==xy[z-1]&&yki[z]==yki[z-1])    	        					
	        					z--;
	        				else
	        					if(xy[z]==ii&&yki[z]==jj)
	        						z--;
	        							        					
	        			}
	        			
	        		}
    				
    				//jesli susceptible to zarazamy
    				if(BallArray[ii][jj].getState()==0)
    				{
    					for(int z=0; z<1; z++)
    					{
    						if(BallArray[xy[z]][yki[z]].getInfectionProbability()>infectionProbability)
    							NewBallArray[ii][jj].setState(1);	        						
    					}
    				}
    				
    				
    				//jesli infected to leczymy
    				if(BallArray[ii][jj].getState()==1)
    				{
    					if(BallArray[ii][jj].getRecoveryProbability()>recoveryProbability)
    						NewBallArray[ii][jj].setState(2);
    					
    				}
    				
    			}
    		
    		
    		
    		//dodaje polozenia nowych zarazonych
    		BallArray=NewBallArray;
    		
    		//checkFirstBall();
    		
	        		infectedCounter=0; recoveredCounter=0;
	        		counter++;
	        		
	     			if(counter>50&&getInfectedCount()==0)
	     			{
	     				initialize=true;
	     				
	     				if(MaxCounter<=counter)
	     				{   
	     					
	     					MaxCounter=counter;
	     				}
	     				
	     				
	     				ProgramCounter++;
	     				for(int i=counter; i<(susceptible.length); i++)
	     				{
	     					
	     					susceptible[i]+=getSusceptibleCount();
	     					infected[i]+=getInfectedCount();
	     					recovered[i]+=getRecoveredCount();
	     					//System.out.println(i+" "+susceptible[i]+" "+infected[i]+" "+recovered[i]);
	     				}
	     				chartUpdate();
	     				stateButton.setText(Integer.toString(ProgramCounter));
	     				
	     			}
	     			
	     			if(ProgramCounter==30)   // ile usrednien
	     			{
	     				timer.stop();
	     				startButton.setEnabled(true);
	     			}
	     			//repaint ();
	     			
	        	}   	    
		
    }
	
	
	
}
