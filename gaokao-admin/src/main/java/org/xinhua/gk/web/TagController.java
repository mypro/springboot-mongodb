package org.xinhua.gk.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.xinhua.gk.common.Convert;
import org.xinhua.gk.domain.entity.TTag;
import org.xinhua.gk.domain.vo.ResultVO;
import org.xinhua.gk.repository.condition.TagCondition;
import org.xinhua.gk.service.TagService;
import org.xinhua.gk.util.Lang;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/tag")
public class TagController extends AbstractController{

    Logger log = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Convert convert;

    @GetMapping(value = "/get")
    public ResultVO get(@RequestParam String id) {
        ResultVO resultVO = new ResultVO();
        TTag tag = tagService.findById(id);
        if (Lang.isEmpty(tag)) {
            resultVO.setCode(404);
            resultVO.setResult("此id相关标签不存在");
            return resultVO;
        }
        resultVO.setResult(tag);
        return resultVO;
    }


    @PostMapping(value = "/page")
    public ResultVO page(@RequestBody TagCondition condition) {
        ResultVO resultVO = new ResultVO();
        setPageObject(condition);
        Page<TTag> pager = tagService.findByCondition(condition);
        resultVO.setResult(pager);
        return resultVO;
    }

    @PostMapping(value = "/query")
    public ResultVO list(@RequestBody TagCondition condition) {
        ResultVO resultVO = new ResultVO();
        setPageObject(condition);
        if (Lang.isNotEmpty(condition.getName())) {
            condition.setName(condition.getName().trim());
        }
        Page<TTag> pager = tagService.findByCondition(condition);
        resultVO.setResult(pager.getContent());
        return resultVO;
    }

    @PostMapping(value = "/save")
    public ResultVO save(@RequestBody TTag tag) {
        ResultVO resultVO = new ResultVO();
        if (null == tag) {
            resultVO.setCode(400);
            return resultVO;
        }
        if ( Lang.isNotEmpty(tag.getName()) && Lang.isNotEmpty(tag.getName().trim())) {
            tag.setName(tag.getName().trim());
        }
        //默认为 1 活动标签
        if (null == tag.getType()) {
            tag.setType(1);
        }
        TTag saved = null;
        try {
            saved = tagService.save(tag);
        } catch (Exception e) {
            log.error("标签保存异常",e);
            resultVO.setCode(500);
            resultVO.setDesc(e.getMessage());
        }
        resultVO.setResult(saved);
        return resultVO;
    }
}
