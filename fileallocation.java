import java.util.*;

class FileAllocation {

    static int totalBlocks = 20;
    static String[] disk = new String[totalBlocks];

    // 🔹 Contiguous Allocation
    static void allocateContiguous(String name, int size) {
        int start = -1, count = 0;

        for (int i = 0; i < totalBlocks; i++) {
            if (disk[i] == null) {
                if (count == 0) start = i;
                count++;

                if (count == size) {
                    for (int j = start; j < start + size; j++) {
                        disk[j] = name;
                    }
                    System.out.println(name + " allocated from block " + start + " to " + (start + size - 1));
                    return;
                }
            } else {
                count = 0;
            }
        }

        System.out.println("Contiguous allocation failed");
    }

    // 🔹 Linked Allocation
    static void allocateLinked(String name, int size) {
        List<Integer> blocks = new ArrayList<>();

        for (int i = 0; i < totalBlocks && blocks.size() < size; i++) {
            if (disk[i] == null) {
                disk[i] = name;
                blocks.add(i);
            }
        }

        if (blocks.size() < size) {
            System.out.println("Linked allocation failed");
            return;
        }

        System.out.println(name + " allocated at blocks: " + blocks);
    }

    // 🔹 Indexed Allocation
    static void allocateIndexed(String name, int size) {
        List<Integer> blocks = new ArrayList<>();

        for (int i = 0; i < totalBlocks && blocks.size() < size + 1; i++) {
            if (disk[i] == null) {
                disk[i] = name;
                blocks.add(i);
            }
        }

        if (blocks.size() < size + 1) {
            System.out.println("Indexed allocation failed");
            return;
        }

        int indexBlock = blocks.get(0);
        List<Integer> fileBlocks = blocks.subList(1, blocks.size());

        System.out.println(name + " index block: " + indexBlock);
        System.out.println(name + " data blocks: " + fileBlocks);
    }

    // 🔹 Display Disk + Count
    static void displayDisk() {
        int free = 0, used = 0;

        System.out.print("Disk: ");
        for (int i = 0; i < totalBlocks; i++) {
            if (disk[i] == null) {
                System.out.print("- ");
                free++;
            } else {
                System.out.print(disk[i] + " ");
                used++;
            }
        }

        System.out.println();
        System.out.println("Total Blocks: " + totalBlocks);
        System.out.println("Used Blocks: " + used);
        System.out.println("Free Blocks: " + free);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Contiguous Allocation");
            System.out.println("2. Linked Allocation");
            System.out.println("3. Indexed Allocation");
            System.out.println("4. Display Disk");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 5) break;

            if (choice == 4) {
                displayDisk();
                continue;
            }

            System.out.print("Enter file name: ");
            String name = sc.next();

            System.out.print("Enter file size: ");
            int size = sc.nextInt();

            switch (choice) {
                case 1:
                    allocateContiguous(name, size);
                    break;
                case 2:
                    allocateLinked(name, size);
                    break;
                case 3:
                    allocateIndexed(name, size);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}