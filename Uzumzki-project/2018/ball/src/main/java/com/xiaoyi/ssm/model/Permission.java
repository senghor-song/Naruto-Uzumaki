package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.List;

/**
 * 实体
 */
public class Permission implements Serializable {
	
	List<Permission> permissions;
	
    public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/** 权限ID */
    private String id;

    /** 权限标题 */
    private String title;

    /** 权限内容 */
    private String content;

    /** 一级权限是否可以访问0=否1=是 */
    private Integer rightType1;

    /** 二级权限是否可以访问0=否1=是 */
    private Integer rightType2;

    /** 三级权限是否可以访问0=否1=是 */
    private Integer rightType3;

    /** 四级权限是否可以访问0=否1=是 */
    private Integer rightType4;

    /** 五级权限是否可以访问0=否1=是 */
    private Integer rightType5;

    /** 六级权限是否可以访问0=否1=是 */
    private Integer rightType6;

    /** 七级权限是否可以访问0=否1=是 */
    private Integer rightType7;

    /** 0=一级菜单1=二级菜单2=页面按钮3右键按钮 */
    private Integer menuButton;

    /** 菜单标题 */
    private String menuTitle;

    /** 菜单Url */
    private String menuUrl;

    /** 一级菜单图标 */
    private String menuIcon;

    /** 父级ID */
    private String parentId;

    /**
     * Permission
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     * @return ID 权限ID
     */
    public String getId() {
        return id;
    }

    /**
     * 权限ID
     * @param id 权限ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 权限标题
     * @return Title 权限标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 权限标题
     * @param title 权限标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 权限内容
     * @return Content 权限内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 权限内容
     * @param content 权限内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 一级权限是否可以访问0=否1=是
     * @return Right_type1 一级权限是否可以访问0=否1=是
     */
    public Integer getRightType1() {
        return rightType1;
    }

    /**
     * 一级权限是否可以访问0=否1=是
     * @param rightType1 一级权限是否可以访问0=否1=是
     */
    public void setRightType1(Integer rightType1) {
        this.rightType1 = rightType1;
    }

    /**
     * 二级权限是否可以访问0=否1=是
     * @return Right_type2 二级权限是否可以访问0=否1=是
     */
    public Integer getRightType2() {
        return rightType2;
    }

    /**
     * 二级权限是否可以访问0=否1=是
     * @param rightType2 二级权限是否可以访问0=否1=是
     */
    public void setRightType2(Integer rightType2) {
        this.rightType2 = rightType2;
    }

    /**
     * 三级权限是否可以访问0=否1=是
     * @return Right_type3 三级权限是否可以访问0=否1=是
     */
    public Integer getRightType3() {
        return rightType3;
    }

    /**
     * 三级权限是否可以访问0=否1=是
     * @param rightType3 三级权限是否可以访问0=否1=是
     */
    public void setRightType3(Integer rightType3) {
        this.rightType3 = rightType3;
    }

    /**
     * 四级权限是否可以访问0=否1=是
     * @return Right_type4 四级权限是否可以访问0=否1=是
     */
    public Integer getRightType4() {
        return rightType4;
    }

    /**
     * 四级权限是否可以访问0=否1=是
     * @param rightType4 四级权限是否可以访问0=否1=是
     */
    public void setRightType4(Integer rightType4) {
        this.rightType4 = rightType4;
    }

    /**
     * 五级权限是否可以访问0=否1=是
     * @return Right_type5 五级权限是否可以访问0=否1=是
     */
    public Integer getRightType5() {
        return rightType5;
    }

    /**
     * 五级权限是否可以访问0=否1=是
     * @param rightType5 五级权限是否可以访问0=否1=是
     */
    public void setRightType5(Integer rightType5) {
        this.rightType5 = rightType5;
    }

    /**
     * 六级权限是否可以访问0=否1=是
     * @return Right_type6 六级权限是否可以访问0=否1=是
     */
    public Integer getRightType6() {
        return rightType6;
    }

    /**
     * 六级权限是否可以访问0=否1=是
     * @param rightType6 六级权限是否可以访问0=否1=是
     */
    public void setRightType6(Integer rightType6) {
        this.rightType6 = rightType6;
    }

    /**
     * 七级权限是否可以访问0=否1=是
     * @return Right_type7 七级权限是否可以访问0=否1=是
     */
    public Integer getRightType7() {
        return rightType7;
    }

    /**
     * 七级权限是否可以访问0=否1=是
     * @param rightType7 七级权限是否可以访问0=否1=是
     */
    public void setRightType7(Integer rightType7) {
        this.rightType7 = rightType7;
    }

    /**
     * 0=一级菜单1=二级菜单2=页面按钮3右键按钮
     * @return Menu_button 0=一级菜单1=二级菜单2=页面按钮3右键按钮
     */
    public Integer getMenuButton() {
        return menuButton;
    }

    /**
     * 0=一级菜单1=二级菜单2=页面按钮3右键按钮
     * @param menuButton 0=一级菜单1=二级菜单2=页面按钮3右键按钮
     */
    public void setMenuButton(Integer menuButton) {
        this.menuButton = menuButton;
    }

    /**
     * 菜单标题
     * @return Menu_title 菜单标题
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * 菜单标题
     * @param menuTitle 菜单标题
     */
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    /**
     * 菜单Url
     * @return Menu_Url 菜单Url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 菜单Url
     * @param menuUrl 菜单Url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 一级菜单图标
     * @return Menu_icon 一级菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 一级菜单图标
     * @param menuIcon 一级菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 父级ID
     * @return Parent_id 父级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级ID
     * @param parentId 父级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}