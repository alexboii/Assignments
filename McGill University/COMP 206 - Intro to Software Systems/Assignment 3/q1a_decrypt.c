/*
 * q1.c
 *
 *  Created on: Nov 6, 2016
 *      Author: Alex Bratyshkin 260684228
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
	false, true
} bool;

void decrypt(char *message, int key);
bool is_number(char number[]);

int main(int argc, char *argv[]) {
	int c, i, key;

	// ARGUMENT CHECKERS
	if (argc != 3) {
		printf("You must pass two arguments to the program\nExiting...\n");
		exit(0);
	}

	if (!is_number(argv[1])) {
		printf("Your key is not a number!\nExiting...\n");
		exit(0);
	}

	// CONVERT ARGUMENT TO INT KEY
	key = strtol(argv[1], NULL, 0);
	char message[1000];

	// OPEN FILE
	FILE *file;
	file = fopen(argv[2], "r");

	// IF FILE EXISTS
	if (file) {
		i = 0;
		// READ ALL CHARACTERS IN THE FILE AND STORE IN CHAR ARRAY
		while ((c = getc(file)) != EOF) {
			message[i] = c;
			i++;
		}

		printf("\nOriginal message: %s", message);
		//DECRYPT MESSAGE
		decrypt(message, key);
		fclose(file);
	} else {
		// IF FILE DNE
		printf("Can't read file");
		exit(0);
	}

}

bool is_number(char number[]) {

	int i = 0;

	// CHECK IF NEGATIVE NUMBER
	if (number[0] == '-')
		return false;
	for (; number[i] != 0; i++) {
		// CHECK IF BETWEEN 9 AND 10
		if (!isdigit(number[i]))
			return false;
	}
	return true;
}

void decrypt(char *message, int key) {
	int i, k;

	char c, *temp;

	int len = strlen(message);

	// SWITCH ALL CHARACTERS OF CHAR ARRAY BY GIVEN KEY
	for (i = 0; i < len; i++) {
		message[i] = message[i] - key;

	}

	// REVERSE CHAR ARRAY
	for (i = 0, k = len - 1; i < (len / 2); i++, k--) {
		temp = message[k];
		message[k] = message[i];
		message[i] = temp;
	}

	// PRINT DECRYPTED MESSAGE
	printf("\n\nDecrypted message: %s\n", message);
	exit(0);

}

