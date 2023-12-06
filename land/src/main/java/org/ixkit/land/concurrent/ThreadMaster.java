package org.ixkit.land.concurrent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class ThreadMaster {
    public static void sleep(long sleepMillis){
        sleep(sleepMillis,null);
    }

    public static void sleep(long sleepMillis,Runnable command){
        if (sleepMillis<=0) return;
        try
        {
            Thread.sleep(sleepMillis);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

    }
    public static ScheduledExecutorService sleepThen(long delayInSeconds, Consumer task){
        Runnable cmd = new Runnable(){

            @Override
            public void run() {
                task.accept(this);
            }
        };
        return sleepThen(delayInSeconds,cmd,false,0);
    }

    public static ScheduledExecutorService sleepThen(long delayInSeconds,Runnable command ){
        return sleepThen(delayInSeconds,command,false,0);
    }

    public static ScheduledExecutorService sleepThen(long delayInSeconds,Runnable command,boolean loop,long duration ){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (loop){
            executorService.scheduleAtFixedRate(command,delayInSeconds,duration,TimeUnit.SECONDS);
        }else {
            executorService.schedule(command, delayInSeconds, TimeUnit.SECONDS);
        }
        return executorService;
    }
    public static void shutdown(ScheduledExecutorService executorService){
        if (null == executorService) return;
        executorService.shutdown();
    }

    public static ScheduledExecutorService run(long delayInSeconds,Runnable command){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(command,   delayInSeconds, TimeUnit.SECONDS);
        return executorService;
    }
    public static void  submitThen(Consumer consumer, Object arg){
    ForkJoinPool.commonPool().submit(() -> {

        try {
            consumer.accept(arg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    });
    }
    public static void traceThread(){
       List list = Thread.getAllStackTraces()
                .keySet()
                .stream()
                .collect(Collectors.toList());
        System.out.println("traceThread:" );
        System.out.print(list.toArray());
        System.out.println(list );
    }
}
