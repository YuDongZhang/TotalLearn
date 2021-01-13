package com.example.totallearn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.totallearn.zhujie.customzhujie.getViewTo;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    public static final String TAG = TestActivity.class.getSimpleName();

    @getViewTo(R.id.bt_1)
    private Button testBt1;

    @getViewTo(R.id.bt_2)
    private Button testBt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG,"onCreate");


       testThread();
        //通过注解生成view
        //getAllAnnotationView();
       // initData();
    }


    public void testThread(){
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initData() {
        // 每隔1s执行一次事件
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.i("接收数据", String.valueOf(aLong));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /**
     * 解析注解 , 获取控件
     *
     * 运行时的注解 , 通过反射来解析注入到数据中去
     *
     * 我们将注解描述在Activity的成员变量mTv和mBtn中，在App运行时，通过反射将findViewbyId得到的控件，注入到mTv和mBtn中。
     *
     * 是不是很熟悉，有点ButterKnife的味道？当然，ButterKnife比这个高级多，毕竟反射多了影响效率，不过我们明白了，
     * 可以通过注解来注入和创建对象，这样可以在一定程度节省代码量。
     */
    private void getAllAnnotationView(){
        //获得成员变量
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields){
            try{
                //判断注解
                if(field.getAnnotations() != null){
                    //确定注解的类型
                    if(field.isAnnotationPresent(getViewTo.class)){
                        //允许修改反射属性
                        field.setAccessible(true);
                        getViewTo getView = field.getAnnotation(getViewTo.class);
                        //findviewbyId 将注解的id ,找到 view 注入到成员变量中
                        field.set(this,findViewById(getView.value()));
                    }
                }
            }catch (Exception e){

            }
        }
    }

    /**
     *  编译时注解  : 简单总结,就是要编译处理器 , 还有编译处理器要扫描class
     *
     *运行时注解RUNTIME如上2.1所示，大多数时候实在运行时使用反射来实现所需效果，这很大程度上影响效率，如果BufferKnife
     * 的每个View注入不可能如何实现。实际上，ButterKnife使用的是编译时注解CLASS，如下图X2.2，是ButterKnife的@BindView注解，
     * 它是一个编译时注解，在编译时生成对应java代码，实现注入。
     *
     * 说到编译时注解，就不得不说注解处理器*** AbstractProcessor，如果你有注意，一般第三方注解相关的类库，如bufferKnike、ARouter，
     * 都有一个Compiler命名的Module，如下图X2.3*，这里面一般都是注解处理器，用于编译时处理对应的注解。
     * 注解处理器（Annotation Processor）是javac的一个工具，它用来在编译时扫描和处理注解（Annotation）。你可以对自定义注解，并注册
     * 相应的注解处理器，用于处理你的注解逻辑。

     */
    @BindView(R.id.test_tv1)
     TextView testTv1;

    /**
     * 如下所示，实现一个自定义注解处理器，至少重写四个方法，并且注册你的自定义Processor，详细可参考下方代码CustomProcessor。
     * 找到这个 CustomProcessor 这个类
     */

    /*
    @AutoService(Processor.class)，谷歌提供的自动注册注解，为你生成注册Processor所需要的格式文件（com.google.auto相关包）。
    init(ProcessingEnvironment env)，初始化处理器，一般在这里获取我们需要的工具类。
    getSupportedAnnotationTypes()，指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
    getSupportedSourceVersion() ，指定java版本。
    process()，处理器实际处理逻辑入口。

    @AutoService(Processor.class)
    public class CustomProcessor extends AbstractProcessor {

    /**
     * 注解处理器的初始化
     * 一般在这里获取我们需要的工具类
     * @param processingEnvironment 提供工具类Elements, Types和Filer
     */

    /*
    @Override
    public synchronized void init(ProcessingEnvironment env){
        super.init(env);
        //Element代表程序的元素，例如包、类、方法。
        mElementUtils = env.getElementUtils();

        //处理TypeMirror的工具类，用于取类信息
        mTypeUtils = env.getTypeUtils();

        //Filer可以创建文件
        mFiler = env.getFiler();

        //错误处理工具
        mMessages = env.getMessager();
    }

    /**
     * 处理器实际处理逻辑入口
     * @param set
     * @param roundEnvironment 所有注解的集合
     * @return
     */

    /*
    @Override
    public boolean process(Set<? extends TypeElement> annoations,
                           RoundEnvironment env) {
        //do someThing
    }

    //指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new LinkedHashSet<String>();

        //大部分class而已getName、getCanonicalNam这两个方法没有什么不同的。
        //但是对于array或内部类等就不一样了。
        //getName返回的是[[Ljava.lang.String之类的表现形式，
        //getCanonicalName返回的就是跟我们声明类似的形式。
        sets(BindView.class.getCanonicalName());

        return sets;
    }

    //指定Java版本，一般返回最新版本即可
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}





    1、遍历得到源码中，需要解析的元素列表。
    2、判断元素是否可见和符合要求。
    3、组织数据结构得到输出类参数。
    4、输入生成java文件。
    5、错误处理。

    然后，让我们理解一个概念：Element，因为它是我们获取注解的基础。
    Processor处理过程中，会扫描全部Java源码，代码的每一个部分都是一个特定类型的Element，它们像是XML一层的层级机构，
    比如类、变量、方法等，每个Element代表一个静态的、语言级别的构件，如下方代码所示。

    package android.demo; // PackageElement

    // TypeElement
    public class DemoClass {

        // VariableElement
        private boolean mVariableType;

        // VariableElement
        private VariableClassE m VariableClassE;

        // ExecuteableElement
        public DemoClass () {
        }

        // ExecuteableElement
        public void resolveData (Demo data   //TypeElement ) {
        }
    }

    其中，Element代表的是源代码，而TypeElement代表的是源代码中的类型元素，例如类。然而，TypeElement并不包含类本身的信息。
    你可以从TypeElement中获取类的名字，但是你获取不到类的信息，例如它的父类。这种信息需要通过TypeMirror获取。
    你可以通过调用elements.asType()获取元素的TypeMirror。
    1、知道了Element，我们就可以通过process 中的RoundEnvironment去获取，扫描到的所有元素，如下图X2.4，
    通过env.getElementsAnnotatedWith，我们可以获取被@BindView注解的元素的列表，其中validateElement校验元素是否可用。

具体你要看  注解使用 , 这个图片
 @Override
  public boolean process(Set<? extends TypeElement> an, RoundEnvironment env) {
    for (Element e : env.getElementsAnnotatedWith(BindView.class)) {
      // 检查元素是否是一个类
      if (ae.getKind() != ElementKind.CLASS) {
            ...
      }
   }
   ...
}


    2、因为env.getElementsAnnotatedWith返回的，是所有被注解了@ BindView的元素的列表。所以有时候我们还需要走一些额外的判断，比如，检查这些Element是否是一个类：
  @Override
  public boolean process(Set<? extends TypeElement> an, RoundEnvironment env) {
    for (Element e : env.getElementsAnnotatedWith(BindView.class)) {
      // 检查元素是否是一个类
      if (ae.getKind() != ElementKind.CLASS) {
            ...
      }
   }
   ...
}

    3、javapoet (com.squareup:javapoet)是一个根据指定参数，生成java文件的开源库，有兴趣了解javapoet的可以看下
    javapoet——让你从重复无聊的代码中解放出来，在处理器中，按照参数创建出 JavaFile之后，通Filer利用javaFile.writeTo(filer);
    就可以生成你需要的java文件。
    4、错误处理，在处理器中，我们不能直接抛出一个异常，因为在process()中抛出一个异常，会导致运行注解处理器的JVM崩溃，
    导致跟踪栈信息十分混乱。因此，注解处理器就有一个Messager类，一般通过messager.printMessage( Diagnostic.Kind.ERROR,
    StringMessage, element)即可正常输出错误信息。
    至此，你的注解处理器完成了所有的逻辑。可以看出，编译时注解实在编译时生成java文件，然后将生产的java文件注入到源码中，
    在运行时并不会像运行时注解一样，影响效率和资源。
    总结
    我们就利用ButterKnife的流程，简单举例做个总结吧。

    1、@BindView在编译时，根据Acitvity生产了XXXActivity$$ViewBinder.java。
    2、Activity中调用的ButterKnife.bind(this);，通过this的类名字，加$$ViewBinder，反射得到了ViewBinder，和编译
    处理器生产的java文件关联起来了，并将其存在map中缓存，然后调用ViewBinder.bind()。
    3、在ViewBinder的bind方法中，通过id，利用ButterKnife的butterknife.internal.Utils工具类中的封装方法，将findViewById()
    控件注入到Activity的参数中。





     */





}
