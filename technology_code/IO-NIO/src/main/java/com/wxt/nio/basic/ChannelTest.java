package com.wxt.nio.basic;

/**
 * @Author: weixiaotao
 * @ClassName ChannelTest
 * @Date: 2018/11/1 17:01
 * @Description:
 */

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：FileInputStream/FileOutputStream/RandomAccessFile
 *
 * 		网络IO：
 * 		Socket、ServerSocket、DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 */
public class ChannelTest {
	/**
	 * 利用通道完成文件的复制--非直接缓冲区
	 * 1G:9981 8161
	 * 100M:968 1011
	 * 10M:94 91
	 */
	@Test
	public void test1(){
		long start = System.currentTimeMillis();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		//1.获取通道
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("D:/nio/movie/10.avi");
			fos = new FileOutputStream("D:/nio/movie/10(2).avi");
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			//2.分配指定大小的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//3.将通道中的数据存入到缓冲区中
			while (inChannel.read(buffer) != -1){
				buffer.flip();//切换成写模式（重要）,不切换->文件无法写入,只能写入1024大小的文件至缓冲区，无法从缓冲区写入到通道
				outChannel.write(buffer);
				buffer.clear();//清空缓冲区,不清空的还文件最后100M->无限大
			}
		} catch (IOException e){

		} finally {
			if(outChannel != null){
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(inChannel != null){
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("耗费时间为：" + (end - start));

	}

	/**
	 * 使用直接缓冲区完成文件的复制(内存映射文件)
	 * 1G:11267 13218
	 * 100M: 240 228
	 * 10M: 22 18
	 */
	@Test
	public void test2() throws IOException{
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("D:/nio/movie/1000.avi"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("D:/nio/movie/1000(2).avi"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

		//内存映射文件
		MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
		MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
		//直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inBuffer.limit()];
		inBuffer.get(dst);
		outBuffer.put(dst);
		inChannel.close();
		outChannel.close();
		long end = System.currentTimeMillis();
		System.out.println("耗费时间为：" + (end - start));
	}

	/**
	 * 通道之间的数据传输: transferFrom() transferTo()
	 * 通道之间的数据传输(直接缓冲区)
	 * 1G 3019 2858 1371
	 * 100M 199(同一个文件的写入，第一次写快些，后面的写入慢 717)
	 * 10M 53 （文件小，多次几乎无差别）
	 */
	@Test
	public void test3() throws IOException{
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("D:/nio/movie/1000.avi"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("D:/nio/movie/1000(2).avi"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

		inChannel.transferTo(0,inChannel.size(),outChannel);
		//outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.close();
        outChannel.close();
		long end = System.currentTimeMillis();
		System.out.println("耗费时间为：" + (end - start));
	}

	/**
	 *
	 * 分散(Scatter)与聚集(Gather)
	 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
	 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
	 */
	@Test
	public void test4() throws IOException{
		RandomAccessFile raf1 = new RandomAccessFile("shein.txt","rw");
		//1.获取通道
		FileChannel channel1 = raf1.getChannel();
		//2.分配指定大小的缓冲区
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(2048);
		//3.分散读取
		ByteBuffer[] buffers = {buffer1,buffer2};
		channel1.read(buffers);

		for(ByteBuffer buffer: buffers){
			buffer.flip();//不切换读写模式的话，后面无法写入
		}
		System.out.println(new String(buffers[0].array(),0,buffers[0].limit()));
		System.out.println("------------");
		System.out.println(new String(buffers[1].array(),0,buffers[1].limit()));

		//4.聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("shein2.txt","rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(buffers);
	}

	/**
	 * 字符集：Charset
	 * 编码：字符串 -> 字节数组
	 * 解码：字节数组  -> 字符串
	 */
	@Test
	public void test() throws IOException{
		/*Map<String,Charset> maps = Charset.availableCharsets();
		Set<Map.Entry<String, Charset>> set = maps.entrySet();
		for (Map.Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}*/


		Charset cs = Charset.forName("UTF-8");
		//获取编码器
		CharsetEncoder ce = cs.newEncoder();
		//获取解码器
		CharsetDecoder cd = cs.newDecoder();
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		charBuffer.put("南京领添信息科技有限公司");
		charBuffer.flip();
        //编码
		ByteBuffer buffer = ce.encode(charBuffer);
		for(int i = 0;i<36;i++){
			System.out.print(buffer.get());
		}
		System.out.println("--------");
		buffer.flip();
		//解码
		CharBuffer charBuffer1 = cd.decode(buffer);
		System.out.println(charBuffer1.toString());
		System.out.println("--------");
		Charset cs2 = Charset.forName("GBK");
		buffer.flip();
		CharBuffer cBuf3 = cs2.decode(buffer);
		System.out.println(cBuf3.toString());
	}
}
