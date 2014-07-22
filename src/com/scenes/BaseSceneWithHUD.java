/**
 * @author Rodrigo Duarte Louro
 * @dateJul 22, 2014
 */
package com.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;

import com.managers.ResourcesManager;
import com.model.Header;


public abstract class BaseSceneWithHUD extends BaseScene {
	
	public void createHUD(Camera camera) {
		HUD hud = new HUD(); 
		hud.attachChild(new Header(ResourcesManager.getInstance().vbom)); 
		camera.setHUD(hud); 
	}
	
}
