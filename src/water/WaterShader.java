package water;

import org.lwjgl.util.vector.Matrix4f;

import shaders.ShaderProgram;
import entities.Camera;
import entities.Light;

public class WaterShader extends ShaderProgram {

	private final static String VERTEX_FILE = "water/waterVertexShader.txt";
	private final static String FRAGMENT_FILE = "water/waterFragmentShader.txt";

	private int location_modelMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	private int location_reflectionTexture;
	private int location_refractionTexture;
	private int location_dudvMap;
	private int location_moveFactor;
	private int location_cameraPosition;
	private int location_normalMap;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_depthMap;
	private int location_near;
	private int location_far;

	public WaterShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_modelMatrix = super.getUniformLocation("modelMatrix");
		location_reflectionTexture = super.getUniformLocation("reflectionTexture");
		location_refractionTexture = super.getUniformLocation("refractionTexture");
		location_dudvMap = super.getUniformLocation("dudvMap");
		location_moveFactor = super.getUniformLocation("moveFactor");
		location_cameraPosition = super.getUniformLocation("cameraPosition");
		location_normalMap = super.getUniformLocation("normalMap");	
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_depthMap = super.getUniformLocation("depthMap");
		location_near = super.getUniformLocation("near");
		location_far = super.getUniformLocation("far");
	}
	
	public void connectTextureUnits() {
		super.loadInt(location_reflectionTexture, 0);
		super.loadInt(location_refractionTexture, 1);
		super.loadInt(location_dudvMap, 2);
		super.loadInt(location_normalMap, 3);
		super.loadInt(location_depthMap, 4);
	}
	
	public void loadNearAndFar(float near, float far) {
		super.loadFloat(location_near, near);
		super.loadFloat(location_far, far);
	}
	
	public void loadLight(Light sun) {
		super.loadVector3f(location_lightPosition, sun.getPosition());
		super.loadVector3f(location_lightColour, sun.getColour());
	}
	
	public void loadMoveFactor(float factor) {
		super.loadFloat(location_moveFactor, factor);
	}

	public void loadProjectionMatrix(Matrix4f projection) {
		loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(){
		Matrix4f viewMatrix = Camera.viewMatrix;
		loadMatrix(location_viewMatrix, viewMatrix);
		super.loadVector3f(location_cameraPosition, Camera.position);
	}

	public void loadModelMatrix(Matrix4f modelMatrix){
		loadMatrix(location_modelMatrix, modelMatrix);
	}

}
