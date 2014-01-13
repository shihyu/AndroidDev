如果只是修改的文件，那git diff产生的差异文件包放在hello-android.patch、hello-kernel.patch
不包括新增文件的被修改文件完整源码放在<差异文件完整源码包>目录

android增加文件：
#       external/hello/Android.mk
#       external/hello/hello.c
#       frameworks/base/core/java/android/os/IHelloService.aidl
#       frameworks/base/services/java/com/android/server/HelloService.java
#       frameworks/base/services/jni/com_android_server_HelloService.cpp
#       hardware/libhardware/include/hardware/hello.h
#       hardware/libhardware/modules/hello/Android.mk
#       hardware/libhardware/modules/hello/hello.c
#       packages/experimental/ExerciseHello/
packages/experimental/ExerciseHello
├── AndroidManifest.xml
├── Android.mk
├── res
│?? ├── drawable
│?? │?? └── icon.png
│?? ├── layout
│?? │?? └── main.xml
│?? └── values
│??     └── strings.xml
└── src
    └── luo
        └── hello
            └── Hello.java

android修改文件：
#       modified:   frameworks/base/Android.mk
#       modified:   frameworks/base/services/java/com/android/server/SystemServer.java
#       modified:   frameworks/base/services/jni/Android.mk
#       modified:   frameworks/base/services/jni/onload.cpp

kernel增加文件：
#       drivers/hello/
drivers/hello/
├── hello.c
├── hello.h
├── Kconfig
└── Makefile

kernel修改文件：
#       modified:   drivers/Kconfig
#       modified:   drivers/Makefile