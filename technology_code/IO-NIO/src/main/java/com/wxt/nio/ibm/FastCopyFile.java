package com.wxt.nio.ibm;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class FastCopyFile
{
  static public void main( String args[] ) throws Exception {
    //    String infile = "D:/nio/copy.txt";
    //    String outfile = "D:/nio/copy(1).txt";
    long start = System.currentTimeMillis();
    //10969
    String infile = "D:/nio/movie/1000.avi";
    String outfile = "D:/nio/movie/1000(2).avi";

    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();

    ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );

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
