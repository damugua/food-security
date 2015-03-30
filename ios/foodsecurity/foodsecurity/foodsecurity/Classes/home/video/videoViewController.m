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

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    [self getCameraList];
    [self playTheVideo];
}

-(void)getCameraList
{
    NSDictionary *parameters = @{@"parentCode":[[NSUserDefaults standardUserDefaults] objectForKey:@"userCode"]};
    commonModel *cameraConnect = [[commonModel alloc]initWithUrl:BASE_URL getPath:CAMERA_LIST parameters:parameters];
    cameraConnect.delegate = self;
}

-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect
{
    NSArray *cameraList = [dataDic objectForKey:@"Cameras"];
    NSLog(@"%@",cameraList);

}

//at.7j26kiaj8glny81n96o40bne6ccp2dqj-1sb6qmatf6-1ntulkv-thg5xpdsz

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect
{
    
}

-(void)connectError:(commonModel *)connect
{

}


-(void)playTheVideo
{
    YSPlayerController *controller = [[YSPlayerController alloc]initWithDelegate:self];

    [controller startPlaybackWithCamera:@"4" accessToken:@"at.7j26kiaj8glny81n96o40bne6ccp2dqj-1sb6qmatf6-1ntulkv-thg5xpdsz" fromTime:12543253 toTime:3254362 inView:_videoView];
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
