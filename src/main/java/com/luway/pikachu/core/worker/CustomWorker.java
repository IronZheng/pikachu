// Copyright 2016-2101 Pica.
package com.luway.pikachu.core.worker;

import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.annotations.WorkerType;
import com.luway.pikachu.core.exception.SimpleException;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.bean.BaseWorker;
import com.luway.pikachu.core.worker.bean.CssTypeEnum;
import com.luway.pikachu.core.worker.bean.Target;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 自定义的工人，可以自由设定worker的url和想要的属性
 *
 * @ClassName CustomWorker
 * @Author iron
 * @Date 2020/2/3 20:36
 * @ModifyDate 2020/2/3 20:36
 * @Version v.1.0
 */
public class CustomWorker extends BaseWorker implements Worker {
    private final static Logger log = LoggerFactory.getLogger(CustomWorker.class);

    public CustomWorker(String id, String url, MatchUrl.Method method) {
        this.id = id;
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("[error] url can not be null");
        }
        super.setType(WorkerType.CUSTOM);
        this.url = url;
        this.setMethod(method);
    }

    public CustomWorker(String url, MatchUrl.Method method) {
        this.id = UUID.randomUUID().toString();
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("[error] url can not be null");
        }
        super.setType(WorkerType.CUSTOM);
        this.url = url;
        this.setMethod(method);
    }

    public CustomWorker addAttr(String name, String value) {
        addAttr(name, value, CssTypeEnum.SELECTOR);
        return this;
    }

    public CustomWorker addAttr(String name, String value, CssTypeEnum type) {
        this.attr = new HashMap<>(16);
        this.attr.put(name, loadTarget(name, value, type));
        return this;
    }


    private Target loadTarget(String name, String value, CssTypeEnum type) {
        switch (type) {
            case XPATH:
                return new Target(name, null, value);
            case SELECTOR:
                return new Target(name, value, null);
            default:
                throw new SimpleException("type is unknow");
        }
    }

    @Override
    public CustomWorker addPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    @Override
    public Boolean validate() {
        if (pipeline == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public CustomWorker cookies(Map<String, String> cookies) {
        super.setCookies(cookies);
        return this;
    }

    public CustomWorker loadJs(Boolean isLoad){
        this.loadJs = isLoad;
        return this;
    }
}
