package com.zong.paycommon.utils.cache.redis;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * @author 宗叶青 on 2017/8/14/23:18
 */
public class SerializeUtils {

    private static final Logger LOGGER = Logger.getLogger(SerializeUtils.class);

    /**
     * 序列化
     * @param object 待序列化对象
     * @return
     * @throws IOException
     */
    public static byte[] serialize(Object object) throws IOException {
        if(object == null)
            return null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        }catch (Exception e){
            LOGGER.error(e);
            throw e;
        }
    }

    public static Object unSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        if(bytes == null)
            return null;
        ByteArrayInputStream bais = null;
        try{
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch (Exception e){
            LOGGER.error(e);
            throw e;
        }
    }
}
