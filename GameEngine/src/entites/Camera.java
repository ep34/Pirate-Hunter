package entites;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	private Vector3f position = new Vector3f(50,20,-50); //camera movement 0 5 0
	private float pitch = 10; //camera rotation 
	private float yaw; //how much left or right camera moving
	private float roll; // how much camera tilted
	
	private Player player;
	
	private float speed;
	public Camera(Player player)
	{
		this.player = player;
		this.speed = 0.5f;
	}
	public void move()  //whenever we want to move camera every frame
	{
		
	/*	yaw =  - (Display.getWidth() - Mouse.getX() / 2);
		pitch =  (Display.getHeight() / 2) - Mouse.getY();
		pitch = (Display.getHeight() / 8f) - (Mouse.getY() / 4f);
		if (pitch >= 90)
		{
			
			pitch = 90;
			
		}
		else if (pitch <= -90)
		{
			
			pitch = -90;
			
		}*/
		
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance  = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance,verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		/*if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			position.z += -(float)Math.cos(Math.toRadians(yaw)) * speed;
			position.x += (float)Math.sin(Math.toRadians(yaw)) * speed;
			//position.z -= 0.2f;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			position.z -= -(float)Math.cos(Math.toRadians(yaw)) * speed;
			position.x -= (float)Math.sin(Math.toRadians(yaw)) * speed;


		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			position.z += (float)Math.sin(Math.toRadians(yaw)) * speed;
			position.x += (float)Math.cos(Math.toRadians(yaw)) * speed;

			//position.x += 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			position.z -= (float)Math.sin(Math.toRadians(yaw)) * speed;
			position.x -= (float)Math.cos(Math.toRadians(yaw)) * speed;
			//position.x -= 0.2f;
		}*/
	}
	
	public void invertPitch()
	{
		this.pitch = -pitch;
	}
	
	public Vector3f getPosition() {
		return position;
	}


	public float getPitch() {
		return pitch;
	}


	public float getYaw() {
		return yaw;
	}


	public float getRoll() {
		return roll;
	}
	
	private void calculateCameraPosition(float horizDistance,float verticDistance)
	{
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticDistance;
	}
	
	private float calculateHorizontalDistance()
	{
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticalDistance()
	{
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom()
	{
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
	}
	private void calculatePitch()
	{
		if(Mouse.isButtonDown(1))
		{
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	private void calculateAngleAroundPlayer()
	{
		if(Mouse.isButtonDown(0))
		{
			float angleChange = Mouse.getDX() * 0.1f;
			angleAroundPlayer -= angleChange;
		}
	}
	
}
