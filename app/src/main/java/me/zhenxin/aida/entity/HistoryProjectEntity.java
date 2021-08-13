package me.zhenxin.aida.entity;

import android.graphics.drawable.Drawable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 历史项目实体类
 * 主要存储历史项目的信息
 * 以便历史项目页展示相关内容
 */
@Entity
public class HistoryProjectEntity {
    /**
     * 项目id
     * ID自增长
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * 项目图标
     * 无法存储图片，动态使用包名查看APP的ICON。
     */
    @Transient
    private Drawable icon;
    /**
     * 项目名称
     */
    @Unique
    private String name;
    /**
     * 项目包名
     */
    @Property
    private String pkg;
    /**
     * 最后修改时间
     */
    @Property
    private String lastTime;

    @Generated(hash = 625361545)
    public HistoryProjectEntity(Long id, String name, String pkg, String lastTime) {
        this.id = id;
        this.name = name;
        this.pkg = pkg;
        this.lastTime = lastTime;
    }

    @Generated(hash = 10136452)
    public HistoryProjectEntity() {
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkg() {
        return this.pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
