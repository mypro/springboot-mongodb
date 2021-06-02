package org.xinhua.gk.util;

import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xinhua.gk.domain.vo.ResultInfo;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class FileUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static boolean delFile(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

    /**
     * 根据内容获取文件原始类型
     *
     * @param mapObj
     * @return
     */
    public static String getContentType(byte[] mapObj) {
        String type = "";
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis = null;
        try {
            bais = new ByteArrayInputStream(mapObj);
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator itr = ImageIO.getImageReaders(mcis);
            while (itr.hasNext()) {
                ImageReader reader = (ImageReader) itr.next();
                if (reader instanceof GIFImageReader)
                    type = "gif";
                else if (reader instanceof JPEGImageReader)
                    type = "jpg";
                else if (reader instanceof PNGImageReader)
                    type = "png";
                else if (reader instanceof BMPImageReader)
                    type = "bmp";
            }
        } finally {
            if (bais != null)
                try {
                    bais.close();
                } catch (IOException ioe) {
                    LOGGER.error(ioe.getMessage());
                }
            if (mcis != null)
                try {
                    mcis.close();
                } catch (IOException ioe) {
                    LOGGER.error(ioe.getMessage());
                }
        }
        return type;
    }

    /**
     * 检查类型
     *
     * @param dirName
     * @param fileType
     * @return
     */
    public static ResultInfo checkImageType(String dirName, String fileType) {
        ResultInfo check = new ResultInfo();
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        if (dirName == null) {
            dirName = "image";
        }
        //检查扩展名
        if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileType)) {
            check.setErrcode("400");
            check.setErrcode("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
            LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
            return check;
        }
        return check;
    }

    //上传文件
    public static void uploadPicture(MultipartFile multipartFile, String path, String fileName) {
        File f = null;

        // 创建文件
        if (Lang.isEmpty(path) || Lang.isEmpty(fileName)) {
            LOGGER.error("根目录不能为空");
        }
        File dir = new File(path);
        dir.mkdirs();
        f = new File(path + File.separator + fileName);
        int n;
        try (InputStream in = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(f)) {
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer, 0, 4096)) != -1) {
                os.write(buffer, 0, n);
            }

        } catch (IOException e) {
            LOGGER.error("文件上传失败", e);
        }
    }

    //删除文件
    public static boolean deletePicture(String path, String rootPath) {
        // 创建文件
        if (Lang.isEmpty(path) || Lang.isEmpty(rootPath) || !"/".equals(rootPath)) {
            LOGGER.error("根目录或文件目录不能为空");
        }
        try {
            File f = new File(rootPath + path);
            if (null != f && f.isFile()) {
                return f.delete();
            }

        } catch (Exception e) {
            LOGGER.error("文件删除失败", e);
        }
        return false;
    }

    public static String getFileMD5(String dirPath) {
        String md5 = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(dirPath);
            md5 = DigestUtils.md5DigestAsHex(fis);
        } catch (IOException e) {
            LOGGER.error("文件读取失败,无法计算MD5。IO异常:" + dirPath, e);
            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException e1) {
                LOGGER.error("文件关闭异常！！！" + dirPath, e1);
            }
        }
        return md5;
    }

}
