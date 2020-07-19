package com.softserve.itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/records/delete")
public class RecordsDeleteServlet extends RecordCreateServlet {
    private AddressBook addressBook;
    public void init() throws ServletException {
        addressBook = AddressBook.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        if (addressBook.delete(firstName, lastName)) {
            response.sendRedirect("/records/list");
        }
        else {
            response.setStatus(404);
            response.getWriter().print(
                    String.format(
                            "Person with name '%s' not found in Address Book!",
                            request.getParameter("first-name") + " " + request.getParameter("last-name")
                    )
            );
        }
    }
}
