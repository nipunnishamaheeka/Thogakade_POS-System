# Togakade POS System

## Overview

Welcome to the Togakade POS System! This online Point of Sale (POS) application is designed to streamline essential tasks such as adding, searching, updating, and deleting records. It features a user-friendly interface crafted with HTML, CSS, JavaScript, and jQuery, while the backend is powered by Java EE and Tomcat.

## Features

### Customer Management:
- **Register New Customers:** Add new entries effortlessly.
- **Locate Existing Customers:** Quickly search for customer details.
- **Modify Customer Details:** Update existing customer information.
- **Remove Customers:** Delete customer records as needed.

### Item Management:
- **Introduce New Items:** Add fresh items to the inventory.
- **Find Current Items:** Search and retrieve item details.
- **Edit Item Details:** Update information for existing items.
- **Eliminate Items:** Remove items from the inventory with ease.

### Order Management:
- **Generate New Orders:** Create and process new orders.
- **Access Current Orders:** Review and manage all existing orders.

## Technologies Used

- **Frontend:** HTML, CSS, JavaScript, jQuery
- **Backend:** Java EE, Tomcat
- **API Documentation:** Postman

## Controllers and Endpoints

### Customer Controller
- **Create Customer:** `POST /customer` - Add a new customer.
- **Find Customer:** `GET /customer/{id}` - Retrieve customer details by ID.
- **Modify Customer:** `PUT /customer` - Update an existing customer's details.
- **Remove Customer:** `DELETE /customer/{id}` - Delete a customer by ID.
- **List Customers:** `GET /customer` - Get a list of all customers.

### Item Controller
- **Add Item:** `POST /item` - Add a new item to the inventory.
- **Find Item:** `GET /item/{id}` - Retrieve item details by ID.
- **Update Item:** `PUT /item` - Update information for an existing item.
- **Remove Item:** `DELETE /item/{id}` - Delete an item by ID.
- **List Items:** `GET /item` - Get a list of all items.

### Order Controller
- **Create Order:** `POST /order` - Initiate a new order.
- **List Orders:** `GET /order` - Retrieve a list of all orders.

## Getting Started

1. **Prerequisites:** Ensure you have a Java EE environment set up and Tomcat installed.
2. **Clone the Repository:** Run `git clone https://github.com/nipunnishamaheeka/Thogakade_POS-System.git` to get the source code.
3. **Set Up the Database:** Configure your database settings in the `application.properties` file.
4. **Build and Run:** Deploy the WAR file to Tomcat and start the application.

## API Documentation

For comprehensive API endpoints and usage instructions, please check out the [Postman Documentation](https://documenter.getpostman.com/view/35385181/2sA3s3GW2o).

## License

This project is licensed under the MIT License. For more information, view the [LICENSE file](https://github.com/nipunnishamaheeka/Thogakade_POS-System/blob/main/LICENSE).

---
