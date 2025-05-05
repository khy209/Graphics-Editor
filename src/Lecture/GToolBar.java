package Lecture;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GToolBar extends JToolBar {


    private static final long serialVersionUID = 1L;
    private JRadioButton rectangleButton;
    private JRadioButton triangleButton;
    private JRadioButton ovalButton;
    private JRadioButton polygonButton;
    private JRadioButton textBoxButton;

    private GShape shape;

    public GToolBar(GDrawingPanel drawingPanel) {// 엑션 리스너 넣기
        setLayout(new FlowLayout(FlowLayout.LEFT));// 버튼을 왼쪽으로 땡
        ButtonGroup radioGroup = new ButtonGroup();

        this.rectangleButton = new JRadioButton("rectangle");
        this.rectangleButton.setActionCommand("rectangle"); // String 타입으로 설정
        this.rectangleButton.addActionListener((ActionListener) drawingPanel); // 리스너 등록
        this.add(this.rectangleButton); //자식으로 등록

        this.triangleButton = new JRadioButton("triangle");
        this.triangleButton.setActionCommand("triangle"); // String 타입으로 설정
        this.triangleButton.addActionListener((ActionListener) drawingPanel); // 리스너 등록
        this.add(this.triangleButton); //자석으로 등록

        this.ovalButton = new JRadioButton("oval");
        this.ovalButton.setActionCommand("oval"); // String 타입으로 설정
        this.ovalButton.addActionListener((ActionListener) drawingPanel); // 리스너 등록
        this.add(this.ovalButton); //자식으로 등록

        this.polygonButton = new JRadioButton("polygon");
        this.polygonButton.setActionCommand("polygon"); // String 타입으로 설정
        this.polygonButton.addActionListener((ActionListener) drawingPanel); // 리스너 등록
        this.add(this.polygonButton); //자식으로 등록

        this.textBoxButton = new JRadioButton("textBox");
        //this.rectangleBUtton.addActionListener(listener);
        this.add(this.textBoxButton); //자식으로 등록

        radioGroup.add(this.rectangleButton); radioGroup.add(this.triangleButton); radioGroup.add(this.ovalButton); radioGroup.add(this.polygonButton); radioGroup.add(this.textBoxButton);



        this.shape = new GShape();
        this.add(this.shape);


    }


    public GShape getShape() {
        return this.shape;
    }

    public void initialize() {


    }

}
