package se.lu.student.arvid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.ArrayList;

import org.tempuri.*;

public class MyGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaOutput;
	private JTextField textFieldInput;
	private JButton buttonSubmit;
	WebServiceGameCenterSoap proxy = new WebServiceGameCenterSoapProxy();

	public MyGUI() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout());

		// create the output area and add it to a scroll pane
		textAreaOutput = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaOutput);
		scrollPane.setPreferredSize(new Dimension(1100, 550));
		add(scrollPane, BorderLayout.NORTH);

		// create the input field and add it to a panel
		textFieldInput = new JTextField();
		textFieldInput.setPreferredSize(new Dimension(200, 50));
		JPanel inputPanel = new JPanel();
		inputPanel.add(textFieldInput);
		add(inputPanel, BorderLayout.CENTER);

		// create the submit button and add it to a panel
		buttonSubmit = new JButton("Submit");
		buttonSubmit.setPreferredSize(new Dimension(200, 50));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(buttonSubmit);
		add(buttonPanel, BorderLayout.SOUTH);

		// set up the click event handler for the submit button
		buttonSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textFieldInput.getText();
				if (!input.isEmpty()) {
					CompletableFuture.runAsync(() -> {
						synchronized (buttonSubmit) {
							buttonSubmit.setEnabled(false);
							buttonSubmit.notifyAll();
						}
					});
				}
			}
		});

		textFieldInput.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buttonSubmit.doClick();
				}
			}
		});
	}

	/**
	 * Method to display text in the output area.
	 */
	public void displayOutput(String text) {
		textAreaOutput.append(text);
		textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength()); // Scroll to bottom
	}

	/**
	 * Method to clear the input field.
	 */
	public void clearInput() {
		textFieldInput.setText("");
	}

	/**
	 * Method to wait for user input from the input field.
	 */
	
	public String waitForInput() throws InterruptedException {
		synchronized (buttonSubmit) {
			while (textFieldInput.getText().isEmpty()) {
				buttonSubmit.wait();
			}
			String input = textFieldInput.getText().trim();
			clearInput();
			textFieldInput.requestFocus();
			buttonSubmit.setEnabled(true);
			return input;
		}
	}
	
	public void runGameCenter() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				String serverSideException = "";
				boolean quit = false;
				while (!quit) {
					textFieldInput.requestFocus();
					displayOutput(
							"\n\n\n\n\n                                                     * * *      ******* \n");
					displayOutput(
							"                                                   * ^^    * ********          *****\n");
					displayOutput("                                                  *O           *********  ******\n");
					displayOutput(" !!Welcome to Game Center!!    >---                    ** \n");
					displayOutput(
							"                                                   * * * ***     *******   *******\n");
					displayOutput(
							"                                                           ** * *         ******\n\n\n");
					displayOutput("Enter your login ID:\n");
					String id = waitForInput();
					displayOutput("Enter your password:\n");
					String loginPassword = waitForInput();
					displayOutput("\n");

					boolean validSelection = false;
					String selectedEntityCon = "";
//					proxy.tryErrorHandling();
					String role = proxy.verifyCredentialsJava(id, loginPassword);
					displayOutput(serverSideException = proxy.getErrorMessage() + "\n");

					if (role != null && (role.equals("Admin") || role.equals("Employee") || role.equals("Customer")))

					{
						if (!role.equals("Admin") && !role.equals("Employee")) {
							displayOutput("Only employees may use this application!\n\n");
							continue;
						}

						displayOutput("Password and username is correct, these are your table options:\n");
						displayOutput("Employee, Customer, Computer, Game, Login\n\n");

						while (!validSelection) {
							displayOutput("Enter the table you want to access:\n");
							selectedEntityCon = waitForInput();
							displayOutput("\n");

							if (selectedEntityCon.equals("Employee") || selectedEntityCon.equals("Customer")
									|| selectedEntityCon.equals("Computer") || selectedEntityCon.equals("Game")
									|| selectedEntityCon.equals("Login")) {
								validSelection = true;
							} else {
								displayOutput("There is no such table. Try again.\n");
							}
						}

						String result = proxy.getSelectedEntityData(selectedEntityCon, role);
						displayOutput(result + "\n");
						displayOutput(serverSideException = proxy.getErrorMessage() + "\n");

						boolean success = false;

						while (!success) {
							displayOutput("\n");
							displayOutput("\nWhat would you like to do next?\n");
							displayOutput("\nCreate, Delete, View table, logout\n");
							String nextAction = waitForInput();

							if (nextAction.toLowerCase().equals("create")) {

								if (selectedEntityCon.equals("Computer")) {
									displayOutput("\nEnter the values for the new computer:\n");
									displayOutput("\nComputerId:\n");

									String computerId = waitForInput();
									displayOutput("\nCpu:\n");
									String cpu = waitForInput();
									displayOutput("\nGpu:\n");
									String gpu = waitForInput();
									displayOutput("\nRam:\n");
									String ram = waitForInput();
									displayOutput("\nDataStorage:\n");
									String dataStorage = waitForInput();
									displayOutput("\n");

									List<MyKeyValuePairCustom> computerData = new ArrayList<MyKeyValuePairCustom>();
									computerData.add(new MyKeyValuePairCustom("ComputerId", computerId));
									computerData.add(new MyKeyValuePairCustom("Cpu", cpu));
									computerData.add(new MyKeyValuePairCustom("Gpu", gpu));
									computerData.add(new MyKeyValuePairCustom("Ram", ram));
									computerData.add(new MyKeyValuePairCustom("DataStorage", dataStorage));

									if (proxy.create(computerData.stream()
											.map(x -> new KeyValuePairCustom(x.getKey(), x.getValue()))
											.toArray(KeyValuePairCustom[]::new), selectedEntityCon, role)) {
										displayOutput("\nComputer successfully created.\n");
									} else {
										displayOutput("\nFailed to create computer.\n");										
										displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
									}
								}

								if (selectedEntityCon.equals("Employee")) {
									displayOutput("\nEnter the values for the new employee:\n\n");
									displayOutput("EmployeeId:\n");
									String employeeId = waitForInput();
									displayOutput("\nName:\n");
									String name = waitForInput();
									displayOutput("\nAddress:\n");
									String address = waitForInput();
									displayOutput("\nPhoneNumber:\n");
									String phoneNumber = waitForInput();
									displayOutput("\nEmail:\n");
									String email = waitForInput();
									displayOutput("\nJobTitle:\n");
									String jobTitle = waitForInput();
									displayOutput("\n");

									List<MyKeyValuePairCustom> employeeData = new ArrayList<MyKeyValuePairCustom>();
									employeeData.add(new MyKeyValuePairCustom("EmployeeId", employeeId));
									employeeData.add(new MyKeyValuePairCustom("Name", name));
									employeeData.add(new MyKeyValuePairCustom("Address", address));
									employeeData.add(new MyKeyValuePairCustom("PhoneNumber", phoneNumber));
									employeeData.add(new MyKeyValuePairCustom("Email", email));
									employeeData.add(new MyKeyValuePairCustom("JobTitle", jobTitle));

									if (proxy.create(employeeData.stream()
											.map(x -> new KeyValuePairCustom(x.getKey(), x.getValue()))
											.toArray(KeyValuePairCustom[]::new), selectedEntityCon, role)) {
										displayOutput("\nEmployee created successfully!\n");
									} else {
										displayOutput("\nFailed to create employee.\n");
										displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
									}
								}

								if (selectedEntityCon.equals("Customer")) {
									displayOutput("\nEnter the values for the new customer:\n\n");
									displayOutput("CustomerId:\n");
									String customerId = waitForInput();
									displayOutput("\nName:\n");
									String name = waitForInput();
									displayOutput("\nAddress:\n");
									String address = waitForInput();
									displayOutput("\nPhoneNumber:\n");
									String phoneNumber = waitForInput();
									displayOutput("\nEmail:\n");
									String email = waitForInput();
									displayOutput("\nLoyaltyLevel:\n");
									String loyaltyLevel = waitForInput();
									displayOutput("\n");

									List<MyKeyValuePairCustom> customerData = new ArrayList<MyKeyValuePairCustom>();
									customerData.add(new MyKeyValuePairCustom("CustomerId", customerId));
									customerData.add(new MyKeyValuePairCustom("Name", name));
									customerData.add(new MyKeyValuePairCustom("Address", address));
									customerData.add(new MyKeyValuePairCustom("PhoneNumber", phoneNumber));
									customerData.add(new MyKeyValuePairCustom("Email", email));
									customerData.add(new MyKeyValuePairCustom("LoyaltyLevel", loyaltyLevel));

									if (proxy.create(customerData.stream()
											.map(x -> new KeyValuePairCustom(x.getKey(), x.getValue()))
											.toArray(KeyValuePairCustom[]::new), selectedEntityCon, role)) {
										displayOutput("\nCustomer created successfully!\n");
									} else {
										displayOutput("\nFailed to create customer.\n");
										displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
									}
								}
								if (selectedEntityCon.equals("Game")) {
									displayOutput("\nEnter the values for the new game:\n\n");
									displayOutput("GameId:\n");
									String gameId = waitForInput();
									displayOutput("\nComputerId:\n");
									String computerId = waitForInput();
									displayOutput("\nTitle:\n");
									String title = waitForInput();
									displayOutput("\nGenre:\n");
									String genre = waitForInput();
									displayOutput("\n");

									List<MyKeyValuePairCustom> gameData = new ArrayList<MyKeyValuePairCustom>();
									gameData.add(new MyKeyValuePairCustom("GameId", gameId));
									gameData.add(new MyKeyValuePairCustom("ComputerId", computerId));
									gameData.add(new MyKeyValuePairCustom("Title", title));
									gameData.add(new MyKeyValuePairCustom("Genre", genre));

									if (proxy.create(
											gameData.stream().map(x -> new KeyValuePairCustom(x.getKey(), x.getValue()))
													.toArray(KeyValuePairCustom[]::new),
											selectedEntityCon, role)) {
										displayOutput("\nGame created successfully!\n");
									} else {
										displayOutput("\nFailed to create game.\n");
										displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
									}
								}
								if (selectedEntityCon.equals("Login")) {
									displayOutput("\nEnter the values for the new login:\n\n");
									displayOutput("LoginId:\n");
									String loginId = waitForInput();
									displayOutput("\nPassword:\n");
									String password = waitForInput();
									displayOutput("\nCustomerId:\n");
									String customerId = waitForInput();
									displayOutput("\nEmployeeId:\n");
									String employeeId = waitForInput();
									displayOutput("\nAccessLevel:\n");
									String accessLevel = waitForInput();
									displayOutput("\n");

									List<MyKeyValuePairCustom> loginData = new ArrayList<MyKeyValuePairCustom>();
									loginData.add(new MyKeyValuePairCustom("LoginId", loginId));
									loginData.add(new MyKeyValuePairCustom("Password", password));
									loginData.add(new MyKeyValuePairCustom("CustomerId", customerId));
									loginData.add(new MyKeyValuePairCustom("EmployeeId", employeeId));
									loginData.add(new MyKeyValuePairCustom("AccessLevel", accessLevel));

									if (proxy.create(loginData.stream()
											.map(x -> new KeyValuePairCustom(x.getKey(), x.getValue()))
											.toArray(KeyValuePairCustom[]::new), selectedEntityCon, role)) {
										displayOutput("\nLogin created successfully!\n");
									} else {
										displayOutput(
												"\nFailed to create login. (invalid values or no admin rights)\n");
										displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
									}
								}
							}
							else if (nextAction.toLowerCase().equals("delete")) {
								displayOutput("\nEnter the ID of the entity you want to delete:\n");
								String idDelete = waitForInput();
								displayOutput("\n");

								if (proxy.delete(idDelete, selectedEntityCon, role)) {
									displayOutput("Delete successful!\n");
								} else {
									displayOutput("Delete failed. Try again.\n");
									displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
								}
							}
							else if (nextAction.toLowerCase().equals("view table")) {
								while (true) {
									displayOutput("\nEnter the table you want to view of the following options:\n");
									displayOutput("Employee, Customer, Computer, Game, Login\n\n");
									selectedEntityCon = waitForInput();
									displayOutput("\n");
									if (selectedEntityCon.equals("Employee") || selectedEntityCon.equals("Customer")
											|| selectedEntityCon.equals("Computer") || selectedEntityCon.equals("Game")
											|| selectedEntityCon.equals("Login")) {
										result = proxy.getSelectedEntityData(selectedEntityCon, role);
										if (!result.isEmpty()) {
											textAreaOutput.setText("");
											displayOutput(result);
											break;
										} else {
											displayOutput("View failed. Please try again.\n\n");
											displayOutput(serverSideException = proxy.getErrorMessage() + "\n");
										}
									} else {
										displayOutput(
												"Invalid selection. Please enter a valid table name and try again.\n\n");										
									}
								}
							}

							else if (nextAction.toLowerCase().equals("logout")) {
								textAreaOutput.setText("");
								success = true;
							}
						}
					}
					else {
						displayOutput("Password or username is incorrect.\n");
						displayOutput("Do you want to try again or quit?\n");
						String quitter = waitForInput();

						if (quitter.equals("quit")) {
							System.exit(0);
						}
					}
				}

				return null;

			}
		};
		worker.execute();

	}
}
