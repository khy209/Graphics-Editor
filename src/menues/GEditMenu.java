package menues;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEditMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private JMenuItem newItem;
	
	public GEditMenu() {
		super("Edit");
		
		this.newItem = new JMenuItem("Property");
		this.add(this.newItem);
		
		this.newItem = new JMenuItem("undo");
		this.add(this.newItem);
		
		this.newItem = new JMenuItem("redo");
		this.add(this.newItem);
	}

}
