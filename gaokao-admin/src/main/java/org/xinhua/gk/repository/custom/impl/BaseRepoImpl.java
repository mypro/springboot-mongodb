package org.xinhua.gk.repository.custom.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBRef;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.xinhua.gk.domain.attr.BaseAttr;
import org.xinhua.gk.repository.condition.BaseCondition;
import org.xinhua.gk.repository.custom.BaseRepo;
import org.xinhua.gk.util.EscapeChars;
import org.xinhua.gk.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * mongoTemplate查询用基类
 * Created by fmy on 2017/8/23.
 */
@Repository
public class BaseRepoImpl<T> implements BaseRepo<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRepoImpl.class);

    private Class<T> entryClass;

    private final String AND = "+";
    private final String OR = "|";
    private final String NOT = "-";
    private final String ESCAPE_AND = "\\+";
    private final String ESCAPE_OR = "\\|";
    private final String ESCAPE_NOT = "\\-";
    private final String NESTED_ATTR = ".";

    // 非查询条件
    private final String neStr = "^((?!%s).)*$";

    public BaseRepoImpl() {
        super();
        try {
            entryClass = ReflectUtil.getTypeParam(getClass(), 0);
        } catch (Exception e) {
            logger.error("!!!严重错误：Fail to get TypeParams for self!", e);
        }
    }

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Object> distinct(Query query, String field) {
        List<Object> list;
        try {
            list = mongoTemplate.findDistinct(query, field, entryClass, Object.class);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return list;
    }

    public List<Integer> distinctInt(Query query, String field) {
        List<Integer> list;
        try {
            list = mongoTemplate.findDistinct(query, field, entryClass, Integer.class);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return list;
    }

    public List<String> distinctStr(Query query, String field) {
        List<String> list;
        try {
            list = mongoTemplate.findDistinct(query, field, entryClass, String.class);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return list;
    }

    @Override
    public boolean exists(Query query) throws RuntimeException {
        boolean exists;
        try {
            exists = mongoTemplate.exists(query, entryClass);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return exists;
    }

    @Override
    public long count(Query query) throws RuntimeException {
        long count;
        try {
            count = mongoTemplate.count(query, entryClass);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return count;
    }

    @Override
    public List<T> find(Query query) throws RuntimeException {
        List<T> list;
        try {
            long startTime = System.currentTimeMillis();
            if (logger.isDebugEnabled()) {
                logger.info("列表查询条件：{}", JSON.toJSONString(query));
            }
            list = mongoTemplate.find(query, entryClass);
            long endTime = System.currentTimeMillis();
            if (logger.isDebugEnabled()) {
                logger.info("列表查询结果，记录数：{}，耗时：{}，结果集：{}",
                        list == null ? 0 : list.size(),
                        (endTime - startTime),
                        JSON.toJSONString(list));
            }
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return list;
    }

    @Override
    public List<T> find(Query query, List<Sort> sorts) throws RuntimeException {
        query = withSorts(query, sorts);
        return find(query);
    }

    @Override
    public T findOne(Query query) throws RuntimeException {
        T object;
        try {
            object = mongoTemplate.findOne(query, entryClass);
        } catch (Exception e) {
            logger.error("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new RuntimeException("查询失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
        return object;
    }

    @Override
    public T findOneById(String id) {
        return findOne(Query.query(Criteria.where("id").is(id)));
    }

    @Override
    public void save(T object) throws Exception {
//        T tObject = initTimestamp(object);
        try {
            mongoTemplate.save(object);
        } catch (Exception e) {
            logger.error("保存失败, 数据库异常!  object:" + JSON.toJSONString(object), e);
            throw new Exception("保存失败, 数据库异常!  object:" + JSON.toJSONString(object), e);
        }
    }

    @Override
    public void remove(Query query) throws Exception {
        try {
            mongoTemplate.remove(query, entryClass);
        } catch (Exception e) {
            logger.error("删除失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
            throw new Exception("删除失败, 数据库异常!  query:" + JSON.toJSONString(query), e);
        }
    }

    @Override
    public void removeOne(T object) throws Exception {
        try {
            mongoTemplate.remove(object);
        } catch (Exception e) {
            logger.error("删除失败, 数据库异常!  object:" + JSON.toJSONString(object), e);
            throw new Exception("删除失败, 数据库异常!  object:" + JSON.toJSONString(object), e);
        }
    }

    @Override
    public void removeById(String id) throws Exception {

        try {
            mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), entryClass);
        } catch (Exception e) {
            logger.error("删除失败, 数据库异常!  id:" + id, e);
            throw new Exception("删除失败, 数据库异常!  id:" + id, e);
        }
    }

    @Override
    public void removeByIds(List<String> ids) throws Exception {
        try {
            mongoTemplate.remove(Query.query(Criteria.where("id").in(ids)), entryClass);
        } catch (Exception e) {
            logger.error("删除失败, 数据库异常!  ids:" + JSON.toJSONString(ids), e);
            throw new Exception("删除失败, 数据库异常!  ids:" + JSON.toJSONString(ids), e);
        }
    }

    @Override
    public void update(Query query, Update update) throws Exception {
        try {
            mongoTemplate.updateMulti(query, update, entryClass);
        } catch (Exception e) {
            logger.error("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
            throw new Exception("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
        }
    }

    @Override
    public void update(Query query, List<Sort> sorts, Update update) throws Exception {
        query = withSorts(query, sorts);
        update(query, update);
    }


    @Override
    public void updateMulti(Query query, Update update) throws Exception {
        try {
            mongoTemplate.updateMulti(query, update, entryClass);
        } catch (Exception e) {
            logger.error("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
            throw new Exception("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
        }
    }

    @Override
    public T findAndModify(Query query, Update update) throws Exception {
        try {
            FindAndModifyOptions options = new FindAndModifyOptions();
            options.returnNew(true);
            return mongoTemplate.findAndModify(query, update, options, entryClass);
        } catch (Exception e) {
            logger.error("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
            throw new Exception("更新失败, 数据库异常!  query:" + JSON.toJSONString(query) + ";  up:" + JSON.toJSONString(update), e);
        }
    }

    @Override
    public T unset(Map<String, Object> queryFields, List<String> fields) throws Exception {
        if (queryFields == null || queryFields.size() == 0) {
            logger.error("unset操作，查询字段为空。");
            throw new Exception("查询字段为空。");
        }
        if (fields == null || fields.size() == 0) {
            logger.error("unset操作，更新字段为空。");
            throw new Exception("更新字段为空。");
        }

        Query query = new Query();
        Update update = new Update();
        for (String key : queryFields.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryFields.get(key)));
        }
        for (String field : fields) {
            update.unset(field);
        }
        return findAndModify(query, update);
    }

    /**
     * 分页查找
     *
     * @param condition
     * @param query
     * @return
     */
    protected Page pageFind(BaseCondition condition, Query query) {
        Page pager = null;
        // 分页条件
        if (condition.getPage() != null) {
            Pageable pageable = PageRequest.of(condition.getPage().getPageNumber(), condition.getPage().getPageSize(),condition.getSort());
            long total = count(query);
            addSortFields(query, condition);
            query.with(condition.getPage());
            long startTime = System.currentTimeMillis();
            pager = new PageImpl<>(find(query), pageable, total);
            long endTime = System.currentTimeMillis();

//            if(logger.isDebugEnabled()) {
//            	logger.info("分页查询结果，耗时：{}，结果集：{}",
//            			(endTime - startTime),
//            			JSON.toJSONString(page));
//            }

            return pager;
        }
        return pager;
    }

    /**
     * 添加sort字段
     *
     * @param query
     * @param condition
     */
    protected void addSortFields(Query query, BaseCondition condition) {
        if (condition.getOrders() != null && condition.getOrders().size() > 0) {
            Sort sort = Sort.by(condition.getOrders());
            query.with(sort);
        }
    }

    /**
     * 构建返回字段
     *
     * @param condition
     * @return
     */
    protected Query genFields(BaseCondition condition) {
        Query query = new Query();
        // 设置返回字段
        if (CollectionUtils.isNotEmpty(condition.getFields())) {
            for (String field : condition.getFields()) {
                query.fields().include(field);
            }
        }
        return query;
    }

    @Override
    public Class<T> getEntryClass() {
        return entryClass;
    }

    @Override
    public void setEntryClass(Class<T> entryClass) {
        this.entryClass = entryClass;
    }

    protected static Query withSorts(Query query, List<Sort> sorts) {
        if (query == null) {
            query = new Query();
        }
        if (sorts != null && sorts.size() > 0) {
            Sort sort = sorts.get(0);
            for (int i = 1; i < sorts.size(); i++) {
                sort.and(sorts.get(i));
            }
            query.with(sort);
        }
        return query;
    }

    /**
     * 生成update对象
     *
     * @param entryClass
     * @return
     * @throws Exception
     */
    protected Update generateUpdate(Object entryClass, Update update, Boolean addOrDel) throws Exception {
        return generateUpdate(entryClass, null, update, addOrDel);
    }

    /**
     * 生成update对象
     *
     * @param entryClass
     * @param parentFiledName
     * @return
     * @throws Exception
     */
    protected Update generateUpdate(Object entryClass, String parentFiledName, Update update, Boolean addOrDel) throws Exception {
        if (entryClass != null) {
//            Class clazz = entryClass.getClass();
//            Field fields[] = clazz.getDeclaredFields();

            // 获取所有字段（包括父类）
            Field fields[] = getAllFields(entryClass);
            Field field;
            Object fieldValue;

            try {
                for (int i = 0; i < fields.length; i++) {
                    field = fields[i];
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    fieldValue = field.get(entryClass);
                    // 字段为空时，不进行更新
                    if (fieldValue == null) {
                        continue;
                    }
                    // 成员为自定义对象时，加上属性名
                    if (StringUtils.isNotEmpty(parentFiledName)) {
                        fieldName = parentFiledName + "." + fieldName;
                        // 序列化字段，不进行更新
                        if (fieldName.contains("serialVersionUID")) {
                            continue;
                        }
                    }
                    // 解析自定义对象中的属性
                    if (containsBaseAttr(field.getType().getAnnotations())) {
                        generateUpdate(field.get(entryClass), fieldName, update, addOrDel);
                        continue;
                    }
//                    if(field.getType().getSuperclass() != null &&
//                            field.getType().getSuperclass().equals(BaseAttr.class)) {
//                        generateUpdate(field.get(entryClass), fieldName);
//                        continue;
//                    }
                    // 设置update对象
                    if (field.getType().equals(List.class)) {
                        // 显式指定添加或删除时
                        if (addOrDel != null) {
                            if (addOrDel) { // 明确指定追加时，往既有记录中追加
                                // 包含document注解
                                if (containsDocAnnotation(field)) {
                                    buildDBRefPush(update, fieldName, fieldValue);
                                } else {
                                    Update.PushOperatorBuilder pushOperatorBuilder = update.push(fieldName);
                                    pushOperatorBuilder.each(field.get(entryClass));
                                }
                            } else { // 并且明确指定删除时，从list中删除记录
                                if (containsDocAnnotation(field)) {
                                    buildDBRefPull(update, fieldName, fieldValue);
                                } else {
                                    Object[] objs = new Object[((List) field.get(entryClass)).size()];
                                    for (int j = 0; j < ((List) field.get(entryClass)).size(); j++) {
                                        objs[j] = ((List) field.get(entryClass)).get(j);
                                    }
                                    if (objs.length > 0)
                                        update.pullAll(fieldName, objs);
                                }
                            }
                        } else {
                            // 注解类型包含Document时
                            if (containsDocAnnotation(field)) {
                                buildDBRefPush(update, fieldName, fieldValue);
                            } else { // 不是document类型时，覆盖原值
                                update.set(fieldName, fieldValue);
                            }
                        }

//                        // 判断父类是否是BaseAttr
//                        Object superClass = this.getClass().getClassLoader().loadClass(type.getActualTypeArguments()[0].getTypeName()).getSuperclass();
//
//                        // 继承自BaseAttr的，在更新时，均重新设置
//                        if(superClass != null &&
//                                superClass.equals(BaseAttr.class)) {
//                            update.set(fieldName, fieldValue);
//                        } else { // 不继承BaseAttr的，在更新时，往既有记录中追加
//                            for (Object obj : (List) field.get(entryClass)) {
//                                update.push(fieldName, obj);
//                            }
//                        }
                    } else {
                        update.set(fieldName, fieldValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return update;
    }

    /**
     * 支持继承，获取子类及父类的字段
     *
     * @param entryClass
     * @return
     * @throws Exception
     */
    private Field[] getAllFields(Object entryClass) throws Exception {
        Class clazz = entryClass.getClass();
        // 子类字段
        Field[] tableFields = clazz.getDeclaredFields();
        // 父类字段
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz.equals(Object.class)) {
            return tableFields;
        } else {
            Field[] tableSuperFields = superClazz.getDeclaredFields();
            Field[] superFields = new Field[tableFields.length + tableSuperFields.length];
            System.arraycopy(tableFields, 0, superFields, 0, tableFields.length);
            System.arraycopy(tableSuperFields, 0, superFields, tableFields.length, tableSuperFields.length);
            Field[] allFields = getSuperClassFields(superFields, superClazz);
            return allFields;
        }
    }

    /**
     * 获取父类的所有字段
     */
    private static Field[] getSuperClassFields(Field[] tableFields, Class<?> clazz) {
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz.equals(Object.class)) {
            return tableFields;
        }
        Field[] tableSuperFields = superClazz.getDeclaredFields();

        Field[] c = new Field[tableFields.length + tableSuperFields.length];
        System.arraycopy(tableFields, 0, c, 0, tableFields.length);
        System.arraycopy(tableSuperFields, 0, c, tableFields.length, tableSuperFields.length);
        getSuperClassFields(c, superClazz);
        return c;
    }

    private Boolean containsBaseAttr(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(BaseAttr.class)) {
                return true;
            }
        }
        return false;
    }

    private Boolean containsDocAnnotation(Field field) throws Exception {

        ParameterizedType type = (ParameterizedType) field.getGenericType();
        // 获取所有注解
        Annotation[] annotations = this.getClass().getClassLoader().loadClass(type.getActualTypeArguments()[0].getTypeName()).getDeclaredAnnotations();
        if (annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Document.class)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将页面输入的与或非查询条件转为mongodb可解析的查询操作
     *
     * @param orKeys
     * @param andKeys
     * @param notKeys
     * @param keywords 搜索关键字
     */
    protected void genSearchData(List<String> orKeys, List<String> andKeys, List<String> notKeys, List<String> keywords) {
        keywords.forEach(item -> extractKeywords(orKeys, andKeys, notKeys, item, null));
    }

    /**
     * 提取与或非查询条件
     *
     * @param orKeys
     * @param andKeys
     * @param notKeys
     * @param splitStrs
     * @param opt
     */
    protected void extractKeywords(List<String> orKeys, List<String> andKeys, List<String> notKeys, String splitStrs, String opt) {
        String[] orArr = splitStrs.split(ESCAPE_OR);
        String[] andArr = splitStrs.split(ESCAPE_AND);
        String[] notArr = splitStrs.split(NOT);
        if (opt == null) {
            // 不是组合条件时，默认作为【且】
            if (orArr.length == 1 && andArr.length == 1 && notArr.length == 1) {
                if (StringUtils.isNotBlank(splitStrs))
                    andKeys.add(splitStrs.replaceAll(ESCAPE_AND, "").replaceAll(ESCAPE_OR, "").replaceAll(NOT, "").trim());
                return;
            }
            // 只有一个非条件时，特殊处理
            if (notArr.length == 2 && orArr.length == 0 && andArr.length == 0) {
                if (StringUtils.isNotBlank(notArr[0]))
                    andKeys.add(notArr[0].trim());
                if (StringUtils.isNotBlank(notArr[1]))
                    notKeys.add(notArr[1].trim());
                return;
            }
        }
        // 抽取【或】查询条件
        if (orArr.length > 1) {
            for (int i = 0; i < orArr.length; i++) {
                if (StringUtils.isBlank(orArr[i]))
                    continue;
                if (!orArr[i].contains(AND) && !orArr[i].contains(NOT)) {
                    orKeys.add(orArr[i].trim());
                } else {
                    extractKeywords(orKeys, andKeys, notKeys, orArr[i], OR);
                }
            }
        } else {
            // 抽取【与】查询条件
            if (andArr.length > 1) {
                for (int i = 0; i < andArr.length; i++) {
                    if (StringUtils.isBlank(andArr[i]))
                        continue;
                    if (i == 0 && opt != null) {
                        if (opt.equals(OR))
                            orKeys.add(andArr[i].trim());
                        if (opt.equals(NOT))
                            notKeys.add(andArr[i].trim());
                    } else {
                        if (!andArr[i].contains(OR) && !andArr[i].contains(NOT)) {
                            andKeys.add(andArr[i].trim());
                        } else {
                            extractKeywords(orKeys, andKeys, notKeys, andArr[i], AND);
                        }
                    }
                }
            } else {
                // 抽取【非】查询条件
                if (notArr.length > 0) {
                    for (int i = 0; i < notArr.length; i++) {
                        if (StringUtils.isBlank(notArr[i]))
                            continue;
                        if (i == 0 && opt != null) {
                            if (opt.equals(OR))
                                orKeys.add(notArr[i].trim());
                            if (opt.equals(AND))
                                andKeys.add(notArr[i].trim());
                        } else {
                            if (!notArr[i].contains(AND) && !notArr[i].contains(OR)) {
                                notKeys.add(notArr[i].trim());
                            } else {
                                extractKeywords(orKeys, andKeys, notKeys, notArr[i], NOT);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据不同稿库分别设定全文检索条件
     *
     * @param andCriteriaList
     * @param condition
     * @param searchStrs
     */
    protected void fullTextSearchCriteria(List<Criteria> andCriteriaList,
                                          BaseCondition condition,
                                          List<String> searchStrs) {
        // 未定稿库
//        if(condition instanceof PendingDocCondition) {
//            addFullTextSearchCriteria(searchStrs, "docContent.textContent", andCriteriaList);
//        }
//        if(condition instanceof ProcessDocCondition || condition instanceof ProductDocCondition) {
//            // 成品稿库
//            addFullTextSearchCriteria(searchStrs, "docContent.issueContent", andCriteriaList);
//        }
    }

    /**
     * 生成全文检索条件
     *
     * @param searchStrs      检索条件数据
     * @param contentKey      检索字段
     * @param andCriteriaList 条件列表
     */
    protected void addFullTextSearchCriteria(List<String> searchStrs,
                                             String contentKey,
                                             List<Criteria> andCriteriaList) {
        searchStrs.stream().forEach(item -> {
            if (item.contains("|")) {
                List<String> orStrs = Arrays.asList(item.split(ESCAPE_OR));
                List<Criteria> criteriaList = new ArrayList<>();
                // 拼接或查询条件
                orStrs.forEach(str -> {
                    // 过滤掉空字符串
                    if (StringUtils.isNotBlank(str)) {
                        Criteria fullTextCriteria = new Criteria();
                        if (str.startsWith(NOT)) {
                            String regexStr = String.format(neStr, EscapeChars.forRegex(str.substring(1)));
                            criteriaList.add(fullTextCriteria.andOperator(Criteria.where(contentKey).regex(regexStr, "si"),
                                    Criteria.where("headline").regex(regexStr, "si")));
//                            Criteria criteria = withElemCriteria(contentKey,regexStr);
//                            criteriaList.add(fullTextCriteria.andOperator(criteria,
//                                    Criteria.where("headline").regex(regexStr, "si")));
                        } else {
                            criteriaList.add(fullTextCriteria.orOperator(Criteria.where(contentKey).regex(str, "si"),
                                    Criteria.where("headline").regex(EscapeChars.forRegex(str), "si")));
//                            Criteria criteria = withElemCriteria(contentKey,str);
//                            criteriaList.add(fullTextCriteria.orOperator(criteria,
//                                    Criteria.where("headline").regex(EscapeChars.forRegex(str), "si")));
                        }
                    }
                });
                if (!CollectionUtils.isEmpty(criteriaList)) {
                    Criteria orCriteria = new Criteria();
                    orCriteria.orOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
                    andCriteriaList.add(orCriteria);
                }
            } else {
                if (item.startsWith(NOT)) {
                    String regexStr = String.format(neStr, EscapeChars.forRegex(item.substring(1)));
                    Criteria fullTextCriteria = new Criteria();
                    andCriteriaList.add(fullTextCriteria.andOperator(Criteria.where(contentKey).regex(regexStr, "si"),
                            Criteria.where("headline").regex(regexStr, "si")));
//                    Criteria criteria = withElemCriteria(contentKey, regexStr);
//                    andCriteriaList.add(fullTextCriteria.andOperator(criteria,
//                            Criteria.where("headline").regex(regexStr, "si")));
                } else {
                    Criteria fullTextCriteria = new Criteria();
                    andCriteriaList.add(fullTextCriteria.orOperator(Criteria.where(contentKey).regex(EscapeChars.forRegex(item), "si"),
                            Criteria.where("headline").regex(EscapeChars.forRegex(item), "si")));
//                    Criteria criteria = withElemCriteria(contentKey, EscapeChars.forRegex(item));
//                    andCriteriaList.add(fullTextCriteria.orOperator(criteria,
//                            Criteria.where("headline").regex(EscapeChars.forRegex(item), "si")));
                }
            }
        });
    }

    /**
     * 提取正则匹配检索条件公共方法
     *
     * @param key             检索字段名
     * @param splitStrs
     * @param andCriteriaList
     */
    protected void addTextSearchCriteria(String key, List<String> splitStrs,
                                         List<Criteria> andCriteriaList, boolean isArrayField) {
        splitStrs.stream().forEach(item -> {
            if (item.contains(OR)) {
                List<String> orStrs = Arrays.asList(item.split(ESCAPE_OR));
                // 拼接或查询条件
                List<Criteria> criteriaList = new ArrayList<>();
                boolean hasNot = false;
                for (String str : orStrs) {
                    // 过滤掉空字符串
                    if (StringUtils.isNotBlank(str)) {
                        if (str.startsWith(NOT)) {
                            str = String.format(neStr, EscapeChars.forRegex(str.substring(1)));
                            hasNot = true;
                        } else {
                            str = EscapeChars.forRegex(str);
                        }
                        criteriaList.add(Criteria.where(key).regex(str, "si"));
//                        Criteria criteria = withElemCriteria(key, str);
//                        criteriaList.add(criteria);
                    }
                }
                // 存储结构为数组时，非查询加上数组为空查询
                if (hasNot && isArrayField) {
                    criteriaList.add(Criteria.where(key).size(0));
                    criteriaList.add(Criteria.where(key).is(null));
                }
                if (!CollectionUtils.isEmpty(criteriaList)) {
                    Criteria orCriteria = new Criteria();
                    orCriteria.orOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
                    andCriteriaList.add(orCriteria);
                }
            } else {
                if (item.startsWith(NOT)) {
                    // 存储结构为数组时，非查询加上数组为空查询
                    if (isArrayField) {
                        Criteria orCriteria = new Criteria();
                        String str = EscapeChars.forRegex(item.substring(1));
                        andCriteriaList.add(orCriteria.orOperator(Criteria.where(key).regex(String.format(neStr, str), "si"),
                                Criteria.where(key).size(0),
                                Criteria.where(key).is(null)
                        ));
//                        Criteria criteria = withElemCriteria(key, String.format(neStr, str));
//                        andCriteriaList.add(orCriteria.orOperator(criteria,
//                                Criteria.where(key).size(0),
//                                Criteria.where(key).is(null)
//                        ));
                    } else {
                        andCriteriaList.add(Criteria.where(key).regex(String.format(neStr, EscapeChars.forRegex(item.substring(1))), "si"));
//                        Criteria criteria = withElemCriteria(key, String.format(neStr, EscapeChars.forRegex(item.substring(1))));
//                        andCriteriaList.add(criteria);
                    }
                } else {
                    andCriteriaList.add(Criteria.where(key).regex(EscapeChars.forRegex(item), "si"));
//                    Criteria criteria = withElemCriteria(key, EscapeChars.forRegex(item));
//                    andCriteriaList.add(criteria);
                }
            }
        });
    }

    /**
     * 判断key是否为内嵌属性
     * 内嵌属性时，转为elemMatch匹配，提高查询效率
     * @param key
     * @param content
     * @return
     */
//    private Criteria withElemCriteria(String key, String content) {
//        Criteria criteria;
//        String[] keys = key.split(EscapeChars.forRegex(NESTED_ATTR));
//        if(keys.length == 2) {
//            criteria = new Criteria(keys[0]);
//            criteria.elemMatch(Criteria.where(keys[1]).regex(content, "si"));
//        } else {
//            criteria = new Criteria(key);
//            criteria.regex(content, "si");
//        }
//        return criteria;
//    }

    /**
     * 构建dbRef列表类型的更新
     *
     * @param fieldValue
     */
    private List<DBRef> buildDBRef(Object fieldValue) {

        List<DBRef> dbRefs = new ArrayList<>();
        List<Object> objects = (List<Object>) fieldValue;
        for (Object object : objects) {
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(object));
            String objId = jsonObject.getString("id");
            if (StringUtils.isEmpty(objId))
                objId = jsonObject.getString("objectId");
            DBRef dbRef = new DBRef(object.getClass().getSimpleName(), new ObjectId(objId));
            dbRefs.add(dbRef);
        }
        return dbRefs;
    }

    private void buildDBRefPush(Update update, String fieldName, Object fieldValue) {
        Update.PushOperatorBuilder pushOperatorBuilder = update.push(fieldName);
        List<DBRef> dbRefs = buildDBRef(fieldValue);
        pushOperatorBuilder.each(dbRefs);
    }

    private void buildDBRefPull(Update update, String fieldName, Object fieldValue) {
        List<DBRef> dbRefs = buildDBRef(fieldValue);
        update.pullAll(fieldName, dbRefs.toArray());
    }
}
