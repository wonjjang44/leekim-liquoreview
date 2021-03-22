package manage;

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
import java.io.FileNotFoundException;
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

public class AdminModify extends JFrame {

	private JPanel adminModifyPanel;
	String adminFilePath = "./resources/adminInfo.txt";
	String adminId = " ";

	public AdminModify(String adminId) {
		this.adminId = adminId;
		initModify();
	}

	public void initModify() {
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(adminFilePath));
			while ((line = br.readLine()) != null) {
				String[] array = line.split("/");
				if (array[1].equals(adminId)) {
					setBounds(100, 100, 513, 598);
					adminModifyPanel = new JPanel();
					adminModifyPanel.setBackground(Color.WHITE);
					adminModifyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(adminModifyPanel);
					setTitle("관리자 정보 수정");
					setLocationRelativeTo(null);
					adminModifyPanel.setLayout(null);
					adminModifyPanel.setLayout(null);

					JLabel joinLabel = new JLabel("관리자 정보 수정");
					joinLabel.setHorizontalAlignment(SwingConstants.CENTER);
					joinLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
					adminModifyPanel.add(joinLabel);
					joinLabel.setLayout(null);
					joinLabel.setBounds(80, 25, 350, 60);

					JLabel nameLabel = new JLabel("이름");
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					adminModifyPanel.add(nameLabel);
					nameLabel.setLayout(null);
					nameLabel.setBounds(140, 145, 60, 23);

					JTextField nameTextField = new JTextField(array[0]);
					adminModifyPanel.add(nameTextField);
					nameTextField.setColumns(15);
					nameTextField.setLayout(null);
					nameTextField.setBounds(192, 145, 180, 23);

					JLabel accountLabel = new JLabel("계정");
					accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
					accountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					adminModifyPanel.add(accountLabel);
					accountLabel.setLayout(null);
					accountLabel.setBounds(140, 195, 60, 23);

					JTextField adminIdTextField = new JTextField(array[1]);
					adminModifyPanel.add(adminIdTextField);
					adminIdTextField.setColumns(15);
					adminIdTextField.setLayout(null);
					adminIdTextField.setBounds(192, 195, 180, 23);

					JLabel phoneNumberLabel = new JLabel("연락처");
					phoneNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
					phoneNumberLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					adminModifyPanel.add(phoneNumberLabel);
					phoneNumberLabel.setLayout(null);
					phoneNumberLabel.setBounds(130, 245, 60, 23);

					JTextField phoneNumberTextField = new JTextField(array[2]);
					adminModifyPanel.add(phoneNumberTextField);
					phoneNumberTextField.setColumns(10);
					phoneNumberTextField.setLayout(null);
					phoneNumberTextField.setBounds(192, 245, 180, 23);

					JLabel passwordLabel = new JLabel("비밀번호");
					passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
					passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					adminModifyPanel.add(passwordLabel);
					passwordLabel.setLayout(null);
					passwordLabel.setBounds(127, 295, 60, 23);

					JTextField passwordTextField = new JTextField(array[3]);
					adminModifyPanel.add(passwordTextField);
					passwordTextField.setColumns(15);
					passwordTextField.setLayout(null);
					passwordTextField.setBounds(194, 295, 180, 23);

					JLabel passwordCheckLabel = new JLabel("비밀번호 확인");
					passwordCheckLabel.setHorizontalAlignment(SwingConstants.CENTER);
					passwordCheckLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					adminModifyPanel.add(passwordCheckLabel);
					passwordCheckLabel.setLayout(null);
					passwordCheckLabel.setBounds(90, 345, 100, 23);

					JTextField passwordCheckTextField = new JTextField(array[4]);
					adminModifyPanel.add(passwordCheckTextField);
					passwordCheckTextField.setColumns(10);
					passwordCheckTextField.setLayout(null);
					passwordCheckTextField.setBounds(194, 345, 180, 23);

					JButton joinButton = new JButton("수정");
					joinButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
					joinButton.setForeground(new Color(255, 255, 255));
					joinButton.setBackground(new Color(135, 206, 250));
					adminModifyPanel.add(joinButton);
					joinButton.setBounds(125, 457, 124, 37);
					joinButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent T) {
							BufferedWriter bw = null;
							BufferedReader br = null;
							String line = null;
							String adminModify = new String();

							try {
								br = new BufferedReader(new FileReader(adminFilePath));
								while ((line = br.readLine()) != null) {
									String[] array = line.split("/");
									if (array[1].equals(adminId)) {
										adminModify += nameTextField.getText() + "/";
										adminModify += adminIdTextField.getText() + "/";
										adminModify += phoneNumberTextField.getText() + "/";
										adminModify += passwordTextField.getText() + "/";
										adminModify += passwordCheckTextField.getText() + "/\n";
									} else {
										adminModify += line + "\r\n";
									}
								}

								bw = new BufferedWriter(new FileWriter(adminFilePath, false));
								bw.write(adminModify);
								
								JOptionPane.showMessageDialog(null, "수정되었습니다.");
								setVisible(false);
							} catch (IOException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "수정에 실패했습니다.");
							} finally {
								try {
									bw.close();
									br.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}

						}
					});

					JButton cancelButton = new JButton("취소");
					cancelButton.setBackground(Color.LIGHT_GRAY);
					cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
					cancelButton.setForeground(new Color(255, 255, 255));
					cancelButton.setBackground(new Color(135, 206, 250));
					adminModifyPanel.add(cancelButton);
					cancelButton.setBounds(276, 457, 134, 37);
					cancelButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent T) {
							JOptionPane.showMessageDialog(null, "취소되었습니다.");
							setVisible(true);
							setVisible(false);
						}
					});

					setVisible(true);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}