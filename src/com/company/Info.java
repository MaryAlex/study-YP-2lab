package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;


public class Info {
    public static final String HASH_ALGORITHM = "SHA-256";
    public static final String ASYMMETRIC_ALGORITHM = "RSA";
    public String textField;
    public String hashText;
    public String encryptedByAsymmetric;
    PrivateKey privateKey;
    PublicKey publicKey;


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
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

            Cipher cipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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

    private void textAsymmetricKeyDecrypt() {
        try {
            Cipher dipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);

            dipher.init(Cipher.DECRYPT_MODE, privateKey);
            System.out.println(new String(dipher.doFinal(hexStringToByteArray(encryptedByAsymmetric))));
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
}
