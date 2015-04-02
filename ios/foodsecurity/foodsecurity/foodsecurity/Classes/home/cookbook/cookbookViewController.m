//
//  cookbookViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/20.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "cookbookViewController.h"
#import "commonModel.h"
#import "API.h"
#import "cookBookModel.h"
#import "YSHTTPClient.h"
#import "DayCookBookModel.h"
#define FRAME ([[UIScreen mainScreen] bounds])

@interface cookbookViewController ()<commonConnectDelegate>
{
    cookBookModel *cookBookModels;
    UIScrollView *scrollView;
    UILabel *contentLabel;
}
@end

@implementation cookbookViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self createNavigationBar];
    [self createWeekButtons];
    [self createScrollView];
    [self createCookBookContent];
    [self gotCookBookparentCode:[[NSUserDefaults standardUserDefaults] stringForKey:@"userCode"] pageIndex:@"1" pageSize:@"10"];
    // Do any additional setup after loading the view from its nib.
}

//创建导航内容
-(void)createNavigationBar
{
    self.navigationItem.title = @"食谱";
    UIButton *leftButton = [UIButton buttonWithType:UIButtonTypeInfoLight];
    [leftButton setImage:[UIImage imageNamed:@""] forState:UIControlStateNormal];
    [leftButton addTarget:self action:@selector(leftButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *leftButtonItem = [[UIBarButtonItem alloc]initWithCustomView:leftButton];
    self.navigationItem.leftBarButtonItem = leftButtonItem;
}

//创建星期的button
-(void)createWeekButtons
{
    NSLog(@"kuan %f",FRAME.size.width);
    NSArray *weekArray=[NSArray arrayWithObjects:@"周一",@"周二",@"周三",@"周四",@"周五", nil];
    for (int i=0; i<weekArray.count; i++) {
        UIButton *weekButton=[UIButton buttonWithType:UIButtonTypeCustom];
        weekButton.frame=CGRectMake((FRAME.size.width/5)*i, 64, FRAME.size.width/5, 40);
        [weekButton setTitle:[weekArray objectAtIndex:i] forState:UIControlStateNormal];
        [weekButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        weekButton.tag=i+1;
        weekButton.layer.borderColor=[UIColor lightGrayColor].CGColor;
        weekButton.layer.borderWidth=0.5;
        [weekButton addTarget:self action:@selector(weekButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
        [self.view addSubview:weekButton];
    }
    
}

//创建一个滚动试图
-(void)createScrollView
{
    scrollView=[[UIScrollView alloc]initWithFrame:CGRectMake(0, 104, FRAME.size.width, FRAME.size.height-104)];
    [self.view addSubview:scrollView];
}

//创建食谱的内容
-(void)createCookBookContent
{
    //食谱内容
    contentLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, 10, FRAME.size.width, 30)];
    contentLabel.numberOfLines=0;
    contentLabel.textColor=[UIColor blackColor];
    contentLabel.font=[UIFont systemFontOfSize:20];
}

//左导航按钮的点击事件
-(void)leftButtonClicked:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}

//星期button的点击事件
-(void)weekButtonClicked:(UIButton *)button
{
    [self freshCookBookContent:button.tag];
}

-(void)freshCookBookContent:(NSInteger)day
{
    
    DayCookBookModel *dayCookBookModel=[cookBookModels.RecipesArray objectAtIndex:day];
    NSString *str=dayCookBookModel.Explain;
    CGRect rect=[str boundingRectWithSize:CGSizeMake(FRAME.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingTruncatesLastVisibleLine attributes:nil context:nil];
    contentLabel.text=str;
    contentLabel.frame=CGRectMake(0, 15, FRAME.size.width, rect.size.height);
    [scrollView addSubview:contentLabel];

}

-(void)gotCookBookparentCode:(NSString *)parentCode pageIndex:(NSString *)pageIndex pageSize:(NSString *)pageSize
{
    //参数后缀  接口给的类型不是字符串
    NSDictionary *paramater = @{@"parentCode":parentCode,@"pageIndex":pageIndex,@"pageSize":pageSize};
    //地址
    
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_COOKBOOK parameters:paramater];
    connect.delegate = self;

}


-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    cookBookModels = [[cookBookModel alloc]init];
    [cookBookModels setParameter:dataDic];
    UIButton *button=(UIButton *)[self.view viewWithTag:1];
    [self weekButtonClicked:button];
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    NSLog(@"%@",errorMessage);
}

-(void)connectError:(commonModel *)connect
{
    
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
