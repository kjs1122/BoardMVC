package controller.action;

public class test {

	

	public static void main(String args[]) {
		int listCount = 5;
		int max = listCount % 10 != 0 ?  listCount / 10 + 1 : listCount / 10;
		System.out.println(max);

	}
}
