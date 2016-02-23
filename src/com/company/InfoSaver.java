package com.company;

import java.io.*;

public class InfoSaver {
    public static void saveObject(String path, Info obj, SaveWindow saveWindow) {
        Info info = new Info(obj);
        if (!saveWindow.normalTextCheckBox.isSelected()) {
            info.setTextField(" ");
        }
        if (!saveWindow.privateAsymmetricKeyCheckBox.isSelected()) {
            info.privateAsymmetricKey = null;
        }
        if (!saveWindow.encryptedByAsymmetricCheckBox.isSelected()) {
            info.encryptedByAsymmetric = " ";
        }
        if (!saveWindow.encryptedBySymmetricCheckBox.isSelected()) {
            info.encryptedBySymmetric = " ";
        }
        if (!saveWindow.publicSymmetricKeyCheckBox.isSelected()) {
            info.publicSymmetricKey = null;
        }
        if (!saveWindow.publicAsymmetricKeyCheckBox.isSelected()) {
            info.publicAsymmetricKey = null;
        }
        if (!saveWindow.hashAlgorithmCheckBox.isSelected()) {
            info.setHashAlgorithm(" ");
        }
        if (!saveWindow.asymmetricAlgorithmCheckBox.isSelected()) {
            info.setAsymmetricAlgorithm(" ");
        }
        if (!saveWindow.symmetricAlgorithmCheckBox.isSelected()) {
            info.setSymmetricAlgorithm(" ");
        }
        if (!saveWindow.hashTextCheckBox.isSelected()) {
            info.hashText = " ";
        }
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(info);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Info loadObject(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fis);
            Info in = (Info) oin.readObject();
            return in;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
