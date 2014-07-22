/**
 * @author Rodrigo Duarte Louro
 * @dateJul 14, 2014
 */
package com.managers;

import com.util.Constants;
import com.util.DataInMemory;

public class GameManager {
	private static GameManager INSTANCE = new GameManager(); 
	private DataInMemory dataInMemory = new DataInMemory(ResourcesManager.getInstance().activity, Constants.FILE_SAVE_DATA);
	
	public static GameManager getInstance() {
		return INSTANCE; 
	}
	
	public GameManager (){
	}

	public DataInMemory getDataInMemory() {
		return dataInMemory;
	}
	
	public boolean alreadyLogedInFacebook() {
		return dataInMemory.readBooleanData(Constants.FACEBOOK_LOGIN); 
	}
	
}
