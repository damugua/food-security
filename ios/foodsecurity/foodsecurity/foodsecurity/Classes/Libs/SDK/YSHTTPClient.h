//
//  YSHTTPClient.h
//  EzvizRealPlay
//
//  Created by zhengwen zhu on 7/10/14.
//  Copyright (c) 2014 yudan. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  http 请求回调
 *
 *  @param responseObject json 字符串
 *  @param error          网络异常错误
 *
 *  @since v1.0.0.0
 */
typedef void (^ComplitionBlock)(id responseObject, NSError *error);

@class YSAlarmSearchInfo;

@interface YSHTTPClient : NSObject

/**
 *  网络请求单例
 *
 *  @return 单例
 *
 *  @since v1.0.0.0
 */
+ (YSHTTPClient *)sharedInstance;

/**
 *  设置app 信息
 *
 *  @param appKey    appkey
 *  @param appSecret appsecret
 */
- (void)setClientAppKey:(NSString *)appKey secret:(NSString *)appSecret;

/**
 *  设置客户端访问令牌
 *
 *  @param token 令牌字符串
 *
 *  @since v1.0.0.0
 */
- (void)setClientAccessToken:(NSString *)token;

/**
 *  请求摄像头列表
 *
 *  @param pageNo 开始请求页
 *  @param size   单页大小
 *  @param block  块回调
 *
 *  @since v1.0.0.0
 */
- (void)requestSearchCameraListPageFrom:(NSInteger)pageNo pageSize:(NSInteger)size complition:(ComplitionBlock)block;

/**
 *  请求报警录像列表
 *
 *  @param cameraId 摄像头id
 *  @param info     报警搜索参数对象
 *  @param pageNo   开始请求页
 *  @param size     单页大小
 *  @param block    块回调
 *
 *  @since v1.0.0.0
 */
- (void)requestSearchAlarmListWithCameraId:(NSString *)cameraId
                           alarmSearchInfo:(YSAlarmSearchInfo *)info
                                  pageFrom:(NSInteger)pageNo
                                  pageSize:(NSInteger)size
                                complition:(ComplitionBlock)block;

/**
 *  请求云服务器上面的录像列表
 *
 *  @param cameraId  摄像头id
 *  @param startTime 查询开始时间
 *  @param endTime   查询结束时间
 *  @param pageNo    开始请求页
 *  @param size      单页大小
 *  @param block     块回调
 *
 *  @since v1.0.0.0
 */
- (void)requestSearchCloudRecordListWithCameraId:(NSString *)cameraId
                                        timeFrom:(NSString *)startTime
                                              to:(NSString *)endTime
                                        pageFrom:(NSInteger)pageNo
                                        pageSize:(NSInteger)size
                                    complication:(ComplitionBlock)block;

/**
 *  请求从用户列表中删除摄像头
 *
 *  @param cameraId 摄像头id
 *  @param block    块回调
 *
 *  @since v1.0.0.0
 */
- (void)requestDeleteDeviceWithDeviceId:(NSString *)deviceId complition:(ComplitionBlock)block;

/**
 *  获取指定录像uuid 的抓图
 *
 *  @param recordUUID 录像 uuid
 *  @param pixel      图片宽度, 合法值区间(0- 1280]像素
 *  @param block      如果成功, 返回UIImage对象
 */
- (void)requestGetDevicePictureWithUUID:(NSString *)recordUUID
                             imageWidth:(NSInteger)pixel
                           complication:(ComplitionBlock)block;

/**
 *  请求短信验证码
 *
 *  @param signString 平台获取的签名字符串
 *  @param block      请求成功返回短信验证码， 失败返回错误信息
 */
- (void)requestGetSMSVerificationCodeWithSign:(NSString *)signString
                                 complication:(ComplitionBlock)block;

/**
 *  校验用户短信验证码
 *
 *  @param theType 标识请求的用途， 1: 帐号绑定获取用户accessToken, 2: 硬件特征码校验
 *  @param uid     用户标识符id
 *  @param no      用户手机号码
 *  @param code    短信验证码
 *  @param block   校验成功返回 200， 失败返回错误码
 */
- (void)requestCheckSMSVerificationCodeWithType:(NSInteger)theType
                                         userId:(NSString *)uid
                                    phoneNumber:(NSString *)no
                               verificationCode:(NSString *)code
                                   complication:(ComplitionBlock)block;
/**
 *  获取视频广场栏位
 *
 *  @param block 查询成功, 回调视频广场栏目信息
 */
- (void)requestSquareColumnWithComplication:(ComplitionBlock)block;

/**
 *  根据栏位获取视频列表
 *
 *  @param channel 广场频道
 *  @param pageNo  开始页
 *  @param size    页大小
 *  @param block   查询成功, 回调广场视频信息
 */
- (void)requestSquareVideoListWithChannel:(NSInteger)channel
                                 pageFrom:(NSInteger)pageNo
                                 pageSize:(NSInteger)size
                             complication:(ComplitionBlock)block;

@end


#pragma mark - YSAlarmSearchInfo

@interface YSAlarmSearchInfo : NSObject

@property (nonatomic, copy) NSString *startTime;     // 报警查询开始时间 时间格式为：2013-09-05 09:38:48
@property (nonatomic, copy) NSString *endTime;       // 报警查询结束时间 时间格式为：2013-09-05 09:38:48
@property (nonatomic, assign) NSInteger alarmType;   // 报警类型
@property (nonatomic, copy) NSString *status;        // 报警消息状态

@end