//
//  loginViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/22.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "loginViewController.h"
#import "AFNetworking.h"
#import "API.h"
#import "registOne.h"
#import "loginModel.h"
#import "homeViewController.h"
#import "commonNavigation.h"
#import <CommonCrypto/CommonDigest.h>


@interface loginViewController ()

@end

@implementation loginViewController
{
    loginModel *model;//返回信息
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self dealTheView];
}


-(void)dealTheView
{
    //界面处理

}


//键盘回调
-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [self.view endEditing:YES];
}


- (IBAction)loginClick:(id)sender {
    //创建联网

    if ([_cellphoneNumber.text isEqualToString:@""]&&[_password.text isEqualToString:@""]) {
        //非空警告
        NSLog(@"error");
        return;
    }



    [self loginConnectWithMd5Password:[self md5:_password.text]];

}

-(NSString *)md5:(NSString *)password
{
    const char *cStr = [password UTF8String];
    unsigned char result[16];
    CC_MD5( cStr, strlen(cStr), result );
    NSString *resultStr =  [NSString stringWithFormat:@"%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x",
            result[0], result[1], result[2], result[3],
            result[4], result[5], result[6], result[7],
            result[8], result[9], result[10], result[11],
            result[12], result[13], result[14], result[15]
            ];
    NSString *reStr = [resultStr substringWithRange:NSMakeRange(8, 16)];
    return reStr;
}

-(void)loginConnectWithMd5Password:(NSString *)password
{
    //参数后缀
    NSString *paramater = [NSString stringWithFormat:@"?phone=%@&password=%@",_cellphoneNumber.text,password];
    //地址
    NSString *url = [NSString stringWithFormat:@"%@%@%@",[[NSUserDefaults standardUserDefaults] objectForKey:@"url"],LOGIN,paramater];

    NSLog(@"%@",url);

    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    [manager GET:url parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        model = [[loginModel alloc]init];

        [model setModel:responseObject];
        //返回处理
        [self dealResponse];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {

    }];


}

//返回处理
-(void)dealResponse
{
    if (model.statue) {
        UIApplication *application=[UIApplication sharedApplication];
        UIWindow *window=application.keyWindow;
        homeViewController *home = [[homeViewController alloc]init];
        commonNavigation *nav = [[commonNavigation alloc]initWithRootViewController:home];
        window.rootViewController=nav;
    }
}


- (IBAction)canNotLoginClick:(id)sender {
}


- (IBAction)regist:(id)sender {
    NSLog(@"aaa");
    registOne *regist = [[registOne alloc]init];

    UINavigationController *nav = [[UINavigationController alloc]initWithRootViewController:regist];
    [self presentViewController:nav animated:YES completion:nil];
}









- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
