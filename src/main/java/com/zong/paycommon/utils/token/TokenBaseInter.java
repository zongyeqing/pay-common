package com.zong.paycommon.utils.token;

/**
 * token 基础接口
 * Created by 宗叶青 on 2017/8/13.
 */
public interface TokenBaseInter {
    String key = "gzzyzz";

    /**
     * 解密
     *
     * @param str
     * @return
     */
    String decrypt(String str);

    /**
     * 加密
     *
     * @param str
     * @return
     */
    String encrypt(String str);

    /**
     * 放入各种定制的参数，生产Tokena
     * @param paramters 参数
     * @return
     */
    String productToken(String[] paramters);

    /**
     * 放入各种定制的参数，生产Token
     * @param userNo
     * @return
     */
    String productToken(String pix, String userNo);
}
