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

public class UserModify extends JFrame {

	private JPanel userModifyPanel;
	String userFilePath = "./resources/userInfo.txt";
	String userId = " ";

	public UserModify(String userId) {
		this.userId = userId;
		initModify();
	}

	public void initModify() {
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(userFilePath));
			while ((line = br.readLine()) != null) {
				String[] array = line.split("/");
				if (array[1].equals(userId)) {
					setBounds(100, 100, 513, 598);
					userModifyPanel = new JPanel();
					userModifyPanel.setBackground(Color.WHITE);
					userModifyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(userModifyPanel);
					setTitle("사용자 정보 수정");
					setLocationRelativeTo(null);
					userModifyPanel.setLayout(null);
					userModifyPanel.setLayout(null);

					JLabel joinLabel = new JLabel("사용자 정보 수정");
					joinLabel.setHorizontalAlignment(SwingConstants.CENTER);
					joinLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
					userModifyPanel.add(joinLabel);
					joinLabel.setLayout(null);
					joinLabel.setBounds(80, 25, 350, 60);

					JLabel nameLabel = new JLabel("이름");
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					userModifyPanel.add(nameLabel);
					nameLabel.setLayout(null);
					nameLabel.setBounds(140, 145, 60, 23);

					JTextField nameTextField = new JTextField(array[0]);
					userModifyPanel.add(nameTextField);
					nameTextField.setColumns(15);
					nameTextField.setLayout(null);
					nameTextField.setBounds(192, 145, 180, 23);

					JLabel accountLabel = new JLabel("계정");
					accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
					accountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					userModifyPanel.add(accountLabel);
					accountLabel.setLayout(null);
					accountLabel.setBounds(140, 195, 60, 23);

					JTextField userIdTextField = new JTextField(array[1]);
					userModifyPanel.add(userIdTextField);
					userIdTextField.setColumns(15);
					userIdTextField.setLayout(null);
					userIdTextField.setBounds(192, 195, 180, 23);

					JLabel phoneNumberLabel = new JLabel("연락처");
					phoneNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
					phoneNumberLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					userModifyPanel.add(phoneNumberLabel);
					phoneNumberLabel.setLayout(null);
					phoneNumberLabel.setBounds(130, 245, 60, 23);

					JTextField phoneNumberTextField = new JTextField(array[2]);
					userModifyPanel.add(phoneNumberTextField);
					phoneNumberTextField.setColumns(10);
					phoneNumberTextField.setLayout(null);
					phoneNumberTextField.setBounds(192, 245, 180, 23);

					JLabel passwordLabel = new JLabel("비밀번호");
					passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
					passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					userModifyPanel.add(passwordLabel);
					passwordLabel.setLayout(null);
					passwordLabel.setBounds(127, 295, 60, 23);

					JTextField passwordTextField = new JTextField(array[3]);
					userModifyPanel.add(passwordTextField);
					passwordTextField.setColumns(15);
					passwordTextField.setLayout(null);
					passwordTextField.setBounds(194, 295, 180, 23);

					JLabel passwordCheckLabel = new JLabel("비밀번호 확인");
					passwordCheckLabel.setHorizontalAlignment(SwingConstants.CENTER);
					passwordCheckLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					userModifyPanel.add(passwordCheckLabel);
					passwordCheckLabel.setLayout(null);
					passwordCheckLabel.setBounds(90, 345, 100, 23);

					JTextField passwordCheckTextField = new JTextField(array[4]);
					userModifyPanel.add(passwordCheckTextField);
					passwordCheckTextField.setColumns(10);
					passwordCheckTextField.setLayout(null);
					passwordCheckTextField.setBounds(194, 345, 180, 23);

					JButton joinButton = new JButton("수정");
					joinButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
					joinButton.setForeground(new Color(255, 255, 255));
					joinButton.setBackground(new Color(135, 206, 250));
					userModifyPanel.add(joinButton);
					joinButton.setBounds(125, 457, 124, 37);
					joinButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent T) {
							BufferedWriter bw = null;
							BufferedReader br = null;
							String line = null;
							String userModify = new String();

							try {
								br = new BufferedReader(new FileReader(userFilePath));
								while ((line = br.readLine()) != null) {
									String[] array = line.split("/");
									if (array[1].equals(userId)) {
										userModify += nameTextField.getText() + "/";
										userModify += userIdTextField.getText() + "/";
										userModify += phoneNumberTextField.getText() + "/";
										userModify += passwordTextField.getText() + "/";
										userModify += passwordCheckTextField.getText() + "/\n";
									} else {
										userModify += line + "\r\n";
									}
								}

								bw = new BufferedWriter(new FileWriter(userFilePath, false));
								bw.write(userModify);
								
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
					userModifyPanel.add(cancelButton);
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