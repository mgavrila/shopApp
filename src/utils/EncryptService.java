package utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncryptService implements an encryption for accounts password
 * @author mihai.gavrila
 * @since 2019-02-19
 */



public class EncryptService {

	public static String getHashOfString(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			 
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = digest.digest(s.getBytes("UTF-8"));
		  
		return convertByteArrayToHexString(hashedBytes);
		}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return stringBuffer.toString();
		}
	
	
}
