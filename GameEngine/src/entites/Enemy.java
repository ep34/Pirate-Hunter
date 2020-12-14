package entites;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import models.TextureModel;
import renderEngine.DisplayManager;

public class Enemy extends Entity {
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	
	long lastTurn = System.currentTimeMillis();
	
	public Enemy(TextureModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

	
	

	public void move()
	{
		 
		if(new Random().nextInt(4) == 0)
		{
		this.currentSpeed = RUN_SPEED;
		
		}
		if(new Random().nextInt(5) == 0)
		{
			this.currentTurnSpeed = TURN_SPEED;
			//this.currentSpeed = RUN_SPEED;
			
		}
		if(new Random().nextInt(6) == 1)
		{
			this.currentSpeed = RUN_SPEED;
		}
		else
		{
			this.currentTurnSpeed = 0;
		}
		/*if(new Random().nextInt(4) == 2)                               //Enemy AI
		{
			this.currentTurnSpeed = TURN_SPEED;
		}
		if(new Random().nextInt(4) == 3)
		{
			this.currentTurnSpeed = -TURN_SPEED;
		}*/
			
		
			
			
	//	System.out.println(this.currentSpeed);
				
				
		
		super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		
	}
	
	
	

}
