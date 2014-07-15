/**
 * @author Rodrigo Duarte Louro
 * @dateJul 14, 2014
 */
package com.managers;

public class GameManager {
	private static final GameManager INSTANCE = new GameManager();
	
	public GameManager getInstance() {
		return INSTANCE; 
	}
	
}
