# 简介

速度更快
代码更少（增加了新的语法 **Lambda 表达式**）
强大的 **Stream API**
便于并行
最大化减少空指针异常 Optional

# 1.Lambda 表达式

为什么使用 Lambda 表达式

​	Lambda 是一个**匿名函数**，我们可以把 Lambda 表达式理解为是**一段可以传递的代码**（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使
Java的语言表达能力得到了提升。

从匿名类到 Lambda 的转换

```java
public 
```

​	Lambda 表达式在Java 语言中引入了一个新的语法元素和操作符。这个操作符为 “->” ， 该操作符被称为 Lambda 操作符或剪头操作符。它将 Lambda 分为两个部分：
	左侧：指定了 Lambda 表达式需要的所有参数
	右侧：指定了 Lambda 体，即 Lambda 表达式要执行的功能。

Lambda 表达式语法

语法格式一：无参，无返回值，Lambda 体只需一条语句

语法格式二：Lambda 需要一个参数

语法格式三：Lambda 只需要一个参数时，参数的小括号可以省略

语法格式四：Lambda 需要两个参数，并且有返回值

语法格式五：当 Lambda 体只有一条语句时，return 与大括号可以省略

类型推断

​	上述 Lambda 表达式中的参数类型都是由编译器推断得出的。Lambda 表达式中无需指定类型，程序依然可以编译，这是因为 javac 根据程序的上下文，在后台推断出了参数的类型。Lambda 表达式的类型依赖于上下文环境，是由编译器推断出来的。这就是所谓的“类型推断”

# 2.函数式接口

# 3.方法引用与构造器引用

# 4.Stream API

# 5.接口中的默认方法与静态方法

# 6.新时间日期 API

# 7.其他新特性





1.分组

```java
payApplyEntities.stream().collect(groupingBy(p -> p.getOrgId() + "&" + p.getSupplierCode().toLowerCase() + "&" + p.getCurId()
                + "&" + p.getBankAccount() + "&" + p.getBankCity() + "&" + p.getBankName() + "&" + p.getBankProvince() + "&" + p.getBranchName()
                + "&" + p.getPayee() + "&" + p.getPayeeMobile() + "&" + p.getPayeeNumber()
        ));
```

