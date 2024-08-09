import { Items } from "../db/DB.js";

// export function saveItem(item) {
//   Items.push(item);
//   console.log(Items);
// }
export async function saveItem(item) {
  // console.log(item = "Item Awa");
  
  // try {
  //   const response = await fetch("http://localhost:8080/app/item", {
  //     method: "POST",
  //     headers: {
  //       "Content-Type": "application/json",
  //     },
  //     body: JSON.stringify(item),
  //   });

  //   if (response.ok) {
  //     console.log("Item saved successfully");
  //   } else {
  //     console.error("Failed to save item");
  //   }
  // } catch (error) {
  //   console.error("Error:", error);
  // }

  let senditem = {
    id: item.itemId,
    name: item.itemName,
    qty: item.itemQty,
    price: item.itemPrice
  }
      
  console.log(senditem);

   console.log(item);

  //  Customers.push(item);

   try {
     const response = await fetch("http://localhost:8080/app/item", {
       method: "POST",
       headers: {
         "Content-Type": "application/json",
       },
       body: JSON.stringify(senditem),
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

     console.log("item added successfully:", result);
     return result;
   } catch (error) {
     console.error("Error adding item:", error);
     throw error;
   }
}
// export function getAllItems() {
//   return Items;
// }

export async function getAllItems() {
  try {
    const response = await fetch("http://localhost:8080/app/item", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const items = await response.json();
    console.log("Items retrieved successfully:", items); // Log the items here
    return items;
  } catch (error) {
    console.error("Error retrieving items:", error);
    return []; // Return an empty array in case of error
  }
}



// export function deleteItem(index) {
//   Items.splice(index, 1);
// }
export async function deleteItem(id) {
  try {
    const response = await fetch(`http://localhost:8080/app/item?id=` + id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

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
// export function updateItem(index, item) {
//   Items[index] = item;
// }
export async function updateItem(index, item) {
  // Marked as async
  try {
    const response = await fetch(`http://localhost:8080/app/item`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
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

    console.log("Item updated successfully:", result);

    Items[index] = item;

    return result;
  } catch (error) {
    console.error("Error updating Item:", error);
    throw error;
  }
}

