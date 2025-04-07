package Lecture;

import javax.swing.JMenuBar;

public class GMenubar extends JMenuBar {
    private static final long serialVersionUID = 1L;



    private GFileMenu fileMenu; // 자식을 만들땐 무조건 프리베이트
    private GEditMenu editMenu;
    private GGraphicMenu graphicMenu;

    public GMenubar() {
        // 여기에도 이벤트 헨들러를 붙여라
        this.fileMenu = new GFileMenu();
        this.add(this.fileMenu);

        this.editMenu = new GEditMenu();
        this.add(this.editMenu);

        this.graphicMenu = new GGraphicMenu();
        this.add(this.graphicMenu);

    }

    public void initialize() {


    }
}
