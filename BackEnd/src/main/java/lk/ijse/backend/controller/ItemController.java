package lk.ijse.backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.backend.bo.ItemBo;
import lk.ijse.backend.bo.custom.ItemBoImpl;
import lk.ijse.backend.dto.ItemDto;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/item")
public class ItemController extends HttpServlet {

    ItemBo itemBo = new ItemBoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(reader, ItemDto.class);

            try{
                if (itemBo.addItem(itemDto)){
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    writer.write("Item Added Successfully");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    writer.write("Failed to add Item");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
