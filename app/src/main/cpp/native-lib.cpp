#include <jni.h>
#include <android/log.h>
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, "libnav", __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , "libnav", __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO   , "libnav", __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN   , "libnav", __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , "libnav", __VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_com_reziena_user_reziena_11_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_reziena_user_reziena_11_MainActivity_Detect(JNIEnv *env, jobject instance) {

    // TODO

}

float resize(Mat img_src, Mat &img_resize, int resize_width){

    float scale = resize_width / (float)img_src.cols ;
    if (img_src.cols > resize_width) {
        int new_height = cvRound(img_src.rows * scale);
        resize(img_src, img_resize, Size(resize_width, new_height));
    }
    else {
        img_resize = img_src;
    }
    return scale;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_reziena_user_reziena_11_MainActivity_loadCascade(JNIEnv *env, jobject instance,
                                                          jstring cascadeFileName_){
    //    const char *cascadeFileName = env->GetStringUTFChars(cascadeFileName_, 0);


    const char *nativeFileNameString = env->GetStringUTFChars(cascadeFileName_, 0);

    string baseDir("/storage/emulated/0/");
    baseDir.append(nativeFileNameString);
    const char *pathDir = baseDir.c_str();


    jlong ret = 0;
    ret = (jlong) new CascadeClassifier(pathDir);
    if (((CascadeClassifier *) ret)->empty()) {
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                            "CascadeClassifier로 로딩 실패  %s", nativeFileNameString);
    }

    else
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                            "CascadeClassifier로 로딩 성공 %s", nativeFileNameString);

    env->ReleaseStringUTFChars(cascadeFileName_, nativeFileNameString);

    return ret;

//    env->ReleaseStringUTFChars(cascadeFileName_, cascadeFileName);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_reziena_user_reziena_11_MainActivity_loadImage(JNIEnv *env, jobject instance,
                                                        jstring imageFileName_, jlong img,
                                                        jlong cascadeClassifier_face)  {

    Mat &img_input = *(Mat *) img;

    const char *nativeFileNameString2 = env->GetStringUTFChars(imageFileName_, JNI_FALSE);

    string baseDir2("/storage/emulated/0/");
    baseDir2.append(nativeFileNameString2);
    const char *pathDir = baseDir2.c_str();

    __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                        "CascadeClassifier로 로딩 실패  %s", pathDir);

    cv::Mat frame, tmpImg;
    cv::Mat Image;
    cv::Mat gray;
    cv::Mat finalImage;
    Mat &srcImage = *(Mat *) img;
    img_input = imread(pathDir, IMREAD_COLOR);




    Mat img_resize;
    float resizeRatio = resize(img_input, img_resize, 640);
    flip(img_resize, img_resize, 0);

    cv::Mat contours, facegray;
    Mat sobel, SobelX, SobelY;
    Mat crop;
    vector<Vec4i> lines;
    Mat houghImg;
    int minCr = 133;
    int maxCr = 173;
    int minCb = 77;
    int maxCb = 127;
    int ret = 0 ;


        std::vector<Rect> faces;
        Mat frame_gray;
        cvtColor(img_input, frame_gray, COLOR_BGR2GRAY);
        equalizeHist(frame_gray, frame_gray);
        //-- Detect faces
    LOGE("확인용로그");
    ((CascadeClassifier *) cascadeClassifier_face)->detectMultiScale( img_resize, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, Size(30, 30) );
    __android_log_print(ANDROID_LOG_DEBUG, (char *) "native-lib :: ", (char *) "face %d found ", faces.size());

    if(faces.size()==1) {

        for (int i = 0; i < faces.size(); i++) {
            double real_facesize_x = faces[i].x / resizeRatio;
            double real_facesize_y = faces[i].y / resizeRatio;
            double real_facesize_width = faces[i].width / resizeRatio;
            double real_facesize_height = faces[i].height / resizeRatio + faces[i].height / 9;
            Rect face_area(real_facesize_x, real_facesize_y, real_facesize_width,
                           real_facesize_height);
            rectangle(srcImage, face_area, Scalar(0, 255, 0), 2);
            LOGE("시발로마", faces.size());
            crop = srcImage(face_area);
        }

        Mat YCrCb;
        cvtColor(crop, YCrCb, COLOR_BGR2YCrCb);

        LOGE("확인용로그1");

        //각 채널별로 분리
        vector<Mat> planes;
        split(YCrCb, planes);
        LOGE("확인용로그2");

        Mat mask = (minCr < planes[1]) & (planes[1] < maxCr) & (minCb < planes[2]) &
                   (planes[2] < maxCb);
        erode(mask, mask, Mat(3, 3, CV_8U, Scalar(1)), Point(-1, -1), 2);

        //Mat mask = getHandMask2(crop);
        LOGE("확인용로그3");

        Mat faceImage(crop.size(), CV_8UC3, Scalar(0));
        LOGE("확인용로그4");

        add(crop, Scalar(0), faceImage, mask);
        LOGE("확인용로그5");

        //flip(Image, finalImage, 1);

        //Canny(faceImage, contours, 50, 100,3);

        cvtColor(faceImage, facegray, COLOR_BGR2GRAY);
        LOGE("확인용로그6");
        Sobel(facegray, SobelX, CV_16S, 1, 0);
        Sobel(facegray, SobelY, CV_16S, 0, 1);
        sobel = abs(SobelX) + abs(SobelY);
        double sobelmin, sobelmax;
        minMaxLoc(sobel, &sobelmin, &sobelmax);
        LOGE("확인용로그7");


        sobel.convertTo(sobel, CV_8U, -255 / sobelmax, 255);
        threshold(sobel, contours, 220, 255, THRESH_BINARY);
        Canny(contours, contours, 50, 200);
        //cv::erode(contours, contours, cv::Mat());
        LOGE("확인용로그8");

        int minLineLength = 100;
        int maxLineGap = 0;
        int threshold = 30; // r,θ 평면에서 몇개의 곡선이 한점에서 만났을 때 직선으로 판단할지에 대한 최소값
        HoughLinesP(contours, lines, 1, CV_PI / 180, threshold);
        LOGE("확인용로그9");


        houghImg = faceImage.clone();
        LOGE("확인용로그10");

        for (size_t i = 0; i < lines.size(); i++) {
            Vec4i l = lines[i];
            line(houghImg, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(0, 255, 0), 1, LINE_AA);
        }
        LOGE("확인용로그11");


        ret = lines.size();
    }
    else{
        ret =0;
    }

        return ret;

        //imshow("original_image", finalImage);


    //env->ReleaseStringUTFChars(imageFileName_, imageFileName);

}

extern "C"
JNIEXPORT jint JNICALL
Java_com_reziena_user_reziena_11_MainActivity_loadFace(JNIEnv *env, jobject instance,
                                                       jstring imageFileName_, jlong img,
                                                       jlong cascadeClassifier_face) {
    Mat &img_input = *(Mat *) img;

    const char *nativeFileNameString2 = env->GetStringUTFChars(imageFileName_, JNI_FALSE);

    string baseDir2("/storage/emulated/0/");
    baseDir2.append(nativeFileNameString2);
    const char *pathDir = baseDir2.c_str();

    __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                        "CascadeClassifier로 로딩 실패  %s", pathDir);

    cv::Mat frame, tmpImg;
    cv::Mat Image;
    cv::Mat gray;
    cv::Mat finalImage;
    Mat &srcImage = *(Mat *) img;
    img_input = imread(pathDir, IMREAD_COLOR);




    Mat img_resize;
    float resizeRatio = resize(img_input, img_resize, 640);
    flip(img_resize, img_resize, 0);

    cv::Mat contours, facegray;
    Mat sobel, SobelX, SobelY;
    Mat crop;
    vector<Vec4i> lines;
    Mat houghImg;
    int minCr = 133;
    int maxCr = 173;
    int minCb = 77;
    int maxCb = 127;
    int ret = 0 ;


    std::vector<Rect> faces;
    Mat frame_gray;
    cvtColor(img_input, frame_gray, COLOR_BGR2GRAY);
    equalizeHist(frame_gray, frame_gray);
    //-- Detect faces
    LOGE("확인용로그");
    ((CascadeClassifier *) cascadeClassifier_face)->detectMultiScale( img_resize, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, Size(30, 30) );
    __android_log_print(ANDROID_LOG_DEBUG, (char *) "native-lib :: ", (char *) "face %d found ", faces.size());

    int face = faces.size();

    return face;
}