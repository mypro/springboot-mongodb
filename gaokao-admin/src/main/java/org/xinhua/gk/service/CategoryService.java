package org.xinhua.gk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xinhua.gk.domain.entity.TCategory;

public interface CategoryService {

    Page<TCategory> findAllWithPage(Pageable pageable);

    TCategory save(TCategory Category);

    TCategory findById(String id);

    TCategory findByCatId(Integer catId);

    void delById(String id);

}
