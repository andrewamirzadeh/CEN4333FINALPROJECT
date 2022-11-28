import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class User {
	private String firstName;
	private String lastName;
	private String password;

	private boolean success;

	Scanner input = new Scanner(System.in);

	Connectors connectors = new Connectors();
	Menu menu = new Menu();

	public boolean createUser() throws Exception {
		Connection con = null;

		System.out.println("Enter your first name: ");
		firstName = input.nextLine();

		System.out.println("Enter your last name: ");
		lastName = input.nextLine();

		System.out.println("Create a password: ");
		password = input.nextLine();

		String sql = "INSERT INTO user (firstName, lastName, password) VALUE (?, ?, ?)";
		con = Connectors.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);

		con.setAutoCommit(false);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, password);

		success = ps.execute();

		con.commit();
		ps.close();
		con.close();

		return success;

	}

	public boolean loginUser() throws Exception {
		Connection con = null;

		System.out.println("Enter your first name: ");
		firstName = input.nextLine();

		System.out.println("Enter your last name: ");
		lastName = input.nextLine();

		System.out.println("Enter your password: ");
		password = input.nextLine();



		String sql = "SELECT * FROM user WHERE firstName = (?) AND lastName = (?) and password = (?)";
		//String sql = "INSERT INTO user (firstName, lastName, password) VALUE (?, ?, ?)";
		con = Connectors.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);

		con.setAutoCommit(false);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, password);

		success = ps.execute();

		ResultSet result = ps.executeQuery();


		if (result.next()) {
			do {
				menu.printMenu();
				switch (menu.getUserResponse()) {
				case 1:
					
					PreparedStatement statement4 = con.prepareStatement("SELECT iduser FROM user WHERE password LIKE (?)\n");
							 
					statement4.setString(1, password);

					ResultSet displayResult1 = statement4.executeQuery();
					
					if (displayResult1.next()) {
						int insertID = displayResult1.getInt(1);
						System.out.print("Enter a description of an item to add to your to do list: ");
						String item = input.nextLine();
						
						String qry1 ="Insert into todolistitem (iduser,itemDescription) VALUES (?, ?)"; 
						PreparedStatement pstmt1 = con.prepareStatement(qry1);
						pstmt1.setInt(1,insertID); 
						pstmt1.setString(2, item);
						pstmt1.executeUpdate();   
					}

					break;

				case 2:
					try{
						PreparedStatement statement3 = con.prepareStatement("SELECT * FROM todolistitem "
								+ "tdl join user u on tdl.iduser = u.iduser WHERE u.password LIKE (?) ORDER BY idtoDoListItem ASC");
						statement3.setString(1, password);

						ResultSet displayResult = statement3.executeQuery();



						while(displayResult.next()){
							System.out.println();
							System.out.print("ID Number: "+ displayResult.getInt("idtoDoListItem"));
							System.out.print(" ");
							System.out.println();
							System.out.print("To do task: " + displayResult.getString("itemDescription"));
							System.out.println();

						}
						System.out.println();
						System.out.println("Enter the ID number of the task you wish to delete: \n");
						int deleteID = input.nextInt();

						String qry ="delete from todolistitem where idtodolistitem= ?"; 
						PreparedStatement pstmt = con.prepareStatement(qry);
						pstmt.setInt(1,deleteID); 
						pstmt.executeUpdate();           

					}catch(Exception e){System.out.println(e);}

					break;
				case 3:

					try{
						PreparedStatement statement2 = con.prepareStatement("SELECT * FROM todolistitem "
								+ "tdl join user u on tdl.iduser = u.iduser WHERE u.password LIKE (?) ORDER BY idtoDoListItem ASC");
						statement2.setString(1, password);

						ResultSet displayResult = statement2.executeQuery();

						while(displayResult.next()){
							System.out.println();
							System.out.print("ID Number: "+ displayResult.getInt("idtoDoListItem"));
							System.out.print(" ");
							System.out.println();
							System.out.print("To do task: " + displayResult.getString("itemDescription"));
							System.out.println();
						}
						System.out.println("All tasks have been listed! \n");

					}catch(Exception e){System.out.println(e);}
					break;
				case 4:
					try{
						PreparedStatement statement7 = con.prepareStatement("SELECT * FROM todolistitem "
								+ "tdl join user u on tdl.iduser = u.iduser WHERE u.password LIKE (?) ORDER BY idtoDoListItem ASC");
						statement7.setString(1, password);

						ResultSet displayResult7 = statement7.executeQuery();



						while(displayResult7.next()){
							System.out.println();
							System.out.print("ID Number: "+ displayResult7.getInt("idtoDoListItem"));
							System.out.print(" ");
							System.out.println();
							System.out.print("To do task: " + displayResult7.getString("itemDescription"));
							System.out.println();


						}
						System.out.println();
						System.out.println("Enter the ID number of the task you wish to edit: \n");
						int editID = input.nextInt();
						input.nextLine();
						
						System.out.println("What would you like to change it to?");
						String editTask = input.nextLine();

						String qry ="UPDATE todolistitem SET itemDescription = (?) WHERE idtodolistitem= ?"; 
						PreparedStatement pstmt7 = con.prepareStatement(qry);
						
						pstmt7.setString(1, editTask);
						pstmt7.setInt(2,editID); 
						pstmt7.executeUpdate();           



					}catch(Exception e){System.out.println(e);}
					break;
				case 5:
					System.out.println("Thanks for using the program!!!");
					ps.close();
					break;

				}

			} while (menu.getUserResponse() != 5);
		}
		else {
			System.out.println("No user was found");
		}

		con.commit();
		ps.close();
		con.close();

		return success;

	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
