package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import entites.Camera;
import entites.Entity;
import entites.Light;
import models.TextureModel;

import shaders.StaticShader;
import shaders.TerrainShader;
import skybox.SkyboxRenderer;
import terrains.Terrain;
public class MasterRenderer {
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	
	private static final float RED = 0.5f;
	private static final float GREEN = 0.5f;
	private static final float BLUE = 0.5f;
	
	private Matrix4f projectionMatrix;

	private StaticShader shader = new StaticShader();
	private EntityRender render;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private Map<TextureModel,List<Entity>> entities = new HashMap<TextureModel,List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	
	private SkyboxRenderer skyboxRenderer;
	
	public MasterRenderer(Loader loader)
	{
		enableCulling();
		createProjectionMatrix();
		render = new EntityRender(shader,projectionMatrix);
		terrainRenderer =  new TerrainRenderer(terrainShader,projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader,projectionMatrix);
	}
	 public Matrix4f getProjectionMatrix() {
	        return this.projectionMatrix;
	    }
	public static void enableCulling()
	{
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(Light sun,Camera camera,Vector4f clipPlane)
	{
		prepare();
		shader.start();
		shader.loadClipPlane(clipPlane);
		//shader.loadSkyColor(RED, GREEN, BLUE);
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		render.render(entities);
		
		terrainShader.start();
		terrainShader.loadClipPlane(clipPlane);
		terrainShader.loadLight(sun);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		skyboxRenderer.render(camera,RED, GREEN, BLUE);
		
		terrains.clear();
		shader.stop();
		entities.clear();
	}
	
	 public void processTerrain(Terrain terrain){
	        terrains.add(terrain);
	    }
	
	public void processEntity(Entity entity)
	{
		TextureModel entitymodel = entity.getModel();
		List<Entity> batch = entities.get(entitymodel);
		if(batch!=null)
		{
			batch.add(entity);
		}
		else
		{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entitymodel, newBatch);
		}
	}
	public void cleanUp()
	{
		shader.cleanUp();
		terrainShader.cleanUp();
	}
	public void prepare()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 1); //background color
	}
	private void createProjectionMatrix()
	{
		 float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
	        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
	        float x_scale = y_scale / aspectRatio;
	        float frustum_length = FAR_PLANE - NEAR_PLANE;
	 
	        projectionMatrix = new Matrix4f();
	        projectionMatrix.m00 = x_scale;
	        projectionMatrix.m11 = y_scale;
	        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
	        projectionMatrix.m23 = -1;
	        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
	        projectionMatrix.m33 = 0;
	}
}
