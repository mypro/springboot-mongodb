package org.xinhua.gk.test.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xinhua.gk.util.RedisUtil;

/**
 * AuthHelper Tester.
 *
 * @author ${author}
 * @version 1.0
 * @since 11/27/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTest {

    @Autowired
    private RedisUtil redisUtil;

    @Before
    public void before() throws Exception {
        System.out.println("*****************************************");
    }

    @After
    public void after() throws Exception {
        System.out.println("*****************************************");
    }

    /**
     * Method: checkUserNavs(String seqId, String systemId, String authorityServer, String getNavs)
     */
    @Test
    public void testCheckUserNavs() throws Exception {
        redisUtil.stringAdd("hello", "world");
        redisUtil.stringAdd("hello2", "world", 400);
        System.out.println(redisUtil.stringGet("hello"));
        System.out.println(redisUtil.stringGet("hello2"));
    }





}
