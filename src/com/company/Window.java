package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public static final String FILE_END = "encinf";
    private JTextField textField;
    private JButton hashText;
    private JButton asymmetricKeyButton;
    private JButton symmetricKeyButton;
    private JButton serializationButton;
    private JPanel panel;
    private JTextArea textArea1;
    Info info = new Info();



    public String saveL() {
        String path = null;
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_END, FILE_END);
        c.setFileFilter(filter);
       // c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int rVal = c.showSaveDialog(Window.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            path = c.getSelectedFile().toString();
            if (!path.endsWith(".encinf")) {
                path += "." + FILE_END;
            }
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            /* no-op */
        }
        return path;
    }

    private ActionListener hashText() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setTextField(textField.getText());
                info.textToHash();
            }
        };
    }
    private ActionListener asymmetricKey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setTextField(textField.getText());
                info.textAsymmetricKeyEncrypt();
            }
        };
    }
    private ActionListener symmetricKey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setTextField(textField.getText());
                info.textSymmetricKeyEncrypt();
            }
        };
    }
    //TODO choose field to save and beautiful save
    private ActionListener saveInfo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveWindow saveWindow = new SaveWindow();
                String path = saveL();
                System.out.println(saveWindow.hashTextCheckBox.isSelected());
                if (path != null) {
                    InfoSaver.saveObject(path, info);
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
        asymmetricKeyButton.addActionListener(asymmetricKey());
        symmetricKeyButton.addActionListener(symmetricKey());
        serializationButton.addActionListener(saveInfo());

        setVisible(true);
    }
}
