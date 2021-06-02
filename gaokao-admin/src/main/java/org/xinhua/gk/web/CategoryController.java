package org.xinhua.gk.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.xinhua.gk.common.Constants;
import org.xinhua.gk.common.Convert;
import org.xinhua.gk.common.PageObject;
import org.xinhua.gk.domain.condition.QueryCondition;
import org.xinhua.gk.domain.entity.TCategory;
import org.xinhua.gk.domain.vo.CategoryVO;
import org.xinhua.gk.domain.vo.ResultVO;
import org.xinhua.gk.domain.vo.SessionUser;
import org.xinhua.gk.service.CategoryService;
import org.xinhua.gk.util.Lang;
import org.xinhua.authority.web.aop.AuthorizationException;
import org.xinhua.authority.web.service.AuthAPIService;
import org.xinhua.authority.web.vo.AuthorityVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cat")
public class CategoryController extends AbstractController {

    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Convert convert;

    @Autowired
    private AuthAPIService authAPIService;

    @GetMapping(value = "/get")
    public ResultVO get(@RequestParam Integer catId) {
        ResultVO resultVO = new ResultVO();
        TCategory category = categoryService.findByCatId(catId);
        if (Lang.isEmpty(category)) {
            resultVO.setCode(404);
            resultVO.setResult("此id相关分类不存在");
            return resultVO;
        }
        CategoryVO vo = convert.categoryDO2VO(category);
        resultVO.setResult(vo);
        return resultVO;
    }


    @PostMapping(value = "/page")
    public ResultVO page(@RequestBody QueryCondition condition) {
        ResultVO resultVO = new ResultVO();
        PageObject pageObject = new PageObject(condition.getPageNo() - 1, condition.getPageSize());
        Page<TCategory> pager = categoryService.findAllWithPage(pageObject);
        resultVO.setResult(pager);
        return resultVO;
    }

    @GetMapping(value = "/list")
//    @RequiresRoles("course-admin")
//    @RequiresPermissions("activities-list")
//    @RequiresPermissions(value = {"activities-list", "apply-manage"}, logical = Logical.AND)
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        PageObject pageObject = new PageObject(0, 50);
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        Page<TCategory> pager = categoryService.findAllWithPage(pageObject);

        List list = new ArrayList();
        List<TCategory> cats = pager.getContent();
        log.info("分类列表查询:{}",JSON.toJSONString(cats));

        //过滤用户数据权限
        try {
            if (!authAPIService.checkRole(Constants.ROLE_ADMINISTRATOR)) {
                List<String> auths = getDataPermission(currentUser.getPersonId());
                List<Integer> data = new ArrayList<>();
                if (Lang.isNotEmpty(auths)) {
                    for (String dataStr : auths) {
                        if (dataStr.contains(Constants.DATA_CAT_PREFIX)) {
                            data.add(Integer.valueOf(auths.get(0).replace(Constants.DATA_CAT_PREFIX, "")));
                        }
                    }

                }
                cats = cats.stream().filter(item -> data.contains(item.getCatId())).collect(Collectors.toList());
            }
        } catch (AuthorizationException e) {
            log.error("权限认证异常", e);
        }

        if (Lang.isNotEmpty(cats)) {
            for (TCategory cat : cats) {
                JSONObject json = new JSONObject();
                json.put("catId", cat.getCatId());
                json.put("name", cat.getName());
                list.add(json);
            }
        }


        resultVO.setResult(list);
        return resultVO;
    }

    @PostMapping(value = "/save")
    public ResultVO save(@RequestBody CategoryVO vo) {
        ResultVO resultVO = new ResultVO();
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        log.info("save");
        TCategory cat = convert.categoryVO2DO(vo);
        cat.setCreatedUser(convert.converUser(currentUser));
        TCategory saved = categoryService.save(cat);
        resultVO.setResult(saved);
        return resultVO;
    }

    @GetMapping(value = "/del")
    public ResultVO del(@RequestParam String id) {
        ResultVO resultVO = new ResultVO();
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        log.info("删除分类,id={},user={}", id, currentUser);

        try {
            categoryService.delById(id);
        } catch (Exception e) {
            log.error("删除失败", e);
            resultVO.setCode(500);
            resultVO.setDesc("删除失败");
        }
        return resultVO;
    }

    private List<String> getDataPermission(String userId) {
        List<String> datas = null;
        org.xinhua.authority.web.vo.ResultVO<List<AuthorityVO>> result = authAPIService.getUserDataPermission(userId);
        if (result.getCode() == 200 && Lang.isNotEmpty(result.getResult())) {
            List<AuthorityVO> list = result.getResult();
            datas = new ArrayList<>();
            for (AuthorityVO auth : list) {
                datas.add(auth.getName());
            }
        }
        return datas;
    }
}
