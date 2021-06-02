package org.xinhua.gk.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xinhua.gk.domain.attr.LabelAttr;
import org.xinhua.gk.domain.attr.ModuleAttr;
import org.xinhua.gk.domain.attr.TemplateAttr;
import org.xinhua.gk.domain.attr.User;
import org.xinhua.gk.domain.entity.TCategory;
import org.xinhua.gk.domain.vo.CategoryVO;
import org.xinhua.gk.domain.vo.SessionUser;
import org.xinhua.gk.domain.vo.UserVO;
import org.xinhua.gk.util.BeanUtil;
import org.xinhua.gk.util.Lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Convert {

    private static Logger logger = LoggerFactory.getLogger(Convert.class);

    private void loggerOut(String functionName, Exception e) {
        logger.warn("对象转换错误，方法：{}，错误：{}", functionName, e.toString());

    }


    public User converUser(SessionUser suser) {
        User user = null;
        if (suser != null) {
            try {
                user = new User();
                BeanUtils.copyProperties(suser, user, BeanUtil.getNullPropertyNames(suser));
                user.setUserId(suser.getPersonId());
                user.setUserLoginName(suser.getLoginName());
                user.setName(suser.getPersonName());
            } catch (Exception e) {
                loggerOut("converUser", e);
            }
        }
        return user;
    }

    public Map<Integer, List<LabelAttr>> formatPresides(List<LabelAttr> presides) {
        Map<Integer, List<LabelAttr>> data = new HashMap<>();
        if (presides == null || presides.size() == 0) {
            return data;
        }
        for (LabelAttr attr : presides) {
            if (Lang.isEmpty(attr.getType())) {
                continue;
            }
            List<LabelAttr> list = data.get(attr.getType());
            if (list == null || list.size() == 0) {
                list = new ArrayList<>();
            }
            list.add(attr);
            data.put(attr.getType(), list);
        }
        return data;
    }


    public CategoryVO categoryDO2VO(TCategory bean) {
        CategoryVO vo = null;
        if (bean != null) {
            try {
                vo = new CategoryVO();
                BeanUtils.copyProperties(bean, vo, BeanUtil.getNullPropertyNames(bean));
            } catch (Exception e) {
                loggerOut("categoryDO2VO", e);
            }
        }
        return vo;
    }

    public TCategory categoryVO2DO(CategoryVO vo) {
        TCategory bean = null;
        if (vo != null) {
            try {
                bean = new TCategory();
                BeanUtils.copyProperties(vo, bean, BeanUtil.getNullPropertyNames(vo));

            } catch (Exception e) {
                loggerOut("categoryVO2DO", e);
            }
        }
        return bean;
    }


    public UserVO userDO2VO(User bean) {
        UserVO vo = null;
        if (bean != null) {
            try {
                vo = new UserVO();
                BeanUtils.copyProperties(bean, vo, BeanUtil.getNullPropertyNames(bean));
            } catch (Exception e) {
                loggerOut("UserDO2VO", e);
            }
        }
        return vo;
    }

    public User userVO2DO(UserVO vo) {
        User bean = null;
        if (vo != null) {
            try {
                bean = new User();
                BeanUtils.copyProperties(vo, bean, BeanUtil.getNullPropertyNames(vo));
            } catch (Exception e) {
                loggerOut("UserVO2DO", e);
            }
        }
        return bean;
    }

    public List<User> userVO2DO(List<UserVO> vos) {
        List<User> list = null;
        if (Lang.isNotEmpty(vos)) {
            list = new ArrayList<>();
            try {
                for (UserVO vo : vos) {
                    User bean = userVO2DO(vo);
                    list.add(bean);
                }
            } catch (Exception e) {
                loggerOut("UserVO2DO", e);
            }
        }
        return list;
    }

    public List<UserVO> userDO2VO(List<User> beans) {
        List<UserVO> list = null;
        if (Lang.isNotEmpty(beans)) {
            list = new ArrayList<>();
            try {
                for (User bean : beans) {
                    UserVO vo = userDO2VO(bean);
                    list.add(vo);
                }
            } catch (Exception e) {
                loggerOut("UserDO2VO", e);
            }
        }
        return list;
    }


    /**
     * 添加TemplateAttr
     *
     * @param map
     * @param labelName
     * @param content
     * @param sno
     * @param labelDisplay
     */
    public void addTemplateAttr(Map<Integer, TemplateAttr> map, String labelName,
                                String content, Integer sno, Integer labelDisplay) {
        TemplateAttr template = map.get(sno);
        if (template == null) {
            template = new TemplateAttr();
        }
        List<ModuleAttr> moduleList = template.getText();
        if (moduleList == null || moduleList.size() == 0) {
            moduleList = new ArrayList<>();
        }
        ModuleAttr module = new ModuleAttr();
        module.setContent(content);
        moduleList.add(module);
        template.setSerNo(sno);
        template.setLabel(labelName);
        template.setIsDisplay(labelDisplay);
        template.setText(moduleList);
        map.put(sno, template);
    }

    /**
     * 更新TemplateAttr
     *
     * @param temp
     * @param presides
     * @param sno
     * @param content
     */
    public void updateTemplateAttr(TemplateAttr temp, List<LabelAttr> presides, Integer sno, String content) {
        if (presides != null && presides.size() > 0) {
            List<ModuleAttr> moduleList = new ArrayList<>();
            for (LabelAttr attr : presides) {
                if (Lang.isEmpty(attr.getType())) {
                    continue;
                }
                if (attr.getType().equals(sno)) {
                    ModuleAttr module = new ModuleAttr();
                    module.setContent(attr.getDescp() + " " + attr.getContent());
                    moduleList.add(module);
                }
            }
            temp.setText(moduleList);
        } else {
            List<ModuleAttr> moduleList = temp.getText();
            for (int i = 0; i < moduleList.size(); i++) {
                ModuleAttr module = moduleList.get(i);
                if (!module.getContent().equals(content)) {
                    module.setContent(content);
                }
                moduleList.set(i, module);
            }
            temp.setText(moduleList);
        }
    }

}
