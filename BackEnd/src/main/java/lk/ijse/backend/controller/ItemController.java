package lk.ijse.backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.backend.bo.BOFactory;
import lk.ijse.backend.bo.ItemBo;
import lk.ijse.backend.bo.custom.ItemBoImpl;
import lk.ijse.backend.dto.ItemDto;
import org.slf4j.Logger;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet (urlPatterns = "/item")
public class ItemController extends HttpServlet {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(ItemController.class);

    ItemBo itemBo = (ItemBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid Content Type");
            System.out.println("req.getContentType() = " + req.getContentType());
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(reader, ItemDto.class);
            System.out.println("itemDto = " + itemDto);
            try{
                if (itemBo.addItem(itemDto)){
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    writer.write("Item Added Successfully");
                    logger.info("Item Added Successfully");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    writer.write("Failed to add Item");
                    logger.error("Failed to add Item");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var id = req.getParameter("id");
//
//        if (id.equals("all")) {
//            try {
//                List<ItemDto> allItems = itemBo.getAllItems();
//                resp.setContentType("application/json");
//                Jsonb jsonb = JsonbBuilder.create();
//                jsonb.toJson(allItems, resp.getWriter());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            resp.setContentType("application/json");
//            Jsonb jsonb = JsonbBuilder.create();
//            try {
//                jsonb.toJson(itemBo.searchItem(id), resp.getWriter());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        try(PrintWriter writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            jsonb.toJson(itemBo.getAllItems(), writer);

        }catch (SQLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPut");
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid Content Type");
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {
            System.out.println("Awa Badu");
            Jsonb jsonb = JsonbBuilder.create();

            ItemDto itemDto = jsonb.fromJson(reader, ItemDto.class);
            System.out.println("itemDto = " + itemDto);
            try {
                if (itemBo.updateItem(itemDto)){
                    System.out.println("itemDto = " + itemDto);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    writer.write("Item Updated Successfully");
                    logger.info("Item Updated Successfully");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    writer.write("Failed to update Item");
                    logger.error("Failed to update Item");
                }
            } catch (Exception e) {
                System.out.println("e = " + e);
                logger.error("Failed to update Item");
                e.printStackTrace();

            }
        }
    }
//TODO: Implement doDelete method
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        var writer = resp.getWriter();

        try{
            if (itemBo.deleteItem(id)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                writer.write("Item Deleted Successfully");
                logger.info("Item Deleted Successfully");
            }
            else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writer.write("Failed to delete Item");
            }
        } catch (SQLException e) {
            logger.error("Failed to delete Item");
            e.printStackTrace();
        }
    }
}
