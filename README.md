# pikachu
皮卡丘，就决定是你了

为什么取个名字叫皮卡丘，大概是这样萌一些。小哥哥是很可爱的。然后本项目是个爬虫项目，使用时候就像派出小精灵一样，派出皮卡丘，就会为你抓回对应的数据。

使用注解的方式，定义数据源。希望pikachu可以作为很好的底层，去支撑开发者的业务系统。

目前版本迭代中。文档也准备着手编写了，原谅我一直比较懒。示例的例子，后续会提交上来。

JDK 版本 1.8

github：https://github.com/Steelzheng/pikachu

开源中国：https://gitee.com/ironzheng/pikachu

# 文档地址
https://www.yuque.com/zhenggangmin/pikachu

中央仓库Maven，此处版本号为最新（随时更新）
```$xml
    <dependency>
      <groupId>cn.luway</groupId>
      <artifactId>pikachu</artifactId>
      <version>1.1.5</version>
    </dependency>
```
## 1.1.2~1.1.3

补丁升级

修复定时管理只能注册常用worker，现支持两种模式。


### 1.1.1 
1.1.1 版本功能介绍，相对预览版，修正了一些BUG，提升稳定性。

优化：修改批量任务处理方式，改为多线程任务调度模式。 

### 1.1.1 预览版
重构了爬虫的核心处理逻辑，使得任务分配比之前版本更加合理，效率上也更高。由于重构了核心部分，所以对之前的版本是不兼容的。

主要优化的功能为：

1.增加cookies，可以对一些站点进行cookies参数验证，也就是模拟登陆。

2.增加一些通用接口。

3.增加定时任务。

4.增加批量处理，可以对分页地址池批量处理。

5.随机时间间隔，防止高并发对网站造成过大的压力。也防止被网站封杀。
（这个还不能彻底解决被封杀的问题，只能说一定程度缓解了高并发可能触发网站的封杀。后续版本继续考虑新的方式。）

### 1.0.2 升级版来啦
升级版中做了对xpath和css select的注解支持。同时优化了核心处理逻辑。使得任务的安排更加有序。
同时项目已经发布至中央仓库，可以直接添加依赖,即可快速开发。

正打算推出几个小例子，方便大家更好的使用pikachu。
还有很多地方需要优化。


### 1.0.0 版本 第一版

第一版其实没有太多东西，非常简单地封装了下爬虫引擎和抓取对象bean。存在很多的不足，需要改进。也欢迎大家给我多提点意见。

欢迎提交issues或者给我发邮件。


====================================================================================================
## 调试方式
安装Java环境，clone 代码 
```$xslt
git clone https://gitee.com/ironzheng/pikachuImpl.git
mvn clean install 
```

使用方式参考test中的示例

先配置好抓取目标的bean。

### 注解说明
@MathUrl 类注解，里面有两个参数，url是目标数据的url地址，请填写完善。method是请求方式。

@CssPath 方法注解，使用select语法。 

@Xpath 方法注解，使用xpath语法。Xpath是一门在 XML 文档中查找信息的语言。

两种不同的注解可以一起使用。
字段名 自定义即可。

```java
// 示例
public class TestPikachu {
    public static void main(String[] args) {
        new Pikachu("test")
                .init()
                .regist(new Worker("test", TestBean.class)
                        .addPipeline(new TestPipeline(new TestBean())))
                .start();
        
        
    // 批量URL工人    
    GeneralWorker generalWorker =  new GeneralWorker("1", TestBean.class)
                    .addPipeline(new BasePipeline(TestBean.class) {
                        @Override
                        public void output(Map result, String url) {
                            System.out.println(result);
                        }
                    });
    
    // 创建一个定时任务中心
    PikachuJobManage pikachuJobManage = new PikachuJobManage(pikachu);
    pikachuJobManage.regiest(generalWorker,1L,5L,TimeUnit.SECONDS);
 
    }
}
```

### 几个小例子

后续写一些小例子，给大家示范一下一些站点数据的抓取。数据抓取仅限学习，不可用于商业目的。
