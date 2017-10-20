package utils;

import org.powerbot.script.Condition;

public class DelayUtil {

    public DelayUtil() {
    }

    public void delay(int milisec) {
        Condition.sleep(milisec);
    }
}
