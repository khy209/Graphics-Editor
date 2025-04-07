package Lecture;

import java.awt.FlowLayout;

import javax.swing.*;

public class GToolBar extends JToolBar {


    private static final long serialVersionUID = 1L;
    private JRadioButton rectangleBUtton;
    private JRadioButton triangleBUtton;
    private JRadioButton ovalBUtton;
    private JRadioButton polygonBUtton;
    private JRadioButton textBoxBUtton;

    private GShape shape;

    public GToolBar(GDrawingPanel drawingPanel) {// 엑션 리스너 넣기
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
        setLayout(new FlowLayout(FlowLayout.LEFT));// 버튼을 왼쪽으로 땡

        this.rectangleBUtton = new JRadioButton("rectangle");
        //this.rectangleBUtton.addActionListener(listener); //이벤트 받기
        this.add(this.rectangleBUtton); //자식으로 등록
        System.out.println("a");

        this.triangleBUtton = new JRadioButton("triangle");
       // this.rectangleBUtton.addActionListener(listener);
        this.add(this.triangleBUtton); //자석으로 등록
        System.out.println("b");

        this.ovalBUtton = new JRadioButton("oval");
       // this.rectangleBUtton.addActionListener(listener);
        this.add(this.ovalBUtton); //자식으로 등록
        System.out.println("c");

        this.polygonBUtton = new JRadioButton("polygon");
       // this.rectangleBUtton.addActionListener(listener);
        this.add(this.polygonBUtton); //자식으로 등록
        System.out.println("d");

        this.textBoxBUtton = new JRadioButton("textBox");
        //this.rectangleBUtton.addActionListener(listener);
        this.add(this.textBoxBUtton); //자식으로 등록
        System.out.println("e");


        this.shape = new GShape();
        this.add(this.shape);


    }


    public GShape getShape() {
        return this.shape;
    }

    public void initialize() {


    }

}
