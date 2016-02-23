package com.company;

import javax.crypto.*;
import javax.swing.*;
import java.io.Serializable;
import java.security.*;


public class Info implements Serializable {
    public String HASH_ALGORITHM = "SHA-256";
    public String ASYMMETRIC_ALGORITHM = "RSA";
    public String SYMMETRIC_ALGORITHM = "DES";
    public String method = "";
    private String textField;
    public String hashText;
    public String encryptedByAsymmetric;
    public String encryptedBySymmetric;
    PrivateKey privateAsymmetricKey;
    PublicKey publicAsymmetricKey;
    Key publicSymmetricKey;

    public Info() {
    }

    public Info(Info obj) {
        this.method = obj.method;
        this.textField = obj.textField;
        this.hashText = obj.hashText;
        this.encryptedByAsymmetric = obj.encryptedByAsymmetric;
        this.encryptedBySymmetric = obj.encryptedBySymmetric;
        this.privateAsymmetricKey = obj.privateAsymmetricKey;
        this.publicAsymmetricKey = obj.publicAsymmetricKey;
        this.publicSymmetricKey = obj.publicSymmetricKey;
    }

    public String getTextField() {
        return textField;
    }

    public void printInTextArea(JTextArea out) {
        out.append("Input text: " + textField + "\n");
        out.append("Hash algorithm: " + HASH_ALGORITHM + "\n");
        out.append("Hash text: " + hashText + "\n");
        out.append("Asymmetric algorithm: " + ASYMMETRIC_ALGORITHM + "\n");
        out.append("Asymmetric text: " + encryptedByAsymmetric + "\n");
        out.append("Symmetric algorithm: " + SYMMETRIC_ALGORITHM + "\n");
        out.append("Symmetric text: " + encryptedBySymmetric + "\n");
    }

    private void eraseDate() {
        this.textField = null;
        this.hashText = null;
        this.encryptedByAsymmetric = null;
        this.privateAsymmetricKey = null;
        this.publicAsymmetricKey = null;
        this.encryptedBySymmetric = null;
        this.publicSymmetricKey = null;

    }

    public void setTextField(String textField) {
        if (this.textField != null && !this.textField.equals(textField)) {
            eraseDate();
        }
        this.textField = textField;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        HASH_ALGORITHM = hashAlgorithm;
    }

    public void setAsymmetricAlgorithm(String asymmetricAlgorithm) {
        ASYMMETRIC_ALGORITHM = asymmetricAlgorithm;
    }

    public void setSymmetricAlgorithm(String symmetricAlgorithm) {
        SYMMETRIC_ALGORITHM = symmetricAlgorithm;
    }

    public void textToHash() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.reset();
            messageDigest.update(textField.getBytes());

            byte result[] = messageDigest.digest();
            hashText = getHexadecimalValue(result);
            System.out.println(hashText);
        } catch (NoSuchAlgorithmException e1) {
            e1.getMessage();
        }
    }

    private String getHexadecimalValue(byte[] result) {
        StringBuffer buf = new StringBuffer();
        for (byte bytes : result) {
            buf.append(String.format("%02x", bytes & 0xff));
        }
        return buf.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    public void textAsymmetricKeyEncrypt() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ASYMMETRIC_ALGORITHM);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateAsymmetricKey = keyPair.getPrivate();
            publicAsymmetricKey = keyPair.getPublic();

            Cipher cipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, publicAsymmetricKey);
            encryptedByAsymmetric = getHexadecimalValue(cipher.doFinal(textField.getBytes()));
            System.out.println("Encrypted Data: " + encryptedByAsymmetric);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public String textAsymmetricKeyDecrypt() throws Exception{
            Cipher dipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);

            dipher.init(Cipher.DECRYPT_MODE, privateAsymmetricKey);
            return new String(dipher.doFinal(hexStringToByteArray(encryptedByAsymmetric)));
    }

    public void textSymmetricKeyEncrypt() {
        try {
            publicSymmetricKey = KeyGenerator.getInstance(SYMMETRIC_ALGORITHM).generateKey();
            Cipher cipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
            byte[] encryptionBytes = null;
            System.out.println("Entered: " + textField);
            cipher.init(Cipher.ENCRYPT_MODE, publicSymmetricKey);
            byte[] inputBytes = textField.getBytes();
            encryptionBytes = cipher.doFinal(inputBytes);
            encryptedBySymmetric = getHexadecimalValue(encryptionBytes);
            System.out.println(encryptedBySymmetric);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public String textSymmetricKeyDecrypt() throws Exception{
            Cipher cipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicSymmetricKey);
            byte[] recoveredBytes = cipher.doFinal(hexStringToByteArray(encryptedBySymmetric));
            String recovered = new String(recoveredBytes);
            return  recovered;
    }
}
