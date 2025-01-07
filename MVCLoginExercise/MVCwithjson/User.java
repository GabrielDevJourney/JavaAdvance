public class User {
	private final String username;
	private final String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

/*	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User user){
			User userToCompareTo = user;
			return this.username.equals(userToCompareTo.username) && this.password.equals(userToCompareTo.password);
		}
		return false;
	}*/
}
