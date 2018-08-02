import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.annotations.Xpath;

import java.util.List;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MathUrl(url = "https://book.douban.com/", method = MathUrl.Method.GET)
public class TestBean {

    @Xpath(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div/div/ul/li/node()")
    private String img;

    @CssPath(selector = "#content > div > div.article > div.section.books-express > div.bd > div > div > ul:nth-child(2) > li > div.cover > a")
    private String title;
}
