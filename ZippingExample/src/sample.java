

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class sample {
public static void main(String[] args) throws IOException {
	
String source = "C:\\temp\\Testing";

String dest = "D:\\Test1";

String fileName = "test";

copyAndDeleteFile(source+"\\"+fileName+".zip", dest+"\\"+fileName+".zip");
copyAndDeleteFile(source+"\\"+fileName+".ctl", dest+"\\"+fileName+".ctl");
}

private static void copyAndDeleteFile(String source, String dest) {
	InputStream inStream = null;
	OutputStream outStream = null;
	
	try{
	
	    File sourceFile =new File(source);
	   
	    File destFile =new File(dest);
	  
	    inStream = new FileInputStream(sourceFile);
	    outStream = new FileOutputStream(destFile);
	    byte[] buffer = new byte[1024];
	    int length;
	    //copy the file content in bytes
	    while ((length = inStream.read(buffer)) > 0){
	    	outStream.write(buffer, 0, length);
	    }
	    inStream.close();
	    outStream.close();
	
	    //delete the original file
	    sourceFile.delete();
	
	    System.out.println("File is copied successful!");
	
	}catch(IOException e){
	    e.printStackTrace();
	}
}
}