# pikachu
爬虫项目

使用注解的方式，定义数据源。希望pikachu可以作为很好的底层，去支撑开发者的业务系统。

持续开发中。。

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

@CssPath 方法注解，使用XPath语法。 Xpath是一门在 XML 文档中查找信息的语言。速度比较快,是爬虫在网页定位中的较优选择。

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

