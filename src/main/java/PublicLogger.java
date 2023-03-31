import java.io.IOException;
import java.util.logging.*;

public class PublicLogger {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("Logfile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger() {

        logger.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);




        return logger;
    }
}
