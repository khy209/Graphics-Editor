package Lecture;


import javax.swing.JComboBox;
import javax.swing.JButton;


public class GShape extends JComboBox<String> {

    private static final long serialVersionUID = 1L;

    private static String[] shape = {"draw","move","resize","rotate"};//
    private JButton draw; //

    public GShape() {
        super(shape);
        System.out.println("shape");
        this.draw = new JButton("draw");
        this.add(this.draw);




    }
    public String getCurrentState() {
        System.out.println("shapeState");
        return (String) this.getSelectedItem(); // 선택된 박스를 문자열로 반환, 반환한 문자열을 DrawingPanel에서 사용
    }



}
