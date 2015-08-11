
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;


public class effdefconshirt {
	public static void main(String[] args) {
		try {
			
			for (String str : permStr("ZXCVBNMY")){
		    System.out.println(str);
			String key = str;
		
			//FileInputStream fis = new FileInputStream("original.txt");
			//FileOutputStream fos = new FileOutputStream("encrypted.txt");
			//encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("/Users/wck/Documents/eff-shirt/eff-shirt/src/encrypted.txt");
			FileOutputStream fos2 = new FileOutputStream("/Users/wck/Documents/eff-shirt/eff-shirt/src/decrypted.txt", true);
		
			decrypt(key, fis2, fos2);
		
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		System.out.println(is);
		System.out.println(key);
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}


	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		/*DESedeKeySpec dks2 = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory skf2 = SecretKeyFactory.getInstance("DESede");   //("DES");
		SecretKey desKey2 = skf2.generateSecret(dks2);
		Cipher cipher2 = Cipher.getInstance("DESede"); // DES/ECB/PKCS5Padding for SunJCE
		if (mode == Cipher.DECRYPT_MODE) {
			cipher2.init(Cipher.DECRYPT_MODE, desKey2);
			CipherOutputStream cos2 = new CipherOutputStream(os3, cipher2);
			doCopy(is, cos2);
		}*/
		
		
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			
			doCopy(is, cos);
		}
		
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		 
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
			
		}
		
		os.flush();
		os.close();
		is.close();
	}
	public static Vector<String> permStr(String str){

	    if (str.length() == 1){
	        Vector<String> ret = new Vector<String>();
	        ret.add(str);
	        return ret;
	    }

	    char start = str.charAt(0);
	    Vector<String> endStrs = permStr(str.substring(1));
	    Vector<String> newEndStrs = new Vector<String>();
	    for (String endStr : endStrs){
	        for (int j = 0; j <= endStr.length(); j++){
	            if (endStr.substring(0, j).endsWith(String.valueOf(start)))
	                break;
	            newEndStrs.add(endStr.substring(0, j) + String.valueOf(start) + endStr.substring(j));
	        }
	    }
	    return newEndStrs;
	}
	
}
