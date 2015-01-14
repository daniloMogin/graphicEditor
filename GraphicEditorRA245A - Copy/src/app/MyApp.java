package app;

public class MyApp {

	public static void main(String[] args) {

		new MainFrame();
		MainFrame mf = MainFrame.getInstance();
		mf.setLocationRelativeTo(null);
		mf.setVisible(true);
	}

}
