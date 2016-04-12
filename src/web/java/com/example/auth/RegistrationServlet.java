package com.example.auth;

import com.example.domain.tasks.BaseServlet;
import com.example.domain.user.TaskUser;
import com.example.domain.user.TaskUserDaoSql;
import org.apache.commons.lang.StringUtils;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("../register.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String err = "";
        String username = getSanitizedValue(req, "username");
        String password = getSanitizedValue(req, "password");
        String passwordConfirm = getSanitizedValue(req, "passwordConfirm");

        if (StringUtils.isBlank(username)) {
            err = "Username cannot be empty.";
        }
        else if (StringUtils.isBlank(password) || StringUtils.isBlank(passwordConfirm)) {
            err = "Password fields cannot be empty.";
        }
        else if (!password.equals(passwordConfirm)) {
            err = "Passwords do not match.";
        }
        else {
            TaskUserDaoSql userDaoSql = TaskUserDaoSql.getInstance();
            TaskUser existingUser = userDaoSql.restoreForName(username);

            if (existingUser == null) {
                TaskUser user = new TaskUser(username, password);
                userDaoSql.insert(user);

                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
            else {
                err = "User already exists for username '" + username + "'.";
            }
        }

        if (StringUtils.isNotBlank(err)) {
            req.setAttribute("error", err);
            doGet(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/tasks/");
    }

}
