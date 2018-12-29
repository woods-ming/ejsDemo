// 1.创建线程（把任务写入run方法）
// 继承类：
class XxxThread extends Thread {
    public void run() {
        // 编写任务
    }
} 
Thread thread = new XxxThread();
    
// 实现接口：
class XxxClass implements Runnable {
    public void run() {
        // 编写任务
    }
}
Thread thread2 = new Thread(new XxxClass());


// 2.设置优先级
// Thread.MAX_PRIORITY: 10
// Thread.NORM_PRIORITY: 5
// Thread.MIN_PRIORITY:  1
thread.setPriority(1-10);
    

// 3.常用方法
// 3.1）启动线程（自动调用线程的run方法）
thread.start();

// 3.2）线程暂停一段时间ms
Thread.sleep(睡眠时间);

// 3.3）主线程等待子线程完成，然后继续执行
thread.join(等待时间) 

// 3.4）使线程中断
thread.interrupt() 

// 3.5）判断当前线程是否中断
Thread.interrupted() 

// 3.6）线程是否处于活动状态（尚未终止）
thread.isAlive() 
