
/**
*Program Name: My Contact Manager V4
*Program Description: My Contact Manager V4 is a program that stores a contact's name, 
*phone number, contact address, and contact email. 
*It includes a menu with the options: view all entries, add entry, modify entry,
*delete entry, save book, load book, sort entries, search entry, and quit. 
*It uses the dynamic ArrayList class to hold arbitrarily many contacts.
*/


package noel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManagerV4 {

	// Declare a string constant and store a phone number regular expression in it for validation
	   static final String phoneNumRegExp = "\\d{3}[-]\\d{3}[-]\\d{4}";
	   
	// Declare a string constant and store a regular expression for the street name validation
	   static final String alphaRegExpStreet = "[a-zA-Z\\s\\.]+";
	   
	// Declare a string constant and store postal code regular expression for validation
	   static final String postalCodeRegExp = "[A-VXYa-vxy][0-9][A-Za-z] ?[0-9][A-Za-z][0-9]$";
	   
	// Declare a string constant and store alphabetic regular expression for validation
	   static final String alphaRegExp = "[a-zA-Z]+";
	   
	// Declare a string constant and store alphanumeric regular expression for validation
	   static final String alphaNumRegExp = "[a-zA-Z0-9]+";

	// Declare contactsList of type Contact
	   static ArrayList<Contact> contactsList = new ArrayList<Contact>();

	// Create Scanner object to accept input from keyboard
	   static Scanner input = new Scanner(System.in);
	   
       static int resultIndex = 0;

	/**
	 * Method Description: This main method will display a menu with the 
	 * options: view all entries, add entry, modify entry, delete entry, save
	 * book, load book, sort entries, search entry, and quit. It will prompt the
	 * user to select an option from 1-9. Based on the selected option, it will
	 * execute the proper method. 
	 */

	public static void main(String[] args) { // java application begins
		
		while (true) {
			
			System.out.println("---------------"); // Display a blank line
			
			// Display menu options to the user
			System.out.println("\nMenu:\n1.View All Entries \n2.Add Entry \n3.Modify Entry \n4.Delete Entry \n5.Save Book \n6.Load Book \n7.Sort \n8.Search \n9.Quit");

			System.out.println("Please enter your option(1 to 9):");
			int option = 0;
			
			try {
				option = Integer.parseInt(input.nextLine());
			} 
			catch (NumberFormatException nfe) {
				
				System.err.println("Sorry. Invalid Option. Please try again!");	
				continue;
				
			} 

			switch (option) { 
			
			case 1:				
				displayAllEntries(); 
				break;
				
			case 2:
				addNewEntry(); 
				break;
				
			case 3:
				modifyEntry(); 
				break;
				
			case 4:
				deleteEntry(); 
				break;
				
			case 5:
				saveBook(); 
				break;
				
			case 6:
				loadBook(); 
				break;
				
			case 7:
				sortEntries(); 
				break;

			case 8:
				searchEntries(); 
				break;

			case 9:
				System.out.println("Exiting..Good Bye!"); 
				input.close(); 
				System.exit(0); 
				
			default:
				// Message to the user if invalid option is chosen
				System.err.println("Invalid Option. Please re-try!");
				
			} 
			
		} 
		
	} 

	/**
	 * Method Name: searchEntries()
	 * Method Description: This method will perform search based on the search field
	 * option chosen by the user. It will display all the search results.
	 */

	public static void searchEntries() {

		// Prompt to the user to select from one of the five search options
		System.out.println("Please enter search field:1.First Name 2. Last Name 3.Number 4.Email 5.City");

	
		int searchOption = 0;

		try {

			searchOption = Integer.parseInt(input.nextLine());

			/*if the user types invalid search option, search field then
			 * display error message and return to the main menu.
			 */

			if (searchOption < 1 || searchOption > 5) {
				
				System.err.println("Sorry. Invalid Option. Please re-try");				
				return;
				
			} 
			System.out.println("Please enter search key");
			String searchKey = input.nextLine();

			/*
			 * Call the searchField method which performs search based on the
			 * selected field and selected search key and return the search
			 * result
			 */
			
			List<Contact> searchResults = searchField(searchOption, searchKey);

			if (searchResults.size() == 0) { 			
				System.out.println("No Search Results");
			} 

			else { // if the number of elements in the list is more than zero
				System.out.println("Search Results:");				
				System.out.println("--------------");				
				for (int i = 0; i < searchResults.size(); i++) {
					System.out.println(searchResults.get(i).toString());
				} 
			} 

		} 

		// If the user types text which isn't a required number, throw a NumberFormatException
		catch (NumberFormatException nfe) {

			// Display error message
			System.err.println("Sorry. Invalid Option. Please re-try!");
			return;
		} 
	} 

	/**
	 * Method Name: searchField(int searchOption,String searchKey)
	 * Method Description: Based on the parameters entered, this 
	 * method will search the contactList and return the search results.
	
	public static List<Contact> searchField(int searchOption, String searchKey) { 
		
		// Declare and create an empty search result list
		List<Contact> searchResults = new ArrayList<Contact>();

		for (int j = 0; j < contactsList.size(); j++) {
			Contact contact = contactsList.get(j);
			switch (searchOption) {

			case 1: // if search by first name option is selected
				
				if (contact.getFirstName().compareTo(searchKey) == 0) {
					searchResults.add(contact);
				} 
				break;

			case 2: // if search by last name option is selected
							
				if (contact.getLastName().compareTo(searchKey) == 0) {
					searchResults.add(contact);
				} 
				break;

			case 3: // if search by number option is selected
					
				if (contact.getNumber().compareTo(searchKey) == 0) {					
					searchResults.add(contact);

				} 
				break;

			case 4: // if search by email option is selected
					
				if (contact.getEmail().compareTo(searchKey) == 0) {
 					searchResults.add(contact);					
				} 
				break;

			case 5: // if search by city option is selected
					
				if (contact.getAddress().getCity().compareTo(searchKey) == 0) {
					searchResults.add(contact);
				} 
				break;

			default:				
				System.out.println("Sorry. Invalid Option.");
			} 

		} 
		return searchResults;

	} 

	/**
	 * Method Name: sortEntries()
	 * Method Description: This method is going to sort the contactsList (in ascending
	 * or descending order), based on the sort by options. 
	 */

	public static void sortEntries() {

		// Prompt to the user to select one of the following sort orders
		System.out.println("Please enter sort order:1.Ascending(A-Z) 2.Desecending(Z-A):");

		int sortOrderOption = 0;

		try {
			sortOrderOption = Integer.parseInt(input.nextLine());
			boolean asc = true;
			
			// if ascending (A-Z) sort option is selected
			if (sortOrderOption == 1) {				
				asc = true;
			} 
			
			else if (sortOrderOption == 2) { // if descending (Z-A) sort option is selected
				asc = false;
			} 

			else { // in case of invalid search option				
				System.err.println("Sorry. Invalid Option. Please re-try");
				return;	
			} 

			// Prompt to the user to select one of the five sort fields options
			System.out.println("Please enter sort field:1.First Name 2.Last Name 3.Number 4.Email 5.City");

			// convert string input to integer and store its value in integer variable “field”
			int field = Integer.parseInt(input.nextLine());
		
			switch (field) {

			case 1:
				sortByFirstName(asc);			
				System.out.println("Sorted by First Name Successfully");
				break;

			case 2:
				sortByLastName(asc); 
				System.out.println("Sorted by Last Name Successfully");				
				break;

			case 3:
				sortByNumber(asc); 				
				System.out.println("Sorted by Number Successfully");
				break;
				
			case 4:
				sortByEMail(asc); 
				System.out.println("Sorted by EMail Successfully");				
				break;

			case 5:
				sortByCity(asc); 				
				System.out.println("Sorted by City Successfully");
				break;

			default:				
				System.out.println("Sorry. Invalid Option");
			} 

		} catch (NumberFormatException nfe) {			
			System.err.println("Sorry Invalid Option. Please re-try");
		} 

	} 

	/**
	 * Method Name: sortByFirstName (boolean asc)
	 * Method Description: This method sorts the contactsList based on the first name of the contact
	 * Parameter: asc-true for ascending, asc-false for descending
	 */

	public static void sortByFirstName(boolean asc) { 
		
		for (int i = 0; i < contactsList.size(); i++) {			
			for (int j = 1; j < (contactsList.size() - i); j++) {
				Contact currcontact = contactsList.get(j - 1);
				Contact contact = contactsList.get(j);
				if (asc) { // if sort order is ascending (A-Z )
					
					if (currcontact.getFirstName().compareTo(contact.getFirstName()) > 0) {		
						swap(j - 1, j); 
					} 
				}
				
				else {	
					if (currcontact.getFirstName().compareTo(contact.getFirstName()) < 0) {	
						swap(j - 1, j); 
					} 	
				} 
				
			} 
			
		} 
		

	} 

	/**
	 * Method Name: sortByLastName(boolean asc)
	 * Method Description: This method sorts the contactsList based on the last name of the contact
	 */

	public static void sortByLastName(boolean asc) { 
		
		for (int i = 0; i < contactsList.size(); i++) {
			for (int j = 1; j < (contactsList.size() - i); j++) {
				Contact currcontact = contactsList.get(j - 1);
				Contact contact = contactsList.get(j);
				
				if (asc) { // if sort order is ascending (A-Z )	
					if (currcontact.getLastName().compareTo(contact.getLastName()) > 0) {
						swap(j - 1, j); 
					} 
				} 				
				else {
					if (currcontact.getLastName().compareTo(contact.getLastName()) < 0) {	
						swap(j - 1, j); 	
					} 
				} 
				
			} 
			
		} 
		
	} 

	/**
	 * Method Name: sortByNumber (boolean asc)
	 * Method Description: This method sorts the contactsList based on the phone number of the contact
	 */

	public static void sortByNumber(boolean asc) { 
		
		for (int i = 0; i < contactsList.size(); i++) {			
			for (int j = 1; j < (contactsList.size() - i); j++) {
				Contact currcontact = contactsList.get(j - 1);
				Contact contact = contactsList.get(j);
	
				if (asc) { // if sort order is ascending (A-Z )				
					if (currcontact.getNumber().compareTo(contact.getNumber()) > 0) {
						swap(j - 1, j); 
					} 
				} 
				
				else {	
					if (currcontact.getNumber().compareTo(contact.getNumber()) < 0) {	
						swap(j - 1, j); 
					} 	
				} 	
			} 
		} 
		
	} 

	/**
	 * Method Name: sortByEMail(boolean asc)
	 * Method Description: This method sorts the contactsList based on the email id of the contact
	 */

	public static void sortByEMail(boolean asc) {
		
		for (int i = 0; i < contactsList.size(); i++) {			
			for (int j = 1; j < (contactsList.size() - i); j++) {
				
				Contact currcontact = contactsList.get(j - 1);
				Contact contact = contactsList.get(j);
				if (asc) {
					
					if (currcontact.getEmail().compareTo(contact.getEmail()) > 0) {						
						swap(j - 1, j); 
					} 

					else {
						if (currcontact.getEmail().compareTo(contact.getEmail()) < 0) {	
							swap(j - 1, j); 
						} 
					} 	
				} 	
			} 	
		} 
	} 

	/**
	 * Method Name: sortByCity(boolean asc)
	 * Method Description: This method sorts the contactsList based on the city of the contact
	 */

	public static void sortByCity(boolean asc) { 
		
		for (int i = 0; i < contactsList.size(); i++) {			
			for (int j = 1; j < (contactsList.size() - i); j++) {
				Contact currcontact = contactsList.get(j - 1);
				Contact contact = contactsList.get(j);
				
				if (asc) { 	
					if (currcontact.getAddress().getCity().compareTo(contact.getAddress().getCity()) > 0) {	
						swap(j - 1, j);
					} 	
				}
				
				else {	
					if (currcontact.getAddress().getCity().compareTo(contact.getAddress().getCity()) < 0){	
						swap(j - 1, j); 	
					} 	
				} 	
			}
			
		} 
		
	} 

	public static void swap(int i, int j) { 
		
		Contact temp = contactsList.get(i);		
		contactsList.set(i, contactsList.get(j));
		contactsList.set(j, temp);

	} 
	/**
	 * Method Name: loadBook()
	 * Method Description: This method loads the entries of the file contacts.txt from current
	 * working directory into contactsList.
	 */

	public static void loadBook() { 

		// declare a variable of type BufferedReader and initialize it to null
		BufferedReader in = null;
		
		try {
			
			// open input stream contact.txt for reading purpose.
			in = new BufferedReader(new FileReader("contacts.txt"));			
			String entry = null;
			Contact contact = null;
			Address address = null;
			
			// read the current line into entry variable and check if that entry is not null
			while ((entry = in.readLine()) != null) {	
				String[] entryData = entry.split(";");
				contact = new Contact(entryData[0], entryData[1], entryData[2]);
				String addressLine = entryData[3];
				String addressParts[] = addressLine.split(",");

				address = new Address();
				address.setStreetNum(addressParts[0]);
				address.setStreetName(addressParts[1]);
				address.setCity(addressParts[2]);
				address.setProvince(addressParts[3]);
				address.setCountry(addressParts[4]);
				address.setPostalCode(addressParts[5]);
				contact.setAddress(address);
				contact.setEmail(entryData[4]);
				contactsList.add(contact);

			} 
			
			System.out.println("Successfully loaded from the book.");
			
			in.close(); 
			
		} catch (IOException e) {// if file not found display the following message			
			e.printStackTrace(); 
		} 
		
	} 

	/**
	 * Method Name: saveBook()
	 * Method Description: This method saves all entries of contactsList to contacts.txt file in
	 * current working directory.
	 */

	public static void saveBook() { 
		
		try {
			
			FileWriter fw = new FileWriter("contacts.txt");
			
			PrintWriter output = new PrintWriter(fw);
			StringBuilder entry = null;
			
			for (int i = 0; i < contactsList.size(); i++) {
				Contact contact = contactsList.get(i);

				entry = new StringBuilder();
				
				// Build a line entry which represents current contact object
				entry.append(contact.getFirstName()).append(";").append(contact.getLastName()).append(";")
						.append(contact.getNumber()).append(";").append(contact.getAddress().getStreetNum()).append(",")
						.append(contact.getAddress().getStreetName()).append(",").append(contact.getAddress().getCity())
						.append(",").append(contact.getAddress().getProvince()).append(",")
						.append(contact.getAddress().getCountry()).append(",")
						.append(contact.getAddress().getPostalCode()).append(";").append(contact.getEmail())
						.append("\n");

				output.write(entry.toString()); 

			} 

			output.flush(); 
			output.close(); 
			
			System.out.println("Finished writing to file.");
			
		} 
		
		catch (IOException e) {			
			e.printStackTrace(); 
		} 
		
	} 

	public static void deleteEntry() { 

		// Prompt to user to choose one of the specified search options
		System.out.println("\nPlease enter the search Key:\n1.Search By Name\n2.Search By Number");
		
		int searchOption = 0; 
		
		try {
			searchOption = Integer.parseInt(input.nextLine());

		} catch (NumberFormatException nfe) {
			
			System.err.println("Sorry. Invalid Option. Please re-try"); 
			
			return;
			
		} 
		
		if (searchOption == 1) { // If search by name option is selected
			
			System.out.println("\nPlease enter Name to search:"); 
			
		}
		
		else if (searchOption == 2) { // If search by number option is selected
			
			System.out.println("\nPlease enter Number to search:"); 
			
		} 
		
		else {
			
			System.err.println("Sorry. Invalid Option. Please re-try"); 
			return;
			
		} 

	
		boolean isValid = false;
		String searchKey;
		
		do {
			
			searchKey = input.nextLine();

			if (searchOption == 1) {
				
				isValid = true; 
				
			} 
			
			else if (searchOption == 2) {
				
				isValid = validatePhoneNum(searchKey);
				
				if (isValid == false) { 
					
					System.err.println("Invalid. Please enter in 123-456-5678 format only");
					
				}
				
			} 
			
			else {
				
				System.err.println("Sorry. Invalid Option"); 
				
				return;
				
			}
			
		} while (!isValid); 

		/*
		 * Call searchEntry method to search if the search key/option is present
		 * or not, and store the result into boolean variable isFound
		 */
		
		boolean isFound = searchEntry(searchKey, searchOption);
		
		if (isFound) {
			System.out.println("Successfully found the entry. Deleting the entry.");
			contactsList.remove(resultIndex);

		} 
		
		else {
			
			System.out.println("Entry NOT found."); 
			
		}
		
	}

	/**
	 * Method Name: modifyEntry()
	 * Method Description: This method modifies the entry from the contactsList
	 */

	public static void modifyEntry() { 
		
		System.out.println("\nPlease enter the search Key:\n1.Search By Name\n2.Search By Number");
		int searchOption = Integer.parseInt(input.nextLine());
		boolean isValid = false;
		
		
		String searchKey;
		
		do {

			
			if (searchOption == 1) {
			
				System.out.println("\nPlease enter Name to search:");
				
				searchKey = input.nextLine();
				
				isValid = true;
				
			}
			
			else if (searchOption == 2) { 
				
				
				System.out.println("\nPlease enter Number to search:");
				searchKey = input.nextLine();
				isValid = validatePhoneNum(searchKey);
				
				if (isValid == false) { 
					System.err.println("Invalid. Please enter in 123-456-5678 format only");
					
				} 
				
			} 
			else {
				
				System.err.println("Sorry. Invalid Option");
				
				return;
				
			} 

		} while (!isValid);

		boolean isFound = searchEntry(searchKey, searchOption);
		
		if (isFound) { 
			Contact modifyContact = contactsList.get(resultIndex);
			System.out.println("Successfully found the entry. Please enter new data.");
			enterAndValidateData(false, modifyContact);
			
			System.out.println("Completed Updating.");
			
		} 
		
		else { 

			System.out.println("Entry NOT found."); 
			
		}
		
	} 

	/**
	 * Method Name: searchEntry (String searchKey, int searchOption)
	 * Method Description: This method searches the contactsList if the searchKey is present or not.
	 */

	public static boolean searchEntry(String searchKey, int searchOption) { 

		boolean found = false; 
		
		for (int i = 0; i < contactsList.size(); i++) {
			
			Contact contact = contactsList.get(i);
			
			if (searchOption == 1) { 
				
				if (contact.getFirstName().compareTo(searchKey) == 0) {
					found = true; 					
					resultIndex = i; 
					break;
					
				} 
				
			} 
			
			else if (searchOption == 2) {
				
				if (contact.getNumber().compareTo(searchKey) == 0) {
					
					found = true; 
					resultIndex = i; 
					break;
					
				} 
				
			} 
			
		} 
		
		return found; 
		
	} 

	/**
	 * Method Name: displayAllEntries()
	 * Method Description: This method display all entries of contactsList
	 */

	public static void displayAllEntries() { 

		if (contactsList.size() == 0) {
			System.out.println("No Entries to Display.");
	
			return;
			
		}
		System.out.println("All Entries:");
		System.out.println("-------------");
		
		for (int i = 0; i < contactsList.size(); i++) {
			Contact contact = contactsList.get(i);
			System.out.println(contact);
			
		} 
		
	} 

	/**
	 * Method Name: addNewEntry
	 * Method Description: This method adds New Entry to contactsList
	 */

	public static void addNewEntry() { 
		Contact newContact = enterAndValidateData(true, null);
		contactsList.add(newContact);
		System.out.println("\nAdded Entry Successfully.\n");	
	} 

	/**
	 * Method Name: enterAndValidateData(boolean create, Contact newContact)
	 * Method Description: This method display two types of prompts for users: input for original entry, 
	 * and prompt for new input for entry to be modified. In case of entry to be modified the word
	 * “new” is added before the entry name. It then calls appropriate method to validate the user input, 
	 * wherever applicable.
	 */

	private static Contact enterAndValidateData(boolean create, Contact newContact) { 

		if (create)
			System.out.println("Please enter First Name:");
		
		else
			System.out.println("Please enter New First Name:");
			String firstName = input.nextLine();
		
		if (create)
			System.out.println("Please enter Last Name:");
		else
			System.out.println("Please enter New Last Name:");
			String lastName = input.nextLine();
		
			boolean isValid = false;
		
			String number;
		
		do {
			
			if (create)
				System.out.println("Please enter Phone Number in format 123-456-7610 only:");
			
			else
				System.out.println("Please enter New Phone Number in format 123-456-7610 only:");
				number = input.nextLine();

				isValid = validatePhoneNum(number);
			
	
				if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		
		if (create) { 
			newContact = new Contact(firstName, lastName, number);
			
		}
		
		else { 
			
			newContact.setFirstName(firstName);
			newContact.setLastName(lastName);
			newContact.setNumber(number);
			
		} 
		Address newAddress = new Address();
		isValid = false;
		String streetNum;
		
		do {
			
			if (create)
				System.out.println("Address:\nPlease enter Street#:");
			
			else
				System.out.println("Address:\nPlease enter New Street#:");

			streetNum = input.nextLine();
			
			isValid = validateAlphaNumeric(streetNum);


			if (!isValid)

				System.err.println("Invalid");
			
		} while (!isValid);
		
		newAddress.setStreetNum(streetNum);
		
		isValid = false;
		
		String streetName;
		
		do {
			
			if (create)
				System.out.println("Please enter Street Name:");
			
			else
				System.out.println("Please enter New Street Name:");

			streetName = input.nextLine();
			isValid = validateAlphaStreetName(streetName);
			if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		
		newAddress.setStreetName(streetName);

		isValid = false;
		
		String city;
		
		do {
			
			if (create)
				
				System.out.println("Please enter City:");
			
			else
				System.out.println("Please enter New City:");
			
			city = input.nextLine();
			isValid = validateAlpha(city);
			if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		
		newAddress.setCity(city);
		
		isValid = false;
		
		String province;
		
		do {
			
			if (create)
				
		        	System.out.println("Please enter Province:");
			
			else
				System.out.println("Please enter New Province:");
			province = input.nextLine();
			isValid = validateAlpha(province);
			if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		
		newAddress.setProvince(province);
		
		isValid = false;
		
		String country;
		
		do {
			
			if (create)
			
				System.out.println("Please enter Country:");
			
			else
				System.out.println("Please enter New Country:");
			country = input.nextLine();
			isValid = validateAlpha(country);
			if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		
		newAddress.setCountry(country);
		
		isValid = false;
		
		String postalCode;
		
		do {
			
			if (create) 
				System.out.println("Please enter Postal Code:");
			
			else
				System.out.println("Please enter New Postal Code:");
			postalCode = input.nextLine();
			isValid = validatePostalCode(postalCode);
			if (!isValid)
				System.err.println("Invalid");
			
		} while (!isValid);
		newAddress.setPostalCode(postalCode);
		newContact.setAddress(newAddress);
		
		if (create)
			System.out.println("Please enter E-Mail:");

		else
			System.out.println("Please enter New E-Mail:");
		newContact.setEmail(input.nextLine());

		return newContact;
	}

	/**
	 * Method Name: validatePhoneNum
	 * Method Date: June 4,2016
	 * Method Description: This method validates phone number inputed by user
	 */

	private static boolean validatePhoneNum(String phoneNum) { 
		
		Pattern pattern = Pattern.compile(phoneNumRegExp);
		Matcher matcher = pattern.matcher(phoneNum);
		return matcher.matches();
	} 

	/**
	 * Method Name: validateAlphaNumeric(String number)
	 * Method Description: This method validates street number inputed by user
	 */

	private static boolean validateAlphaNumeric(String number) {
		Pattern pattern = Pattern.compile(alphaNumRegExp);
		Matcher matcher = pattern.matcher(number);

		return matcher.matches();
	}

	/**
	 * Method Name: validateAlphaStreetName(String name)
	 * Method Description: This method validates street name inputed by user
	 */

	private static boolean validateAlphaStreetName(String name) { 
		Pattern pattern = Pattern.compile(alphaRegExpStreet);
		Matcher matcher = pattern.matcher(name);
		
		return matcher.matches();
	}

	/**
	 * Method Name: validateAlpha(String name)
	 * Method Description: This method validates city, province or country inputed by user
	 */

	private static boolean validateAlpha(String name) { 

		Pattern pattern = Pattern.compile(alphaRegExp);
		Matcher matcher = pattern.matcher(name);
	
		return matcher.matches();
		
	} 

	/**
	 * Method Name: validatePostalCode(String postaCode)
	 * Method Description: This method validates Postal Code inputed by user
	 */

	private static boolean validatePostalCode(String postaCode) { 
		Pattern pattern = Pattern.compile(postalCodeRegExp);
		Matcher matcher = pattern.matcher(postaCode);
	
		return matcher.matches();
	} 

} // end class ContactManagerV4

