package Schedule.TechInsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Filename: Main.java
 * @Package: Schedule.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月18日 16:21
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        ScheduleService scheduleService = new ScheduleService();
        scheduleService.schedule(() -> System.out.println(LocalDateTime.now().format(formatter) + "这是100毫秒一次的"), 100);
        Thread.sleep(1000);
        scheduleService.schedule(() -> System.out.println(LocalDateTime.now().format(formatter) + "这是200毫秒一次的"), 200);
    }
}
