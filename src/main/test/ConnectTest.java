import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.engine.impl.PikachuImpl;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;


/**
 * @author : iron
 * @version : 1.0.0
 * @date : 4:29 PM 2018/11/8
 */

public class ConnectTest {

    @Test
    public void test() throws IOException {

        Pikachu pikachu = new PikachuImpl("test")
                .setCoreNum(10)
                .setMaxThreadNum(20)
                .init();
        Document document = pikachu.getConnect("http://www.baidu.com",MatchUrl.Method.GET);
        System.out.println(document);
    }
}
