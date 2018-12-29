import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 苹果消费者
 */
class AppleConsumer implements Runnable {
    private List<String> applePlate; // 从哪个盘子里拿苹果
    private String name;

    public AppleConsumer(List<String> applePlate, String name) {
        this.applePlate = applePlate;
        this.name = name;
    }

    /**
     * 从盘子里拿苹果
     */
    private void getApple() {
        String appleName = this.applePlate.remove(0);
        System.out.println(name + " 从盘子里拿苹果:" + appleName);
    }

    /**
     * 模拟不停的“从盘子里拿苹果”这个过程
     */
    public void mockAddAppleProcedure() {
        while (true) {
            synchronized (applePlate) {
                // 检查盘子是否空了
                while (applePlate.isEmpty()) {
                    System.out.println(name + "在等待... 盘子kong了");

                    // 空了就等一会
                    try {
                        applePlate.wait();
                        System.out.println(name + "被唤醒 ");
                    } catch (InterruptedException e) {
                        System.out.println(name + " 没等住");
                    }
                }

                // 取出苹果
                getApple();

                // 通知等待的线程
                applePlate.notify();
            }

            // 休息2秒
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        mockAddAppleProcedure();
    }

}

/**
 * 苹果生产者
 */
class AppleProducer implements Runnable {
    private List<String> applePlate; // 往哪个盘子里放苹果
    private String name;

    public AppleProducer(List<String> applePlate, String name) {
        this.applePlate = applePlate;
        this.name = name;
    }

    /**
     * 往盘子里放苹果
     */
    private void addApple(String appleName) {
        this.applePlate.add(appleName);
        System.out.println(name + " 往盘子里放苹果:" + appleName);
    }

    /**
     * 模拟不停的“往盘子里放苹果”这个过程
     */
    public void mockAddAppleProcedure() {
        String appleName = "";
        while (true) {
            synchronized (applePlate) {
                // 检查盘子是否满了
                while (applePlate.size() == 10) {
                    System.out.println(name + "在等待... 盘子man了");

                    // 满了就等一会
                    try {
                        applePlate.wait();
                        System.out.println(name + "被唤醒 ");
                    } catch (InterruptedException e) {
                        System.out.println("没等住");
                    }
                }

                // 添加苹果
                appleName = "红富士" + (new Random()).nextInt(10000) + "号";
                addApple(appleName);

                // 通知等待的线程
                applePlate.notifyAll();
            }

            // 休息3秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        mockAddAppleProcedure();
    }

}

public class ProducerConsumerTest {

    public static void main(String[] args) {
        // 盘子（数据结构：栈）
        List<String> applePlate = new ArrayList<String>();

        // 苹果生产者
        AppleProducer pro1 = new AppleProducer(applePlate, "果农1");
        AppleProducer pro2 = new AppleProducer(applePlate, "果农2");
        AppleProducer pro3 = new AppleProducer(applePlate, "果农3");

        // 苹果消费者
        AppleConsumer con1 = new AppleConsumer(applePlate, "同学1");
        AppleConsumer con2 = new AppleConsumer(applePlate, "同学2");
        AppleConsumer con3 = new AppleConsumer(applePlate, "同学3");
        AppleConsumer con4 = new AppleConsumer(applePlate, "同学4");

        // 老师们往盘子里放苹果
        Thread pro1Thread = new Thread(pro1);
        Thread pro2Thread = new Thread(pro2);
        Thread pro3Thread = new Thread(pro3);
        pro1Thread.start();
        pro2Thread.start();
        pro3Thread.start();

        // 同学们从盘子里取苹果
        Thread con1Thread = new Thread(con1);
        Thread con2Thread = new Thread(con2);
        Thread con3Thread = new Thread(con3);
        Thread con4Thread = new Thread(con4);
        con1Thread.start();
        con2Thread.start();
        con3Thread.start();
        con4Thread.start();
    }

}
