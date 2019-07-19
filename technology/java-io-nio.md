# IO

# NIO

nio入门手册 https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html#icomments



## 1.Java NIO 简介

​	Java NIO（New IO）是从Java 1.4版本开始引入的一个新的IO API，可以替代标准的Java IO API。NIO与原来的IO有同样的作用和目的，但是使用的方式完全不同，NIO支持面向缓冲区的、基于通道的IO操作。NIO将以更加高效的方式进行文件的读写操作。

## 2.Java NIO 与 IO 的主要区别

| IO                      | NIO                         |
| ----------------------- | --------------------------- |
| 面向流(Stream Oriented) | 面向缓冲区(Buffer Oriented) |
| 阻塞IO(Blocking IO)     | 非阻塞IO(Non Blocking       |
| /                       | 选择器(Selectors)           |

## 3.缓冲区(Buffer)和通道(Channel)

​	Java NIO系统的核心在于：通道(Channel)和缓冲区(Buffer)。通道表示打开到 IO 设备(例如：文件、套接字)的连接。若需要使用 NIO 系统，需要获取用于连接 IO 设备的通道以及用于容纳数据的缓冲区。然后操作缓冲区，对数据进行处理。

​	**简而言之，Channel 负责传输， Buffer 负责存储**

### 缓冲区（Buffer）	

​	缓冲区（Buffer）：一个用于特定基本数据类型的容器。由 java.nio 包定义的，所有缓冲区都是 Buffer 抽象类的子类。
​	 Java NIO 中的 Buffer 主要用于与 NIO 通道进行交互，**数据是从通道读入缓冲区，从缓冲区写入通道中的**。

​	Buffer 就像一个数组，可以保存多个相同类型的数据。根据数据类型不同(boolean 除外) ，有以下 Buffer 常用子类：ByteBuffer、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer

​	上述 Buffer 类 他们都采用相似的方法进行管理数据，只是各自管理的数据类型不同而已。都是通过如下方法获取一个 Buffer 对象：
​	static XxxBuffer allocate(int capacity) : 创建一个容量为 capacity 的 XxxBuffer 对象

#### 缓冲区的基本属性

Buffer 中的重要概念：
​	容量 (**capacity**) ：表示 Buffer 最大数据容量，缓冲区容量不能为负，并且创建后不能更改。
​	限制 (**limit**)：第一个不应该读取或写入的数据的索引，即位于 limit 后的数据不可读写。缓冲区的限制不能为负，并且不能大于其容量。
​	位置 (**position**)：下一个要读取或写入的数据的索引。缓冲区的位置不能为负，并且不能大于其限制

​	标记 (mark)与重置 (reset)：标记是一个索引，通过 Buffer 中的 mark() 方法指定 Buffer 中一个特定的 position，之后可以通过调用 reset() 方法恢复到这个 position.
​	标记、位置、限制、容量遵守以下不变式： 0 <= mark <= position <= limit <= capacity

![](.\image\io-nio\QQ截图20181031222017.png)

Buffer 的常用方法

![](.\image\io-nio\QQ截图20181031222138.png)

#### 缓冲区的数据操作

​	Buffer 所有子类提供了两个用于数据操作的方法：get() 与 put() 方法
​	获取 Buffer 中的数据
​		get() ：读取单个字节
​		get(byte[] dst)：批量读取多个字节到 dst 中
​		get(int index)：读取指定索引位置的字节(不会移动 position)

​	放入数据到 Buffer 中
​		put(byte b)：将给定单个字节写入缓冲区的当前位置
​		put(byte[] src)：将 src 中的字节写入缓冲区的当前位置
​		put(int index, byte b)：将指定字节写入缓冲区的索引位置(不会移动 position)

#### 直接与非直接缓冲区

非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率

​	1.字节缓冲区要么是直接的，要么是非直接的。如果为直接字节缓冲区，则 Java 虚拟机会尽最大努力直接在此缓冲区上执行本机 I/O 操作。也就是说，在每次调用基础操作系统的一个本机 I/O 操作之前（或之后）

​	2.虚拟机都会尽量避免将缓冲区的内容复制到中间缓冲区中（或从中间缓冲区中复制内容）。
​	3.直接字节缓冲区可以通过调用此类的 allocateDirect() 工厂方法来创建。此方法返回的缓冲区进行分配和取消.分配所需成本通常**高于**非直接缓冲区。直接缓冲区的内容可以驻留在常规的垃圾回收堆之外，因此，它们对应用程序的内存需求量造成的影响可能并不明显。所以，建议将直接缓冲区主要分配给那些易受基础系统的本机 I/O 操作影响的大型、持久的缓冲区。一般情况下，最好仅在直接缓冲区能在程序性能方面带来明显好处时分配它们。

​	4.直接字节缓冲区还可以通过 FileChannel 的 map() 方法 将文件区域直接映射到内存中来创建。该方法返回
MappedByteBuffer 。Java 平台的实现有助于通过 JNI 从本机代码创建直接字节缓冲区。如果以上这些缓冲区中的某个缓冲区实例指的是不可访问的内存区域，则试图访问该区域不会更改该缓冲区的内容，并且将会在访问期间或稍后的某个时间导致抛出不确定的异常。
​	5. 字节缓冲区是直接缓冲区还是非直接缓冲区可通过调用其 isDirect() 方法来确定。提供此方法是为了能够在性能关键型代码中执行显式缓冲区管理。

非直接缓冲区

![](.\image\io-nio\QQ截图20181031222326.png)

直接缓冲区

![](.\image\io-nio\QQ截图20181031222350.png)



### 通道（Channel）

​	通道（Channel）：由 java.nio.channels 包定义的。Channel 表示 IO 源与目标打开的连接。Channel 类似于传统的“流”。只不过 Channel 本身不能直接访问数据，Channel 只能与Buffer 进行交互。

![](.\image\io-nio\QQ截图20181101092223.png)

![](.\image\io-nio\QQ截图20181101092353.png)

![](.\image\io-nio\QQ截图20181101092403.png)



Java 为 Channel 接口提供的最主要实现类如下：
FileChannel：用于读取、写入、映射和操作文件的通道。
DatagramChannel：通过 UDP 读写网络中的数据通道。
SocketChannel：通过 TCP 读写网络中的数据。

ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。

#### 获取通道

​	获取通道的一种方式是对支持通道的对象调用getChannel() 方法。支持通道的类如下： FileInputStream
、FileOutputStream、RandomAccessFile、DatagramSocket、Socket、ServerSocket

​	获取通道的其他方式是使用 Files 类的静态方法 newByteChannel() 获取字节通道。或者通过通道的静态方法 open() 打开并返回指定通道。

#### 通道的数据传输

将 Buffer 中数据写入 Channel

```java
int bytesWritten = inChannel.write(buf);
```

从 Channel 读取数据到 Buffer

```java
int bytesRead = inChannel.read(buf);
```

#### 分散(Scatter)和聚集(Gather)

​	分散读取（Scattering Reads）是指从 Channel 中读取的数据“分散”到多个 Buffer 中。

​	**注意**：按照缓冲区的顺序，从 Channel 中读取的数据依次将 Buffer 填满。

![](.\image\io-nio\QQ截图20181101094518.png)

​	聚集写入（Gathering Writes）是指将多个 Buffer 中的数据“聚集”到 Channel。

​	**注意**：按照缓冲区的顺序，**写入 position 和 limit** 之间的数据到 Channel 。

![](.\image\io-nio\QQ截图20181101094829.png)

transferFrom()与transferTo()

将数据从源通道传输到其他 Channel 中：

![](.\image\io-nio\QQ截图20181101094942.png)

将数据从源通道传输到其他 Channel 中：

![](.\image\io-nio\QQ截图20181101095025.png)

FileChannel 的常用方法

![](.\image\io-nio\QQ截图20181101095206.png)

## 4.NIO 的非阻塞式网络通信

### 阻塞与非阻塞

​	传统的 IO 流都是阻塞式的。也就是说，当一个线程调用 read() 或 write() 时，该线程被阻塞，直到有一些数据被读取或写入，该线程在此期间不能执行其他任务。因此，在完成网络通信进行 IO 操作时，由于线程会阻塞，所以服务器端必须为每个客户端都提供一个独立的线程进行处理，服务器端需要处理大量客户端时，性能急剧下降。

​	Java NIO 是非阻塞模式的。当线程从某通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。线程通常将非阻塞 IO 的空闲时间用于在其他通道上执行 IO 操作，所以单独的线程可以管理多个输入
和输出通道。因此，NIO 可以让服务器端使用一个或有限几个线程来同时处理连接到服务器端的所有客户端。

### 选择器(Selector)

​	选择器（Selector） 是 SelectableChannle 对象的多路复用器，Selector 可以同时监控多个 SelectableChannel 的 IO 状况，也就是说，利用 Selector 可使一个单独的线程管理多个 Channel。Selector 是非阻塞 IO 的核心。
​	SelectableChannle 的结构如下图：

![](.\image\io-nio\QQ截图20181101100322.png)



选择器（Selector）的应用

创建 Selector ：通过调用 Selector.open() 方法创建一个 Selector。

向选择器注册通道：SelectableChannel.register(Selector sel, int ops)

![](.\image\io-nio\QQ截图20181101100631.png)

 	当调用 register(Selector sel, int ops) 将通道注册选择器时，选择器对通道的监听事件，需要通过第二个参数 ops 指定。
 可以监听的事件类型（可使用 SelectionKey 的四个常量表示）：
 读 : SelectionKey.OP_READ （1）
 写 : SelectionKey.OP_WRITE （4）
 连接 : SelectionKey.OP_CONNECT （8）
 接收 : SelectionKey.OP_ACCEPT （16）
 若注册时不止监听一个事件，则可以使用“位或”操作符连接。

```java
//注册监听事件
int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE；
```

​	SelectionKey：表示 SelectableChannel 和 Selector 之间的注册关系。每次向选择器注册通道时就会选择一个事件(选择键)。选择键包含两个表示为整数值的操作集。操作集的每一位都表示该键的通道所支持的一类可选择操作。

![](.\image\io-nio\QQ截图20181101101350.png)

Selector 的常用方法

![](.\image\io-nio\QQ截图20181101101428.png)



SocketChannel

​	Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。
​	操作步骤：
​		打开 SocketChannel
​		读写数据
​		关闭 SocketChannel

ServerSocketChannel

​	Java NIO中的 ServerSocketChannel 是一个可以监听新进来的TCP连接的通道，就像标准IO中的ServerSocket一样。

DatagramChannel

​	Java NIO中的DatagramChannel是一个能收发UDP包的通道。
​       操作步骤：
​		打开 DatagramChannel
​		接收/发送数据

## 5.管道(Pipe)

​	Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

![](.\image\io-nio\QQ截图20181101101721.png)

向管道写数据

![](.\image\io-nio\QQ截图20181101101806.png)

从管道读取数据

​	从读取管道的数据，需要访问source通道。

![](.\image\io-nio\QQ截图20181101102035.png)

​	调用source通道的read()方法来读取数据

![](.\image\io-nio\QQ截图20181101102041.png)

## 6.Java NIO2 (Path、Paths 与 Files )

​	随着 JDK 7 的发布，Java对NIO进行了极大的扩展，增强了对文件处理和文件系统特性的支持，以至于我们称他们为 NIO.2。因为 NIO 提供的一些功能，NIO已经成为文件处理中越来越重要的部分。

### Path 与 Paths

java.nio.file.Path 接口代表**一个平台无关的平台路径**，描述了目录结构中文件的位置。
 Paths 提供的 get() 方法用来获取 Path 对象：
 Path get(String first, String … more) : 用于将多个字符串串连成路径。
 Path 常用方法：
 boolean endsWith(String path) : 判断是否以 path 路径结束
 boolean startsWith(String path) : 判断是否以 path 路径开始
 boolean isAbsolute() : 判断是否是绝对路径
 Path getFileName() : 返回与调用 Path 对象关联的文件名
 Path getName(int idx) : 返回的指定索引位置 idx 的路径名称
 int getNameCount() : 返回Path 根目录后面元素的数量
 Path getParent() ：返回Path对象包含整个路径，不包含 Path 对象指定的文件路径
 Path getRoot() ：返回调用 Path 对象的根路径
 Path resolve(Path p) :将相对路径解析为绝对路径
 Path toAbsolutePath() : 作为绝对路径返回调用 Path 对象
 String toString() ： 返回调用 Path 对象的字符串表示形式

### Files 类

java.nio.file.Files 用于操作文件或目录的工具类。
 Files常用方法：
 Path copy(Path src, Path dest, CopyOption … how) : 文件的复制
 Path createDirectory(Path path, FileAttribute<?> … attr) : 创建一个目录
 Path createFile(Path path, FileAttribute<?> … arr) : 创建一个文件
 void delete(Path path) : 删除一个文件
 Path move(Path src, Path dest, CopyOption…how) : 将 src 移动到 dest 位置
 long size(Path path) : 返回 path 指定文件的大小

Files常用方法：用于判断
 boolean exists(Path path, LinkOption … opts) : 判断文件是否存在
 boolean isDirectory(Path path, LinkOption … opts) : 判断是否是目录
 boolean isExecutable(Path path) : 判断是否是可执行文件
 boolean isHidden(Path path) : 判断是否是隐藏文件
 boolean isReadable(Path path) : 判断文件是否可读
 boolean isWritable(Path path) : 判断文件是否可写
 boolean notExists(Path path, LinkOption … opts) : 判断文件是否不存在
 public static <A extends BasicFileAttributes> A readAttributes(Path path,Class<A> type,LinkOption... 
options) : 获取与 path 指定的文件相关联的属性。
 Files常用方法：用于操作内容
 SeekableByteChannel newByteChannel(Path path, OpenOption…how) : 获取与指定文件的连接，
how 指定打开方式。
 DirectoryStream newDirectoryStream(Path path) : 打开 path 指定的目录
 InputStream newInputStream(Path path, OpenOption…how):获取 InputStream 对象
 OutputStream newOutputStream(Path path, OpenOption…how) : 获取 OutputStream 对象

### 自动资源管理

​	Java 7 增加了一个新特性，该特性提供了另外一种管理资源的方式，这种方式能自动关闭文件。这个特性有时被称为自动资源管理(Automatic Resource Management, ARM)， 该特性以 try 语句的扩展版为基础。自动资源管理主要用于，当不再需要文件（或其他资源）时，可以防止无意中忘记释放它们。

自动资源管理基于 try 语句的扩展形式：
try(需要关闭的资源声明){
//可能发生异常的语句
}catch(异常类型 变量名){
//异常的处理语句
}
……
finally{
//一定执行的语句
}
当 try 代码块结束时，自动释放资源。因此不需要显示的调用 close() 方法。该形式也称为“带资源的 try 语句”。
注意：
①try 语句中声明的资源被隐式声明为 final ，资源的作用局限于带资源的 try 语句
②可以在一条 try 语句中管理多个资源，每个资源以“;” 隔开即可。
③需要关闭的资源，必须实现了 AutoCloseable 接口或其自接口 Closeable



# NIO 与 IO 区别

https://blog.csdn.net/zhouhl_cn/article/details/6568119 

​	传统的socket IO中，需要为每个连接创建一个线程，当并发的连接数量非常巨大时，线程所占用的栈内存和CPU线程切换的开销将非常巨大。使用NIO，不再需要为每个线程创建单独的线程，可以用一个含有限数量线程的线程池，甚至一个线程来为任意数量的连接服务。由于线程数量小于连接数量，所以每个线程进行IO操作时就不能阻塞，如果阻塞的话，有些连接就得不到处理，NIO提供了这种非阻塞的能力。

​	小量的线程如何同时为大量连接服务呢，答案就是就绪选择。这就好比到餐厅吃饭，每来一桌客人，都有一个服务员专门为你服务，从你到餐厅到结帐走人，这样方式的好处是服务质量好，一对一的服务，VIP啊，可是缺点也很明显，成本高，如果餐厅生意好，同时来100桌客人，就需要100个服务员，那老板发工资的时候得心痛死了，这就是传统的一个连接一个线程的方式。

​	老板是什么人啊，精着呢。这老板就得捉摸怎么能用10个服务员同时为100桌客人服务呢，老板就发现，服务员在为客人服务的过程中并不是一直都忙着，客人点完菜，上完菜，吃着的这段时间，服务员就闲下来了，可是这个服务员还是被这桌客人占用着，不能为别的客人服务，用华为领导的话说，就是工作不饱满。那怎么把这段闲着的时间利用起来呢。这餐厅老板就想了一个办法，让一个服务员（前台）专门负责收集客人的需求，登记下来，比如有客人进来了、客人点菜了，客人要结帐了，都先记录下来按顺序排好。每个服务员到这里领一个需求，比如点菜，就拿着菜单帮客人点菜去了。点好菜以后，服务员马上回来，领取下一个需求，继续为别人客人服务去了。这种方式服务质量就不如一对一的服务了，当客人数据很多的时候可能需要等待。但好处也很明显，由于在客人正吃饭着的时候服务员不用闲着了，服务员这个时间内可以为其他客人服务了，原来10个服务员最多同时为10桌客人服务，现在可能为50桌，60客人服务了。

这种服务方式跟传统的区别有两个：

1、增加了一个角色，要有一个专门负责收集客人需求的人。NIO里对应的就是Selector。

2、由阻塞服务方式改为非阻塞服务了，客人吃着的时候服务员不用一直侯在客人旁边了。传统的IO操作，比如read()，当没有数据可读的时候，线程一直阻塞被占用，直到数据到来。NIO中没有数据可读时，read()会立即返回0，线程不会阻塞。

 	NIO中，客户端创建一个连接后，先要将连接注册到Selector，相当于客人进入餐厅后，告诉前台你要用餐，前台会告诉你你的桌号是几号，然后你就可能到那张桌子坐下了，SelectionKey就是桌号。当某一桌需要服务时，前台就记录哪一桌需要什么服务，比如1号桌要点菜，2号桌要结帐，服务员从前台取一条记录，根据记录提供服务，完了再来取下一条。这样服务的时间就被最有效的利用起来了。

![](.\image\io-nio\传统IO.png)

![阻塞IO](.\image\io-nio\阻塞IO.png)

![](.\image\io-nio\NIIO.png)

![NIO非阻塞IO](.\image\io-nio\NIO非阻塞IO.png)