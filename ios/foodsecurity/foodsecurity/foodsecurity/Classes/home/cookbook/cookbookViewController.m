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
#define FRAME ([[UIScreen mainScreen] bounds])

@interface cookbookViewController ()<commonConnectDelegate>
{
    cookBookModel *cookBookModels;
}
@end

@implementation cookbookViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self createNavigationBar];
    [self createWeekButtons];
    // Do any additional setup after loading the view from its nib.
}

-(void)createNavigationBar
{
    self.navigationItem.title = @"食谱";
    UIButton *leftButton = [UIButton buttonWithType:UIButtonTypeInfoLight];
    [leftButton setImage:[UIImage imageNamed:@""] forState:UIControlStateNormal];
    [leftButton addTarget:self action:@selector(leftButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *leftButtonItem = [[UIBarButtonItem alloc]initWithCustomView:leftButton];
    self.navigationItem.leftBarButtonItem = leftButtonItem;
}

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

-(void)leftButtonClicked:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}

-(void)weekButtonClicked:(UIButton *)button
{
    NSLog(@"周 %d",button.tag);
}


-(void)gotCookBookId:(NSString *)Id page:(NSString *)page pageSize:(NSString *)pageSize
{
    //参数后缀  接口给的类型不是字符串
    NSDictionary *paramater = @{@"kindergartenId":Id,@"pageIndex":page,@"pageSize":pageSize};
    //地址
    
    commonModel *connect = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_COOKBOOK parameters:paramater];
    connect.delegate = self;

}


-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    cookBookModels = [[cookBookModel alloc]init];
    [cookBookModels setParameter:dataDic];
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
