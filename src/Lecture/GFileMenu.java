package Lecture;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GFileMenu extends JMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem newItem;

    public GFileMenu() {
        //이벤트 헨들러 붙이기, 쉽지 않을거
        super("File");

        this.newItem = new JMenuItem("new"); // 구조적으로 속성값(글씨)만 변하기 때문에 특화시킬 필요가 없음, 자식만 만든 것
        this.add(this.newItem);// 자식을 등록한 것

        this.newItem = new JMenuItem("save");
        this.add(this.newItem);

        this.newItem = new JMenuItem("save as");
        this.add(this.newItem);

        this.newItem = new JMenuItem("Quit");
        this.add(this.newItem);
    }

}