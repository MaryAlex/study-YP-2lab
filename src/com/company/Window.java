package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private JTextField textField;
    private JButton hashText;
    private JButton asymmetricKeyButton;
    private JButton button3;
    private JButton button4;
    private JPanel panel;
    private JTextArea textArea1;
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
    private ActionListener asymmetricKey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.textField = textField.getText();
                info.textAsymmetricKeyEncrypt();
            }
        };
    }

    public Window() {
        super("Hello");
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hashText.addActionListener(hashText());
        asymmetricKeyButton.addActionListener(asymmetricKey());

        setVisible(true);
    }
}
