import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.sun.istack.internal.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kurt on 23/01/2017.
 */
public class Application {

    public static final String BACK_TO_START = "BACK_TO_START";

    public static void main(final String[] args) throws FileNotFoundException {
        final Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/licence.json"));

        final List<LicenceRelation> licenceRelations = gson.fromJson(reader, new TypeToken<List<LicenceRelation>>() {
        }.getType());
        final LicenceRelation onlyLicence = licenceRelations.get(0);

        final TelegramBot bot = TelegramBotAdapter.build("271082119:AAHGdwihF-2qEwSvdjt48FSywunyDrYEBGU");
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(final List<Update> updates) {
                // process updates
                for (final Update update : updates) {
                    final Message message = update.message();
                    final CallbackQuery callbackQuery = update.callbackQuery();
                    if (message != null && !message.text().isEmpty()) {
                        if (message.text().equals("/help")) {
                            Help.doOnHelp(bot, message);
                        } else if (message.text().equals("/start")) {
                            Start.doOnStart(bot, update.message(), true);
                        } else {
                            final SendMessage newRequest = new SendMessage(update.message().chat().id(), "I received your message: "
                                    + update.message().text())
                                    .parseMode(ParseMode.HTML)
                                    .disableWebPagePreview(true)
                                    .disableNotification(true);
                            bot.execute(newRequest);
                        }
                    } else if (update.chosenInlineResult() != null) {
                        System.out.printf(update.chosenInlineResult().toString());
                    } else if (callbackQuery != null) {
                        if (callbackQuery.data().equals(Start.COMPARE_CALLBACK_DATA)) {
                            InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                                    new InlineKeyboardButton[]{
                                            new InlineKeyboardButton("Back to start").callbackData(BACK_TO_START)
                                    });
                            EditMessageText editMessageText = new EditMessageText(callbackQuery.message().chat().id(),
                                    callbackQuery.message().messageId(), getFirstLicencesText(onlyLicence))
                                    .parseMode(ParseMode.HTML)
                                    .disableWebPagePreview(true)
                                    .replyMarkup(inlineKeyboard);
                            bot.execute(editMessageText);
                        } else if (callbackQuery.data().equals(BACK_TO_START)) {
                            Start.doOnStart(bot, callbackQuery.message(), false);
                        }

                    }

                }
                return CONFIRMED_UPDATES_ALL;
            }
        });
    }

    @NotNull
    private static String getFirstLicencesText(@NotNull final LicenceRelation onlyLicence) {
        return "Choose first licence:\n\n/Licence_1 " + onlyLicence.getName().getTitle();
    }

    @NotNull
    private static String getSecondLicences(@NotNull final LinkedHashMap<Licence, Relation> licenceRelationHashMap,
                                            final int itemsOnPage,
                                            final int page) {
        //TODO add paging later
        String text = "Choose second licence:\n\n/";
        int count = 1;
        for (Map.Entry<Licence, Relation> entry : licenceRelationHashMap.entrySet()) {
            text = text + "/Licence_" + count + " " + entry.getKey();
            count++;
        }
        return text;
    }


}
