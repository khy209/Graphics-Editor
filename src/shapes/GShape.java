package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public abstract class GShape {
	private final static int ANCHOR_W = 10;
	private final static int ANCHOR_H = 10;
	public enum EPoints { // 몇개의 점이 들어가는지
		e2P,  //2점일 때/ 이 값이 eShape로 들어가야함
		eNP	  //n개의 점일 때
	}
	public enum EAnchor {
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eRR(new Cursor(Cursor.MOVE_CURSOR)),
		eMM(new Cursor(Cursor.N_RESIZE_CURSOR)); //도형 위에는 있는데 앵커는 아닌 것 앵커 라인인데 앵커 점이 아닌곳은 무브
		private Cursor cursor;
		private EAnchor(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() {
			return this.cursor;
		}
	}
	
	private Shape shape;
	private AffineTransform affineTransform;
	private Ellipse2D anchors[];
	private boolean bSelected; // 현재 선택이 되었냐
	private EAnchor eSelectedAnchor; //선택 되었는데 어떤 앵커가 마우스 위에 있냐
	// 메이트릭스 생성
	private int px, py; //전점
	public GShape(Shape shape) {
		this.shape = shape;
		this.affineTransform = new AffineTransform(); //초기화
		this.anchors = new Ellipse2D.Double[EAnchor.values().length-1]; //-1은 무브는 동그라미 안만들기 때문에 한 것
		for(int i=0; i<this.anchors.length; i++) { //포인터
			this.anchors[i] = new Ellipse2D.Double();
		}
		this.bSelected = false; //선택된 상황이 아님
		this.eSelectedAnchor = null; // 선택된 앵커가 없음
	}
	//getters and setters , 속성을 세팅
	protected Shape getShape() {
		return this.shape;
	}
	public boolean isSelected() {// 선택되었나?
		return this.bSelected;
	}
	public void setSelected(boolean bSelected){ //그림이 끝났을 때 얘를 호출하면 그림을 그렸을 때 바로 앵커가 나타남
		this.bSelected = bSelected;
	}
	public EAnchor getEselectedAnchor() {
		return this.eSelectedAnchor;
	}
	//method
	private void setAnchors() { //실제로 앵커를 그리는 함수, 바운딩 rect를 가지고 만듦
		Rectangle bounds = this.shape.getBounds(); //구하고 있는 사각형을 구함
		int bx = bounds.x;
		int by = bounds.y; //동그라미의 좌표
		int bw = bounds.width;
		int bh = bounds.height;

		int cx=0;
		int cy=0; //앵커 중심점
		for(int i=0; i<this.anchors.length; i++) {
			switch (EAnchor.values()[i]) {
				case eSS: cx = bx+bw/2; cy= by+bh;   break;
				case eSE: cx = bx+bw;   cy= by+bh;   break;
				case eSW: cx = bx;      cy= by+bh;   break;
				case eNN: cx = bx+bw/2; cy= by;      break;
				case eNE: cx = bx+bw;   cy= by;      break;
				case eNW: cx = bx;      cy= by;      break;
				case eEE: cx = bx+bw;   cy= by+bh/2; break;
				case eWW: cx = bx;      cy= by+bh/2; break;
				case eRR: cx = bx+bw/2; cy= by-30;   break;
				default: break;
			}
			anchors[i].setFrame(cx-ANCHOR_W/2,cy-ANCHOR_H/2, ANCHOR_W, ANCHOR_H);

		}

	}
	public void draw(Graphics2D graphics2D) { //그림은 여기서 그리고 도형의 정보는 GRectangle로
		Shape transformedShape = this.affineTransform.createTransformedShape(shape); //원래 도형(shape)에 좌표를 바꾼 새로운 도형을 여기다 만들어줌
		graphics2D.draw(transformedShape); // 그냥 점의 집합을 그려라 shape입장에선 점의 집합으로 그림, move는 4개의 점을 한번에 움직이고 resize도 똑같음
		if (bSelected) { //선택된 상황이면 앵커를 그려라
			this.setAnchors(); // 앵커 계산
			for(int i=0; i<this.anchors.length; i++) {
				//앵커의 색을 채움, black white같은 상수를 쓰지마라, 사용자가 지정한 값으로 짜야함
				Color penColor = graphics2D.getColor();
				graphics2D.setColor(graphics2D.getBackground());
				graphics2D.fill(anchors[i]);
				graphics2D.setColor(penColor);

				graphics2D.draw(anchors[i]);//앵커즈의 i번쨰를 그려라 얘는 선만 그림

			}
		}
	}
	public boolean contains(int x, int y) { // 이 좌표가 어디냐 포함되었냐
		if(bSelected) {
			for(int i=0; i<this.anchors.length; i++) {
				if(anchors[i].contains(x, y)) { //앵카거 x y를 포함 했으면,
					this.eSelectedAnchor = EAnchor.values()[i];
					return true;//맞다, 근데 어느 앵커인지는 값을 가져다 씀
				}
			}
		}
		if(this.shape.contains(x, y)) {
			this.eSelectedAnchor = EAnchor.eMM; // 앵커가 아니라 무브
		}
		return false; // true false가 return
	}
	//draw method
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void dragPoint(int x, int y);
	//move method


	public void setMovePoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void movePoint(int x, int y) {
		int dx = x-px;
		int dy = y-py;

		this.affineTransform.translate(dx, dy); //움직임을 누적해서 저장
		//전점을 다시 기억
		this.px = x;
		this.py = y;
	}
}
