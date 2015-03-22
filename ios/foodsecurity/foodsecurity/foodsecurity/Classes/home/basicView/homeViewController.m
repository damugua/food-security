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

@interface homeViewController ()

@end

@implementation homeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self setNavigationBar];

}

-(void)setNavigationBar
{
    self.navigationItem.title = @"主页";
}




- (IBAction)videoClick:(id)sender {
    videoViewController *video = [[videoViewController alloc]init];
    [self.navigationController pushViewController:video animated:NO];
}


- (IBAction)communicateClick:(id)sender {
    communicateViewController *communicate = [[communicateViewController alloc]init];
    [self.navigationController pushViewController:communicate animated:NO];

}

- (IBAction)cookbookClick:(id)sender {
    cookbookViewController *cookbook = [[cookbookViewController alloc]init];
    [self.navigationController pushViewController:cookbook animated:NO];

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
