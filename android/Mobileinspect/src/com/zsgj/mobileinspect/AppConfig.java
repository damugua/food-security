package com.zsgj.mobileinspect;

public class AppConfig {
	public static String SERVERIP = "123.130.113.6";
	public static String SERVERPORT = "7000";
	public static String SERVER = "http://" + SERVERIP + ":" + SERVERPORT;

	// http://123.130.113.6:7000/
	// 登录接口
	public static final String LOGIN_URL = "/SyjUserWcfService.svc/SyjLoginByPhone";
	 //是否应经登陆
    public static boolean isLogin = false;
    //日常巡查
    public static final String ROUTINE_URL="/GeneralInspectionWcfService.svc/QueryByUserIdCNameAndTime";
    //日常巡查
    public static final String QUERYSCHOOL_URL="/KindergartenWcfService.svc/QueryBySyjIdAndName";

}
