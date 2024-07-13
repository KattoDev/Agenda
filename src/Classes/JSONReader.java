package Classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;



public class JSONReader {

    static final String filePath = "src/data/contacts.json";
    static final String key = "contacts";
    static Gson gson = new Gson();

    public static void PrintContacts() {
        try {
            FileReader fr = new FileReader(filePath);
            Type contactListType = new TypeToken<Map<String, List<Contact>>>() {}.getType();

            Map<String, List<Contact>> contactMap = gson.fromJson(fr, contactListType);
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Contact> contacts = contactMap.get(key);
            for (Contact contact : contacts) {
                System.out.println(contact);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void SaveContacts() {
        try {
            // Read existing contacts
            FileReader fr = new FileReader(filePath);
            Type contactListType = new TypeToken<Map<String, List<Contact>>>() {}.getType();

            Map<String, List<Contact>> contactMap = gson.fromJson(fr, contactListType);
            fr.close();

            List<Contact> contacts = contactMap.get(key);

            // contact info
            String id = "id3";
            String name = "name3";
            String surname = "surname3";
            String phoneNumber = "PN3";
            String address = "adress3";

            // create contact instance and add it to list
            Contact newContact = new Contact(id, name, surname, phoneNumber, address);
            contacts.add(newContact);

            // save the list to JSON
            FileWriter fw = new FileWriter(filePath);
            gson.toJson(contactMap, fw);
            fw.close();

            System.out.println("Se creó el contacto");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        SaveContacts();
        PrintContacts();
    }
}
