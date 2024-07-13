package Classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONReader {

    static final String filePath = "src/data/contacts.json";
    static final String key = "contacts";
    static Gson gson = new Gson();

    public static List<Contact> GetContacts() {

        List<Contact> contacts = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filePath);
            Type contactListType = new TypeToken<Map<String, List<Contact>>>() {
            }.getType();

            Map<String, List<Contact>> contactMap = gson.fromJson(fr, contactListType);
            contacts = contactMap.get(key);
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.err.println("No se ha encontrado el archivo " + e);
        }

        return contacts;
    }

    public static void CreateNewContact(String id, String name, String surname, String phoneNumber, String address) {
        try {
            // Read existing contacts
            FileReader fr = new FileReader(filePath);
            Type contactListType = new TypeToken<Map<String, List<Contact>>>() {
            }.getType();

            Map<String, List<Contact>> contactMap = gson.fromJson(fr, contactListType);
            fr.close();

            List<Contact> contacts = contactMap.get(key);

            // create contact instance and add it to list
            Contact newContact = new Contact(id, name, surname, phoneNumber, address);
            contacts.add(newContact);

            // save the list to JSON
            FileWriter fw = new FileWriter(filePath);
            gson.toJson(contactMap, fw);
            fw.close();
            System.out.println("Se creó el contacto");
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error guardando el contacto" + e);
        }
    }

    public static void SaveContacts(List<Contact> contacts){
        try {
            Map<String, List<Contact>> contactMap = Map.of(key, contacts);
            FileWriter fw = new FileWriter(filePath);
            gson.toJson(contactMap, fw);
            fw.close();

        } catch (Exception e) {
            System.err.println("No se ha podido guardar el contacto " + e);
        }
    }
}
