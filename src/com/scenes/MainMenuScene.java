/**
 * @author Rodrigo Duarte Louro
 * @dateJul 22, 2014
 */
package com.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.util.adt.color.Color;
import org.json.JSONObject;

import com.managers.SceneManager.SceneType;
import com.server.HTTPPostRequester;
import com.server.HTTPResponseListener;
import com.server.MakeParameters;

public class MainMenuScene extends BaseScene implements HTTPResponseListener{

	@Override
	public void createScene() {
		createBackground();
		createButtonTest(); 
		makeRequestTest(); 		
	}
	
	private void createButtonTest() {
		
	}
	
	private void makeRequestTest() {
		JSONObject obj = MakeParameters.makeTestparams();
		new HTTPPostRequester().asyncPost(this, obj); 
		
	}

	private void createBackground() {
		setBackground(new Background(Color.BLUE));
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.MAINMENU_SCENE; 
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResponse(JSONObject json) {
		System.out.println("O json recebedio é " + json);
	}

}
