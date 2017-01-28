import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by kurt on 23/01/2017.
 */
public class LicenceRelation {

    @SerializedName("title")
    private Licence title;
    @SerializedName("fields")
    private LinkedHashMap<Licence, Relation> relations;

    public Licence getTitle() {
        return title;
    }

    public LinkedHashMap<Licence, Relation> getRelations() {
        return relations;
    }

}