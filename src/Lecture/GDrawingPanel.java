package Lecture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel implements ActionListener {//여기서는 그림 그리는걸 테스트함
    private static final long serialVersionUID = 1L;

    private Vector<GRectangle> rectangles; // 네모를 저장할 빈 벡터
    private Vector<GOval> ovals; // 타원들을 저장하는 리스트
    private Vector<GPolygon> polygons;
    private Vector<GTriangle> triangles;

    private String currentShapeType;


    public GDrawingPanel() { // 생성자 진짜로 하려면 마우스 이벤터를 받아야함
        MouseHandler mouseHandler = new MouseHandler(); // 마우스 버튼 이벤트를 다루는 핸들러, 이벤트 핸들러를 드로잉 패널에 붙임, OS에서 온 이벤트를 받게 함
        this.addMouseListener(mouseHandler); // 부품 1개 / 마우스 버튼 리스너
        this.addMouseMotionListener(mouseHandler); //부품 2개 마우스 모션 / 리스너 나머지 하나는 마우스 휠 리스너

        this.rectangles = new Vector<GRectangle>();
        this.ovals = new Vector<GOval>();
        this.polygons = new Vector<GPolygon>();
        this.triangles = new Vector<GTriangle>();
    }
    public void initialize() {
        // TODO Auto-generated method stub
    }
    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    // 도형 변경 메서드, 이것도 행위 분리를 하나 묻기
    @Override
    public void actionPerformed(ActionEvent e) {
        String shapeType = e.getActionCommand(); // 툴바에서 클릭된 버튼의 도형 타입 가져옴
        setCurrentShapeType(shapeType);
    }
    public void setCurrentShapeType(String shapeType) {
        this.currentShapeType = shapeType; // shapeType 업데이트
    }

    protected void paintComponent(Graphics graphics) { // 나를 그려라, 오버라이딩, JPanel에 페인트 컴퍼넌트가 하는 일을 여기서 하게 함(부모가 해야할 일을 자식이 대체한다는걸 오바라이딩),
        super.paintComponent(graphics);//나를 그림 JPanel을 해야할 일을 여기서해라 라고 먼저 선빵 침
        for (GRectangle rectangle: rectangles) { //저장된 만큼
            rectangle.draw((Graphics2D)graphics); // 그려라
        }
        for (GTriangle triangle: triangles) { //저장된 만큼
            triangle.draw((Graphics2D)graphics); // 그려라
        }
        for (GOval oval: ovals) { //저장된 만큼
            oval.draw((Graphics2D)graphics); // 그려라
        }
        for (GPolygon polygon: polygons) { //저장된 만큼
            polygon.draw((Graphics2D)graphics); // 그려라
        }
    }


    private class MouseHandler implements MouseListener, MouseMotionListener {//드로잉 패널만 쓰는 클래스, OS가 호출할 수 있는 형태로만 만들었음 그 이름이 마우스 리스너 (인터페이스만 맞춰잠implements MouseListener/ 밑에 있는 함수들은 몸통을 만듬)

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("마우스Clicked");
/*			if (e.getClickCount() == 1) {
				//if (xxx) 그림을 안그린 상태면
				System.out.println("마우스Pressed");
				transformer = new GTransformer();
				Graphics2D graphics2D = (Graphics2D) getGraphics();
				graphics2D.setXORMode(getBackground());
				transformer.start(graphics2D, e.getX(), e.getY()); // 컴보지션, 클래스 안에서 보면 부모가 다 보임
			}
			else if (e.getClickCount() ==2) {
			System.out.println("마우스Released");
			Graphics2D graphics2D = (Graphics2D)getGraphics();
			graphics2D.setXORMode(getBackground());
			GRectangle rectangle = transformer.finish(graphics2D, e.getX(), e.getY()); //rectangle을 저장
			rectangles.add(rectangle);
			}*/
        }
        private GTransformer transformer;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("마우스Pressed");
            transformer = new GTransformer(currentShapeType);
            Graphics2D graphics2D = (Graphics2D)getGraphics();
            graphics2D.setXORMode(getBackground());
            transformer.start(graphics2D, e.getX(), e.getY()); // 컴보지션, 클래스 안에서 보면 부모가 다 보임
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("마우스Dragged");
            Graphics2D graphics2D = (Graphics2D)getGraphics();
            graphics2D.setXORMode(getBackground());
            transformer.drag(graphics2D, e.getX(), e.getY());
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("마우스Move");
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("마우스Released");
            Graphics2D graphics2D = (Graphics2D)getGraphics();
            graphics2D.setXORMode(getBackground());

            GRectangle rectangle = transformer.recFinish(graphics2D, e.getX(), e.getY()); //rectangle을 저장
            rectangles.add(rectangle);
            GTriangle triangle = transformer.triFinish(graphics2D, e.getX(), e.getY()); //triangle을 저장
            triangles.add(triangle);
            GOval oval = transformer.ovaFinish(graphics2D, e.getX(), e.getY()); //oval을 저장
            ovals.add(oval);
            GPolygon polygon = transformer.polFinish(graphics2D, e.getX(), e.getY()); //polygon을 저장
            polygons.add(polygon);

        }
        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("마우스Entered");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("마우스Exited");
        }
    }
}
