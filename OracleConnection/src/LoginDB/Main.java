package LoginDB;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main implements ActionListener {
	private Button btn;
	private TextField result;
	TextField id, pwd;
	private MemberDAO dao;
	private Frame f ;
	private Frame f2 ;
	

	public Main() {
		dao = new MemberDAO();
		f = new Frame("Login");
		f.setSize(330, 180);
		f.setLayout(null);

		Label lid = new Label("ID : ", Label.RIGHT);
		lid.setBounds(20, 20, 50, 50);
		Label lpwd = new Label("PWD : ", Label.RIGHT);
		lpwd.setBounds(20, 50, 50, 50);

		id = new TextField(10);
		id.setBounds(80, 35, 120, 20);
		pwd = new TextField(10);
		pwd.setEchoChar('*');
		pwd.setBounds(80, 65, 120, 20);

		btn = new Button("Login");
		btn.setBounds(210, 30, 50, 50);
		btn.addActionListener(this);

		result = new TextField("ID와 PW를 입력해주세요.");
		result.setEnabled(false);
		result.setColumns(32);
		result.setBounds(80, 95, 180, 25);

		f.add(lid);
		f.add(id);
		f.add(lpwd);
		f.add(pwd);
		f.add(btn);
		f.add(result);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		

		f2 = new Frame("Window");
		f2.setSize(500, 500);
		f2.setLayout(null);
		f2.setVisible(false);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click!!!");
		System.out.println(id.getText());
		if (!id.getText().equals("") && !pwd.getText().equals("")) {
			ArrayList<MemberVo> ar = dao.list(id.getText());
			if (ar.size() != 0 && ar.get(0).getPwd().equals(pwd.getText())) {
				result.setText("로그인이 되었습니다");
				f2.setVisible(true);
				f.setVisible(false);
				f2.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}
				});

			} else {
				result.setText("로그인이 실패했습니다.");
			}
		} else {
			result.setText("틀렸습니다.");
		}
	}
}