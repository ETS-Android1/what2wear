#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_what2wear_mvp_weatherInfo_WeatherInfoPresenterImpl_getWeatherKey(JNIEnv *env, jobject thiz) {
    return (*env)->  NewStringUTF(env, "e1b082fbc97814e810b880dbdc55c3ae");
}