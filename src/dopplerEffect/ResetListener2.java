package dopplerEffect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener2 implements ActionListener {
	
	boolean bool1=true;
	Lejaut appWindow;
	
	
	public ResetListener2(Lejaut lejaut)
	{
		appWindow=lejaut;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		appWindow.losowySir.initialize=true;
		appWindow.losowySir.timer.stop();
		
		appWindow.losowySir.frequencyGraph.dataSet.clear();
		appWindow.losowySir.frequencyGraph.dataSet2.clear();
		appWindow.losowySir.frequencyGraph.dataSet3.clear();
		appWindow.losowySir.counter=0;
		appWindow.startButton.setText("Start");
		
		
	}

}