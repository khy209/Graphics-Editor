package Lecture;

import java.awt.Graphics2D;

public class GTransformer {
    /*이벤트를 추상화하는 클래스, 마우스 핸들러를 감싸고 있음(드로잉 패널에 이벤트 핸들러가 있음), 드로잉 패널에 발생하는 마우스 이벤트는 이 클래스를 통해 도형을 그림
     * 이 마우스 이벤트 핸들러가가 드로잉 패널에 있는 이유는 드로잉 패널에 올 때 이벤트가 발생하는 것을 구현하기 위함
    Graphics는 내가 그려야 할 영역을 포함함, 패널을 그림 판으로 봄, JPanel에다 달았으면 전체로 인식 할 것
    각각 도형 클래스에 그림을 그릴 수 있는 정보를 일반화 시켜서 전달
    따라서 x y 위치와 그래픽스를 줘야함
    */
    private GRectangle rectangle;
    private GTriangle triangle;
    private GOval oval;
    private GPolygon polygon;

    // 현재 타입 저장
    private String shapeType;

    //DrawingPanel과 연결되어 shapeType을 가져옴
    public GTransformer(String shapeType) {
        this.shapeType = shapeType;
    }


    public void start(Graphics2D graphics, int x, int y) { //파라미터로 점을 줌, 드로잉 패널은 이벤트 밖에 있음
        rectangle = new GRectangle();
        rectangle.setPoint1(0, 0);
        rectangle.setPoint2(0, 0);

        triangle = new GTriangle();
        triangle.setPoint1(0, 0);
        triangle.setPoint2(0, 0);

        oval = new GOval();
        oval.setPoint1(0, 0);
        oval.setPoint2(0, 0);

        polygon = new GPolygon();
        polygon.setPoint1(0, 0);
        polygon.setPoint2(0, 0);


        if (shapeType.equals("rectangle")) {
            rectangle = new GRectangle();
            rectangle.setPoint1(x, y);
            rectangle.setPoint2(x, y); //원점을 같이 잡아줌
        }else if (shapeType.equals("triangle")) {
            triangle = new GTriangle();
            triangle.setPoint1(x, y);
            triangle.setPoint2(x, y); //원점을 같이 잡아줌
        } else if (shapeType.equals("oval")) {
            oval = new GOval();
            oval.setPoint1(x, y);
            oval.setPoint2(x, y); //원점을 같이 잡아줌
        } else if (shapeType.equals("polygon")) {
            polygon = new GPolygon();
            polygon.setPoint1(x, y);
            polygon.setPoint2(x, y); //원점을 같이 잡아줌
        }
    }
    public void drag(Graphics2D graphics, int x, int y) { //중간점이랑 끝점이랑 같음, 움직이는 점의 마지막=끝점
        if (shapeType.equals("rectangle")) {
        rectangle.draw(graphics); //지우고
        rectangle.setPoint2(x, y); //다시 그리고
        rectangle.draw(graphics);
        } else if (shapeType.equals("triangle")) {
            triangle.draw(graphics);
            triangle.setPoint2(x, y);
            triangle.draw(graphics);
        } else if (shapeType.equals("oval")) {
            oval.draw(graphics);
            oval.setPoint2(x, y);
            oval.draw(graphics);
        } else if (shapeType.equals("polygon")) {
            polygon.draw(graphics);
            polygon.setPoint2(x, y);
            polygon.draw(graphics);
        }
    }
    public GRectangle recFinish(Graphics2D graphics, int x, int y) {
        return rectangle;
    }
    public GTriangle triFinish(Graphics2D graphics, int x, int y) {
        return triangle;
    }
    public GOval ovaFinish(Graphics2D graphics, int x, int y) {
        return oval;
    }
    public GPolygon polFinish(Graphics2D graphics, int x, int y) {
        return polygon;
    }
}
