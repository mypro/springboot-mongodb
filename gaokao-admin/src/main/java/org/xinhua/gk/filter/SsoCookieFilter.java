package org.xinhua.gk.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.xinhua.gk.util.Lang;
import org.xinhua.gk.util.RedisUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class SsoCookieFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(SsoCookieFilter.class);

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("login", "logout", "getUserInfo", "actuator", "static", "/act/video/callback")));

    private static final Set<String> ALLOWED_PATHS_PREFIX = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/gk")));

    private static final Set<String> ALLOWED_PATHS_SUFFIX = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(".js", ".css")));


    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
//        boolean allow = ALLOWED_PATHS.contains(path);
        boolean allow = false;
        for (String allowedPath : ALLOWED_PATHS) {
            if (path.contains(allowedPath)) {
                allow = true;
                break;
            }
        }
        for (String allowedPath : ALLOWED_PATHS_PREFIX) {
            if (path.startsWith(allowedPath)) {
                allow = true;
                break;
            }
        }
        for (String allowedPath : ALLOWED_PATHS_SUFFIX) {
            if (path.endsWith(allowedPath)) {
                allow = true;
                break;
            }
        }
        if (allow) {
            filterChain.doFilter(request, response);
            return;
        }
        Cookie nameCookie = getCookie(request, "user_seq_id");
        String code = request.getParameter("code");
        String access_token = request.getParameter("access_token");
        String catId = request.getParameter("catId");
        if (nameCookie == null && Lang.isEmpty(code) && Lang.isEmpty(access_token)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println("{\"code\":401,\"message\":\"未登录\",\"data\":\"\"}");
            response.getWriter().flush();
            return;

            //PC 请求
        } else if (nameCookie != null) {
            resolveCookie(nameCookie, request);
        }
        filterChain.doFilter(request, response);
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookieName == null || cookieName.equals("")) {
            return null;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
                return (Cookie) c;
            }
        }
        return null;
    }

    public void resolveCookie(Cookie nameCookie, HttpServletRequest request) {

    }


}