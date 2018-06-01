package com.zhr.test.bean;

import java.util.List;

public class DatasBean {

    /**
     * apkLink :
     * author : jiangwei0910410003
     * chapterId : 93
     * chapterName : 基础知识
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 989
     * link : http://blog.csdn.net/jiangwei0910410003/article/details/17006087
     * niceDate : 2016-09-04
     * origin : CSDN
     * projectLink :
     * publishTime : 1472977885000
     * superChapterId : 134
     * superChapterName : 自定义控件
     * tags : []
     * title : Android中attrs.xml文件的使用详解
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private long publishTime;
    private int superChapterId;
    private String superChapterName;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private List<?> tags;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "DatasBean{" +
                "apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                ", tags=" + tags +
                '}';
    }
}
