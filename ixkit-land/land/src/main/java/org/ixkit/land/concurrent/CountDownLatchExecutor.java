package org.ixkit.land.concurrent;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @class:CountDownLatchExecutor
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 25/11/2021
 * @version:0.1.0
 * @purpose:
 */

public class CountDownLatchExecutor extends CountDownLatch {


    /**
     * Constructs a {@code CountDownLatch} initialized with the given count.
     *
     * @param count the number of times {@link #countDown} must be invoked
     *              before threads can pass through {@link #await}
     * @throws IllegalArgumentException if {@code count} is negative
     */
    public CountDownLatchExecutor(int count, String tip) {
        super(count);
        startMonitor(tip);
    }
    private ScheduledExecutorService executorService;
    private void startMonitor(String tip){

        executorService = ThreadMaster.sleepThen(0,()->{
            System.out.print(".");
            killMonitorIfNeeds();
        },true,1);

    }
    @Override
    public void countDown() {
        super.countDown();
    }

    public boolean awaitSecond(long timeout  ) throws InterruptedException {
        boolean result = super.await(timeout,TimeUnit.SECONDS);

        final CountDownLatchExecutor self = this;
        ThreadMaster.sleep(timeout*1000,()->{
            self.forceClose();
        });
        return result;
    }
    private void forceClose(){

        ThreadMaster.shutdown(executorService);
        System.out.print("\n");
        executorService = null;

    }

    private boolean killMonitorIfNeeds(){

        if (this.getCount()<=0 && null != executorService){
            ThreadMaster.shutdown(executorService);
            System.out.print("\n");
            executorService = null;
            return true;
        }
        return false;
    }



}
