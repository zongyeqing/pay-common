package com.zong.paycommon.utils.token;

/**
 * @author 宗叶青 on 2017/8/13/15:17
 */
public class TokenProductFactory {

    public final static String KEY = "gzzyzz";
    private static TokenBaseInter base64 = new TokenToolEncrypterBase64();
    private static TokenBaseInter des64 = new TokenToolEncrypter();

    public static TokenBaseInter getInstalBase64(){return base64;}
    public static TokenBaseInter getInstalDES64(){return des64;}

}
