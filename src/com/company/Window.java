package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Window extends JFrame {
    private JTextField textField;
    private JButton hashText;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel;
    Info info = new Info();

    private ActionListener hashText() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.textField = textField.getText();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.reset();
                    messageDigest.update(info.textField.getBytes());

                    byte result[] = messageDigest.digest();
                    StringBuffer buf = new StringBuffer();
                    for (byte bytes : result) {
                        buf.append(String.format("%02x", bytes & 0xff));
                    }
                    System.out.println(buf.toString());
                } catch (NoSuchAlgorithmException e1) {
                    e1.getMessage();
                }

            }
        };
    }

    public Window() {
        super("Hello");
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hashText.addActionListener(hashText());

        setVisible(true);
    }
}
