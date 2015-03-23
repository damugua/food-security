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

    if (_cellphoneNumber.text==nil&&_password.text) {
        //非空警告
        NSLog(@"error");
    }



    [self loginConnect];

}

-(void)loginConnect
{
    //参数后缀
    NSString *paramater = [NSString stringWithFormat:@"?phone=%@&password=%@",_cellphoneNumber.text,_password.text];
    //地址
    NSString *url = [NSString stringWithFormat:@"%@%@%@",[[NSUserDefaults standardUserDefaults] objectForKey:@"url"],LOGIN,paramater];

    NSLog(@"%@",url);

    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    [manager GET:url parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        model = [[loginModel alloc]init];
        [model setModel:responseObject];
        //返回处理
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {

    }];


}

//返回处理
-(void)dealResponse
{

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
