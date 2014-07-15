/**
 * @author Rodrigo Duarte Louro
 * @dateJul 14, 2014
 */
package com.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.util.adt.color.Color;

import com.managers.SceneManager.SceneType;

public class GameModeScene extends BaseScene implements IOnMenuItemClickListener {

	private MenuScene gameMenu; 
	private final int MENU_SINGLE_PLAYER = 0;
	private final int MENU_MULTIPLAYER = 1;
	
	@Override
	public void createScene() {
		createBackground();
		createGameModeMenu(); 
	}

	@Override
	public void onBackKeyPressed() {

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_MODE_SCENE; 
	}

	@Override
	public void disposeScene() {

	}
	
	//Mudar para uma imagem talvez depois. 
	private void createBackground() {
		setBackground(new Background(Color.BLACK));
	}
	
	private void createGameModeMenu() {
	    gameMenu = new MenuScene(camera);
	    
	    gameMenu.setPosition(0, 0);
	    
	    final IMenuItem singlePlayerItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_SINGLE_PLAYER, resourcesManager.singlePlayerRegion, vbom), 0.8f, 1);
	    final IMenuItem multiplayerItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_MULTIPLAYER, resourcesManager.multiplayerRegion, vbom), 0.8f, 1);
	    
	    gameMenu.addMenuItem(singlePlayerItem);
	    gameMenu.addMenuItem(multiplayerItem);
	    
	    gameMenu.buildAnimations();
	    gameMenu.setBackgroundEnabled(false);
	    
	    //Talvez eu faço uma função pra imitar o padding. 
	    singlePlayerItem.setPosition(singlePlayerItem.getX(), (singlePlayerItem.getY() + (singlePlayerItem.getHeight() * 0.2f)));
	    multiplayerItem.setPosition(multiplayerItem.getX(), multiplayerItem.getY() - (multiplayerItem.getHeight() * 0.2f));
	    
	    //Poderia ter uma classe aqui que é responsavel por isso. (O click do botão de menu. 
	    gameMenu.setOnMenuItemClickListener(this); 
	    
	    setChildScene(gameMenu);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		return false;
	}
}
