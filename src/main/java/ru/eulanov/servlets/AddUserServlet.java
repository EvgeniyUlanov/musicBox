package ru.eulanov.servlets;

import com.google.gson.Gson;
import ru.eulanov.entities.Address;
import ru.eulanov.entities.User;
import ru.eulanov.stores.AddressStore;
import ru.eulanov.stores.UserStore;
import ru.eulanov.utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet to save user to database
 */
public class AddUserServlet extends HttpServlet {
    /** user store*/
    private UserStore userStore;
    /** address store*/
    private AddressStore addressStore;

    @Override
    public void init() throws ServletException {
        userStore = UserStore.getInstance();
        addressStore = AddressStore.getInstance();
    }

    /**
     * method doPost
     * @param req - http request
     * @param resp - http response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String incomingString = MyUtil.getIncomingStringFromRequest(req);
        Gson gson = new Gson();
        User user = gson.fromJson(incomingString, User.class);
        Address address = new Address();
        address.setAddress(user.getAddress());
        addressStore.addAddress(address);
        userStore.addUser(user);
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    public AddressStore getAddressStore() {
        return addressStore;
    }

    public void setAddressStore(AddressStore addressStore) {
        this.addressStore = addressStore;
    }
}