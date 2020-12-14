package entites;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import engineTester.Testing;
import engineTester.MainGameLoop.SoundEffect;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.TextureModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import terrains.Terrain;

public class Player extends Entity {
	
	static String bulletSound;
	static SoundEffect bullet = new SoundEffect();
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	public static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30; 
	
	private static final float TERRAIN_HEIGHT = 0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardSpeed = 0;
	
	private boolean isInAir = false;
	
	public Player(TextureModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

	public void move(Terrain terrain)
	{
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x,super.getPosition().z);
		if(super.getPosition().y < terrainHeight) //collision
		{
			upwardSpeed = 0;
			isInAir = false;
			//GameOver.gameOver(null);
			super.getPosition().y = terrainHeight;
		}
	}
	
	private void jump()
	{
		if(!isInAir)
		{
		this.upwardSpeed = JUMP_POWER;
		isInAir = true;
		}
	}
	
	public void checkInputs()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			this.currentSpeed = RUN_SPEED;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			this.currentSpeed = -RUN_SPEED;
		}
		else
		{
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			this.currentTurnSpeed = -TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			this.currentTurnSpeed = TURN_SPEED;
		}else
		{
			this.currentTurnSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_F))
		{
			bulletSound = ".//res//mortar.wav";
			bullet.setFile(bulletSound);
			bullet.Play();
		}
		
	}
	
	/////sound
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
