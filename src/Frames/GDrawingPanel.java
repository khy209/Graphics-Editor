package Frames;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import Frames.GShapeToolBar.EShapeTool;
import shapes.GShape;
import shapes.GShape.EPoints;
import transformers.GDrawer;
import transformers.GMover;
import transformers.GTransformer;
import shapes.GShape.EAnchor;

public class GDrawingPanel extends JPanel {//여기서는 그림 그리는걸 테스트함
	private static final long serialVersionUID = 1L;
	
	public enum EDrawingState { // 상태
		eIdle,
		e2P,
		eNP
	}

//	private EShapeType eShapeType; // 툴바에서 얘를 세팅할 수 있게 함, 툴바에서 도구가 선택되면 여기서 세팅을 하게 함, eShapeType을 드로잉 패널을 가져다 놓음
	private Vector<GShape> shapes; // 네모를 저장할 빈 벡터

	private GTransformer transformer;
	private GShape currentShape;
	private GShape selectedShape;
	private EShapeTool eShapeTool;
	private EDrawingState eDrawingState;
	
	public GDrawingPanel() { // 생성자 진짜로 하려면 마우스 이벤터를 받아야함
		MouseHandler mouseHandler = new MouseHandler(); // 마우스 버튼 이벤트를 다루는 핸들러, 이벤트 핸들러를 드로잉 패널에 붙임, OS에서 온 이벤트를 받게 함
		this.addMouseListener(mouseHandler); // 부품 1개 / 마우스 버튼 리스너
		this.addMouseMotionListener(mouseHandler); //부품 2개 마우스 모션 / 리스너 나머지 하나는 마우스 휠 리스너
		
		this.currentShape = null;
		this.selectedShape = null;
		this.shapes = new Vector<GShape>();
		this.eShapeTool = null;
		this.eDrawingState = EDrawingState.eIdle; 
	}
	public void initialize() {
		// TODO Auto-generated method stub
		
		
	}
	public void setEShapeTool(EShapeTool eShapeTool) { //툴바와 연결?
		this.eShapeTool = eShapeTool;
	}
	
	//repaint()시 호출되는 메소드
	protected void paintComponent(Graphics graphics) { // 나를 그려라, 오버라이딩, JPanel에 페인트 컴퍼넌트가 하는 일을 여기서 하게 함(부모가 해야할 일을 자식이 대체한다는걸 오바라이딩), 
		super.paintComponent(graphics);//나를 그림 JPanel을 해야할 일을 여기서해라 라고 먼저 선빵 침
		for (GShape shape: this.shapes) { //저장된 만큼
			shape.draw((Graphics2D)graphics); // 그려라
		}
		//행위의 확장
	}
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapes) { 
			if (shape.contains(x, y)) {
				return shape; //도형 찾음
			}
		}
		return null; //도형을 못찾음
	}

	private void startTransform(int x, int y) { // 드로잉패널에게 두 점을 찍어줌, 그림 그려라
		//set Shape, 드로잉 상태일때 
		this.currentShape = eShapeTool.newShape();
		this.shapes.add(this.currentShape); // currentShape의 주소를 집어넣음
		if (this.eShapeTool == EShapeTool.eSelect) {
			this.selectedShape = onShape(x, y);
			if (this.selectedShape == null) {
				this.transformer = new GDrawer(this.currentShape);
			} else {
				this.transformer = new GMover(this.selectedShape);
			}
			//그림이 있는지 없는지 확인
		} else {
			this.transformer = new GDrawer(this.currentShape); // 그림을 저장
		}
		this.transformer.start((Graphics2D) getGraphics(), x, y); // 그림을 그려라
	
	}
	private void keepTransform(int x, int y) {
		this.transformer.drag((Graphics2D) getGraphics(), x, y);
		this.repaint();
	}
	private void addPoint(int x, int y) { // NP 드로앙일 때 킵 드로잉과 에드 포인트를 계속 반복함, 
		this.transformer.addPoint((Graphics2D) getGraphics(), x, y);
	}
	private void finishTransform(int x, int y) { // 마지막 위치를 잡아
		this.transformer.finish((Graphics2D) getGraphics(), x, y);
		this.selectShape(this.currentShape);

		if(this.eShapeTool == EShapeTool.eSelect) {
			this.shapes.remove(this.shapes.size()-1);
		}
		this.repaint();
	}
	private void selectShape(GShape shape) {
		for(GShape otherShape: this.shapes) {
			otherShape.setSelected(false);
		}
		this.currentShape.setSelected(true); //currentShape은 현재 그리고 있는 shape
	}
	private void changeCursor(int x, int y) {
		this.selectedShape = onShape(x, y);
		if(this.selectedShape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			EAnchor eAnchor = this.selectedShape.getEselectedAnchor();
			this.setCursor(eAnchor.getCursor());
		}

	}
	private class MouseHandler implements MouseListener, MouseMotionListener {//드로잉 패널만 쓰는 클래스, OS가 호출할 수 있는 형태로만 만들었음 그 이름이 마우스 리스너 (인터페이스만 맞춰잠implements MouseListener/ 밑에 있는 함수들은 몸통을 만듬)
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("마우스Clicked");
			if (e.getClickCount() == 1) {
				this.mouse1Clicked(e);
			}
			else if (e.getClickCount() ==2) {
				this.mouse2Clicked(e);
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (eShapeTool.getEPoints() == EPoints.e2P) {
					startTransform(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2P;
				} else if (eShapeTool.getEPoints() == EPoints.eNP) {
					startTransform(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNP;
				}
			} else if (eDrawingState == EDrawingState.e2P) {
				finishTransform(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			} else if (eDrawingState == EDrawingState.eNP) {
				addPoint(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2P) {
				keepTransform(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eNP) {
				keepTransform(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eIdle){ //idle 상태면
				changeCursor(e.getX(), e.getY());
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNP) {
				finishTransform(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
 		}
		@Override
		public void mouseDragged(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
