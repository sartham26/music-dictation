package musicdictation;

public class UserAccount {

	private String accountId;
	private String email;
	private String passwordHash;

	public void register(String email, String password) {
		throw new UnsupportedOperationException();
	}

	public void login(String password) {
		throw new UnsupportedOperationException();
	}

	public void sendFeedback(Composition composition, String message) {
		throw new UnsupportedOperationException();
	}

}