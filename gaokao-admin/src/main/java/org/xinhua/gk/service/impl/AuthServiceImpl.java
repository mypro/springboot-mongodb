package org.xinhua.gk.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.xinhua.gk.common.Constants;
import org.xinhua.gk.domain.vo.SessionUser;
import org.xinhua.gk.util.Lang;
import org.xinhua.authority.web.aop.AuthorizationException;
import org.xinhua.authority.web.service.AuthAPIService;
import org.xinhua.authority.web.utils.AuthHelper;
import org.xinhua.authority.web.vo.AuthorityVO;
import org.xinhua.authority.web.vo.MenuVO;
import org.xinhua.authority.web.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthAPIService {

    Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${common.auth.serverUrl}")
    private String serverUrl;

    @Value("${common.auth.getNavsUrl}")
    private String getNavsUrl;

    @Value("${common.auth.getUserAuthorityUrl}")
    private String getUserAuthorityUrl;

    @Value("${common.auth.getUserPermissionsUrl}")
    private String getUserPermissionsUrl;

    @Value("${common.auth.getUserDataAuthorityUrl}")
    private String getUserDataAuthorityUrl;

    @Value("${common.auth.getUserInfoUrl}")
    private String getUserInfoUrl;

    @Value("${common.auth.systemId}")
    private String systemId;

    @Autowired
    private HttpSession httpSession;

    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "getUserNavs")
    public ResultVO<List<MenuVO>> getUserNavs(String userId) {
        ResultVO<List<MenuVO>> resultVO = AuthHelper.getUserNavs(userId, systemId, serverUrl, getNavsUrl);
        return resultVO;
    }

    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "checkUserAuthority")
    public boolean checkUserPermission(String userId, String targetAuthority) {
        return AuthHelper.checkUserPermission(userId, systemId, targetAuthority, serverUrl, getUserPermissionsUrl);
    }

    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "getUserDataAuthority")
    public ResultVO<List<AuthorityVO>> getUserDataPermission(String userId) {
        return AuthHelper.getUserDataPermission(userId, systemId, serverUrl, getUserDataAuthorityUrl);
    }



    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "checkUserRole")
    public boolean checkUserRole(String userId, String targetRole) {
        return AuthHelper.checkUserRole(userId, systemId, targetRole, serverUrl, getUserInfoUrl);
    }

    @Override
    public boolean checkRole(String s) throws AuthorizationException {
        boolean bool = checkUserRole(getCurrentUserId(), s);
        if (!bool) {
            throw new AuthorizationException("current has no auth of  " + s);
        }
        return true;
    }

    @Override
    public boolean checkRoles(String[] roles) throws AuthorizationException {
        if (Lang.isEmpty(roles)) {
            throw new AuthorizationException();
        }
        return AuthHelper.checkUserRoles(getCurrentUserId(), systemId, roles, serverUrl, getUserInfoUrl);
    }

    @Override
    public boolean checkPermission(String s) throws AuthorizationException {
        boolean bool = checkUserPermission(getCurrentUserId(), s);
        if (!bool) {
            throw new AuthorizationException("current has no permission of  " + s);
        }
        return true;
    }

    @Override
    @Cacheable(value = Constants.ACT_CACHE_PREFIX + "checkPermissions")
    public boolean checkPermissions(String[] perms) throws AuthorizationException {
        boolean bool = AuthHelper.checkUserPermissions(getCurrentUserId(), systemId, perms, serverUrl, getUserPermissionsUrl);
        if (!bool) {
            throw new AuthorizationException("current has no auth of  " + perms);
        }
        return true;
    }

    @Override
    public boolean isPermitted(String s) {
        boolean bool = false;
        try {
            bool = checkUserPermission(getCurrentUserId(), s);
        } catch (AuthorizationException e) {
            LOGGER.error("权限认证异常", e);
        }
        return bool;
    }

    private String getCurrentUserId() throws AuthorizationException {
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        if (null == currentUser || Lang.isEmpty(currentUser.getPersonId())) {
            throw new AuthorizationException();
        }
        return currentUser.getPersonId();
    }
}
