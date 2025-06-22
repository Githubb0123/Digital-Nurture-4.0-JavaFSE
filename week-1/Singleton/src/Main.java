public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        
        logger.setLogLevel(LogLevel.DEBUG);
        
        logger.debug("Debug message");
        logger.info("Application started");
        logger.warn("Low disk space");
        logger.error("Failed to load configuration");
        logger.critical("System crash imminent!");
        
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Logger threadLogger = Logger.getInstance();
                threadLogger.info("Log from thread " + Thread.currentThread().getId());
            }).start();
        }
    }
}