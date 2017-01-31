package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * Created by kurt on 31/01/2017.
 */
public enum InfoGroup {

    @SerializedName("Permission")
    PERMISSION("Permission", "How licensed software can be used"),
    @SerializedName("Condition")
    CONDITION("Conditions", "Conditions that should be met to use license"),
    @SerializedName("Limitation")
    LIMITATION("Limitation", null);

    @NotNull
    private final String title;
    @Nullable
    private final String description;

    InfoGroup(@NotNull final String title, @Nullable final String description) {
        this.title = title;
        this.description = description;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

}