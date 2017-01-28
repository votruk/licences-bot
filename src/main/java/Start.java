import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Created by kurt on 24/01/2017.
 */
public class Start {

    public static final String GET_LICENCES_INFO = "GET_LICENCES_INFO";

    public static void doOnStart(final TelegramBot bot, final Message message, final boolean newMessage) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Get licences info").callbackData(GET_LICENCES_INFO)
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
