//
//  loginModel.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/23.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "loginModel.h"

@implementation loginModel


-(void)setModel:(NSData *)data
{
    NSDictionary * dataDic = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];

    if ([[dataDic objectForKey:@"Data"] isKindOfClass:[NSNull class]]) {
        NSData *errorData = [[dataDic objectForKey:@"ResponseInstance"] dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *errorDic = [NSJSONSerialization JSONObjectWithData:errorData options:NSJSONReadingMutableContainers error:nil];
        NSString *errorMessage = [[errorDic objectForKey:@"BusinessExceptionInstance"] objectForKey:@"Message"];
        NSLog(@"%@",errorMessage);
    }else{
        NSData *data = [[dataDic objectForKey:@"Data"] dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *userData = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];
        NSLog(@"%@",userData);
        [self setParameter:userData];
    }
}

-(void)setParameter:(NSDictionary *)userData
{
    //存住用户名密码
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:[userData objectForKey:@"Phone"] forKey:@"username"];


    _statue = [userData objectForKey:@"Status"];

}

@end
