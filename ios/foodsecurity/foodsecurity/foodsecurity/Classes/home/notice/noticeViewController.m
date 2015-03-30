//
//  noticeViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "noticeViewController.h"
#import "commonModel.h"
#import "API.h"
#import "noticeListModel.h"
@interface noticeViewController ()<commonConnectDelegate>
{
    noticeListModel *noticeListModels;
}
@end

@implementation noticeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    [self togoRequest];
    
}

//添加导航条的标题和返回按钮
-(void)setNavigationBar
{
    self.navigationItem.title = @"公告";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(buttonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
}

//去做网络请求
-(void)togoRequest
{
    
    [self gotNoticeListTime:@"" SyjID:@"" pageIndex:@"1" pageSize:@"10"];
}

//网络请求
///GetByTimeAndSyjID(string time, long SyjID, int pageIndex, int pageSize)
-(void)gotNoticeListTime:(NSString *)time SyjID:(NSString *)SyjID pageIndex:(NSString *)pageIndex pageSize:(NSString *)pageSize
{
    //参数后缀  接口给的类型不是字符串
    NSDictionary *paramater = @{@"pageIndex":pageIndex,@"pageSize":pageSize};
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_NOTICE_LIST parameters:paramater];
    connect.delegate = self;
    
}

//网络请求回调的三个方法
-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    noticeListModels = [[noticeListModel alloc]init];
    [noticeListModels setParameter:dataDic];
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    NSLog(@"%@",errorMessage);
}

-(void)connectError:(commonModel *)connect
{
    
}

//做导航按钮返回的点击事件
-(void)buttonClicked:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
 #pragma mark - Navigation
 
 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
