package org.xinhua.gk.common;

public class Constants {

    public static final int SUCCESS_CODE = 200;         //请求成功
    public static final int FAIL_CODE = 500;
    public static final String FAIL_STATUS = "-1";

    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";

    public static final String SESSION_SYSTEMID = "systemId";

    public static final String ACT_CACHE_PREFIX = "act:";

    public static final String VIDEO_ROOT_PATH = ACT_CACHE_PREFIX + "videoRootPath";
    public static final String VIDEO_OUTPUT_PATH = ACT_CACHE_PREFIX + "videoOutputPath";
    public final static String ACT_OUTPUT_VIDEO_DOMAIN = ACT_CACHE_PREFIX + "outputVideoDomain";
    /**
     * 查询列表默认每页行数
     */
    public final static Integer PAGE_SIZE = 20;

    /**
     * session存的用户名称
     */
    public final static String SESSION_USER = "sessionUser";

    /**
     * 留言建议模板
     */
    public final static String PROPOSAL_TEMPLATE_NAME = "proposalTemplate";
    /**
     * 直播模板
     */
    public final static String LIVETYPE_TEMPLATE_NAME = "liveTemplate";

    /**
     * 直播默认模板配置参数
     */
    public final static String LIVE_TEMPLATE_PARAM = "liveTemplateParam";

    /**
     * 录播默认模板配置参数
     */
    public final static String VIDEO_TEMPLATE_PARAM = "videoTemplateParam";

    /**
     * 视频回放页面警示语
     */
    private final static String WARNING_CONTENT = "warningContent";

    /**
     * 学时统计学分转换阈值(观看进度超过90%开始计算学分)
     */
    public final static String DURATION_THRESHOLD = "durationThreshold";

    /**
     * redis access_token key
     */
    public final static String ACCESS_TOKEN = "xhcourses:dataservice:access_token";

    /**
     * 回调地址
     */
    public final static String ACT_CALLBACK_URL = "actCallbackUrl";
    /**
     * 转码地址
     */
    public final static String ACT_TRANSCODE_URL = "actTranscodeUrl";

    /**
     * 备用推拉流地址
     */
    public final static String BAK_STREAM_ADDRESS = "bakStreamAddress";
    public final static String BAK_STREAM_LIST = "bakStreamList";

    /**
     * 直播在线人数阈值
     */
    public final static String ONLINE_THRESHOLD = "onlineThreshold";

    /**
     * 超级管理员角色
     */
    public final static String ROLE_ADMINISTRATOR = "administrator";
    /**
     * 管理员角色
     */
    public final static String ROLE_MANAGER = "manager";
    /**
     * 数据权限前缀
     * 分类数据
     */
    public final static String DATA_CAT_PREFIX = "data@cat@";


    /*****************枚举配置*******************/
    public enum ActivityStatusEnum {

        HAVE_NOT_BEGUN(1, "未开始"),
        ON_THE_MARCH(2, "进行中"),
        WEED_OUT(3, "已淘汰"),
        HAVE_ENDED(4, "报名已截止"),
        HAVE_FINISHED(5, "已结束"),;

        private Integer code;
        private String desc;

        ActivityStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * LabelAttr_type类型枚举
     */
    public enum LabelTypeEnum {

        LECTURER(1, "主讲人"),
        SHARER(2, "分享人"),
        HOST(3, "主持人"),
        OTHERS(4, "其他"),
        ;

        private Integer code;
        private String desc;

        LabelTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }


    //直播状态
    public enum LiveStatusEnum {

        HAVE_NOT_BEGUN(1, "未开始"),
        ON_THE_MARCH(2, "进行中"),
        HAVE_FINISHED(3, "已结束"),;

        private Integer code;
        private String desc;

        LiveStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    //直播分类
    public enum LiveTypeEnum {

        LIVE_TYPE_VIDEO(1, "视频直播"),
        LIVE_TYPE_IMG_AND_TEXT(2, "图文直播"),
        LIVE_TYPE_IMG_AND_OTHER(30, "其他"),;

        LiveTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum ConvertTaskStatusEnum {

        NULL(0, "任务未在运行"),
        PROCESSING(1, "转换任务执行中"),
        FINISH(2, "任务已执行完成"),
        ERROR(3, "任务转换出错"),
        ;

        ConvertTaskStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    //直播分类
    public enum TemplateSerNoEnum {

        SER_NO_TITLE(1, "课程标题"),
        SER_NO_LECTURER(2, "主讲人"),
        SER_NO_SHARER(3, "分享人"),
        SER_NO_HOST(4, "主持人"),
        SER_NO_TIME(5, "起始时间"),
        SER_NO_DESC(6, "简介"),
        SER_NO_WARN(99, "提示文字"),;

        TemplateSerNoEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 学时类型
     */
    public enum ClassHourTypeEnum {

        OFF_JOB_TRAINING(1, "脱产培训学时"),
        NETWORK_TRAINING(2, "网络培训学时"),
        OTHER_TRAINING(9, "其他");

        private Integer code;
        private String desc;

        ClassHourTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 学时类型
     */
    public enum StreamTypeEnum {

        XINHUA_INTRANET(2024, "新华社流"),
        ALIBABA_INTERNET(2021, "阿里流");


        private Integer code;
        private String desc;

        StreamTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static StreamTypeEnum getStreamType(Integer code) {
            if (null ==  code) {
                return StreamTypeEnum.XINHUA_INTRANET;
            }
            for (StreamTypeEnum streamType : StreamTypeEnum.values()) {
                if (streamType.getCode().intValue() == code.intValue()) {
                    return streamType;
                }
            }
            return StreamTypeEnum.XINHUA_INTRANET;
        }
    }

}
