
<%@ page import="java.util.List" %>
<%@ page import="NwaforInnocetIfenna.model.Product" %>
<%@ page import="NwaforInnocetIfenna.dao.AdminDAO" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 20/03/2023
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> products;
    try {
        products = AdminDAO.listOfProduct();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-commerce Store Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
    <style>
        /* Set default font family and color */
        body {
            font-family: Arial, sans-serif;
            color: #333;
            background: #9d9d9d;
        }

        /* Set header style */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem;
            background-color: #f2f2f2;
        }
        header h1 {
            left: 50%;
            color: #721c24;
            box-shadow:
                    2px 2px 5px #888888,
                    -2px -2px 5px #000000;
            border-radius: 5px;
        }

        /* Style logout link */
        header a {
            text-decoration: none;
            color: #666;
            border: 1px solid #666;
            padding: 0.5rem;
            border-radius: 5px;
            box-shadow:
                    2px 2px 5px #888888,
                    -2px -2px 5px #000000;

        }

        /* Style search form */
        nav form {
            margin: 1rem 0;
        }

        nav label {
            margin-right: 0.5rem;
        }

        nav input[type="text"] {
            padding: 0.5rem;
            border-radius: 5px;
            border: none;
            box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
        }

        nav button[type="submit"] {
            padding: 0.5rem;
            background-color: black;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        /* Style navigation links */
        nav ul {
            display: flex;
            justify-content: flex-end;
        }

        nav li {
            margin-left: 1rem;
        }

        nav a {
            text-decoration: none;
            color: #333;
        }

        nav a:hover {
            text-decoration: underline;
        }

        /* Style products table */
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 0.5rem;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            align-items: center;
            background-color: olivedrab;
        }

        /* Style remove button */
        .remove-btn {
            padding: 0.5rem;
            background-color: #ff6961;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .remove-btn:hover {
            background-color: #ff4136;
        }

        /* Style form for updating price and quantity */
        form {
            display: inline-block;
        }

        form label {
            display: inline-block;
            margin-right: 10px;
        }

        form input[type="number"] {
            width: 80px;
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            margin-right: 10px;
        }

        form button[type="submit"] {
            background-color: #4CAF50;
            color: red;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        form button[type="submit"]:hover {
            background-color: #3e8e41;
        }

    </style>
</head>
<body>
<header>
    <a href="index.jsp">Home</a>
    <h1>Welcome Admin</h1>
    <a href="#">Logout</a>
</header>
<nav>
    <form>
        <label for="search">Search Products:</label>
        <input type="text" id="search" name="search">
        <button type="submit">Search</button>
    </form>
    <ul>
        <li><button><a href="#">View Orders</a></button></li>
        <%--        <li><a href="#">Add Product</a></li>--%>
    </ul>
</nav>
<main>
    <h2>Products List</h2>
    <table>
        <thead>
        <tr>
            <th>ADDING PRODUCT</th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td>
                <form method="post", action="AddProduct">
                    <label for="productName">Set Product Name:</label>
                    <input type="text" id="productName" placeholder="Product Name" name="name">
                    <label for="productCategory">Set Product Category:</label>
                    <input type="text" id="productCategory" placeholder="Product Category" name="category">
                    <label for="productPrice">Set Price:</label>
                    <input type="number"id="productPrice" placeholder="Product Price" name="price">
                    <label for="productQuantity">Set Quantity:</label>
                    <input type="number" id="productQuantity"placeholder="Product Quantity" name="quantity">
                    <label for="productImage">Set Product Image:</label>
                    <input type="text" id="productImage"placeholder="Product Image" name="image">

                    <button type="submit">Add Product</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <br>

    <h2>Products List</h2>
    <table>
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (!products.isEmpty()) {
                for (Product p : products) {
        %>
        <tr name="<%=p.getName()%>">
            <td><%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <%--                <td><%=p.getProductName()%></td>--%>
            <td>$<%=p.getPrice()%></td>
            <td><%=p.getQuantity()%></td>
            <td>
                <form method="post" action="price_update">
                    <label for="price">Set Price:</label>
                    <input type="hidden" name="product_id" value="<%=p.getId()%>">
                    <input type="number" id="price" name="price" placeholder="Product Price">
                    <button type="submit">Update</button>
                </form>
                <form method="post" action="quantity_update">
                    <label for="quantity">Set Quantity:</label>
                    <input type="hidden" name="product_id" value="<%=p.getId()%>">
                    <input type="number" id="quantity" name="quantity" placeholder="Product Quantity">
                    <button type="submit">Update</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>


    <br>
    <br>
    <table>
        <thead>
        <tr>
            <th>REMOVING PRODUCT</th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td>
                <form method="post", action="RemoveProduct">
                    <label for="productID1">Enter ProductID:</label>
                    <input type="number" id="productID1" name="productID" placeholder = "name">
                    <button type="submit">Remove Product</button>
                </form>

            </td>
        </tr>
        </tbody>
    </table>

</main>
</body>
</html>
