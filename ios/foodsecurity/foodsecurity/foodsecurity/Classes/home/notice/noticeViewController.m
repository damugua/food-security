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
#import "NoticesModel.h"
#import "MJRefresh.h"
#define FRAME ([[UIScreen mainScreen] bounds])
@interface noticeViewController ()<commonConnectDelegate,UITableViewDataSource,UITableViewDelegate>
{
    noticeListModel *noticeListModels;
    UITableView *noticeTableView;
    NSMutableArray *dataArray;
    NSInteger page;
}
@end

@implementation noticeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initData];
    [self setNavigationBar];
    [self togoRequest];
    [self createTableView];
    [self setupRefresh];
}

//初始化
-(void)initData
{
    dataArray=[[NSMutableArray alloc]init];
    page=0;
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
    NSString *pages=[NSString stringWithFormat:@"%d",page];
    [self gotNoticeListparentCode:[[NSUserDefaults standardUserDefaults] stringForKey:@"userCode"] pageIndex:pages pageSize:@"10"];
    NSLog(@"pages  %@",pages);
}

//创建 tableView
-(void)createTableView
{
    noticeTableView=[[UITableView alloc]initWithFrame:CGRectMake(0, 0, FRAME.size.width, FRAME.size.height) style:UITableViewStylePlain];
    noticeTableView.delegate=self;
    noticeTableView.dataSource=self;
    [self.view addSubview:noticeTableView];
}

//网络请求
///GetByTimeAndSyjID(string time, long SyjID, int pageIndex, int pageSize)
-(void)gotNoticeListparentCode:(NSString *)parentCode pageIndex:(NSString *)pageIndex pageSize:(NSString *)pageSize
{
    //参数后缀  接口给的类型不是字符串
    NSDictionary *paramater = @{@"parentCode":parentCode,@"pageIndex":pageIndex,@"pageSize":pageSize};
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_NOTICE_LIST parameters:paramater];
    connect.delegate = self;
    
}

//网络请求回调的三个方法
-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    noticeListModels = [[noticeListModel alloc]init];
    [noticeListModels setParameter:dataDic];
    [dataArray addObjectsFromArray:noticeListModels.noticesArray];
    [noticeTableView reloadData];
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    NSLog(@"%@",errorMessage);
}

-(void)connectError:(commonModel *)connect
{
    
}

//tableView的协议方法
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return dataArray.count;
}

-(UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *nameCell=@"nameCell";
    UITableViewCell *cell=[tableView dequeueReusableCellWithIdentifier:nameCell];
    if (nil==cell) {
        cell=[[UITableViewCell alloc]initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:nameCell];
    }
    NoticesModel *noticesModel=[dataArray objectAtIndex:indexPath.row];
    cell.textLabel.text=noticesModel.Content;
    cell.textLabel.numberOfLines=0;
    cell.detailTextLabel.text=noticesModel.Time;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 60.0;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"%d",indexPath.row);
}

//做导航按钮返回的点击事件
-(void)buttonClicked:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}


/**
 *  集成刷新控件
 */
- (void)setupRefresh
{
    // 1.下拉刷新(进入刷新状态就会调用self的headerRereshing)
    [noticeTableView addHeaderWithTarget:self action:@selector(headerRereshing)];
    //warning 自动刷新(一进入程序就下拉刷新)
    //[self.scrollView headerBeginRefreshing];
    
    // 2.上拉加载更多(进入刷新状态就会调用self的footerRereshing)
    [noticeTableView addFooterWithTarget:self action:@selector(footerRereshing)];
    // 设置文字(也可以不设置,默认的文字在MJRefreshConst中修改)
    noticeTableView.headerPullToRefreshText = @"下拉可以刷新了";
    noticeTableView.headerReleaseToRefreshText = @"松开马上刷新了";
    noticeTableView.headerRefreshingText = @"正在刷新中...";
    noticeTableView.footerPullToRefreshText = @"上拉可以加载更多数据了";
    noticeTableView.footerReleaseToRefreshText = @"松开马上加载更多数据了";
    noticeTableView.footerRefreshingText = @"正在加载中...";
    
}


#pragma mark 开始进入刷新状态
- (void)headerRereshing
{
    // 2.2秒后刷新表格UI
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        // 刷新表格
        NSLog(@"下拉刷新了。。。。。。");
        [dataArray removeAllObjects];
        page=0;
        [self togoRequest];
        // (最好在刷新表格后调用)调用endRefreshing可以结束刷新状态
        [noticeTableView headerEndRefreshing];
    });
    
}

- (void)footerRereshing
{
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        // 刷新表格
        NSLog(@"上拉刷新了。。。。。。");
        page++;
        [self togoRequest];
        // (最好在刷新表格后调用)调用endRefreshing可以结束刷新状态
        [noticeTableView footerEndRefreshing];
    });
    
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
