import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;

public final class AddressBookApp {

    private static String user;
    private static String password;
    
    private static String lastName;
    private static String firstName;
    private static String workNumber;
    private static String cellPhone;
    private static String contactEmail;
    private static String contactAddress;
    private static String contactCity;
    private static String contactState;
    private static String contactZip;

    private static final Scanner input = new Scanner(System.in);
    private static Connection con;
    private static Statement st;

    private AddressBookApp() {
        // Don't let anyone instantiate the class
    }

    public static void main(String[] args) throws SQLException {
        int choice;

        while (true) {
            try {
                getUserCredentials();
                establishConnection();
                System.out.println("***** Connection to database success...*****");
                break;
            } catch (SQLException e) {
                System.out.println("***** Access denied *****");
            }
        }

        do {
            printMainMenu();

            while (!input.hasNextInt()) {
                input.next();
                System.out.println("***** Invalid choice *****");
                printMainMenu();
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    displayContacts();
                    break;
                case 2:
                    createNewContact();
                    break;
                case 3:
                    selectContactAndDelete();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("***** Invalid choice *****");
            }
        } while (choice != 4);

        System.out.println("Exiting program...");
    }

    public static void getUserCredentials() {
        System.out.println("Please enter your credentials to access a database: ");
        System.out.print("Enter user name: ");
        user = input.nextLine().trim();
        System.out.print("Enter password: ");
        password = input.nextLine().trim();
    }

    public static Connection establishConnection() throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/contacts";
        con = DriverManager.getConnection(connectionUrl, user, password);
        return con;
    }

    public static Statement createStatement() throws SQLException {
        st = establishConnection().createStatement();
        return st;
    }

    public static void displayContacts() throws SQLException {
        printHeader();
        ResultSet resultSet = createStatement().executeQuery("SELECT * from contact_entries");

        while (resultSet.next()) {
            Formatter ft2 = new Formatter();
            ft2.format("%-6s %-16s %-12s %-15s %-15s %-16s %-20s %-15s %-10s %-15s",
                    resultSet.getInt("id"),
                    resultSet.getString("l_name"),
                    resultSet.getString("f_name"),
                    resultSet.getString("work_number"),
                    resultSet.getString("cellphone_number"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("zip"));
            System.out.println(ft2);
        }
        printDashes();
    }

    public static void collectInfoForNewContactCreation() {
        do {
            System.out.print("Enter Last Name: \n");
            lastName = input.nextLine().trim();
        } while (lastName.equals(""));

        do {
            System.out.print("Enter First Name: \n");
            firstName = input.nextLine().trim();
        } while (firstName.equals(""));

        do {
            System.out.print("Enter work number(10 digits): \n");
            workNumber = input.nextLine().trim();
        } while (workNumber.length() != 10 || !workNumber.matches("\\d+"));

        do {
            System.out.print("Enter cellphone number(10 digits): \n");
            cellPhone = input.nextLine().trim();
        } while (cellPhone.length() != 10 || !workNumber.matches("\\d+"));

        do {
            System.out.print("Enter email: \n");
            contactEmail = input.nextLine().trim();
        } while (contactEmail.equals("") || !contactEmail.matches("^(.+)@(.+)$"));

        do {
            System.out.print("Enter Address: \n");
            contactAddress = input.nextLine().trim();
        } while (contactAddress.equals(""));

        do {
            System.out.print("Enter City: \n");
            contactCity = input.nextLine().trim();
        } while (contactCity.equals(""));

        do {
            System.out.print("Enter State(2 letters): \n");
            contactState = input.nextLine().trim().toUpperCase();
        } while (contactState.length() != 2);

        do {
            System.out.print("Enter Zip code(5 digits): \n");
            contactZip = input.nextLine().trim();
        } while (contactZip.length() != 5 || !contactZip.matches("\\d+"));
    }

    public static int createNewId() throws SQLException {
        int max = 0;
        int counter;
        ResultSet resultSet = createStatement().executeQuery("SELECT * from contact_entries");
        while (resultSet.next()) {
            counter = resultSet.getInt("id");

            if (counter > max) {
                max = counter;
            }
        }
        return max + 1;
    }

    public static void createNewContact() throws SQLException {
        collectInfoForNewContactCreation();

        String sqlQuery = "INSERT INTO contact_entries(id, l_name, f_name, work_number, cellphone_number, email, address, city, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pr = con.prepareStatement(sqlQuery);
        pr.setInt(1, createNewId());
        pr.setString(2, lastName);
        pr.setString(3, firstName);
        pr.setString(4, workNumber);
        pr.setString(5, cellPhone);
        pr.setString(6, contactEmail);
        pr.setString(7, contactAddress);
        pr.setString(8, contactCity);
        pr.setString(9, contactState);
        pr.setString(10, contactZip);
        pr.executeUpdate();
        System.out.println("***** New Contact successfully created *****");
    }

    public static void selectContactAndDelete() throws SQLException {
        int contactId;
        int choice2;

        do {
            System.out.println("1 - delete contact?\n2 - return to main menu ");
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("***** Invalid choice *****");
                System.out.println("1 - delete contact?\n2 - return to main menu ");
            }
            choice2 = input.nextInt();
            input.nextLine();

            switch (choice2) {

                case 1: {
                    System.out.print("Enter contact id to delete: ");
                    contactId = input.nextInt();
                    input.nextLine();

                    if (ifContactExists(contactId)) {
                        st.executeUpdate("DELETE FROM contact_entries WHERE id = " + contactId);
                        System.out.println("***** Contact successfully deleted *****");
                    } else {
                        System.out.println("***** Contact with id " + contactId + " does not exist. *****");
                    }
                    break;
                }

                case 2:
                    break;
                default:
                    System.out.println("***** Invalid choice *****");
            }
        } while (choice2 != 2);
    }

    public static boolean ifContactExists(int id) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM contact_entries");
        while (rs.next()) {
            if (rs.getInt("id") == id) {
                return true;
            }
        }
        return false;
    }

    public static void printHeader() {
        Formatter ft = new Formatter();
        printDashes();
        ft.format("%-6s %-16s %-12s %-15s %-15s %-16s %-20s %-15s %-10s %-15s",
                "ID",
                "Last Name",
                "First Name",
                "Work Phone",
                "Cellphone", "E-mail",
                "Address", "City",
                "State", "Zip Code");
        System.out.println(ft);
        printDashes();
    }

    public static void printMainMenu() {
        System.out.println("---------------------------------------------------------------------- MAIN MENU -" +
                "------------------------------------------------------------");
        System.out.println("1 - Display Contacts");
        System.out.println("2 - Create New Contact");
        System.out.println("3 - Delete Contact");
        System.out.println("4 - EXIT");
    }

    public static void printDashes() {
        System.out.println("--------------------------------------------------------------------------" +
                "--------------------------------------------------------------------");
    }
}
