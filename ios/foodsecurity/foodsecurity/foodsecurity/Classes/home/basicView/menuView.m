//
//  menuView.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/24.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "menuView.h"

@implementation menuView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/


-(instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self createUI:frame];
        self.backgroundColor = [UIColor blackColor];
    }
    return self;
}

-(void)createUI:(CGRect)frame
{
    UIButton * accountManager = [[UIButton alloc]initWithFrame:CGRectMake(0, 10, frame.size.width, (frame.size.width-10)/2)];
    [accountManager setTitle:@"账户管理" forState:UIControlStateNormal];
    [accountManager addTarget:self action:@selector(accountManagerClick) forControlEvents:UIControlEventTouchUpInside];
    accountManager.showsTouchWhenHighlighted = YES;
    [self addSubview:accountManager];
}

-(void)accountManagerClick
{
    [self.delegate accountManagerClick];
}


@end
