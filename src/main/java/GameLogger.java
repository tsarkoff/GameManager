import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import static java.lang.String.format;

public enum GameLogger {
    INFO,
    WARN,
    ERROR,
    FATAL;
    private static final StringBuilder log = new StringBuilder();
    private static final boolean WRITE_TO_CONSOLE = true;

    public static void writeLog(String line, GameLogger severity) {
        String logLine = format("[%.24s] %6s => %s\n", LocalDateTime.now(), severity.toString(), line);
        log.append(logLine);
        if (WRITE_TO_CONSOLE) {
            String start = "", end = GameLogger.ANSI_RESET;
            switch (severity) {
                case INFO:
                    start = GameLogger.ANSI_GREEN;
                    break;
                case WARN:
                    start = GameLogger.ANSI_YELLOW;
                    break;
                case ERROR:
                    start = GameLogger.ANSI_PURPLE;
                    break;
                case FATAL:
                    start = GameLogger.ANSI_RED;
                    break;
            }
            System.out.print(start + logLine + end);
        }
    }

    public static void flushToLogFile() {
        try (FileWriter fileWriter = new FileWriter(GameFileStruct.ROOT_DIR + GameFileStruct.LOG_FILE)) {
            fileWriter.append(log.toString());
            fileWriter.flush();
        } catch (IOException e) {
            writeLog("Невозможно использовать лог-файл: \"temp.txt\" (" + e.getMessage() + ")", GameLogger.FATAL);
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
