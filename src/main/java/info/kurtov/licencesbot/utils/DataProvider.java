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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kurt on 31/01/2017.
 */
public class DataProvider {

    private static final String PATH_TO_RESOURCES = "src/main/resources/";
    private static List<LicenceRelation> licenceRelations;
    private static List<LicenceInfo> licenceInfos;

    @NotNull
    public static String initializeAndGetToken() throws IOException {
        final Gson gson = new Gson();

        licenceRelations = parseLicenceRelations(gson);
        licenceInfos = parseLicenceInfos(gson);

        final String token = parseBotToken(gson);
        return token;
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
    private static String parseBotToken(@NotNull final Gson gson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(PATH_TO_RESOURCES + "bot-token")));
    }


    @NotNull
    private static List<LicenceRelation> parseLicenceRelations(@NotNull final Gson gson) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(PATH_TO_RESOURCES + "licences3.json"));
        final List<LicenceRelation> licenceRelations = gson.fromJson(reader, new TypeToken<List<LicenceRelation>>() {
        }.getType());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return licenceRelations;
    }

    @NotNull
    private static List<LicenceInfo> parseLicenceInfos(@NotNull final Gson gson) throws FileNotFoundException {
        JsonReader reader2 = new JsonReader(new FileReader(PATH_TO_RESOURCES + "licences_main_updated.json"));
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
