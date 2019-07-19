package com.wxt.nio.basic;

/**
 * @Author: weixiaotao
 * @ClassName BufferTest
 * @Date: 2018/11/1 17:01
 * @Description:
 */

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
public class BufferTest {

	@Test
	public void test1(){
		String shein = "Hello Shein.....";
		//1.分配一个指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		System.out.println("======allocate()======");
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());

		//2.利用put()存入数据到缓冲区
		buffer.put(shein.getBytes());
		System.out.println("======put()======");
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());
		//3.切换数据读取模式 flip()
		buffer.flip();
		System.out.println("======flip()======");
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());
		//4.利用get()读取缓冲区中数据
		byte[] read = new byte[buffer.limit()];
		buffer.get(read);
		System.out.println("======get()======");
		System.out.println("read:" + new String(read,0,read.length));
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());
		//5. rewind() : 可重复读
		buffer.rewind();
		System.out.println("======rewind()======");
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());
		//6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
		buffer.clear();
		System.out.println("======clear()======");
		System.out.println("position:"+buffer.position()+",limit:" + buffer.limit()
				+",capacity:"+ buffer.capacity()+",mark:" + buffer.mark());
		System.out.println((char) buffer.get());


	}
}
