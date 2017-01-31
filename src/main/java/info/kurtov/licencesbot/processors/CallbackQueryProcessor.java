package info.kurtov.licencesbot.processors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.Constants;
import info.kurtov.licencesbot.utils.DataProvider;
import info.kurtov.licencesbot.utils.StringUtils;

/**
 * Created by kurt on 31/01/2017.
 */
public class CallbackQueryProcessor {

    public static void process(@NotNull final TelegramBot bot, @NotNull final CallbackQuery callbackQuery) {
        if (callbackQuery.data().equals(Constants.GET_LICENCES_INFO)) {
            final InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]{
                            new InlineKeyboardButton("Back to start").callbackData(Constants.BACK_TO_START)
                    });
            final EditMessageText editMessageText = new EditMessageText(callbackQuery.message().chat().id(),
                    callbackQuery.message().messageId(), "Choose licence you want to know about by " +
                    "clicking on corresponding link:\n\n"
                    + StringUtils.getListOfLicences(Constants.LICENCE_PREFIX, DataProvider.getLicenceRelations()))
                    .parseMode(ParseMode.Markdown)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard);
            bot.execute(editMessageText);
        } else if (callbackQuery.data().equals(Constants.GET_MORE_ABOUT)) {
            final InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]{
                            new InlineKeyboardButton("Back to start").callbackData(Constants.BACK_TO_START)
                    });
            final EditMessageText editMessageText = new EditMessageText(callbackQuery.message().chat().id(),
                    callbackQuery.message().messageId(), "There are three main groups:\n\n"
                    + StringUtils.getInfosGroups())
                    .parseMode(ParseMode.Markdown)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard);
            bot.execute(editMessageText);

        } else if (callbackQuery.data().equals(Constants.BACK_TO_START)) {
            Start.doOnStart(bot, callbackQuery.message(), false);
        }
    }

}
