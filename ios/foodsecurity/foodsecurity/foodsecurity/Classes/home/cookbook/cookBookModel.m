//
//  cookBookModel.m
//  foodsecurity
//
//  Created by fuyang on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "cookBookModel.h"
#import "DayCookBookModel.h"
@implementation cookBookModel

-(void)setParameter:(NSDictionary *)cookBookData
{
    NSLog(@"cookBookData %@",cookBookData);
    NSArray *cookBookArray=[cookBookData valueForKey:@"Recipes"];
    self.RecipesArray=[[NSMutableArray alloc]init];
    for (int i=0; i<cookBookArray.count;i++) {
        DayCookBookModel *dayCookBookModel=[[DayCookBookModel alloc]init];
        NSDictionary *noticeDict=[cookBookArray objectAtIndex:i];
        NSString *str=[[noticeDict valueForKey:@"Explain"] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        NSLog(@"cook book  str %@",str);
        dayCookBookModel.Explain=str;
        [self.RecipesArray addObject:dayCookBookModel];
    }
}


@end
