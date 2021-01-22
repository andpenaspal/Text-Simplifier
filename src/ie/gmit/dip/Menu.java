package ie.gmit.dip;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * An option Menu. This class provides a command-prompt Menu to navigate through different options [Simplify Text,
 * Change sources and Exit].<p>
 * A separate Thread loads the default database from this class.
 * 
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public class Menu {

	/**
	 * Scanner to read user Input.
	 */
	private Scanner s = new Scanner(System.in);
	/**
	 * Database object to create and load the default database. 
	 */
	private DataBase dataBase = new DBDefaultSources();
	/**
	 * Thread to set and load the database on the background. 
	 */
	private Thread t = null;

	/**
	 * Prints the Heading of the Program and starts the command-prompt Option Menu.<p>
	 * The Heading shows the basic information about the Program
	 */
	public void heading() {
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.1                *");
		System.out.println("*       (AKA Orwellian Language Compliance)       *");		
		System.out.println("*                                                 *");
		System.out.println("***************************************************");

		System.out.println(ConsoleColour.RESET);
		
		//Start the Thread to load the Database on the Background
				t = new Thread(dataBase);
				t.start(); 
		
		//Give time to catch IOException if Default Sources Files not found
		//Showing error just before the Main Menu to make easier to understand where to Change Sources
		//Even with Error Message in the middle, Scanner would still be waiting for Menu Input, possible confusion
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
	}

	/**
	 * Main Menu of the application. Asks & reads for Input and shows the different options: simplify text, 
	 * change sources & exit. 
	 */
	private void menu() {
		int menuOption = 0;

		do {
			try {
				System.out.println("===================================================");
				System.out.println("Main Menu\n===================================================");
				System.out.println("Please, select one option:");
				System.out.println("1) Simplify text \n2) Change Sources \n3) Exit");

				menuOption = s.nextInt();
				if(menuOption > 3) System.out.println("This option does not exist.");
			}catch (InputMismatchException e) {
				System.out.println("Value not valid. Please, select a valid option [1-3]\n");
			}
			s.nextLine();
		} while (menuOption < 1 | menuOption > 3);

		redirectMenu(menuOption);

	}

	/**
	 * Redirects depending on the Input from the Main Menu
	 * @param menuOption Option selected on the Main Menu
	 */
	private void redirectMenu(int menuOption) {
		switch (menuOption) {
		case 1:
			textMenu();
			break;
		case 2:
			changeSourcesMenu();
			break;
		case 3:
			exit();
		default:
			break;
		}
	}

	/**
	 * Asks for user Input about the text to simplify and shows in console the simplified text.
	 */
	private void textMenu() { //O(n((log n)^2))
		System.out.println(ConsoleColour.RESET);
		System.out.println("Please, Enter Text>");
		String text = s.nextLine();
		isLoaded();

		//Pass the object where we loaded the database, not a new one
		Simplify simplify = new Simplify(dataBase);		
		System.out.println("\nSimplified Text: \n" + simplify.getText(text)); //O(n((log n)^2))
		System.out.println();
		
		//Looks cool
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		menu();
	}
	/**
	 * Makes sure the database is loaded before the program continues
	 */
	private void isLoaded() {
		if(t.isAlive()) {
			System.out.println("Loading DataBase...");
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Load Completed :)");
		}
	}

	/**
	 * Menu to change and load the database sources. Allows multiple source files.
	 */
	private void changeSourcesMenu() { //O(n^3(log n))
		System.out.println("Warning: the files must be located in the \"src\" folder.\n");
		System.out.println("Please, write the name of your file with the Common Words: "
				+ "\n(** For more than one file, write the names separated by commas ',')");
		String sources1 = s.nextLine();

		System.out.println("Please, write the name of your file with the Thesaurus: "
				+ "\n(** For more than one file, write the names separated by commas ',')");
		String sources2 = s.nextLine();

		//Set the database as the new database with the custom sources
		this.dataBase = new DBMCustomSources(sources1, sources2);
		t = new Thread(dataBase); 
		t.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menu();
	}
	/**
	 * Closes Scanner and exit the program
	 */
	private void exit() {
		System.out.println("\n* Text Simplifier has been closed * \nThanks and see you soon!");
		s.close();
		System.exit(0);
	}

}
