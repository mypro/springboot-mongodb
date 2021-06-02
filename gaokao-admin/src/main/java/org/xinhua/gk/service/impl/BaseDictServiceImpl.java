package org.xinhua.gk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xinhua.gk.common.Constants;
import org.xinhua.gk.domain.entity.TBaseDict;
import org.xinhua.gk.dserialNO.ISerialNO;
import org.xinhua.gk.repository.BaseDictRepository;
import org.xinhua.gk.service.BaseDictService;
import org.xinhua.gk.util.Lang;

import java.util.Optional;

@Service
public class BaseDictServiceImpl implements BaseDictService {

    @Autowired
    protected ISerialNO iSerialNO;

    @Autowired
    private BaseDictRepository baseDictRepository;

    public Page<TBaseDict> findAllWithPage(Pageable pageable) {
        return baseDictRepository.findAll(pageable);
    }

    @Override
    public TBaseDict save(TBaseDict dict) {
        if (Lang.isEmpty(dict)) {
            return null;
        }

        return baseDictRepository.save(dict);
    }

    @Override
    public TBaseDict findById(String id) {
        if (Lang.isEmpty(id)) {
            return null;
        }
        Optional<TBaseDict> actOpt = baseDictRepository.findById(id);
        if (actOpt.isPresent()) {
            return actOpt.get();
        }
        return null;
    }

    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "BaseDict:findByName")
    public TBaseDict findByName(String name) {
        return baseDictRepository.findByName(name);
    }

    @Override
    public void delById(String id) {
        baseDictRepository.deleteById(id);
    }
}
