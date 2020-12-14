package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entites.Camera;
import entites.Light;
import toolBox.Maths;

public class StaticShader extends ShaderProgram{
     
    private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
 
    private int location_transformation_matrix;
    private int location_projection_matrix;
    private int location_viewMatrix;
    private int location_lightColor;
    private int location_lightPosition;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_useFakeLighting;
    private int location_skyColor;
    private int location_plane;
    
    
    
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

	@Override
	protected void getAllUniformLocation() {
		location_transformation_matrix = super.getUniformLocation("transformationMatrix");
		location_projection_matrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");
		location_shineDamper =  super.getUniformLocation("shineDamper");
		location_reflectivity =  super.getUniformLocation("reflectivity");
		location_useFakeLighting = super.getUniformLocation("useFakeLighting");
		location_skyColor = super.getUniformLocation("skyColor");
		location_plane = super.getUniformLocation("plane");
	}
     
	public void loadClipPlane(Vector4f plane)
	{
		super.loadVector(location_plane, plane);
	}
	
	public void loadSkyColor(float r,float g,float b)
	{
		super.loadVector(location_skyColor, new Vector3f(r,g,b));
	}
	public void disableSkyColor()
	{
		//null
	}
	
	public void loadFakeLightingVariable(boolean useFake)
	{
		super.loadBoolean(location_useFakeLighting, useFake);
	}
	
	public void loadShineVariables(float damper,float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
    public void loadTransformationMatrix(Matrix4f matrix)
    {
    	super.loadMatrix(location_transformation_matrix, matrix);
    }
    
    public void loadLight(Light light)
    {
    	super.loadVector(location_lightPosition, light.getPositions());
    	super.loadVector(location_lightColor,light.getColor());
    }
    
    public void loadViewMatrix(Camera camera)
    {
    	Matrix4f viewMatrix = Maths.createViewMatrix(camera);
    	super.loadMatrix(location_viewMatrix, viewMatrix);
    }
    
    public void loadProjectionMatrix(Matrix4f projection)
    {
    	super.loadMatrix(location_projection_matrix, projection);
    }
}