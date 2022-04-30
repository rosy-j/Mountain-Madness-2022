#include <stdio.h>
#include <string.h>
#include <ctype.h>

int count(char* mainstr, char* substr) {
    // Initialize and declare some stuff
    int i, j, len1, len2, count, ans;
    len1 = strlen(mainstr);
    len2 = strlen(substr);
    ans = 0;

    // if substring is bigger than main string then it can't be in the main string
    if(len2 > len1) {
        return 0;
    }
    
    // convert the user input to lower case
    for(i=0; i<=len1; i++) {
        mainstr[i] = tolower(mainstr[i]);
    }

    // search how many times the sub string appears in the main string
    for(i=0; i<=len1; i++) {
        j=0;
        count = 0;
        while(mainstr[i] == substr[j]) {
            i++;
            j++;
            count++;
        }
        if(count == strlen(substr)) {
            ans++;
        }
    }
    
    // return answer
    return ans;
}

int main() {
    char haystack[100]; // using temp variable 100 for now
    char needle[] = "speed test";
    printf("Enter a string\n");
    fgets(haystack, 100, stdin);

    printf("%s appeared %d times in %s", needle, count(haystack, needle), haystack);
    return 0;

}