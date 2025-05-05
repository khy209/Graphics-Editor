package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

public class GShape {
	protected Shape shape;
	public GShape() {
	}
	public void draw(Graphics2D graphics2D) { //그림은 여기서 그리고 도형의 정보는 GRectangle로
		graphics2D.draw(shape); // 그냥 점의 집합을 그려라 shape입장에선 점의 집합으로 그림, move는 4개의 점을 한번에 움직이고 resize도 똑같음
	}
	public void move() {
		
	}
	public void resize() {
		
	}
}
