package dopplerEffect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ButtonsListener2 implements ActionListener {
	final Lejaut appWindow;
	final int buttonId;
	private boolean bool1=true, bool2=true, bool3=true, bool4 =true, isPolish=true;

	public ButtonsListener2(Lejaut appWindow, int buttonId) {
		this.appWindow = appWindow;
		this.buttonId = buttonId;
	//	isPolish=appWindow.getLanguage();	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttonId == 1) {
			startStopButtonOperations();
		} 
		
	}
	
	
	void getData () {
	}
	
	void startStopButtonOperations(){
		if (bool1 == true) {
			try{
				getData();
    		}
			catch (java.lang.NumberFormatException e1){
				return;
			}
			appWindow.losowySir.animationStarter();
			//setEnabledFalse();
			appWindow.startButton.setText("Stop");
			bool1=false;
		} 
			else if (bool1==false) {
			appWindow.losowySir.timer.stop();
			appWindow.startButton.setText("Start");
			bool1=true;
		}
	}
	


	void setBool1 (boolean bool1){
		this.bool1=bool1;
		}
	
	boolean getBool1 () {
		return bool1;
	}
	
	void setBool3 (boolean bool3){
		this.bool3=bool3;	
	}
	
	boolean getBool3 () {
		return bool3;
	}
	
	void setBool4 (boolean bool4){
		this.bool4=bool4;	
	}
	
	boolean getBool4 () {
		return bool4;
	}
}

/**
 * Sound thread class, which plays sounds.
 * @author Pawe³ Kowaleczko
 *
 */
