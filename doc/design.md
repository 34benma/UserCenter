# UserCenter

## 模块说明
用户中心，承担用户注册，登录和登录态的管理。需要维护的用户信息有：
1. Id，用户唯一识别码
2. 用户昵称
3. 性别
4. 密码
5. 手机号码
6. 可用邮箱
7. 用户注册登录方式
8. 用户最后登录时间
9. 用户状态

用户性质管理
用户登录时间，登录IP，登录方式信息存储。按照用户尾号100取余存库
用于后期用户活跃度和用户行为追踪
用户权限管理，比如被拉黑，被禁言等，单独成表存储

## 功能分解
1. 注册登录管理，注册方式支持微信，QQ，新浪微博，Github
2. 登录态管理，登录态校验，将登录态存储到redis

## 模块依赖
用户中心是中心模块，其他所有模块应该依赖该模块。
中心数据直接Redis持久化（GemFile）

## 数据模型
数据库名：miai_usercenter0
数据库表：
用户基本信息表
```
userinfo0 {
    id, bigint(20),
    username,varchar(50),
    gender,tinyint(2) comment '0 male 1 female'
    password,varchar(64),
    tel,varchar(12),
    email,varchar(32),
    l_from,tinyint(2) comment '0 register, 1 QQ 2 wechat, 3 sina 4 github'
   update_time,varchar(30)
}
```

```
userstatus0 {
   id,bigint(20),
   is_del,tinyint(2) comment '0 false, 1 true',
   is_black,tinyint(2) comment '0 false, 1 true',
   ban_say,tinyint(2) comment '0 false, 1 true'
   ext1,varchar(10) comment '保留字段'
   ext2,varchar(10) comment '保留字段'
}
```

## 接口解析

1 注册接口 register

2 登录接口 login

3 查询接口 query/{id}

4 修改接口 update

5 状态更改接口 status
