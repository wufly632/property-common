package cn.wufly.property.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PageWrapper<T> implements Serializable {
    private static final long serialVersionUID = 327337670248278855L;
    private Long totalCount;

    private Integer pageSize;

    private Integer pageIndex;

    private Collection<T> list;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageIndex() {
        if (pageIndex < 1) {
            return 1;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex == null || pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public List<T> getList() {
        return null == list ? Collections.emptyList() : new ArrayList<>(list);
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }



    public boolean isEmpty() {
        return list == null || list.size() == 0;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }


    public static <T> PageWrapper<T> of(int pageSize, int pageIndex) {
        PageWrapper<T> response = new PageWrapper<>();
        response.setList(Collections.emptyList());
        response.setTotalCount(0L);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageWrapper<T> of(Collection<T> data, long totalCount, int pageSize, int pageIndex) {
        PageWrapper<T> response = new PageWrapper<>();
        response.setList(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}

