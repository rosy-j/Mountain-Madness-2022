#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <math.h>

int count(char* mainstr) {  // Bug: You can add any letters after "speed" or "test" and it will count it as correct i.e speedo
    // Initialize and declare some stuff
    int i, j, len, count, ans;
    len = strlen(mainstr);
    char substr1[] = "speed";
    char substr2[] = "test";
    ans = 0;
    
    // convert the user input to lower case
    for(i=0; i<=len; i++) {
        mainstr[i] = tolower(mainstr[i]);
    }

    // search how many times the sub string appears in the main string
    for(i=0; i<=len; i++) {
        j=0;
        count = 0;
        if(mainstr[i] == 's') { // check if speed
            while(mainstr[i] == substr1[j]) {
                i++;
                j++;
                count++;
            }
            if(count == 5) {
                ans++;
            }
        }
        if(mainstr[i] == 't') { // check if test
            while(mainstr[i] == substr2[j]) {
                i++;
                j++;
                count++;
            }
            if(count == 4) {
                ans++;
            }
        }
    }
    
    // return answer
    return ans;
}

int main() {
    char haystack[200]; // using a buffer of 200 characters
    time_t start, end;
    int time_taken;
    char temp;

    printf("Type \"speed test\" as many times as you want as fast as you can\n");
    printf("Press Enter to start\n");
    scanf("%c", &temp);

    time(&start);
    fgets(haystack, 200, stdin);
    time(&end);
    //time_taken = (int)floor(difftime(end, start));

    //printf("You typed %d/min", count(haystack)/time_taken*60);
    printf("words: %d\n", count(haystack));
    printf("time taken: %.2f\n", difftime(end, start));
    printf("You typed %d words per min", (int)(60*count(haystack)/difftime(end, start)));
    return 0;
}