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
import info.kurtov.licencesbot.Func1;
import info.kurtov.licencesbot.models.InfoGroup;
import info.kurtov.licencesbot.models.Licence;
import info.kurtov.licencesbot.models.LicenceRelation;
import info.kurtov.licencesbot.models.Relation;
import info.kurtov.licencesbot.utils.DataProvider;
import info.kurtov.licencesbot.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kurt on 31/01/2017.
 */
public class MessageProcessor {

    public static final String compatPattern = "(" + Constants.FINAL_COMPAT_PREFIX + ")(\\d\\d)_(\\d\\d)";

    public static void process(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final String text = message.text();
        if (text.equals(Constants.HELP_COMMAND) || text.equals(Constants.START_COMMAND)) {
            showStartingInfo(bot, message, true);
        } else if (text.startsWith(Constants.LICENCE_PREFIX)) {
            sendLicenceInfo(bot, message);
        } else if (text.startsWith(Constants.COMPATIBILITY_PREFIX)) {
            sendCompatabilityInfo(bot, message);
        } else if (text.matches(compatPattern)) {
            respondToCompat(bot, message);
        } else if (text.equals("/" + InfoGroup.PERMISSION.getTitle())) {
            showInfo(bot, message, InfoGroup.PERMISSION);
        } else if (text.equals("/" + InfoGroup.CONDITION.getTitle())) {
            showInfo(bot, message, InfoGroup.CONDITION);
        } else if (text.equals("/" + InfoGroup.LIMITATION.getTitle())) {
            showInfo(bot, message, InfoGroup.LIMITATION);
        } else {
            searchForLicence(bot, message);
        }
    }

    public static void showStartingInfo(@NotNull final TelegramBot bot,
                                        @NotNull final Message message,
                                        final boolean newMessage) {
        final InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Get licences info").callbackData(Constants.GET_LICENCES_INFO)
                });
        if (newMessage) {
            bot.execute(new SendMessage(message.chat().id(), StringUtils.getStartInfo())
                    .parseMode(ParseMode.HTML)
                    .replyMarkup(inlineKeyboard));
        } else {
            bot.execute(new EditMessageText(message.chat().id(), message.messageId(), StringUtils.getStartInfo())
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .replyMarkup(inlineKeyboard));
        }

    }

    private static void showInfo(@NotNull final TelegramBot bot,
                                 @NotNull final Message message,
                                 @NotNull final InfoGroup infoGroup) {
        bot.execute(new SendMessage(message.chat().id(), StringUtils.getInfosByGroup(infoGroup))
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true));
    }

    private static void searchForLicence(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final List<Licence> licences = new ArrayList<>();
        for (final Licence licence : Licence.values()) {
            if (licence.getName().toLowerCase(Locale.US).contains(message.text().toLowerCase())) {
                licences.add(licence);
            }
        }
        final String text;
        if (licences.size() == 0) {
            text = "Can't find any matching licence. Try searching again or begin from the /start";
        } else if (licences.size() == 1) {
            final int number = licences.get(0).getOrder();
            text = StringUtils.getLicenceFullInfo(DataProvider.getLicenceRelations().get(number),
                    DataProvider.getLicenceInfos().get(number), number);
        } else {
            final List<LicenceRelation> selectedLicenceRelations = new ArrayList<>();
            for (final LicenceRelation licenceRelation : DataProvider.getLicenceRelations()) {
                for (final Licence licence : licences) {
                    if (licence == licenceRelation.getTitle()) {
                        selectedLicenceRelations.add(licenceRelation);
                        break;
                    }
                }
            }
            text = "Here the list of licences that matches your search query \"" + message.text()
                    + "\"\n\n" + StringUtils.getListOfLicences(Constants.LICENCE_PREFIX, selectedLicenceRelations);
        }
        final SendMessage newRequest = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static void respondToCompat(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final String text = message.text();
        final int firstNumber = Integer.valueOf(
                text.replace(Constants.FINAL_COMPAT_PREFIX, "").substring(0, 2));
        final int secondNumber = Integer.valueOf(
                text.substring(text.length() - 2, text.length()));
        final LicenceRelation firstLicence = DataProvider.getLicenceRelations().get(firstNumber);
        final Licence secondLicence = DataProvider.getLicenceRelations().get(secondNumber).getTitle();
        final Relation relation = firstLicence.getRelations().get(secondLicence);
        final String sendingText = "Is licence \"" + firstLicence.getTitle().getName()
                + "\" compatible with licence \"" + secondLicence.getName() + "\"?\n\n"
                + relation.name();
        bot.execute(new SendMessage(message.chat().id(), sendingText)
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true)
                .disableNotification(true));
    }

    private static void sendLicenceInfo(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final String licenceNumber = message.text().replace(Constants.LICENCE_PREFIX, "");
        final String text = StringUtils.getText(DataProvider.getLicenceRelations(), licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(final Integer number) {
                return StringUtils.getLicenceFullInfo(DataProvider.getLicenceRelations().get(number),
                        DataProvider.getLicenceInfos().get(number), number);
            }
        });
        final SendMessage newRequest = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static void sendCompatabilityInfo(@NotNull final TelegramBot bot, @NotNull final Message message) {
        String licenceNumber = message.text().replace(Constants.COMPATIBILITY_PREFIX, "");
        final String text = StringUtils.getText(DataProvider.getLicenceRelations(), licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(final Integer number) {
                return StringUtils.getLicenceWithCompatability(DataProvider.getLicenceRelations(),
                        DataProvider.getLicenceRelations().get(number), number);
            }
        });
        final SendMessage newRequest = new SendMessage(message.chat().id(), text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }


}