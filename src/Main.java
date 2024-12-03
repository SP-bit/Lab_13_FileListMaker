import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
public class Main
{
    private static final Scanner in = new Scanner(System.in); // got help w/ methods
    private static final ArrayList<String> list = new ArrayList<>() ; // got help w/ methods
    private static boolean needsToBeSaved = false;
    public static void main(String[] args)
    {

        boolean finished = false;

        while (!finished) {
            showMenu();
            String choice = SafeInput.getRegExString(in, "Choose an option [AaDdIiPpVvMmOoSsCcQq]: ", "[AaDdIiPpVvMmOoSsCcQq]").toUpperCase();

            if (choice.equals("A"))
            {
                addItem();
            }
            else if (choice.equals("D"))
            {
                deleteItem();
            }
            else if (choice.equals("I"))
            {
                insertItem();
            }
            else if (choice.equals("P"))
            {
                printList();
            }
            else if (choice.equals("V"))
            {
                printList();
            }
            else if (choice.equals("M"))
            {
                moveItem();
            }
            else if (choice.equals("O"))
            {
                openFile();
            }
            else if (choice.equals("S"))
            {
                saveToFile();
            }
            else if (choice.equals("C"))
            {
                clearList();
            }
            else if (choice.equals("Q"))
            {
                if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?"))
                {
                    finished = true;
                }
            }
        }
    }
    private static void showMenu()
    {
        String menu = """
            \n--- Menu ---
            A - Add an item
            D - Delete an item
            I - Insert an item
            P - Print the list
            V - View the List
            M- Move an item
            O - Open a file
            S - Save the current list
            C- Clear list
            Q - Quit
            
            """;
        System.out.print(menu);
    }
    private static void printList()
    {
        System.out.println("\n--- List Items ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i)); // got help w/ formatting
        }
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item);
        needsToBeSaved = true;
        System.out.println("Item added.");
    }


    private static void deleteItem() {

        printList();
        int deletedItem = SafeInput.getRangedInt(in, "Enter the number of the item to delete", 1, list.size());
        list.remove(deletedItem - 1);
        needsToBeSaved = true;
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        printList();
        int position = SafeInput.getRangedInt(in, "Enter the position to insert at (1 to " + (list.size() + 1) + ")", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
        list.add(position - 1, item);
        needsToBeSaved = true;
        System.out.println("Item inserted.");
    }

    private static void moveItem()
    {
        printList();
        int moveFrom = SafeInput.getRangedInt(in, "Enter the number of the item to move: ", 1, list.size());
        int moveTo = SafeInput.getRangedInt(in, "Enter the new position (1 to " + list.size() + "): ", 1, list.size());

        if (moveFrom == moveTo) {
            System.out.println("The item is already in the desired position.");
            return;
        }

        String item = list.remove(moveFrom - 1);
        list.add(moveTo - 1, item);
        needsToBeSaved = true;
        System.out.println("Item moved.");
    }

    private static void openFile()
    {
        if (needsToBeSaved) {
            if (SafeInput.getYNConfirm(in, "You have unsaved changes. Save first?"))
            {
                saveToFile();
            }
        }
        String filename = SafeInput.getNonZeroLenString(in, "Enter the file name to open (.txt): ");
        try {
            list.clear();
            list.addAll(loadFile(filename));
            needsToBeSaved = false;
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
    private static ArrayList<String> loadFile(String filename) throws IOException {
        Path filePath = Path.of(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        return new ArrayList<>(Files.readAllLines(filePath));
    }

    private static void saveToFile() {
        String filename = SafeInput.getNonZeroLenString(in, "Enter the file name (to save as): ");
        if (!filename.endsWith(".txt")) {
            filename += ".txt"; // Ensure .txt extension
        }
        try {
            saveFile(list, filename);
            needsToBeSaved = false;
            System.out.println("File saved.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }


    private static void saveFile(ArrayList<String> list, String filename) throws IOException { // got help w/ paths & throws
        Files.write(Path.of(filename), list);
    }

    private static void clearList() {
        if (SafeInput.getYNConfirm(in, "Are you sure you want to clear the list?")) {
            list.clear();
            needsToBeSaved = true;
            System.out.println("List cleared.");
        }
    }
    }





