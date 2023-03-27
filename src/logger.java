import java.util.logging.Level;
import java.util.logging.Logger;
public class logger {
    public static final Logger LOGGER = Logger.getLogger(logger.class.getName());

    public void set_Level(Level level){
        LOGGER.setLevel(level);

    }

    public static Logger getLogger() {
        LOGGER.setLevel(Level.FINER);
        return LOGGER;
    }
}
