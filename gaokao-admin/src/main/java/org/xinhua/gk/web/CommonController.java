package org.xinhua.gk.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xinhua.gk.common.Constants;
import org.xinhua.gk.common.Convert;
import org.xinhua.gk.common.FileUploadComponent;
import org.xinhua.gk.domain.condition.QueryCondition;
import org.xinhua.gk.domain.entity.TBaseDict;
import org.xinhua.gk.domain.vo.ResultInfo;
import org.xinhua.gk.domain.vo.ResultVO;
import org.xinhua.gk.domain.vo.SessionUser;
import org.xinhua.gk.service.BaseDictService;
import org.xinhua.gk.service.FileManageService;
import org.xinhua.gk.util.Lang;
import org.xinhua.gk.util.UploadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/common")
public class CommonController extends AbstractController {

    Logger log = LoggerFactory.getLogger(CommonController.class);


    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Convert convert;

    @Autowired
    private FileUploadComponent fileUploadComponent;

    @Autowired
    private BaseDictService baseDictService;

    @Autowired
    private FileManageService fileManageService;


    @ApiOperation(value = "上传图片")
    @PostMapping(value = "fileUpload")
    @ResponseBody
    public ResultInfo noAspectFileUpload(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam("file") MultipartFile[] multipartFiles) {

        ResultInfo resultInfo = new ResultInfo();
        if (Lang.isEmpty(multipartFiles)) {
            resultInfo.setErrcode("400");
            return resultInfo;
        }
        QueryCondition parameter = new QueryCondition();
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        parameter.setOperator(convert.converUser(currentUser));

        try {
            resultInfo = fileUploadComponent.uploadPic(multipartFiles, parameter);
        } catch (Exception e) {
            log.error("上传异常",e);
            resultInfo.setErrcode("500");
        }
        return resultInfo;

    }

    @ApiOperation(value = "删除图片")
    @PostMapping(value = "deletePic")
    @ResponseBody
    public ResultInfo deletePic(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryCondition condition) {
        ResultInfo resultInfo = new ResultInfo();
        String picPath = condition.getPicPath();
        if (Lang.isEmpty(picPath)) {
            resultInfo.setErrcode("400");
            return resultInfo;
        }

        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        condition.setOperator(convert.converUser(currentUser));

        boolean bool = false;
        try {
            bool = fileUploadComponent.deletePic(picPath, condition);
        } catch (Exception e) {
            resultInfo.setErrcode("500");
            return resultInfo;
        }
        resultInfo.setContent(bool);
        return resultInfo;

    }

    @PostMapping("/download/image")
    @ApiOperation(value = "图片资料下载", notes = "传入下载图片地址")
    public void download(@RequestBody QueryCondition condition,
                         HttpServletResponse response) {
        try {
            String downloadFilename = System.currentTimeMillis() + "_图片.zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            String rootPath = fileUploadComponent.getUploadPath();
            List<String> images = condition.getImages();
            if (Lang.isNotEmpty(images)) {
                for (int i = 0; i < images.size(); i++) {

                    zos.putNextEntry(new ZipEntry(images.get(i).substring(images.get(i).lastIndexOf(File.separator) + 1)));
                    InputStream fis = new FileInputStream(new File(rootPath + images.get(i)));
                    byte[] buffer = new byte[1024];
                    int r = 0;
                    while ((r = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, r);
                    }
                    fis.close();
                }
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            log.info("下载用户资料图片出异常了", e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info("下载用户资料图片出异常了", e);
        }
    }


    /**
     * 分片上传
     * produces属性可以设置返回数据的类型以及编码 配合@ResponseBody使用
     */
    @RequestMapping(value = "/chunkUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo chunkUpload(HttpServletRequest request, HttpServletResponse response) {
        ResultInfo resultInfo = new ResultInfo();
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin", "*");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获得文件分片数据
        MultipartFile file = multipartRequest.getFile("chunk");

        int index = Integer.parseInt(multipartRequest.getParameter("fileIndex"));
        log.info("当前文件分片下标为[{}]", index);

        // 新的文件名称 没有的'-'的uuid
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "-" + index + ".tmp";

        String filePath = UploadUtil.generatePath() + newFileName;
        TBaseDict pathDict = baseDictService.findByName(Constants.VIDEO_ROOT_PATH);
        String rootPath = pathDict.getValue() == null ? "/tmp" : pathDict.getValue();
        // 文件分片的新的文件路径
        File uploadFile = new File(rootPath, filePath);

        // 判断文件父目录是否存在 不存在就创建
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        // 保存文件
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 前端传递来文件名
        String originalFilename = multipartRequest.getParameter("filename");

        // 上传的文件分片名称
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tempFile", newFileName);
        resultInfo.setContent(jsonObject);
        return resultInfo;
    }

    /**
     * 合并所有文件
     */
    @RequestMapping(value = "/mergeFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo mergeFile(HttpServletResponse response, String tempFile, String originalFileName) {
        // 解决多域名跨域问题
//        response.addHeader("Access-Control-Allow-Origin", "*");

        ResultInfo resultInfo = new ResultInfo();
        String[] tempFiles = tempFile.split(",");

        log.info("分片文件数组为》{}《", JSON.toJSONString(tempFiles));

        // 文件名后缀 有'.'
        String fileExtName = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 新文件名称 没有'-'的UUID
        String fileName = UUID.randomUUID().toString().replace("-", "") + fileExtName;

        String filePath = UploadUtil.generatePath() + fileName;
        boolean flag = fileManageService.mergeFiles(tempFiles, filePath);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put("recordVedio", filePath);
        }

        jsonObject.put("flag", flag);
        resultInfo.setContent(jsonObject);
        return resultInfo;
    }

    @GetMapping(value = "/currentUser")
    public ResultVO currentUser() {
        ResultVO resultVO = new ResultVO();
        SessionUser currentUser = (SessionUser) httpSession.getAttribute(Constants.SESSION_USER);
        resultVO.setResult(currentUser);
        return resultVO;
    }




}
