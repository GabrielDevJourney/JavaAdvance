import java.util.List;

public class LoginController {
	private UserList userList = new UserList();
	private List<User> users;
	private final LoginView loginView;
	private boolean isValid = false;


	public LoginController(LoginView loginView) {
		userList.parseJsonToList();
		users = userList.getUserList();
		this.loginView = loginView;
	}

	public boolean isValid() {
		return isValid;
	}

	private String getEnteredUsername() {
		return loginView.getEnteredUsername();
	}

	private String getEnteredPassword() {
		return loginView.getEnteredPassword();
	}

	public void authenticateUser() {
		String username = getEnteredUsername();
		String password = getEnteredPassword();

		for (User user : users) {
			if (user.getName().equals(username) && user.getPassword().equals(password)) {
				isValid = true;
				return;
			}
		}
	}

	public void displayLoginResult() {
		loginView.showLoginStatus(isValid);
	}

	public void runLoginFlow() {
		loginView.promptUserCredentials();
		authenticateUser();
		displayLoginResult();
	}

}
