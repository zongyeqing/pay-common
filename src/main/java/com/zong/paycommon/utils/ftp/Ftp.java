package com.zong.paycommon.utils.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * ftp 工具类
 * Created by 宗叶青 on 2017/8/13.
 */
public class Ftp {
    private FTPClient ftpClient = new FTPClient();
    private static final Logger LOGGER = Logger.getLogger(Ftp.class);

    public Ftp(){
        ftpClient.setConnectTimeout(20000);
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
    }

    /**
     * 得到当前ftp目录下的文件列表
     * @param tempDir
     * @return
     */
    public FTPFile[] listFiles(String tempDir){
        FTPFile[] ff = null;
        try{
            ff = ftpClient.listFiles();
        }catch (IOException e){
            return null;
        }
        return ff;
    }

    public boolean connect(String hostname, int port, String username, String password) throws IOException {
        ftpClient.connect(hostname,port);
        LOGGER.info("FTP 远程连接成功");
        if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
            if(ftpClient.login(username,password)){
                LOGGER.info("FTP 远程登录成功");
                return true;
            }
        }
        LOGGER.info("FTP 远程连接成功");
        return false;
    }

    public boolean download(String remote, OutputStream out){
        LOGGER.info("FTP 远程连接，文件开始下载");
        ftpClient.enterLocalPassiveMode();
        boolean result = false;
        try{
            result = ftpClient.retrieveFile(remote,out);
            out.close();
            disconnect();
        }catch (IOException e){
            result = false;
        }

        return result;
    }

    public void disconnect() throws IOException {
        if(ftpClient.isConnected()){
            ftpClient.disconnect();
        }
    }
}
