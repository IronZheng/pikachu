package com.luway.pikachu.jobs;

import com.luway.pikachu.common.SnowFlakeUtil;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.exception.SimpleException;
import com.luway.pikachu.core.worker.BathWorker;
import com.luway.pikachu.core.worker.GeneralWorker;
import com.luway.pikachu.core.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午3:48 2018/8/22
 */

public class PikachuJobManage {

    private ScheduledExecutorService taskPool;
    private Pikachu pikachu;
    private final static Logger logger = LoggerFactory.getLogger(PikachuJobManage.class);

    public PikachuJobManage(Pikachu pikachu) {
        this.pikachu = pikachu;
        this.taskPool = new ScheduledThreadPoolExecutor(2);
    }

    /**
     * 注册定时任务
     *
     * @param worker
     * @param startTime
     * @param space
     * @param unit
     * @return
     */
    public boolean regiest(Worker worker, Long startTime, Long space, TimeUnit unit) throws Exception {
        try {
            String id = SnowFlakeUtil.getId();
            logger.info("注册定时任务，id为【{}}】", id);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (worker instanceof GeneralWorker) {
                        pikachu.regist((GeneralWorker) worker);
                    }
                    if (worker instanceof BathWorker) {
                        pikachu.regist((BathWorker) worker);
                    }
                }
            };
            taskPool.scheduleAtFixedRate(task, startTime, space, unit);
            return true;
        } catch (Exception e) {
            throw new Exception("regiest error", e);
        }
    }

    /**
     * 定时运行已注册任务
     * 注意这里如果预先注册的定时任务被删除或者被修改，可能会导致无法运行，或者与预期结果不一致情况。
     * 请做好检查。
     *
     * @param workerId
     * @param startTime
     * @param space
     * @param unit
     * @return
     */
    public boolean runScheduleJob(String workerId, Long startTime, Long space, TimeUnit unit) throws Exception {
        try {
            String id = SnowFlakeUtil.getId();
            logger.info("注册定时任务，id为【{}}】", id);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    pikachu.runWorkId(workerId);
                }
            };
            taskPool.scheduleAtFixedRate(task, startTime, space, unit);
            return true;
        } catch (Exception e) {
            throw new Exception("runScheduleJob error", e);
        }
    }


    /**
     * 关闭定时任务线程池，这个会导致所有任务直接结束。
     *
     * @return
     */
    public boolean shutdown() {
        try {
            taskPool.shutdown();
        } catch (Exception e) {
            throw new SimpleException(e);
        }
        return true;
    }
}
