package Lecture;

public class GMain {
    public static void main(String[] args) {


        //main과 mianFrame은 부모 자식 관계가 아니라 그저 연결된 관계이다  mainFrame은 자기가 직접 만들 수 없기에 main을 따로 빼내어 만듬
        GMainFrame mainFrame = new GMainFrame();//Tree를 완전히 만들고 초기화를 시킴(속성들 초기화) 자식이 안만들었는데 초기화를 해버리면 큰 문제가 생길 수도 있음
        mainFrame.initialize(); // 다 만들어진 Tree를 한번 더 돌면(이건 있어도 좋고 없어도 좋지만 있는게 좋다.), 2단계 초기화, 연결해놓은걸 초기화
    }
}

