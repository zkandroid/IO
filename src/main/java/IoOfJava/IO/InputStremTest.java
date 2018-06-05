package IoOfJava.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * java的io操作大概分为四类：
 * 1，基于字节：inputStrem outputStrem
 * 2,基于字符：Writer Reader
 * 3,基于磁盘：file
 * 4，基于网络：Socket
 * @author zk
 *
 */
public class InputStremTest {
	
	public static void main(String [] args) throws IOException {
		
		InputStremTest test =new InputStremTest();
		test.readFile("");
		
	}
	
	
	public void readFile(String path) throws IOException {
		InputStream in = null;
		try {
			 in= new FileInputStream("C:\\Users\\zk\\Desktop\\新建文本文档.txt");
		} catch (FileNotFoundException e) {
			System.out.println("no found file");
		}
		/*
		int data = in.read();//返回读到的字节长度
		while(data != -1){
	        System.out.println(data);
	       // data = inputstream.read();
	        data =in.read();
	    }*/
		
		byte [] data = new byte[4];
		int    bytesRead = in.read(data);
		String str1="";
		while(bytesRead != -1) {
			//System.out.println("len:"+bytesRead);
			bytesRead = in.read(data);
			str1 += new String(data);				  
		}
		System.out.println(str1);
		
		
		in.close();
	}
	
	public void readSocket() {
		
	}

}
