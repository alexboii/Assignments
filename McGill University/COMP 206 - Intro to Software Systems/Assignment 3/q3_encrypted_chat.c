/*
 * q3_encrypted_chat.c
 *
 *  Created on: Nov 8, 2016
 *      Author: Alex Bratyshkin 260684228
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include  <signal.h>
#define MAX_MESSAGE_LENGTH 1000

typedef enum {
	false, true
} bool;

bool send(char *file_name, char *send_message);
bool receive(char *file_name, int key);
bool check_termination();
void exit_handler(int sig);
char *encrypt(char *message, int key);
char *decrypt(char *message, int key);
bool is_number(char number[]);

char old_message[MAX_MESSAGE_LENGTH] = "";
char new_message[MAX_MESSAGE_LENGTH] = "";
char decrypt_message[MAX_MESSAGE_LENGTH] = "";

int main(int argc, char *argv[]) {

	char c, message[MAX_MESSAGE_LENGTH] = "";
	bool wait_for_message = false;

	// ARGUMENT CHECKER
	if (argc != 5) {
		printf("You must pass 4 arguments to the program\nExiting...\n");
		exit(0);
	}

	if(!is_number(argv[4])){
		printf("Your key is not a number!\nExiting...\n");
		exit(0);
	}

	// PROCESS SIGNAL HANDLING TO KNOW WHEN USER EXITS TERMINAL BY CLICKING CNTRL+C
	signal(SIGINT, exit_handler);

	char *filename_input = argv[1];
	char *filename_output = argv[2];
	char *username = argv[3];
	int key = strtol(argv[4], NULL, 0);

	// IF TERMINATION FILE EXISTS (WHICH IS ONLY CREATED ONCE ONE OF THE TERMINALS EXITS
	// THE CHAT SESSION), THEN DELETE IT
	FILE* termination_file = fopen("termination.txt", "r");

	if (termination_file) {
		remove("termination.txt");
	}

	FILE *input_file;
	input_file = fopen(filename_input, "r");

	// IF INPUT FILE DOESN'T CONTAIN ANYTHING, THEN NO MESSAGE WAS RECEIVED
	if (input_file == NULL) {
		printf("No message received\n");
	} else {
		// GET LAST MESSAGE
		fgets(new_message, sizeof(new_message), input_file);
		strcpy(old_message, new_message);
		strcpy(decrypt_message, decrypt(new_message, key));
		printf("Received: %s", decrypt_message);
		fclose(input_file);
	}

	// SEND MESSAGE
	wait_for_message = false;

	fflush(stdout);

	// WHILE THE CHAT SESSION HAS NOT BEEN TERMINATED
	while (check_termination()) {


		// IF IT'S THE USER'S TURN TO RECEIVE A MESSAGE
		if (wait_for_message == true) {
			wait_for_message = receive(filename_input, key);
		}

		// IF IT'S THE USER'S TURN TO SEND A MESSAGE
		if (wait_for_message == false) {
			printf("Send    : ");
			fflush(stdout);
			fflush(stdin);

			fgets(message, 1000, stdin);
			check_termination();

			char* final_message = malloc(
					strlen(message) + strlen(username) + 3);

			//CREATE FINAL STRING TO BE SENT
			strcpy(final_message, "[");
			strcat(final_message, username);
			strcat(final_message, "]");
			strcat(final_message, " ");
			strcat(final_message, message);

			//DO ENCRYPTION
			strcpy(final_message, encrypt(final_message, key));

			wait_for_message = send(filename_output, final_message);

		}

	}
}

// WRITE NEW MESSAGE TO OUTPUT FILE
bool send(char *file_name, char *send_message) {

	FILE *file = fopen(file_name, "w");

	if (file) {
		fprintf(file, "%s", send_message);
		fclose(file);
		// ONCE MESSAGE HAS BEEN SENT, SET TURN TO RECEIVE MESSAGE
		return true;
	} else {
		return false;
	}

}

// RECEIVE MESSAGE FROM
bool receive(char *file_name, int key) {
	FILE *input_file = fopen(file_name, "r");

	// IF FILE DNE, KEEP WAITING TO RECEIVE MESSAGE
	if (input_file) {
		fgets(new_message, sizeof(new_message), input_file);
		fclose(input_file);
	} else {
		return true;
	}

	// IF OLD MESSAGE IN THE FILE IS NOT EQUAL TO NEW ONE THAT'S BEEN SENT, THEN
	// SET TURN TO SEND MESSAGE
	if (strcmp(old_message, new_message)) {
		strcpy(old_message, new_message);
		strcpy(decrypt_message, decrypt(new_message, key));
		printf("Received: %s", decrypt_message);
		return false;
	}

	return true;

}

// CHECKS IF USER HAS ENTERED CONTROL+C IN THE TERMINAL (TERMINATION)
void exit_handler(int sig) {

	signal(sig, SIG_IGN);
	FILE *file = fopen("termination.txt", "w");
	fprintf(file, "terminate");
	fclose(file);
	exit(0);

}

// CHECKS FOR TERMINATION OF THE INPUT STREAM
bool check_termination() {
	char get_status[MAX_MESSAGE_LENGTH] = "";

	FILE *file = fopen("termination.txt", "r");
	// IF THE TERMINATION FILE HAS BEEN CREATED BY THE OTHER TERMINAL,
	// END CHAT SESSION
	if (file != NULL) {
		fgets(get_status, sizeof(get_status), file);
		if (strlen(get_status) != 0) {
			printf("Session terminated due to end of input stream.\n");
			remove("termination.txt");
			exit(0);
		}
		fclose(file);
	}

	// RETURN TRUE AS LONG AS CHAT SESSION HAS NOT BEEN TERMINATED
	return true;
}

char *decrypt(char *message, int key) {
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

	return message;
}

char *encrypt(char *message, int key) {
	int i, k;

	fflush(stdin);
	char c, *temp;

	int len = strlen(message);

	// SWITCH ALL CHARACTERS OF CHAR ARRAY BY GIVEN KEY
	for (i = 0; i < len; i++) {
		message[i] = message[i] + (key % 256);

	}

	// REVERSE CHAR ARRAY
	for (i = 0, k = len - 1; i < (len / 2); i++, k--) {
		temp = message[k];
		message[k] = message[i];
		message[i] = temp;
	}

	return message;
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
