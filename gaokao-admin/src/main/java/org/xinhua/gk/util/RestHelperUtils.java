package org.xinhua.gk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestHelperUtils {

    private static Logger logger = LoggerFactory.getLogger(RestHelperUtils.class);

    public static JSONObject postJson(String url, String jsonBody) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        org.springframework.http.MediaType type = org.springframework.http.MediaType
                .parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", org.springframework.http.MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>(jsonBody, headers);

        JSONObject postForObject = new JSONObject();

        long timeStart = System.currentTimeMillis();

        try {
            postForObject = restTemplate.postForObject(url, formEntity, JSONObject.class);
        } catch (Exception e) {
            logger.error("RestHelperUtils.postJson ", e);
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.postRequest url:{}", url);
                logger.info("RestHelperUtils.postRequest jsonBody:{}", jsonBody);
                logger.info("RestHelperUtils.postRequest result:{}", postForObject.toString());
                logger.info("RestHelperUtils.postRequest costTime:{}", costTime);
            }
        }

        return postForObject;
    }

    public static String postJsonString(String url, String jsonBody) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        org.springframework.http.MediaType type = org.springframework.http.MediaType
                .parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", org.springframework.http.MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>(jsonBody, headers);

        String resp = null;

        long timeStart = System.currentTimeMillis();

        try {
            resp = restTemplate.postForObject(url, formEntity, String.class);
            logger.info(resp);
        } catch (Exception e) {
            logger.error("RestHelperUtils.postJson ", e);
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.postRequest url:{}", url);
                logger.info("RestHelperUtils.postRequest jsonBody:{}", jsonBody);
                logger.info("RestHelperUtils.postRequest result:{}", resp);
                logger.info("RestHelperUtils.postRequest costTime:{}", costTime);
            }
        }

        return resp;
    }

    public static <T> T getForObject(String serviceUrl, String uri, Class<T> responseType, HashMap<String, Object> map) {

        RestTemplate restTemplate = new RestTemplate();

        T result = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        long timeStart = System.currentTimeMillis();

        try {
            result = restTemplate.getForObject(serviceUrl + uri, responseType, map);

            if (responseType.getSimpleName().equals("byte[]")
                    && result != null) {
                String resultStr = new String((byte[]) result);
                JSONObject resultJson = null;
                try {
                    resultJson = JSON.parseObject(resultStr);
                } catch (Exception e) {
                }
                if (resultJson != null && resultJson.getString("code") != null && "0".equals(resultJson.getString("code"))) {
                    logger.error("RestException : {}", resultJson.getString("message"));
                }
            }
        } catch (Exception e) {
            logger.error("RestHelperUtils.getForObject ", e);
            logger.error("RestHelperUtils.getForObject {} occured exception {}", serviceUrl + uri, e.getMessage());
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.getRequest url:{}", serviceUrl + uri);
                logger.info("RestHelperUtils.getRequest costTime:{}", costTime);
            }
        }
        return result;

    }

    public static <T> T postForObject(String url, Class<T> responseType, HashMap<String, Object> paramMap) {

        RestTemplate restTemplate = new RestTemplate();

        T result = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//            map.add(entry.getKey(), String.valueOf(entry.getValue()));
//            logger.info("key: {} value:{}", entry.getKey(), String.valueOf(entry.getValue()));
//        }

        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(
                paramMap, httpHeaders);

        long timeStart = System.currentTimeMillis();
        try {
            result = restTemplate.postForObject(url, httpEntity, responseType);

            logger.info("RestHelperUtils.postForObject  result: {}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("RestHelperUtils.postForObject ", e);
            logger.error("RestHelperUtils.postForObject {} occured exception {}", url, e.getMessage());
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.postForObject url:{}", url);
                logger.info("RestHelperUtils.postForObject costTime:{}", costTime);
            }
        }
        return result;

    }

    public static <T> T postForObject(String url, Class<T> responseType, List param) {

        RestTemplate restTemplate = new RestTemplate();

        T result = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());

//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.put("body",param);

        HttpEntity<ArrayList> httpEntity = new HttpEntity(
                param, httpHeaders);

        long timeStart = System.currentTimeMillis();
        try {
            result = restTemplate.postForObject(url, httpEntity, responseType);

            logger.info("RestHelperUtils.postForObject  result: {}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("RestHelperUtils.postForObject ", e);
            logger.error("RestHelperUtils.postForObject {} occured exception {}", url, e.getMessage());
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.postForObject url:{}", url);
                logger.info("RestHelperUtils.postForObject costTime:{}", costTime);
            }
        }
        return result;

    }

    public static <T> T getForObject(String serviceUrl, String uri, Class<T> responseType) {

        RestTemplate restTemplate = new RestTemplate();

        T result = null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        long timeStart = System.currentTimeMillis();

        try {
            result = restTemplate.getForObject(serviceUrl + uri, responseType);

            if (responseType.getSimpleName().equals("byte[]")
                    && result != null) {
                String resultStr = new String((byte[]) result);
                JSONObject resultJson = null;
                try {
                    resultJson = JSON.parseObject(resultStr);
                } catch (Exception e) {
                }
                if (resultJson != null && resultJson.getString("code") != null && "0".equals(resultJson.getString("code"))) {
                    logger.error("RestException : {}", resultJson.getString("message"));
                }
            }
        } catch (Exception e) {
            logger.error("RestHelperUtils.getForObject ", e);
            logger.error("RestHelperUtils.getForObject {} occured exception {}", serviceUrl + uri, e.getMessage());
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.getRequest url:{}", serviceUrl + uri);
                logger.info("RestHelperUtils.getRequest costTime:{}", costTime);
            }
        }
        return result;

    }

    public static <T> T postForFormObject(String serviceUrl, String url, Map<String, Object> paramMap, Class<T> responseType, Object... uriVariables) {

        RestTemplate restTemplate = new RestTemplate();

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        String requestStatus = "";
        T result = null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);

        long timeStart = System.currentTimeMillis();

        try {
            if (null != uriVariables && uriVariables.length > 0) {
                result = restTemplate.postForObject(serviceUrl + url, httpEntity, responseType, uriVariables);
            } else {
                result = restTemplate.postForObject(serviceUrl + url, httpEntity, responseType);
            }

            if (responseType.getSimpleName().equals("byte[]")
                    && result != null) {
                String resultStr = new String((byte[]) result);
                JSONObject resultJson = null;
                try {
                    resultJson = JSON.parseObject(resultStr);
                } catch (Exception e) {
                }
                if (resultJson != null && resultJson.getString("code") != null && "0".equals(resultJson.getString("code"))) {
                    logger.error("RestException : {}", resultJson.getString("message"));
                }
            }
        } catch (Exception e) {
            logger.error("RestHelperUtils.postRequest ", e);
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                if (paramMap.containsKey("paramJson")) {
                    logger.info("RestHelperUtils.postForFormObject url:{}", serviceUrl + url);
                    logger.info("RestHelperUtils.postForFormObject accessToken:{}", paramMap.get("access_token"));
                    logger.info("RestHelperUtils.postForFormObject paramJson:{}", paramMap.get("paramJson"));
                } else {
                    logger.info("RestHelperUtils.postForFormObject url:{},param:{}", serviceUrl + url, JSON.toJSONString(paramMap));
                }
                logger.info("RestHelperUtils.postForFormObject costTime:{}", costTime);
            }

            if (logger.isDebugEnabled()) {
                if (!StringUtils.isEmpty(result) && result instanceof byte[]) {
                    logger.info("RestHelperUtils.postForFormObject result:{}", returnActualLength((byte[]) result));
                } else {
                    logger.info("RestHelperUtils.postForFormObject result:{}", result);
                }
                if (result instanceof String && !StringUtils.isEmpty(result)) {
                    JSONObject resultJson = JSON.parseObject(result.toString());

                    if (resultJson.containsKey("code")) {
                        requestStatus = resultJson.getString("code");
                    } else if (resultJson.containsKey("resultcode")) {
                        requestStatus = resultJson.getString("resultcode");
                    } else if (resultJson.containsKey("errcode")) {
                        requestStatus = resultJson.getString("errcode");
                    }
                    logger.info("RestHelperUtils.postRequest requestStatus:{}", requestStatus);
                }
            }
        }
        return result;
    }

    public static String getRequest(String serviceUrl, String url) {

        RestTemplate restTemplate = new RestTemplate();

        long timeStart = System.currentTimeMillis();
        String result = "";
        try {
            if (logger.isDebugEnabled()) {
                logger.info("RestHelperUtils.getRequest url:{}", serviceUrl + url);
            }
            result = restTemplate.getForObject(serviceUrl + url, String.class);
            if (logger.isDebugEnabled()) {
                logger.info("RestHelperUtils.getRequest success result:{}", result);
            }
        } catch (Exception e) {
            logger.error("RestHelperUtils.getRequest ", e);
            logger.error("RestHelperUtils.getRequest {} occured exception {}", serviceUrl + url, e.getMessage());
        } finally {
            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;
            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.getRequest url:{}", serviceUrl + url);
                logger.info("RestHelperUtils.getRequest costTime:{}", costTime);
            }
        }
        return result;
    }

    public static String getRequest(String serviceUrl, String url, Map<String, String> header) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> entry : header.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        ResponseEntity<String> result = null;

        long timeStart = System.currentTimeMillis();

        try {
            if (logger.isDebugEnabled()) {
                logger.info("RestHelperUtils.getRequest url:{}, header:{}", serviceUrl + url, headers);
            }

            result = restTemplate.exchange(serviceUrl + url, HttpMethod.GET, new HttpEntity<byte[]>(headers), String.class);

            if (logger.isDebugEnabled()) {
                logger.info("RestHelperUtils.getRequest success result:{}", result);
            }
        } catch (Exception e) {
            logger.error("RestHelperUtils.postJson ", e);
        } finally {

            double costTime = (System.currentTimeMillis() - timeStart) / 1000.0;

            if (logger.isDebugEnabled() || costTime > 1) {
                logger.info("RestHelperUtils.getRequest url:{}, header:{}", serviceUrl + url, headers);
                logger.info("RestHelperUtils.getRequest costTime:{}", costTime);
            }
        }

        return result.getBody();
    }

    private static int returnActualLength(byte[] data) {
        int i = 0;
        for (; i < data.length; i++) {
            if (data[i] == '\0')
                break;
        }
        return i;
    }
}
