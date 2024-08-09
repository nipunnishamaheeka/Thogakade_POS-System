import { Customers } from "../db/DB.js";

// export function saveCustomer(customer) {
//     Customers.push(customer);
// }
export async function saveCustomer(customer) {
  console.log(customer);

  Customers.push(customer);

  try {
    const response = await fetch("http://localhost:8080/app/customer", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(customer),
    });

    // Check if the response is OK
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const contentType = response.headers.get("Content-Type");
    let result;
    if (contentType && contentType.includes("application/json")) {
      result = await response.json();
    } else {
      result = await response.text();
      console.warn("Response is not JSON:", result);
    }

    console.log("Customer added successfully:", result);
    return result;
  } catch (error) {
    console.error("Error adding customer:", error);
    throw error;
  }
}

export async function getAllCustomers() {
  try {
    const response = await fetch("http://localhost:8080/app/customer");

    if (!response.ok) {
      throw new Error(`HTTP error: ${response.status}`);
    }
    const customerslist = await response.json();

    return customerslist;
  } catch (error) {
    console.error("Error retrieving customers:", error);
    return []; // Return an empty array in case of an error
  }
}

// export function updateCustomer(index, customer) {
//   Customers[index] = customer;
// }
export async function updateCustomer(index, customer) {
  // Marked as async
  try {
    const response = await fetch(`http://localhost:8080/app/customer`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(customer),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const contentType = response.headers.get("Content-Type");
    let result;
    if (contentType && contentType.includes("application/json")) {
      result = await response.json();
    } else {
      result = await response.text();
      console.warn("Response is not JSON:", result);
    }

    console.log("Customer updated successfully:", result);

    Customers[index] = customer;

    return result;
  } catch (error) {
    console.error("Error updating customer:", error);
    throw error;
  }
}

export async function deleteCustomer(custId) {
  try {
    const response = await fetch(
      `http://localhost:8080/app/customer/${custId}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    console.log("Customer Deleted");
    return await response.text();
  } catch (error) {
    console.error("Error deleting customer:", error);
    throw error;
  }
}



