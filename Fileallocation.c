  le
char fileNames[MAX][20];
int fileBlocks[MAX][MAX];
int fileSizes[MAX];
char typeMap[MAX][20];
int fileCount = 0;

// 🔹 Display Disk#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100

int totalBlocks;
char disk[MAX][20];   // stores file name or empty

// File Tab
void displayDisk() {
    printf("\nDisk: ");
    for (int i = 0; i < totalBlocks; i++) {
        if (strlen(disk[i]) == 0)
            printf("- ");
        else
            printf("%s ", disk[i]);
    }
    printf("\n");
}

// 🔹 Show Files
void showFiles() {
    printf("\nFile Table:\n");
    for (int i = 0; i < fileCount; i++) {
        printf("%s (%s) -> ", fileNames[i], typeMap[i]);
        for (int j = 0; j < fileSizes[i]; j++) {
            printf("%d ", fileBlocks[i][j]);
        }
        printf("\n");
    }
}

// 🔹 Contiguous Allocation
void contiguous(char name[], int size) {
    int start = -1, count = 0;

    for (int i = 0; i < totalBlocks; i++) {
        if (strlen(disk[i]) == 0) {
            if (count == 0) start = i;
            count++;

            if (count == size) {
                printf("Allocated: ");
                for (int j = start; j < start + size; j++) {
                    strcpy(disk[j], name);
                    fileBlocks[fileCount][j - start] = j;
                    printf("%d ", j);
                }

                strcpy(fileNames[fileCount], name);
                strcpy(typeMap[fileCount], "Contiguous");
                fileSizes[fileCount] = size;
                fileCount++;
                printf("\n");
                return;
            }
        } else {
            count = 0;
        }
    }

    printf("Contiguous allocation failed\n");
}

// 🔹 Linked Allocation
void linked(char name[], int size) {
    int count = 0;

    printf("Allocated: ");
    for (int i = 0; i < totalBlocks && count < size; i++) {
        if (strlen(disk[i]) == 0) {
            strcpy(disk[i], name);
            fileBlocks[fileCount][count++] = i;
            printf("%d ", i);
        }
    }

    if (count < size) {
        printf("\nLinked allocation failed\n");
        return;
    }

    strcpy(fileNames[fileCount], name);
    strcpy(typeMap[fileCount], "Linked");
    fileSizes[fileCount] = size;
    fileCount++;

    printf("\n");
}

// 🔹 Indexed Allocation
void indexed(char name[], int size) {
    int count = 0;
    int blocks[MAX];

    for (int i = 0; i < totalBlocks && count < size + 1; i++) {
        if (strlen(disk[i]) == 0) {
            strcpy(disk[i], name);
            blocks[count++] = i;
        }
    }

    if (count < size + 1) {
        printf("Indexed allocation failed\n");
        return;
    }

    printf("Index Block: %d\n", blocks[0]);
    printf("Data Blocks: ");

    for (int i = 1; i < count; i++) {
        printf("%d ", blocks[i]);
        fileBlocks[fileCount][i - 1] = blocks[i];
    }

    strcpy(fileNames[fileCount], name);
    strcpy(typeMap[fileCount], "Indexed");
    fileSizes[fileCount] = size;
    fileCount++;

    printf("\n");
}

// 🔹 Main Function
int main() {
    int choice, type, size;
    char name[20];

    printf("Enter total blocks: ");
    scanf("%d", &totalBlocks);

    // Initialize disk
    for (int i = 0; i < totalBlocks; i++) {
        disk[i][0] = '\0';
    }

    while (1) {
        printf("\n1. Create File\n");
        printf("2. Display Disk\n");
        printf("3. Show Files\n");
        printf("4. Exit\n");

        scanf("%d", &choice);

        if (choice == 4) break;

        if (choice == 2) {
            displayDisk();
            continue;
        }

        if (choice == 3) {
            showFiles();
            continue;
        }

        printf("Choose Allocation:\n");
        printf("1. Contiguous\n");
        printf("2. Linked\n");
        printf("3. Indexed\n");

        scanf("%d", &type);

        printf("Enter file name: ");
        scanf("%s", name);

        printf("Enter size: ");
        scanf("%d", &size);

        switch (type) {
            case 1: contiguous(name, size); break;
            case 2: linked(name, size); break;
            case 3: indexed(name, size); break;
            default: printf("Invalid choice\n");
        }
    }

    return 0;
}