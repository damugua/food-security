//
//  cookBookModel.h
//  foodsecurity
//
//  Created by fuyang on 15/3/30.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface cookBookModel : NSObject
@property(nonatomic,strong)NSMutableArray *RecipesArray;
-(void)setParameter:(NSDictionary *)cookBookData;

@end
