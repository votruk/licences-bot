package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;


public enum Licence {

    @SerializedName("Academic Free License v3.0")
    ACADEMIC_FREE("Academic Free License v3.0", 0),
    @SerializedName("GNU Affero General Public License v3.0")
    GNU_AFFERO("GNU Affero General Public License v3.0", 1),
    @SerializedName("Apache License 2.0")
    APACHE("Apache License 2.0", 2),
    @SerializedName("Artistic License 2.0")
    ARTISTIC("Artistic License 2.0", 3),
    @SerializedName("BSD 2-clause \"Simplified\" License")
    BSD_2_SIMPLIFIED("BSD 2-clause \"Simplified\" License", 4),
    @SerializedName("BSD 3-clause Clear License")
    BSD_3_CLAUSE("BSD 3-clause Clear License", 5),
    @SerializedName("BSD 3-clause \"New\" or \"Revised\" License")
    BSD_3_NEW("BSD 3-clause \"New\" or \"Revised\" License", 6),
    @SerializedName("Boost Software License 1.0")
    BOOST_SOFTWARE("Boost Software License 1.0", 7),
    @SerializedName("Creative Commons Attribution 4.0")
    CC_ATTRIBUTION_4("Creative Commons Attribution 4.0", 8),
    @SerializedName("Creative Commons Attribution Share Alike 4.0")
    CC_ATTRIBUTION_SHARE_ALIKE("Creative Commons Attribution Share Alike 4.0", 9),
    @SerializedName("Creative Commons Zero v1.0 Universal")
    CC_ZERO("Creative Commons Zero v1.0 Universal", 10),
    @SerializedName("Eclipse Public License 1.0")
    ECLIPSE("Eclipse Public License 1.0", 11),
    @SerializedName("European Union Public License 1.1")
    EU_PUBLIC("European Union Public License 1.1", 12),
    @SerializedName("GNU General Public License v2.0")
    GNU_2("GNU General Public License v2.0", 13),
    @SerializedName("GNU General Public License v3.0")
    GNU_3("GNU General Public License v3.0", 14),
    @SerializedName("ISC License")
    ISC("ISC License", 15),
    @SerializedName("GNU Lesser General Public License v2.1")
    GNU_LESSER_2_1("GNU Lesser General Public License v2.1", 16),
    @SerializedName("GNU Lesser General Public License v3.0")
    GNU_LESSER_3("GNU Lesser General Public License v3.0", 17),
    @SerializedName("LaTeX Project Public License v1.3c")
    LATEX("LaTeX Project Public License v1.3c", 18),
    @SerializedName("MIT License")
    MIT("MIT License", 19),
    @SerializedName("Mozilla Public License 2.0")
    MOZILLA("Mozilla Public License 2.0", 20),
    @SerializedName("Microsoft Public License")
    MICROSOFT_PUBLIC("Microsoft Public License", 21),
    @SerializedName("Microsoft Reciprocal License")
    MICROSOFT_RECIPROCAL("Microsoft Reciprocal License", 22),
    @SerializedName("SIL Open Font License 1.1")
    SIL("SIL Open Font License 1.1", 23),
    @SerializedName("Open Software License 3.0")
    OPEN_SOFTWARE("Open Software License 3.0", 24),
    @SerializedName("The Unlicense")
    UNLICENCE("The Unlicense", 25),
    @SerializedName("Do What The F*ck You Want To Public License")
    DWTFYWTPS("Do What The F*ck You Want To Public License", 26),
    @SerializedName("zlib License")
    ZLIB("zlib License", 27);

    @NotNull
    private final String name;
    private int order;

    Licence(@NotNull final String name, final int order) {
        this.name = name;
        this.order = order;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

}