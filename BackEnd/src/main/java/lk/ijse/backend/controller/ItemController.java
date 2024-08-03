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
import java.util.List;

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
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");

        if (id.equals("all")) {
            try {
                List<ItemDto> allItems = itemBo.getAllItems();
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(allItems, resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            try {
                jsonb.toJson(itemBo.searchItem(Integer.parseInt(id)), resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
