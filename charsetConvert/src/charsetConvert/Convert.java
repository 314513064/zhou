package charsetConvert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;
import java.util.Scanner;

public class Convert {
	
	public static void main(String[] args) throws IOException {
		System.out.println("******************************************************");
		System.out.println("              Hello, begin to convert");
		System.out.println("******************************************************\n\n");
		String path = Convert.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int index = path.lastIndexOf('/');
		path = path.substring(0, index+1)+"config.properties";
		
		Properties properties = new Properties();
		BufferedReader bf = new BufferedReader(new FileReader(path));
		properties.load(bf);
		String targetPath = properties.getProperty("targetPath");
		String targetFileType = properties.getProperty("targetFileType");
		traversalDir(targetPath, targetFileType);
		
		System.out.println("\n\n******************************************************");
		System.out.println("                      End");
		System.out.println("******************************************************");
		
	}
	public static void traversalDir(String filePath, String targetFile) throws IOException {
		File dir = new File(filePath);
		if(dir.isDirectory()) {
			String[] fileNames = dir.list();
			for(String fileName : fileNames) {
				fileName = filePath + fileName;
				System.out.println(fileName);
				File file = new File(fileName);
				if(file.isDirectory())
					traversalDir(fileName+"/",targetFile);
				else {
					convertFile(fileName, targetFile);
				}
			}
		}else if (dir.isFile()) {
			convertFile(filePath, targetFile);
		}
	}
	public static void convertFile(String fileName, String targetFile) throws IOException {
		if(!fileName.endsWith(targetFile))
			return ;
		System.out.println(fileName);
		File file = new File(fileName);
		String tmpFileName = fileName.substring(0, fileName.indexOf(".java")) + "_tmp"+".java";
		File tmpFile = new File(tmpFileName);
		tmpFile.createNewFile();
		FileInputStream fis = new FileInputStream(new File(fileName));
		InputStreamReader isr = new InputStreamReader(fis, "GBK");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null) {
			line += "\n";
			sb.append(line);
		}
		String convertString = sb.toString();
		FileOutputStream fos = new FileOutputStream(tmpFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		osw.write(convertString);
		osw.flush();
		osw.close();
		fos.close();
		br.close();
		isr.close();
		fis.close();
		file.delete();
		tmpFile.renameTo(new File(fileName));
	}
}
