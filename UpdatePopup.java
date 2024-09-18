import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePopup extends JFrame {

    UpdatePopup(int indexOfContact) {
        setTitle("Update Contact");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Contact contact = ContactManager.getContactObject(indexOfContact);

        JLabel newName = new JLabel("Name: ");
        JTextField newNameInput = new JTextField(20);

        JLabel newPhoneNumber = new JLabel("Contact Number: ");
        JTextField newPhoneNumberInput = new JTextField(20);

        JLabel newCompanyName = new JLabel("Company: ");
        JTextField newCompanyNameInput = new JTextField(20);

        JLabel newSalary = new JLabel("Salary: ");
        JTextField newSalaryInput = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(newName, gbc);

        gbc.gridx = 1;
        panel.add(newNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(newPhoneNumber, gbc);

        gbc.gridx = 1;
        panel.add(newPhoneNumberInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(newCompanyName, gbc);

        gbc.gridx = 1;
        panel.add(newCompanyNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(newSalary, gbc);

        gbc.gridx = 1;
        panel.add(newSalaryInput, gbc);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(100, 30));
        buttonsPanel.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        buttonsPanel.add(cancelButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newNameInput.getText();
                String company = newCompanyNameInput.getText();
                String phoneNumber = newPhoneNumberInput.getText();
                String salary = newSalaryInput.getText();

                StringBuilder errorMessage = new StringBuilder();

                if (!phoneNumber.isEmpty()) {
                    if (!Validator.phoneNumberValidator(phoneNumber)) {
                        errorMessage.append("Invalid phone number. Please enter a valid number.\n");
                        newPhoneNumberInput.setText("");
                    }
                }

                if (!salary.isEmpty()) {
                    if (!Validator.salaryValidator(salary)) {
                        errorMessage.append("Invalid salary. Please enter a valid number.\n");
                        newSalaryInput.setText("");
                    }
                }

                if (errorMessage.length() > 0) {
                    showPopupMessage(errorMessage.toString());
                } else {
                   
                    if (!name.isEmpty()) {
                        contact.setName(name);
                    }

                    if (!company.isEmpty()) {
                        contact.setCompanyName(company);
                    }

                    if (!phoneNumber.isEmpty()) {
                        contact.setPhoneNumber(phoneNumber);
                    }

                    if (!salary.isEmpty()) {
                        contact.setSalary(salary);
                    }

                    showPopupMessage("Contact Updated Successfully.");
                    dispose(); 
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the dialog on screen
        setVisible(true);
    }

    private static void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdatePopup(1));
    }
}


