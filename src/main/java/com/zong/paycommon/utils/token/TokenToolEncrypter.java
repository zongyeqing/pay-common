package com.zong.paycommon.utils.token;

import com.zong.paycommon.exceptions.BizException;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;

/**
 * 一个Token加密与解密工具 ，不限于Token范围  只能解本JDK加密串<br/>
 * Created by 宗叶青 on 2017/8/13.
 */
public class TokenToolEncrypter implements TokenBaseInter {

    /**BASE64 加密处理工具*/
    private static Cipher ecipher;

    /**BASE 64 解密处理工具*/
    private static Cipher dcripher;

    public TokenToolEncrypter(){
        init();
    }

    public static void init(){
        try{
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");
            dcripher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcripher.init(Cipher.DECRYPT_MODE, key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    @Override
    public   String decrypt(String str) {
        try {
            if(ecipher==null){
                init();
            }if(org.apache.commons.lang3.StringUtils.isEmpty(str)){
                throw  BizException.TOKEN_IS_ILLICIT;
            }
            str = str.replace("_", "+");
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcripher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     * @param str
     * @return
     */
    @Override
    public String encrypt(String str) {
        try{
            if(ecipher == null){
                init();
            }
            if(StringUtils.isEmpty(str)){
                throw BizException.DB_SELECTONE_IS_NULL;
            }
            byte[] utf8 = str.getBytes("UTF8");

            byte[] enc = ecipher.doFinal(utf8);

            return new sun.misc.BASE64Encoder().encode(enc).replace("+","_");
        } catch (javax.crypto.BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String productToken(String[] paramters) {
        if(paramters==null || paramters.length==0){
            return null;
        }else{
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < paramters.length; i++) {
                sb.append(paramters[i]+"-");
            }
            sb.append(key);//最后加上Key值
            return this.encrypt(sb.toString());
        }
    }

    @Override
    public String productToken(String pix, String userNo) {
        return this.encrypt(pix+"-"+userNo+"-"+System.currentTimeMillis()+"-"+key);
    }
}
