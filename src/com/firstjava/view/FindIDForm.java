package com.firstjava.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class FindIDForm extends JFrame {

	private JTextField tf_name, tf_phoneNum, tf_email; 
	JLabel la_passImage, la_background, la_idImage, la_logoimage;
	public JLabel la_name, la_phoneNum, la_email;
	public JButton bt_findID, bt_cancel;
	public JLabel la_idPassSearch;

	public FindIDForm() {
		
		setTitle("아이디 찾기");
		
		la_name = new JLabel("이름: ");
		la_name.setForeground(Color.white);
		la_name.setFont(new Font("맑은 고딕", Font.BOLD,15));
		//la_idImage.setIcon(new ImageIcon("image/User_Login.PNG"));
		la_name.setBounds(25, 250, 80, 40);
		
		la_phoneNum = new JLabel("전화번호: ");
		la_phoneNum.setForeground(Color.white);
		la_phoneNum.setFont(new Font("맑은 고딕", Font.BOLD,15));
		//la_passImage.setIcon(new ImageIcon("image/Pw_Login.PNG"));
		la_phoneNum.setBounds(25, 310, 80, 40);
		
		la_email = new JLabel("이메일: ");
		la_email.setForeground(Color.white);
		la_email.setFont(new Font("맑은 고딕", Font.BOLD,15));
		//la_passImage.setIcon(new ImageIcon("image/Pw_Login.PNG"));
		la_email.setBounds(25, 370, 80, 40);

		tf_name = new JTextField();
		tf_name.setBounds(110, 250, 180, 41);
		tf_name.setColumns(10);

		tf_phoneNum = new JTextField();
		tf_phoneNum.setColumns(10);
		tf_phoneNum.setBounds(110, 310, 180, 41);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(110, 370, 180, 41);
		

		bt_findID = new JButton("아이디찾기");
		bt_findID.setForeground(Color.white);
		bt_findID.setBackground(new Color(140, 143, 143));
		bt_findID.setFont(new Font("맑은 고딕", Font.BOLD,13));
		bt_findID.setBounds(50, 450, 100, 40);

		bt_cancel = new JButton("취소");
		bt_cancel.setForeground(Color.WHITE);
		bt_cancel.setBackground(new Color(140, 143, 143));
		bt_cancel.setFont(new Font("맑은 고딕", Font.BOLD,13));
		bt_cancel.setBounds(200, 450, 100, 40);

		la_idPassSearch = new JLabel("비밀번호 찾기");
		la_idPassSearch.setForeground(SystemColor.menu);
		la_idPassSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		la_idPassSearch.setBounds(110, 500, 178, 32);
		
		la_logoimage = new JLabel("");
		la_logoimage.setIcon(new ImageIcon("image/logo_42.png"));
		la_logoimage.setBounds(85, 30, 170, 180);

		la_background = new JLabel("");
		la_background.setIcon(new ImageIcon("image/background_800.jpg"));
		la_background.setBounds(0, 0, 344, 561);

		getContentPane().add(la_name);
		getContentPane().add(la_phoneNum);
		getContentPane().add(la_email);
		
		getContentPane().add(tf_name);
		getContentPane().add(tf_phoneNum);
		getContentPane().add(tf_email);
		
		getContentPane().add(bt_findID);
		getContentPane().add(bt_cancel);
		
		getContentPane().add(la_idPassSearch);
		getContentPane().add(la_logoimage);
		getContentPane().add(la_background);
		
		setBounds(1280, 170, 360, 600);
		getContentPane().setLayout(null);
		setVisible(false);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindIDForm frame = new FindIDForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}