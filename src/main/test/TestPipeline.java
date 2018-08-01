import com.pikachu.core.pipeline.Pipeline;

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
    }
}
