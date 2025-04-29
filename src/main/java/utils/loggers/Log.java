package utils.loggers;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

    public static void info(String message){
        Allure.attachment("info", message);
        logger.info(message);
    }
    public static void error(String message){
        Allure.attachment("error", message);
        logger.error(message);
    }
    public static void debug(String message){
        logger.debug(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }
}
