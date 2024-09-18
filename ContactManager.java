import java.util.*;
import javax.swing.JLabel;

public class ContactManager {
    private static final ArrayList<Contact> contactArrayList = new ArrayList<>();

    public static void addContact(String contactId, String name, String phoneNumber, String company, String salary,
            String birthday) {
        Contact contact = new Contact(contactId, name, phoneNumber, company, salary, birthday);
        contactArrayList.add(contact);
    }

    public static void removeContact(int index) {
        if (index >= 0 && index < contactArrayList.size()) {
            contactArrayList.remove(index);
        }
    }

    public static ArrayList<Contact> getContactList() {
        return new ArrayList<>(contactArrayList);
    }

   
    public static Contact getContactObject(int index) {
        if (index >= 0 && index < contactArrayList.size()) {
            return contactArrayList.get(index);
        }
        return null;
    }

    
    public static int searchContactIndex(String search) {
        int index = getContactIndexByName(search);
        return (index != -1) ? index : getContactIndexByPhoneNumber(search);
    }

   
    public static void sortByName() {
        contactArrayList.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
    }


    public static void sortBySalary() {
        contactArrayList.sort(Comparator.comparing(contact -> Integer.parseInt(contact.getSalary())));
    }


    public static void sortByBirthday() {
        contactArrayList.sort(Comparator.comparing(Contact::getBirthday));
    }

   
    public static int getContactIndexByName(String name) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getContactIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

   
    public static void displayContact(int index, JLabel contactIdLabel, JLabel nameLabel, JLabel contactNumberLabel,
            JLabel companyLabel, JLabel salaryLabel, JLabel birthdayLabel) {
        if (index >= 0 && index < contactArrayList.size()) {
            Contact contact = contactArrayList.get(index);
            contactIdLabel.setText("Contact ID: " + contact.getId());
            nameLabel.setText("Name: " + contact.getName());
            contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
            companyLabel.setText("Company: " + contact.getCompanyName());
            salaryLabel.setText("Salary: " + contact.getSalary());
            birthdayLabel.setText("Birthday: " + contact.getBirthday());
        }
    }
}