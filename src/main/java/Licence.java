import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;


public enum Licence {

    @SerializedName("Academic Free License v3.0")
    ACADEMIC_FREE("Academic Free License v3.0"),
    @SerializedName("GNU Affero General Public License v3.0")
    GNU_AFFERO("GNU Affero General Public License v3.0"),
    @SerializedName("Apache License 2.0")
    APACHE("Apache License 2.0"),
    @SerializedName("Artistic License 2.0")
    ARTISTIC("Artistic License 2.0"),
    @SerializedName("BSD 2-clause \"Simplified\" License")
    BSD_2_SIMPLIFIED("BSD 2-clause \"Simplified\" License"),
    @SerializedName("BSD 3-clause Clear License")
    BSD_3_CLAUSE("BSD 3-clause Clear License"),
    @SerializedName("BSD 3-clause \"New\" or \"Revised\" License")
    BSD_3_NEW("BSD 3-clause \"New\" or \"Revised\" License"),
    @SerializedName("Boost Software License 1.0")
    BOOST_SOFTWARE("Boost Software License 1.0"),
    @SerializedName("Creative Commons Attribution 4.0")
    CC_ATTRIBUTION_4("Creative Commons Attribution 4.0"),
    @SerializedName("Creative Commons Attribution Share Alike 4.0")
    CC_ATTRIBUTION_SHARE_ALIKE("Creative Commons Attribution Share Alike 4.0"),
    @SerializedName("Creative Commons Zero v1.0 Universal")
    CC_ZERO("Creative Commons Zero v1.0 Universal"),
    @SerializedName("Eclipse Public License 1.0")
    ECLIPSE("Eclipse Public License 1.0"),
    @SerializedName("European Union Public License 1.1")
    EU_PUBLIC("European Union Public License 1.1"),
    @SerializedName("GNU General Public License v2.0")
    GNU_2("GNU General Public License v2.0"),
    @SerializedName("GNU General Public License v3.0")
    GNU_3("GNU General Public License v3.0"),
    @SerializedName("ISC License")
    ISC("ISC License"),
    @SerializedName("GNU Lesser General Public License v2.1")
    GNU_LESSER_2_1("GNU Lesser General Public License v2.1"),
    @SerializedName("GNU Lesser General Public License v3.0")
    GNU_LESSER_3("GNU Lesser General Public License v3.0"),
    @SerializedName("LaTeX Project Public License v1.3c")
    LATEX("LaTeX Project Public License v1.3c"),
    @SerializedName("MIT License")
    MIT("MIT License"),
    @SerializedName("Mozilla Public License 2.0")
    MOZILLA("Mozilla Public License 2.0"),
    @SerializedName("Microsoft Public License")
    MICROSOFT_PUBLIC("Microsoft Public License"),
    @SerializedName("Microsoft Reciprocal License")
    MICROSOFT_RECIPROCAL("Microsoft Reciprocal License"),
    @SerializedName("SIL Open Font License 1.1")
    SIL("SIL Open Font License 1.1"),
    @SerializedName("Open Software License 3.0")
    OPEN_SOFTWARE("Open Software License 3.0"),
    @SerializedName("The Unlicense")
    UNLICENCE("The Unlicense"),
    @SerializedName("Do What The F*ck You Want To Public License")
    DWTFYWTPS("Do What The F*ck You Want To Public License"),
    @SerializedName("zlib License")
    ZLIB("zlib License");


    @NotNull
    private final String title;

    Licence(@NotNull final String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

}