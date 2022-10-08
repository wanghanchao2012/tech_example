package com.example.common;

/**
 * 含时效
 * 
 * @author Joe
 */
public interface Expiration {
	
	/**
	 * 时效（秒）
	 * @return
	 */
	int getExpiresIn();
}
