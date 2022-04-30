#include <stdio.h>
#include <string.h>
#define BUFFER_SIZE 15

char str[11] = "speed test";

int main(int argc) {
	printf("Hello, world!\n");
	char buffer[BUFFER_SIZE];
	fgets(buffer, BUFFER_SIZE, stdin);
	printf("%s", buffer);
}