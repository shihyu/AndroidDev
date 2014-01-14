#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

int main() {
    JNIEnv* env;
    JavaVM* jvm;
    JavaVMInitArgs vm_args;
    JavaVMOption options[1];
    // launch Jvm
    options[0].optionString = "-Djava.class.path=."; // add user classes
    vm_args.version = JNI_VERSION_1_6; //JDK version.
    vm_args.options = options;
    vm_args.nOptions = 1;

    if (JNI_CreateJavaVM(&jvm, (void*) &env, &vm_args) < 0) {
        fprintf(stderr , "Launch JVM Error\n");
        exit(1);
    }

    // find the obj & method
    jclass my_class;
    jmethodID my_main;

    if (!(my_class = (*env)->FindClass(env, "MyJavaClass"))) {
        fprintf(stderr , "'Class' Not Found\n");
        exit(1);
    }

    if (!(my_main = (*env)->GetStaticMethodID(env, my_class , "main" ,  "([Ljava/lang/String;)V"))) {
        fprintf(stderr , "'main' Not Found\n");
    } else { // Call main function
        (*env)->CallStaticVoidMethod(env, my_class, my_main, NULL);
    }

    // finish
    (*jvm)->DestroyJavaVM(jvm);
    return 0;
}
