LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    hello.cpp

LOCAL_SHARED_LIBRARIES := \
	libcutils \
	libutils  

LOCAL_MODULE := helloworld
LOCAL_MODULE_TAGS := optional

include $(BUILD_EXECUTABLE)
