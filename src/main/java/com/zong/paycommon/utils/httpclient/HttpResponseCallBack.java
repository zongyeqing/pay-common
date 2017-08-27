package com.zong.paycommon.utils.httpclient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 宗叶青 on 2017/8/13.
 */
public interface HttpResponseCallBack {

    void processResponse(InputStream responseBody) throws IOException;
}
