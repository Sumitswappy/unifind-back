package com.collegesearch.collegesearch.Util;

import lombok.AllArgsConstructor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class SecurityManager {
    public String encryptPassword(String inputPassword){
        StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
        return encryptor.encryptPassword(inputPassword);
    }

    public Boolean checkPassword(String inputPassword,String encryptedPassword){
        StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
        return encryptor.checkPassword(inputPassword, encryptedPassword);
    }
}
