#ifndef __HELLOWORLD_H
#define __HELLOWORLD_H

#include <utils/RefBase.h>
#include <stdio.h>

using namespace android;
using namespace std;

class WPTest : public RefBase {
public:
    WPTest(){
        printf("WPTest()\n");
        sp_AA = new AA;
    }
    virtual ~WPTest() {
        printf("~WPTest\n");
    }
    
 
    virtual void onFirstRef() {
    }
 
    virtual void onLastStrongRef(const void* id) {
    }
 
    virtual void onLastWeakRef(const void* id) {
    }

    class AA : public RefBase {
        public:

        AA(){
        
            printf("AA()\n");
        }

        ~AA(){
        
            printf("~AA()\n");
        }
    };

private:
    sp<AA> sp_AA;
};

#endif 
