import com.luway.pikachu.core.annotations.CssPath;
import com.luway.pikachu.core.annotations.MatchUrl;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 2:15 PM 2018/12/14
 */

@MatchUrl(url = "https://juejin.im/timeline/frontend", method = MatchUrl.Method.GET)
public class JuejinTest {


    @CssPath(selector = "#juejin > div.view-container > main > div > div > ul")
    private String title;
}
