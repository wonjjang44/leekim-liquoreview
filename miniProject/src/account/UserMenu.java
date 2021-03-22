package account;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;


public class UserMenu extends JFrame {

	private JPanel mainBookPane;
	String userId = " ";

	public UserMenu(String userId) {
		this.userId = userId;
		itBook();
	}

	public void itBook() {
		setTitle("사용자 메뉴");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 148);
		mainBookPane = new JPanel();
		mainBookPane.setBackground(new Color(255, 255, 255));
		mainBookPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainBookPane);
		mainBookPane.setLayout(null);

		JButton inquiryButton = new JButton("책 조회");
		inquiryButton.setForeground(new Color(255, 255, 255));
		inquiryButton.setBackground(new Color(153, 204, 255));
		inquiryButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		inquiryButton.setBounds(14, 38, 116, 36);
		inquiryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookSelect().setVisible(true);
			}
		});
		mainBookPane.add(inquiryButton);

		JButton rentalButton = new JButton("대여");
		rentalButton.setForeground(new Color(255, 255, 255));
		rentalButton.setBackground(new Color(153, 204, 255));
		rentalButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rentalButton.setBounds(144, 38, 116, 36);
		rentalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookRental().setVisible(true);
			}
		});
		mainBookPane.add(rentalButton);

		JButton returnButton = new JButton("반납");
		returnButton.setForeground(new Color(255, 255, 255));
		returnButton.setBackground(new Color(153, 204, 255));
		returnButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		returnButton.setBounds(274, 38, 116, 36);
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookReturn().setVisible(true);
			}
		});
		mainBookPane.add(returnButton);

		JButton memberModify = new JButton("회원정보수정");
		memberModify.setForeground(Color.WHITE);
		memberModify.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		memberModify.setBackground(new Color(153, 204, 255));
		memberModify.setBounds(404, 38, 124, 36);
		mainBookPane.add(memberModify);
		memberModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserModify userModify = null;
				BufferedReader br = null;
				try {
					String line = " ";
					br = new BufferedReader(new FileReader("./resources/userInfo.txt"));
					if (e.getSource() == memberModify) {
						while ((line = br.readLine()) != null) {
							String[] array = line.split("/");
							if (array[1].equals(userId)) {
								userModify = new UserModify(array[1]);
							}
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				userModify.setVisible(true);
			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}
}

