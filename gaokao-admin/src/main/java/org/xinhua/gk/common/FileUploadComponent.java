package org.xinhua.gk.common;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xinhua.gk.domain.condition.QueryCondition;
import org.xinhua.gk.domain.vo.ResultInfo;
import org.xinhua.gk.util.DateUtil;
import org.xinhua.gk.util.FileUtil;
import org.xinhua.gk.util.Lang;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileUploadComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadComponent.class);

    @Value("${common.uploadConfig.rootPath}")
    private String uploadPath;



    public ResultInfo uploadPic(MultipartFile[] multipartFiles, QueryCondition condition) throws Exception {

        ResultInfo resultInfo = new ResultInfo();

        if (Lang.isEmpty(multipartFiles)) {
            resultInfo.setErrcode("400");
            return resultInfo;
        }

        String filePath = generatePath();

        List<String> files = new ArrayList<>();
        int num = 0;
        for (MultipartFile uploadPic : multipartFiles) {
            //检验文件类型
            String fileType = null;
            try {
                fileType = FileUtil.getContentType(uploadPic.getBytes());

                ResultInfo check = FileUtil.checkImageType("image", fileType);
                if (check.getErrcode().equals("400")) {
                    LOGGER.error("文件类型不接受:{} ", fileType);
                    continue;
                }
            } catch (Exception e) {
                LOGGER.error("文件类型检验错误：", e);

            }

            String fileName = condition.getOperator().getUserLoginName() + "_" + num++ + "_" + System.currentTimeMillis() + "." + fileType;

            FileUtil.uploadPicture(uploadPic, uploadPath + filePath, fileName);
            files.add(filePath + fileName);
            LOGGER.info("上传图片，操作人:{},path:{},", JSON.toJSONString(condition.getOperator()), filePath + fileName);
        }
        resultInfo.setContent(files);
        return resultInfo;
    }

    public boolean deletePic(String path,QueryCondition condition) throws Exception {


        if (Lang.isEmpty(path)) {
            return false;
        }
        LOGGER.info("删除图片，操作人:{},path:{},", JSON.toJSONString(condition.getOperator()), path);

        return FileUtil.deletePicture(path, uploadPath);
    }

    private String generatePath() {
        Date today = new Date();
        StringBuilder path = new StringBuilder();
        path.append(File.separator);
        path.append("actupload");
        path.append(File.separator);
        path.append(DateUtil.transferDate(today, "yyyyMM"));
        path.append(File.separator);
        path.append(DateUtil.transferDate(today, DateUtil.FOR_STR_YYYYMMDD));
        path.append(File.separator);
        return path.toString();
    }

    public String  getUploadPath(){
        return this.uploadPath;
    }

}
