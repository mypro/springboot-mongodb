package org.xinhua.gk.repository.custom;

import org.springframework.data.domain.Page;
import org.xinhua.gk.domain.entity.TActivities;
import org.xinhua.gk.repository.condition.ActivitiesCondition;

import java.util.List;

public interface ActivitiesCustomRepo extends BaseRepo<TActivities> {

    Page<TActivities> getPageByCondition(ActivitiesCondition condition);

    List<TActivities> getListByCondition(ActivitiesCondition condition);

    List<Object> distinctByField(ActivitiesCondition condition, String field);
}
