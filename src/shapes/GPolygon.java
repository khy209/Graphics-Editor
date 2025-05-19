package shapes;

import java.awt.Polygon;

public class GPolygon extends GShape {//좌표 저징
	private Polygon polygon;
	
	public GPolygon() { 
		super(new Polygon()); // double은 정밀도가 2배로 높아지는데 일단 Float로 함, 명시적으로 일부로 초기값을 써줌
		this.polygon = (Polygon) this.getShape();
	}
	public void setPoint(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void dragPoint(int x, int y) {// 2번째 점
		this.polygon.xpoints[this.polygon.npoints-1] =x;
		this.polygon.ypoints[this.polygon.npoints-1] =y;
	}
	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
}