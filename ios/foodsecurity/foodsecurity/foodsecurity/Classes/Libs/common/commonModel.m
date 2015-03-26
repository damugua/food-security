//
//  commonModel.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/26.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "commonModel.h"
#import "AFNetworking.h"



@implementation commonModel


//get
-(id)initWithUrl:(NSString *)url
{
    if (self = [super init]) {
        [self getTheDataWithUrl:url];
    }
    return self;

}

-(void)getTheDataWithUrl:(NSString *)url
{
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];

    [manager GET:url parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSDictionary * dataDic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableContainers error:nil];

        if ([[dataDic objectForKey:@"Data"] isKindOfClass:[NSNull class]]) {
            NSData *errorData = [[dataDic objectForKey:@"ResponseInstance"] dataUsingEncoding:NSUTF8StringEncoding];
            NSDictionary *errorDic = [NSJSONSerialization JSONObjectWithData:errorData options:NSJSONReadingMutableContainers error:nil];
            NSString *errorMessage = [[errorDic objectForKey:@"BusinessExceptionInstance"] objectForKey:@"Message"];
            [self.delegate gotTheErrorMessage:errorMessage and:self];
        }else{
            NSData *data = [[dataDic objectForKey:@"Data"] dataUsingEncoding:NSUTF8StringEncoding];
            NSDictionary *dataDic = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];
            [self.delegate gotTheData:dataDic and:self];
        }
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self.delegate connectError:self];
    }];
}

//post
-(id)initWithUrl:(NSString *)url parameters:(NSDictionary *)parameter
{
    if (self = [super init]) {
        [self getTheDataWithUrl:url parameters:parameter];
    }
    return self;
}

-(void)getTheDataWithUrl:(NSString *)url parameters:(NSDictionary *)parameter
{
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];

    [manager POST:url parameters:parameter success:^(AFHTTPRequestOperation *operation, id responseObject)
    {
        NSDictionary * dataDic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableContainers error:nil];

        if ([[dataDic objectForKey:@"Data"] isKindOfClass:[NSNull class]]) {
            NSData *errorData = [[dataDic objectForKey:@"ResponseInstance"] dataUsingEncoding:NSUTF8StringEncoding];
            NSDictionary *errorDic = [NSJSONSerialization JSONObjectWithData:errorData options:NSJSONReadingMutableContainers error:nil];
            NSString *errorMessage = [[errorDic objectForKey:@"BusinessExceptionInstance"] objectForKey:@"Message"];
            [self.delegate gotTheErrorMessage:errorMessage and:self];
        }else{
            NSData *data = [[dataDic objectForKey:@"Data"] dataUsingEncoding:NSUTF8StringEncoding];
            NSDictionary *dataDic = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];
            [self.delegate gotTheData:dataDic and:self];
        }
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self.delegate connectError:self];

    }];
}




@end
