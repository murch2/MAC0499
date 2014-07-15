/**
 * @author Rodrigo Duarte Louro
 * @dateJul 14, 2014
 */
package com.managers;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.managers.ResourcesManager;
import com.scenes.BaseScene;
import com.scenes.GameModeScene;
import com.scenes.SplashScene;

public class SceneManager {
	
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SPLASH_SCENE;
	private BaseScene currentScene;
	private Engine engine = ResourcesManager.getInstance().engine;
	
	private BaseScene splashScene; 
	private BaseScene gameModeScene; 
	
	public static SceneManager getInstance() {
		return INSTANCE; 
	}
	
	public enum SceneType {
		SPLASH_SCENE,
		GAME_MODE_SCENE
	}
	
	//Talvez esse método possa ser private 
	public void setScene(BaseScene scene) {
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}
	
	public void setScene(SceneType sceneType) {
		switch (sceneType) {
		case SPLASH_SCENE:
			setScene(splashScene);
			break;
		case GAME_MODE_SCENE:
			setScene(gameModeScene); 
		default:
			break;
		}
	}
	
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
		ResourcesManager.getInstance().loadSplashScreen(); 
		splashScene = new SplashScene(); 
		currentScene = splashScene; 
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene); 
	}
	
	private void disposeSplashScene() {
	    ResourcesManager.getInstance().unloadSplashScreen();
	    splashScene.disposeScene();
	    splashScene = null;
	}

	public void createGameModeScene() {
	    ResourcesManager.getInstance().loadGameModeMenu(); 
	    gameModeScene = new GameModeScene(); 
	    setScene(gameModeScene);
	    disposeSplashScene();
	}
	
	public BaseScene getCurrentScene() {
		return currentScene; 
	}
}
