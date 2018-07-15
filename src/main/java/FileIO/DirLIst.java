package FileIO;

import java.io.File;

public class DirLIst {

	
	public static void main(String [] args) {
		File path =new File(".");
		String [] list;
		list=path.list();
		for(String dirItem:list)
			System.out.println(dirItem);
	}
}
