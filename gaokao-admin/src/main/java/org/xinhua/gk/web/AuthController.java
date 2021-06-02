package org.xinhua.gk.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xinhua.gk.domain.vo.SessionUser;
import org.xinhua.gk.domain.vo.UserVO;
import org.xinhua.authority.web.service.AuthAPIService;
import org.xinhua.authority.web.vo.MenuVO;
import org.xinhua.authority.web.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController extends AbstractController {

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthAPIService authAPIService;


    @Autowired
    private HttpSession httpSession;

    @Value("${common.auth.getUserInfoUrl}")
    private String getUserInfoUrl;


    @GetMapping(value = "/getUserNavs")
    public ResultVO getUserNavs() {
        SessionUser user = (SessionUser) httpSession.getAttribute("sessionUser");
        ResultVO<List<MenuVO>> result = authAPIService.getUserNavs(user.getPersonId());
        return result;
    }

}
