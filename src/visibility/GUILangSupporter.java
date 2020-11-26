package visibility;

public final class GUILangSupporter {
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
    public static final LangString STRUCT_NODE_CHOICE_MESSAGE = new LangString(
            "选择一个结点代号",
            "Choose an ID of node"
    );
    public static final LangString STRUCT_NODE_CHOICE_TITLE = new LangString(
            "选择结点",
            "Choose a node"
    );
    public static final LangString WARNING = new LangString(
            "警告",
            "Warning"
    );
    public static final LangString INFORMATION = new LangString(
            "信息",
            "Info"
    );

    public static final LangString SPONSORSHIP_THANKS = new LangString(
            "感谢您的好意, 叶焯仁心领了!",
            "Thanks for your kindness, Coekjan appreciate you kind."
    );

    public static final LangString FEEDBACK_INFO = new LangString(
            "反馈请加微信 : Shiuhing_Ycj",
            "Wechat for feedback : Shiuhing_Ycj"
    );

    public static final LangString STRUCT_NOT_ENOUGH_NODES_MESSAGE = new LangString(
            "结点数目不足.",
            "The number of nodes is not enough."
    );
    public static final LangString STRUCT_NOT_ENOUGH_SPACE_MESSAGE = new LangString(
            "结点过多, 空间不足",
            "The number of nodes is too big to display."
    );
    public static final LangString STRUCT_NODE_ID_TEXT = new LangString(
            "代号",
            "Identification"
    );
    public static final LangString STRUCT_NODE_DATA_TEXT = new LangString(
            "数据",
            "Data"
    );
    public static final LangString STRUCT_NODE_ADDER_APPEND = new LangString(
            "在尾部增加结点",
            "Add a node to the tail"
    );
    public static final LangString STRUCT_NODE_ADDER_AHEAD = new LangString(
            "在头部增加结点",
            "Add a node to the head"
    );
    public static final LangString STRUCT_TREE_NODE_LEFT_ROTATION = new LangString(
            "向左旋转结点",
            "Left-rotate node"
    );
    public static final LangString STRUCT_TREE_NODE_RIGHT_ROTATION = new LangString(
            "向右旋转结点",
            "Right-rotate node"
    );
    public static final LangString STRUCT_NODE_ADDER_INSERT_BEHIND = new LangString(
            "在选定结点后增加结点",
            "Add a node behind a specified node"
    );
    public static final LangString STRUCT_NODE_ADDER_INSERT_POS = new LangString(
            "在选定结点下新增结点",
            "Add a node beneath a specific node"
    );
    public static final LangString STRUCT_NODE_LEFT = new LangString(
            "左侧",
            "Left"
    );
    public static final LangString STRUCT_NODE_RIGHT = new LangString(
            "右侧",
            "Right"
    );
    public static final LangString STRUCT_NODE_DELETER_TAIL = new LangString(
            "从尾部删除结点",
            "Delete a node from tail"
    );
    public static final LangString STRUCT_NODE_DELETER_HEAD = new LangString(
            "从头部删除结点",
            "Delete a node from head"
    );
    public static final LangString STRUCT_NODE_DELETER_SELECTION = new LangString(
            "选择删除结点",
            "Delete a node you choose"
    );
    public static final LangString STRUCT_NODE_INSERT_POSITION_SELECTION_TEXT =  new LangString(
            "请选择一侧方向",
            "Please select a position"
    );
    public static final LangString STRUCT_NODE_INSERT_POSITION_SELECTION_TITLE = new LangString(
            "选择方向",
            "Position Selection"
    );
    public static final LangString BINARY_TREE_POSITION_CONFIRM_TEXT = new LangString(
            "确认方向",
            "Confirm the position"
    );
    public static final LangString BINARY_TREE_POSITION_CONFIRM_LEFT = new LangString(
            "此结点只有左边空余, 确认选择左边?",
            "For this node, only the LEFT is available, confirm it?"
    );
    public static final LangString BINARY_TREE_POSITION_CONFIRM_RIGHT = new LangString(
            "此结点只有右边空余, 确认选择右边?",
            "For this node, only the RIGHT is available, confirm it?"
    );
    public static final LangString DATA_ADD_MESSAGE = new LangString(
            "输入数据",
            "Input specific data"
    );
    public static final LangString DATA_ADD_DEFAULT = new LangString(
            "数据",
            "Data"
    );
    public static final LangString STRUCT_NODE_POINT_NULL = new LangString(
            "<空结点>",
            "<NULL>"
    );

    public static final LangString LINKED_LINEAR_LIST = new LangString(
            "链式线性表",
            "Linked Linear List"
    );
    public static final LangString LINKED_LINEAR_LIST_SINGLY_NOT_LOOP = new LangString(
            "单向链表",
            "Singly-Linked List"
    );
    public static final LangString LINKED_LINEAR_LIST_DOUBLE_NOT_LOOP = new LangString(
            "双向链表",
            "Doubly-Linked List"
    );
    public static final LangString BINARY_TREE = new LangString(
            "二叉树",
            "Binary Tree"
    );
    public static final LangString COMMON_BINARY_TREE = new LangString(
            "普通二叉树",
            "Common Binary Tree"
    );
    public static final LangString NLR_TRAVERSAL_BINARY_TREE = new LangString(
            "先序线索二叉树",
            "NLR-Threaded Binary Tree"
    );
    public static final LangString LNR_TRAVERSAL_BINARY_TREE = new LangString(
            "中序线索二叉树",
            "LNR-Threaded Binary Tree"
    );
    public static final LangString LRN_TRAVERSAL_BINARY_TREE = new LangString(
            "后序线索二叉树",
            "LRN-Threaded Binary Tree"
    );
    public static final LangString LINKED_NODE_NEXT_TEXT = new LangString(
            "向后链接到",
            "Next-linked to"
    );
    public static final LangString LINKED_NODE_PREV_TEXT = new LangString(
            "向前链接到",
            "Prev-Linked to"
    );
    public static final LangString LINKED_NODE_PARENT_TEXT = new LangString(
            "父结点",
            "Parent node"
    );
    public static final LangString LINKED_NODE_LEFT_CHILD_TEXT = new LangString(
            "左子结点",
            "Left-Child node"
    );
    public static final LangString LINKED_NODE_RIGHT_CHILD_TEXT = new LangString(
            "右子结点",
            "Right-Child node"
    );
}
