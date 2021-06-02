package org.xinhua.gk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xinhua.gk.domain.entity.TCategory;
import org.xinhua.gk.dserialNO.ISerialNO;
import org.xinhua.gk.repository.CategoryRepository;
import org.xinhua.gk.service.CategoryService;
import org.xinhua.gk.util.Lang;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    protected ISerialNO iSerialNO;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<TCategory> findAllWithPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public TCategory save(TCategory category) {
        if (Lang.isEmpty(category)) {
            return null;
        }
        if (Lang.isEmpty(category.getCatId())) {
            if (Lang.isEmpty(category.getParent())) {
                //0 是分类跟节点
                category.setParent(0);
            }
            category.setCatId(iSerialNO.getCategorySerialNo());
            category.setCreatedAt(new Date());
        } else {
            category.setUpdatedAt(new Date());
        }
        return categoryRepository.save(category);
    }

    @Override
    public TCategory findById(String id) {
        if (Lang.isEmpty(id)) {
            return null;
        }
        Optional<TCategory> actOpt = categoryRepository.findById(id);
        if (actOpt.isPresent()) {
            return actOpt.get();
        }
        return null;
    }

    @Override
    public TCategory findByCatId(Integer catId) {
        if (Lang.isEmpty(catId)) {
            return null;
        }
        return categoryRepository.findByCatId(catId);
    }

    @Override
    public void delById(String id) {
        categoryRepository.deleteById(id);
    }
}
