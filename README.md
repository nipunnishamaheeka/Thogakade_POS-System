Togakade POS System

Overview

The Togakade POS System is an online Point of Sale (POS) application designed to manage fundamental tasks, including record addition, search, updates, and deletions. The user interface is created with HTML, CSS, JavaScript, and jQuery, while the backend is supported by Java EE and Tomcat.

**Features**

**Customer Management:**
- Register new customers
- Locate existing customers
- Modify customer details
- Remove customers

**Item Management:**
- Introduce new items to the inventory
- Find current items
- Edit item details
- Eliminate items from the inventory

**Order Management:**
- Generate new orders
- Access and review current orders

  **Technologies Used**

**Frontend:** HTML, CSS, JavaScript, jQuery  
**Backend:** Java EE, Tomcat  
**API Documentation:** Postman


**Controllers and Endpoints**

**Customer Controller**
- **Create Customer:** POST /customer - Adds a new customer.
- **Find Customer:** GET /customer/{id} - Fetches customer details using ID.
- **Modify Customer:** PUT /customer - Updates information for an existing customer.
- **Remove Customer:** DELETE /customer/{id} - Deletes a customer by ID.
- **List Customers:** GET /customer - Retrieves a list of all customers.

**Item Controller**
- **Add Item:** POST /item - Introduces a new item to the inventory.
- **Find Item:** GET /item/{id} - Retrieves item details using ID.
- **Update Item:** PUT /item - Modifies details of an existing item.
- **Remove Item:** DELETE /item/{id} - Deletes an item by ID.
- **List Items:** GET /item - Retrieves a list of all items.

**Order Controller**
- **Create Order:** POST /order - Initiates a new order.
- **List Orders:** GET /order - Retrieves a list of all orders.

 **Getting Started**

**Prerequisites:** Make sure you have a Java EE environment ready and Tomcat installed.

**Clone the Repository:** Use the command `git clone https://github.com/nipunnishamaheeka/Thogakade_POS-System.git` to get the code.

**Set Up the Database:** Adjust your database settings in the `application.properties` file.

**Build and Run:** Deploy the WAR file to Tomcat and launch the application.

**API Documentation**

For detailed API endpoints and usage instructions, please refer to the [Postman Documentation](https://documenter.getpostman.com/view/35385181/2sA3s3GW2o).

**License**

**License**

This project is licensed under the MIT License. For more details, please refer to the [LICENSE file](https://github.com/nipunnishamaheeka/Thogakade_POS-System/blob/main/LICENSE).


