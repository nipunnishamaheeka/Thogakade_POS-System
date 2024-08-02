package lk.ijse.backend.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.backend.DAO.CustomerDAOImpl;
import lk.ijse.backend.dto.CustomerDto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            var initialContext =  new InitialContext();
            DataSource pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/thogakadepos");
            this.connection = pool.getConnection();
//            logger.debug("Connection initialized",this.connection);

        }catch (SQLException | NamingException e){
        throw new RuntimeException("DB connection not init",e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Todo:save student
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var customerDAOImpl = new CustomerDAOImpl();
            CustomerDto student = jsonb.fromJson(req.getReader(), CustomerDto.class);
//            student.setCust_id(Uti.idGenerate());
            //Save data in the DB
            writer.write(customerDAOImpl.saveCustomer(student,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}