package Lecture;


import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GGraphicMenu extends JMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem newItem;

    public GGraphicMenu() {
        super("Edit");

        this.newItem = new JMenuItem("line thickness");
        this.add(this.newItem);

        this.newItem = new JMenuItem("line Style");
        this.add(this.newItem);

        this.newItem = new JMenuItem("font style");
        this.add(this.newItem);

        this.newItem = new JMenuItem("font size");
        this.add(this.newItem);
    }

}
