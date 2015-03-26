//
//  videoViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/20.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "videoViewController.h"
#import "API.h"
#import "commonModel.h"
@interface videoViewController ()<commonConnectDelegate>

@end

@implementation videoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    [self getCameraList];
}

-(void)getCameraList
{
    NSString *url = [NSString stringWithFormat:@"%@%@?parentCode=%@",BASE_URL,CAMERA_LIST,[[NSUserDefaults standardUserDefaults] objectForKey:@"userCode"]];
    NSLog(@"%@",url);
    commonModel *cameraConnect = [[commonModel alloc]initWithUrl:url];
    cameraConnect.delegate = self;
}

-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    NSArray *cameraList = [dataDic objectForKey:@"Cameras"];
    
}

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{

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
