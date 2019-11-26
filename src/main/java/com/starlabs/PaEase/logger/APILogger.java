package com.starlabs.PaEase.logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Log class.
 *
 *
 */
@SuppressWarnings({"FinalClass", "ClassWithoutLogger"})
public final class APILogger{

    /**
     * Logger object.
     */
    @SuppressWarnings("FieldMayBeFinal")
    private Logger logger;

    /**
     * Constructor.
     *
     * @param clazz the class doing the logging
     */
    public APILogger(final Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    /**
     * Get the logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Log a debug message.
     *
     * @param arg0 the message
     */
    public void debug(final String arg0) {
        logger.debug(arg0);
    }

    /**
     * Log an error message.
     *
     * @param arg0 the format
     * @param arg1 the throwable exception
     */
    public void error(final String arg0, final Throwable arg1) {
        this.logger.error(arg0, arg1);
    }

    /**
     * Log an error message.
     *
     * @param arg0 the format
     */
    public void error(final String arg0) {
        this.logger.error(arg0);
    }

    /**
     * Log an info message.
     *
     * @param arg0 the format
     */
    public void info(final String arg0) {
        this.logger.info(arg0);
    }

    /**
     * Log a warning message.
     *
     * @param arg0 the format
     * @param arg1 the throwable exception
     */
    public void warn(final String arg0, final Throwable arg1) {
        this.logger.warn(arg0, arg1);
    }

    /**
     * Log a warning message.
     *
     * @param arg0 the format
     */
    public void warn(final String arg0) {
        this.logger.warn(arg0);
    }

    /**
     * Log a fatal message.
     *
     * @param arg0 the format
     * @param arg1 the throwable exception
     */
    public void fatal(final String arg0, final Throwable arg1) {
        this.logger.warn(arg0, arg1);
    }

    /**
     * Log a fatal message.
     *
     * @param arg0 the format
     */
    public void fatal(final String arg0) {
        this.logger.warn(arg0);
    }
}
