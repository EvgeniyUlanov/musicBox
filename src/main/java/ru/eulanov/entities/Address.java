package ru.eulanov.entities;

/**
 * entity for address.
 */
public class Address {
    /** id of address*/
    private long id;
    /** name of address*/
    private String address;

    /** default constructor*/
    public Address() {
    }

    /**
     * get method for id
     * return id
     */
    public long getId() {
        return id;
    }

    /**
     * set method for id
     * @param id - address id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * get method for address
     * @return address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * set method for address.
     * @param address - string address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address1 = (Address) o;

        return address != null ? address.equals(address1.address) : address1.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }
}
