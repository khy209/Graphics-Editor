package shapes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GRectangle extends GShape {//좌표 저징
	private Rectangle2D rectangle; //좌표를 Rectangle2D의 rectangle에 저장, n개의 점을 찍을 것, 원점 찍도 2번째 점 찍고 2번째 점은 막 움직임, 
	private AffineTransform affineTransform;// 메이트릭스 생성
	
	public GRectangle() { 
		super(new Rectangle2D.Float(0, 0, 0, 0)); // double은 정밀도가 2배로 높아지는데 일단 Float로 함, 명시적으로 일부로 초기값을 써줌
		this.rectangle = (Rectangle2D) this.getShape();
		this.affineTransform = new AffineTransform(); //초기화
	}
	public void setPoint(int x, int y) {
		this.rectangle.setFrame(x, y, 0, 0); // 4개의 점을 잡음
	}
	public void dragPoint(int x, int y) {// 2번째 점
		double ox = rectangle.getX(); //원점
		double oy = rectangle.getX(); //원점
		double w = x - ox;
		double h = y - oy;
		this.rectangle.setFrame(ox, oy, w, h);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	private int px, py; //전점
	@Override
	public void setMovePoint(int x, int y) {
		this.px = x;
		this.py = y;
		
	}
	@Override
	public void movePoint(int x, int y) {
		int dx = x-px;
		int dy = y-py;

//		this.affineTransform.translate(dx, dy); //움직임을 누적헤서 저장
		
		//전점을 다시 기억
		this.px = x;
		this.py = y;
	}
	
}
