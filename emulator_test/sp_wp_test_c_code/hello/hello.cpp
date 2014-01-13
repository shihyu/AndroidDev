#include <stdio.h>
#include "hello.h"

int main(int argc, char** argv)
{
    sp<WPTest> a = new WPTest(); 
    printf("Count: %d.\n", a->getStrongCount());  
    return 0;
}
