package engineTester;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import engineTester.Sound.SoundEffect;
import entites.Bullet;
import entites.Camera;
import entites.Enemy;
import entites.Entity;
import entites.GameOver;
import entites.Light;
import entites.MousePicker;
import entites.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TextureModel;
import particles.Particle;
import particles.ParticleMaster;
import particles.ParticleSystem;
import particles.ParticleTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
//import renderEngine.Render;
//import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
public class MainGameLoop {

	 static String clickSound;
	 static String bellSound; 
	static SoundEffect bgm = new SoundEffect();
	static SoundEffect bell = new SoundEffect();
	public static void GameLoop(String[] args) {
		clickSound = ".//res//sea.wav";
		bellSound = ".//res//bell.wav";
		bgm.setFile(clickSound);
		bgm.Play();
		bgm.loop();
		
		bell.setFile(bellSound);
		bell.Play();
		//options
		DisplayManager.createDisplay(); // display without gui
	//	Testing obj = new Testing(); /// display with gui
	//	obj.startGL();
		Loader loader = new Loader();
		/// terrain texture staff///
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		//TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
 		
		TerrainTexturePack texturePack =  new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		//-------------------------------------------------------
		
		 Terrain terrain = new Terrain(0,-1,loader,texturePack,blendMap,"heightmap");
		// Terrain terrain1 = new Terrain(-1,-1,loader,texturePack,blendMap,"heightmap");   
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		//ModelTexture texture = new ModelTexture(loader.loadTexture("wood"));
		
		TextureModel staticModel = new TextureModel(model,new ModelTexture(loader.loadTexture("tree"))); //diffuse graphics 
		TextureModel grass = new TextureModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("grassTexture")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUsefakeLighting(true);
		TextureModel fern = new TextureModel(OBJLoader.loadObjModel("fern", loader),new ModelTexture(loader.loadTexture("fern")));
		fern.getTexture().setHasTransparency(true);
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random(676452);
		for(int i=0;i<500;i++){
			if(i%7==0) {
				float x = random.nextFloat()*400 -200;
				float z = random.nextFloat() * -400;
				float y = terrain.getHeightOfTerrain(x, z);
            //entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
            entities.add(new Entity(grass,new Vector3f(x,y,z),0,0,0,1.8f));
         
           // entities.add(new Entity(fern,new Vector3f(random.nextFloat()*400 - 200,0,random.nextFloat() * -400),0,0,0,2.3f));
		}
			if(i%3==0)
			{
				float x = random.nextFloat()*800 -400;
				float z = random.nextFloat() * -600;
				float y = terrain.getHeightOfTerrain(x, z);
				
				 entities.add(new Entity(staticModel, new Vector3f(x,y,z),0,0,0,random.nextFloat() * 1 + 4));
				entities.add(new Entity(fern,new Vector3f(x,y,z),0,0,0,0.9f));
			}
		}
		
		
		Light light = new Light(new Vector3f(20000,40000,2000),new Vector3f(1,1,1));
		
		
		
		
		
		List<GuiTexture>guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("pirate"),new Vector2f(-0.8f,0.8f),new Vector2f(0.20f,0.20f));
		guis.add(gui);
		
		
		GuiTexture gui1 = new GuiTexture(loader.loadTexture("heart"),new Vector2f(-0.89f,0.66f),new Vector2f(0.07f,0.07f));
		guis.add(gui1);	
		DisplayManager.updateDisplay();
		
			
			
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		MasterRenderer renderer =  new MasterRenderer(loader);
		ParticleMaster.init(loader, renderer.getProjectionMatrix());
		
		RawModel shipModel = OBJLoader.loadObjModel("playerShip", loader);
		RawModel bulletModel = OBJLoader.loadObjModel("dragon", loader);
		RawModel normalBoat = OBJLoader.loadObjModel("bottt", loader);
		RawModel normalBoat1 = OBJLoader.loadObjModel("bottt", loader);
		RawModel enemyShipModel = OBJLoader.loadObjModel("playerShip",loader);
		RawModel enemyShipModel1 = OBJLoader.loadObjModel("playerShip",loader);
		RawModel enemyShipModel2 = OBJLoader.loadObjModel("playerShip",loader);
		TextureModel playerShip = new TextureModel(shipModel,new ModelTexture(loader.loadTexture("gg")));
		TextureModel bulletInShip = new TextureModel(bulletModel,new ModelTexture(loader.loadTexture("wood")));
		TextureModel enemyShip = new TextureModel(enemyShipModel,new ModelTexture(loader.loadTexture("wood")));
		TextureModel enemyShip1 = new TextureModel(enemyShipModel1,new ModelTexture(loader.loadTexture("wood")));
		TextureModel enemyShip2 = new TextureModel(enemyShipModel2,new ModelTexture(loader.loadTexture("wood")));
		TextureModel normBoat = new TextureModel(normalBoat,new ModelTexture(loader.loadTexture("wood")));
		TextureModel normBoat1 = new TextureModel(normalBoat,new ModelTexture(loader.loadTexture("wood")));
		
		
		Player player = new Player(playerShip,new Vector3f(240,0,20),0,0,0,1);
		//Bullet player1 = new Bullet(bulletInShip,new Vector3f(50,2,-50),0,0,0,1); 
		Enemy enemy = new Enemy(enemyShip,new Vector3f(250,0,-60),0,0,0,1);
		Enemy enemy1 = new Enemy(enemyShip1,new Vector3f(450,0,-80),0,0,0,1);
		Enemy enemy2 = new Enemy(enemyShip1,new Vector3f(650,0,-100),0,0,0,1);
		Enemy enemy0 = new Enemy(normBoat,new Vector3f(130,1.5f,0),0,0,0,1);
		Enemy enemy00 = new Enemy(normBoat1,new Vector3f(140,1.5f,0),0,0,0,1);
			Camera camera = new Camera(player);		
		//Vector4f clipPlane = new Vector4f();
		//TextureModel playerShip = new TextureModel
		WaterFrameBuffers buffers = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader,waterShader,renderer.getProjectionMatrix(),buffers);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile(0,-10,1);
		waters.add(water);
		
		MousePicker picker = new MousePicker(camera,renderer.getProjectionMatrix(),terrain);
		
		ParticleTexture particleTexture = new ParticleTexture(loader.loadTexture("particleAtlas"),4);
		ParticleSystem system = new ParticleSystem(particleTexture,50,25,0.3f,4,1);
		system.randomizeRotation();
		system.setDirection(new Vector3f(0,1,0), 0.1f);
		system.setLifeError(0.1f);
		system.setSpeedError(0.4f);
		system.setScaleError(0.8f);
		while(!Display.isCloseRequested())
		{
			gameOver();
		//	entity.increaseRotation(0, 1, 0);
			//entity.increasePosition(0, 0, -0.1f);
			camera.move();
			player.move(terrain);
		//	player1.move();
			enemy.move();
			enemy1.move();
			enemy2.move();
			
			picker.update();
			system.generateParticles(player.getPosition());
			//control particle 
			/*if(Keyboard.isKeyDown(Keyboard.KEY_Y))
			{
				system.generateParticles(player.getPosition());
				//new Particle(new Vector3f(player.getPosition()),new Vector3f(0,30,0),1,4,0,1);
			}*/
			
			ParticleMaster.update(camera);
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE1);
			//entity.increaseRotation(0, 1, 0);
			buffers.bindReflectionFrameBuffer();
			float distance  = 2 *(camera.getPosition().y-water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain1);
			 for(Entity entity:entities){
	                renderer.processEntity(entity);
	            }
			//renderer.processEntity(entity);
			renderer.render(light, camera,new Vector4f(0,1,0,-water.getHeight()));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			buffers.bindRefractionFrameBuffer();
			//buffers.bindReflectionFrameBuffer();
			renderer.processEntity(player);
			//renderer.processEntity(player1);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain1);
			 for(Entity entity:entities){
	                renderer.processEntity(entity);
	            }
			//renderer.processEntity(entity);
			renderer.render(light, camera,new Vector4f(0,-1,0,water.getHeight()));
			
			
			GL11.glDisable(GL30.GL_CLIP_DISTANCE1);
			buffers.unbindCurrentFrameBuffer();
			
			
			renderer.processEntity(player);
		//	renderer.processEntity(player1);
			renderer.processEntity(enemy);
			renderer.processEntity(enemy1);
			renderer.processEntity(enemy2);
			renderer.processEntity(enemy0);
			renderer.processEntity(enemy00);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain1);
			 for(Entity entity:entities){
	                renderer.processEntity(entity);
	            }
			//renderer.processEntity(entity);
			renderer.render(light, camera,new Vector4f(0,1,0,0));
			waterRenderer.render(waters, camera,light);
			
			ParticleMaster.renderParticles(camera);
			
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}
		ParticleMaster.cleanUp();
		buffers.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay(); 
		//obj.stopGL();
	}
	
	
	public static void gameOver()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_X))
		{			
			DisplayManager.closeDisplay(); 
			GameOver.gameOver(null);
		}
	}
	
	
	public static class SoundEffect
	{
		Clip clip;
		public void setFile(String soundFileName)
		{
			try
			{
				File file = new File(soundFileName);
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
				
			}
			catch(Exception e)
			{
				
			}
		}
		public void Play()
		{
			clip.setFramePosition(0);
			clip.start();
		}
		public void loop()
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
	}
	

}
