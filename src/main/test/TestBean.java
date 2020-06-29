import com.luway.pikachu.core.annotations.CssPath;
import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.annotations.Xpath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MatchUrl(url = "https://hz.lianjia.com/ershoufang/xihu/pg2/", method = MatchUrl.Method.GET, loadJs = true)
public class TestBean {

    public static String s = "qwe";
//    @Xpath(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div/div/ul/li/node()")
//    private String img;

    @CssPath(selector = "body > div.content > div.leftContent > ul > li > div.info.clear > div.title > a")
    private String title;

    @Xpath(xpath = "body > div.content > div.leftContent > ul > li > div.info.clear > div.priceInfo > div > span")
    private String price;


    public static void main(String[] args) {
        Object w2 = null;
        String s = (String) w2;
        System.out.println(s);
        List<String> w = new ArrayList<>();
        w.add("@");
        w = s(w);
        System.out.println(w);
    }

    public static List<String> s(List<String> w) {
        w.add("2");
        return w;
    }
}
