import java.util.*;

public class code5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Questions> quiz = new ArrayList<>();
        Stack<Questions> reviseQuestions = new Stack<>();

        // Adding questions to the quiz with options and correct answer index
        quiz.add(new Questions("What is the capital of France?",
                new String[] { "Paris", "London", "Berlin", "Madrid" }, 0));
        quiz.add(new Questions("Which planet is known as the Red Planet?",
                new String[] { "Earth", "Mars", "Jupiter", "Venus" }, 1));
        quiz.add(new Questions("What is the largest mammal?",
                new String[] { "Elephant", "Blue Whale", "Giraffe", "Shark" }, 1));
        quiz.add(new Questions("Who wrote 'Romeo and Juliet'?",
                new String[] { "Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen" }, 1));
        quiz.add(new Questions("What is the square root of 64?",
                new String[] { "6", "7", "8", "9" }, 2));

        while (true) {
            System.out.println("\n===== Quiz or Flashcard App =====");
            System.out.println("1. Add Quiz/Flashcard");
            System.out.println("2. Remove a Question");
            System.out.println("3. Edit Question");
            System.out.println("4. Quiz Mode");
            System.out.println("5. Revision Mode");
            System.out.println("6. Display All Questions");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuiz(quiz, sc);
                    break;
                case 2:
                    removeQuestion(quiz, sc);
                    break;
                case 3:
                    editQuestion(quiz, sc);
                    break;
                case 4:
                    quizMode(quiz, sc, reviseQuestions);
                    break;
                case 5:
                    revisionMode(reviseQuestions, sc);
                    break;
                case 6:
                    displayAllQuestions(quiz);
                    break;
                case 7:
                    System.out.println("Exiting... Thank you for using the app!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addQuiz(ArrayList<Questions> quiz, Scanner sc) {
        while (true) {
            System.out.print("\nEnter the question (or type '0' to exit): ");
            String question = sc.nextLine();
            if (question.equals("0")) {
                return;
            }

            System.out.println("Enter the 4 options:");
            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("Option " + (i + 1) + ": ");
                options[i] = sc.nextLine();
            }

            System.out.print("Enter the correct option number (1-4): ");
            int correctOption = sc.nextInt() - 1; // Convert to 0-based index
            sc.nextLine(); // Consume newline

            quiz.add(new Questions(question, options, correctOption));
            System.out.println("Question added successfully!");
        }
    }

    private static void removeQuestion(ArrayList<Questions> quiz, Scanner sc) {
        displayAllQuestions(quiz);
        System.out.print("Enter the question number to remove: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (choice > 0 && choice <= quiz.size()) {
            quiz.remove(choice - 1);
            System.out.println("Question removed successfully!");
        } else {
            System.out.println("Invalid question number!");
        }
    }

    private static void editQuestion(ArrayList<Questions> quiz, Scanner sc) {
        displayAllQuestions(quiz);
        System.out.print("Enter the question number to edit: ");
        int number = sc.nextInt() - 1;
        sc.nextLine(); // Consume newline

        if (number >= 0 && number < quiz.size()) {
            Questions temp = quiz.get(number);
            System.out.println("What do you want to edit?");
            System.out.println("1. Question");
            System.out.println("2. Options");
            System.out.println("3. Correct Option");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the new question: ");
                    temp.question = sc.nextLine();
                    break;
                case 2:
                    System.out.println("Enter the new 4 options:");
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Option " + (i + 1) + ": ");
                        temp.options[i] = sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Enter the new correct option number (1-4): ");
                    temp.correctOption = sc.nextInt() - 1;
                    sc.nextLine(); // Consume newline
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println("Question updated successfully!");
        } else {
            System.out.println("Invalid question number!");
        }
    }

    private static void quizMode(ArrayList<Questions> quiz, Scanner sc, Stack<Questions> reviseQuestions) {
        Collections.shuffle(quiz); // Shuffle the questions
        System.out.println("\n===== Quiz Mode =====");
        int score = 0;

        for (int i = 0; i < quiz.size(); i++) {
            Questions q = quiz.get(i);
            System.out.println("\n" + (i + 1) + ". " + q.question);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println("   " + (j + 1) + ". " + q.options[j]);
            }

            System.out.print("Your answer (1-4): ");
            int answer = sc.nextInt() - 1; // Convert to 0-based index

            if (answer == q.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + (q.correctOption + 1));
                reviseQuestions.push(q);
                System.out.println("Question added to revision folder.");
            }
        }

        System.out.println("\nQuiz completed! Your score: " + score + "/" + quiz.size());
    }

    private static void revisionMode(Stack<Questions> reviseQuestions, Scanner sc) {
        if (reviseQuestions.isEmpty()) {
            System.out.println("No questions in the revision folder!");
            return;
        }

        System.out.println("\n===== Revision Mode =====");
        int score = 0;

        while (!reviseQuestions.isEmpty()) {
            Questions q = reviseQuestions.pop();
            System.out.println("\n" + q.question);
            for (int i = 0; i < q.options.length; i++) {
                System.out.println("   " + (i + 1) + ". " + q.options[i]);
            }

            System.out.print("Your answer (1-4): ");
            int answer = sc.nextInt() - 1; // Convert to 0-based index

            if (answer == q.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + (q.correctOption + 1));
                reviseQuestions.push(q); // Add back to the stack for further revision
                System.out.println("Question added back to revision folder.");
            }
        }

        System.out.println("\nRevision completed! Your score: " + score);
    }

    private static void displayAllQuestions(ArrayList<Questions> quiz) {
        System.out.println("\n===== All Questions =====");
        for (int i = 0; i < quiz.size(); i++) {
            Questions q = quiz.get(i);
            System.out.println((i + 1) + ". " + q.question);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println("   " + (j + 1) + ". " + q.options[j]);
            }
            System.out.println("   Correct Option: " + (q.correctOption + 1));
        }
    }
}

class Questions {
    String question;
    String[] options;
    int correctOption;

    public Questions(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}