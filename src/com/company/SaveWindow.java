package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveWindow extends JFrame {
    public JCheckBox normalTextCheckBox;
    public JCheckBox privateAsymmetricKeyCheckBox;
    public JCheckBox encryptedBySymmetricCheckBox;
    public JCheckBox publicSymmetricKeyCheckBox;
    public JCheckBox encryptedByAsymmetricCheckBox;
    public JCheckBox publicAsymmetricKeyCheckBox;
    public JPanel panel;
    public JCheckBox hashAlgorithmCheckBox;
    public JCheckBox asymmetricAlgorithmCheckBox;
    public JCheckBox symmetricAlgorithmCheckBox;
    public JCheckBox hashTextCheckBox;
    public JButton okButton;
    private String path;

    public SaveWindow() {
        super("Hello");
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
