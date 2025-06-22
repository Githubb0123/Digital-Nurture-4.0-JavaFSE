import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private static Logger instance;
    private static final ReentrantLock lock = new ReentrantLock();
    
    private LogLevel logLevel = LogLevel.INFO;
    private PrintWriter fileWriter;
    private final SimpleDateFormat dateFormat = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    private Logger() {
        setupFileWriter();
        Runtime.getRuntime().addShutdownHook(new Thread(this::cleanup));
    }
    
    private void setupFileWriter() {
        try {
            File logsDir = new File("logs");
            if (!logsDir.exists()) logsDir.mkdir();
            
            String filename = "logs/app_" + 
                new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log";
            fileWriter = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.err.println("Failed to initialize file logger: " + e.getMessage());
        }
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Logger();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    
    public void setLogLevel(LogLevel level) {
        this.logLevel = level;
    }
    
    public void log(LogLevel level, String message) {
        if (!logLevel.shouldLog(level)) return;
        
        String timestamp = dateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();
        String logEntry = String.format("[%s] [%s] [%s] %s",
            timestamp, threadName, level, message);
        
        System.out.println(logEntry);
        
        if (fileWriter != null) {
            fileWriter.println(logEntry);
            fileWriter.flush();
        }
    }
    
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    
    public void info(String message) {
        log(LogLevel.INFO, message);
    }
    
    public void warn(String message) {
        log(LogLevel.WARNING, message);
    }
    
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
    
    public void critical(String message) {
        log(LogLevel.CRITICAL, message);
    }
    
    private void cleanup() {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
    
    void setFileWriter(PrintWriter writer) {
        this.fileWriter = writer;
    }
}