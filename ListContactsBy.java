import javax.swing.*;
import java.awt.*;

public class ListContactsBy extends MyFrame {

    public ListContactsBy(String sortby) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titleLabel = new JLabel("LIST CONTACTS BY " + sortby);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        switch (sortby) {
            case "SALARY" -> ContactManager.sortBySalary();
            case "NAME" -> ContactManager.sortByName();
            case "BIRTHDAY" -> ContactManager.sortByBirthday();
        }

        String[] columnNames = { "Contact ID", "Name", "Contact Number", "Company", "Salary", "Birthday" };
        Object[][] data = new Object[ContactManager.getContactList().size()][6];

        int i = 0;
        for (Contact contact : ContactManager.getContactList()) {
            data[i][0] = contact.getId();
            data[i][1] = contact.getName();
            data[i][2] = contact.getPhoneNumber();
            data[i][3] = contact.getCompanyName();
            data[i][4] = contact.getSalary();
            data[i][5] = contact.getBirthday();
            i++;
        }

        JTable contactsTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(contactsTable);

        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(tableScrollPane, gbc);

        JButton backButton = new JButton("Go Back");
        JButton backToHomeButton = new JButton("Back To Home");

        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(backToHomeButton, gbc);

        add(mainPanel);

        backButton.addActionListener(e -> {
            new SortContact().setVisible(true);
            dispose();
        });

        backToHomeButton.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListContactsBy(""));
    }
}