package transformers;

import shapes.GShape;
import shapes.GShape.EAnchor;

import java.awt.*;

public class GResizer extends GTransformer { //파사드?

	private GShape shape;
//	private Rectangle bounds;
	private EAnchor eResizeAnchor;

	private int px, py;
	private int cx, cy; //앵커의 기준점

	public GResizer(GShape shape) {
		super(shape);
		this.shape = shape;
		this.eResizeAnchor=null;

	}
	
	public void start(Graphics2D graphics, int x, int y) { //원점을 기준으로 몇 배 늘어났냐 계산
		int dx = x - px;
		int dy = y - py;

		int sx = 1; //newWidth
		int sy = 1;
		Rectangle r = this.shape.getBounds(); //현재 그려진 그림의 박스를 가져옴
		//어떤 앵커에서 마우스가 들어갔는지 계산
		EAnchor eSelectedAnchor = this.shape.getESelectedAnchor();
		EAnchor eResizeAnchor = null;
		switch (eResizeAnchor) {
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eWW: eResizeAnchor =EAnchor.eEE; cx=r.x+r.width; cy=r.y+r.height/2; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			case eNW: eResizeAnchor =EAnchor.eSE; cx=r.x+r.width; cy=r.y+r.height; break;
			default: break;
		}

	}
	public void drag(Graphics2D graphics, int x, int y) {
		double dx = 0; double dy = 0;
		switch (eResizeAnchor) {

		}
		Shape transformedShape = this.shape.getTransformedShape(); //마우스 가지고 얼마나 움직였는지 계산
		
		//얼마만큼 늘어났냐
		double w1 = transformedShape.getBounds().width;
		double w2 = dx+w1;
		double h1 = transformedShape.getBounds().height;
		double h2 = dy+h1;

		double xScale = w2/w1;
		double yScale = h2/h1;

		this.shape.getAffineTransform().translate(cx, cy);
		this.shape.getAffineTransform().translate(xScale, yScale);
		this.shape.getAffineTransform().translate(-cx, -cy);
		//왜 한번 하고 빼는지, 어떻게 하면 안찌그러지게 만드냐 고민을 해보기
//		this.shape.getAffineTransform().scale(cx, cy); //행렬을 여기다 가져옴

		this.px = x;
		this.py = y;
	}

	public void finish(Graphics2D graphics, int x, int y) {
	}

	@Override
	public void addPoint(Graphics2D graphics, int x, int y) {
		// TODO Auto-generated method stub
		shape.addPoint(x, y);
	}
}