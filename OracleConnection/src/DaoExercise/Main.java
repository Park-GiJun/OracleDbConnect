package DaoExercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("Input....");
		Scanner sc = new Scanner(System.in);
		String inp = sc.nextLine();
		sc.close();
		System.out.println(inp);

		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVo> list = dao.list(inp);

		for (int i = 0; i < list.size(); i++) {
			MemberVo data = (MemberVo) list.get(i);
			String empno = data.getEmpno();
			String ename = data.getEname();
			int sal = data.getSal();
			int comm = data.getComm();

			System.out.println(empno + ":" + ename + ":" + sal + ":" + comm);

		}

	}

}
