//
//  menuView.h
//  foodsecurity
//
//  Created by wangweiyi on 15/3/24.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol sendButtonClickEvent <NSObject>

-(void)accountManagerClick;

@end

@interface menuView : UIView

@property(nonatomic,assign)id <sendButtonClickEvent> delegate;

@end
