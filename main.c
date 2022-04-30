#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>
#include <time.h>

#define BUFFER_SIZE 15
#define NS_PER_S 1000000000L

char str[12] = "speed test\n";

void sub_timespec(struct timespec *start, struct timespec *end, struct timespec *td)
{
	td->tv_nsec = end->tv_nsec - start->tv_nsec;
	td->tv_sec = end->tv_sec - start->tv_sec;
	if (td->tv_sec > 0 && td->tv_nsec < 0)
	{
		td->tv_nsec += NS_PER_S;
		td->tv_sec--;
	}
	else if (td->tv_sec < 0 && td->tv_nsec > 0)
	{
		td->tv_nsec -= NS_PER_S;
		td->tv_sec++;
	}
}

int main() {
	char buffer[BUFFER_SIZE];
	struct timespec start, end, delta;

	printf("Starting speed test in:\n3\n");
	sleep(1);
	printf("2\n");
	sleep(1);
	printf("1\n");
	sleep(1);
	printf("GO! Type 'speed test' as fast as you can!\n");

	clock_gettime(CLOCK_REALTIME, &start);
	
	fgets(buffer, BUFFER_SIZE, stdin);

	clock_gettime(CLOCK_REALTIME, &end);

	//makes everything lowercase for consistency
	for (int i = 0; str[i]; i++) {
		buffer[i] = tolower(buffer[i]);
	}

	sub_timespec(&start, &end, &delta);

	printf("Finished in %d.%d seconds.\n", (int) delta.tv_sec, (int) delta.tv_nsec / 1000000L);
	if (strcmp(str, buffer) != 0) {
		printf("bruh.");
	}
}