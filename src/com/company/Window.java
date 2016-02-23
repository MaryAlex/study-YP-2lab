package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Window extends JFrame {
    public static final String FILE_END = "encinf";
    private JTextField textField;
    private JButton hashText;
    private JButton asymmetricKeyButton;
    private JButton symmetricKeyButton;
    private JButton serializationButton;
    private JPanel panel;
    private JTextArea out;
    private JComboBox hashComboBox;
    private JComboBox asymmetricComboBox;
    private JComboBox symmetricComboBox;
    private JButton loadButton;
    Info info = new Info();

    public void print() {
        out.setText("");
        info.printInTextArea(out);
    }

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

    public String openL() {
        String path = null;
        JFileChooser c = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_END, FILE_END);
        c.setFileFilter(filter);
       // c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int rVal = c.showOpenDialog(Window.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            path = c.getSelectedFile().toString();
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
                info.setHashAlgorithm(hashComboBox.getSelectedItem().toString());
                info.textToHash();
                print();
                SaveWindow saveWindow = new SaveWindow(false);
                saveWindow.hashTextCheckBox.setSelected(true);
                saveWindow.hashAlgorithmCheckBox.setSelected(true);
                info.method = "Hash";
                InfoSaver.saveObject("/home/mary/Work/Java/YP/2lab/src/file." + FILE_END, info, saveWindow);
                info.method = "";
            }
        };
    }
    private ActionListener asymmetricKey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setTextField(textField.getText());
                info.setAsymmetricAlgorithm(asymmetricComboBox.getSelectedItem().toString());
                info.textAsymmetricKeyEncrypt();
                print();
                SaveWindow saveWindow = new SaveWindow(false);
                saveWindow.encryptedByAsymmetricCheckBox.setSelected(true);
                saveWindow.asymmetricAlgorithmCheckBox.setSelected(true);
                saveWindow.publicAsymmetricKeyCheckBox.setSelected(true);
                info.method = "Symmetric key";
                InfoSaver.saveObject("/home/mary/Work/Java/YP/2lab/src." + FILE_END, info, saveWindow);
                info.method = "";
            }
        };
    }
    private ActionListener symmetricKey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setTextField(textField.getText());
                info.setSymmetricAlgorithm(symmetricComboBox.getSelectedItem().toString());
                info.textSymmetricKeyEncrypt();
                print();
                SaveWindow saveWindow = new SaveWindow(false);
                saveWindow.encryptedBySymmetricCheckBox.setSelected(true);
                saveWindow.symmetricAlgorithmCheckBox.setSelected(true);
                saveWindow.publicSymmetricKeyCheckBox.setSelected(true);
                info.method = "Symmetric key";
                InfoSaver.saveObject("/home/mary/Work/Java/YP/2lab/src." + FILE_END, info, saveWindow);
                info.method = "";
            }
        };
    }

    private ActionListener saveInfo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveWindow saveWindow = new SaveWindow(true);
                String path = saveL();
                if (path != null) {
                    InfoSaver.saveObject(path, info, saveWindow);
                }
            }
        };
    }

    private ActionListener loadInfo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = openL();
                if (path != null) {
                    info = InfoSaver.loadObject(path);
                }
                print();
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
        loadButton.addActionListener(loadInfo());
        JMenuBar menuBar = new JMenuBar();

        // File Menu, F - Mnemonic
        JMenu fileMenu = new JMenu("Menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        // File->New, N - Mnemonic
        JMenuItem newMenuItem = new JMenuItem("Hash", KeyEvent.VK_N);
        newMenuItem.addActionListener(hashText());
        fileMenu.add(newMenuItem);
        JMenuItem newMenuItem3 = new JMenuItem("Asymmetric", KeyEvent.VK_N);
        newMenuItem3.addActionListener(asymmetricKey());
        fileMenu.add(newMenuItem3);
        JMenuItem newMenuItem4 = new JMenuItem("Symmetric", KeyEvent.VK_N);
        newMenuItem4.addActionListener(symmetricKey());
        fileMenu.add(newMenuItem4);
        JMenuItem newMenuItem6 = new JMenuItem("Save", KeyEvent.VK_N);
        newMenuItem6.addActionListener(saveInfo());
        fileMenu.add(newMenuItem6);
        JMenuItem newMenuItem7 = new JMenuItem("Load", KeyEvent.VK_N);
        newMenuItem7.addActionListener(loadInfo());
        fileMenu.add(newMenuItem7);


        setJMenuBar(menuBar);
        setVisible(true);
    }
}
