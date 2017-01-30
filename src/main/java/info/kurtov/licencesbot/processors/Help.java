package info.kurtov.licencesbot.processors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.sun.istack.internal.NotNull;

/**
 * Created by kurt on 24/01/2017.
 */
public class Help {

    public static void doOnHelp(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final SendMessage newRequest = new SendMessage(message.chat().id(),
                "Welcome to the *Licences Bot* where you can find help with licences." +
                        "\nYou can start with this bot using /start command")
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

}
