package com.example.demo.service;

import com.example.demo.synchronization.CustomThread;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.Future;

/**
 * @author Saksham
 */
@Service
public class APIService {

    private final TaskExecutor taskExecutor;

    private final ApplicationContext applicationContext;

    public APIService(TaskExecutor taskExecutor, ApplicationContext applicationContext) {
        this.taskExecutor = taskExecutor;
        this.applicationContext = applicationContext;
    }

    @Async
    public Future<String> startMethod(Integer num)
    {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().contains("custom-thread") && !t.isInterrupted()) {
                t.interrupt();
            }
        }
        CustomThread thread = applicationContext.getBean(CustomThread.class);
        thread.setN(num);
        taskExecutor.execute(thread);
        return new AsyncResult<>("Thread started with random number: " + num + " as input.");
    }


    @Async
    public Future<String> endMethod()
    {
        Thread th = null;
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().contains("custom-thread") && !t.isInterrupted()) {
                th = t;
                t.interrupt();
            }
        }
        if(!th.getName().equals("custom-thread1")) {//by default main thread custom-thread1 will always run, so first time we need to overlook this condition
            return new AsyncResult<>( th.getName() + " Thread closed");
        }
        else {
            return new AsyncResult<>( "No existing Thread");
        }

    }
}
