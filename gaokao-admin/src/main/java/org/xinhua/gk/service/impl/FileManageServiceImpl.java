package org.xinhua.gk.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xinhua.gk.common.Constants;
import org.xinhua.gk.domain.entity.TBaseDict;
import org.xinhua.gk.repository.BaseDictRepository;
import org.xinhua.gk.service.FileManageService;
import org.xinhua.gk.util.UploadUtil;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件上传服务层
 */
@Service("fileManageService")
public class FileManageServiceImpl implements FileManageService {

    private static Logger LOGGER = LoggerFactory.getLogger(FileManageServiceImpl.class);

    @Autowired
    private BaseDictRepository baseDictRepository;

    /**
     * 利用nio FileChannel合并多个文件
     *
     * @param fpaths     分片文件数组
     * @param resultPath 合并后的文件全路径名
     * @return
     */
    @Override
    public boolean mergeFiles(String[] fpaths, String resultPath) {

        if (fpaths == null || fpaths.length == 0) {
            return false;
        }
        TBaseDict pathDict = baseDictRepository.findByName(Constants.VIDEO_ROOT_PATH);

        String rootPath = pathDict.getValue() == null ? "/tmp" : pathDict.getValue();
        resultPath = rootPath + resultPath;

        if (fpaths.length == 1) {
            return new File(fpaths[0]).renameTo(new File(resultPath));
        }

        // 按照文件的索引进行冒泡排序
        for (int i = 0; i < fpaths.length; i++) {
            for (int j = 0; j < fpaths.length - i - 1; j++) {
                int varJ = Integer.parseInt(fpaths[j].substring(fpaths[j].lastIndexOf("-") + 1, fpaths[j].lastIndexOf(".")));
                int varJ1 = Integer.parseInt(fpaths[j + 1].substring(fpaths[j + 1].lastIndexOf("-") + 1
                        , fpaths[j + 1].lastIndexOf(".")));
                if (varJ > varJ1) { // 即这两个相邻的数是逆序的 交换
                    String temp = fpaths[j];
                    fpaths[j] = fpaths[j + 1];
                    fpaths[j + 1] = temp;
                }
            }
        }

        File[] files = new File[fpaths.length];
        for (int i = 0; i < fpaths.length; i++) {
            files[i] = new File(rootPath + UploadUtil.generatePath() + fpaths[i]);
            // 不存在或不是文件
            if (fpaths[i] == null || !files[i].exists() || !files[i].isFile()) {
                return false;
            }
        }

//        // 逆序排序 前端使用异步ajax上传较快但不能包装文件先后顺序 数据并不是有序的此方法不可用
//        for (int min = 0, max = files.length - 1; min < max; min++, max--) {
//            // 对数组的元素进行位置交换
//            File temp = files[min]; // 定义空变量 保存下标为min的元素 min就为空
//            files[min] = files[max];
//            files[max] = temp;
//        }

        File resultFile = new File(resultPath);

        try {

            // FileOutputStream会造成内存泄露 每次操作文件内容 会先把文件读到内存
            // FileChannel fileChannel = new FileOutputStream(resultFile, true).getChannel();

            // RandomAccessFile可随机访问文件 即支持任意位置读写 操作时零内存
            FileChannel fileChannel = new RandomAccessFile(resultFile, "rw").getChannel();

            for (int i = 0; i < fpaths.length; i++) {
                // 虚拟内存 并不是物理内存 这个暂未搞定 暂时使用fileChannel合成文件
                // 使用内存映射文件能让你创建和修改那些因为太大而无法放入内存的文件 可认为文件已经全部读进了内存 然后把它当成大数组来访问
                // MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileDate.length);

                FileChannel blk = new FileInputStream(files[i]).getChannel();
                fileChannel.transferFrom(blk, fileChannel.size(), blk.size());
                blk.close();
            }
            fileChannel.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
            return false;
        } catch (IOException e) {
            LOGGER.error("", e);
            return false;
        }

        // 删除临时文件
        for (int i = 0; i < fpaths.length; i++) {
            files[i].delete();
        }

        LOGGER.info("文件合并成功文件为[{}]", resultFile);

        return true;
    }
}