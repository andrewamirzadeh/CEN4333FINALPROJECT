import java.util.Scanner;

public class Menu {
	int userResponse;
	int newUserResponse;
	Scanner input = new Scanner(System.in);
	
	public int getNewUserResponse() {
		return newUserResponse;
	}

	public void setNewUserResponse(int newUserResponse) {
		this.newUserResponse = newUserResponse;
	}

	public int getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(int userResponse) {
		this.userResponse = userResponse;
	}
	
public int printNewUserMenu() {
	do {
		try {
			System.out.print("----New User Main Menu---- \n\n(1). Login. \n(2). Create new User. \n"
					+ "(3). Exit \n");
			newUserResponse = Integer.parseInt(input.next());
			if (newUserResponse < 1 || newUserResponse > 3) {
				System.out.print("Enter a value of 1 to 3. \n");
			}
		} catch (Exception E) {
			System.out.print("Please enter a valid value. \n");
		}
	} while (newUserResponse < 1 || newUserResponse > 3);	
	
	
	
	return userResponse;
	}
	
	public int printMenu() {
		do {
			try {
				System.out.print("----Main Menu---- \n\n(1). Add a To Do List Item. \n(2). Delete a To Do List Item. \n"
						+ "(3). List To Do Items \n(4). Edit a task\n(5). Exit\n");
				userResponse = Integer.parseInt(input.next());
				if (userResponse < 1 || userResponse > 5) {
					System.out.print("Enter a value of 1 to 5. \n");
				}
			} catch (Exception E) {
				System.out.print("Please enter a valid value. \n");
			}
		} while (userResponse < 1 || userResponse > 5);

		return userResponse;
	}
	
	
	
	
}