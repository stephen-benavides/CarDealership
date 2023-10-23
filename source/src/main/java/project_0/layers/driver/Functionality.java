package project_0.layers.driver;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import project_0.layers.logging.MyLogger;
import project_0.layers.models.Car;
import project_0.layers.models.Offer;
import project_0.layers.models.User;
import project_0.layers.service.CarService;
import project_0.layers.service.OfferService;
import project_0.layers.service.UserService;

public class Functionality {

	public static void main(String[] args) {
		MyLogger.logger.info("Program Started");
//////////////////////////////////////////////////////
///////////////////////Menus//////////////////////////
//////////////////////////////////////////////////////
		String c_menu = "1. View all the cars\n" + "2. Make an offer\n" + "3. View the cars that you own\n"
				+ "4. View remaining payments\n" + "5. Montly Payments";

		String e_menu = "1. Add a car\n" + "2. Accept/Reject Offer\n" + "3. Remove a car\n" + "4. View All Payments\n";
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

		Scanner sc = new Scanner(System.in);

		System.out.println("Input '1' to Log In or '2' to Register");
		String access = sc.nextLine();
		if (access.matches("^[1-2]$")) {
			switch (access) {
			case "1":
				System.out.println("Hello, Please Input Your Credentials");
				System.out.println("Username: ");
				String username = sc.nextLine();
				System.out.println("Password: ");
				String password = sc.nextLine();

				// Set User ID and Role in the User class
				User user = UserService.logging(username, password);

				try {
					do {
						// Role 1 = Costumer
						if (user.getRole() == 1) {
							System.out.println("\n--- Costumer Menu ---");
							System.out.println("PLEASE USE NUMBERS TO NAVIGATE THE MENU");
							System.out.println(c_menu);
							// Scanner input to user
							String c_input = sc.nextLine();

							if (c_input.matches("^[1-5]$")) {
								switch (c_input) {

								// View all the cars
								case "1":
									for (Car a : CarService.getAllCars())
										System.out.println(a);
									break;

								// Make an offer
								case "2":
									Offer offer = new Offer();

									System.out.println("Please select your car you like by the id:");
									int c_id = Integer.parseInt(sc.nextLine());
									System.out.println("Please enter the amount you want to place: ");
									int amount = Integer.parseInt(sc.nextLine());

									offer.setCar_id(c_id);
									offer.setUser_id(user.getId());
									offer.setAmount(amount);

									if (OfferService.addOffer(offer))
										System.out.println("The offer has been placed!");
									break;

								// View the cars that you own
								case "3":
									System.out.println("You own the following vehicles: ");
									for (Car c : CarService.getMyCars(user.getId())) {
										MyLogger.logger.warn("The geetMyCars relationship needs fixing");
										System.out.println(c);
									}

									break;

								// View remaining payments
								case "4":
									try {	
										for (Offer i : OfferService.getPayments(user.getId()))
											System.out.println(i);
									} catch (Exception e) {
										System.out.println("Please get a car first");
									}
									break;

								// Monthly Payments
								case "5":
									
									double payment = (ThreadLocalRandom.current().nextInt(20000, 30000 + 1000) / 12) * 0.12;
									System.out.println(payment);
									
									break;
								}

							}

							else {
								System.out.println("Please, only use the numbers in the menu");
							}
						}
						// Role 2 = Employee
						if (user.getRole() == 2) {
							System.out.println("\n--- Employee Menu ---");
							System.out.println("PLEASE USE NUMBERS TO NAVIGATE THE MENU");
							System.out.println(e_menu);
							String c_input = sc.nextLine();
							if (c_input.matches("^[1-4]$")) {
								switch (c_input) {
								// Add a car
								case "1":
									Car c = new Car();
									System.out.println("Name: ");
									c.setName(sc.nextLine());
									System.out.println("Color: ");
									c.setColor(sc.nextLine());
									System.out.println("Description: ");
									c.setDescription(sc.nextLine());
									CarService.addCar(c);
									System.out.println("The car has been added");
									break;

								// Accept offer, by default reject the others
								case "2":
									System.out.println("These are the current offers");
									OfferService os = new OfferService();
									for (Offer o : os.getAllCurrentOffers())
										System.out.println(o);
									System.out.println("To accept an offer input the following:");
									System.out.println("ORDER ID: ");
									int o_id = Integer.parseInt(sc.nextLine());
									System.out.println("Car ID: ");
									int c_id = Integer.parseInt(sc.nextLine());
									if (OfferService.acceptOffer(o_id, c_id))
										System.out.println(
												"The offer has been accepted and the others have been rejected\n\n");
									break;

								// Remove a car
								case "3":
									System.out.println("These are the current cars in the lot");
									for (Car lot : CarService.getAllCars())
										System.out.println(lot);
									System.out.println("Enter the Car ID to delete it from the system");
									int id = Integer.parseInt(sc.nextLine());
									CarService.deleteCar(id);
									System.out.println("The car has been deleted!");
									break;

								// View All Payments
								case "4":
									OfferService accepted = new OfferService();
									System.out.println("These are all the payments");
									for (Offer o : accepted.getAllAcceptedOffers())
										System.out.println(o);
									break;
								}
							}

							else {
								System.out.println("Please, only use the numbers in the menu");
							}

						}
					} while (true);

				} catch (NullPointerException e) {
					System.out.println("Wrong Username or Password");
				} finally {
					System.out.println("Would you like to register?");

				}

			case "2":
				/*
				 * User Registration
				 */
				System.out.println("Register for an account");
				System.out.println("Enter a Username: ");
				String new_user = sc.next();
				System.out.println("Enter a Username: ");
				String new_password = sc.next();

				User newUser = new User();
				newUser.setUsername(new_user);
				newUser.setPassword(new_password);
				if (UserService.addUser(newUser)) {
					System.out.println("Registration Complete!");
				}
				/*
				 * User Registration Complete
				 */
			}

		} else {
			System.out.println("Wrong Input");
		}

	}
}
