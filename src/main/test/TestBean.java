import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MathUrl(url = "http://www.baidu.com", method = MathUrl.Method.GET)
public class TestBean {

    @CssPath(selector = "div>h1")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
