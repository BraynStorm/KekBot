package braynstorm.kekbot.core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class Utils {
	public static byte[] charToByteArr(char [] arr){
		try {
			return arr.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[0];
	}
	
	public static int[] convertIntegers(List<Integer> integers)
	{
		int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}

	public static String stringFromData(int[] preData, int[] data) {
		StringBuilder result = new StringBuilder();
		result.append("predata: ");
		for(int i : preData){
			result.append(Integer.toHexString(i));
			result.append(' ');
		}
		result.append(", data: ");
		for(int i : data){
			result.append(Integer.toHexString(i));
			result.append(' ');
		}
		
		return result.toString();
	}
	
	public static byte[]  integersToBytes(int[] values)
	{
	   ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   DataOutputStream dos = new DataOutputStream(baos);
	   for(int i=0; i < values.length; ++i)
	   {
	        try {
				dos.writeInt(values[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }

	   return baos.toByteArray();
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
