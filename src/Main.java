public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		User user = new User();

		do {
			menu.printNewUserMenu();
			switch (menu.getNewUserResponse()) {
			case 1:
				try {
					user.loginUser();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					user.createUser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Thanks for using the program!");
				break;
			}

		} while (menu.getNewUserResponse() != 3);

	}

}
