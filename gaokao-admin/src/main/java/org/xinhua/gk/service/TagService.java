package org.xinhua.gk.service;

import org.springframework.data.domain.Page;
import org.xinhua.gk.domain.entity.TTag;
import org.xinhua.gk.repository.condition.TagCondition;

import java.util.List;

public interface TagService {

    TTag findById(String id);

    TTag findByName(Integer type, String name);

    List<TTag> findAll();

    TTag save(TTag tag);

    Page<TTag> findByCondition(TagCondition condition);
}
