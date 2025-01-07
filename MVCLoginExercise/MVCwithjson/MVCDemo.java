public class MVCDemo {
	public static void main(String[] args) {
		User user = new User("gabriel", "eusougay");
		LoginView loginView = new LoginView();
		LoginController loginController = new LoginController(loginView);

		loginController.runLoginFlow();
	}
}
