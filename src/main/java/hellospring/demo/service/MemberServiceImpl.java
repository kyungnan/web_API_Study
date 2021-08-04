package hellospring.demo.service;

import hellospring.demo.domain.Member;
import hellospring.demo.mapper.MemberMapper;
import hellospring.demo.util.AES128Util;
import hellospring.demo.util.RSAUtil;
import hellospring.demo.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void addMember(Member member) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
//        SHA-256 암호화
        String salt = SHA256Util.generatedSalt();
        String encodePassword = SHA256Util.bytesToHex(SHA256Util.sha256((member.getPassword()),salt));

        // AES-128 암호화
//        SecretKey secretKey = AES128Util.getSecretEncryptionKey();
//        String encodePassword = SHA256Util.bytesToHex(AES128Util.encryptPassword(member.getPassword(), secretKey));

       // RSA 암호화
//        KeyPair keyPair = RSAUtil.generateRSAKeyPair();
//        String encodePassword = RSAUtil.encryptRSA(member.getPassword(), keyPair.getPublic());

        member.setPassword(encodePassword);
        memberMapper.insertMember(member);
    }

    @Override
    public void alterMember(long id, String password, String name) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        // SHA-256 암호화
        String salt = SHA256Util.generatedSalt();
        String encodePassword = SHA256Util.bytesToHex(SHA256Util.sha256(password, salt));

        // AES-128 암호화
//        SecretKey secretKey = AES128Util.getSecretEncryptionKey();
//        String encodePassword = SHA256Util.bytesToHex(AES128Util.encryptPassword(password, secretKey));

        // RAS 암호화
//        KeyPair keyPair = RSAUtil.generateRSAKeyPair();
//        String encodePassword = RSAUtil.encryptRSA(password, keyPair.getPublic());

        memberMapper.updateMember(id, encodePassword, name);
    }
}
