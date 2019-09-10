   
# 注意
Entity里不是缺少get、set方法，Eclipse、IDEA请先安装lombok插件
 
## 技术选型
* 1 后端使用技术
    * 1.1 springframework4.3.7.RELEASE
    * 1.2 mybatis3.1.0、MyBatis-Plus 3.1.0
    * 1.3 shiro1.3.2
    * 1.4 servlet3.1.0
    * 1.5 druid1.0.28
    * 1.6 slf4j1.7.19
    * 1.7 fastjson1.2.30
    * 1.8 poi3.15
    * 1.9 velocity1.7
    * 1.10 quartz2.2.3
    * 1.11 mysql5.1.39
    * 1.12 swagger2.4
    * 1.13 j2cache2.3.22-release
    * 1.14 weixin-java-mp3.2.0
    * 1.15 MybatisPlus3.1.0
    * 1.16 lombok
        
* 2 前端使用技术
    * 2.1 Vue2.5.1
    * 2.2 iview
    * 2.3 layer3.0.3
    * 2.4 jquery2.2.4
    * 2.5 bootstrap3.3.7
    * 2.6 jqgrid5.1.1
    * 2.7 ztree3.5.26
    * 2.8 froala_editor1.2.2

## 项目结构
~~~

|--platform-admin 后台管理
|--platform-api 微信小程序商城api接口
|--platform-common 公共模块
|--platform-yinwei 系统WEB合并，请打包发布此项目
|--yinwei-wxmall 微信小程序商城

~~~


## 安装教程

* 配置环境（推荐jdk1.8、maven3.3、tomcat8、mysql5.7、redis4.0.1）
* 创建数据库
* 依次初始化sql脚本 
    * /_sql/platform-shopmall.sql
    
* 导入项目到IDE中 
* 修改配置文件 /platform-admin/src/main/resources/dev/platform.properties
    * jdbc.url
    * jdbc.username
    * jdbc.password
    * wx.appId
    * wx.secret
    * wx.mchId
    * wx.paySignKey
    * wx.notifyUrl
    * sms.validIp
    * mp.appId
    * mp.secret
    * mp.token
    * mp.aesKey
* 修改配置文件 /platform-admin/src/main/resources/j2cache.properties
    * redis.hosts
    * redis.password
* 启动后台项目（参照<a href="#doc">开发文档</a>）
* 打开微信开发者工具
* 导入 /yinwei-wxmall填写appId
* 修改 /yinwei-wxmall/config/api.js里API_BASE_URL的值
* 启动项目后默认访问路径
    * http://localhost:8080/platform-yinwei/login.html
	账户：admin  密码：admin

    
    


