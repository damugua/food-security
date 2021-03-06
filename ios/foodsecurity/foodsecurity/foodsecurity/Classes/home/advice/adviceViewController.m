//
//  adviceViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "adviceViewController.h"

#import "commonModel.h"
#import "API.h"
#import "adviceModel.h"
#import "ToAdviceViewController.h"

@interface adviceViewController ()<commonConnectDelegate>
{
    adviceModel *adviceModels;
}
@end

@implementation adviceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    [self setToolBar];
    [self togoRequest];
    // Do any additional setup after loading the view from its nib.
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    self.navigationController.toolbarHidden=NO;
}

-(void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
    self.navigationController.toolbarHidden=YES;
}

//添加导航条的标题和返回按钮
-(void)setNavigationBar
{
    self.navigationItem.title = @"投诉建议";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(buttonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
    
}

//创建工具栏
-(void)setToolBar
{
    UIBarButtonItem *item=[[UIBarButtonItem alloc]initWithTitle:@"我要投诉" style:UIBarButtonItemStylePlain target:self action:@selector(toolbarClicked)];
    UIBarButtonItem *space=[[UIBarButtonItem alloc]initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:self action:nil];
    self.toolbarItems=@[space,item,space];
    
}

-(void)toolbarClicked
{
    NSLog(@"tool bar clicked");
    ToAdviceViewController *toAdvieceVC=[[ToAdviceViewController alloc]init];
    [self.navigationController pushViewController:toAdvieceVC animated:YES];
    
}

//去做网络请求
-(void)togoRequest
{
    
    
}

//网络请求
-(void)gotAdviceListpageIndex:(NSString *)pageIndex pageSize:(NSString *)pageSize
{
    //参数后缀  接口给的类型不是字符串
    NSDictionary *paramater = @{@"pageIndex":pageIndex,@"pageSize":pageSize};
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_ADVICE_LIST parameters:paramater];
    connect.delegate = self;
    
}

//网络请求回调的三个方法
-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    adviceModels = [[adviceModel alloc]init];
    [adviceModels setParameter:dataDic];
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    NSLog(@"%@",errorMessage);
}

-(void)connectError:(commonModel *)connect
{
    
}



//左导航按钮返回的点击事件
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
