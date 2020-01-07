package dopplerEffect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;



public class LosowySir extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	static int wymiar=50;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	int[] xTableInfected=new int[20000];
	int[] yTableInfected=new int[20000];
	int[] xTableRecovered=new int[20000];
	int[] yTableRecovered=new int[20000];
	int[] randomNumbers = new int[10];
	double step=0.1;
	JTextField initialInfectedField = new JTextField("5");
	JTextField initialRecoveredField = new JTextField("0");
	JTextField infectionProbabilityField = new JTextField("5");
	JTextField recoveryProbabilityField = new JTextField("5");
	//tablica wszystkich ludzikow
	Ball[][] BallArray = new Ball[wymiar][wymiar];
	Ball[][] NewBallArray=BallArray;
	JPanel graphPanel=new JPanel();
	XYChart frequencyGraph=new XYChart(graphPanel);
	int infCompare;
	int recCompare;
	Timer timer;
	Random generator = new Random();
	static boolean initialize=true;
	//tu sterujesz ilu ma byc na poczatek zarazonych
	private static int initialInfected=0;
	private static int initialRecovered=0;
	//tu ustawiasz jak latwo maja sie zarazac
	private double infectionProbability, recoveryProbability;
	static int infectedCounter=initialInfected;
	static int recoveredCounter=initialRecovered;
	static int counter=0;
	double theoreticalInfectedCounter=initialInfected;
	double theoreticalRecoveredCounter=initialRecovered;
	double theoreticalSusceptibleCounter=wymiar*wymiar-theoreticalInfectedCounter-theoreticalRecoveredCounter;
    
	
	public LosowySir(){		
		frequencyGraph.lineGraph.setTitle("Model 2");
		initialInfectedField.addKeyListener(new TextListener());
		initialRecoveredField.addKeyListener(new TextListener2());
		infectionProbabilityField.addKeyListener(new TextListener3());
		recoveryProbabilityField.addKeyListener(new TextListener4());
	}
	
	
	public int getInfCompare()
	{
		if(infectionProbabilityField.getText().length()==0)	
			return 0;
		else
			return Integer.parseInt(infectionProbabilityField.getText());
	}
	
	public int getRecCompare()
	{
		if(recoveryProbabilityField.getText().length()==0)
			return 0;
		else
			return Integer.parseInt(recoveryProbabilityField.getText());		
	}
	
	public double getInfectionProbability(){
		if(infectionProbabilityField.getText().length()==0)
		{
			infCompare=0;
			return 0;
		}
		else
		{
			infCompare=Integer.parseInt(infectionProbabilityField.getText());
			return (1-(Double.parseDouble(infectionProbabilityField.getText())/100));}
	}

	public double getRecoveryProbability(){
		
		if(recoveryProbabilityField.getText().length()==0)
		{
		recCompare=0;
		return 0;}
	else
		{infCompare=Integer.parseInt(recoveryProbabilityField.getText());
		return (1-(Double.parseDouble(recoveryProbabilityField.getText())/100));}
		
	}
	
	public void setInitialInfectedCount(int infectedCount){
		initialInfected=infectedCount;
	}
	
	
	public void setInitialRecoveredCount(int recoveredCount){
		initialRecovered=recoveredCount;
	}
	
	public int getInitialInfectedCount(){
		
		if(initialInfectedField.getText().length()==0)
			return 0;
			else
				return Integer.parseInt(initialInfectedField.getText());
	}
	
	
	public int getInitialRecoveredCount(){
		
		if(initialRecoveredField.getText().length()==0)
			return 0;
			else
				return Integer.parseInt(initialRecoveredField.getText());
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
	
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (initialize)
    		initializeBallArray();
		Graphics2D g2 = (Graphics2D) g;
		//rysowania poszczegolnych zdrowych, zarazonych itp
		for (int ii=0; ii<wymiar; ii++)
			for (int jj=0; jj<wymiar; jj++)
			{
				if(BallArray[ii][jj].getState()==0)
					g2.setColor(Color.YELLOW);
				else if(BallArray[ii][jj].getState()==1)
					g2.setColor(Color.BLUE);
				else if(BallArray[ii][jj].getState()==2)
					g2.setColor(Color.BLACK);
			    fillCircle(g2,(int) BallArray[ii][jj].getX(),(int) BallArray[ii][jj].getY(),5);
			    g2.setColor(Color.BLACK);
			    drawCircle(g2,(int) BallArray[ii][jj].getX(),(int) BallArray[ii][jj].getY(),5);
			}
    } 
    
    
    public void animationStarter() {
		timer = new Timer(100, null);
		initialInfected=getInitialInfectedCount();
		initialRecovered=getInitialRecoveredCount();
		infectionProbability=getInfectionProbability();
		recoveryProbability=getRecoveryProbability();
		
		if (initialize)
    		initializeBallArray();
		repaint();
		initialize=false;
		timer.addActionListener(new TimerListener()); 
	  	timer.start();
	  	
	}  
    

    void drawCircle(Graphics2D cg, int xCenter, int yCenter, int r) {
		cg.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
		}
    void fillCircle(Graphics2D cg, int xCenter, int yCenter, int r) {
		cg.fillOval(xCenter-r, yCenter-r, 2*r, 2*r);
		}
    //inicjalizuje rysunek w stanie poczatkowym
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
    	for (int ii=0; ii<LosowySir.initialInfected; ii++)
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
    	for (int ii=0; ii<LosowySir.initialRecovered; ii++)
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
    
    private class TextListener implements KeyListener
	{
		public void keyPressed(KeyEvent arg0) {}
		
		public void keyReleased(KeyEvent arg0) {	
			
		}
		
		
		public void keyTyped(KeyEvent arg0) {
			
			char c = arg0.getKeyChar();
			
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
				
				arg0.consume();
			}	
			
			
									
			if((getInitialInfectedCount()>250))
			{	
				arg0.consume();
			}
			
			if(getInitialInfectedCount()>((int)Math.floor((2500-getInitialRecoveredCount())/10)))
			{	
				arg0.consume();
			}
			
									
		}
				
	}
    
    private class TextListener2 implements KeyListener
	{
		public void keyPressed(KeyEvent arg0) {}
		
		public void keyReleased(KeyEvent arg0) {	
			
		}
		
		
		public void keyTyped(KeyEvent arg0) {
			
			char c = arg0.getKeyChar();
			
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
				
				arg0.consume();
			}	
			
											
			
			if((getInitialRecoveredCount()>250))
			{			
							arg0.consume();
						}
			
			if(getInitialRecoveredCount()>((int)Math.floor((2500-getInitialInfectedCount())/10)))
			{	
				arg0.consume();
			}
			
						
		}
				
	}
    
    
    private class TextListener3 implements KeyListener
	{
		public void keyPressed(KeyEvent arg0) {}
		
		public void keyReleased(KeyEvent arg0) {	
			
		}
		
		
		public void keyTyped(KeyEvent arg0) {
			
			char c = arg0.getKeyChar();
			
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
				
				arg0.consume();
			}	
			
						
			if(getInfCompare()>10)
			{			
							arg0.consume();
						}
			
						
		}
				
	}
    
    
    private class TextListener4 implements KeyListener
	{
		public void keyPressed(KeyEvent arg0) {}
		
		public void keyReleased(KeyEvent arg0) {	
			
		}
		
		
		public void keyTyped(KeyEvent arg0) {
			
			char c = arg0.getKeyChar();
			double b=getRecoveryProbability();
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE )){
				
				arg0.consume();
			}	
			//grp=1-wart
					//wart=1-grp
						
			if(getRecCompare()>10)
			{			
							arg0.consume();
						}
			
						
		}
				
	}
    
    
    
    private class TimerListener implements ActionListener 
    {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
				frequencyGraph.chartUpdate(counter, getSusceptibleCount(), getInfectedCount(), getRecoveredCount());
				
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
	        		
	     			if(counter>50&&getInfectedCount()==0)
	     				timer.stop();
	     			counter++;
	     			repaint ();
	        	}   	    	
    }

}
