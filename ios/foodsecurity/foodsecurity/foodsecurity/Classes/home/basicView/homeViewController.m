//
//  homeViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/20.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "homeViewController.h"
#import "AFNetworking.h"
#import "communicateViewController.h"
#import "cookbookViewController.h"
#import "videoViewController.h"
#import "menuView.h"
#import "accountManager.h"

@interface homeViewController ()<sendButtonClickEvent>

@end

@implementation homeViewController
{
    UIView *backShadow;
    menuView *menu;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self setNavigationBar];

}

-(void)setNavigationBar
{
    self.navigationItem.title = @"主页";
    UIButton *menuBtn = [UIButton buttonWithType:UIButtonTypeContactAdd];
    [menuBtn addTarget:self action:@selector(menuBtnClick) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barbtn = [[UIBarButtonItem alloc]initWithCustomView:menuBtn];
    self.navigationItem.rightBarButtonItem = barbtn;
}

-(void)menuBtnClick
{
    menu = [[menuView alloc]initWithFrame:CGRectMake(self.view.frame.size.width*0.6-10, 50,self.view.frame.size.width*0.4,self.view.frame.size.width*0.4*0.8)];
    menu.delegate = self;
    [self.view.window addSubview:menu];

    backShadow = [[UIView alloc]initWithFrame:self.view.frame];
    backShadow.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4];
    [self.view addSubview:backShadow];
}

-(void)accountManagerClick
{
    [backShadow removeFromSuperview];
    [menu removeFromSuperview];
    accountManager *manager = [[accountManager alloc]init];
    [self.navigationController pushViewController:manager animated:YES];
}





-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    UITouch *touch = [touches anyObject];
    if (touch.view == backShadow) {
        [backShadow removeFromSuperview];
        [menu removeFromSuperview];
    }
}




- (IBAction)videoClick:(id)sender {
    videoViewController *video = [[videoViewController alloc]init];
    [self.navigationController pushViewController:video animated:YES];
}


- (IBAction)communicateClick:(id)sender {
    communicateViewController *communicate = [[communicateViewController alloc]init];
    [self.navigationController pushViewController:communicate animated:YES];

}

- (IBAction)cookbookClick:(id)sender {
    cookbookViewController *cookbook = [[cookbookViewController alloc]init];
    [self.navigationController pushViewController:cookbook animated:YES];

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
