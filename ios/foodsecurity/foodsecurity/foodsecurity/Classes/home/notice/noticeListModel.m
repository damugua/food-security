//
//  noticeListModel.m
//  foodsecurity
//
//  Created by fuyang on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "noticeListModel.h"
#import "NoticesModel.h"
@implementation noticeListModel

-(void)setParameter:(NSDictionary *)noticeListData
{
    self.noticesArray=[[NSMutableArray alloc]init];
    NSLog(@"noticeListData %@",noticeListData);
    NSArray *noticeArray=[noticeListData valueForKey:@"Notices"];
    NSLog(@"noticeArray %@",noticeArray[0]);
    for (int i=0; i<noticeArray.count;i++) {
        
        NSDictionary *noticeDict=[noticeArray objectAtIndex:i];
        self.noticesModel=[[NoticesModel alloc]init];
        self.noticesModel.Category=[noticeDict valueForKey:@"Category"];
        self.noticesModel.Content=[[noticeDict valueForKey:@"Content"] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        self.noticesModel.Id=[noticeDict valueForKey:@"Id"];
        self.noticesModel.Title=[[noticeDict valueForKey:@"Title"] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        self.noticesModel.Time=[noticeDict valueForKey:@"Time"];
        self.noticesModel.TotalCount=[noticeDict valueForKey:@"TotalCount"];
        NSLog(@"sdfd  %@",self.noticesModel.Content);
        [self.noticesArray addObject:self.noticesModel];
    }
}
@end
