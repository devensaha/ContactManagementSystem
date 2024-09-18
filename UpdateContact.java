import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateContact extends MyFrame {
    private JTextField searchField;
    private JLabel contactIdLabel, nameLabel, contactNumberLabel, companyLabel, salaryLabel, birthdayLabel;
    private JButton updateButton, clearButton, backButton;
    private int indexOfContact = -1;

    public UpdateContact() {
        setTitle("UPDATE CONTACT");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("UPDATE CONTACT", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(Color.CYAN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(header, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(searchField, gbc);
        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        contactIdLabel = createLabel("Contact ID");
        nameLabel = createLabel("Name");
        contactNumberLabel = createLabel("Contact Number");
        companyLabel = createLabel("Company");
        salaryLabel = createLabel("Salary");
        birthdayLabel = createLabel("Birthday");

        addFormField(panel, gbc, contactIdLabel, 2);
        addFormField(panel, gbc, nameLabel, 3);
        addFormField(panel, gbc, contactNumberLabel, 4);
        addFormField(panel, gbc, companyLabel, 5);
        addFormField(panel, gbc, salaryLabel, 6);
        addFormField(panel, gbc, birthdayLabel, 7);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        updateButton = new JButton("Update Contact");
        buttonPanel.add(updateButton);
        updateButton.setEnabled(false);

        clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);

        backButton = new JButton("Back To HomePge");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        gbc.gridy = 9;
        panel.add(backButton, gbc);

        add(panel);
        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                indexOfContact = ContactManager.searchContactIndex(search);

                if (indexOfContact == -1) {
                    showPopupMessage("No contact found for " + search + "...");
                    updateButton.setEnabled(false);
                    searchField.setText("");
                    clearFields();

                } else {
                    updateButton.setEnabled(true);
                    ContactManager.displayContact(indexOfContact, contactIdLabel, nameLabel, contactNumberLabel,
                            companyLabel, salaryLabel, birthdayLabel);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indexOfContact != -1) {
                    new UpdatePopup(indexOfContact);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen().setVisible(true);
                dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButton.setEnabled(false);
                searchField.setText("");
                clearFields();
            }
        });
    }

    private static void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void clearFields() {
        contactIdLabel.setText("Contact ID: ");
        nameLabel.setText("Name: ");
        contactNumberLabel.setText("Contact Number: ");
        companyLabel.setText("Company: ");
        salaryLabel.setText("Salary: ");
        birthdayLabel.setText("Birthday: ");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, JLabel label, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(label, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteContact::new);
    }
}