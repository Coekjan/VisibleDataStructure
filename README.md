# 可视化数据结构(Visible Data Structure)

**基本信息**

项目 | 内容
:----: | :---
软件名称 | 可视化数据结构
开发者 | 19373372叶焯仁
开发时间 | 2020年秋季
总代码行数 | 2297

----

## 软件综述

### 开发环境

本软件使用**IntelliJ IDEA**集成开发环境(调配**JDK13**)进行开发.

### 功能简述

本软件提供两类基础数据结构的可视化编辑.

#### 链表

对于链表, 本软件提供6种操作:
* 插入操作包括三个
  * 头部插入
  * 尾部插入
  * 选定结点后插入
* 删除操作包括三个
  * 头部删除
  * 尾部删除
  * 选定结点后删除

对于链表, 本软件提供其两种子类的可视化演示:
* 单向不循环链表
* 双向不循环链表

对比这两种链表, 演示的区别在于连接两结点的图形, **单向**链表采用单向箭头, **双向**链表采用双向箭头; 并且在点击结点时, **双向链表**的结点将在右下角提供比**单向链表**多一个信息: 前驱结点.

#### 二叉树

对于二叉树, 本软件提供4种操作:
* 插入
* 删除
* 左旋
* 右旋

有以下要点需要注意:
1. 插入操作只允许发生在**至少**有一个孩子为空的结点, 并需要指明插入的方向(*若只有一个孩子为空, 则询问是否选取该方向*). 首次插入将作为根结点的初始化.
2. 删除操作需要指明方向(*若只有一个孩子非空, 则询问是否选择该方向; 若双子皆空, 则直接删除, 不存在后续的替换逻辑*), 软件将根据所选择的方向来选择替代被删除结点的继承者. 具体选择逻辑为: 界面中指定方向的**水平距离**最近者.
3. 旋转操作只允许发生在可以旋转的结点, 选定结点的将在旋转后处于其父结点之上. 具体操作不再详述, 就是简单的树旋转.

对于二叉树, 本软件提供4种视觉展示:
* 普通展示(即展示父子结点的相连关系)
* 先序遍历顺序
* 中序遍历顺序
* 后序遍历顺序

具体说明:
1. 普通展示将以双向箭头连接父子结点.
2. 后三种遍历展示, 将隐藏双向箭头, 并以单向箭头展示遍历的顺序.
3. 四种展示均能通过树的结点分布, 合理安排结点的位置.

## 设计与实现

本软件是作者首个应用面向对象开发的软件, 主要的**继承**与**实现**(**Extends** & **Implements**)脉络如下:

* 界面部分(`visibility`包)
  * `GUIFramework`与`GUILangSupporter`实现界面的控制与语言控制
  * 提供`DrawablePane`与`CanvasPairController`抽象类, 从而使得界面控制可扩展.
  * 提供`ButtonPairShapeConstructor`与`CanvasPairControllerConstructor`两个接口, 使得界面类(即上述的抽象界面)可以实时实例化.
* 数据结构控制器(`visibleStructure`包)
  * `ArrowLine`类用于获取箭头图形
  * `StructureNodeController`抽象类是所有可视结点的公用抽象父类.
    * `GeneralLinkedNodeController`抽象类继承了`StructureNodeController`, 并添加了一些链表结点的共有特性.
    * `GeneralBinaryTreeNodeController`抽象类继承了`StructureNodeController`, 并添加了一些二叉树结点的共有特性.
  * `visibility`包中的`CanvasPairController`是所有可视结构的公用抽象父类.
    * `GeneralLinkedListController`抽象类继承了`CanvasPairController`, 并添加了一些链表的公有方法与特性.
    * `GeneralBinaryTreeController`抽象类继承了`CanvasPairController`, 并添加了一些二叉树的公有方法与特性.

对于上述抽象类, 进行了一系列的继承扩充.
* 抽象类`GeneralLinkedListController`
  * 子类`SinglyLinkedListController`, 其内部`SinglyLinkedNodeController`类继承`GeneralLinkedNodeController`.
  * 子类`DoublyLinkedListController`, 其内部`DoublyLinkedNodeController`类继承`GeneralLinkedNodeController`.
* 抽象类`GeneralBinaryTreeController`
  * 子类`CommonBinaryTreeController`, 其内部`CommonBinaryTreeNodeController`类继承`GeneralBinaryTreeNodeController`.
    * 抽象类`TraversalBinaryTreeController`继承了`CommonBinaryTreeController`, 其内部`TraversalBinaryTreeNodeController`类继承`GeneralBinaryTreeNodeController`. 提供抽象的更新函数`void TraversalBinaryTreeController::refactorThread()`.
      * `TraversalBinaryTreeController`的三个子类`NLRBinaryTreeController`, `LNRBinaryTreeController`, `LRNBinaryTreeController`都实现了该更新函数, 从而实现三种不同的二叉树遍历展示方式.

**层层继承, 从而提升代码复用率, 是本软件以少行数实现多功能的基石.**

## 反思与展望

1. 限于时间原因, 本软件提供的可视化数据结构类型并不丰富, 但由于采用了较好的代码架构, 扩展性开发绝不是问题.
2. 限于时间原因, 本软件并未采用文件形式对操作进行存储, 因此并不利于教学的课前准备, 与远程传输. 后续有机会的话, 将添加此功能.