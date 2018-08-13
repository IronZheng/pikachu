import com.luway.pikachu.core.annotations.CssPath;
import com.luway.pikachu.core.annotations.MathUrl;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MathUrl(url = "https://movie.douban.com/cinema/nowplaying/hangzhou/", method = MathUrl.Method.GET)
public class TestBean {

//    @Xpath(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div/div/ul/li/node()")
//    private String img;

    @CssPath(selector = "#nowplaying > .mod-bd>ul >li>ul> .stitle")
    private String title;

}
