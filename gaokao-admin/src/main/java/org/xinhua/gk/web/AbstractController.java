package org.xinhua.gk.web;

import org.xinhua.gk.common.Constants;
import org.xinhua.gk.common.PageObject;
import org.xinhua.gk.repository.condition.BaseCondition;

public class AbstractController {

    protected void setPageObject(BaseCondition condition) {
        condition.setIfPaged(true);

        int pageNo = condition.getPageNo() != null ? condition.getPageNo().intValue() - 1 : 0;
        int pageSize = condition.getPageSize() != null ? condition.getPageSize().intValue() : Constants.PAGE_SIZE;
        PageObject page = new PageObject(pageNo, pageSize);
        condition.setPage(page);
    }

}
