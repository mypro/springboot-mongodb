package org.xinhua.gk.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by fmy on 2017/8/25.
 */
public class PageObject<T> extends PageRequest {
    /**
     *
     */
    private static final long serialVersionUID = 7238712350765538098L;

    public PageObject(int page, int size) {
        super(page, size);
    }

    public PageObject(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, direction, properties);
    }

    public PageObject(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    // 总条数
    private long total = 0;
    // 结果集
    private List<T> result;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
