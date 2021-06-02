package org.xinhua.gk.service.impl;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xinhua.gk.common.Constants;
import org.xinhua.authority.web.service.AuthAPIService;

/**
 * AuthServiceImpl Tester.
 *
 * @author ${author}
 * @version 1.0
 * @since 12/22/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceImplTest {
    @Autowired
    private AuthAPIService authService;

    @Before
    public void before() throws Exception {
        System.out.println("*****************************************");
    }

    @After
    public void after() throws Exception {
        System.out.println("*****************************************");
    }

    /**
     * Method: getUserNavs(String userId)
     */
    @Test
    public void testGetUserNavs() throws Exception {
        String userId = "SN_2018052317281143369";
        System.out.println(JSON.toJSONString(authService.getUserNavs(userId)));
    }

    /**
     * Method: checkUserAuthority(String userId, String targetAuthority)
     */
    @Test
    public void testCheckUserAuthority() throws Exception {
        String userId = "SN_2018052317281143369";
        String role = "manager";
        System.out.println(authService.checkUserRole(userId, role));
    }

    /**
     * Method: getUserDataAuthority(String userId)
     */
    @Test
    public void getUserDataPermission() throws Exception {
        String userId = "SN_2018052317281143369";
        System.out.println(JSON.toJSONString(authService.getUserDataPermission(userId)));
    }

    /**
     * Method: checkUserRole(String userId, String targetRole)
     */
    @Test
    public void testCheckUserRole() throws Exception {
        String userId = "SN_2018052317281143369";
//        System.out.println(authService.checkUserRole(userId, "manager"));
        System.out.println(authService.checkUserRole(userId, Constants.ROLE_ADMINISTRATOR));
    }


} 
