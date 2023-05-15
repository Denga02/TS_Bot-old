import java.io.IOException;
import java.util.logging.*;

public class PublicLogger {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final FileHandler fileHandler;
    public static final Handler consoleHandler = new ConsoleHandler();

    static {
        try {
            fileHandler = new FileHandler("logfile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void configLogging() {
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);

        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(fileHandler);
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());


    }

}
