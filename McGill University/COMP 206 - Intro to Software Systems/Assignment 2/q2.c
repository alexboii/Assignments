/*
 * q2.c
 *
 *  Created on: Oct 15, 2016
 *      Author: Alex Bratyshkin 260684228
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef enum {
	false, true
} bool;

const char *english_file = "english_labels.txt";
const char *french_file = "spanish_labels.txt";
const char *spanish_file = "french_labels.txt";
const int DAYS_IN_WEEK = 7;
const int DAYS_IN_MONTH = 30;
const int TOTAL_MONTHS = 12;
const int LAST_DAY = 6;
const int STARS_AND_SPACE_IN_DAY_FORMAT = 3;

char *months[12];
char *days[7];
int size_of_star_line;

int get_int_length(int integer);
bool is_number(char number[]);
void pad_by(int limit);
void print_calendar();
void print_starline();

int main(int argc, char *argv[]) {

	int i = 0;
	int j = 0;
	int k = 0;
	char *token;

	// MAKE SURE USER ENTERED APPROPRIATE AMOUNT OF ARGUMENTS
	if (argc != 4) {
		printf("You must enter the proper arguments!\n");
		return 1;
	}

	// MAKE SURE THAT SECOND AND THIRD ARGUMENT ARE NUMBERS
	if (!is_number(argv[2]) && !is_number(argv[3])) {
		printf("Enter right argument\n");
		return 1;
	}

	int DAY_SIZE = strtol(argv[2], NULL, 0);
	int DAY_OFFSET = argv[3][0] - '0'; // strtol(argv[2], NULL, 0);

	if (DAY_OFFSET > 7 || DAY_OFFSET < 1) {
		printf("Your number must less or equal to 7 and positive");
		return 1;
	}

	if (DAY_SIZE < 2) {
		printf("Your number must be greater than 1 and positive");
		return 1;
	}

	FILE *file_name;
	int isFile = access(argv[1], F_OK);
	// CHECK FOR FILES, ENSURE IT IS THE RIGHT ONE
	if (isFile == 0) {

		file_name = fopen(argv[1], "r");
		char line[100];

		// ENTER EACH OF THE TWO LINES INTO THEIR RESPECTIVE ARRAYS
		while (fgets(line, sizeof line, file_name) != NULL) {

			if (j == 1) {
				token = strtok(line, " ,.-");
				for (i = 0; (i < sizeof(months)) && (token != NULL); i++) {
					int len = strlen(token);
					months[i] = malloc(len);
					strncpy(months[i], token, len); // LEN - ARGV[2]
					token = strtok(NULL, " ,.-\n");
				}
			}
			if (j == 0) {

				token = strtok(line, " ,.-\n");
				for (i = 0; (i < sizeof(days)) && (token != NULL); i++) {
					int len = strlen(token);
					days[i] = malloc(len);
					strncpy(days[i], token, DAY_SIZE);
					token = strtok(NULL, " ,.-\n");
				}
			}

			j++;
		}

		fclose(file_name);

	} else {
		printf("Invalid file name\n");
		return 1;
	}

	size_of_star_line = DAYS_IN_WEEK
			* (STARS_AND_SPACE_IN_DAY_FORMAT + DAY_SIZE) - 1;

	print_calendar(DAY_SIZE, DAY_OFFSET);

	for (i = 0; (i < sizeof(months)) && (token != NULL); i++) {
		free(months[i]);
	}
	for (i = 0; (i < sizeof(days)) && (token != NULL); i++) {
		free(days[i]);
	}

}

// GET THE NUMBER OF DIGITS OF AN INT
int get_int_length(int integer) {
	int counter = 1;
	// COUNT HOW MANY OCCURENCES FROM 1 TO 9
	while (integer > 9) {
		counter++;
		integer /= 10;
	}
	return counter;
}

// CHECK IF IT'S A NUMBER
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

void print_starline() {
	int i = 0;
	for (i = 0; i < (size_of_star_line); i++) {
		printf("*");
	}
}

void pad_by(int limit) {
	int j = 0;
	for (j = 0; j <= (limit); j++) {
		printf(" ");
	}
}

void print_calendar(int DAY_SIZE, int DAY_OFFSET) {

	int month_counter = 0;
	int i = 0;
	int j = 0;
	int k = 0;

	while (month_counter < TOTAL_MONTHS) {

		// RESET STANDARD COUNTERS
		i = 0;
		j = 0;
		k = 0;

		if (DAY_OFFSET > 7) {
			DAY_OFFSET = 1;
		}
		int difference;

		// PRINT FIRST STARLINE
		print_starline();
		printf("*");

		// PRINT MONTH
		printf("\n* %s\n", months[month_counter]);

		// PRINT SECOND STARLINE
		print_starline();
		printf("*\n");

		// PRINT DAYS OF WEEK
		for (i = 0; i < DAYS_IN_WEEK; i++) {
			printf("*");
			if (strlen(days[i]) < DAY_SIZE && i != LAST_DAY) {
				// IF DAY'S LENGTH IS LESS THAN USER'S DESIRED DAY LENGTH
				// PAD BY APPROPRIATE AMOUNT OF SPACES
				printf(" %s", days[i]);
				difference = DAY_SIZE - strlen(days[i]);
				pad_by(difference);
			} else if (strlen(days[i]) >= DAY_SIZE && i != LAST_DAY) {
				printf(" %s ", days[i]);
			}

			// DO SPECIFIC PADDING FOR LAST DAY OF WEEK TO MAKE SURE WE GO ONTO NEXT LINE
			if (i == LAST_DAY) {
				printf(" %s\n", days[i]);
			}

		}

		// PRINT NEXT STARLINE
		print_starline();

		printf("*\n");
		int padded_days = 0;

		// PRINT OUT EMPTY DAYS BEFORE REACHING BEGINNING OF MONTH
		for (padded_days = 0; padded_days < (DAY_OFFSET - 1); padded_days++) {
			printf("* ");
			for (i = 0; i < DAY_SIZE; i++) {
				printf(" ");
			}
			printf(" ");

		}

		// START DISPLAYING DAYS FROM 0
		k = 0;

		int days_current_month_offset = DAYS_IN_MONTH + padded_days;
		while (padded_days < days_current_month_offset) {
			padded_days++;
			k++;

			// DISPLAY THE DAY
			printf("* %i", k);

			// PAD SPACES RELATIVE TO THE APPROPRIATE AMOUNT OF DIGITS IN YOUR INTEGER
			int n_digits = get_int_length(k);
			int pad_until = DAY_SIZE - n_digits;
			pad_by(pad_until);

			// PRINT NEW LINE EVERY TIME A WEEj ENDS
			int mod_day = padded_days % DAYS_IN_WEEK;
			if (mod_day == 0 && k != DAYS_IN_MONTH) {
				printf(" \n");
			}

			// PAD LAST DAYS OF THE MONTH
			if (k == DAYS_IN_MONTH && mod_day != 0) {
				difference = DAYS_IN_WEEK - (mod_day);
				for (i = 0; i < (difference); i++) {
					printf("*  ");
					pad_by(DAY_SIZE - 1);
				}
			}
		}

		printf("\n");
		// INCREASE OFFSET, INCREASE MONTH
		month_counter++;
		DAY_OFFSET++;
	}

}
