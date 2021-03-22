package account;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//import miniProject.book.ModifyBook.EventHandler;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;

public class BookSelect extends JFrame {

	private JPanel contentPane;

	private JTextField tBookName;
	private JTextField tAuthor;
	private JTextField tPublisher;
	private JTextField tPublicationYear;

	private JTable table = new JTable();
	private Vector<String> comlumn = new Vector<String>();
	private DefaultTableModel tm = null;
	private JScrollPane scrollPane = new JScrollPane(table);

	public BookSelect() {
		initBook();
	}

	private void initBook() {
		tm = new DefaultTableModel(new Object[] { "책 제목", "저자", "출판사", "코드번호", "위치" }, 0);
		table.setModel(tm);

		setBounds(100, 100, 806, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tBookName = new JTextField();
		tBookName.setBounds(270, 101, 284, 35);
		tBookName.addActionListener(new EventHandler());
		contentPane.add(tBookName);
		tBookName.setColumns(10);

		JLabel lblNewLabel = new JLabel("책 제목");
		lblNewLabel.setBounds(196, 101, 62, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("저자");
		lblNewLabel_1.setBounds(196, 146, 62, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("출판사");
		lblNewLabel_2.setBounds(196, 191, 62, 35);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("출판년도");
		lblNewLabel_3.setBounds(196, 236, 62, 35);
		contentPane.add(lblNewLabel_3);

		JButton search = new JButton("조회");
		search.setBounds(331, 463, 97, 35);
		search.addActionListener(new EventHandler());
		contentPane.add(search);

		tAuthor = new JTextField();
		tAuthor.setColumns(10);
		tAuthor.setBounds(270, 146, 284, 35);
		tAuthor.addActionListener(new EventHandler());
		contentPane.add(tAuthor);

		tPublisher = new JTextField();
		tPublisher.setColumns(10);
		tPublisher.setBounds(270, 191, 284, 35);
		tPublisher.addActionListener(new EventHandler());
		contentPane.add(tPublisher);

		tPublicationYear = new JTextField();
		tPublicationYear.setColumns(10);
		tPublicationYear.setBounds(270, 236, 284, 35);
		tPublicationYear.addActionListener(new EventHandler());
		contentPane.add(tPublicationYear);

		scrollPane.setBounds(196, 292, 358, 156);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setBounds(437, 269, 82, 21);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("잭 정보 조회");
		lblNewLabel_6.setBounds(349, 35, 215, 21);
		contentPane.add(lblNewLabel_6);

		setVisible(true);
	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//
			String bookName = tBookName.getText();
			String author = tAuthor.getText();
			String publisher = tPublisher.getText();
			String publicationYear = tPublicationYear.getText();
			String filePath = "./resources/bookInfo.txt";
			String line = "";

			System.out.println(bookName + author);
			try {
				File file = new File(filePath);
				BufferedReader bufReader = new BufferedReader(new FileReader(file));
				while ((line = bufReader.readLine()) != null) {
					String[] strAry = line.split("/");
					if (strAry[0].equals(bookName)) {
						tm.addRow(new Object[] { strAry[0], strAry[1], strAry[2], strAry[3] });
						tAuthor.setText(strAry[1]);
					}
				}
				bufReader.close();
			} catch (FileNotFoundException fileException) {
				fileException.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
