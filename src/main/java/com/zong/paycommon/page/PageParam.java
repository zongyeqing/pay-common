package com.zong.paycommon.page;

import java.io.Serializable;

/**
 * @author 宗叶青 on 2017/8/20/21:32
 */
public class PageParam  implements Serializable{

    private static final long serialVersionUID = 8648445583869769286L;
    private int pageNum;
    private int numPerPage;

    public PageParam(int pageNum, int numPerPage){
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    /** 当前页数 */
    public int getPageNum() {
        return pageNum;
    }

    /** 当前页数 */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /** 每页记录数 */
    public int getNumPerPage() {
        return numPerPage;
    }

    /** 每页记录数 */
    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
}
