package lk.ijse.backend.dao;

import lk.ijse.backend.dao.custom.CustomerDaoImpl;
import lk.ijse.backend.dao.custom.ItemDaoImpl;
import lk.ijse.backend.dao.custom.OrderDaoImpl;
import lk.ijse.backend.dao.custom.OrderDetailDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, Order , OrderDetail
    }

    public SuperDAO getDAO(DAOType daoType) {
        return switch (daoType) {
            case CUSTOMER -> new CustomerDaoImpl();
            case ITEM -> new ItemDaoImpl();
            case Order -> new OrderDaoImpl();
            case OrderDetail -> new OrderDetailDaoImpl();
        };
    }
}
