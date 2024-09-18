import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SortContact extends MyFrame {

    public SortContact() {
        setTitle("Contacts List");

        JPanel mainPanel = createMainPanel();
        mainPanel.setBackground(Color.WHITE);

        add(mainPanel);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = createGridBagConstraints();

        JLabel titleLabel = createTitleLabel();
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        JButton listByNameButton = createButton("List by Name", e -> navigateToListContactsBy("NAME"));
        JButton listBySalaryButton = createButton("List by Salary", e -> navigateToListContactsBy("SALARY"));
        JButton listByBirthdayButton = createButton("List by Birthday", e -> navigateToListContactsBy("BIRTHDAY"));
        JButton backButton = createButton("Back To Homepage", e -> navigateToHomeScreen());

        gbc.gridy = 1;
        mainPanel.add(listByNameButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(listBySalaryButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(listByBirthdayButton, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1;
        mainPanel.add(Box.createVerticalGlue(), gbc);

        gbc.gridy = 5;
        gbc.weighty = 0;
        mainPanel.add(backButton, gbc);

        return mainPanel;
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("CONTACTS LIST");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        return titleLabel;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void navigateToHomeScreen() {
        new HomeScreen().setVisible(true);
        dispose();
    }

    private void navigateToListContactsBy(String sortBy) {
        new ListContactsBy(sortBy).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortContact::new);
    }
}