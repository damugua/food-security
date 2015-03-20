//
//  homeViewController.m
//  foodsecurity
//
//  Created by wangweiyi on 15/3/20.
//  Copyright (c) 2015年 com.zsgj. All rights reserved.
//

#import "homeViewController.h"
#import "AFNetworking.h"
#import "communicateViewController.h"
#import "cookbookViewController.h"
#import "videoViewController.h"

@interface homeViewController ()

@end

@implementation homeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self test];
}

-(void)test
{
    NSString *url = @"http://123.130.113.6:7000/RecipeWcfService.svc/GetInfoByKindergartenId?kindergartenId=1&pageIndex=1&pageSize=5";

    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    [manager GET:url parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {

        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableContainers error:nil];
        NSString * str = [dic objectForKey:@"Data"];
        NSData *data = [str dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *dic2 = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:nil];


        NSLog(@"%@",dic2);

    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {

    }];

}


- (IBAction)videoClick:(id)sender {
    videoViewController *video = [[videoViewController alloc]init];
    [self.navigationController pushViewController:video animated:NO];
}


- (IBAction)communicateClick:(id)sender {
    communicateViewController *communicate = [[communicateViewController alloc]init];
    [self.navigationController pushViewController:communicate animated:NO];

}

- (IBAction)cookbookClick:(id)sender {
    cookbookViewController *cookbook = [[cookbookViewController alloc]init];
    [self.navigationController pushViewController:cookbook animated:NO];

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
