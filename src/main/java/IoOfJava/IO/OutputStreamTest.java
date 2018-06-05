package IoOfJava.IO;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamTest {
	public static void main(String [] args) throws IOException {
		OutputStreamTest test = new OutputStreamTest();
		test.writeFile("");
	}
	
	public void writeFile(String path) throws IOException {
		OutputStream out1 = new FileOutputStream("C:\\Users\\zk\\Desktop\\out.txt");
		byte[] data = new byte[1024];
		String str1 = "akdfja;lfjkafkjaoifkl;adfmjklafdjaopfjal;fka[osfaslfkmakljfajkmao";
		data = str1.getBytes();
		out1.write(data);
		out1.flush();
		out1.close();		
	}

}
