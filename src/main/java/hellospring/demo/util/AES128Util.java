package hellospring.demo.util;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES128Util {
    /**
     * 암호화/복호화하는 key 생성하는 메서드
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey getSecretEncryptionKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");   //KeyGeneratot-> 대칭키 생성하는 class (AES, DES, DESede, HmacSHA1, HmacSHA256 키 생성 가능)
        generator.init(128);
        SecretKey secretKey = generator.generateKey();

        return secretKey;
    }

    /**
     * 평문 -> 암호문 (AES 알고리즘)
     * @param password
     * @param secretKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encryptPassword(String password, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //Cipher class: AES 암호화/복호화 기능을 제공한다. CBC, PKCS5Padding 모드 선택 기능도 제공함
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey); //Cipher.ENCRYPT_MODE: aesCipher 객체의 파라미터로 넘겨주며 암호화 모드로 초기설정함.
        byte[] byteCipherPassword = aesCipher.doFinal(password.getBytes());     //암호문 생성

        return byteCipherPassword;
    }

    /**
     * 암호문 -> 평문 (AES 알고리즘)
     * @param byteCipherPassword
     * @param secretKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decryptPassword(byte[] byteCipherPassword, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytePlainPassword = aesCipher.doFinal(byteCipherPassword);

        return new String(bytePlainPassword);
    }
}
