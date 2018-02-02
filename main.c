//
//  main.c
//  generate
//
//  Created by FRAU Antoine on 02/02/2018.
//  Copyright © 2018 Frau Antoine. All rights reserved.
//

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define RAND_MAX 10
int main(void) {
    char * s[10];
    srand(time(0));
    static const char alphanum[] =     "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < 10; ++i) {
        s[i] = alphanum[rand() % (sizeof(alphanum) - 1)];
        printf("%c", s[i]);
    }
    s[10] = 0;
}


