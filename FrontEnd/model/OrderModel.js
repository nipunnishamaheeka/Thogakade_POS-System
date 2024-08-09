import { Orders } from "../db/DB.js";

// export function getAllOrders() {
//   return Orders;
// }
const baseUrl = "http://localhost:8080/app/order";

export async function getAllOrders() {
  try {
    const response = await fetch(baseUrl);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching orders:", error);
    throw error;
  }
}
// export function saveOrder(order) {
//   Orders.push(order);
// }
export async function saveOrder(order) {
  const sendItems = order.items.map((item) => ({
    id: item.itemCode,
    name: item.getItems,
    qty: item.itemQty,
    price: item.itemPrice,
  }));

  const orderData = {
    id: order.orderId,
    date: order.orderDate,
    customerId: order.custId,
    items: sendItems,
    total: order.total,
    discount: order.discount,
    subTotal: order.subTotal,
    cash: order.cash,
    balance: order.balance,
  };

  try {
    const response = await fetch(baseUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(orderData),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    console.log(data);
    alert("Order Placed");
    return data;
  } catch (error) {
    console.error("Error saving order:", error);
    throw error;
  }
}