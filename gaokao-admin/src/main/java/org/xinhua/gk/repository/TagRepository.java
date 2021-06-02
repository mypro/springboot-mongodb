package org.xinhua.gk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xinhua.gk.domain.entity.TTag;


public interface TagRepository extends MongoRepository<TTag, String> {


    Page<TTag> findAll(Pageable pageable);

}
