package org.xinhua.gk.common;

import cn.hutool.core.util.StrUtil;

/**
 * @author 3mm
 * @Description 国际化 语言枚举类
 */
public enum LanguageEnum {

    /**
     * 美式英文
     */
    LANGUAGE_EN_US("en_us"),
    /**
     * 简体中文
     */
    LANGUAGE_ZH_CN("zh_cn");

    private String language;

    private LanguageEnum(String language) {
        this.language = language;
    }

    /**
     * 获取指定语言类型(如果没有对应的语言类型,则返回中文)
     *
     * @param language 语言类型
     * @return
     */
    public static String getLanguageType(String language) {
        if (StrUtil.isEmpty(language)) {
            return LANGUAGE_ZH_CN.language;
        }
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum.language;
            }
        }
        return LANGUAGE_ZH_CN.language;
    }
}
