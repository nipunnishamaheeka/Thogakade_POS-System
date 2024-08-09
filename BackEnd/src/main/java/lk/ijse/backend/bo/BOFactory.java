package lk.ijse.backend.bo;

import lk.ijse.backend.bo.custom.CustomerBoImpl;
import lk.ijse.backend.bo.custom.ItemBoImpl;
import lk.ijse.backend.bo.custom.OrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ?
                boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, Order
    }

    public SuperBo getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case CUSTOMER -> new CustomerBoImpl();
            case ITEM -> new ItemBoImpl();
            case Order -> new OrderBoImpl();
        };
    }
}
