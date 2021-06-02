package org.xinhua.gk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.xinhua.gk.domain.entity.TTag;
import org.xinhua.gk.repository.TagRepository;
import org.xinhua.gk.repository.condition.TagCondition;
import org.xinhua.gk.repository.custom.TagCustomRepo;
import org.xinhua.gk.service.TagService;
import org.xinhua.gk.util.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private static Logger log = LoggerFactory.getLogger(TagServiceImpl.class);


    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagCustomRepo tagCustomRepo;

    @Override
    public TTag findById(String id) {
        if (Lang.isEmpty(id)) {
            return null;
        }
        Optional<TTag> optional = tagRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public TTag findByName(Integer type, String name) {
        TTag tag = new TTag();
        tag.setType(type);
        tag.setName(name);
        Example<TTag> example = Example.of(tag);
        Optional<TTag> opt = tagRepository.findOne(example);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    @Override
    public List<TTag> findAll() {
        List<TTag> list = tagRepository.findAll();
        return list;
    }

    @Override
    public TTag save(TTag tpl) {
        if (null == tpl) {
            return null;
        }
        return tagRepository.save(tpl);
    }

    @Override
    public Page<TTag> findByCondition(TagCondition condition) {
        // 排序
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "name");
        orders.add(order);
        condition.setOrders(orders);
        Sort sort = new Sort(orders);
        condition.setSort(sort);
        Page<TTag> pager = tagCustomRepo.getPageByCondition(condition);
        return pager;
    }
}
