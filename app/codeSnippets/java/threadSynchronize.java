import java.util.ArrayList;
import java.util.List;

/**
 * 线程同步（售票大厅）
 */
public class SynchronousDemo implements Runnable {
    private static List<String> reservedTickets = new ArrayList<String>();
    static {
        for (int i = 0; i < 10; i++) {
            reservedTickets.add("100" + (i + 1));
        }
    }

    private String windowName; // 售票窗口

    public SynchronousDemo(String windowName) {
        this.windowName = windowName;
    }

    private boolean sellOneTicket() {
        // 询问
        System.out.println(windowName + " 为您服务...");

        String ticketNo;
        synchronized ("") {
            // 检查余票
            int count = reservedTickets.size();
            if (count == 0) {
                System.out.println(windowName + " 票已售罄");
                return false;
            } else {
                System.out.println(windowName + " 还剩 " + count + " 章票");
            }

            // 从票池里拿一张票
            ticketNo = reservedTickets.get(count - 1);

            // 经过1秒后
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 出票
            reservedTickets.remove(ticketNo);
        }

        // 公布
        System.out.println(windowName + " 出票：" + ticketNo + "\r\n");

        return true;
    }

    /**
     * 售票
     */
    public void sellTicketTask() {
        // 开始有票
        boolean hasTickets = true;

        // 一直卖到没票
        while (hasTickets) {
            hasTickets = sellOneTicket();
        }
    }

    @Override
    public void run() {
        sellTicketTask();
    }

    public static void main(String[] args) {
        // 3个窗口同时卖票，互不干涉
        SynchronousDemo window1 = new SynchronousDemo("窗口1");
        SynchronousDemo window2 = new SynchronousDemo("窗口2");
        SynchronousDemo window3 = new SynchronousDemo("窗口3");

        Thread t1 = new Thread(window1);
        Thread t2 = new Thread(window2);
        Thread t3 = new Thread(window3);

        t1.start();
        t2.start();
        t3.start();
    }

}
