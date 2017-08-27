package com.zong.paycommon.utils.rsa;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 宗叶青 on 2017/8/15/23:08
 */
public class CipherStream extends InputStream {

    private InputStream source;
    private byte[] key;
    int len;
    int cur;

    public CipherStream(InputStream source, byte[] keys){
        this.source = source;
        if(keys == null || keys.length < 5){
            throw new IllegalArgumentException("The keys is null or too short");
        }

        this.key = new byte[keys.length * 2];
        for(int i = 0; i < keys.length; i++){
            key[i] = (byte)(keys[i] - i);
            key[i + keys.length] = (byte)(keys[i] + i);
        }

        len = key.length;
        cur = 0;
    }
    @Override
    public int read() throws IOException {
        int i = source.read();
        if (i < 0)
            return i;

        int k = key[cur];

        int n = (cur + 1) % len;

        int nk = k + key[n];

        key[cur] = (byte) nk;

        int r = (i ^ k) & 0x00ff;

        cur = n;

        return r;
    }
}
