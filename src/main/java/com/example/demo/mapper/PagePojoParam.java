package com.example.demo.mapper;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-12 14:04
 */
public class PagePojoParam {
    private Integer pageNum;
    private Integer pageSize;

    public PagePojoParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
