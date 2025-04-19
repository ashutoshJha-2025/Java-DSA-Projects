The code is a **console-based shopping cart system** in Java, simulating a mini point-of-sale (POS) or grocery store application. Here’s a breakdown of its functionality:

---

### 🛒 **Main Features:**

1. **Inventory Management:**
   - Uses a `HashMap<Integer, Product>` to store a list of predefined products (like Mango, Apple, etc.), each with a stock count and price.

2. **Shopping Cart:**
   - Uses an `ArrayList<Product>` as a cart to store items the user wants to purchase.

3. **User Menu:**
   - A loop displays a menu with options:
     1. View Inventory and Add items to cart
     2. View Cart
     3. Remove items from Cart
     4. Get Bill

---

### 🔧 **Core Functional Methods:**

- **`displayInventory()`**: Shows all available products with their ID, name, price, and current stock.

- **`addItemsToCart()`**: Lets users add products to their cart by entering the product ID and quantity.
   - Updates inventory stock accordingly.

- **`viewCart()`**: Lists items currently in the user's cart.

- **`removeFromCart()`**: Removes an item from the cart by index and restores that item's quantity back to inventory.

- **`getBill()`**: 
   - Calculates and shows the total bill.
   - Accepts a discount code (`SAVE10`) for a 10% discount.
   - Prompts user to pay the final amount.
   - If correct amount entered, it clears the cart and confirms checkout.

---

### 📦 **Product Class:**
Custom class with:
- Fields: `name`, `price`, `quantity`
- Methods: Getters and a `toString()` override for display

---
