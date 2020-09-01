package visibility;

public class GlobalUserInterfaceLangController {
    public static int currentLangIndex = LangString.CHINESE;

    public static final LangString TITLE = new LangString(
            "数据结构可视化",
            "Visible Data Structure"
    );
    public static final LangString MENU_TEXT_FILE = new LangString(
            "文件",
            "File"
    );
    public static final LangString MENU_TEXT_ABOUT = new LangString(
            "关于",
            "About"
    );
    public static final LangString MENU_TEXT_FILE_NEW = new LangString(
            "新建",
            "New File"
    );
    public static final LangString MENU_TEXT_FILE_OPEN = new LangString(
            "打开",
            "Open File"
    );
    public static final LangString MENU_TEXT_FILE_SAVE = new LangString(
            "保存",
            "Save File"
    );
    public static final LangString MENU_TEXT_FILE_LANG = new LangString(
            "语言",
            "Languages"
    );
    public static final LangString MENU_TEXT_FILE_EXIT = new LangString(
            "退出",
            "Exit"
    );
    public static final LangString MENU_TEXT_ABOUT_SPONSOR = new LangString(
            "资助",
            "Sponsor"
    );
    public static final LangString MENU_TEXT_ABOUT_FEEDBACK = new LangString(
            "反馈",
            "Feedback"
    );
    public static final LangString MENU_TEXT_ABOUT_AUTHOR = new LangString(
            "作者",
            "Author"
    );
    public static final LangString AUTHOR = new LangString(
            "@叶焯仁",
            "@Yip Coekjan"
    );
    public static final LangString LANG_CHOOSE_TITLE = new LangString(
            "选择一种语言",
            "Choose one language"
    );
    public static final LangString EXIT_BUT_NOT_SAVE_TITLE = new LangString(
            "警告",
            "Warning"
    );
    public static final LangString EXIT_BUT_NOT_SAVE_MESSAGE = new LangString(
            "文件未保存, 确认退出吗?",
            "The current file is not saved yet, confirm to exit?"
    );

    public static final LangString STRUCTURE_SWITCH_WARNING_MESSAGE = new LangString(
            "文件未保存, 切换数据结构将丢失文件数据! 确认切换吗?",
            "The file is not saved yet. if the structure is switched now, the data of file will be discarded! Confirm to switch?"
    );
    public static final LangString STRUCTURE_SWITCH_WARNING_TITLE = new LangString(
            "警告",
            "Warning"
    );

    public static final LangString LINKED_LINEAR_LIST = new LangString(
            "链式线性表",
            "Linked Linear List"
    );
    public static final LangString LINKED_LINEAR_LIST_SINGLY_NOT_LOOP = new LangString(
            "单向不循环链表",
            "Singly Linked List"
    );
    public static final LangString LINKED_LINEAR_LIST_DOUBLE_NOT_LOOP = new LangString(
            "双向不循环链表",
            "Doubly Linked List"
    );
}
