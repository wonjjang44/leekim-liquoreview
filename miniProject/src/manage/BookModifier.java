package manage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class BookModifier extends JFrame {

   private JFrame frame;
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;
   private String inputText = "";

   public BookModifier(String inputText) {
      getContentPane().setBackground(Color.WHITE);
      this.inputText = inputText;
      initialize();
      setLocationRelativeTo(null);
   }

   private void initialize() {

      try {
         String s = null;
         BufferedReader br = new BufferedReader(new FileReader("./resources/BookInfo.txt"));
         while ((s = br.readLine()) != null) {
            String[] array = s.split("/");
            if (array[3].equals(inputText)) {

               setTitle("책 수정");
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

               textField = new JTextField(array[0]);
               textField.setBounds(116, 64, 149, 27);
               getContentPane().add(textField);
               textField.setColumns(10);

               textField_1 = new JTextField(array[1]);
               textField_1.setColumns(10);
               textField_1.setBounds(116, 118, 149, 27);
               getContentPane().add(textField_1);

               textField_2 = new JTextField(array[2]);
               textField_2.setColumns(10);
               textField_2.setBounds(116, 172, 149, 27);
               getContentPane().add(textField_2);

               JButton btnNewButton_2 = new JButton("취소");
               btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
               btnNewButton_2.setForeground(new Color(255, 255, 255));
               btnNewButton_2.setBackground(new Color(153, 204, 255));
               btnNewButton_2.setBounds(308, 117, 72, 29);
               getContentPane().add(btnNewButton_2);
               btnNewButton_2.addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent e) {
                     if (e.getActionCommand().equals("취소")) {
                        new InputCode();
                        setVisible(false);
                     }
                  }
               });

               JButton btnNewButton_2_1 = new JButton("저장");
               btnNewButton_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
               btnNewButton_2_1.setForeground(new Color(255, 255, 255));
               btnNewButton_2_1.setBackground(new Color(153, 204, 255));
               btnNewButton_2_1.setBounds(308, 63, 72, 29);
               getContentPane().add(btnNewButton_2_1);
               btnNewButton_2_1.addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent e) {
                     if (e.getSource() == btnNewButton_2_1) {
                        if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
                              || textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
                           JOptionPane.showMessageDialog(null, "빈 칸을 입력해주세요");
                           return;
                        }
                        BufferedWriter bw = null;
                        BufferedReader br = null;
                        BufferedReader br_1 = null;
                        try {
                           String s = null;
                           String strModifed = new String();
                           br = new BufferedReader(new FileReader("./resources/BookInfo.txt"));


                           while ((s = br.readLine()) != null) {
                              
                              String[] array = s.split("/");
                              if (array[3].equals(inputText)) {
                                 strModifed += textField.getText() + "/";
                                 strModifed += textField_1.getText() + "/";
                                 strModifed += textField_2.getText() + "/";
                                 strModifed += textField_3.getText() + "\r\n";
                              } else {
                                 strModifed += s+"\r\n";
                              }
                              
                           }
                           br.close();
                           
                           bw = new BufferedWriter(
                                 new FileWriter("./resources/BookInfo.txt", false));
                           bw.write(strModifed);
                           
                           JOptionPane.showMessageDialog(null, "수정되었습니다.");
                           

                        } catch (Exception e2) {
                           e2.printStackTrace();
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
                  }
               });

               JLabel lblNewLabel_1_1_1 = new JLabel("도서 코드");
               lblNewLabel_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
               lblNewLabel_1_1_1.setBounds(17, 226, 82, 21);
               getContentPane().add(lblNewLabel_1_1_1);

               textField_3 = new JTextField();
               textField_3.setColumns(10);
               textField_3.setBounds(116, 225, 149, 27);
               getContentPane().add(textField_3);
               textField_3.setText(inputText);

               setVisible(true);

            }
         }

      } catch (Exception e) {
      }
   }
}