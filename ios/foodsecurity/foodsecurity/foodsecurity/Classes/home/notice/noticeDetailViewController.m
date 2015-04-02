//
//  noticeDetailViewController.m
//  foodsecurity
//
//  Created by fuyang on 15/4/2.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "noticeDetailViewController.h"
#define FRAME ([[UIScreen mainScreen] bounds])
@interface noticeDetailViewController ()
{
    UIScrollView *scrollView;
}
@end

@implementation noticeDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    [self createScrollView];
    [self createContent];
}

//添加导航条的标题和返回按钮
-(void)setNavigationBar
{
    self.navigationItem.title = @"公告详情";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(buttonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
}

//创建一个滚动试图
-(void)createScrollView
{
    scrollView=[[UIScrollView alloc]initWithFrame:CGRectMake(0, 0, FRAME.size.width, FRAME.size.height)];
    [self.view addSubview:scrollView];
}

//增加内容
-(void)createContent
{
    //标题
    UILabel *titleLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, 15, FRAME.size.width, 30)];
    titleLabel.numberOfLines=0;
    titleLabel.textColor=[UIColor blackColor];
    titleLabel.font=[UIFont systemFontOfSize:20];
    NSString *str=self.noticeModel.Title;
    CGRect rect=[str boundingRectWithSize:CGSizeMake(FRAME.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingTruncatesLastVisibleLine attributes:nil context:nil];
    titleLabel.text=str;
    titleLabel.frame=CGRectMake(0, 15, FRAME.size.width, rect.size.height);
    [scrollView addSubview:titleLabel];
    
    //线
    UILabel *lineLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, titleLabel.frame.size.height+titleLabel.frame.origin.y+15, FRAME.size.width, 0.5)];
    lineLabel.backgroundColor=[UIColor lightGrayColor];
    [scrollView addSubview:lineLabel];
    
    //公告正文
    UILabel *contentLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, lineLabel.frame.size.height+lineLabel.frame.origin.y+15, FRAME.size.width, 30)];
    contentLabel.numberOfLines=0;
    contentLabel.textColor=[UIColor blackColor];
    contentLabel.font=[UIFont systemFontOfSize:15];
    NSString *contentStr=[NSString stringWithFormat:@"       %@\n%@",self.noticeModel.Content,self.noticeModel.Time];
    CGRect contentRect=[contentStr boundingRectWithSize:CGSizeMake(FRAME.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingTruncatesLastVisibleLine attributes:nil context:nil];
    contentLabel.text=contentStr;
    contentLabel.frame=CGRectMake(0, lineLabel.frame.size.height+lineLabel.frame.origin.y+15, FRAME.size.width, contentRect.size.height+30);
    [scrollView addSubview:contentLabel];

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

@end
