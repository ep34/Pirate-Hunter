package entites;

import org.lwjgl.util.vector.Vector3f;

public class Light {

	private Vector3f positions;
	private Vector3f color;
	public Light(Vector3f positions, Vector3f color) {
		this.positions = positions;
		this.color = color;
	}
	public Vector3f getPositions() {
		return positions;
	}
	public void setPositions(Vector3f positions) {
		this.positions = positions;
	}
	public Vector3f getColor() {
		return color;
	}
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
}
