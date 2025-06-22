public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        
        // Set log level (try changing to DEBUG)
        logger.setLogLevel(LogLevel.DEBUG);
        
        // Test logging
        logger.debug("Debug message");
        logger.info("Application started");
        logger.warn("Low disk space");
        logger.error("Failed to load configuration");
        logger.critical("System crash imminent!");
        
        // Test thread safety
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Logger threadLogger = Logger.getInstance();
                threadLogger.info("Log from thread " + Thread.currentThread().getId());
            }).start();
        }
    }
}