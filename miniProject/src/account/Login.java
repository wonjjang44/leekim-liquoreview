package account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import manage.ManagerMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.imageio.stream.FileCacheImageInputStream;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;

public class Login extends JFrame {

	private JPanel loginPane;
	private JTextField textAccount;
	private JPasswordField textPassward;

	public Login() {
		initLogin();
	}

	private void initLogin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 513, 598);
		setTitle("도서관리프로그램");
		setLocationRelativeTo(null);
		loginPane = new JPanel();
		loginPane.setBackground(Color.WHITE);
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPane);
		loginPane.setLayout(null);

		JLabel labelLogo = new JLabel("도서관리프로그램");
		labelLogo.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogo.setBounds(70, 93, 361, 47);
		loginPane.add(labelLogo);

		JLabel labelAccount = new JLabel("계정");
		labelAccount.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		labelAccount.setHorizontalAlignment(SwingConstants.CENTER);
		labelAccount.setBounds(48, 202, 108, 39);
		loginPane.add(labelAccount);

		textAccount = new JTextField();
		textAccount.setBounds(142, 202, 230, 39);
		loginPane.add(textAccount);
		textAccount.setColumns(10);

		JLabel labelPassward = new JLabel("비밀번호");
		labelPassward.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		labelPassward.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassward.setBounds(48, 251, 108, 39);
		loginPane.add(labelPassward);

		textPassward = new JPasswordField();
		textPassward.setColumns(10);
		textPassward.setBounds(142, 251, 230, 39);
		loginPane.add(textPassward);

		JButton adminButton = new JButton("관리자 로그인");
		adminButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		adminButton.setForeground(new Color(255, 255, 255));
		adminButton.setBackground(new Color(153, 204, 255));
		adminButton.setBounds(142, 380, 230, 29);
		adminButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (textAccount.getText().length() != 0 || textPassward.getText().length() != 0) {
					try {
						String line = "";
						boolean th = false;
						BufferedReader admin = new BufferedReader(new FileReader("./resources/adminInfo.txt"));
						while ((line = admin.readLine()) != null) {
							String[] array = line.split("/"); // 1 id 3 pw
							if (array[1].equals(textAccount.getText()) && array[3].equals(textPassward.getText())) {
								JOptionPane.showMessageDialog(null, "로그인이 되었습니다.");
								new ManagerMenu(textAccount.getText());
								th = true;
								break;
							}
						}
						if (th) {
							setVisible(false);
							new ManagerMenu(textAccount.getText()).setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "계정과 비밀번호를 확인해주세요");
						}
						admin.close();

					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "계정과 비밀번호를 입력해주세요");
				}

			}
		});
		loginPane.add(adminButton);

		JButton userButton = new JButton("사용자 로그인");
		userButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		userButton.setForeground(new Color(255, 255, 255));
		userButton.setBackground(new Color(153, 204, 255));
		userButton.setBounds(142, 340, 230, 29);
		userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textAccount.getText().length() != 0 || textPassward.getText().length() != 0) {
					try {
						String line = "";
						boolean th = false;
						BufferedReader member = new BufferedReader(new FileReader("./resources/userInfo.txt"));
						while ((line = member.readLine()) != null) {
							String[] array = line.split("/"); // 1 id 3 pw
							if (array[1].equals(textAccount.getText()) && array[3].equals(textPassward.getText())) {
								JOptionPane.showMessageDialog(null, "로그인이 되었습니다.");
								new UserMenu(textAccount.getText());
								th = true;
								break;
							}
						}
						if (th) {
							setVisible(false);
							new UserMenu(textAccount.getText()).setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "계정과 비밀번호를 확인해주세요");
						}
						member.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "계정과 비밀번호를 입력해주세요");
				}
			}
		});
		loginPane.add(userButton);

		JButton buttonMember = new JButton("회원가입");
		buttonMember.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		buttonMember.setForeground(new Color(255, 255, 255));
		buttonMember.setBackground(new Color(153, 204, 255));
		buttonMember.setBounds(142, 420, 230, 29);
		buttonMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Join().setVisible(true);
			}
		});
		loginPane.add(buttonMember);
		setVisible(true);

	}
}