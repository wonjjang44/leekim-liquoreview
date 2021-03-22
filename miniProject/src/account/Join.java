package account;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class Join extends JFrame {

	private JPanel joinPanel;
	private JFileChooser chooser;
	String adminFilePath = "./resources/adminInfo.txt";
	String userFilePath = "./resources/userInfo.txt";
	File administratorsFile = new File(adminFilePath);
	File membersFile = new File(userFilePath);
	ButtonGroup buttongroup = new ButtonGroup();

	public Join() {
		setBounds(100, 100, 513, 598);
		joinPanel = new JPanel();
		joinPanel.setBackground(Color.WHITE);
		joinPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(joinPanel);
		setTitle("회원가입");
		setLocationRelativeTo(null);
		joinPanel.setLayout(null);
		joinPanel.setLayout(null);

		JLabel joinLabel = new JLabel("회원가입");
		joinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		joinLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		joinPanel.add(joinLabel);
		joinLabel.setLayout(null);
		joinLabel.setBounds(187, 25, 150, 60);

		JCheckBox administratorCheckBox = new JCheckBox("관리자");
		administratorCheckBox.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		administratorCheckBox.setBackground(Color.WHITE);
		administratorCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		administratorCheckBox.setBounds(140, 110, 115, 23);
		joinPanel.add(administratorCheckBox);
		administratorCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isChecked = administratorCheckBox.isSelected();
			}

		});

		JCheckBox userCheckBox = new JCheckBox("사용자");
		userCheckBox.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		userCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		userCheckBox.setBackground(Color.WHITE);
		userCheckBox.setBounds(259, 110, 115, 23);
		joinPanel.add(userCheckBox);
		userCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isChecked = userCheckBox.isSelected();
			}

		});

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		joinPanel.add(nameLabel);
		nameLabel.setLayout(null);
		nameLabel.setBounds(140, 165, 60, 23);

		JTextField nameTextField = new JTextField();
		joinPanel.add(nameTextField);
		nameTextField.setColumns(15);
		nameTextField.setLayout(null);
		nameTextField.setBounds(192, 165, 180, 23);

		JLabel accountLabel = new JLabel("계정");
		accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		joinPanel.add(accountLabel);
		accountLabel.setLayout(null);
		accountLabel.setBounds(140, 215, 60, 23);

		JTextField accountTextField = new JTextField();
		joinPanel.add(accountTextField);
		accountTextField.setColumns(15);
		accountTextField.setLayout(null);
		accountTextField.setBounds(192, 215, 180, 23);

		JLabel phoneNumberLabel = new JLabel("연락처");
		phoneNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		phoneNumberLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		joinPanel.add(phoneNumberLabel);
		phoneNumberLabel.setLayout(null);
		phoneNumberLabel.setBounds(130, 265, 60, 23);

		JTextField phoneNumberTextField = new JTextField();
		joinPanel.add(phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setLayout(null);
		phoneNumberTextField.setBounds(192, 265, 180, 23);

		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		joinPanel.add(passwordLabel);
		passwordLabel.setLayout(null);
		passwordLabel.setBounds(127, 315, 60, 23);

		JTextField passwordTextField = new JTextField();
		joinPanel.add(passwordTextField);
		passwordTextField.setColumns(15);
		passwordTextField.setLayout(null);
		passwordTextField.setBounds(194, 315, 180, 23);

		JLabel passwordCheckLabel = new JLabel("비밀번호 확인");
		passwordCheckLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordCheckLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		joinPanel.add(passwordCheckLabel);
		passwordCheckLabel.setLayout(null);
		passwordCheckLabel.setBounds(90, 365, 100, 23);

		JTextField passwordCheckTextField = new JTextField();
		joinPanel.add(passwordCheckTextField);
		passwordCheckTextField.setColumns(10);
		passwordCheckTextField.setLayout(null);
		passwordCheckTextField.setBounds(194, 365, 180, 23);

		JButton joinButton = new JButton("회원가입");
		joinButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		joinButton.setForeground(new Color(255, 255, 255));
		joinButton.setBackground(new Color(135, 206, 250));
		joinPanel.add(joinButton);
		joinButton.setBounds(125, 457, 124, 37);
		joinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent T) {
				try {
					if (administratorCheckBox.isSelected() == true) {
						if (passwordTextField.getText().equals(passwordCheckTextField.getText())) {
							JOptionPane.showMessageDialog(null, "회원가입 되셨습니다.");

							BufferedWriter bufferedwriter = new BufferedWriter(
									new FileWriter(administratorsFile, true));
							bufferedwriter.write(nameTextField.getText() + "/");
							bufferedwriter.write(accountTextField.getText() + "/");
							bufferedwriter.write(phoneNumberTextField.getText() + "/");
							bufferedwriter.write(passwordTextField.getText() + "/");
							bufferedwriter.write(passwordCheckTextField.getText() + "/\n");
							bufferedwriter.close();

							setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다. \n비밀번호를 확인해주세요.");
						}

					} else if (userCheckBox.isSelected() == true) {
						if (passwordTextField.getText().equals(passwordCheckTextField.getText())) {
							JOptionPane.showMessageDialog(null, "회원가입 되셨습니다.");

							BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(membersFile, true));
							bufferedwriter.write(nameTextField.getText() + "/");
							bufferedwriter.write(accountTextField.getText() + "/");
							bufferedwriter.write(phoneNumberTextField.getText() + "/");
							bufferedwriter.write(passwordTextField.getText() + "/");
							bufferedwriter.write(passwordCheckTextField.getText() + "/\n");
							bufferedwriter.close();

							setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다. \n비밀번호를 확인해주세요.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "관리자/사용자에 체크해주세요.");

					}

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하셨습니다. 다시 확인해주세요.");
				}

			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setBackground(new Color(135, 206, 250));
		joinPanel.add(cancelButton);
		cancelButton.setBounds(276, 457, 134, 37);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent T) {
				JOptionPane.showMessageDialog(null, "취소되었습니다.");
				setVisible(true);
			}
		});

		setVisible(true);
	}

}