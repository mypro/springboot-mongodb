package org.xinhua.gk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xinhua.gk.domain.entity.TBaseDict;


public interface BaseDictRepository extends MongoRepository<TBaseDict, String> {

    Page<TBaseDict> findAll(Pageable pageable);

    TBaseDict findByName(String name);
}
