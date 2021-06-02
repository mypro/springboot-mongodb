package org.xinhua.gk.repository.custom.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.xinhua.gk.domain.entity.TActivities;
import org.xinhua.gk.repository.condition.ActivitiesCondition;
import org.xinhua.gk.repository.custom.ActivitiesCustomRepo;
import org.xinhua.gk.util.Lang;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivitiesCustomRepoImpl extends BaseRepoImpl<TActivities> implements ActivitiesCustomRepo {

    @Override
    public List<Object> distinctByField(ActivitiesCondition condition, String field) {
        Query query = new Query();
        generateQuery(query, condition);
        return super.distinct(query, field);
    }

    @Override
    public Page<TActivities>
    getPageByCondition(ActivitiesCondition condition) {
        // 设定返回字段
        Query query = super.genFields(condition);
        // 生成列表查询条件
        generateQuery(query, condition);
        // 返回分页查询结果
        return super.pageFind(condition, query);
    }

    @Override
    public List<TActivities> getListByCondition(ActivitiesCondition condition) {
        Query query = super.genFields(condition);
        // 生成列表查询条件
        generateQuery(query, condition);
        super.addSortFields(query, condition);
        List<TActivities> resultList = super.find(query);
        return resultList;
    }

    /**
     * 拼接查询条件
     *
     * @param query
     * @param condition
     */
    @SuppressWarnings("static-access")
    private void generateQuery(Query query, ActivitiesCondition condition) {
        Criteria criteria = new Criteria();
        List<Criteria> andCriteriaList = new ArrayList<>();
        List<Criteria> orCriteriaList = new ArrayList<>();
        if (condition != null) {

            if (Lang.isNotEmpty(condition.getStatus())) {
                query.addCriteria(criteria.where("status").is(condition.getStatus()));
            }

            if (Lang.isNotEmpty(condition.getCatId())) {
                query.addCriteria(criteria.where("catId").is(condition.getCatId()));
            }

            if (Lang.isNotEmpty(condition.getName())) {
                query.addCriteria(criteria.where("title").regex(condition.getName()));
            }


            if (Lang.isNotEmpty(condition.getBeginPublishDate())) {
                query.addCriteria(criteria.where("actTime.startDate").gte(condition.getBeginPublishDate()));
            }
            if (Lang.isNotEmpty(condition.getEndPublishDate())) {
                query.addCriteria(criteria.where("actTime.endDate").lte(condition.getEndPublishDate()));
            }

            if (Lang.isNotEmpty(condition.getManagerUserId())) {
                orCriteriaList.add(criteria.where("managers").elemMatch(Criteria.where("userId").in(condition.getManagerUserId())));
            }

            if (Lang.isNotEmpty(condition.getPublishDeptId())) {
                query.addCriteria(criteria.where("createdUser.deptId").is(condition.getPublishDeptId()));
            }

            if (Lang.isNotEmpty(condition.getLiveEnabled())) {
                query.addCriteria(criteria.where("liveEnabled").is(condition.getLiveEnabled()));
            }

            if (Lang.isNotEmpty(condition.getApplyEnabled())) {
                query.addCriteria(criteria.where("applyEnabled").is(condition.getApplyEnabled()));
            }
            if (Lang.isNotEmpty(condition.getNoteEnabled())) {
                query.addCriteria(criteria.where("noteEnabled").is(condition.getNoteEnabled()));
            }
            if (Lang.isNotEmpty(condition.getProposalEnabled())) {
                query.addCriteria(criteria.where("proposalEnabled").is(condition.getProposalEnabled()));
            }
            if (Lang.isNotEmpty(condition.getSceneEnabled())) {
                query.addCriteria(criteria.where("sceneEnabled").is(condition.getSceneEnabled()));
            }

            if (Lang.isNotEmpty(condition.getTag())) {
                query.addCriteria(criteria.where("tags").is(condition.getTag()));
            }


//            if (StringUtils.isNotEmpty(condition.getReceiveCensorUserId())) {
//                orCriteriaList.add(criteria.where("receiveCensorUser").elemMatch(Criteria.where("userId").in(condition.getReceiveCensorUserId())));
//            }
//
//            if (CollectionUtils.isNotEmpty(condition.getTypes())) {
//                orCriteriaList.add(criteria.where("type").in(condition.getTypes()));
//            }


            //  添加or条件
            if (!CollectionUtils.isEmpty(orCriteriaList)) {
                Criteria orCriteria = new Criteria();
                orCriteria.orOperator(orCriteriaList.toArray(new Criteria[orCriteriaList.size()]));
                andCriteriaList.add(orCriteria);
            }

            // 添加and条件
            if (!CollectionUtils.isEmpty(andCriteriaList)) {
                Criteria andCriteria = new Criteria();
                query.addCriteria(andCriteria.andOperator(andCriteriaList.toArray(new Criteria[andCriteriaList.size()])));
            }
        }
    }

}
