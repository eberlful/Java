/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Markus Eberl
 * @version 2.0
 */
public class CryptoClass {
    
    /**
     *
     */
    public void setProvider() {
        java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * Funktion verschlüsselt eine Datei. Hier ist Sie zur Verschlüsselung der User-Datei gedacht.
     * @param originalFile Speicherpfad der orginalen Datei
     * @param encryptedFile Speicherpfad der verschlüsselten Datei
     * @param password Passwort zur Verschlüsselung
     * @throws Exception
     */
    public void encryptFile(String originalFile, String encryptedFile, String password) throws Exception {
        CipherOutputStream out;
        InputStream in;
        Cipher cipher;
        SecretKey key;
        byte[] byteBuffer;      
        cipher = Cipher.getInstance("DES");
        key = new SecretKeySpec(password.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        in = new FileInputStream(originalFile);
        out = new CipherOutputStream(new FileOutputStream(encryptedFile), cipher);
        byteBuffer = new byte[1024];
        for (int n;(n = in.read(byteBuffer)) != -1;out.write(byteBuffer, 0, n));
        in.close();
        out.close();
        //new File(originalFile).delete();
    }

    /**
     * Funktion entschlüsselt eine Datei. Hier ist Sie zur Entschlüsselung der User-Datei gedacht.
     * @param encryptedFile Speicherpfad der entschlüsselten Datei
     * @param decryptedFile Speicherpfad der verschlüsselten Datei
     * @param password Passwort zur Entschlüsselung
     * @throws Exception
     */
    public void decryptFile(String encryptedFile, String decryptedFile, String password) throws Exception {
        CipherInputStream in;
        OutputStream out;
        Cipher cipher;
        SecretKey key;
        byte[] byteBuffer;
        cipher = Cipher.getInstance("DES");
        key = new SecretKeySpec(password.getBytes(), "DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        in = new CipherInputStream(new FileInputStream(encryptedFile), cipher);
        out = new FileOutputStream(decryptedFile);
        byteBuffer = new byte[1024];
        for (int n;(n = in.read(byteBuffer)) != -1;out.write(byteBuffer, 0, n));
        in.close();
        out.close();
        //new File(encryptedFile).delete();
    }
}
