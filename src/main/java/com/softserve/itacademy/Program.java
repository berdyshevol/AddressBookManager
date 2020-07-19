package com.softserve.itacademy;

public class Program {
    public static void main( String[] args ) {
        AddressBook addressBook = AddressBook.getInstance();

        addressBook.create("John", "Brown", "Address #1");
        addressBook.create("Susan", "Brown", "Address #4");
        addressBook.create("Karen", "Davis", "Address #2");
        addressBook.create("John", "Taylor", "Address #1");

        for (Object record : addressBook) {
            System.out.println(record);
        }

        addressBook.update("Karen", "Davis", "New Address");

        String address = addressBook.read("Karen", "Davis");
        System.out.println("========================================================");
        System.out.println(address);

        addressBook.sortedBy(SortOrder.ASC);
        System.out.println("========================================================");
        for (Object record : addressBook) {
            System.out.println(record);
        }

        addressBook.sortedBy(SortOrder.DESC);
        System.out.println("========================================================");
        for (Object record : addressBook) {
            System.out.println(record);
        }

        addressBook.delete("Susan", "Brown");
        addressBook.delete("John", "Taylor");
        System.out.println("========================================================");
        for (Object record : addressBook) {
            System.out.println(record);
        }
    }
}
