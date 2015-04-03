//
//  loginModel.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/23.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "loginModel.h"

@implementation loginModel




-(void)setParameter:(NSDictionary *)userData
{
    //存住用户名密码
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:[userData objectForKey:@"Phone"] forKey:@"username"];

    [defaults setObject:[userData objectForKey:@"Code"] forKey:@"userCode"];
    _statue = [userData objectForKey:@"Status"];
    
    [defaults setObject:[userData objectForKey:@"Name"] forKey:@"Name"];//名字
    [defaults setObject:[userData objectForKey:@"Relation"] forKey:@"Relation"];//关系
    [defaults setObject:[userData objectForKey:@"Address"] forKey:@"Address"];//地址
    [defaults setObject:[userData objectForKey:@"AccreditTime"] forKey:@"AccreditTime"];//注册时间

}

@end
