package org.xinhua.gk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.xinhua.gk.domain.entity.TCategory;


public interface CategoryRepository extends MongoRepository<TCategory, String> {


    Page<TCategory> findAll(Pageable pageable);

   TCategory findByCatId(Integer CatId);

}
