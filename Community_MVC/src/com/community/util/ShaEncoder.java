package com.community.util;

import java.security.MessageDigest;

public class ShaEncoder {
	private static ShaEncoder encoder;
	private ShaEncoder() {}
	
	public static ShaEncoder getInstance() {
		if(encoder == null) {
			encoder = new ShaEncoder();
		}
		
		return encoder;
	}
	
	public String getEncodedStr(String base) {
		String result = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(base.getBytes());
			byte[] hash = digest.digest();
			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
			
			result = hexString.toString();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
