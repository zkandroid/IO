package IoOfJava.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 一般只要是后面加的有Reader或者Writer的都是以字符读写，否则是以字节读写
 * @author zk
 *
 */
public class ReadTest {
	
	public static void main(String [] args) throws IOException {
		ReadTest test = new ReadTest();
		test.readFile("");
	}
	
	
	public void readFile(String path) throws IOException {
		InputStreamReader in1= new FileReader("C:\\Users\\zk\\Desktop\\out.txt");
		char [] data = new char[1024];
		int len = in1.read(data);
		String str1="";
		while(len!=-1) {
			str1 +=new String(data);
			len=in1.read(data);
		}
		System.out.println(str1);
		in1.close();
	}

}
