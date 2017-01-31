package info.kurtov.licencesbot.models;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;


public enum Licence {

    @SerializedName("Academic Free License v3.0")
    ACADEMIC_FREE("Academic Free License v3.0", 0, "https://opensource.org/licenses/afl-3.0"),
    @SerializedName("GNU Affero General Public License v3.0")
    GNU_AFFERO("GNU Affero General Public License v3.0", 1, "https://www.gnu.org/licenses/agpl-3.0.txt"),
    @SerializedName("Apache License 2.0")
    APACHE("Apache License 2.0", 2, "http://www.apache.org/licenses/LICENSE-2.0.html"),
    @SerializedName("Artistic License 2.0")
    ARTISTIC("Artistic License 2.0", 3, "http://www.perlfoundation.org/attachment/legal/artistic-2_0.txt"),
    @SerializedName("BSD 2-clause \"Simplified\" License")
    BSD_2_SIMPLIFIED("BSD 2-clause \"Simplified\" License", 4, "https://opensource.org/licenses/BSD-2-Clause"),
    @SerializedName("BSD 3-clause Clear License")
    BSD_3_CLAUSE("BSD 3-clause Clear License", 5, "https://spdx.org/licenses/BSD-3-Clause-Clear.html"),
    @SerializedName("BSD 3-clause \"New\" or \"Revised\" License")
    BSD_3_NEW("BSD 3-clause \"New\" or \"Revised\" License", 6, "https://opensource.org/licenses/BSD-3-Clause"),
    @SerializedName("Boost Software License 1.0")
    BOOST_SOFTWARE("Boost Software License 1.0", 7, "https://opensource.org/licenses/BSL-1.0"),
    @SerializedName("Creative Commons Attribution 4.0")
    CC_ATTRIBUTION_4("Creative Commons Attribution 4.0", 8, "https://creativecommons.org/licenses/by/4.0/legalcode.txt"),
    @SerializedName("Creative Commons Attribution Share Alike 4.0")
    CC_ATTRIBUTION_SHARE_ALIKE("Creative Commons Attribution Share Alike 4.0", 9, "https://creativecommons.org/licenses/by-sa/4.0/legalcode.txt"),
    @SerializedName("Creative Commons Zero v1.0 Universal")
    CC_ZERO("Creative Commons Zero v1.0 Universal", 10, "https://creativecommons.org/publicdomain/zero/1.0/"),
    @SerializedName("Eclipse Public License 1.0")
    ECLIPSE("Eclipse Public License 1.0", 11, "https://www.eclipse.org/legal/epl-v10.html"),
    @SerializedName("European Union Public License 1.1")
    EU_PUBLIC("European Union Public License 1.1", 12, "https://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11"),
    @SerializedName("GNU General Public License v2.0")
    GNU_2("GNU General Public License v2.0", 13, "https://www.gnu.org/licenses/gpl-2.0.txt"),
    @SerializedName("GNU General Public License v3.0")
    GNU_3("GNU General Public License v3.0", 14, "https://www.gnu.org/licenses/gpl-3.0.txt"),
    @SerializedName("ISC License")
    ISC("ISC License", 15, "https://opensource.org/licenses/isc-license"),
    @SerializedName("GNU Lesser General Public License v2.1")
    GNU_LESSER_2_1("GNU Lesser General Public License v2.1", 16, "https://www.gnu.org/licenses/lgpl-2.1.txt"),
    @SerializedName("GNU Lesser General Public License v3.0")
    GNU_LESSER_3("GNU Lesser General Public License v3.0", 17, "https://www.gnu.org/licenses/lgpl-3.0.txt"),
    @SerializedName("LaTeX Project Public License v1.3c")
    LATEX("LaTeX Project Public License v1.3c", 18, "https://www.latex-project.org/lppl/lppl-1-3c/"),
    @SerializedName("MIT License")
    MIT("MIT License", 19, "https://opensource.org/licenses/MIT"),
    @SerializedName("Mozilla Public License 2.0")
    MOZILLA("Mozilla Public License 2.0", 20, "https://www.mozilla.org/media/MPL/2.0/index.txt"),
    @SerializedName("Microsoft Public License")
    MICROSOFT_PUBLIC("Microsoft Public License", 21, "https://opensource.org/licenses/ms-pl"),
    @SerializedName("Microsoft Reciprocal License")
    MICROSOFT_RECIPROCAL("Microsoft Reciprocal License", 22, "https://opensource.org/licenses/ms-rl"),
    @SerializedName("SIL Open Font License 1.1")
    SIL("SIL Open Font License 1.1", 23, "http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=OFL_web"),
    @SerializedName("Open Software License 3.0")
    OPEN_SOFTWARE("Open Software License 3.0", 24, "https://opensource.org/licenses/OSL-3.0"),
    @SerializedName("The Unlicense")
    UNLICENCE("The Unlicense", 25, "http://unlicense.org/UNLICENSE"),
    @SerializedName("Do What The F*ck You Want To Public License")
    DWTFYWTPS("Do What The F*ck You Want To Public License", 26, "http://www.wtfpl.net/"),
    @SerializedName("zlib License")
    ZLIB("zlib License", 27, "https://opensource.org/licenses/Zlib");

    @NotNull
    private final String name;
    @NotNull
    private final String link;
    private int order;

    Licence(@NotNull final String name, final int order, @NotNull final String link) {
        this.name = name;
        this.order = order;
        this.link = link;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    @NotNull
    public String getLink() {
        return link;
    }
}