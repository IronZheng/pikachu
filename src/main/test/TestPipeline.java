import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.GeneralWorker;

import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午5:03 2018/8/1
 */

public class TestPipeline extends BasePipeline<TestBean> {


    public TestPipeline(TestBean testBean) {
        super(testBean);
    }

    @Override
    public void output(Map<String, Object> result,String url) {
//        NodeList nodeList = (NodeList) result.get("img");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            /**
//             * Node.getTextContent() 此属性返回此节点及其后代的文本内容。
//             * Node.getFirstChild()  此节点的第一个子节点。
//             * Node.getAttributes() 包含此节点的属性的 NamedNodeMap（如果它是 Element）；否则为 null
//             * 如果想获取相应对象的相关属性，可以调用  getAttributes().getNamedItem("属性名") 方法
//             */
//            System.out.println(
//                    node.getNodeValue() == null ? node.getTextContent() : node.getNodeValue());
//
//        }
        String title = (String) result.get("title");
        System.out.println(title);

        String price = (String) result.get("price");
        System.out.println(url);

//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        GeneralWorker worker = new GeneralWorker("1",TestBean.class).addPipeline(new TestPipeline(new TestBean()));
//        regiestWorker(worker);
    }

}
