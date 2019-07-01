package com.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Loginview extends JFrame{
	 private mainframe main;
	    private ProfilGUI managerGUI;
	   
	    private JButton btnLogin;
	    private JButton btnInit;
	    private JPasswordField passText;
	    private JTextField userText;
	    private boolean bLoginCheck;
	   
	    public static void main(String[] args) {
	        //new LoginView();
	    }
	 
	    public Loginview(){
	        // setting
	        setTitle("관리자 로그인");
	        setSize(580, 350);
	        setResizable(false);
	        setLocation(800, 450);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	       
	        // panel
	        JPanel panel = new JPanel();
	        placeLoginPanel(panel);
	       
	       
	        // add
	        add(panel);
	       
	        // visiible
	        setVisible(true);
	    }
	   
	    public void placeLoginPanel(JPanel panel){
	        panel.setLayout(null);     
	        JLabel tx_label=new JLabel("프로필 관리 프로그램");
	        tx_label.setBounds(195,0,240,180);
	        tx_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	        tx_label.setForeground(Color.BLUE);
	        panel.add(tx_label);
	        
	        JLabel label=new JLabel(new ImageIcon("group.jpg"));
	        label.setBounds(0, 0, 580, 180);
	        panel.add(label);
	        JLabel userLabel = new JLabel("User");
	        userLabel.setBounds(180, 200, 80, 25);
	        panel.add(userLabel);
	       
	        JLabel passLabel = new JLabel("Pass");
	        passLabel.setBounds(180, 230, 80, 25);
	        panel.add(passLabel);
	       
	        userText = new JTextField(20);
	        userText.setBounds(240, 200, 160, 25);
	        panel.add(userText);
	       
	        passText = new JPasswordField(20);
	        passText.setBounds(240, 230, 160, 25);
	        panel.add(passText);
	        passText.addActionListener(new ActionListener() {          
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                isLoginCheck();        
	            }
	        });
	       
//	        btnInit = new JButton("Reset");
//	        btnInit.setBounds(10, 80, 100, 25);
//	        panel.add(btnInit);
//	        btnInit.addActionListener(new ActionListener() {
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	                userText.setText("");
//	                passText.setText("");
//	            }
//	        });
	       
	        btnLogin = new JButton("Login");
	        btnLogin.setBounds(265, 270, 100, 25);
	        panel.add(btnLogin);
	        btnLogin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                isLoginCheck();
	            }
	        });
	    }
	   
	    public void isLoginCheck(){
	        if(userText.getText().equals("송원준") && new String(passText.getPassword()).equals("7162")){
	            JOptionPane.showMessageDialog(null, "로그인성공");
	            bLoginCheck = true;
	           
	            // 로그인 성공이라면 매니져창 뛰우기
	            if(isLogin()){
	                main.showframe(); // 메인창 메소드를 이용해 창뛰우기
	            }                  
	        }else{
	            JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다");
	        }
	    }
	 
	   
	    // mainProcess와 연동
	    public void setMain(mainframe main) {
	        this.main = main;
	    }
	   
	 
	    public boolean isLogin() {     
	        return bLoginCheck;
	    }
	 
	}

