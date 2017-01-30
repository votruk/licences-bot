package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurt on 24/01/2017.
 */
public enum Relation {

    @SerializedName("yes")
    YES,
    @SerializedName("no")
    NO,
    @SerializedName("ng")
    NG,
    @SerializedName("")
    UNDEFINED

}
