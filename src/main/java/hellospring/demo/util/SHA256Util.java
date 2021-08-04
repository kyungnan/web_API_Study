package hellospring.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Util {

    /**
     * SHA-256으로 해싱하는 메서드
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] sha256(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // key-stretching
        for (int i=0; i<10000; i++){
            String temp = password+salt;
            md.update(temp.getBytes());
        }

        return md.digest();
    }

    /**
     * byte 값 -> Hex 값으로 변환하는 메서드
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 랜덤으로 SALT 값 가져오는 메서드
     * @return
     */
    public static String generatedSalt(){
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt); //byte[] salt에 임의의 숫자로 채워짐

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<salt.length; i++){
            //byte 값 -> Hex(16진수) 값으로 바꾸기
            sb.append(String.format("%02x",salt[i]));   //"%02x": 16진수
        }
        return sb.toString();
    }

}
