# This Android.mk is empty, and >> does not build subdirectories <<.
# Individual packages in experimental/ must be built directly with "mmm".
LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(call all-subdir-java-files)
LOCAL_PACKAGE_NAME := Hello
include $(BUILD_PACKAGE)