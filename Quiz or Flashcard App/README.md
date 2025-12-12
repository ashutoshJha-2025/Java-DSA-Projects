### 🧠 **Main Purpose:**
This app is designed to help users learn through **multiple choice questions (MCQs)** with immediate feedback and the ability to retry incorrectly answered questions.

---

### 📋 **Core Functionalities:**

#### 1. **Add Quiz/Flashcard**
- User inputs:
  - Question text
  - Four options
  - Correct answer index (1–4)
- The question is stored in an `ArrayList<Questions>`.

#### 2. **Remove a Question**
- Displays all existing questions.
- Lets user remove a question by its number.

#### 3. **Edit a Question**
- User can update:
  - The question text
  - Any of the four options
  - The correct option

#### 4. **Quiz Mode**
- Shuffles questions randomly.
- User answers each one.
- Correct answers increase the score.
- Incorrect answers get added to a **revision stack**.

#### 5. **Revision Mode**
- Reattempt questions from the stack (`Stack<Questions>`).
- If answered incorrectly again, they stay in the stack for future retry.

#### 6. **Display All Questions**
- Shows all current questions along with their options and correct answer.

#### 7. **Exit**
- Exits the program.

---

### 📦 **Data Structures Used:**
- `ArrayList<Questions>`: Main question bank.
- `Stack<Questions>`: Stores incorrectly answered questions for revision.
- `Scanner`: Handles user input.
- `Questions` class: Holds question text, options array, and correct answer index.

---
