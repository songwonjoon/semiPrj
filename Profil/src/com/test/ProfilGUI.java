package com.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProfilGUI {
	Frame frame;
	 Panel [] panel;
	 Label lblTitle, lblName, lbllive, lblGender, lblAge, lblphone;
	 TextField tfName, tflive, tfAge, tfphone;
	 Button btnSave, btnSearch, btnDelete, btnReset, btnUpdate;
	 
	 List list;
	 Checkbox chMale, chFemale;
	 CheckboxGroup chGroup;
	 public ProfilGUI(){
		  frame = new Frame("프로필 관리 프로그램");
		  panel = new Panel[12]; //배열선언
		  for (int i = 0; i < panel.length; i++){
		   panel[i] = new Panel(); // panel heap 메모리에 올린다.
		  }
		  lblTitle = new Label("정보 입력 및 검색", Label.CENTER);
		  Font font = new Font("dialog", Font.BOLD, 20);
		  lblTitle.setFont(font);
		  lblTitle.setForeground(new Color(123,54,32));
		  lblName = new Label("이 름");
		  lbllive = new Label("주소 ");
		  lblGender = new Label("성 별");
		  lblAge = new Label("나 이 ");
		  lblphone = new Label("번호");
		  
		  tfName = new TextField("");
		  tfAge = new TextField("");
		  tfphone = new TextField("");
		  tflive = new TextField("");
		  btnSave = new Button("저장");
		  btnSearch = new Button("검색");
		  btnDelete = new Button("삭제");
		  btnReset = new Button("지우기");
		  btnUpdate = new Button("수정");
		  
		  list =new List(7,false); // 7줄 표시, true - 중복선택 가능.
		  chGroup = new CheckboxGroup();
		  chMale = new Checkbox("Male",chGroup,false);
		  chFemale = new Checkbox("FeMale", chGroup,false);
		  
		  frame.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent e){
				  System.exit(0);
			  }
		  });

	// 리스트 부분 처리하는 메소드
	list.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent e){
			String str = list.getSelectedItem();
			StringTokenizer st = new StringTokenizer(str);
			tfName.setText(st.nextToken());
			tfAge.setText(st.nextToken());
			String sex = st.nextToken();
			if (sex.equals("여")){
				chFemale.setState(true);
			}else{
				chMale.setState(true);
			}
			tflive.setText(st.nextToken());
			tfphone.setText(st.nextToken());
			
		}
	});
	//지우기 버튼에 기능을 주는 부분
	 btnReset.addActionListener (new ActionListener(){
		 public void actionPerformed(ActionEvent e){
			 tfName.setText("");
			 tfAge.setText("");
			 tflive.setText("");
			 tfphone.setText("");
			 chGroup.setSelectedCheckbox(null);
		 }
	 });
	 //저장 버튼에 기능을 주는 부분
	 btnSave.addActionListener(new ActionListener(){ 
		 public void actionPerformed(ActionEvent e){
			String name = tfName.getText();
			String age = tfAge.getText();
			String sex = "여";
			if (chMale.getState()){
				sex = "남";
			}
			String addr = tflive.getText();
			String pnumber = tfphone.getText();
			ProfilDAO dao = new ProfilDAO();
			dao.Insert(name, age, sex, addr, pnumber);
			displayAll();
		 }
	 });
	 //삭제 버튼에 기능을 주는 부분
	 btnDelete.addActionListener (new ActionListener(){ 
		 public void actionPerformed(ActionEvent e){
			 String name = tfName.getText();
			 ProfilDAO dao = new ProfilDAO();
			 dao.delete(name);
			 displayAll();
			 tfName.setText("");
			 tfAge.setText("");
			 tflive.setText("");
			 tfphone.setText("");
			 chGroup.setSelectedCheckbox(null);
		 }
	 });
	 //수정 버튼에 기능을 주는 부분
	 btnUpdate.addActionListener (new ActionListener(){ 
		 public void actionPerformed(ActionEvent e){
			 String name = tfName.getText();
			 String age = tfAge.getText();
			 String sex = "여";
			 if (chMale.getState()){
				 sex = "남";
			 }
			 String addr = tflive.getText();
			 String pnumber = tfphone.getText();
			 ProfilDAO dao = new ProfilDAO();
			 dao.update(name, age, sex, addr, pnumber);
			 displayAll();
		 }
	 });
	 //검색 버튼에 기능을 주는 부분
	 btnSearch.addActionListener (new ActionListener(){ 
		 @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			 String name = tfName.getText();
			 ProfilDAO dao = new ProfilDAO();
			 ProfilDTO dto = null;
			 try{
				 dto = dao.search(name);
			 }catch(RecordNotFoundException e1){
				 final Dialog dialog = new Dialog(frame, "경고");
				 dialog.setLayout(new FlowLayout());
				 dialog.setSize(200,80);
				 dialog.setTitle(e1.getMessage());
				 Button btnOK = new Button("확 인");
				 btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				 });
				 dialog.add(btnOK);
				 dialog.show();
				return;
			 }// try 끝
			 
			 tfName.setText(dto.getName());
			 tfAge.setText(Integer.toString(dto.getAge()));
			 String sex = Character.toString(dto.getGender()); 
			 if (sex.equals("여")){
				 chFemale.setState(true);
			 }else{
				 chMale.setState(true);
			 }
			 tflive.setText(dto.getAddr());
			 tfphone.setText(dto.getPhone());
		 }
	 });
	 //endManager 생성자 ==> 화면을 구성하기 위해서 해당 객체를 생성
	 //버튼에 기능을 넣을 메소드 선언 해 준다.
	 }
	 
	 public void launchFrame(){
		 panel[1].setLayout(new GridLayout(2,1));
		 panel[1].add(lblName);
		 panel[1].add(lblphone);
		 panel[2].setLayout(new GridLayout(2,1));
		 panel[2].add(tfName);
		 panel[2].add(tfphone);
		 panel[3].setLayout(new BorderLayout());
		 panel[3].add(panel[1], "West");
		 panel[3].add(panel[2], "Center");
		 panel[3].add(new Label(""), "East");
		 panel[4].setLayout(new GridLayout(2,1));
		 panel[4].add(lblAge);
		 panel[4].add(lbllive);
		 panel[5].setLayout(new GridLayout(2,1));
		 panel[5].add(tfAge);
		 panel[5].add(tflive);
		 panel[6].setLayout(new BorderLayout());
		 panel[6].add(panel[4], "West");
		 panel[6].add(panel[5], "Center");
		 panel[7].setLayout(new GridLayout(1,2));
		 panel[7].add(panel[3]);
		 panel[7].add(panel[6]);
		 panel[8].add(lblGender);
		 panel[8].add(chMale);
		 panel[8].add(chFemale);
		 panel[9].setLayout(new BorderLayout());
		 panel[9].add(lblTitle, "North");
		 panel[9].add(panel[7], "Center");
		 panel[9].add(panel[8], "South");
		 panel[10].setLayout(new GridLayout(1,5));
		 panel[10].setBackground(Color.black);
		 panel[10].setForeground(Color.white);
		 panel[10].add(new Label("이 름 "));
		 panel[10].add(new Label("나 이 "));
		 panel[10].add(new Label("성별  "));
		 panel[10].add(new Label("주소  "));
		 panel[10].add(new Label("번호  "));
		 panel[11].setLayout(new BorderLayout());
		 panel[11].add(panel[10], "North");
		 panel[11].add(list, "Center");
		 panel[0].add(btnSave);
		 panel[0].add(new Label(" "));
		 panel[0].add(btnSearch);
		 panel[0].add(new Label(" "));
		 panel[0].add(btnDelete);
		 panel[0].add(new Label(" "));
		 panel[0].add(btnUpdate);
		 panel[0].add(new Label(" "));
		 panel[0].add(btnReset);

		 frame.add(panel[9], "North");
		 frame.add(panel[11], "Center");
		 frame.add(panel[0], "South");

		 frame.setSize(580,360);

//		 frame.pack();

		 frame.setResizable(false);
		 frame.setLocation(((frame.getToolkit().getScreenSize()).width - 
		 frame.getWidth())/2,
		 ((frame.getToolkit().getScreenSize()).height - frame.getHeight())/2);
		 frame.setVisible(true);
		 displayAll();
		 }

		 public void displayAll(){
			 list.removeAll(); //리스트 화면 클리어
			 ProfilDAO dao = new ProfilDAO();
			 ArrayList<ProfilDTO> allData = dao.select(); 
			 
			 for (ProfilDTO dto : allData){
				String name = dto.getName();
				int age = dto.getAge();
				char gender = dto.getGender(); 	 
				String addr = dto.getAddr();
				String phone = dto.getPhone();
				list.add(name + "                            " + age + "                                   " + 
						 gender + "                           " + addr + "                           " + phone);
			 }
		 }

		 public static void main(String[] args){
			 ProfilGUI mem = new ProfilGUI();
			 //화면 구성하기 위한 준비, 버튼 기능 넣어주는
//			 my.dispose();
			 mem.launchFrame();
			 //프레임에 화면 붙이고, select() 호출
		 }
	}
