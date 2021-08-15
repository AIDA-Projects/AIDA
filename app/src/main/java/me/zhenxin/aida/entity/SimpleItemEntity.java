package me.zhenxin.aida.entity;

/**
 * 简单子项实体类
 */
public class SimpleItemEntity {
    /**
     * 简单子项图片
     */
    private Object simpleItemImg;
    /**
     * 简单子项标题
     */
    private String simpleItemTitle;
    /**
     * 简单子项摘要
     */
    private String simpleItemSummary;

    public Object getSimpleItemImg() {
        return simpleItemImg;
    }

    public void setSimpleItemImg(Object simpleItemImg) {
        this.simpleItemImg = simpleItemImg;
    }

    public String getSimpleItemTitle() {
        return simpleItemTitle;
    }

    public void setSimpleItemTitle(String simpleItemTitle) {
        this.simpleItemTitle = simpleItemTitle;
    }

    public String getSimpleItemSummary() {
        return simpleItemSummary;
    }

    public void setSimpleItemSummary(String simpleItemSummary) {
        this.simpleItemSummary = simpleItemSummary;
    }
}
