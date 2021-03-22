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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputCode extends JFrame {

   private JFrame frame;
   private JTextField codeFieldText;
   private JButton btnInput;

   public InputCode() {
      getContentPane().setBackground(Color.WHITE);
      initialize();
      setLocationRelativeTo(null);

   }

   private void initialize() {
      setTitle("코드 입력");
      setBounds(100, 100, 279, 220);
      getContentPane().setLayout(null);

      JLabel lblNewLabel = new JLabel("도서코드를 입력하세요");
      lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel.setBounds(55, 15, 165, 36);
      getContentPane().add(lblNewLabel);

      codeFieldText = new JTextField();
      codeFieldText.setBounds(17, 57, 223, 36);
      getContentPane().add(codeFieldText);
      codeFieldText.setColumns(10);

      btnInput = new JButton("입력");
      btnInput.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      btnInput.setForeground(new Color(255, 255, 255));
      btnInput.setBackground(new Color(153, 204, 255));
      btnInput.setBounds(36, 120, 74, 29);
      getContentPane().add(btnInput);
      btnInput.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            BufferedReader br = null;
            try {
               String s = null;
               boolean find = false;
               br = new BufferedReader(new FileReader("./resources/BookInfo.txt"));
               if (e.getSource() == btnInput) {
                  while ((s = br.readLine()) != null) {
                     String[] array = s.split("/");
                     if (array[3].equals(codeFieldText.getText())) {
                        find = true;
                        new BookModifier(array[3]);
                        setVisible(false);
                        break;
                     } else {
                        find = false;
                     }
                  }
                  
                  if(find==false)
                     JOptionPane.showMessageDialog(null, "코드 6자리를 확인해주세요.");
               }
               
            } catch (Exception e2) {
               JOptionPane.showMessageDialog(null, "입력에 실패했습니다.");
            } finally {
               try {
                  br.close();
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
            }

         }
      });

      JButton btnCancel = new JButton("취소");
      btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      btnCancel.setForeground(new Color(255, 255, 255));
      btnCancel.setBackground(new Color(153, 204, 255));
      btnCancel.setBounds(146, 120, 74, 29);
      getContentPane().add(btnCancel);
      btnCancel.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("취소")) {
               setVisible(false);
            }
         }
      });

      setVisible(true);
   }
}