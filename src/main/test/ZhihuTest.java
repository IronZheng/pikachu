import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.engine.impl.PikachuImpl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 1:41 PM 2019/1/30
 */

public class ZhihuTest {
    @Test
    public void test() throws IOException {
        Pikachu pikachu = new PikachuImpl("sn")
                .setCoreNum(3)
                .setMaxThreadNum(5)
                .init();
        pikachu.start();
        Map<String, String> map = new HashMap<>();
        map.put("Cookie", "_zap=c2eebb1b-506b-4ee3-8e57-6770ab17a3f8; d_c0=\"AHBkOCQl9A2PTuugLyiB0LWjKlRzhTt3TT8=|1532502598\"; z_c0=\"2|1:0|10:1537622636|4:z_c0|92:Mi4xWHl4dUFBQUFBQUFBY0dRNEpDWDBEU1lBQUFCZ0FsVk5iSkNUWEFEQWxBMnotMWNVanVCUXBzMks3S0VaZnBwYmdn|12e210b9cd2990d6f66c77f2615dc5aba01269d360b0a5980b14ed37f9ff14aa\"; __gads=ID=44f038b685bda33f:T=1539571274:S=ALNI_MaPpo9PbGt3PxzDZnB6abo0IXj6nw; _ga=GA1.2.1921219534.1534831769; q_c1=2a01d7ab7ae741a8aae09302e4d79994|1546483001000|1537853125000; __utma=155987696.1921219534.1534831769.1546516389.1546516389.1; __utmz=155987696.1546516389.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _xsrf=o71F0Rh2WkByUMqNezqOC0vowghao0GP; tgw_l7_route=73af20938a97f63d9b695ad561c4c10c; tst=h");
        Document document = pikachu.getConnect("https://www.zhihu.com/hot", MatchUrl.Method.GET, map);
        Elements elements = document.select("#TopstoryContent > div > div > section > div.HotItem-content");
        for (Element e : elements) {
            String title = e.select("a > h2").text();
            String href = e.select("a").attr("href");
            String hot = e.select("div > div").first().text();
            System.out.println(title+hot.replace("分享",""));
        }
    }



    @Test
    public void test2() throws IOException {
        Pikachu pikachu = new PikachuImpl("sn")
                .setCoreNum(3)
                .setMaxThreadNum(5)
                .init();
        pikachu.start();
        Map<String, String> map = new HashMap<>();
//        map.put("Cookie", "_zap=c2eebb1b-506b-4ee3-8e57-6770ab17a3f8; d_c0=\"AHBkOCQl9A2PTuugLyiB0LWjKlRzhTt3TT8=|1532502598\"; z_c0=\"2|1:0|10:1537622636|4:z_c0|92:Mi4xWHl4dUFBQUFBQUFBY0dRNEpDWDBEU1lBQUFCZ0FsVk5iSkNUWEFEQWxBMnotMWNVanVCUXBzMks3S0VaZnBwYmdn|12e210b9cd2990d6f66c77f2615dc5aba01269d360b0a5980b14ed37f9ff14aa\"; __gads=ID=44f038b685bda33f:T=1539571274:S=ALNI_MaPpo9PbGt3PxzDZnB6abo0IXj6nw; _ga=GA1.2.1921219534.1534831769; q_c1=2a01d7ab7ae741a8aae09302e4d79994|1546483001000|1537853125000; __utma=155987696.1921219534.1534831769.1546516389.1546516389.1; __utmz=155987696.1546516389.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _xsrf=o71F0Rh2WkByUMqNezqOC0vowghao0GP; tgw_l7_route=73af20938a97f63d9b695ad561c4c10c; tst=h");
        Document document = pikachu.getConnect(" https://movie.douban.com/subject_search?search_text=%E9%9C%B8%E7%8E%8B%E5%88%AB%E5%A7%AC&cat=1002", MatchUrl.Method.GET);
        Elements elements = document.select("#TopstoryContent > div > div > section > div.HotItem-content");
        for (Element e : elements) {
            String title = e.select("a > h2").text();
            String href = e.select("a").attr("href");
            String hot = e.select("div > div").first().text();
            System.out.println(title+hot.replace("分享",""));
        }
    }

}
