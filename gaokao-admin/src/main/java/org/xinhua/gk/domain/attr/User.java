package org.xinhua.gk.domain.attr;


import java.io.Serializable;

/**
 * @author 陈伟超
 * @Description 人员
 */
@BaseAttr
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8075737093607316000L;
    /**
     * 用户id
     */
    private String userId;            //ID
    /**
     * 登录用户名
      */
    private String userLoginName;
    /**
     * 用户名称
     */
    private String name;
    // 角色Id
    private String roleId;
    // 角色名称
    private String roleName;
    // 组室id
    private String orgId;
    // 组室名称
    private String orgName;
    // 部门id
    private String deptId;
    // 部门名称
    private String deptName;
    // 行政部门
    private String groupPaths;
    // 用户ip
    private String ipAddress;



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public User() {

    }

    public User(String name, String deptName, String roleName) {
        this.name = name;
        this.deptName = deptName;
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGroupPaths() {
        return groupPaths;
    }

    public void setGroupPaths(String groupPaths) {
        this.groupPaths = groupPaths;
    }
}
