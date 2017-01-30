import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

/**
 * Created by kurt on 30/01/2017.
 */
public class LicenceInfo {

    @SerializedName("licence")
    private Licence licence;
    @SerializedName("fields")
    private LinkedHashMap<Info, Relation> fields;

    public Licence getLicence() {
        return licence;
    }

    public LinkedHashMap<Info, Relation> getFields() {
        return fields;
    }

}
