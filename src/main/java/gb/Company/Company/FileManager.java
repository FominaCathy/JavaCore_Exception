package gb.Company.Company;

import java.io.*;

public class FileManager {

    private final Service sevice;

    public FileManager(Service service) {
        this.sevice = service;
    }

    public void importBD(String nameFile){
        try {
            FileOutputStream fileOutput = new FileOutputStream(nameFile);
            ObjectOutputStream exportEmployee = new ObjectOutputStream(fileOutput);
            exportEmployee.writeObject(this.sevice);


        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void exportBD(String nameFile){
//        try {
//            FileInputStream fileInput = new FileInputStream(nameFile);
//            ObjectInputStream exportEmployee = new ObjectInputStream(fileInput);
//            this.service = (Service) exportEmployee.readObject();
//
//
//        }catch (IOException e){
//            System.out.println(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }
}
