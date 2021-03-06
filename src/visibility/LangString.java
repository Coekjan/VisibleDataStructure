package visibility;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class LangString {
    public static final int LANG_NUM = 2;
    public static final int CHINESE = 0;
    public static final int ENGLISH = 1;
    public static String[] languages = {
            "简体中文",
            "English"
    };
    private final String[] langArray = new String[LANG_NUM];

    public LangString(String ch, String en) {
        langArray[0] = ch;
        langArray[1] = en;
    }

    @Override
    public String toString() {
        return langArray[GUILangSupporter.currentLangIndex];
    }
}