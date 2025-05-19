package Frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GMainFrame extends JFrame { // JFrame을 상속받아서 GMainFrame을 특화함, extends는 JFrame의 모든것은 받아서 내가 추가를 함, 특화(상속). 바인딩, 실체와 연결?
	private static final long serialVersionUID = 1L;// 이것도 속성 (long)
	
	private GMenubar menubar;
	private GShapeToolBar toolbar;
	private GDrawingPanel drawingPanel; 
	// 3개의 부품(클래스)
	
	
	public GMainFrame() { //생성자(메뉴바, 툴바, 드로잉 패널을 만들 것), 아직 이 객체를 사용 불가
		//속성(속성을 가지고 정의, 값들)실체를 만들어 낼 때 값
		this.setLocation(100, 200); // 이렇게 상수를 직접 넣는것은 최악임, 윈도우마다 사이즈가 다르기 때문, 나중에 사이즈 계산해서 위치를 잡는 방법으로 가야함
		this.setSize(600, 400); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		
		
		// 내 자식에 관한 것(components), 자식이 3개 있음 부모가 자식들의 레이아웃을 세팅
//		this.setLayout(new BorderLayout());//부모가 자식들을 관리해야하기 때문에 레이아웃을 MainFrame에다 만듬
			
		this.menubar = new GMenubar();
		this.setJMenuBar(menubar); // 메뉴바만 자식으로 붙일 때 메뉴만 set으로 하고 나머진 다 add로 함, MainFrame에 붙기 때문에 레이아웃 지정x
		
		this.toolbar = new GShapeToolBar();
		this.add(toolbar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel(); // 인스턴테이션
		this.add(drawingPanel, BorderLayout.CENTER);	
		

		
	}//끝났기에 객체 사용 가능 안에 있는 자식들의 new를 다 만들어야함, 생서===
	
	//기능, 행위를 정의한 클래스
	public void initialize() { //초기화
		//관계를 만들어야함lBar
		this.toolbar.associate(this.drawingPanel);
		this.menubar.associate(this.drawingPanel);
	//Associated attributes
	this.setVisible(true); // 메인 프레임을 그리면 초기화가 끝남/ 이메일 같은 경우 백그라운드에서만 돌고 화면에는 안뜸(false), 자식들을 다 만들고 visible해라
	
	
	this.menubar.initialize();
	this.toolbar.initialize();
	this.drawingPanel.initialize();

	}
	
}
