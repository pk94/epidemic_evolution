package dopplerEffect;

import javax.swing.*;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Lejaut extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AnimationPanel symulationPanel ;
	JButton startButton = new JButton("Start");
	JLabel initialInfectedLabel = new JLabel("I (0)");
	JLabel infectionProbabilityLabel = new JLabel("inf prob %");
	JLabel recoveryProbabilityLabel = new JLabel("rec prob %");
	JLabel initialRecoveredLabel = new JLabel("R (0)");
	JTextField initialInfectedField = new JTextField("5");
	JTextField initialRecoveredField = new JTextField("0");
	JCheckBox box1 = new JCheckBox("box1",true);
	JCheckBox box2 = new JCheckBox("box2",true);
	JCheckBox box3 = new JCheckBox("box3",true);
	JCheckBox box4 = new JCheckBox("box4",true);
	JCheckBox box5 = new JCheckBox("box5",true);
	JCheckBox box6 = new JCheckBox("box6",true);
	JPanel panelGlowny = new JPanel();
	JPanel panelBoczny = new JPanel();
	JPanel panelNastaw = new JPanel();
	JPanel panelNastawPrawy = new JPanel();
	JPanel panelNastawLewy = new JPanel();
	ButtonsListener startListener = new ButtonsListener(this, 1);
	BoxListener boxListener = new BoxListener();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Lejaut(new AnimationPanel());
	}

	public Lejaut(AnimationPanel panel){
		
		setMinimumSize(new Dimension(1035, 560)); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
			
	
		symulationPanel=panel;
		
		
	
		this.setLayout(new GridLayout(1,2));
		
		panelNastaw.setPreferredSize(new Dimension(500,130));

		symulationPanel.setBorder(BorderFactory.createTitledBorder("plansza"));
		this.add(symulationPanel);
	
		
		panelBoczny.setLayout(new FlowLayout());
		panelBoczny.setMinimumSize(new Dimension(500,560));
		panelBoczny.setBorder(BorderFactory.createTitledBorder("wykres"));
		panelNastaw.setBorder(BorderFactory.createTitledBorder("nastawy"));
		
				
		panelBoczny.add(symulationPanel.graphPanel);
		
			
		
		panelNastaw.setLayout(new GridLayout(1,2));
		
		
		
		
		startButton.addActionListener(startListener);
		

		
		panelNastawLewy.setLayout(new GridLayout(5,1));
		
		panelNastawLewy.add(infectionProbabilityLabel);
		panelNastawLewy.add(symulationPanel.infectionProbabilityField);
		panelNastawLewy.add(recoveryProbabilityLabel);
		panelNastawLewy.add(symulationPanel.recoveryProbabilityField);
		
		panelNastawLewy.add(initialInfectedLabel);
		panelNastawLewy.add(symulationPanel.initialInfectedField);
		panelNastawLewy.add(initialRecoveredLabel);
		panelNastawLewy.add(symulationPanel.initialRecoveredField);
		panelNastawLewy.add(startButton);
		
		panelNastawPrawy.setLayout(new GridLayout(3,2));
		
		box1.addActionListener(boxListener);
		box2.addActionListener(boxListener);
		box3.addActionListener(boxListener);
		box4.addActionListener(boxListener);
		box5.addActionListener(boxListener);
		box6.addActionListener(boxListener);
		
		panelNastawPrawy.add(box1);
		panelNastawPrawy.add(box2);
		panelNastawPrawy.add(box3);
		panelNastawPrawy.add(box4);
		panelNastawPrawy.add(box5);
		panelNastawPrawy.add(box6);
		
		panelNastaw.add(panelNastawLewy);
		panelNastaw.add(panelNastawPrawy);
		panelBoczny.add(panelNastaw);
		

		
		
		this.add(panelBoczny);
	
		this.setVisible(true);
		
	}
	
	
	
	private class BoxListener implements ActionListener {
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			symulationPanel.frequencyGraph.xySeriesCollection=new XYSeriesCollection();
			if(box1.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet);
			
			if(box2.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet2);
			
			if(box3.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet3);
			
			if(box4.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet4);
			
			if(box5.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet5);
			
			if(box6.isSelected()==true)
				symulationPanel.frequencyGraph.xySeriesCollection.addSeries(symulationPanel.frequencyGraph.dataSet6);
			
				
			symulationPanel.frequencyGraph.xyDataset=symulationPanel.frequencyGraph.xySeriesCollection;	
	    	
	    }
	}

	
	
}
