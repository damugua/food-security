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
    //添加日常巡查
    public static final String ADDINSPECT_URL="/GeneralInspectionWcfService.svc/CreateGeneralInspection";
    //查询学校
    public static final String QUERYSCHOOL_URL="/KindergartenWcfService.svc/QueryBySyjIdAndName";
    //查询投诉建议
    public static final String SUGGESTION_URL="/ComplaintWcfService.svc/GetByTimeAndStatus";
    //获取一条投诉举报信息
    public static final String SUGGESTIONDETAIL_URL="/ComplaintWcfService.svc/GetById";
    // 获取日常巡查结果类型
    public static final String INSPECTRESULTTYPE_URL="/InspectResultTypeWcfService.svc/FindAll";
    // 获取公告消息
    public static final String NOTICES_URL="/NoticeWcfService.svc/GetBySyjCode";

}
