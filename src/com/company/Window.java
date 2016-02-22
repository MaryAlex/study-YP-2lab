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
                info.textToHash();

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
