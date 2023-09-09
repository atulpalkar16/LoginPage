package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.other.ConProvider;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection con = null;
    PreparedStatement pst = null;
    String query = "INSERT INTO login.validate (name, email, password) VALUES (?, ?, ?)";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("regiName");
        String email = req.getParameter("regiEmail");
        String password = req.getParameter("regiPassword");

        // JDBC Connection
        ConProvider get = new ConProvider();
        try {
            con = get.getConn();
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            int count = pst.executeUpdate();

            if (count > 0) {
                res.sendRedirect("index.html"); // Registration successful, redirect to the login page.
            } else {
                // Registration failed, display an error message or redirect to an error page.
                res.sendRedirect("error.html");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle the unique constraint violation (duplicate email).
            // You can redirect to a registration error page or display an error message.
            res.sendRedirect("error.html");
        } catch (ClassNotFoundException | SQLException e) {
            // Handle other exceptions (e.g., database connection issues).
            e.printStackTrace();
            res.sendRedirect("error.html"); // Redirect to a general error page on database error.
        } finally {
            // Close resources properly in a real application.
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
