//
//  commonModel.h
//  foodsecurity
//
//  Created by wangweiyi on 15/3/26.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol commonConnectDelegate;


@interface commonModel : NSObject


-(id)initWithUrl:(NSString *)url;
-(id)initWithUrl:(NSString *)url parameters:(NSDictionary *)parameter;

@property(nonatomic,assign)id<commonConnectDelegate> delegate;

@end


@protocol commonConnectDelegate <NSObject>

-(void)gotTheData:(NSDictionary *)dataDic and:(commonModel *)connect;

-(void)gotTheErrorMessage:(NSString *)errorMessage and:(commonModel *)connect;

-(void)connectError:(commonModel *)connect;

@end