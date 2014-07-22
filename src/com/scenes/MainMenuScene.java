/**
 * @author Rodrigo Duarte Louro
 * @dateJul 22, 2014
 */
package com.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.util.adt.color.Color;
import org.json.JSONObject;

import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.managers.GameManager;
import com.managers.SceneManager.SceneType;
import com.server.HTTPPostRequester;
import com.server.HTTPResponseListener;
import com.server.MakeParameters;
import com.util.FacebookFacade;

public class MainMenuScene extends BaseSceneWithHUD implements HTTPResponseListener, GraphUserCallback, IOnMenuItemClickListener{

	//Talvez isso vá pra uma das classes singleton. 
	private FacebookFacade fb;
	private MenuScene menuTeste; 

	@Override
	public void createScene() {
		createBackground();
		createHUD(camera); 
		makeRequestTest();
		if (!GameManager.getInstance().isLoggedUser()) {
			fb = new FacebookFacade(); 
			fb.login(this); 
		}
	}

	private void makeRequestTest() {
		JSONObject obj = MakeParameters.makeTestparams();
		new HTTPPostRequester().asyncPost(this, obj); 

	}

	private void createBackground() {
		setBackground(new Background(Color.BLUE));
	}

	//Aqui vou fazer um pop-up pra sair do jogo. 
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
		System.out.println("DEBUG - O json recebedio é " + json);
	}

	//Podia ter uma classe que cuidava disso, e só essa classe seria responsavel pelas respostas do facebook e soh ela implementaria 
	//	a interface usergraphCallback. 
	@Override
	public void onCompleted(GraphUser user, Response response) {
		if (user != null) {
			GameManager.getInstance().setLoggedUser(true);  
			GameManager.getInstance().setUserName(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName()); 
			GameManager.getInstance().setUserID(user.getId()); 
		}
	}

	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener#onMenuItemClicked(org.andengine.entity.scene.menu.MenuScene, org.andengine.entity.scene.menu.item.IMenuItem, float, float)
	 */
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {

		return false;
	}

}
