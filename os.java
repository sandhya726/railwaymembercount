import java.util.*;

class FileAllocation {

    static int totalBlocks = 20;
    static boolean[] disk = new boolean[totalBlocks];
    static HashMap<String, int[]> files = new HashMap<>();

    // 🔹 Allocate (Contiguous)
    static void allocateContiguous(String fileName, int size) {
        int start = -1, count = 0;

        for (int i = 0; i < totalBlocks; i++) {
            if (!disk[i]) {
                if (count == 0) start = i;
                count++;

                if (count == size) {
                    for (int j = start; j < start + size; j++) {
                        disk[j] = true;
                    }

                    files.put(fileName, new int[]{start, start + size - 1});

                    System.out.println(fileName + " allocated from block "
                            + start + " to " + (start + size - 1));
                    return;
                }
            } else {
                count = 0;
            }
        }

        System.out.println("Allocation failed (no contiguous space)");
    }

    // 🔹 Delete File
    static void deleteFile(String name) {
        if (!files.containsKey(name)) {
            System.out.println("File not found");
            return;
        }

        int[] range = files.get(name);

        for (int i = range[0]; i <= range[1]; i++) {
            disk[i] = false;
        }

        files.remove(name);
        System.out.println("File deleted");
    }

    // 🔹 Display Disk
    static void displayDisk() {
        System.out.print("Disk: ");
        for (int i = 0; i < totalBlocks; i++) {
            System.out.print(disk[i] ? "1 " : "0 ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Allocate File");
            System.out.println("2. Delete File");
            System.out.println("3. Display Disk");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String name = sc.next();

                    System.out.print("Enter file size: ");
                    int size = sc.nextInt();

                    allocateContiguous(name, size);
                    break;

                case 2:
                    System.out.print("Enter file name to delete: ");
                    String del = sc.next();
                    deleteFile(del);
                    break;

                case 3:
                    displayDisk();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}