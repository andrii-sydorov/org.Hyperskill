package Interfaces;

/**
 * Suppose, you are writing a geometric engine. Now it includes classes Circle,
 * Rectangle and interfaces Movable and Scalable.
 * 
 * You need:
 * 
 * to write a new interface MutableShape that extends both existing interfaces;
 * to implement the new interface in each class; to override methods move and
 * scale in both classes: scale should multiply the radius of a circle by the
 * specified factor; scale should multiply width and height of a rectangle by
 * the specified factor; move should add dx and dy to the center of a circle;
 * move should add dx and dy to the upper-left corner of a rectangle.
 * 
 * See the provided code and read comments to understand your task better. Now
 * the code is not compiled.
 * 
 * Note:
 * 
 * do not remove existing classes and their members (including getters and
 * constructors). do not make your classes and interfaces public
 * 
 * After your changes, the following code should be compiled:
 * 
 * MutableShape circle = new Circle(2.0f, 3.5f, 10.1f);
 * 
 * circle.move(10.1f, 20.2f); circle.scale(2.2f);
 * 
 * ((Circle) circle).getRadius();
 * 
 * @author SMD_ASY
 *
 */

public class MutableGeometricShapes {

	public static void main(String[] args) {

		MutableShape circle = new Circle(2.0f, 3.5f, 10.1f);

		circle.move(10.1f, 20.2f);
		circle.scale(2.2f);

		((Circle) circle).getRadius();
	}

}

interface Movable {

	void move(float dx, float dy);
}

interface Scalable {

	void scale(float factor);
}

interface MutableShape extends Movable, Scalable {

}

final class Circle implements MutableShape {

	/**
	 * Defines the horizontal position of the center of the circle
	 */
	private float centerX;

	/**
	 * Defines the vertical position of the center of the circle
	 */
	private float centerY;

	/**
	 * Defines the radius of the circle
	 */
	private float radius;

	public Circle(float centerX, float centerY, float radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public float getRadius() {
		return radius;
	}

	public void move(float dx, float dy) {
		centerX += dx;
		centerY += dy;
	}

	public void scale(float factor) {
		radius *= factor;
	}
}

final class Rectangle implements MutableShape {

	/**
	 * Defines the X coordinate of the upper-left corner of the rectangle.
	 */
	private float x;

	/**
	 * Defines the Y coordinate of the upper-left corner of the rectangle.
	 */
	private float y;

	/**
	 * Defines the width of the rectangle.
	 */
	private float width;

	/**
	 * Defines the height of the rectangle.
	 */
	private float height;

	public Rectangle(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void move(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public void scale(float factor) {
		width *= factor;
		height *= factor;
	}
}
