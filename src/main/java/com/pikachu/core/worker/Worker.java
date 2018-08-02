package com.pikachu.core.worker;

import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.annotations.Xpath;
import com.pikachu.core.exception.SimpleException;
import com.pikachu.core.pipeline.BasePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author zhenggm
 * @create 2018-03-01 上午 11:18
 **/


public class Worker extends BaseDynamicBean {
    private final static Logger log = LoggerFactory.getLogger(Worker.class);
    private MathUrl.Method method;
    private BasePipeline pipeline;

    public Worker(String id, Class<?> bean) {
        this.id = id;
        if (bean == null) {
            throw new RuntimeException("[error] class is null");
        }
        load(bean);
    }

    private Worker load(Class<?> bean) {
        MathUrl u = bean.getAnnotation(MathUrl.class);
        log.debug(bean.getName() + "is load");
        this.url = u.url();
        if (this.url == null) {
            throw new RuntimeException("[error] url can not be null");
        }
        this.method = u.method();
        return attr(bean);
    }

    public Worker attr(Class<?> bean) {
        attr = new HashMap<>(16);
        try {
            Field[] fields = bean.getDeclaredFields();
            for (Field field : fields) {
                boolean fieldHasAnno = field.isAnnotationPresent(CssPath.class);
                if (fieldHasAnno) {
                    CssPath cssPath = field.getAnnotation(CssPath.class);
                    //输出注解属性
                    Target t = new Target(field.getName(), field.getType().toString(), cssPath.selector(), null);
                    attr.put(field.getName(), t);
                    log.debug(field.getName() + ":" + field.getType().toString() + ":" + cssPath.selector());
                }
                boolean fieldHasXpath = field.isAnnotationPresent(Xpath.class);
                if (fieldHasXpath) {
                    Xpath xpath = field.getAnnotation(Xpath.class);
                    //输出注解属性
                    Target t = new Target(field.getName(), field.getType().toString(), null, xpath.xpath());
                    attr.put(field.getName(), t);
                    log.debug(field.getName() + ":" + field.getType().toString() + ":" + xpath.xpath());
                }
            }
        } catch (Exception e) {
            log.error("attr error", e);
            throw new SimpleException(e);
        }
        return this;
    }

    public Worker addPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    @Override
    public void start() {
        if (null == pipeline) {
            throw new RuntimeException("pipeline can not be null.");
        }

    }

    @Override
    public String toString() {
        return "Worker{" +
                "method=" + method +
                ", pipeline=" + pipeline +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", attr=" + attr +
                '}';
    }


    public MathUrl.Method getMethod() {
        return method;
    }

    public void setMethod(MathUrl.Method method) {
        this.method = method;
    }

    public BasePipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
    }
}
