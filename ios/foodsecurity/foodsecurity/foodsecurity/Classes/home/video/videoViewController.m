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

#import <CoreMotion/CoreMotion.h>
#import <OpenAL/al.h>
#import <OpenAL/alc.h>

@interface videoViewController ()<commonConnectDelegate,YSPlayerControllerDelegate>

@end

@implementation videoViewController
{
    commonModel *getToken;
    commonModel *cameraConnect;
}



- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    NSLog(@"%f",_videoView.frame.size.height);

    [self getCameraList];
    [self getToken];
    [self playTheVideo];
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
//        NSLog(@"%@",dataDic);
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
    YSPlayerController *controller = [[YSPlayerController alloc]initWithDelegate:self];


    NSDate *date = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyy-MM-dd"];

    NSString *strStartTime = [formatter stringFromDate:date];
    NSDate   *startTime    = [formatter dateFromString:strStartTime];
    NSTimeInterval fStartTime = [startTime timeIntervalSince1970];
    NSTimeInterval fStopTime  = fStartTime - 23 * 3600.0 - 59 * 60 - 59;

//    NSLog(@"%f,%f",fStartTime,fStopTime);

//    [controller startRealPlayWithCamera:@"4" accessToken:@"at.7l17ok9n9m1bbdizdanfgfsq2cbfudjf-706hn8h9xj-16gwwpl-gkxg61tvx" inView:_videoView];

    [controller startPlaybackWithCamera:@"4" accessToken:@"at.7l17ok9n9m1bbdizdanfgfsq2cbfudjf-706hn8h9xj-16gwwpl-gkxg61tvx" fromTime:fStopTime toTime:fStartTime inView:_videoView];
}

-(void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value
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
