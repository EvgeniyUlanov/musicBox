package ru.eulanov.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * class for utils
 */
public class MyUtil {

    /**
     * method create string from request buffer reader
     * @param req - request
     * @return string
     */
    public static String getIncomingStringFromReqest(HttpServletRequest req) {
        StringBuilder incomingString = new StringBuilder();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                incomingString.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomingString.toString();
    }
}
