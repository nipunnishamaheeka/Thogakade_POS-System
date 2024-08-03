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
import java.sql.SQLException;

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
}
