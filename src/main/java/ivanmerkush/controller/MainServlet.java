package ivanmerkush.controller;

import ivanmerkush.sql.Sql;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private Sql sql;

    @Override
    public void init() throws ServletException {
        try {
            sql = new Sql();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(action.equals("add")) {
            if(session.getAttribute("model").equals("customer")) {
                sql.addCustomer(request.getParameter("name"), new java.sql.Date(new Date().getTime()));
                request.setAttribute("model", "customer");
            }
            else {
                try {
                    sql.addOrder(request.getParameter("name"),
                                    request.getParameter("clothing"),
                                    Integer.parseInt(request.getParameter("price")),
                                    new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("term")).getTime()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                request.setAttribute("model", "order");
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue()[0]);
        }

        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        switch(action) {
            case "getAll":
                session.invalidate();
                session = request.getSession();
                session.setAttribute("model", "order");
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
            case "get":
                if(session.getAttribute("special").equals("name") || session.getAttribute("special").equals("time")) {
                    dispatcher = request.getRequestDispatcher("orderSpecial.jsp");
                    break;
                }
            case "add":
                dispatcher = request.getRequestDispatcher(action +".jsp");
                break;
            case"Delete Customer":
                sql.deleteCustomer(request.getParameter("name"));
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
            case"Delete Order":
                sql.deleteOrder(request.getParameter("clothing"), Integer.parseInt(request.getParameter("price")));
            default:
                dispatcher = request.getRequestDispatcher("index.jsp");
                break;
        }
        dispatcher.forward(request,response);


    }
}
