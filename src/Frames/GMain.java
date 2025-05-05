package Frames;

public class GMain {//윈도우, OS와 직접 연결되어 있는 윈도우, 마우스와 여러 이벤트, 값들을 통해 받을 수 있음(JFrame을 통해서)직렬화 개념 알기
	//메인은 메모리에 올라가는 순간 이 부분을 가르침(0라인) GMainFrame을 생성하는 코드를 싱행함
	public static void main(String[] args) {
		//main과 mianFrame은 부모 자식 관계가 아니라 그저 연결된 관계이다  mainFrame은 자기가 직접 만들 수 없기에 main을 따로 빼내어 만듬
		// create aggregation hierarchy, Tree 전체를 만듦
		GMainFrame mainFrame = new GMainFrame();//Tree를 완전히 만들고 초기화를 시킴(속성들 초기화) 자식이 안만들었는데 초기화를 해버리면 큰 문제가 생길 수도 있음
		// hierarchy(Tree) traverse to DFS
		mainFrame.initialize(); // 다 만들어진 Tree를 한번 더 돌면(이건 있어도 좋고 없어도 좋지만 있는게 좋다.), 2단계 초기화, 연결해놓은걸 초기화
		
		
		
		                     

	}
}
