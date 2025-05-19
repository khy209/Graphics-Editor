package Frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

//import Frames.GDrawingPanel.EDrawingType;
import shapes.GPolygon;
//import Frames.GDrawingPanel.EShapeType;
import shapes.GRectangle;
import shapes.GShape;
import shapes.GShape.EPoints;

public class GShapeToolBar extends JToolBar {
	//자식
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingPanel;

	// Class에 대한 정의
	public enum EShapeTool { // 이뉴멀로 클래스일때는 대문자 E, new가 된 객체인 경우엔 소문자 e로 선언
		eSelect("select", EPoints.e2P, GRectangle.class),
		eRectangle("rectangle", EPoints.e2P, GRectangle.class), //0번쨰 객체 글자의 의미 총 3개 포함, 프로그램이 실행 되기 전에? new가 되어서 이미 이미 메모리가 할당되어서 함eRectangle은 메모리 주소임, 이 총 4개는 배열에 들어가있음, 실제로 값은 0이지만 실제로 메모리 주소를 갖고, 실제로 오브젝트임(new가 되었다.)
		// GRectangle.class를 이용? 아무튼 괄호 안에 이름, 이드로잉타입, 클래스가 있음
		eEllipse("ellipse", EPoints.e2P, GRectangle.class), //1번째 객체, 괄호 안에 있는 것은 생성자자를 호출한거하고 비슷함
		eLine("line", EPoints.e2P, GRectangle.class),
		ePolygon("polygon", EPoints.eNP, GPolygon.class); //객체 4개 생성
		
		private String name; 
		private EPoints ePoints;
		private Class<?> classShape;
		private EShapeTool(String name, EPoints ePoints, Class<?> classShape) { 
			this.name = name;
			this.ePoints = ePoints;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
			
		}
		public EPoints getEPoints() {
			return this.ePoints;
		}
		public GShape newShape() {
			GShape shape;
	         try {
	             shape = (GShape) classShape.getConstructor().newInstance();
	             return shape;
	          } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
	                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	          }
	          return null;
		}
	}
		
	public GShapeToolBar() {
		//this.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		
		//setLayout(new FlowLayout(FlowLayout.LEFT));// 버튼을 왼쪽으로 땡
		ButtonGroup buttonGroup = new ButtonGroup();// 버튼 그룹
		for (EShapeTool eShapeType: EShapeTool.values()) {
			JRadioButton radioButton = new JRadioButton(eShapeType.getName()); //버튼 생성
			ActionHandler actionHandler = new ActionHandler();
			radioButton.addActionListener(actionHandler); // 버튼에 이벤트 핸들러를 달아놓음
			radioButton.setActionCommand(eShapeType.toString());// 어떤 버튼이 눌렀는지를 핸들러에게 전달을 함
			this.add(radioButton); //자식으로 등록, 이벤트 핸들러를 공통으로 쓰기
			buttonGroup.add(radioButton);
			System.out.println("a");
		}

	
		
	}

	public void initialize() {
		// selcect 버튼(0번째)를 기본으로 클릭되게 초기화(강제로 클릭)
		JRadioButton button = (JRadioButton) this.getComponent(EShapeTool.eSelect.ordinal());
		button.doClick();
	}
	public void associate (GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	private class ActionHandler implements ActionListener {
		 @Override
	        public void actionPerformed(ActionEvent e) {
	            String sShapeType = e.getActionCommand();
	            EShapeTool eShapeType = EShapeTool.valueOf(sShapeType); //다시 오브젝트로 바꿈
	            drawingPanel.setEShapeTool(eShapeType);
	        }
		
	}

}
