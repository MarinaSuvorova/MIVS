import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class LoggerMIVS {
    private static final Logger LOGGER = Logger.getLogger("mivsLogger");
        public void addMessageToLogFile(String msg)  {
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("appLogger.log", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new LoggerMessageFormatter());
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(fileHandler);
        LOGGER.info(msg);
    }
}


class LoggerMessageFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime + " | " + record.getMessage() + "\n";
    }
}
