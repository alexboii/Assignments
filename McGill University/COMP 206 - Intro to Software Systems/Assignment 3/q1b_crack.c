/*
 * q1b_crack.c
 *
 *  Created on: Nov 6, 2016
 *      Author: Alex Bratyshkin 260684228
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#define MAX_MESSAGE_LENGTH 1000

typedef enum {
	false, true
} bool;

void decrypt(char *message);
bool char_check(char c);
char *reverse_string(char *str);

int main(int argc, char *argv[]) {
	int c, i, key;
	char message[MAX_MESSAGE_LENGTH];

	FILE *file;
	file = fopen(argv[1], "r");

	// CHECK ARGUMENT
	if (argc != 2) {
		printf("You must pass one argument to the program\nExiting...\n");
		exit(0);
	}

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
		decrypt(message);
		fclose(file);

	} else {
		printf("Can't read file");
	}

	exit(0);

}

void decrypt(char *message) {
	int i;
	int k = 0;

	char c, *temp, *hold_message;
	bool stop = false;
	int key = 0;
	int len = strlen(message);

	hold_message = malloc(len);

	// CHECK EACH OF THE STRING'S CHARACTERS FOR 255 DIFFERENT KEYS
	// IF ALL CHARACTERS IN THE STRING FALL WITHIN THE SPECIFIED RANGE OF
	// ASCII CHARACTERS, THEN WE KNOW THAT IT IS A GOOD MESSAGE.
	while (key < 256 && !stop) {

		key++;
		k = 0;
		for (i = 0; i < len; i++) {

			hold_message[i] = (message[i] - key);

			// CHECK IF CHARACTER BELONGS TO ASCII CHARACTER RANGE
			if (char_check(hold_message[i])) {
				k++;
				if (k == strlen(message)) {
					k = 0;
					stop = true;
				}
			}
		}
	}

	// IF MESSAGE WAS DECRYPTED
	if (stop) {
		// REVERSE STRING
		hold_message = reverse_string(hold_message);
		printf("\nKey: %i\nDecrypted message: %s\n", key, hold_message);
	// IF NOT DECRYPTED
	} else {
		printf("\nCould not crack message (Does not follow pattern specified in the assignment's instructions)\n");
	}

}


// CHECK IF IT FALLS WITHIN SPECIFIED ASCII RANGE
bool char_check(char c) {
	if (c < 91 && c > 64) {
		return true;
	}

	if (c < 123 && c > 96) {
		return true;
	}

	if (c == 32) {
		return true;
	}

	return false;
}

// REVERSE THE STRING
char *reverse_string(char *str) {
	char temp;
	size_t len = strlen(str) - 1;
	size_t stop = len / 2;
	size_t i, k;

	for (i = 0, k = len; i < stop; i++, k--) {
		temp = str[k];
		str[k] = str[i];
		str[i] = temp;
	}

	return str;
}
