import java.io.IOException;
import java.util.logging.*;

public class PublicLogger {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final FileHandler fileHandler;
    public static final Handler consoleHandler = new ConsoleHandler();

    static {
        try {
            fileHandler = new FileHandler("Logfile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void configLogging() {
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);

        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(fileHandler);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());


    }

}
