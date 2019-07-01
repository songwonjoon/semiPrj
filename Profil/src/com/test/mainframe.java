package com.test;

public class mainframe {
	Loginview loginview;
	ProfilGUI mem=new ProfilGUI();
	
	public static void main(String[] args) {
		mainframe main=new mainframe();
		main.loginview=new Loginview();
		main.loginview.setMain(main);
	}
	public void showframe(){
		loginview.dispose();
		mem.launchFrame();
	}
}
