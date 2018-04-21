package com.zhr.test.bean;

import java.util.List;

public class DataBean {

    /**
     * date : 20180420
     * stories : [{"images":["https://pic4.zhimg.com/v2-9ca54fec9c1ec0e0f53ce5868993526b.jpg"],"type":0,"id":9679386,"ga_prefix":"042022","title":"小事 · 5 岁时，我就知道我要做程序员"},{"title":"就算你不看动画片，肯定也知道这只粉红色的猪","ga_prefix":"042021","images":["https://pic4.zhimg.com/v2-b315f27e97438662df81f21bdc421db3.jpg"],"multipic":true,"type":0,"id":9679463},{"images":["https://pic2.zhimg.com/v2-b57db5877535a34ca9336690c5c78625.jpg"],"type":0,"id":9679400,"ga_prefix":"042019","title":"我假装不去靠近你，就不会受伤吧"},{"images":["https://pic3.zhimg.com/v2-faef2b86ee6f088a2e56325e3942288a.jpg"],"type":0,"id":9679410,"ga_prefix":"042018","title":"读书早晚会忘，为什么还要读书？"},{"images":["https://pic3.zhimg.com/v2-a29a62061078863566761c105fb3c78a.jpg"],"type":0,"id":9679112,"ga_prefix":"042017","title":"辞职被老板动情挽留，该怎么办?"},{"images":["https://pic3.zhimg.com/v2-e33c5bfd97750636b6334a2f447ad722.jpg"],"type":0,"id":9679408,"ga_prefix":"042016","title":"在谷歌工作是种怎样的体验？说说我这 4 年来的感受"},{"images":["https://pic2.zhimg.com/v2-24a3ebe629bb6b6604e8eb7f83479f69.jpg"],"type":0,"id":9679275,"ga_prefix":"042015","title":"4A 广告业最后一个大佬也离场了，广告业不行了吗？"},{"images":["https://pic2.zhimg.com/v2-fd00b1d9dc541c859182b0ef93e1b0ad.jpg"],"type":0,"id":9679381,"ga_prefix":"042012","title":"去了备受争议的《复仇者联盟 3》漫威宣发的现场，我想做两点澄清"},{"images":["https://pic2.zhimg.com/v2-46a524d3f643c08fe5399615c48f3ffd.jpg"],"type":0,"id":9678842,"ga_prefix":"042012","title":"大误 · 伏地魔为什么一定要用咒语来杀死哈利？"},{"images":["https://pic4.zhimg.com/v2-fbfbcb725fa8782c77f1bd19d87b7803.jpg"],"type":0,"id":9678597,"ga_prefix":"042010","title":"从什么时候起，你玩游戏开始考虑时间成本了？"},{"images":["https://pic2.zhimg.com/v2-cbe0cfbefd29b840af990df7ff10d279.jpg"],"type":0,"id":9677870,"ga_prefix":"042009","title":"说「加州生活质量全美最差」前，先了解下评分是怎么来的"},{"images":["https://pic3.zhimg.com/v2-a499b71532db156bf7d3f8dccf1397de.jpg"],"type":0,"id":9679264,"ga_prefix":"042008","title":"城市青年生活故事 · 离不开这座安逸巴适的城"},{"images":["https://pic1.zhimg.com/v2-27d0daef0f8136306514bd550b8e0828.jpg"],"type":0,"id":9678728,"ga_prefix":"042008","title":"一种留莫西干发型还用屁股呼吸的朋克龟，快要灭绝了"},{"images":["https://pic2.zhimg.com/v2-c02f2dedac9f221886194dfd5bf9fa89.jpg"],"type":0,"id":9678468,"ga_prefix":"042007","title":"历史上有哪些很有意思的心理学实验？"},{"images":["https://pic3.zhimg.com/v2-4468664bc517d23e464ac24d5b848a1e.jpg"],"type":0,"id":9678907,"ga_prefix":"042007","title":"在一家幼儿园里，我曾不经意间看见了许多暴力"},{"images":["https://pic3.zhimg.com/v2-b40308b9b566b0c89c4af5f78f27b4f2.jpg"],"type":0,"id":9679099,"ga_prefix":"042006","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }
}
