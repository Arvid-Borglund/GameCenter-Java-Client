package se.lu.student.borglund;
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
import java.util.Arrays;

import org.tempuri.CronusWebService.*;

public class MyGuiCronus extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573633286925623585L;
	private JTextArea textAreaOutput;
	private JTextField textFieldInput;
	private JButton buttonSubmit;
	CronusWebServiceSoap proxy = new CronusWebServiceSoapProxy();

	public MyGuiCronus() {
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
	
	public void runCronusApp() {
	    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            displayOutput("Welcome to the Cronus CRUD employee application!\n\n");

	            boolean quit = false;

	            while (!quit) {
	                displayOutput("Select an option\n");
	                displayOutput("1. Create an employee\n");
	                displayOutput("2. Read an employee\n");
	                displayOutput("3. Update an employee\n");
	                displayOutput("4. Delete an employee\n");
	                displayOutput("5. Get the primary key constraints\n");
	                displayOutput("6. Get all the column names\n");
	                displayOutput("7. Get the total number of tables\n");
	                displayOutput("8. Get the total number of columns\n");
	                displayOutput("9. Quit\n\n");

	                String option = waitForInput();

	                boolean isOptionValid = (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5") || option.equals("6") || option.equals("7") || option.equals("8") || option.equals("9"));

	                if (isOptionValid) {

	                    switch (option) {
	                    
	                    case "1":
	                        displayOutput("Enter the employee's ID\n");
	                        String empId = waitForInput();
	                        displayOutput("Enter the employee's first name\n");
	                        String firstName = waitForInput();
	                        displayOutput("Enter the employee's last name\n");
	                        String lastName = waitForInput();
	                        displayOutput("Enter the employee's city\n");
	                        String city = waitForInput();
	                        displayOutput("Enter the employee's phone number\n");
	                        String phoneNumber = waitForInput();
	                        displayOutput("Enter the employee's job title\n");
	                        String jobTitle = waitForInput();
	                        
	                     // Initialize ArrayList instead of Arrays
	                        List<MyKeyValuePairCronus> employeeData = new ArrayList<>();

	                        employeeData.add(new MyKeyValuePairCronus("EmpId", empId));
	                        employeeData.add(new MyKeyValuePairCronus("FirstName", firstName));
	                        employeeData.add(new MyKeyValuePairCronus("LastName", lastName));
	                        employeeData.add(new MyKeyValuePairCronus("JobTitle", jobTitle));
	                        employeeData.add(new MyKeyValuePairCronus("City", city));
	                        employeeData.add(new MyKeyValuePairCronus("PhoneNo", phoneNumber));

	                        KeyValuePairCronus[] employeeDataArray = employeeData.stream()
	                                .map(MyKeyValuePairCronus::toKeyValuePair)
	                                .toArray(KeyValuePairCronus[]::new);
	                        
	                        	displayOutput("Calling createEmployee...\n");
	                            String createResult = proxy.createEmployee(employeeDataArray);
	                            displayOutput("createEmployee call finished.\n");
	                            displayOutput("\n" + createResult + "\n\n");
	                            displayOutput(proxy.getServerError());
	                            
	                        break;
	                        	                        	                        	                        	                        	                     	                        
	                    case "2":
	                        displayOutput("Enter the ID of the employee you want to read\n");
	                        String readEmpId = waitForInput();
	                        String readResult = proxy.getEmployeeById(readEmpId);
	                        displayOutput(readResult + "\n\n");
	                        displayOutput(proxy.getServerError());
	                        break;

	                    case "3":
	                        displayOutput("Enter the ID of the employee you want to update\n");
	                        String updateEmpId = waitForInput();
	                        displayOutput("Enter the (updated) employee first name\n");
	                        String updateFirstName = waitForInput();
	                        displayOutput("Enter the (updated) employee last name\n");
	                        String updateLastName = waitForInput();
	                        displayOutput("Enter the (updated) employee city\n");
	                        String updateCity = waitForInput();
	                        displayOutput("Enter the (updated) employee phone number\n");
	                        String updatePhoneNumber = waitForInput();
	                        displayOutput("Enter the (updated) employee job title\n");
	                        String updateJobTitle = waitForInput();
	                        List<MyKeyValuePairCronus> updateCustomerData = Arrays.asList(
	                                new MyKeyValuePairCronus("EmpId", updateEmpId),
	                                new MyKeyValuePairCronus("FirstName", updateFirstName),
	                                new MyKeyValuePairCronus("LastName", updateLastName),
	                                new MyKeyValuePairCronus("JobTitle", updateJobTitle),
	                                new MyKeyValuePairCronus("City", updateCity),
	                                new MyKeyValuePairCronus("PhoneNo", updatePhoneNumber)
	                        );
	                        if (proxy.getEmployeeById(updateEmpId).equals("Employee not found.")) {
	                            displayOutput("Employee does not exist\n\n");
	                        } else {
	                            KeyValuePairCronus[] updateArray = updateCustomerData.stream()
	                                    .map(MyKeyValuePairCronus::toKeyValuePair)
	                                    .toArray(KeyValuePairCronus[]::new);
	                            String updateResult = proxy.updateEmployee(updateArray);
	                            displayOutput("\n" + updateResult + "\n\n");
	                            displayOutput(proxy.getServerError());
	                        }
	                        break;

	                    case "4":
	                        displayOutput("Enter the ID of the employee you want to delete\n");
	                        String deleteEmpId = waitForInput();
	                        String deleteResult = proxy.deleteEmployee(deleteEmpId);
	                        displayOutput("\n" + deleteResult + "\n\n");
	                        displayOutput(proxy.getServerError());
	                        break;


	                    case "5":
	                        displayOutput("Primary key constraints\n");
	                        String[] primaryKeyConstraints = proxy.getPrimaryKeyConstraintNames();
	                        StringBuilder primaryKeyResult = new StringBuilder();
	                        for (String primaryKeyConstraint : primaryKeyConstraints) {
	                            primaryKeyResult.append(primaryKeyConstraint).append("\n");
	                        }
	                        if (!primaryKeyResult.toString().isEmpty()) {
	                            displayOutput(primaryKeyResult.toString() + "\n");
	                        } else {
	                            displayOutput("No primary key constraints\n");
	                        }
	                        displayOutput("\n");
	                        displayOutput(proxy.getServerError());
	                        break;

	                    case "6":
	                        displayOutput("All the column names\n");
	                        String[] tableNames = proxy.getAllColumnNames();
	                        StringBuilder result = new StringBuilder();
	                        for (String tableName : tableNames) {
	                            result.append(tableName).append("\n");
	                        }
	                        displayOutput(result.toString() + "\n");
	                        displayOutput("\n");
	                        displayOutput(proxy.getServerError());
	                        break;

	                    case "7":
	                        displayOutput("Total number of tables\n");
	                        String totalTablesResult = proxy.getTotalTables();
	                        displayOutput(totalTablesResult + "\n");
	                        displayOutput("\n");
	                        displayOutput(proxy.getServerError());
	                        break;

	                    case "8":
	                        displayOutput("Total number of columns\n");
	                        String totalColumnsResult = proxy.getTotalColumns();
	                        displayOutput(totalColumnsResult + "\n");
	                        displayOutput("\n");
	                        displayOutput(proxy.getServerError());
	                        break;

	                    case "9":
	                    	System.exit(0);
	                        break;
	                    }

	                } else {
	                    displayOutput("Invalid option, try again\n");
	                }
	            }
	            return null;
	        }
	    };
	    worker.execute();
	}
		
			
			
}
