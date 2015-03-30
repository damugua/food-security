//
//  commonModel.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/26.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "commonModel.h"
#import "AFHTTPClient.h"
#import "AFJSONRequestOperation.h"

@implementation commonModel


//get
-(id)initWithUrl:(NSString *)url getPath:(NSString *)path parameters:(NSDictionary *)parameters
{
    if (self = [super init]) {
        [self getTheDataWithUrl:url getPath:path parameters:parameters];
    }
    return self;

}

-(void)getTheDataWithUrl:(NSString *)url getPath:(NSString *)path parameters:(NSDictionary *)parameters
{

    NSURL *baseURL = [NSURL URLWithString:url];
    AFHTTPClient *client = [[AFHTTPClient alloc] initWithBaseURL:baseURL];

    [client registerHTTPOperationClass:[AFJSONRequestOperation class]];
    [client setDefaultHeader:@"Accept" value:@"text/html"];

    [client getPath:path parameters:parameters success:^(AFHTTPRequestOperation*operation, id responseObject) {
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
    }failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"%@",error);
        [self.delegate connectError:self];

    }];



}

//post
-(id)initWithUrl:(NSString *)url postpath:(NSString *)path parameters:(NSDictionary *)parameter
{
    if (self = [super init]) {
        [self postTheDataWithUrl:url path:path parameters:parameter];
    }
    return self;
}

-(void)postTheDataWithUrl:(NSString *)url path:(NSString *)path parameters:(NSDictionary *)parameter
{

    NSURL *baseURL = [NSURL URLWithString:url];
    AFHTTPClient *client = [[AFHTTPClient alloc] initWithBaseURL:baseURL];

    [client registerHTTPOperationClass:[AFJSONRequestOperation class]];
    [client setDefaultHeader:@"Accept" value:@"text/html"];
    [client postPath:path parameters:parameter success:^(AFHTTPRequestOperation*operation, id responseObject) {
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

    }failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        [self.delegate connectError:self];
    }];
}







@end
