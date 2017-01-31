package info.kurtov.licencesbot.processors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.Constants;
import info.kurtov.licencesbot.utils.StringUtils;

/**
 * Created by kurt on 24/01/2017.
 */
public class Start {

    public static void doOnStart(@NotNull final TelegramBot bot,
                                 @NotNull final Message message,
                                 final boolean newMessage) {
        final InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Get licences info").callbackData(Constants.GET_LICENCES_INFO)
                });
        if (newMessage) {
            bot.execute(new SendMessage(message.chat().id(), StringUtils.getStartInfo())
                    .parseMode(ParseMode.Markdown)
                    .replyMarkup(inlineKeyboard));
        } else {
            bot.execute(new EditMessageText(message.chat().id(), message.messageId(), StringUtils.getStartInfo())
                    .parseMode(ParseMode.Markdown)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard));
        }

    }

}