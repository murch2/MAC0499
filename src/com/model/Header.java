/**
 * @author Rodrigo Duarte Louro
 * @dateJul 22, 2014
 */
package com.model;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import com.managers.ResourcesManager;
import com.util.Constants;
import org.andengine.engine.camera.Camera;

public class Header extends Entity{
	private Sprite backGround;
	
	//não sei direito o que eu to fazendo. 
	public Header (VertexBufferObjectManager vbom) {
		ResourcesManager.getInstance().loadHeader(); 
		createBackground(vbom); 
		createLabel(); 
	}
	
	private void createBackground(VertexBufferObjectManager vbom) {
		backGround = new Sprite(Constants.CAMERA_WIDTH / 2, Constants.CAMERA_HEIGHT * 0.9f,
				ResourcesManager.getInstance().headerRegion, vbom) {
    		@Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
    	};
    	
    	attachChild(backGround); 
    	
	}
	
	private void createLabel() {
		
	}
}
