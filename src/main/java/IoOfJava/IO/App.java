package IoOfJava.IO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       String STR1 = "kick_machine id=15 =20";
       //System.out.println(STR1.lastIndexOf(""));
       try {
    	   //System.out.println(Integer.parseInt(STR1.substring(STR1.indexOf("id")+3,STR1.lastIndexOf("")-2)));
    	   System.out.println(Integer.parseInt(STR1.substring(STR1.lastIndexOf("=")+1)));
	} catch (Exception e) {
		System.out.println(e);
	}
       
       
       if(STR1.length()>100) {
    	   System.out.println("ID");
       }else {
    	   System.out.println("I");
	}
    }
}
