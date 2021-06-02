package org.xinhua.gk.repository.custom;

import org.springframework.data.domain.Page;
import org.xinhua.gk.domain.entity.TTag;
import org.xinhua.gk.repository.condition.TagCondition;

import java.util.List;

public interface TagCustomRepo {


    Page<TTag> getPageByCondition(TagCondition condition);

    List<TTag> getListByCondition(TagCondition condition);

    List<Object> distinctByField(TagCondition condition, String field);
}
