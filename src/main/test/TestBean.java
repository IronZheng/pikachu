import com.luway.pikachu.core.annotations.CssPath;
import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.annotations.Xpath;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MatchUrl(url = "https://hz.lianjia.com/ershoufang/xihu/pg2/", method = MatchUrl.Method.GET)
public class TestBean {

//    @Xpath(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div/div/ul/li/node()")
//    private String img;

    @CssPath(selector = "body > div.content > div.leftContent > ul > li > div.info.clear > div.title > a")
    private String title;

    @Xpath(xpath = "body > div.content > div.leftContent > ul > li > div.info.clear > div.priceInfo > div > span")
    private String price;

}
