package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Info {
    public String textField;
    public String hashText;

    public void textToHash() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(textField.getBytes());

            byte result[] = messageDigest.digest();
            StringBuffer buf = new StringBuffer();
            for (byte bytes : result) {
                buf.append(String.format("%02x", bytes & 0xff));
            }
            hashText = buf.toString();
            System.out.println(hashText);
        } catch (NoSuchAlgorithmException e1) {
            e1.getMessage();
        }
    }
}
