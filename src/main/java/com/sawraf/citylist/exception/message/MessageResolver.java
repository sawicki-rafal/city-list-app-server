package com.sawraf.citylist.exception.message;

import com.sawraf.citylist.exception.ApplicationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Resolves locale messages based on {@link MessageCode} for Exceptions
 */
@Component
public class MessageResolver {

    private static final Locale LOCALE = LocaleContextHolder.getLocale();

    private final MessageSource messageSource;

    public MessageResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(ApplicationException exception) {
        final MessageCode messageCode = exception.getMessageCode();
        if (messageCode == null) {
            return exception.getMessage();
        }
        final Object[] args = exception.getMessageArgs().toArray();
        return getMessage(messageCode, args);
    }

    public String getMessage(MessageCode messageCode, Object... args) {
        return messageSource.getMessage(messageCode.getKey(), args, LOCALE);
    }
}
