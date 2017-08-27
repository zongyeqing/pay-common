package com.zong.paycommon.utils.httpclient;

import javax.net.ssl.KeyManagerFactory;

/**
 * @author 宗叶青 on 2017/8/13/23:39
 */
public class ClientKeyStore {
    private KeyManagerFactory keyManagerFactory;

    ClientKeyStore(KeyManagerFactory keyManagerFactory){
        this.keyManagerFactory = keyManagerFactory;
    }

    KeyManagerFactory getKeyManagerFactory(){
        return keyManagerFactory;
    }
}
