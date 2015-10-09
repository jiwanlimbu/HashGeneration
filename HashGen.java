package com.jiwan.hash;




import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
		



public class HashGen{
	
	static int count = 0;
	
	public static void main(String [] args) throws IOException{
	         
		try {
			
			System.out.println("Enter the number of bytes :");
			Scanner sc1 = new Scanner(System.in);
			String ip1 = sc1.nextLine();
			
			
			
			
			System.out.println("Enter the values you prefer to match with the radom Hash :");
			Scanner sc = new Scanner(System.in);
			String ip = sc.nextLine();
			int into = Integer.parseInt(ip);
		
			byte[] bytes = ByteBuffer.allocate(4).putInt(into).array();
				
		
			//Should get the HEx of the input string
			String ip2 = HashGenMD5.ByteArrayToHexStringConversion(bytes);
			String s = ip2;
			s = s.replaceFirst ("^0*", "");
			
			System.out.println("The Hexadecimal value of user input is :" + s);
			
			
		  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 
			while(true){
		//Hex and  MD5 of the 20 random bytes
			String Twenty = HashGenMD5.BytesGen();
			String md5Hash2 = HashGenMD5.genMD5(Twenty);
			System.out.println("MD5 Hash of random message :" + md5Hash2);
			count = count+1;
			
			
			if (md5Hash2.startsWith(s))
		
			{
				System.out.println("-----------------------------------");
				System.out.println("Boom!");
				System.out.println("Match Found in " + count + " trials");
				
				break;
				
			}
			}
			sc.close();
		} catch (HashException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}	
		
		
	}

	private static void sleep(int i) {}
	
}
		


class HashGenMD5 {

	private HashGenMD5(){
		
	}
		
		public static String genMD5(String message) throws HashException{
			
			return stringHash(message, "MD5");
		}
		
	
	
	private static String stringHash(String message, String algorithm) throws HashException 
	{
		
		try{
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		byte[] hBytes =digest.digest(message.getBytes("UTF-8"));
		
		return ByteArrayToHexStringConversion(hBytes);
		
		}catch (NoSuchAlgorithmException |  UnsupportedEncodingException ex){
				throw new HashException( "Can not generate hash from the String ..", ex);
		}
	}
			
	
	static String ByteArrayToHexStringConversion(byte[] arrayBytes){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i<arrayBytes.length; i++){
		stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff)+ 0x100, 16).substring(1));
		
		
		}
//		System.out.println("Hex : "+ stringBuffer.toString());
		return stringBuffer.toString();

	}
	
	
	static String BytesGen(){
		
		byte [] b = new byte[20];
		new Random().nextBytes(b);
		System.out.println("Hex value of twenty byte random message is :" + ByteArrayToHexStringConversion(b));
		return ByteArrayToHexStringConversion(b);
		
		
	}
		

}




