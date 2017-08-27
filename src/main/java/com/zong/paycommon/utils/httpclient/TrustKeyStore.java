package com.zong.paycommon.utils.httpclient;

import javax.net.ssl.TrustManagerFactory;

/**
 * @author 宗叶青 on 2017/8/13/23:41
 */
public class TrustKeyStore {

    private TrustManagerFactory trustManagerFactory;

    TrustKeyStore(TrustManagerFactory trustManagerFactory){
        this.trustManagerFactory = trustManagerFactory;
    }

    TrustManagerFactory getTrustManagerFactory(){
        return trustManagerFactory;
    }
}
