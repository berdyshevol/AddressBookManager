package com.softserve.itacademy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/records/create")
public class RecordCreateServlet extends HttpServlet {
    private AddressBook addressBook;
    public void init() throws ServletException {
        addressBook = AddressBook.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String address = request.getParameter("address");
        if (addressBook.create(firstName, lastName, address)) {
            response.sendRedirect("/records/list");
        }
        else {
            doGetWithError(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/records-create.jsp");
        request.setAttribute("error-message", "");
        request.setAttribute("first-name", "");
        request.setAttribute("last-name", "");
        request.setAttribute("address", "");
        requestDispatcher.forward(request, response);
    }

    protected void doGetWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/records-create.jsp");
        request.setAttribute("error-message", "An error occurred! Please try again!");
        request.setAttribute("first-name", request.getParameter("first-name"));
        request.setAttribute("last-name", request.getParameter("last-name"));
        request.setAttribute("address", request.getParameter("address"));
        requestDispatcher.forward(request, response);
    }
    protected List<Map<String, String>> addressBookgetAll() {
        String str1 = "First name: ";
        String str2 = ", Last name: ";
        String str3 = ", Address: ";
        List<Map<String, String>> list = new ArrayList<>();
        Iterator<String> interator = addressBook.iterator();
        while (interator.hasNext()) {
            String cur = interator.next();
            int find1 = cur.indexOf(str1);
            int find2 = cur.indexOf(str2);
            int find3 = cur.indexOf(str3);
            Map<String, String> item = new HashMap();
            item.put("first-name", cur.substring(find1 + str1.length(), find2));
            item.put("last-name",cur.substring(find2 + str2.length(), find3));
            item.put("address", cur.substring(find3 + str3.length() ));
            item.put("all", cur);
            list.add(item);
        }
        System.out.println(list);
        return list;
    }
}
