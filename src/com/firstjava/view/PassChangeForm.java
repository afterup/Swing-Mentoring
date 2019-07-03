package com.firstjava.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class PassChangeForm extends JFrame {

	 public JTextField tf_newPass, tf_newPassCheck, tf_oldPass;
	 public JButton bt_cancel, bt_submit;
	 JLabel la_validCheck, lb_oldPass, lb_newPass, la_passCheck;



	public PassChangeForm() {

		getContentPane().setLayout(null);

		tf_oldPass = new JTextField();
		tf_oldPass.setColumns(10);
		tf_oldPass.setBounds(55, 199, 234, 39);
		
		tf_newPass = new JTextField();
		tf_newPass.setBounds(55, 285, 234, 39);
		tf_newPass.setColumns(10);
		
		tf_newPassCheck = new JTextField();
		tf_newPassCheck.setColumns(10);
		tf_newPassCheck.setBounds(55, 362, 234, 39);
		
		la_validCheck = new JLabel("유효성 검사");
		la_validCheck.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		la_validCheck.setBounds(118, 421, 111, 32);
		
		bt_cancel = new JButton("취소");
		bt_cancel.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		bt_cancel.setBounds(178, 463, 111, 46);
		
		lb_oldPass = new JLabel("현재 비밀번호");
		lb_oldPass.setForeground(Color.WHITE);
		lb_oldPass.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		lb_oldPass.setBounds(55, 155, 174, 32);
		
		lb_newPass = new JLabel("새로운 비밀번호");
		lb_newPass.setForeground(Color.WHITE);
		lb_newPass.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		lb_newPass.setBounds(55, 250, 174, 23);
		
		getContentPane().add(tf_oldPass);
		getContentPane().add(tf_newPass);
		getContentPane().add(tf_newPassCheck);
		getContentPane().add(la_validCheck);
		getContentPane().add(bt_cancel);
		getContentPane().add(lb_oldPass);
		getContentPane().add(lb_newPass);
		
		la_passCheck = new JLabel("새로운 비밀번호 확인");
		la_passCheck.setForeground(Color.WHITE);
		la_passCheck.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		la_passCheck.setBounds(55, 329, 174, 23);
		getContentPane().add(la_passCheck);
		
		bt_submit = new JButton("확인");
		bt_submit.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		bt_submit.setBounds(55, 463, 111, 46);
		getContentPane().add(bt_submit);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호 변경");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(106, 42, 171, 57);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image/background_800.jpg"));
		lblNewLabel.setBounds(0, 0, 342, 553);
		getContentPane().add(lblNewLabel);
		
		setBounds(100, 100, 360, 600);
		setVisible(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassChangeForm frame = new PassChangeForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}