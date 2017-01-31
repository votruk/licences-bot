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
            bot.execute(new SendMessage(message.chat().id(), "Hi!\n\nI’m an IP bot that can help you to deal with open source matters. " +
                    "Just print a name of the license that you are interested in and I\'ll send you necessary information." +
                    "\n\nIn case you don’t remember full license name, just send me what you have, I\'ll find for you something. " +
                    "\n\nIf you don\'t know what exactly you are looking for, click \"Get license info\" down here and see what I have." +
                    "\n\nFor more information about my capabilities and instructions send me /help.")
                    .replyMarkup(inlineKeyboard));
        } else {
            bot.execute(new EditMessageText(message.chat().id(), message.messageId(),
                    "Choose what you want to do")
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard));
        }

    }

}