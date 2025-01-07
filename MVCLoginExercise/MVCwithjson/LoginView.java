import java.util.Scanner;

public class LoginView {
	private Scanner scanner = new Scanner(System.in);
	private String nameInput;
	private String passwordInput;

	public void promptUserCredentials(){
		System.out.println("Username: ");
		nameInput = scanner.nextLine();
		System.out.println("Password ");
		passwordInput = scanner.nextLine();
	}

	public String getEnteredUsername() {
		return nameInput;
	}

	public String getEnteredPassword() {
		return passwordInput;
	}

	public void showLoginStatus(boolean isValid){
		if(isValid){
			System.out.println("Your are wellcome");
		}else{
			System.out.println("Try again!");
		}
	}
}
