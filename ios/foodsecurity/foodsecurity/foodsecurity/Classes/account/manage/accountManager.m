//
//  accountManager.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/25.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "accountManager.h"
#define FRAME ([[UIScreen mainScreen] bounds])
#define NAVIGATIONBAR_HIGHT 64
@interface accountManager ()

@end

@implementation accountManager

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    [self createLabels];
    // Do any additional setup after loading the view from its nib.
}

//添加导航条的标题和返回按钮
-(void)setNavigationBar
{
    self.navigationItem.title = @"账号管理";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(buttonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
}

//创建内容
-(void)createLabels
{
    UILabel *nameLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, 10+NAVIGATIONBAR_HIGHT, FRAME.size.width, 40)];
    nameLabel.text=[NSString stringWithFormat:@"  家长姓名:  %@",[[NSUserDefaults standardUserDefaults] valueForKey:@"Name"]];
    nameLabel.textColor=[UIColor blackColor];
    nameLabel.font=[UIFont systemFontOfSize:15];
    nameLabel.layer.borderColor=[UIColor lightGrayColor].CGColor;
    nameLabel.layer.borderWidth=0.5;
    [self.view addSubview:nameLabel];
    
    UILabel *relationLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, 10+NAVIGATIONBAR_HIGHT+40, FRAME.size.width, 40)];
    relationLabel.text=[NSString stringWithFormat:@"  亲属关系:  %@",[[NSUserDefaults standardUserDefaults] valueForKey:@"Relation"]];
    relationLabel.textColor=[UIColor blackColor];
    relationLabel.font=[UIFont systemFontOfSize:15];
    relationLabel.layer.borderColor=[UIColor lightGrayColor].CGColor;
    relationLabel.layer.borderWidth=0.5;
    [self.view addSubview:relationLabel];
    
    UILabel *addressLabel=[[UILabel alloc]initWithFrame:CGRectMake(0, 10+NAVIGATIONBAR_HIGHT+40+40, FRAME.size.width, 80)];
    addressLabel.text=[NSString stringWithFormat:@"  家庭住址:  %@",[[NSUserDefaults standardUserDefaults] valueForKey:@"Address"]];
    addressLabel.textColor=[UIColor blackColor];
    addressLabel.font=[UIFont systemFontOfSize:15];
    addressLabel.layer.borderColor=[UIColor lightGrayColor].CGColor;
    addressLabel.layer.borderWidth=0.5;
    addressLabel.numberOfLines=0;
    [self.view addSubview:addressLabel];
    
    
    UIButton *changePassWordBtn=[UIButton buttonWithType:UIButtonTypeCustom];
    changePassWordBtn.frame=CGRectMake(0, 170+NAVIGATIONBAR_HIGHT+20, FRAME.size.width, 40);
    [changePassWordBtn setTitle:@"修改密码" forState:UIControlStateNormal];
    [changePassWordBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    changePassWordBtn.titleLabel.textAlignment=NSTextAlignmentLeft;
    changePassWordBtn.layer.borderWidth=0.5;
    changePassWordBtn.layer.borderColor=[UIColor lightGrayColor].CGColor;
    [self.view addSubview:changePassWordBtn];
    
//    UIButton *changePassWordBtn=[UIButton buttonWithType:UIButtonTypeCustom];
//    changePassWordBtn.frame=CGRectMake(0, 170+NAVIGATIONBAR_HIGHT+20, FRAME.size.width, 40);
//    [changePassWordBtn setTitle:@"修改密码" forState:UIControlStateNormal];
//    [changePassWordBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
//    changePassWordBtn.titleLabel.textAlignment=NSTextAlignmentLeft;
//    changePassWordBtn.layer.borderWidth=0.5;
//    changePassWordBtn.layer.borderColor=[UIColor lightGrayColor].CGColor;
//    [self.view addSubview:changePassWordBtn];
    
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
