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
    /**
     * method doPost
     * @param req - http request
     * @param resp - http response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String incomingString = MyUtil.getIncomingStringFromReqest(req);
        Gson gson = new Gson();
        User user = gson.fromJson(incomingString, User.class);
        Address address = new Address();
        address.setAddress(user.getAddress());
        AddressStore.getInstance().addAddress(address);
        UserStore.getInstance().addUser(user);
    }
}
