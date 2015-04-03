//
//  ToAdviceViewController.m
//  foodsecurity
//
//  Created by fuyang on 15/4/3.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "ToAdviceViewController.h"
#import "ToAdviceModel.h"
#import "commonModel.h"
#import "API.h"
#import "PrintObject.h"
#define FRAME ([[UIScreen mainScreen] bounds])
#define NAVIGATIONBAR_HIGHT 64
@interface ToAdviceViewController ()<commonConnectDelegate>
{
    UITextField *targetTextField;
    UITextField *titleTextField;
    UITextView *contentTextView;
    ToAdviceModel *complaint;
}
@end

@implementation ToAdviceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    [self createAdviceContent];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    self.navigationController.toolbarHidden=YES;
}

//添加导航条的标题和返回按钮
-(void)setNavigationBar
{
    //self.navigationController.navigationBar.translucent=NO;
    self.navigationItem.title = @"我的投诉建议";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(leftClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
    
    UIButton *submitButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [submitButton addTarget:self action:@selector(rightClicked:) forControlEvents:UIControlEventTouchUpInside];
    [submitButton setTitle:@"提交" forState:UIControlStateNormal];
    submitButton.frame=CGRectMake(0, 0, 60, 30);
    [submitButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    UIBarButtonItem *rightItem = [[UIBarButtonItem alloc]initWithCustomView:submitButton];
    self.navigationItem.rightBarButtonItem = rightItem;
    
}

//创建投诉内容信息
-(void)createAdviceContent
{
    targetTextField=[[UITextField alloc]initWithFrame:CGRectMake(10, 10+NAVIGATIONBAR_HIGHT, FRAME.size.width-20, 30)];
    targetTextField.placeholder=@"投诉对象";
    targetTextField.borderStyle=UITextBorderStyleRoundedRect;
    [self.view addSubview:targetTextField];
    
    titleTextField=[[UITextField alloc]initWithFrame:CGRectMake(10, 50+NAVIGATIONBAR_HIGHT, FRAME.size.width-20, 30)];
    titleTextField.placeholder=@"标题";
    titleTextField.borderStyle=UITextBorderStyleRoundedRect;
    [self.view addSubview:titleTextField];
    
    
    contentTextView=[[UITextView alloc]initWithFrame:CGRectMake(10, 90+NAVIGATIONBAR_HIGHT, FRAME.size.width-20, FRAME.size.height-NAVIGATIONBAR_HIGHT-100)];
    contentTextView.layer.borderColor=[UIColor lightGrayColor].CGColor;
    contentTextView.layer.borderWidth=0.5;
    [self.view addSubview:contentTextView];
}


//去做网络请求
-(void)togoRequest
{
    complaint=[[ToAdviceModel alloc]init];
    complaint.Title=titleTextField.text;
    complaint.Content=contentTextView.text;
    complaint.ByComplainant=targetTextField.text;
    complaint.Complainant=[[NSUserDefaults standardUserDefaults] stringForKey:@"username"];
    complaint.Status=@"false";
    NSDate *date=[[NSDate alloc]init];
    complaint.Time=[NSString stringWithFormat:@"%@",date];
    [self gotNoticeListTitle:complaint.Title Content:complaint.Content Complainant:complaint.Complainant ByComplainant:complaint.ByComplainant Time:complaint.Complainant Status:complaint.Complainant];
}

//网络请求
///GetByTimeAndSyjID(string time, long SyjID, int pageIndex, int pageSize)
-(void)gotNoticeListTitle:(NSString *)Title Content:(NSString *)Content Complainant:(NSString *)Complainant ByComplainant:(NSString *)ByComplainant Time:(NSString *)Time Status:(NSString *)Status
{
    //参数后缀  接口给的类型不是字符串
  //  NSDictionary *paramater = @{@"Title":Title,@"Content":Content,@"Complainant":Complainant,@"ByComplainant":ByComplainant,@"Time":Time,@"Status":Status};
    NSData *jsonData = [PrintObject getJSON:complaint options:NSJSONWritingPrettyPrinted error:nil];
    NSString *jsonText = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    NSDictionary *paramater = [NSDictionary dictionaryWithObject:jsonText forKey:@"complaint"];
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL postpath:POST_ADVICE parameters:paramater];
    connect.delegate = self;
    
}

//网络请求回调的三个方法
-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
     complaint=[[ToAdviceModel alloc]init];
    [complaint setParameter:dataDic];
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    NSLog(@"%@",errorMessage);
}

-(void)connectError:(commonModel *)connect
{
    NSLog(@"error %@",connect);
}




//左导航按钮返回的点击事件
-(void)leftClicked:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}

//右导航按钮的点击事件
-(void)rightClicked:(UIButton *)button
{
    [self togoRequest];
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
