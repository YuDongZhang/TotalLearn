//
// Created by Shinelon on 2019/12/19.
//

#include "com_example_totallearn_ndkdemo_NDKTools.h"

JNIEXPORT jstring JNICALL Java_com_example_totallearn_ndkdemo_NDKTools_getStringFromNDK
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env,"Hellow World，这是隔壁老李头的NDK的第一行代码");
}