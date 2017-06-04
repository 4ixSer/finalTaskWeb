package ua.nure.gnuchykh.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {


    // класс извлекает информацию из файла messages.properties
    private MessageManager() {
    }

    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        return resourceBundle.getString(key);
    }
}