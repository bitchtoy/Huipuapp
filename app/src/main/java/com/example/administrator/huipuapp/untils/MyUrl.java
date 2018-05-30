package com.example.administrator.huipuapp.untils;

/**
 * 请求地址
 * Created by Administrator on 2017/6/1.
 */

public class MyUrl {
    public static String MyURl = "http://124.239.180.168:8080/jc6";


    public  static  String BASEURL="http://210.209.72.186/";
    //登录
    public static String login = MyURl + "/platform/sys/login!login.action";

    //获取机构
    public static String getmechanism = MyURl + "/app/dept!list.action";

    //获取用户
    public static String getuser = MyURl + "/app/user!list.action";

    //退出
    public static  String signout=MyURl+"/platform/sys/logout!logout.action";

    //我的获取待办流程列表
    public  static  String mine=MyURl+"/app/business-list!myProcess.action";

    //
    public  static  String START="http://210.209.72.186/messageInter/initApp.do?";
    public  static  String EXIT="http://210.209.72.186/messageInter/exitApp.do?";
    public  static  String NOTICE="http://210.209.72.186/messageInter/getBottle.do?";
}
