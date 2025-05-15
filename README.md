
# OpenCart API Testing Project

This project contains a set of API tests for the OpenCart demo website: [https://demo.opencart.com](https://demo.opencart.com)

## 🔍 Overview

The purpose of this project is to test various publicly accessible endpoints of OpenCart using **Postman**.

Since the demo site does not provide direct access to RESTful APIs for cart or login functionality, we are simulating the testing using available URLs and checking server responses.

---

## ✅ Included API Test Cases

| Test Name                 | Endpoint                                                                 | Description                                   |
|--------------------------|--------------------------------------------------------------------------|-----------------------------------------------|
| Homepage                 | `GET /`                                                                  | Check if the homepage is accessible           |
| Get Desktops Category    | `GET /index.php?route=product/category&path=20`                         | Ensure the Desktops category loads properly   |
| Search Product (valid)   | `GET /index.php?route=product/search&search=mac`                        | Ensure search returns expected results        |
| Search Product (invalid) | `GET /index.php?route=product/search&search=xyz123`                     | Ensure invalid search returns "no product"    |
| Contact Page             | `GET /index.php?route=information/contact`                              | Verify that the Contact Us page is available  |

---

## 📦 How to Use

1. Open **Postman**.
2. Import the collection file: `OpenCart_API_Testing_Advanced.postman_collection.json`
3. Run the collection or individual requests.
4. Check the **Tests tab** for pass/fail status.

---

## 📋 Notes

- These are **public endpoints** so responses are in **HTML**, not JSON.
- The tests are basic but structured to show typical API testing behavior:
  - Status code validation
  - Response content checks
  - Performance (response time)

---

## 💡 Suggestions

To build a more realistic API test:
- Setup OpenCart locally and enable REST API module.
- Add authentication & CRUD operations for products/cart.

