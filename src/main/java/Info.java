import com.google.gson.annotations.SerializedName;

/**
 * Created by kurt on 30/01/2017.
 */
public enum Info {

    @SerializedName("Commercial Use")
    COMMERCIAL_USE("Commercial Use"),
    @SerializedName("Distribution")
    DISRIBUTION("Distribution"),
    @SerializedName("Modification")
    MODIFICATION("Modification"),
    @SerializedName("Patent Use")
    PATENT_USE("Patent Use"),
    @SerializedName("Private Use")
    PRIVATE_USE("Private Use"),
    @SerializedName("Disclose Source")
    DISCLOSURE_SOURCE("Disclose Source"),
    @SerializedName("License and Copyright Notice")
    LICENCE_AND_COPYRIGHT_NOTICE("License and Copyright Notice"),
    @SerializedName("Network Use is Distribution")
    NETWORK_USE_IS_DISTRIBUTION("Network Use is Distribution"),
    @SerializedName("Same License")
    SAME_LICENCE("Same License"),
    @SerializedName("State Changes")
    STATE_CHANGES("State Changes"),
    @SerializedName("Liability")
    LIABILITY("Liability"),
    @SerializedName("Trademark Use")
    TRADEMARK_USE("Trademark Use"),
    @SerializedName("Warranty")
    WARRANTY("Warranty");

    private final String name;

    Info(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
