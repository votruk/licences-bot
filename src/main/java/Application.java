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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kurt on 23/01/2017.
 */
public class Application {

    public static final String BACK_TO_START = "BACK_TO_START";
    private static final String LICENCE_PREFIX = "/L_";
    public static final String COMPATABILITY_PREFIX = "/C_";
    public static final String FINAL_COMPAT_PREFIX = "/CC_";
    public static final String compatPattern = "(" + FINAL_COMPAT_PREFIX + ")(\\d\\d)_(\\d\\d)";

    public static void main(final String[] args) throws FileNotFoundException {
        final Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/licences3.json"));

        final List<LicenceRelation> licenceRelations = gson.fromJson(reader, new TypeToken<List<LicenceRelation>>() {
        }.getType());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonReader reader2 = new JsonReader(new FileReader("src/main/resources/licences_main_updated.json"));
        final List<LicenceInfo> licenceInfos = gson.fromJson(reader2, new TypeToken<List<LicenceInfo>>() {
        }.getType());
        try {
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                        } else if (message.text().startsWith(LICENCE_PREFIX)) {
                            sendLicenceInfo(message, licenceRelations, licenceInfos, bot);
                        } else if (message.text().startsWith(COMPATABILITY_PREFIX)) {
                            sendCompatabilityInfo(message, licenceRelations, bot);
                        } else if (message.text().matches(compatPattern)) {
                            respondToCompat(message, licenceRelations, bot);
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
                        if (callbackQuery.data().equals(Start.GET_LICENCES_INFO)) {
                            InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                                    new InlineKeyboardButton[]{
                                            new InlineKeyboardButton("Back to start").callbackData(BACK_TO_START)
                                    });
                            EditMessageText editMessageText = new EditMessageText(callbackQuery.message().chat().id(),
                                    callbackQuery.message().messageId(), getFirstLicencesText(LICENCE_PREFIX, licenceRelations))
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

    private static void respondToCompat(final Message message,
                                        final List<LicenceRelation> licenceRelations,
                                        final TelegramBot bot) {
        final String text = message.text();
        final int firstNumber = Integer.valueOf(
                text.replace(FINAL_COMPAT_PREFIX, "").substring(0, 2));
        final int secondNumber = Integer.valueOf(
                text.substring(text.length() - 2, text.length()));
        final LicenceRelation firstLicence = licenceRelations.get(firstNumber);
        final Licence secondLicence = licenceRelations.get(secondNumber).getTitle();
        final Relation relation = firstLicence.getRelations().get(secondLicence);
        final String sendingText = "Is licence\n\"" + firstLicence.getTitle().getName()
                + "\"\ncompatable with licence\n\"" + secondLicence.getName() + "\"?\n\n"
                + relation.name();
        final SendMessage newRequest = new SendMessage(message.chat().id(), sendingText)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static void sendLicenceInfo(final Message message,
                                        final List<LicenceRelation> licenceRelations,
                                        final List<LicenceInfo> licenceInfos,
                                        final TelegramBot bot) {
        final String licenceNumber = message.text().replace(LICENCE_PREFIX, "");
        final String text = getText(licenceRelations, licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(Integer number) {
                return getLicenceFullInfo(licenceRelations.get(number), licenceInfos.get(number), number);
            }
        });
        final SendMessage newRequest = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static String getText(final List<LicenceRelation> licenceRelations,
                                  final String licenceNumber,
                                  final Func1<Integer, String> func) {
        String text;
        if (licenceNumber.matches("[0-9]+")) {
            final int number = Integer.valueOf(licenceNumber);
            if (number >= licenceRelations.size()) {
                text = "Unknown licence with number: " + number;
            } else {
                text = func.call(number);
            }
        } else {
            text = "Unknown licence with number: " + licenceNumber;
        }
        return text;
    }

    private static void sendCompatabilityInfo(final Message message,
                                              final List<LicenceRelation> licenceRelations,
                                              final TelegramBot bot) {
        String licenceNumber = message.text().replace(COMPATABILITY_PREFIX, "");
        final String text = getText(licenceRelations, licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(Integer number) {
                return getLicenceWithCompatability(licenceRelations, licenceRelations.get(number), number);
            }
        });
        final SendMessage newRequest = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static String getLicenceFullInfo(final LicenceRelation licenceRelation,
                                             final LicenceInfo licenceInfo,
                                             final int number) {
        return licenceRelation.getTitle().getName() + "\n\n" + getLiceceInfos(licenceInfo) + "\n" +
                "You can check compatibility with other licences by pressing following link: "
                + COMPATABILITY_PREFIX + getFullNumber(number);
    }

    private static String getLiceceInfos(final LicenceInfo licenceInfo) {
        String text = "";
        for (final Info info : Info.values()) {
            text = text + info.getName() + ": " + licenceInfo.getFields().get(info).name() + "\n";
        }
        return text;
    }

    private static String getLicenceWithCompatability(final List<LicenceRelation> licenceRelations,
                                                      final LicenceRelation chosenLicence,
                                                      final int number) {
        return chosenLicence.getTitle().getName() + "\n\n" + "Choose one of the following licences to know either " +
                "these licences compatible or not\n" +
                getFirstLicencesText(FINAL_COMPAT_PREFIX + getFullNumber(number) + "_", licenceRelations);
    }

    private static String getFirstLicencesText(final String prefix, final List<LicenceRelation> licences) {
        String text = "Choose first licence:\n\n";
        for (int i = 0; i < licences.size(); i++) {
            text = text + prefix + getFullNumber(i) + "  " + licences.get(i).getTitle().getName() + "\n";
        }
        return text;
    }

    private static String getFullNumber(int i) {
        return i < 10 ? ("0" + i) : ("" + i);
    }

    private static String getSecondLicences(final LinkedHashMap<Licence, Relation> licenceRelationHashMap) {
        //TODO add paging later
        String text = "Choose second licence:\n\n/";
        int count = 1;
        for (Map.Entry<Licence, Relation> entry : licenceRelationHashMap.entrySet()) {
            text = text + LICENCE_PREFIX + count + " " + entry.getKey();
            count++;
        }
        return text;
    }


}
