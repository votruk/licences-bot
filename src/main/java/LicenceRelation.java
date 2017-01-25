import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;

import java.util.LinkedHashMap;

/**
 * Created by kurt on 23/01/2017.
 */
public class LicenceRelation {

    @NotNull
    @SerializedName("name")
    private Licence name;
    @NotNull
    @SerializedName("fields")
    private LinkedHashMap<Licence, Relation> relations;

    @NotNull
    public Licence getName() {
        return name;
    }

    @NotNull
    public LinkedHashMap<Licence, Relation> getRelations() {
        return relations;
    }

}