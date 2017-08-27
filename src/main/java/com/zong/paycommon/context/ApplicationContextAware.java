package com.zong.paycommon.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author 宗叶青 on 2017/8/16/0:24
 */
public class ApplicationContextAware implements org.springframework.context.ApplicationContextAware{

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextAware.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
