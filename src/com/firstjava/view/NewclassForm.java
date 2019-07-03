package com.firstjava.view;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewclassForm extends JFrame {

	private JPanel contentPane;
	public JTextField tf_name, tf_student, tf_open, tf_close;
	public JComboBox jb_category;
	public JTextArea ta_desc;
	public JButton bt_new, bt_cancel; 
		
	/**
	 * Create the frame.
	 */
	public NewclassForm() {
		setBounds(450,170,800,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		setTitle("강의개설");
		
		
		//라벨
		
		JLabel label_1 = new JLabel("강의");
		label_1.setBounds(50, 40, 80, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("카테고리");
		label_2.setBounds(50, 80, 80, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("최대인원수");
		label_3.setBounds(50, 120, 80, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("개강일자");
		label_4.setBounds(50, 160, 80, 18);
		contentPane.add(label_4);
		
		JLabel label_4_2 = new JLabel("종강일자");
		label_4_2.setBounds(50, 200, 80, 18);
		contentPane.add(label_4_2);
		
		JLabel label_7 = new JLabel("강의설명");
		label_7.setBounds(50, 240, 80, 18);
		contentPane.add(label_7);
		
		JLabel la_3 = new JLabel("인원수는 최소1명, 최대 5명입니다.");
		la_3.setBounds(290, 120, 300, 18);
		contentPane.add(la_3);
		

		//필드
		tf_name = new JTextField();
		tf_name.setBounds(150, 40, 580, 24);
		contentPane.add(tf_name);
		tf_name.setColumns(10);
		
		String category[] = {"IT", "디자인", "뷰티", "외국어", "음악", "라이프"};
		
		
		jb_category = new JComboBox(category);
		jb_category.setBounds(150, 80, 130, 24);
		contentPane.add(jb_category);
		
		tf_student = new JTextField();
		tf_student.setBounds(150, 120, 130, 24);
		contentPane.add(tf_student);
		tf_student.setColumns(10);
		
		tf_open = new JTextField();
		tf_open.setBounds(150, 160, 130, 24);
		contentPane.add(tf_open);
		tf_open.setColumns(10);
		
		tf_close = new JTextField();
		tf_close.setBounds(150, 200, 130, 24);
		contentPane.add(tf_close);
		tf_close.setColumns(10);
		
		
		ta_desc = new JTextArea();
		ta_desc.setBounds(150, 240, 580, 300);
		contentPane.add(ta_desc);
		
		
		
		//버튼
		bt_new = new JButton("신청");
		bt_new.setBounds(250, 580, 105, 30);
		contentPane.add(bt_new);
		
		bt_cancel = new JButton("취소");
		bt_cancel.setBounds(450, 580, 105, 30);
		contentPane.add(bt_cancel);
		setVisible(false);
	
	}
	
	public void initText() {
		tf_name.setText("강의 이름");
		tf_close.setText("");
		tf_open.setText("");
		tf_student.setText("");
		ta_desc.setText("400자 이내의 강의 내용을 작성해주세요.");
		jb_category.setSelectedIndex(0);
	}//initText
	
	public void controlsetEnabled() {
		tf_name.setEnabled(true);
		tf_student.setEnabled(true); 
		tf_open.setEnabled(true);
		tf_close.setEnabled(true);
		ta_desc.setEnabled(true);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new NewclassForm();
			
	}
	
}
