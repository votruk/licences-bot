package info.kurtov.licencesbot.processors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.Constants;
import info.kurtov.licencesbot.models.Licence;
import info.kurtov.licencesbot.models.Relation;
import info.kurtov.licencesbot.utils.DataProvider;
import info.kurtov.licencesbot.utils.StringUtils;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kurt on 31/01/2017.
 */
public class MessageProcesser {

    public static final String compatPattern = "(" + Constants.FINAL_COMPAT_PREFIX + ")(\\d\\d)_(\\d\\d)";

    public static void process(@NotNull final TelegramBot bot, @NotNull final Message message) {
        if (message.text().equals("/help")) {
            Help.doOnHelp(bot, message);
        } else if (message.text().equals("/start")) {
            Start.doOnStart(bot, message, true);
        } else if (message.text().startsWith(Constants.LICENCE_PREFIX)) {
            sendLicenceInfo(bot, message);
        } else if (message.text().startsWith(Constants.COMPATABILITY_PREFIX)) {
            sendCompatabilityInfo(bot, message);
        } else if (message.text().matches(compatPattern)) {
            respondToCompat(bot, message);
        } else {
            searchForLicence(bot, message);
        }
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
        final String sendingText = "Is licence\n\"" + firstLicence.getTitle().getName()
                + "\"\ncompatable with licence\n\"" + secondLicence.getName() + "\"?\n\n"
                + relation.name();
        final SendMessage newRequest = new SendMessage(message.chat().id(), sendingText)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        bot.execute(newRequest);
    }

    private static void sendLicenceInfo(@NotNull final TelegramBot bot, @NotNull final Message message) {
        final String licenceNumber = message.text().replace(Constants.LICENCE_PREFIX, "");
        final String text = StringUtils.getText(DataProvider.getLicenceRelations(), licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(Integer number) {
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
        String licenceNumber = message.text().replace(Constants.COMPATABILITY_PREFIX, "");
        final String text = StringUtils.getText(DataProvider.getLicenceRelations(), licenceNumber, new Func1<Integer, String>() {
            @Override
            public String call(Integer number) {
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