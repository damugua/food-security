//
//  API.h
//  foodsecurity
//
//  Created by wangweiyi on 15/3/22.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface API : NSObject

#define iPhone5 ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(640, 1136), [[UIScreen mainScreen] currentMode].size) : NO)
#define iPhone4 ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(640, 960), [[UIScreen mainScreen] currentMode].size) : NO)
#define iPhone6 ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(750, 1334), [[UIScreen mainScreen] currentMode].size) : NO)
#define iPhone6p ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(1080, 1920), [[UIScreen mainScreen] currentMode].size) : NO)



//联网接口
#define BASE_URL @"http://123.130.113.6:7000"

#define LOGIN @"/ParentInfoWcfService.svc/CheckLogin"
#define GET_ACCESS_TOKEN @"/InvokeYs7ServiceWcfService.svc/GetAccessToken"
#define CAMERA_LIST @"/CameraWcfService.svc/GetCameraInfoByParentCode" //要用户id
#define GET_COOKBOOK @"RecipeWcfService.svc/GetInfoByKindergartenId" //食谱
#define GET_NOTICE_LIST @"NoticeWcfService.svc/GetByTimeAndSyjID"  //公告列表
#define POST_ADVICE @"ComplaintWcfService.svc/CreateNewComplaint" //投诉建议
@end
