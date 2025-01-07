import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserList {
	private List<User> userList = new ArrayList<>();

	public List<User> getUserList() {
		return userList;
	}

	public void parseJsonToList(){
		try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
			String line;
			String username = null;

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.contains("username")) {
					// Get text between the quotes after username:
					username = line.split("\"username\": \"")[1].replace("\"", "").replace(",", "");
				}
				else if (line.contains("password") && username != null) {
					// Get text between the quotes after password:
					String password = line.split("\"password\": \"")[1].replace("\"", "").replace(",", "");
					userList.add(new User(username, password));
					username = null;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}
