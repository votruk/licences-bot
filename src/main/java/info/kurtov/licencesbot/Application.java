package info.kurtov.licencesbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import info.kurtov.licencesbot.processors.CallbackQueryProcessor;
import info.kurtov.licencesbot.processors.MessageProcesser;
import info.kurtov.licencesbot.utils.DataProvider;
import info.kurtov.licencesbot.utils.LogUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by kurt on 23/01/2017.
 */
public class Application {

    public static void main(final String[] args) throws IOException {

        final TelegramBot bot = TelegramBotAdapter.build(DataProvider.initializeAndGetToken());
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(final List<Update> updates) {
                for (final Update update : updates) {
                    final Message message = update.message();
                    final CallbackQuery callbackQuery = update.callbackQuery();
                    if (message != null && !message.text().isEmpty()) {
                        LogUtils.logMessage(message);
                        MessageProcesser.process(bot, message);
                    } else if (callbackQuery != null) {
                        LogUtils.logCallbackQuery(callbackQuery);
                        CallbackQueryProcessor.process(bot, callbackQuery);
                    }

                }
                return CONFIRMED_UPDATES_ALL;
            }
        });
    }

}