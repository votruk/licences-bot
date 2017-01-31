package info.kurtov.licencesbot.utils;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.User;
import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kurt on 31/01/2017.
 */
public class LogUtils {

    private static SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("HH:mm:ss dd.MM.YYYY", Locale.forLanguageTag("ru"));

    public static void logMessage(@NotNull  final Message message) {
        System.out.println(DATE_FORMAT.format(new Date()) + " Got message: \"" + message.text() +
                "\"" + getFullUserName(message.from()));
    }

    public static void logCallbackQuery(@NotNull final CallbackQuery callbackQuery) {
        System.out.println(DATE_FORMAT.format(new Date()) + " Got callbackQuery: \"" + callbackQuery.data() +
                "\"" + getFullUserName(callbackQuery.from()));
    }

    @NotNull
    private static String getFullUserName(@NotNull final User user) {
        return " from " + user.firstName() + " " + user.lastName() + " (" + user.username() + ")";
    }

}
