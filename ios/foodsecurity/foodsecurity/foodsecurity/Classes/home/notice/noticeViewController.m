//
//  noticeViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "noticeViewController.h"

@interface noticeViewController ()

@end

@implementation noticeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setNavigationBar];
    // Do any additional setup after loading the view from its nib.
}

-(void)setNavigationBar
{
    self.navigationItem.title = @"公告";
    UIButton *backButton = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [backButton addTarget:self action:@selector(buttonClicked:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:backButton];
    self.navigationItem.leftBarButtonItem = barbtn;
}


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
