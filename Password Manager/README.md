### 🔐 **Main Purpose:**
To help users manage their site credentials (Gmail + password) by storing them in memory using a `HashMap`.

---

### 📋 **Functionality Overview:**

#### 1. **Main Menu Options:**
Users are shown three options repeatedly:
```
1. Add a new account
2. Search password by Site name
3. Exit
```

---

### 🔧 **Key Functional Parts:**

#### ✅ `addAccount()`:
- Lets the user add a new entry for a website.
- Prompts for:
  - Site name
  - Gmail
  - Option to either:
    - Enter a custom strong password, or
    - Let the program auto-generate one
- Stores this info in a `HashMap<String, passManage>` with the site name as the key.

#### 🔐 `generateStrongPassword()`:
- Builds a random password that:
  - Includes uppercase, lowercase, digits, special characters
  - Has at least 8 characters
- Uses `SecureRandom` for strong randomness.
- Randomizes character order for better security.

#### 🔍 `searchbySiteName()`:
- Looks up credentials based on the site name.
- If found, displays Gmail and Password.
- Otherwise, informs the user that nothing was found.

#### 🗃️ `passManage` Class:
- Stores the Gmail and Password.
- Provides simple getters for both.

---

### ✅ **Highlights:**
- Good use of `HashMap` to associate site names with account details.
- Secure password generation with randomness and diversity of character types.
- Clean separation of logic through modular methods.
- Easy to navigate for users through a clear menu.

---