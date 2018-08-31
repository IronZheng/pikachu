package com.luway.pikachu.core.worker;

import com.luway.pikachu.core.pipeline.BasePipeline;

import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午5:03 2018/8/28
 */
public interface Worker {

    /**
     * 获取ID
     * @return
     */
    String getId();

    /**
     * 注册管道
     * @param pipeline
     * @return
     */
    Worker addPipeline(BasePipeline pipeline);

    /**
     *
     * @return
     */
    Boolean validate();

    /**
     *
     * @param cookies
     * @return
     */
    Worker cookies(Map<String, String> cookies);

    /**
     * 获取管道
     * @return
     */
    BasePipeline getPipeline();
}
