package org.xinhua.gk.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.xinhua.gk.common.Constants;
import org.xinhua.gk.common.Convert;
import org.xinhua.gk.common.PageObject;
import org.xinhua.gk.domain.entity.TBaseDict;
import org.xinhua.gk.service.BaseDictService;
import org.xinhua.gk.domain.condition.QueryCondition;
import org.xinhua.gk.domain.vo.ResultVO;
import org.xinhua.gk.domain.vo.SessionUser;

import javax.servlet.http.HttpSession;

/**
 * 字典表
 */
@RestController
@RequestMapping("/dict")
public class BaseDictController  extends AbstractController{

    Logger log = LoggerFactory.getLogger(BaseDictController.class);

    @Autowired
    private BaseDictService baseDictService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Convert convert;

    @GetMapping(value = "/get")
    public ResultVO get(@RequestParam String id) {
        ResultVO resultVO = new ResultVO();
        TBaseDict dict = baseDictService.findById(id);
        resultVO.setResult(dict);
        return resultVO;
    }
    @GetMapping(value = "/findByName")
    public ResultVO findByName(@RequestParam String name) {
        ResultVO resultVO = new ResultVO();
        TBaseDict dict = baseDictService.findByName(name);
        resultVO.setResult(dict);
        return resultVO;
    }


    @PostMapping(value = "/page")
    public ResultVO page(@RequestBody QueryCondition condition) {
        ResultVO resultVO = new ResultVO();
        PageObject pageObject = new PageObject(condition.getPageNo() - 1, condition.getPageSize());
        Page<TBaseDict> pager = baseDictService.findAllWithPage(pageObject);
        resultVO.setResult(pager);
        return resultVO;
    }

    @PostMapping(value = "/save")
    public ResultVO save(@RequestBody TBaseDict dict) {
        ResultVO resultVO = new ResultVO();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        log.info("save");
        TBaseDict saved = baseDictService.save(dict);
        resultVO.setResult(saved);
        return resultVO;
    }
    @GetMapping(value = "/del")
    public ResultVO del(@RequestParam String id) {
        ResultVO resultVO = new ResultVO();
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        log.info("删除配置,id={},user={}", id, currentUser);

        try {
            baseDictService.delById(id);
        } catch (Exception e) {
            log.error("删除失败", e);
            resultVO.setCode(500);
            resultVO.setDesc("删除失败");
        }
        return resultVO;
    }
}
