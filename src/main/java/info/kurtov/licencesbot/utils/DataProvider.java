package info.kurtov.licencesbot.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sun.istack.internal.NotNull;
import info.kurtov.licencesbot.models.LicenceInfo;
import info.kurtov.licencesbot.processors.LicenceRelation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by kurt on 31/01/2017.
 */
public class DataProvider {

    private static List<LicenceRelation> licenceRelations;
    private static List<LicenceInfo> licenceInfos;

    public static void initialize() throws FileNotFoundException {
        final Gson gson = new Gson();

        licenceRelations = parseLicenceRelations(gson);
        licenceInfos = parseLicenceInfos(gson);
    }

    @NotNull
    public static List<LicenceRelation> getLicenceRelations() {
        return licenceRelations;
    }

    @NotNull
    public static List<LicenceInfo> getLicenceInfos() {
        return licenceInfos;
    }

    @NotNull
    private static List<LicenceRelation> parseLicenceRelations(@NotNull final Gson gson) throws FileNotFoundException {
        JsonReader reader2 = new JsonReader(new FileReader("src/main/resources/licences3.json"));
        final List<LicenceRelation> licenceRelations = gson.fromJson(reader2, new TypeToken<List<LicenceRelation>>() {
        }.getType());
        try {
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return licenceRelations;
    }

    @NotNull
    private static List<LicenceInfo> parseLicenceInfos(@NotNull final Gson gson) throws FileNotFoundException {
        JsonReader reader2 = new JsonReader(new FileReader("src/main/resources/licences_main_updated.json"));
        final List<LicenceInfo> licenceInfos = gson.fromJson(reader2, new TypeToken<List<LicenceInfo>>() {
        }.getType());
        try {
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return licenceInfos;
    }

}
