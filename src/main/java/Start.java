import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.sun.istack.internal.NotNull;

/**
 * Created by kurt on 24/01/2017.
 */
public class Start {

    public static final String COMPARE_CALLBACK_DATA = "COMPARE_CALLBACK_DATA";

    public static void doOnStart(@NotNull final TelegramBot bot, @NotNull final Message message, final boolean newMessage) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Compare Licences").callbackData(COMPARE_CALLBACK_DATA)
                });
        if (newMessage) {
            SendMessage request = new SendMessage(message.chat().id(), "Choose what you want to do").replyMarkup(inlineKeyboard);
            bot.execute(request);
        } else {
            EditMessageText editMessageText = new EditMessageText(message.chat().id(), message.messageId(),
                    "Choose what you want to do")
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard);
            bot.execute(editMessageText);
        }

    }

}
