package com.reserveme.backend.config.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;

public class LogLevelStringConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String level = event.getLevel().toString().toLowerCase();
        return StringUtils.capitalize(level);
    }
}
