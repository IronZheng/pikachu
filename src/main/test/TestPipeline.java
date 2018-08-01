import com.alibaba.fastjson.JSON;
import com.pikachu.core.pipeline.Pipeline;

import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午5:03 2018/8/1
 */

public class TestPipeline extends Pipeline<TestBean> {


    public TestPipeline(TestBean testBean) {
        super(testBean);
    }

    @Override
    public void output(String json) {
        System.out.println(json);
        Map<String,Object> test = JSON.parseObject(json, Map.class);
        System.out.println(test.get("img"));
    }
}
