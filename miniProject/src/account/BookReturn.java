package account;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;


public class BookReturn extends JFrame {
   File bookFile = new File("./resources/save.txt");
   private DefaultTableModel tm = null;
   private String userNames;
   private String bookNames;

   public BookReturn() {
      getContentPane().setBackground(Color.WHITE);
      setTitle("도서 반납");
      setBounds(100, 100, 513, 598);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);

      JLabel BookReturn = new JLabel("도서 반납");
      BookReturn.setHorizontalAlignment(SwingConstants.CENTER);
      BookReturn.setFont(new Font("맑은 고딕", Font.BOLD, 35));
      BookReturn.setBounds(148, 44, 213, 39);
      getContentPane().add(BookReturn);

      JLabel user = new JLabel("사용자");
      user.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      user.setHorizontalAlignment(SwingConstants.CENTER);
      user.setBounds(58, 161, 110, 39);
      getContentPane().add(user);

      JTextField userNameText = new JTextField();
      userNameText.setBounds(154, 168, 236, 30);
      userNameText.setForeground(new Color(0, 0, 0));
      userNameText.setBackground(new Color(255, 255, 255));
      getContentPane().add(userNameText);

      JLabel bookName = new JLabel("도서명");
      bookName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      bookName.setHorizontalAlignment(SwingConstants.CENTER);
      bookName.setBounds(85, 258, 57, 15);
      getContentPane().add(bookName);

      JTextField bookNameText = new JTextField();
      bookNameText.setColumns(15);
      bookNameText.setBounds(154, 252, 234, 30);
      bookNameText.setForeground(new Color(0, 0, 0));
      bookNameText.setBackground(new Color(255, 255, 255));
      getContentPane().add(bookNameText);

      JButton returnButton = new JButton("반납");
      returnButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      returnButton.setForeground(new Color(255, 255, 255));
      returnButton.setBackground(new Color(135, 206, 250));
      
      returnButton.setBounds(123, 397, 110, 48);
      getContentPane().add(returnButton);
      
      JButton MainButton = new JButton("메인메뉴");
      MainButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      MainButton.setBounds(280, 397, 110, 48);
      MainButton.setForeground(new Color(255, 255, 255));
      MainButton.setBackground(new Color(135, 206, 250));
      getContentPane().add(MainButton);
      MainButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            setVisible(true);
            setVisible(false);            
         }
         
      });

      returnButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (userNameText.getText().length() != 0 || bookNameText.getText().length() != 0) {
               try {
                  String line = "";
                  String strWrite = new String();
                  boolean existBook = false;

                  BufferedReader br = new BufferedReader(new FileReader(bookFile));
                  
                  while ((line = br.readLine()) != null) {
                     String[] array = line.split("/");
                     if(array.length < 3) {  
                        strWrite += line + "\r\n";
                        continue;
                     }
                     if(!array[2].equals("대여불가")) {  
                        strWrite += line + "\r\n";
                        continue;
                     }
                     if(!array[0].equals(bookNameText.getText())) {
                        strWrite += line + "\r\n";
                        continue;
                     }
                     if (!array[1].equals(userNameText.getText())) { 
                        strWrite += line + "\r\n";
                        continue;
                     }
                     strWrite="";
                     existBook = true;
                  }
                  br.close();
                  BufferedWriter bw = new BufferedWriter(new FileWriter(bookFile,false));
                  bw.write(strWrite);
                  bw.close();
                  
                  if(existBook) {
                     JOptionPane.showMessageDialog(null, "반납 되었습니다.");
                  } else {
                     JOptionPane.showMessageDialog(null, "반납할 책을 찾지 못했습니다. 정보를 다시 확인해주세요");
                  }
               } catch (IOException e1) {
                  e1.printStackTrace();
               }

            }

         }
      });

      setVisible(true);
   }
}