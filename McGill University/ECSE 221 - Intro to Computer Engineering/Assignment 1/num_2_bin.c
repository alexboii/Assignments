/*
 * Alexander Bratyshkin
 * 260684228
 * ECSE 221
 * 14-02-2016
 *
 */

#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

double d2b(double decimal, double fraction, int sigfigs); // method which will transform input to binary
double d2h(double decimal, double fraction, int sigfigs); // method which will transform input to hex

int displayDecimal[30], displayFraction[30]; // arrays holding fractional and decimal part of binary output
char displayDecimalHex[30], displayFractionHex[30]; // arrays holding fractional and decimal part of hex output
int maxInput = pow(2, 30) - 1; // maximum number that can be held in array of size 30

void set_std_buffer_off() {

	// this method, for some mystical reason, let's me display my program on the Eclipse console
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);
}

int main() {

	set_std_buffer_off();

	double input, fraction;
	int length;
	char inputCopy[32];

	printf("Input decimal number, or enter 0 to exit program \n");

	printf(">");

	if (scanf("%lf", &input) == 1) { // if the input is not a number...
		sprintf(inputCopy, "%g", input); // copies input into array inputCopy

		int n = strlen(inputCopy); // computes the length of the input

		if (input > 0 && input <= maxInput) { // checks if the input is positive and below maxInput

			if (strchr(inputCopy, '.') != NULL) { // inputCopy contains period, do not count it as a significant figure
				length = n - 1;
			} else {
				length = n;
			}

			fraction = input - (int) input; // gets the fractional part of the input

			d2b(input, fraction, length); // calls binary method
			d2h(input, fraction, length); // calls hex method

		}

		if (input == 0) { // checks if input is 0 to exit
			printf("Exiting...");
			exit(0);
		}

		if (input < 0 || input > maxInput) { // checks if input is negative or exceeds range of computation
			printf("Input is invalid! Exiting...");
		}
	} else { // if the input is not a number...
		printf("Your input is not a number! Exiting...");
		exit(0);
	}

}

double d2b(double decimal, double fraction, int sigfigs) {

	int i;

	int temp = trunc(decimal); // removes fractional part from decimal

	for (i = 0; i < 30; i++) { // calculates the fractional part of the binary

		fraction = fraction - trunc(fraction);
		displayFraction[i] = trunc(fraction * 2);
		fraction *= 2;

	}

	for (i = 30 - 1; i >= 0; i--) { // calculates the decimal part of the binary

		if (temp % 2 == 0) {
			displayDecimal[i] = 0;
		} else
			displayDecimal[i] = 1;

		temp /= 2;

	}

	int n = -1; // store number of leading zero's in array
	for (i = 0; i < 30; ++i) { // calculates amount of leading zero's and removes them
		n++;
		if (displayDecimal[i] != 0) {

			break;
		}
	}

	int sizeArray = sizeof(displayDecimal) / sizeof(displayDecimal[0]); // computes size of decimal array

	int figsDecimal = sizeArray - n; // computes length of decimal part of number

	for (; i < 30; i++) { // display decimal part of number
		printf("%c", displayDecimal[i] + '0');
	}

	int displaylength = (rint(sigfigs * (log(10)) / log(2))) - figsDecimal; // calculate remaining amount of significant figures to display in fractional array

	printf(".");

	for (i = 0; i < displaylength; i++) { // display fractional part of the number
		printf("%d", displayFraction[i]);
	}
	printf("\n");

	return 0;

}

double d2h(double decimal, double fraction, int sigfigs) {

	int i = 0;

	char hex[17] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', '\0' }; // story all hex numbers here

	int temp = trunc(decimal); // removes fractional part from decimal

	int j;

	for (j = 0; j < 60; j++) { // calculates the fractional part of the hex

		fraction = fraction - trunc(fraction);
		displayFractionHex[j] = hex[(int) (fraction * 16)];
		fraction *= 16;
	}

	for (i = sizeof(displayDecimalHex) - 1; i >= 0; i--) { // calculates the decimal part of the hex

		displayDecimalHex[i] = hex[temp % 16];
		temp /= 16;
	}

	int n;

	if ((n = strspn(displayDecimalHex, "0")) != 0
			&& displayDecimalHex[n] != '\0') { // remove all leading zero's from hex number
		printf("%s", &displayDecimalHex[n]);
	}

	int figsDecimal;

	figsDecimal = sizeof(displayDecimalHex) - n; // computes length of decimal part of number

	printf(".");

	int displaylength = (rint(sigfigs * (log(10)) / log(16))) - figsDecimal; // calculate remaining amount of significant figures to display in fractional array

	for (i = 0; i < displaylength; i++) { // display fractional part of the array
		printf("%c", displayFractionHex[i]);
	}

	return 0;

}
