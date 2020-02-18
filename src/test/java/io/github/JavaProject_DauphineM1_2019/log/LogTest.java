package io.github.JavaProject_DauphineM1_2019.log;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

class LogTest {

	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(LogTest.class);
	private static ListAppender<ILoggingEvent> ListAppenderLogEvent = new ListAppender<>();

	@Test
	void testLog() {
		ListAppenderLogEvent.start();
		LOGGER.addAppender(ListAppenderLogEvent);

		LOGGER.info("This is an info message");
		LOGGER.warn("This is a warning message"); 
		LOGGER.error("This is an error message"); 
		
		assertEquals("This is an info message", ListAppenderLogEvent.list.get(0).getMessage());
        assertEquals(Level.INFO, ListAppenderLogEvent.list.get(0).getLevel());
        assertEquals("This is a warning message", ListAppenderLogEvent.list.get(1).getMessage());
        assertEquals(Level.WARN, ListAppenderLogEvent.list.get(1).getLevel());
        assertEquals("This is an error message", ListAppenderLogEvent.list.get(2).getMessage());
        assertEquals(Level.ERROR, ListAppenderLogEvent.list.get(2).getLevel());
	}

}
