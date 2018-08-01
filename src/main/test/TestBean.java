import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;

import java.util.List;

/**
 * @author zhenggm
 * @create 2018-04-24 17:12
 **/

@MathUrl(url = "http://www.meizitu.com/tag/quanluo_4_1.html", method = MathUrl.Method.GET)
public class TestBean {

    @CssPath(selector = "#maincontent > div.inWrap > ul>li>div>div>a")
    private List img;

    public List getTitle() {
        return img;
    }

    public void setTitle(List list) {
        this.img = list;
    }
}
