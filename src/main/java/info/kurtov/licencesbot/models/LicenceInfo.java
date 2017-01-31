package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;

import java.util.LinkedHashMap;

/**
 * Created by kurt on 30/01/2017.
 */
public class LicenceInfo {

    @SerializedName("licence")
    private Licence licence;
    @SerializedName("fields")
    private LinkedHashMap<Info, Relation> fields;

    @NotNull
    public Licence getLicence() {
        return licence;
    }

    @NotNull
    public LinkedHashMap<Info, Relation> getFields() {
        return fields;
    }

}
