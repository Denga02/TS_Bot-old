import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class PublicLogger {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final Handler consoleHandler = new ConsoleHandler();
    private static FileHandler fileHandler;
    private static String logFileName;
    private static final int TIMER_FO_ONE_DAY = 24 * 60 * 60 * 1000;

    // 5 minutes = 300,000 milliseconds
    private static final long LOG_FILE_UPDATE_INTERVAL = 300000;

    private static final long CREATE_NEW_LOGFILE_INTERVAL = TIMER_FO_ONE_DAY;

    public static void configLogging() {
        configureLogger();
        createNewLogFile();
        startLogFileUpdateThread();
    }

    private static void configureLogger() {
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);

        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
    }

    private static void createNewLogFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = dateFormat.format(new Date());
        logFileName = "logfile_" + formattedDate + ".txt";

        try {
            fileHandler = new FileHandler(logFileName);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log file handler: " + e.getMessage(), e);
        }
    }

    private static void startLogFileUpdateThread() {
        Thread logFileUpdateThread = new Thread(() -> {
            try {
                while (true) {
                    fileHandler.flush();
                    checkAndCreateNewLogFile();
                    Thread.sleep(LOG_FILE_UPDATE_INTERVAL);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        logFileUpdateThread.setDaemon(true);
        logFileUpdateThread.start();
    }

    private static void checkAndCreateNewLogFile() {
        Path logFilePath = Paths.get(logFileName);
        if (Files.exists(logFilePath)) {
            try {
                long fileAge = System.currentTimeMillis() - Files.getLastModifiedTime(logFilePath).toMillis();
                if (fileAge >= CREATE_NEW_LOGFILE_INTERVAL) {
                    createNewLogFile();
                }
            } catch (IOException e) {
                logger.warning("Failed to create new log file: " + e.getMessage());
            }
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
