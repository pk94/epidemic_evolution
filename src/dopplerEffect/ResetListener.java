package dopplerEffect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
	
	boolean bool1=true;
	Lejaut appWindow;
	
	
	public ResetListener(Lejaut lejaut)
	{
		appWindow=lejaut;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		appWindow.symulationPanel.initialize=true;
		appWindow.symulationPanel.timer.stop();
		
		appWindow.symulationPanel.frequencyGraph.dataSet.clear();
		appWindow.symulationPanel.frequencyGraph.dataSet2.clear();
		appWindow.symulationPanel.frequencyGraph.dataSet3.clear();
		appWindow.symulationPanel.counter=0;
		appWindow.startButton.setText("Start");
//		appWindow.symulationPanel.xTableInfected=new int[20000];
//		appWindow.symulationPanel.yTableInfected=new int[20000];
//		appWindow.symulationPanel.xTableRecovered=new int[20000];
//		appWindow.symulationPanel.yTableInfected=new int[20000];
//		
		
	}

}
