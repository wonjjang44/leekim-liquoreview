package manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookRegister extends JFrame {

	private JFrame frame;
	private JTextField bookNameTextField;
	private JTextField writerTextField;
	private JTextField publisherTextField;
	private JTextField codeTextField;

	public BookRegister() {
		getContentPane().setBackground(Color.WHITE);
		initialize();
		setLocationRelativeTo(null);

	}

	private void initialize() {

		setTitle("책 등록");
		setBounds(100, 100, 418, 400);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("책 이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(17, 65, 82, 21);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("저자");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(17, 119, 82, 21);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("출판사");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(17, 173, 82, 21);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("도서코드");
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(17, 227, 96, 21);
		getContentPane().add(lblNewLabel_1_2);

		bookNameTextField = new JTextField();
		bookNameTextField.setBounds(116, 64, 149, 27);
		getContentPane().add(bookNameTextField);
		bookNameTextField.setColumns(10);

		writerTextField = new JTextField();
		writerTextField.setColumns(10);
		writerTextField.setBounds(116, 118, 149, 27);
		getContentPane().add(writerTextField);

		publisherTextField = new JTextField();
		publisherTextField.setColumns(10);
		publisherTextField.setBounds(116, 172, 149, 27);
		getContentPane().add(publisherTextField);

		codeTextField = new JTextField();
		codeTextField.setEditable(false);
		codeTextField.setColumns(10);
		codeTextField.setBounds(116, 226, 149, 27);
		getContentPane().add(codeTextField);

		JButton btnNew = new JButton("생성");
		btnNew.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnNew.setForeground(new Color(255, 255, 255));
		btnNew.setBackground(new Color(153, 204, 255));
		btnNew.setBounds(303, 227, 76, 29);
		getContentPane().add(btnNew);
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				codeTextField.setText(numberGen(6));

			}

		});

		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(153, 204, 255));
		btnCancel.setBounds(308, 117, 72, 29);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("취소")) {
					setVisible(false);
				}
			}
		});

		JButton btnReg = new JButton("등록");
		btnReg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnReg.setForeground(new Color(255, 255, 255));
		btnReg.setBackground(new Color(153, 204, 255));
		btnReg.setBounds(308, 63, 72, 29);
		getContentPane().add(btnReg);
		setVisible(true);
		btnReg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnReg) {
					if (bookNameTextField.getText().isEmpty() || writerTextField.getText().isEmpty()
							|| publisherTextField.getText().isEmpty() || codeTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "빈 칸을 입력해주세요");
						return;
					}
					BufferedWriter bw = null;
					BufferedReader br = null;
					try {
						String s = null;
						boolean isOK = false;
						boolean isOK2 = false;
						bw = new BufferedWriter(new FileWriter("./resources/BookInfo.txt", true));
						br = new BufferedReader(new FileReader("./resources/BookInfo.txt"));
						while ((s = br.readLine()) != null) {
							String[] array = s.split("/");
							if (array[0].equals(bookNameTextField.getText())) {
								isOK = true;
								break;
							}
							if (array[3].equals(codeTextField.getText())) {
								isOK2 = true;
								break;
							}
						}
						if (isOK) {
							JOptionPane.showMessageDialog(null, "동일 이름의 책이 존재합니다.");
						} else if (isOK2) {
							JOptionPane.showMessageDialog(null, "동일 코드가 존재합니다.");
						} else {
							bw.write(bookNameTextField.getText() + "/");
							bw.write(writerTextField.getText() + "/");
							bw.write(publisherTextField.getText() + "/");
							bw.write(codeTextField.getText() + "\r\n");
							JOptionPane.showMessageDialog(null, "등록되었습니다.");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "등록에 실패했습니다.");
					} finally {
						try {
							bw.close();
							br.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

	}

	public String numberGen(int len) {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < len; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			if (true) {
				if (!numStr.contains(ran)) {
					numStr += ran;
				} else {
					i -= 1;
				}
			}
		}
		return numStr;
	}

}