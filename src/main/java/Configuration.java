import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Configuration {
    //class for the config.json file
    //gives all important methods
    private CounterConfig counter;

    public static Configuration load(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.readValue(new File(filePath), Configuration.class);
    }

    public CounterConfig getCounter() {
        return counter;
    }

    public void setCounter(CounterConfig counter) {
        this.counter = counter;
    }

    public void save(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.writeValue(new File(filePath), this);
    }

    public static class CounterConfig {
        private int kevin;
        private int steven;

        public int getKevin() {
            return kevin;
        }

        public void setKevin(int kevin) {
            this.kevin = kevin;
        }

        public int getSteven() {
            return steven;
        }

        public void setSteven(int steven) {
            this.steven = steven;
        }
    }
}
