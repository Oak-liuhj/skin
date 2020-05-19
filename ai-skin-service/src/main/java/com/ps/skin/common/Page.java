package com.ps.skin.common;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author liuhj
 * @date 2020/05/19 13:00
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int PAGE_INDEX = 1;
    public static final int PAGE_SIZE = 10;
    private int pageIndex;
    private int pageSize;
    private List<T> rows;
    private long total;
    private long pageCount;
    private boolean hasNext;
    private boolean hasPrevious;

    public Page() {
        this(1, 10, 0L, new ArrayList());
    }

    public Page(int pageIndex, int pageSize, long total, List<T> rows) {
        this.pageIndex = 1;
        this.pageSize = 10;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.pageCount = total / (long) pageSize + (long) (total % (long) pageSize > 0L ? 1 : 0);
        this.hasNext = (long) pageIndex < this.pageCount;
        this.hasPrevious = pageIndex > 1;
        this.rows = rows;
    }

    public Page(List<T> rows) {
        this.pageIndex = 1;
        this.pageSize = 10;
        PageInfo<T> pageInfo = new PageInfo(rows);
        this.pageIndex = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.pageCount = this.pageSize == 0 ? 0L : this.total / (long) this.pageSize + (long) (this.total % (long) this.pageSize > 0L ? 1 : 0);
        this.hasNext = (long) this.pageIndex < this.pageCount;
        this.hasPrevious = this.pageIndex > 1;
        this.rows = rows;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public long getTotal() {
        return this.total;
    }

    public long getPageCount() {
        return this.pageCount;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public boolean isHasPrevious() {
        return this.hasPrevious;
    }
}
