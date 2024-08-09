package lk.ijse.backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.backend.bo.CustomerBo;
import lk.ijse.backend.bo.custom.CustomerBoImpl;
import lk.ijse.backend.dto.CustomerDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet( urlPatterns = "/customer" )
public class CustomerController extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try ( var reader = req.getReader(); var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(reader, CustomerDto.class);

            try {
                if (customerBo.addCustomer(customerDto)){
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    writer.write("Customer Added Successfully");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    writer.write("Failed to add Customer");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(PrintWriter writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            jsonb.toJson(customerBo.getAllCustomers(), writer);

        }catch (SQLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (var reader = req.getReader(); var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(reader, CustomerDto.class);

            if (customerBo.updateCustomer(customerDto)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Customer Updated Successfully");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writer.write("Failed to update Customer");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Delete");
//        String id = req.getParameter("id");
//        try {
//
//            if (customerBo.deleteCustomer(id)) {
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                resp.getWriter().write("Customer Deleted Successfully");
//            } else {
//                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                resp.getWriter().write("Failed to delete Customer");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String id = req.getParameter("id");

    try {
        if (customerBo.deleteCustomer(id)){
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Customer Deleted Successfully");
//            logger.info("Customer Deleted Successfully");
        }
        else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Failed to delete Customer");
//            logger.error("Failed to delete Customer");
        }
    } catch (Exception e) {
//        logger.error("Failed to delete Customer");
        e.printStackTrace();
    }

}

}
