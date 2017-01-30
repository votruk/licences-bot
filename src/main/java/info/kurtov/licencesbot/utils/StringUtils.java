package info.kurtov.licencesbot.utils;

import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.Constants;
import info.kurtov.licencesbot.models.Info;
import info.kurtov.licencesbot.models.LicenceInfo;
import info.kurtov.licencesbot.processors.LicenceRelation;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by kurt on 31/01/2017.
 */
public class StringUtils {

    @NotNull
    public static String getListOfLicences(@NotNull final String prefix, @NotNull final List<LicenceRelation> licences) {
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
                "You can check compatibility with other licences by pressing following link:\n"
                + Constants.COMPATABILITY_PREFIX + StringUtils.getFullNumber(number);
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
        for (final Info info : Info.values()) {
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
