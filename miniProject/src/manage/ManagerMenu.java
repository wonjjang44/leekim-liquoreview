package manage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;

import account.UserModify;

public class ManagerMenu extends JFrame {

	private JFrame frame;
	String adminId = " ";

	public ManagerMenu(String adminId) {
		this.adminId = adminId;
		getContentPane().setBackground(Color.WHITE);
		initialize();
		setLocationRelativeTo(null);

	}

	private void initialize() {

		setTitle("관리자 메뉴");
		setBounds(100, 100, 560, 148);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnReg = new JButton("책 등록");
		btnReg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnReg.setForeground(new Color(255, 255, 255));
		btnReg.setBackground(new Color(153, 204, 255));
		btnReg.setBounds(14, 30, 116, 36);
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("책 등록")) {
					new BookRegister();
					setVisible(false);
				}
			}
		});
		getContentPane().add(btnReg);

		JButton btnBMod = new JButton("책 수정");
		btnBMod.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnBMod.setForeground(new Color(255, 255, 255));
		btnBMod.setBackground(new Color(153, 204, 255));
		btnBMod.setBounds(144, 30, 116, 36);
		btnBMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("책 수정")) {
					new InputCode();
					setVisible(false);
				}
			}
		});

		getContentPane().add(btnBMod);

		JButton btnNewButton_3 = new JButton("책 삭제");
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(153, 204, 255));
		btnNewButton_3.setBounds(274, 30, 116, 36);
		getContentPane().add(btnNewButton_3);

		JButton btnIMod = new JButton("회원정보 수정");
		btnIMod.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnIMod.setForeground(new Color(255, 255, 255));
		btnIMod.setBackground(new Color(153, 204, 255));
		btnIMod.setBounds(404, 30, 116, 36);
		getContentPane().add(btnIMod);
		
		btnIMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminModify adminModify = null;
				BufferedReader br = null;
				try {
					String line = " ";
					br = new BufferedReader(new FileReader("./resources/adminInfo.txt"));
					if (e.getSource() == btnIMod) {
						while ((line = br.readLine()) != null) {
							String[] array = line.split("/");
							if (array[1].equals(adminId)) {
								adminModify = new AdminModify(array[1]);
							}
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				adminModify.setVisible(true);
			}
		});
		setVisible(true);
	}

}