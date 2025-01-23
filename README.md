# Selenium Automation Task: Amazon.eg

## Task Description
This project automates a specific user scenario on the [Amazon.eg](https://www.amazon.eg/) website using Selenium with Java. Below is a detailed breakdown of the steps:

1. **Login**: Open the website and log in with valid credentials.
2. **Open "All" Menu**: Open the "All" menu from the left-hand side of the page.
3. **Navigate to Video Games**: Click on "Video Games" and then select "All Video Games."
4. **Apply Filters**: From the filter menu on the left-hand side, add the following filters:
   - Free Shipping
   - Condition: New
5. **Sort Products**: Open the sort menu on the right-hand side and sort by "Price: High to Low."
6. **Add Products to Cart**: Add all products priced below 15,000 EGP to the cart. If no products meet this criterion on the current page, navigate to the next page and repeat.
7. **Verify Cart Items**: Ensure all selected products are successfully added to the cart.
8. **Proceed to Checkout**:
   - Add a delivery address.
   - Choose "Cash on Delivery" as the payment method.
9. **Validate Total Amount**: Ensure the total amount displayed matches the sum of all item prices plus shipping fees, if applicable.

---

## Prerequisites
Before running the automation script, ensure the following are installed and configured:

1. **Java Development Kit (JDK)**: Version 8 or above.
2. **Maven**: For dependency management.
3. **Selenium WebDriver**: For browser automation.
4. **WebDriverManager**: For managing browser drivers.
5. **Allure Report**: For generating test reports.

---

## How to Run

### Step 1: Clone the Repository
Clone the project repository to your local machine:
```bash
git clone <repository-url>
```

### Step 2: Install Dependencies
Navigate to the project directory and install the required dependencies using Maven:
```bash
mvn clean install
```

### Step 3: Configure Login Credentials
Open the `config.properties` file (if available) or update the login credentials directly in the script:
```java
driver.findElement(By.id("ap_email")).sendKeys("your_email@example.com");
driver.findElement(By.id("ap_password")).sendKeys("your_password");
```

### Step 4: Run the Test Script
Execute the test script using Maven:
```bash
mvn test
```

---

## Generating Allure Report

1. **Run Tests with Allure Listener**:
   Ensure that the Allure listener is configured in your project. This can be done by adding the following dependency to your `pom.xml` file:
   ```xml
   <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-testng</artifactId>
       <version>2.17.3</version>
   </dependency>
   ```

2. **Execute Tests**:
   Run the test cases as described in the "How to Run" section.

3. **Generate Allure Report**:
   After the test execution, generate the Allure report using the following command:
   ```bash
   allure serve allure-results
   ```
   This will start a local server and open the Allure report in your default web browser.

---

## Project Structure

```
.
├── java
├── Pages
│   ├── P01_HomePage
│   ├── P02_LoginPage
│   ├── P03_AllVideoGamesPage
│   ├── P04_CartPage
├── resources
├── test
│   └── java
│       └── Foodics
│           ├── T01_Hooks
│           └── T02_Amazon
├── pom.xml
├── config.properties
└── README.md
