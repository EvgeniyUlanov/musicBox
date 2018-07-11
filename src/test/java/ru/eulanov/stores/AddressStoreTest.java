package ru.eulanov.stores;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eulanov.InitDataBase;
import ru.eulanov.entities.Address;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class AddressStoreTest {

    @BeforeClass
    public static void initDB() {
        InitDataBase.createTableAddress();
    }

    @Test
    public void testAddGetDeleteAddress() {
        AddressStore addressStore = AddressStore.getInstance();
        Address address = new Address();
        address.setAddress("address");
        addressStore.addAddress(address);

        List<Address> addresses = addressStore.getAllAddress();

        assertTrue(addresses.contains(address));

        for (Address address1 : addresses) {
            if (address1.getAddress().equals("address")) {
                addressStore.deleteAddress(address1);
            }
        }

        addresses = addressStore.getAllAddress();

        assertTrue(!addresses.contains(address));
    }
}
