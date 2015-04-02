//
//  noticeListModel.h
//  foodsecurity
//
//  Created by fuyang on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NoticesModel.h"
@interface noticeListModel : NSObject

@property(nonatomic,strong)NSMutableArray *noticesArray;
@property(nonatomic,strong)NoticesModel *noticesModel;


-(void)setParameter:(NSDictionary *)noticeListData;
@end
