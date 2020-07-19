package com.softserve.itacademy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/records/list")
public class RecordListServlet extends HttpServlet {
    private AddressBook addressBook;
    public void init() throws ServletException {
        addressBook = AddressBook.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/records-list.jsp");
        String sort = request.getParameter("sort");
        if (sort != null) {
            if (sort.equals("asc")) addressBook.sortedBy(SortOrder.ASC);
            if (sort.equals("desc")) addressBook.sortedBy(SortOrder.DESC);
        }
        request.setAttribute("records", addressBookgetAll());
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
