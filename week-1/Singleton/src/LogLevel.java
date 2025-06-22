public enum LogLevel {
    DEBUG, INFO, WARNING, ERROR, CRITICAL;
    
    public boolean shouldLog(LogLevel other) {
        return this.ordinal() <= other.ordinal();
    }
}