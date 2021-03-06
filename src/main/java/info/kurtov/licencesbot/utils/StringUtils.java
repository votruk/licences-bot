package info.kurtov.licencesbot.utils;

import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.Constants;
import info.kurtov.licencesbot.Func1;
import info.kurtov.licencesbot.models.Info;
import info.kurtov.licencesbot.models.InfoGroup;
import info.kurtov.licencesbot.models.LicenceInfo;
import info.kurtov.licencesbot.models.LicenceRelation;

import java.util.List;

/**
 * Created by kurt on 31/01/2017.
 */
public class StringUtils {

    @NotNull
    public static String getStartInfo() {
        return "Hi!\n\nThere are several things that I can do for you." +
                "\n\nFirst of all, I can bring you up-to-date about the most common open source license. " +
                "To get information about license, type its name or the part of it and I\'ll search for suitable license." +
                "\n\nIf you don\'t know what exactly you are looking for, click button \"Get license info\"" +
                " and I\'ll send you a list of licenses that I am aware of. " +
                "\n\nAnother thing that I can do for you is to check compatibility of licenses. " +
                "To proceed this, choose a license, compatibility of which you want to check, " +
                "then choose the second license and I\'ll send you the result of compatibility analysis.";
    }

    @NotNull
    public static String getInfosByGroup(@NotNull final InfoGroup infoGroup) {
        String text = infoGroup.getTitle() + " (" + infoGroup.getDescription() + ")\n\n";
        for (final Info info : Info.values()) {
            if (info.getGroup() == infoGroup) {
                text = text + info.getName() + " - " + info.getDescription() + "\n";
            }
        }
        return text;
    }

    @NotNull
    public static String getInfosGroups() {
        String text = "";
        for (final InfoGroup infoGroup : InfoGroup.values()) {
            text = text + "/" + infoGroup.getTitle() + " - " + infoGroup.getDescription() + ".\n\n";
        }
        return text;
    }

    @NotNull
    public static String getListOfLicences(@NotNull final String prefix,
                                           @NotNull final List<LicenceRelation> licences) {
        String text = "";
        for (final LicenceRelation licence : licences) {
            text = text + prefix + getFullNumber(licence.getTitle().getOrder())
                    + "  " + licence.getTitle().getName() + "\n";
        }
        return text;
    }

    @NotNull
    public static String getFullNumber(int i) {
        return i < 10 ? ("0" + i) : ("" + i);
    }

    @NotNull
    public static String getLicenceFullInfo(@NotNull final LicenceRelation licenceRelation,
                                            @NotNull final LicenceInfo licenceInfo,
                                            @NotNull final int number) {
        return licenceRelation.getTitle().getName() + "\n\n" + getLicenceInfos(licenceInfo) + "\n" +
                "Check compatibility with other licences:\n"
                + Constants.COMPATIBILITY_PREFIX + StringUtils.getFullNumber(number);
    }


    @NotNull
    public static String getText(@NotNull final List<LicenceRelation> licenceRelations,
                                 @NotNull final String licenceNumber,
                                 @NotNull final Func1<Integer, String> func) {
        if (licenceNumber.matches("[0-9]+")) {
            final int number = Integer.valueOf(licenceNumber);
            if (number >= licenceRelations.size()) {
                return "Unknown licence with number: " + number;
            } else {
                return func.call(number);
            }
        } else {
            return "Unknown licence with number: " + licenceNumber;
        }
    }

    @NotNull
    private static String getLicenceInfos(final LicenceInfo licenceInfo) {
        String text = "";
        InfoGroup currentGroup = null;
        for (final Info info : Info.values()) {
            if (currentGroup == null) {
                currentGroup = info.getGroup();
                text = text + "/" + currentGroup.getTitle() + "\n";
            } else if (currentGroup != info.getGroup()) {
                text = text + "\n";
                currentGroup = info.getGroup();
                text = text + "/" + currentGroup.getTitle() + "\n";

            }
            text = text + info.getName() + ": " + licenceInfo.getFields().get(info).name() + "\n";
        }
        return text;
    }

    @NotNull
    public static String getLicenceWithCompatability(final List<LicenceRelation> licenceRelations,
                                                     final LicenceRelation chosenLicence,
                                                     final int number) {
        return chosenLicence.getTitle().getName() + "\n\n" + "Choose one of the following licences to know either " +
                "these licences compatible or not:\n\n" +
                StringUtils.getListOfLicences(Constants.FINAL_COMPAT_PREFIX
                        + StringUtils.getFullNumber(number) + "_", licenceRelations);
    }

}
