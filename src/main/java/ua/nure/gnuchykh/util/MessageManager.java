package ua.nure.gnuchykh.util;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * The class retrieves information from the messages.properties file.
 * @author qny4ix
 *
 */
public final class MessageManager {

    private MessageManager() {
    }

    public static String getProperty(final String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        return resourceBundle.getString(key);
    }
}