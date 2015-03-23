//
//  registOne.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/23.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "registOne.h"

@interface registOne ()

@end

@implementation registOne

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    [self setNavigation];
}

//设置导航返回

-(void)setNavigation
{
    UIBarButtonItem *btn = [[UIBarButtonItem alloc]initWithTitle:@"取消" style:UIBarButtonItemStyleDone target:self action:@selector(backToLogin)];
    self.navigationItem.leftBarButtonItem = btn;
}

-(void)backToLogin
{
    [self dismissViewControllerAnimated:YES completion:nil];
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
