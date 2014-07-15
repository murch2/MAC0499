package com.managers;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;

import com.activities.GameActivity;

public class ResourcesManager {
	private static final ResourcesManager INSTANCE = new ResourcesManager();
	
	//Ainda acho que essas coisas deveriam ser private. 
	public Engine engine;
    public GameActivity activity;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    
    //Splash
    public ITextureRegion splashRegion; 
    private BitmapTextureAtlas splashTextureAtlas;
    
    ///GameModeMenu
    public ITextureRegion singlePlayerRegion;
    public ITextureRegion multiplayerRegion;
    private BuildableBitmapTextureAtlas gameModeMenuAtlas; 
    
    public Font font; 
	public static ResourcesManager getInstance() {
        return INSTANCE;
    }
	
	//Só deverá ser chamado uma vez. 
    public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    public synchronized void loadSplashScreen() { 
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/scenes/splash/"); 
    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR); 
    	splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splashScreen.png", 0, 0); 
    	splashTextureAtlas.load();
    }
    
    public synchronized void unloadSplashScreen() {
    	splashTextureAtlas.unload();
    	splashRegion = null;
    }
    
    public synchronized void loadGameModeMenu() {
    	loadFonts(); 
    	loadGameModeGraphics(); 
    }
    
    //Depois tenho que ver o que fazer com as fonts no livro
    private synchronized void loadFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    
    private synchronized void loadGameModeGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/scenes/gameMode/"); 
    	gameModeMenuAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 310, 160, TextureOptions.BILINEAR); 
    	singlePlayerRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameModeMenuAtlas, activity, "singlePlayerButton.png");
    	multiplayerRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameModeMenuAtlas, activity, "multiPlayerButton.png");
    	
    	try {
			this.gameModeMenuAtlas.build((new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1)));
			this.gameModeMenuAtlas.load(); 
		} catch (TextureAtlasBuilderException e) {
			e.printStackTrace();
		}
    }
}
