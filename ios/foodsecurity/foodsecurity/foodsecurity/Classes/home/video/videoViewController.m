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
#import "YSPlayerController.h"
#import "YSHTTPClient.h"

#import <CoreMotion/CoreMotion.h>
#import <OpenAL/al.h>
#import <OpenAL/alc.h>

@interface videoViewController ()<commonConnectDelegate,YSPlayerControllerDelegate>

@end

@implementation videoViewController
{
    commonModel *getToken;
    commonModel *cameraConnect;
    NSString *accessToken;
    YSPlayerController *controller;

}



- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self getCameraList];
    [self getToken];
}



-(void)getCameraList
{
    NSDictionary *parameters = @{@"parentCode":[[NSUserDefaults standardUserDefaults] objectForKey:@"userCode"]};
    cameraConnect = [[commonModel alloc]initWithUrl:BASE_URL getPath:CAMERA_LIST parameters:parameters];
    cameraConnect.delegate = self;
}

-(void)getToken
{
    getToken = [[commonModel alloc]initWithUrl:BASE_URL getPath:GET_ACCESS_TOKEN parameters:nil];
    getToken.delegate = self;
}

-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    if (connect==cameraConnect) {
        NSArray *cameraList = [dataDic objectForKey:@"Cameras"];
//        NSLog(@"%@",cameraList);
    }
    if (connect==getToken) {
        NSString *message = [dataDic objectForKey:@"Message"];
        NSData *messageData = [message dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *messageDic = [NSJSONSerialization JSONObjectWithData:messageData options:NSJSONReadingMutableContainers error:nil];
        accessToken = [[[messageDic objectForKey:@"result"] objectForKey:@"data"] objectForKey:@"accessToken"];
        [[YSHTTPClient sharedInstance] setClientAccessToken:accessToken];

        [self playTheVideo];
    }

}


-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    
}

-(void)connectError:(commonModel *)connect
{

}


-(void)playTheVideo
{
    controller = [[YSPlayerController alloc]initWithDelegate:self];

//    NSLog(@"%f,%f",fStartTime,fStopTime);
    NSLog(@"%@",accessToken);
    [controller startRealPlayWithCamera:@"1fc581e773a54bbc9b8052ba593f5a92" accessToken:accessToken inView:_videoView];

//    [controller startPlaybackWithCamera:@"4" accessToken:accessToken fromTime:fStopTime toTime:fStartTime inView:_videoView];
}

-(void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value
{
    NSLog(@"%ld",(long)msgType);
}

-(void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
    [controller stopRealPlay];

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
