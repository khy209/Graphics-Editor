package Lecture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel  {//여기서는 그림 그리는걸 테스트함

    private static final long serialVersionUID = 1L;
    boolean isDragging = false;
    int offsetX, offsetY;

    private GToolBar toolbar;


    public GDrawingPanel() { // 생성자 진짜로 하려면 마우스 이벤터를 받아야함
        MouseHandler mouseHandler = new MouseHandler(); // 마우스 버튼 이벤트를 다루는 핸들러, 이벤트 핸들러를 드로잉 패널에 붙임, OS에서 온 이벤트를 받게 함
        this.addMouseListener(mouseHandler); // 부품 1개 / 마우스 버튼 리스너
        this.addMouseMotionListener(mouseHandler); //부품 2개 마우스 모션 / 리스너 나머지 하나는 마우스 휠 리스너

    }
    public void initialize() {


    }
    protected void paintComponent(Graphics graphics) { // 오버라이딩, JPanel에 페인트 컴퍼넌트가 하는 일을 여기서 하게 함(부모가 해야할 일을 자식이 대체한다는걸 오바라이딩),
        super.paintComponent(graphics);//JPanel을 해야할 일을 여기서해라 라고 먼저 선빵 침

        //행위의 확장
    }



    public void draw(int x, int y, int w, int h) {
        System.out.println("draw 실행됨!");
        Graphics2D graphics = (Graphics2D)this.getGraphics(); // new를 하면 OS, 부모가 준 속성값을 초기화 해버림 그래서 this 사용 Graphics2D는 기본 그림도구에서 2차원 전용 도구로 확장함 2D 전용
        graphics.setXORMode(getBackground()); // XORMode 사용
        graphics.drawRect(x, y, w, h); // 그래픽스를 이벤트가 발생한 위치에다 그려라
    }

    public void setToolbar(GToolBar toolbar) {
        this.toolbar = toolbar;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {//드로잉 패널만 쓰는 클래스, OS가 호출할 수 있는 형태로만 만들었음 그 이름이 마우스 리스너 (인터페이스만 맞춰잠implements MouseListener/ 밑에 있는 함수들은 몸통을 만듬)

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("마우스Clicked");
        }
        private int x1, y1; // 원점
        @Override

        public void mousePressed(MouseEvent e) {
            System.out.println("마우스Pressed");
            String state = toolbar.getShape().getCurrentState();
            if ("draw".equals(state)) {// GShape가 "draw" 일 때
                this.x1 = e.getX();
                this.y1 = e.getY();
                this.x2 = this.x1; // 원점에서는 두 점이 같음 ,원점으로 모든걸 초기화
                this.y2 = this.y1;
                isDragging = true;

            }  if ("move".equals(state)) {// GShape가 "move" 일 때
                offsetX = 0;
                offsetY = 0;
                isDragging = true;
            }

        }

        private int x2, y2; // 정점, 저장할 필요가 있음
        //private int ox2, oy2;
        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("마우스Dragged");
            String state = toolbar.getShape().getCurrentState();
            if ("draw".equals(state)&&isDragging) {
                //  erase
                draw(x1, y1, x2 - x1, y2 - y1);
                //draw
                this.x2 = e.getX(); // 새 좌표
                this.y2 = e.getY();
                draw(x1, y1, x2 - x1, y2 - y1); // 이벤트에는 좌표가 들어가있음 그 좌표를 가져옴, 새로운 네모를 그림
                offsetX = e.getX();
                offsetY = e.getY();
            } if ("move".equals(state)&&isDragging) {
                draw(x1, y1, x2 - x1, y2 - y1); // 기존 네모 지우기
                int width = x2 - x1; // 그렸던 네모의 가로(x2 - x1) 저장
                int height = y2 - y1; 
                this.x1 = e.getX() - offsetX; // 새 좌표
                this.y1 = e.getY() - offsetY;
                this.x2 = x1 + width; // 새 정점
                this.y2 = y1 + height;
                draw(x1, y1, x2 - x1, y2 - y1); // 다시 새로 그리기
            }
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("마우스Move");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("마우스Released");
            isDragging = false;

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
