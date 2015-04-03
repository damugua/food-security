//
//  ToAdviceModel.h
//  foodsecurity
//
//  Created by fuyang on 15/4/3.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ToAdviceModel : NSObject

@property(nonatomic,strong)NSString *Title;//标题
@property(nonatomic,strong)NSString *Content;//内容
@property(nonatomic,strong)NSString *Complainant;//投诉方
@property(nonatomic,strong)NSString *ByComplainant;//被投诉方
//@property(nonatomic,strong)NSString *Time;//时间
//@property(nonatomic,assign)NSString *Status;//是否处理

-(void)setParameter:(NSDictionary *)toAdviceData;
@end
