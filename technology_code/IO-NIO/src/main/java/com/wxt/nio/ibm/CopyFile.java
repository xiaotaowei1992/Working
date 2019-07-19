package com.wxt.nio.ibm;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class CopyFile
{
  static public void main( String args[] ) throws Exception {

//    String infile = "D:/nio/copy.txt";
//    String outfile = "D:/nio/copy(1).txt";
//12354
	  String infile = "D:/nio/movie/1000.avi";
	  String outfile = "D:/nio/movie/1000(1).avi";
    long start = System.currentTimeMillis();
    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    while (true) {
      buffer.clear();

      int r = fcin.read( buffer );

      if (r==-1) {
        break;
      }

      buffer.flip();

      fcout.write( buffer );

    }
	  System.out.println(System.currentTimeMillis() - start);
  }
}
