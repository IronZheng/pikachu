import com.luway.pikachu.core.annotations.CssPath;
import com.luway.pikachu.core.annotations.MathUrl;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MathUrl(url = "https://limengke123.github.io/", method = MathUrl.Method.GET)
public class TestBean {

//    @Xpath(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div/div/ul/li/node()")
//    private String img;

    @CssPath(selector = "#app > div:nth-child(3) > div > div.van-tabbar-item.van-tabbar-item--active > div.van-tabbar-item__text")
    private String title;

}
