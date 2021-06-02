package org.xinhua.gk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xinhua.gk.domain.entity.TBaseDict;

public interface BaseDictService {

    Page<TBaseDict> findAllWithPage(Pageable pageable);

    TBaseDict save(TBaseDict dict);

    TBaseDict findById(String id);

    TBaseDict findByName(String name);

    void delById(String id);

}
