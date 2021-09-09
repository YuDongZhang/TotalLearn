package com.example.totallearn.fragmentset.frag09;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

public class Density {

//    修改density，scaleDensity, densityDpi值-直接更改系统内部对于目标尺寸而言的像素密度。
    //这里相当于设计师给的dp
    private static final float  WIDTH = 320;//参考设备的宽，单位是dp 320 / 2 = 160

    private static float appDensity;//表示屏幕密度
    private static float appScaleDensity; //字体缩放比例，默认appDensity

    public static void setDensity(final Application application, Activity activity){
        //获取当前app的屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0){
            //初始化赋值操作
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            //添加字体变化监听回调  //用户在设置中修改字体也是可以改变的
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生更改，重新对scaleDensity进行赋值
                    if (newConfig != null && newConfig.fontScale > 0){
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //计算目标值density, scaleDensity, densityDpi
        float targetDensity = displayMetrics.widthPixels / WIDTH; // 1080 / 360 = 3.0  当运行在宽度为1080的设备上的时候,缩放的比例就是3.0
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);//字体缩放比例
        int targetDensityDpi = (int) (targetDensity * 160);//屏幕像素密度,得到目标值来进行替换,操作

        //替换Activity的density, scaleDensity, densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }

}

/*
第一种 : 需要多界面进行操作 , 所有的都来继承 base activity
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(),this);
    }
}

第二种 : 在 application 使用

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
        //每当一个 activity 启动都会回调他对应的方法
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Density.setDensity(App.this, activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
}



 */
