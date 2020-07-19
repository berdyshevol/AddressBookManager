package com.softserve.itacademy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/records/read")
public class RecordsReadServlet extends HttpServlet {
    private AddressBook addressBook;
    public void init() throws ServletException {
        addressBook = AddressBook.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = addressBook.read(request.getParameter("first-name"), request.getParameter("last-name"));
        if (address != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/record-read.jsp");
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
