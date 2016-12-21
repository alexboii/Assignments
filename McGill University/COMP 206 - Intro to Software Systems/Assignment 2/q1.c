/*
 * Part1.c
 *
 *  Created on: Oct 12, 2016
 *      Author: Alex Bratyshkin 260684228
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

char *months[12];
char *days[7];

void day_display(char *day);
void month_display(char *month);

int main(int argc, char *argv[]) {

	char date[256], *day, *month, *number, *time, *time_zone, *year;
	int i = 0, j = 0;
	char *token;

	// ENSURES THAT A FILE HAS BEEN SPECIFIED
	if (argc != 2) {
		printf("You must specify file name!\n");
		return 1;
	}

	// ENSURES THAT A DATE HAS BEEN PASSED IN
	if (isatty(fileno(stdin))) {
		printf("You must pipe in a date!\n");
		//exit function, error code is 3
		return 1;
	}

	// SPLIT DATE INTO ITS COMPONENTS
	if (fgets(date, sizeof date, stdin)) {

		token = strtok(date, " ,.-");
		day = token;

		while (token != NULL) {

			i++;

			token = strtok(NULL, " ,.-");

			if (i == 1) {
				month = token;
			}
			if (i == 2) {
				number = token;
			}
			if (i == 3) {
				time = token;
			}
			if (i == 4) {
				time_zone = token;
			}
			if (i == 5) {
				year = token;
			}

		}

	}

	FILE *file_name;

	// CHECK THAT IT IS AN ACTUAL FILE, IF SO, PERFORM STANDARD OPERATIOS
	int isFile = access(argv[1], F_OK);

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
				token = strtok(line, " ,.-");
				for (i = 0; (i < sizeof(days)) && (token != NULL); i++) {
					int len = strlen(token);
					days[i] = malloc(len);
					strncpy(days[i], token, len);
					token = strtok(NULL, " ,.-\n");
				}
			}

			j++;
		}

		fclose(file_name);
	}else{
		printf("You must enter a proper file!");
		return 1;
	}

	// FINAL DISPLAY
	day_display(day);
	month_display(month);
	printf("%s %s %s %s", number, time, time_zone, year);

	return EXIT_SUCCESS;

}

// PRINT OUT THE APPROPRIATE DAY
void day_display(char *day) {
	if (strcmp("Sun", day) == 0) {
		day = days[0];
		printf("%s ", day);
	}
	if (strcmp("Mon", day) == 0) {
		day = days[1];
		printf("%s ", day);
	}
	if (strcmp("Tue", day) == 0) {
		day = days[2];
		printf("%s ", day);
	}
	if (strcmp("Wed", day) == 0) {
		day = days[3];
		printf("%s ", day);
	}
	if (strcmp("Thu", day) == 0) {
		day = days[4];
		printf("%s ", day);
	}
	if (strcmp("Fri", day) == 0) {
		day = days[5];
		printf("%s ", day);
	}
	if (strcmp("Sat", day) == 0) {
		day = days[6];
		printf("%s ", day);
	}
}

// PRINT OUT THE APPROPRIATE MONTH
void month_display(char *month) {
	if (strcmp("Jan", month) == 0) {
		month = months[0];
		printf("%s ", month);
	}
	if (strcmp("Feb", month) == 0) {
		month = months[1];
		printf("%s ", month);
	}
	if (strcmp("Mar", month) == 0) {
		month = months[2];
		printf("%s ", month);
	}
	if (strcmp("Apr", month) == 0) {
		month = months[3];
		printf("%s ", month);
	}
	if (strcmp("May", month) == 0) {
		month = months[4];
		printf("%s ", month);
	}
	if (strcmp("Jun", month) == 0) {
		month = months[5];
		printf("%s ", month);
	}
	if (strcmp("Jul", month) == 0) {
		month = months[6];
		printf("%s ", month);
	}
	if (strcmp("Aug", month) == 0) {
		month = months[7];
		printf("%s ", month);
	}
	if (strcmp("Sep", month) == 0) {
		month = months[8];
		printf("%s ", month);
	}
	if (strcmp("Oct", month) == 0) {
		month = months[9];
		printf("%s ", month);
	}
	if (strcmp("Nov", month) == 0) {
		month = months[10];
		printf("%s ", month);
	}
	if (strcmp("Dec", month) == 0) {
		month = months[11];
		printf("%s ", month);
	}

}
