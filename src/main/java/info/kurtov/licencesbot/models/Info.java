package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;

/**
 * Created by kurt on 30/01/2017.
 */
public enum Info {

    @SerializedName("Commercial Use")
    COMMERCIAL_USE(InfoGroup.PERMISSION, "Commercial Use", "The software may be used for commercial purposes"),
    @SerializedName("Distribution")
    DISRIBUTION(InfoGroup.PERMISSION, "Distribution", "The software may be freely distributed"),
    @SerializedName("Modification")
    MODIFICATION(InfoGroup.PERMISSION, "Modification", "The software may be freely modified"),
    @SerializedName("Private Use")
    PRIVATE_USE(InfoGroup.PERMISSION, "Private Use", "The software may be used for private purposes"),
    @SerializedName("Disclose Source")
    DISCLOSURE_SOURCE(InfoGroup.CONDITION, "Disclose Source", "There is an obligation to disclose source code, when software is distributed"),
    @SerializedName("License and Copyright Notice")
    LICENCE_AND_COPYRIGHT_NOTICE(InfoGroup.CONDITION, "License and Copyright Notice", "There is an obligation to put license and copyright notice"),
    @SerializedName("Network Use is Distribution")
    NETWORK_USE_IS_DISTRIBUTION(InfoGroup.CONDITION, "Network Use is Distribution", "Network use of the licensed product considered as distribution"),
    @SerializedName("Same License")
    SAME_LICENCE(InfoGroup.CONDITION, "Same License", "The modified software should be released under the same license"),
    @SerializedName("State Changes")
    STATE_CHANGES(InfoGroup.CONDITION, "State Changes", "There is an obligation to note all changes that is made to the code"),
    @SerializedName("Liability")
    LIABILITY(InfoGroup.LIMITATION, "Liability", "Liability is limited"),
    @SerializedName("Trademark Use")
    TRADEMARK_USE(InfoGroup.LIMITATION, "Trademark Use", "The license does not grant trademark rights"),
    @SerializedName("Warranty")
    WARRANTY(InfoGroup.LIMITATION, "Warranty", "The license does not provide any warranty");

    @NotNull
    private final InfoGroup group;
    @NotNull
    private final String name;
    @NotNull
    private final String description;

    Info(@NotNull final InfoGroup group, @NotNull final String name, @NotNull final String description) {
        this.group = group;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public InfoGroup getGroup() {
        return group;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

}