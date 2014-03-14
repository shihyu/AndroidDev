CC=gcc
INCLUDE=-I/usr/lib/jvm/jdk1.6.0_45/include/ -I/usr/lib/jvm/jdk1.6.0_45/include/linux/
LIB=-L/usr/lib/jvm/jdk1.6.0_45/jre/lib/amd64/server/
CFLAGS= -ljvm

all:
	javac MyJavaClass.java
	$(CC) $(INCLUDE) $(LIB) c_to_java.c $(CFLAGS)     
clean:
	rm -f ./a.out  ./*.class
