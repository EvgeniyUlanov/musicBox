package ru.eulanov.servlets;

import ru.eulanov.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet to set music type to user as favorite.
 */
public class AddMusicToUserServlet extends HttpServlet {
    /** user store*/
    private UserStore userStore;

    @Override
    public void init() throws ServletException {
        userStore = UserStore.getInstance();
    }

    /**
     * method doPost.
     * @param req - http request
     * @param resp - http response
     * @throws ServletException - servlet exception
     * @throws IOException - io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String musicType = req.getParameter("musicType");
        userStore.setMusicPrefToUser(userId, musicType);
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }
}
