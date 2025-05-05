package transformers;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shapes.GRectangle;

public class GTransformer { 
	/*이벤트를 추상화하는 클래스, 마우스 핸들러를 감싸고 있음(드로잉 패널에 이벤트 핸들러가 있음), 드로잉 패널에 발생하는 마우스 이벤트는 이 클래스를 통해 도형을 그림 
	 * 이 마우스 이벤트 핸들러가가 드로잉 패널에 있는 이유는 드로잉 패널에 올 때 이벤트가 발생하는 것을 구현하기 위함
	Graphics는 내가 그려야 할 영역을 포함함, 패널을 그림 판으로 봄, JPanel에다 달았으면 전체로 인식 할 것
	각각 도형 클래스에 그림을 그릴 수 있는 정보를 일반화 시켜서 전달
	따라서 x y 위치와 그래픽스를 줘야함
	*/
	private GRectangle rectangle;
	
	public void start(Graphics2D graphics, int x, int y) { //파라미터로 점을 줌, 드로잉 패널은 이벤트 밖에 있음
		rectangle = new GRectangle();
		rectangle.setPoint(x,  y);
		rectangle.dragPoint(x, y); //원점을 같이 잡아줌

	}
	public void drag(Graphics2D graphics, int x, int y) { //중간점이랑 끝점이랑 같음, 움직이는 점의 마지막=끝점
		rectangle.draw(graphics); //지우고
		rectangle.dragPoint(x, y); //다시 그리고
		rectangle.draw(graphics);

	}
	public GRectangle finish(Graphics2D graphics, int x, int y) { 
		return rectangle;
	}

}
