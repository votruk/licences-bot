package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;

import java.util.LinkedHashMap;

/**
 * Created by kurt on 23/01/2017.
 */
public class LicenceRelation {

    @SerializedName("title")
    private Licence title;
    @SerializedName("fields")
    private LinkedHashMap<Licence, Relation> relations;

    @NotNull
    public Licence getTitle() {
        return title;
    }

    @NotNull
    public LinkedHashMap<Licence, Relation> getRelations() {
        return relations;
    }

}