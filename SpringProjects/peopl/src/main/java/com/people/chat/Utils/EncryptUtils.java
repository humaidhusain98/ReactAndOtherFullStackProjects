package com.people.chat.Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {
    private static Cipher ecipher = null;
    private static Cipher dcipher = null;

    private static SecretKey key = null;

    public static String encrypt(String str) {
        try {
            if (ecipher == null) {
                key = KeyGenerator.getInstance("DES").generateKey();
                ecipher = Cipher.getInstance("DES");
                dcipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                dcipher.init(Cipher.DECRYPT_MODE, key);
            }
            // encode the string into a sequence of bytes using the named charset
            // storing the result into a new byte array.
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            // encode to base64
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String str) {
        try {
            if (ecipher == null) {
                key = KeyGenerator.getInstance("DES").generateKey();
                ecipher = Cipher.getInstance("DES");
                dcipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                dcipher.init(Cipher.DECRYPT_MODE, key);
            }
            byte[] dec = BASE64DecoderStream.decode(str.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);
            // create new string based on the specified charset
            return new String(utf8, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encryptWithSHA(String accessToken) {
        return DigestUtils.sha256Hex(accessToken);
    }

    public static void main(String[] args) {
        // System.out.println(decrypt(
        // "lanpuP7+mrBelmdEGLueiSPUolFQsOyaZzrweglfUlbux4frvaYab0JOMXlP94jhEdtI1rmruKuW7kW0UuLYxAlC7kuhve2F9+S87rBRii8Wp236xjI+28CGYYLWaohEEVbs2LGuCvn8uOcipA13gnPF2ZFWsicNeliqU5lgMSoVPdmkqDLnj9CfsatJrXIpSr1bQvmoboe9dqtA+K2wElOm9tBKQQAsSrIOXt5+xEN2SCECmOW62vHSW1tVIg5M"));

        System.out.println(encryptWithSHA("EAALZBKtfQAjkBAN0MTAIuYquxFgrVY7ZB1ZBA2ZC418oS7bhZCyZC8CTvUyXHZCRa6dEWj8l4BKYYcD7Yxl43fKdbugfgv6n5fmCqrLIGGsrhLouxeauO8yAVPNGvHL29sZAFnF9xQ5oxxUsg7A9qNivTJnRFavrNRwQxqnLP08ebHPk1txZBvUSTfwWW7Qf6lkyrvFndpZCkKhdZACmyb7dYb7oZAln7pSmS6gZD"));

    }
}