##oauth2-demo

### 用demo介绍三种oauth2生成token的方式
  在AuthorizationServerConfigurer中，该类继承自AuthorizationServerConfigurerAdapter类，重写configure(ClientDetailsServiceConfigurer)；在此方法中定义三个client；详见代码
  
  
  1.client_1为authorization_code（授权码模式）
    通过链接 http://localhost:7779/oauth/authorize?grant_type=authorization_code&client_id=client_1&redirect_uri=http://www.baidu.com&response_type=code 页面会跳转到redirect_uri，并且会附带一个code；如果未登录的用户，会先跳转到登录页面后，并提示是否授权后；返回结果链接：https://www.baidu.com/?code=uW3kix 将code值与其他指定参数生成token。
     ![image](https://github.com/chenluxing/oauth2-demo/blob/master/src/main/resources/static/images/authorization_code.png)
  2.client_2为password（密码模式）
     ![image](https://github.com/chenluxing/oauth2-demo/blob/master/src/main/resources/static/images/password.png)
  3.client_3为client_credentials（客户端模式）
     ![image](https://github.com/chenluxing/oauth2-demo/blob/master/src/main/resources/static/images/client_credentials.png)