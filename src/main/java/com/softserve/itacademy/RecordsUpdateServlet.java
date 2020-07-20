package com.softserve.itacademy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/records/update")
public class RecordsUpdateServlet extends RecordCreateServlet {
    private AddressBook addressBook;
    public void init() throws ServletException {
        addressBook = AddressBook.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String address = request.getParameter("address");
        if (addressBook.update(firstName, lastName, address)) {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/records-update.jsp");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String address = addressBook.read(firstName, lastName);
        if (address != null) {
            request.setAttribute("address", address);
            requestDispatcher.forward(request, response);
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
