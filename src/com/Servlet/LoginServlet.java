package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.other.ConProvider;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement pst = null;
    String myEmail, myPassword = null;
    String query = "SELECT * FROM validate WHERE email=? AND password=? ";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // JDBC connection
        ConProvider get = new ConProvider();
        try {
            con = get.getConn();
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                myEmail = rs.getString("email");
                myPassword = rs.getString("password");

                if (email.equals(myEmail) && password.equals(myPassword)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("email", email);

                    res.sendRedirect("welcome.jsp");
                } else {
                    res.sendRedirect("index.html");
                }
            }
            else {
                res.sendRedirect("index.html");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (con, pst, rs) here
            get.closeConnection(con);
        }
    }
}
