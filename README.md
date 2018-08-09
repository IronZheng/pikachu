# pikachu
皮卡丘，就决定是你了

为什么取个名字叫皮卡丘，大概是这样萌一些。小哥哥是很可爱的。然后本项目是个爬虫项目，使用时候就像派出小精灵一样，派出皮卡丘，就会为你抓回对应的数据。

使用注解的方式，定义数据源。希望pikachu可以作为很好的底层，去支撑开发者的业务系统。

持续开发中。。

### 1.0.1 升级版来啦
升级版中做了对xpath和css select的注解支持。同时优化了核心处理逻辑。使得任务的安排更加有序。
同时项目已经发布至中央仓库，可以直接添加依赖,即可快速开发。

```$xml
        <dependency>
            <groupId>cn.luway</groupId>
            <artifactId>pikachu</artifactId>
            <version>1.0.1-sonatype</version>
        </dependency>
```
正打算推出几个小例子，方便大家更好的使用pikachu。
还有很多地方需要优化。


### 1.0.0 版本 第一版

第一版其实没有太多东西，非常简单地封装了下爬虫引擎和抓取对象bean。存在很多的不足，需要改进。也欢迎大家给我多提点意见。

欢迎提交issues或者给我发邮件。

## 使用方式
安装Java环境，clone 代码 
```$xslt
git clone https://gitee.com/ironzheng/pikachu.git
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
    }
}
```

### 几个小例子

后续写一些小例子，给大家示范一下一些站点数据的抓取。数据抓取仅限学习，不可用于商业目的。
