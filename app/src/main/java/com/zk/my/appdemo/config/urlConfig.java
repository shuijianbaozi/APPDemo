package com.zk.my.appdemo.config;

/**茶百科
 * 开始抓包 或者分析数据
 * Created by My on 2016/10/22.
 */
public class urlConfig {
    //广告地址
    public static final String AD_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";
    //头条
    public static final String TT_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines&page=%d&rows=15";
    //百科/资讯/数据/经营  列表数据
    public static final String OTHRER_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&page=%d&rows=15&type=%d";//根据API文档: d% page是变量可以改变;rows返回的数据行数,也可以改变但是不建议;d% type新闻的分类

}
