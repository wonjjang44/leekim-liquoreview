package account;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class BookRental extends JFrame {

	private JPanel bookRentalPane;
	private JTextField textBookTitle;
	private JTextField textUserName;
	private JTextField textConditions;
	private File saveFile = new File("./resources/save.txt");
	private JTable table = new JTable();
	private JTable rentalTable = new JTable();
	private DefaultTableModel tm = null;
	private DefaultTableModel tm1 = null;
	private JScrollPane listScrollPane = new JScrollPane(table);
	private JScrollPane rentalScrollPane = new JScrollPane(rentalTable);
	

	public BookRental() {
		setBackground(new Color(255, 255, 255));
		initBook();
		textFile();
		saveFile();
	}

	private void initBook() {
		setTitle("도서 대여");
		tm = new DefaultTableModel(new Object[] { "책 제목", "저자", "출판사", "코드번호"}, 0);
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		table.setModel(tm);
		tm1 = new DefaultTableModel(new Object[] { "책 제목", "사용자이름", "상태"}, 0);
		rentalTable.setForeground(new Color(0, 0, 0));
		rentalTable.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rentalTable.setModel(tm1); 
		setLocationRelativeTo(null);
		setBounds(100, 100, 545, 587);
		bookRentalPane = new JPanel();
		bookRentalPane.setBackground(new Color(255, 255, 255));
		bookRentalPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bookRentalPane);
		bookRentalPane.setLayout(null);

		textBookTitle = new JTextField();
		textBookTitle.setBackground(new Color(255, 255, 255));
		textBookTitle.setBounds(121, 284, 284, 35);
		bookRentalPane.add(textBookTitle);
		textBookTitle.setColumns(10);

		JLabel bookListLabel = new JLabel("책 목록");
		bookListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		bookListLabel.setBounds(48, 122, 61, 35);
		bookRentalPane.add(bookListLabel);

		JLabel rentalConditionsLabel = new JLabel("대여목록");
		rentalConditionsLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		rentalConditionsLabel.setBounds(42, 215, 61, 35);
		bookRentalPane.add(rentalConditionsLabel);

		JLabel bookTitleLabel = new JLabel("책 제목");
		bookTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		bookTitleLabel.setBounds(48, 284, 61, 35);
		bookRentalPane.add(bookTitleLabel);

		JLabel userNameLabel = new JLabel("사용자 이름");
		userNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		userNameLabel.setBounds(25, 329, 83, 35);
		bookRentalPane.add(userNameLabel);

		JButton rentalButton = new JButton("대여");
		rentalButton.setForeground(new Color(255, 255, 255));
		rentalButton.setBackground(new Color(135, 206, 250));
		rentalButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		rentalButton.setBounds(121, 434, 123, 47);
		rentalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					BufferedReader bookReader = new BufferedReader(
							new FileReader("./resources/bookInfo.txt"));
					BufferedReader member = new BufferedReader(
							new FileReader("./resources/userInfo.txt"));
					String line = "";
					String line1 ="";
					boolean tr = false;
					boolean tr1 = false;
					while ((line = bookReader.readLine()) != null) {
						String bookArray[] = line.split("/"); // 1 id 3 pw
						if(bookArray[0].equals(textBookTitle.getText())) {
							textBookTitle.setText(bookArray[0]);
							tr = true;
							break;
						}
					}
					while ((line = member.readLine()) != null) {
						String array[] = line.split("/");
					if(array[0].equals(textUserName.getText())) {
						textUserName.setText(array[0]);
						tr1 = true;
						break;
					}
					}
					if(tr==tr1) {
						BufferedWriter bufferedwriter = new BufferedWriter(
								new FileWriter(saveFile, true));
						bufferedwriter.write(textBookTitle.getText() + "/");
						bufferedwriter.append(textUserName.getText()+ "/");
						bufferedwriter.append(textConditions.getText() + "/\n");
						bufferedwriter.close();
						JOptionPane.showMessageDialog(null, "대여가 완료 되었습니다.");
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "책이름 및 사용자이름을 확인해주세요");
					}
					member.close();
					bookReader.close();
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		bookRentalPane.add(rentalButton);

		JLabel conditionsLabel = new JLabel("상태");
		conditionsLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		conditionsLabel.setBounds(67, 374, 42, 35);
		bookRentalPane.add(conditionsLabel);

		textUserName = new JTextField();
		textUserName.setColumns(10);
		textUserName.setBounds(121, 329, 284, 35);
		bookRentalPane.add(textUserName);

		textConditions = new JTextField("대여불가만 입력하세요!");
		textConditions.setColumns(10);
		textConditions.setBounds(121, 374, 284, 35);
		textConditions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textConditions.setText("");
			}
		}); 
			
		
		bookRentalPane.add(textConditions);

		listScrollPane.setBounds(121, 87, 284, 98);
		bookRentalPane.add(listScrollPane);
		
		
		rentalScrollPane.setBounds(121, 191, 284, 83);
		bookRentalPane.add(rentalScrollPane);
		
		JButton cancelButton = new JButton("취소");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		cancelButton.setBackground(new Color(135, 206, 250));
		cancelButton.setBounds(282, 434, 123, 47);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		bookRentalPane.add(cancelButton);
		
		JLabel titleLabel = new JLabel("도서 대여");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(101, 10, 304, 60);
		bookRentalPane.add(titleLabel);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void textFile() {
		try {
			File file = new File("./resources/bookInfo.txt");
			FileReader fileReaderBook = new FileReader(file);
			BufferedReader bufReaderBook = new BufferedReader(fileReaderBook);
			String line = "";
			while((line = bufReaderBook.readLine()) != null) {
				String[] strAry = line.split("/");
				
				if (strAry.length == 4) {
					tm.addRow(new Object[] { strAry[0], strAry[1], strAry[2], strAry[3]});
				}
				
			}
			
			bufReaderBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}
	
	private void saveFile() {
		try {
			File file = new File("./resources/save.txt");
			FileReader fileReaderSave = new FileReader(file);
			BufferedReader bufReaderSave = new BufferedReader(fileReaderSave);
			String line = "";
			while((line = bufReaderSave.readLine()) != null) {
				String[] strAry = line.split("/");
				
				if (strAry.length == 3) {
					tm1.addRow(new Object[] { strAry[0], strAry[1], strAry[2]});
				}
			}
			bufReaderSave.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}

	}
	
}
