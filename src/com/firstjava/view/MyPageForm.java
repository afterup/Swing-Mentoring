package com.firstjava.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class MyPageForm extends JFrame{
	public JButton bt_my, bt_class_request, bt_homepage, bt_drop_id;

	public JPanel panel_my_page;// 레이아웃 기준
	public JPanel panel_my_data, panel_class;// 카드
	public CardLayout card;

	//내정보 Component
	public JButton bt_infoUpdate, bt_pwChange;
	public JTextField tf_id, tf_name, tf_phone1, tf_phone2, tf_phone3, tf_email;
	public JComboBox<String> cb_job;
	public JLabel la_my_data ,la_id, la_name, la_phone, la_addr, la_mento, la_mentoYN;
	public JComboBox<String> cb_mentor_category;

	//내강의 Component
	public JButton bt_menti_request_cancel,bt_classupdate,bt_classdelete,bt_info,bt_review;
	public JComboBox<String> cb_menti_category;
	JLabel la_menticlass,la_mentoclass;
	public JTable table_menti;

	public JTable table_mentor;
	JScrollPane scroll_table_menti,scroll_table_mento;
	
	public DefaultTableModel dtm_menti;
	public DefaultTableModel dtm_mentor;
	
	
	public MyPageForm() {
		setTitle("마이 페이지");
		
		//폰트설정
		Font font16 = new Font("나눔바른고딕 UltraLight",Font.BOLD, 16);
		
		bt_my = new JButton("내정보");
		bt_my.setBounds(458, 30, 100, 40);
		bt_my.setFont(font16);
		bt_my.setForeground(Color.WHITE);
		bt_my.setBackground(Color.WHITE);
		bt_my.setOpaque(false);
		bt_my.setBorderPainted(false);
		
		bt_class_request = new JButton("내강의");
		bt_class_request.setBounds(566, 30, 100, 40);
		bt_class_request.setFont(font16);
		bt_class_request.setBackground(Color.white);
		bt_class_request.setOpaque(false);
		bt_class_request.setBorderPainted(false);

		bt_homepage = new JButton("홈페이지");
		bt_homepage.setFont(font16);
		bt_homepage.setBounds(660, 30, 120, 40);
		bt_homepage.setForeground(Color.BLUE);
		bt_homepage.setBackground(Color.white);
		bt_homepage.setOpaque(false);
		bt_homepage.setBorderPainted(false);

		getContentPane().setLayout(null);
		getContentPane().add(bt_homepage);
		getContentPane().add(bt_my);
		getContentPane().add(bt_class_request);

		// ==================================MYDATA FRAME========================================
		panel_my_data = new JPanel(); // new
		panel_my_data.setBounds(25, 80, 736, 564);
		bt_pwChange = new JButton("비밀번호 변경");
		bt_pwChange.setBounds(182, 494, 130, 40);
		bt_infoUpdate = new JButton("정보 수정");
		bt_infoUpdate.setBounds(42, 494, 120, 40);

		tf_id = new JTextField();
		tf_id.setBounds(112, 237, 100, 25);
		tf_name = new JTextField();
		tf_name.setBounds(112, 277, 100, 25);
		tf_phone1 = new JTextField();
		tf_phone1.setBounds(112, 317, 40, 25);
		tf_phone2 = new JTextField();
		tf_phone2.setBounds(162, 317, 40, 25);
		tf_phone3 = new JTextField();
		tf_phone3.setBounds(212, 317, 40, 25);
		tf_email = new JTextField();
		tf_email.setBounds(112, 357, 200, 25);

		la_my_data = new JLabel("내 정보");
		la_my_data.setFont(new Font("나눔바른고딕 UltraLight", Font.BOLD, 20));
		la_my_data.setBounds(192, 116, 150, 40);
		la_id = new JLabel("ID :");
		la_id.setBounds(42, 237, 50, 25);
		la_name = new JLabel("이름 :");
		la_name.setBounds(42, 277, 80, 25);
		la_phone = new JLabel("전화번호 :");
		la_phone.setBounds(42, 317, 80, 25);
		la_addr = new JLabel("Email 주소 :");
		la_addr.setBounds(42, 357, 80, 25);
		la_mento = new JLabel("멘토신청:");
		la_mento.setBounds(42, 412, 80, 25);
		
		la_mentoYN = new JLabel("");
		la_mentoYN.setForeground(new Color(128, 0, 0));
		la_mentoYN.setBounds(114, 415, 302, 18);
		
		bt_drop_id = new JButton("탈퇴");
		bt_drop_id.setBounds(603, 494, 100, 40);
		bt_drop_id.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		bt_drop_id.setForeground(Color.red);
		bt_drop_id.setBackground(Color.white);
		bt_drop_id.setOpaque(false);
		bt_drop_id.setBorderPainted(false);
		
		panel_my_data.setLayout(null);
		panel_my_data.add(bt_pwChange);
		panel_my_data.add(bt_drop_id);
		panel_my_data.add(bt_infoUpdate);
		panel_my_data.add(tf_id);
		panel_my_data.add(tf_name);
		panel_my_data.add(tf_phone1);
		panel_my_data.add(tf_phone2);
		panel_my_data.add(tf_phone3);
		panel_my_data.add(tf_email);
		panel_my_data.add(la_my_data);
		panel_my_data.add(la_id);
		panel_my_data.add(la_name);
		panel_my_data.add(la_phone);
		panel_my_data.add(la_addr);
		panel_my_data.add(la_mento);
		panel_my_data.add(la_mentoYN);
		
		//=======setEnabled
		
		tf_id.setEnabled(false);
		tf_name.setEnabled(false);
		tf_id.setDisabledTextColor(Color.BLACK); 
		tf_name.setDisabledTextColor(Color.BLACK); 

		// ==================================CLASS FRAME============================================
		panel_class = new JPanel(); // new
		panel_class.setBounds(25, 80, 736, 564);

		//신청한 강의
		la_menticlass = new JLabel("신청한 강의");
		la_menticlass.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		la_menticlass.setBounds(31, 117, 150, 40);

		bt_menti_request_cancel = new JButton("신청 취소");
		bt_menti_request_cancel.setBounds(228, 513, 103, 40);

		String[] columnTitle_menti = { "번호", "강의명", "멘토" };
		Object[][] rowData_menti = new Object[0][3];
		dtm_menti = new DefaultTableModel(rowData_menti, columnTitle_menti);

		table_menti = new JTable(dtm_menti) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_menti.setRowHeight(30);
		table_menti.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table_menti.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		table_menti.getColumn("번호").setPreferredWidth(20);
		table_menti.getColumn("강의명").setPreferredWidth(100);
		table_menti.getColumn("멘토").setPreferredWidth(50);
		scroll_table_menti = new JScrollPane(table_menti);
		scroll_table_menti.setBounds(31, 169, 300, 332);
		
		//개설한 강의
		la_mentoclass = new JLabel("개설한 강의");
		la_mentoclass.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		la_mentoclass.setBounds(399, 117, 150, 40);
		
		bt_classupdate = new JButton("강의수정");
		bt_classupdate.setBounds(513, 513, 86, 40);
		
		bt_classdelete = new JButton("강의삭제");
		bt_classdelete.setBounds(613, 513, 86, 40);
		
		String[] columnTitle_mentor = { "번호", "강의명" };
		Object[][] rowData_mentor = new Object[0][2];
		dtm_mentor = new DefaultTableModel(rowData_mentor, columnTitle_mentor);
		
		table_mentor = new JTable(dtm_mentor) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_mentor.setRowHeight(30);
		table_mentor.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table_mentor.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		table_mentor.getColumn("번호").setPreferredWidth(20);
		table_mentor.getColumn("강의명").setPreferredWidth(100);
		scroll_table_mento = new JScrollPane(table_mentor);
		scroll_table_mento.setBounds(399, 169, 300, 332);
		
		bt_info = new JButton("정보 조회");
		bt_info.setBounds(129, 513, 102, 40);
		


		panel_class.add(bt_classupdate);
		panel_class.add(la_mentoclass);
		panel_class.add(bt_classdelete);
		panel_class.add(scroll_table_mento);
		panel_class.add(bt_info);
		panel_class.setLayout(null);
		panel_class.add(la_menticlass);
		panel_class.add(bt_menti_request_cancel);
		panel_class.add(scroll_table_menti);

		
		//=========카드 레이아웃===========
		card = new CardLayout();
		panel_my_page = new JPanel();
		panel_my_page.setLayout(card);// 카드레이아웃
		panel_my_page.setBounds(25, 80, 736, 564);
		
		panel_my_page.add(panel_my_data, "1"); // 카드붙이기
		panel_my_page.add(panel_class, "2"); 
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("image/logo_blacksmall.png"));
		lblNewLabel_1.setBounds(42, 37, 150, 177);
		panel_my_data.add(lblNewLabel_1);
		
		bt_review = new JButton("평점 작성");
		bt_review.setBounds(31, 513, 100, 40);
		panel_class.add(bt_review);
		
		getContentPane().add(panel_my_page);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image/background_800.jpg"));
		lblNewLabel.setBounds(0, 0, 794, 665);
		getContentPane().add(lblNewLabel);

		setBounds(450, 170, 800, 700);
		setVisible(false);
		setResizable(false);

	}
	
	public void menuColor(String menu) {
		
		if(menu.equals("mydata")) {
			bt_my.setForeground(Color.WHITE);
			bt_class_request.setForeground(Color.BLACK);
		}
		
		if(menu.equals("class")) {
			bt_my.setForeground(Color.BLACK);
			bt_class_request.setForeground(Color.WHITE);
		}
		
	}
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPageForm frame = new MyPageForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
