import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class PublicLogger {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final Handler consoleHandler = new ConsoleHandler();
    public static FileHandler fileHandler;
    public static String logFileName;

    public static void configLogging() {
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);

        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());

        createNewLogFile();

        // Start the log file update thread
        Thread logFileUpdateThread = new Thread(() -> {
            try {
                while (true) {
                    fileHandler.flush();
                    checkAndCreateNewLogFile();
                    Thread.sleep(300000); // 5 minutes = 300,000 milliseconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        logFileUpdateThread.setDaemon(true);
        logFileUpdateThread.start();
    }

    private static void createNewLogFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = dateFormat.format(new Date());
        logFileName = "logfile_" + formattedDate + ".txt";

        try {
            fileHandler = new FileHandler(logFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    }

    private static void checkAndCreateNewLogFile() {
        Path logFilePath = Paths.get(logFileName);
        if (Files.exists(logFilePath)) {
            try {
                long fileAge = System.currentTimeMillis() - Files.getLastModifiedTime(logFilePath).toMillis();
                long oneWeekInMillis = 7 * 24 * 60 * 60 * 1000; // 1 week = 7 days = 7 * 24 * 60 * 60 * 1000 milliseconds
                if (fileAge >= oneWeekInMillis) {
                    createNewLogFile();
                }
            } catch (IOException e) {
                logger.warning("Failed to create new log file: " + e.getMessage());
            }
        }
    }
}
