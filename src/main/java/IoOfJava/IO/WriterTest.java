package IoOfJava.IO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class WriterTest {
	public static void main(String [] args) throws IOException {
		WriterTest test = new WriterTest();
		test.writeFile("");
	}
	
	
	public void writeFile(String path) throws IOException {
		OutputStreamWriter out1 = new FileWriter("C:\\Users\\zk\\Desktop\\out.txt",true);//true表示从文件的结尾写
		char [] data = new char[20];
		String str1 = "THIS IS char ";
		data =str1.toCharArray();
		out1.write(data);
		out1.flush();
		out1.close();
		
	}

}
