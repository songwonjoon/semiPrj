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
	        setTitle("������ �α���");
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
	        JLabel tx_label=new JLabel("������ ���� ���α׷�");
	        tx_label.setBounds(195,0,240,180);
	        tx_label.setFont(new Font("���� ���", Font.BOLD, 20));
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
	        if(userText.getText().equals("�ۿ���") && new String(passText.getPassword()).equals("7162")){
	            JOptionPane.showMessageDialog(null, "�α��μ���");
	            bLoginCheck = true;
	           
	            // �α��� �����̶�� �Ŵ���â �ٿ��
	            if(isLogin()){
	                main.showframe(); // ����â �޼ҵ带 �̿��� â�ٿ��
	            }                  
	        }else{
	            JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
	        }
	    }
	 
	   
	    // mainProcess�� ����
	    public void setMain(mainframe main) {
	        this.main = main;
	    }
	   
	 
	    public boolean isLogin() {     
	        return bLoginCheck;
	    }
	 
	}

