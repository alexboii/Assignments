/*
 * q2_text_chat.c
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

bool send(char *file_name, char *username, char *send_message);
bool receive(char *file_name);
bool check_termination();
void exit_handler(int sig);

char old_message[MAX_MESSAGE_LENGTH] = " ";
char new_message[MAX_MESSAGE_LENGTH] = " ";

int main(int argc, char *argv[]) {

	char c, message[MAX_MESSAGE_LENGTH] = " ";
	bool wait_for_message = false;

	// ARGUMENT CHECKER
	if (argc != 4) {
		printf("You must pass 3 arguments to the program\nExiting...\n");
		exit(0);
	}

	// PROCESS SIGNAL HANDLING TO KNOW WHEN USER EXITS TERMINAL BY CLICKING CNTRL+C
	signal(SIGINT, exit_handler);

	char *filename_input = argv[1];
	char *filename_output = argv[2];
	char *username = argv[3];

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
		printf("Received   : %s", new_message);
		strcpy(old_message, new_message);
		fclose(input_file);
	}

	// SEND MESSAGE
	wait_for_message = false;

	fflush(stdout);

	// WHILE THE CHAT SESSION HAS NOT BEEN TERMINATED
	while (check_termination()) {

		// IF IT'S THE USER'S TURN TO RECEIVE A MESSAGE
		if (wait_for_message == true) {
			wait_for_message = receive(filename_input);
		}

		// IF IT'S THE USER'S TURN TO SEND A MESSAGE
		if (wait_for_message == false) {
			printf("Send    : ");
			fgets(message, 100, stdin);
			check_termination();
			wait_for_message = send(filename_output, username, message);
		}

	}
}

// WRITE NEW MESSAGE TO OUTPUT FILE
bool send(char *file_name, char *username, char *send_message) {

	FILE *file = fopen(file_name, "w");

	if (file) {
		fprintf(file, "[%s] %s", username, send_message);
		fclose(file);
		// ONCE MESSAGE HAS BEEN SENT, SET TURN TO RECEIVE MESSAGE
		return true;
	} else {
		return false;
	}

}

// RECEIVE MESSAGE FROM
bool receive(char *file_name) {
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
		printf("Received: %s", new_message);
		strcpy(old_message, new_message);
		return false;
	}

	return true;

}

// CHECKS IF USER HAS ENTERED CONTROL+C IN THE TERMINAL (TERMINATION)
void exit_handler(int sig) {

	signal(sig, SIG_IGN);
	// WRITE A FILE THAT IS CALLED TERMINATION, FROM WHICH THE OTHER CHAT SESSION
    // CONTINOUSLY CHECKS IF CHAT SESSION HAS BEEN TERMINATED OR NOT
	FILE *file = fopen("termination.txt", "w");
	fprintf(file, "terminate");
	fclose(file);
	// EXIT CURRENT CHAT SESSION
	exit(0);

}

// CHECKS FOR TERMINATION OF THE INPUT STREAM
bool check_termination() {
	char get_status[1000] = "";

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
