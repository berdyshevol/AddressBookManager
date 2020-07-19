package com.softserve.itacademy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

enum SortOrder {
    ASC, DESC
}

@SuppressWarnings("unchecked")
public class AddressBook implements Iterable {
    private NameAddressPair[] addressBook;
    private int counter = 0;

    private AddressBook(int size) {
        addressBook = new NameAddressPair[size];
    }

    public boolean create(String firstName, String lastName, String address) {
        if (addressBook.length == counter) {
            addressBook = Arrays.copyOf(addressBook, 2 * counter);
        }
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                return false;
            }
        }
        addressBook[counter++] = new NameAddressPair(person, address);
        return true;
    }

    public String read(String firstName, String lastName) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                return addressBook[i].address;
            }
        }
        return null;
    }

    public boolean update(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                addressBook[i].address = address;
                return true;
            }
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                NameAddressPair[] newAddressBook = new NameAddressPair[addressBook.length];
                System.arraycopy(addressBook, 0, newAddressBook, 0, i);
                System.arraycopy(addressBook, i + 1, newAddressBook, i, addressBook.length - i - 1);
                addressBook = newAddressBook;
                counter--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return counter;
    }

    public NameAddressPair[] getAll() {return addressBook;}

    @Override
    public AddressBookIterator iterator() {
        return new AddressBookIterator();
    }

    public void sortedBy(SortOrder order) {
        switch (order) {
            case ASC:
                Arrays.sort(addressBook, (pair1, pair2) -> {
                    if (pair1 == null || pair2 == null) return 0;
                    return pair1.person.firstName.compareTo(pair2.person.firstName) == 0 ?
                            pair1.person.lastName.compareTo(pair2.person.lastName) :
                            pair1.person.firstName.compareTo(pair2.person.firstName);
                });
                break;
            case DESC:
                Arrays.sort(addressBook, (pair1, pair2) -> {
                    if (pair1 == null || pair2 == null) return 0;
                    return pair1.person.firstName.compareTo(pair2.person.firstName) == 0 ?
                            -pair1.person.lastName.compareTo(pair2.person.lastName) :
                            -pair1.person.firstName.compareTo(pair2.person.firstName);
                });
        }
    }

    private class AddressBookIterator implements Iterator {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < AddressBook.this.counter;
        }

        @Override
        public String next() {
            NameAddressPair nameAddressPair = addressBook[counter++];
            return "First name: " + nameAddressPair.person.firstName
                    + ", Last name: " + nameAddressPair.person.lastName
                    + ", Address: " + nameAddressPair.address;
        }
    }

    public static class NameAddressPair {
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private final String firstName;
            private final String lastName;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            @Override
            public boolean equals(Object object) {
                if (this == object) return true;
                if (object == null || getClass() != object.getClass()) return false;
                Person person = (Person) object;
                return Objects.equals(firstName, person.firstName) &&
                        Objects.equals(lastName, person.lastName);
            }
        }
    }

    private static AddressBook addressBookInstance = null;

    public static AddressBook getInstance() {
        if (addressBookInstance == null) {
            addressBookInstance = new AddressBook(5);
        }
        return addressBookInstance;
    }
}