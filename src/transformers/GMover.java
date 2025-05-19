package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GMover extends GTransformer { //파사드?
	
	private GShape shape;

	public GMover(GShape shape) {
		super(shape);
		this.shape = shape;
	}
	
	public void start(Graphics2D graphics, int x, int y) { //파라미터로 점을 줌, 드로잉 패널은 이벤트 밖에 있음
		shape.setMovePoint(x, y);

	}
	public void drag(Graphics2D graphics, int x, int y) { //중간점이랑 끝점이랑 같음, 움직이는 점의 마지막=끝점
		shape.movePoint(x, y);
	}
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void addPoint(Graphics2D graphics, int x, int y) {
		shape.addPoint(x, y);
	}

}