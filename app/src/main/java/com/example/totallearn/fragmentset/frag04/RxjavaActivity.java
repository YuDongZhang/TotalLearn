package com.example.totallearn.fragmentset.frag04;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.adapter.Frag09Adapter;
import com.example.totallearn.fragmentset.frag04.f4entity.JokeEntity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*
 * https://www.jianshu.com/p/0cd258eecf60

 * 通过 setOnClickListener() 方法，Button 持有 OnClickListener 的引用（这一过程没有在图上画出）；当用户点击时，
 * Button 自动调用 OnClickListener 的 onClick() 方法。另外，如果把这张图中的概念抽象出来（Button -> 被观察者、OnClickListener ->
 * 观察者、setOnClickListener() -> 订阅，onClick() -> 事件），就由专用的观察者模式（例如只用于监听控件点击）转变成了通用的观察者模式。

 * 有人可能会注意到， subscribe() 这个方法有点怪：它看起来是『observalbe 订阅了 observer / subscriber』而不是『observer / subscriber
 * 订阅了 observalbe』，这看起来就像『杂志订阅了读者』一样颠倒了对象关系。这让人读起来有点别扭，不过如果把 API 设计成
 * observer.subscribe(observable) / subscriber.subscribe(observable) ，虽然更加符合思维逻辑，但对流式 API 的设计就造成影响了，
 * 比较起来明显是得不偿失的。

 * observeable subscribe() observer 不支持背压

 * flowable  subscribe() subscriber 支持背压(告诉上游降低发送速度)  响应式编程是一种基于异步数据流概念的编程模式。
 * 数据流就像一条河：它可以被观测，被过滤，被操作，或者为新的消费者与另外一条流合并为一条新的流。

 * subcribeon 指定上游的 , observeron 指定下游的

 * 常用的操作符有 : map , flatmap , concat , zip , interval
 * map对于对象的变换,一对一
 * flatmap依次请求 , 一对多变换 . flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables
 * 然后将它们发射的数据合并后放到一个单独的 Observable，利用这个特性，我们很轻松地达到了我们的需求。

 * concat 缓存
 * zip 合并
 * interval 心跳循环

 * Func1 和 Action 的区别在于， Func1 包装的是有返回值的方法。另外，和 ActionX 一样， FuncX 也有多个，
 * 用于不同参数个数的方法。FuncX 和 ActionX 的区别在 FuncX 包装的是有返回值的方法。

 * rxjava 如何实现线程变换的
 * subscribeon() 看到这句 source.subscribe(parent)，是不是觉得似曾相识呢？
 * SubscribeTask 实现了是Runnable接口，在其run方法中，定义了一个需要在线程中执行的任务。按照类的继承关系，
 * 很明显source 就是ObservableSubscribeOn 的上游Observable，parent是一个Observer。也就是说这个run方法要执行
 * 的内容就是实现ObservableSubscribeOn的上游和Observer的订阅。一旦某个线程执行了这个Runnable（SubscribeTask），
 * 就会触发了这个run方法，从而实现订阅，而一旦这个订阅实现，那么后面的流程就是上节所说的事情了。
 * 这里可以解答第三个问题了，上游事件是怎么给弄到子线程里去的，这里很明显了，就是直接把订阅方法放在了一个Runnable中去执行，
 * 这样就一旦这个Runnable在某个子线程执行，那么上游所有事件只能在这个子线程中执行了。
 */

public class RxjavaActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String[] data = {
            "0. 测试方法一",
            "1. 测试方法二",
            "2. 测试方法三",
            "3. 测试方法四"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
        Frag09Adapter adapter = new Frag09Adapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Frag09Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        testCreate();
                        break;
                    case 1:
                        testCreate_2();
                        break;
                    case 2:
                        test3();
                        break;
                    case 3:
                        test4();
                        break;
                }
            }
        });

    }


    private void testCreate() {//记忆 observable  subscribe  observer
        Observable.create(new ObservableOnSubscribe<Integer>() { //第一步：初始化Observable
            @Override //，回调的是 ObservableEmitter  作者在文章中称呼这个为回调
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {// 第三步：订阅

            // 我们的 RxJava 2.x 也为我们保留了简化订阅方法，我们可以根据需求，进行相应的简化订阅，只不过传入对象改为了 Consumer。
            //Consumer 即消费者，用于接收单个值，BiConsumer 则是接收两个值，Function 用于变换对象，Predicate 用于判断。
            // 这些接口命名大多参照了 Java 8 ，熟悉 Java 8 新特性的应该都知道意思，这里也不再赘述。

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable; //Disposable    用于解除订阅

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
                Log.d(TAG, "onNext: " + i);//可以看结果虽然一直在发送但是不接收了
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");
            }
        });


    }


    @SuppressLint("CheckResult")
    private void testCreate_2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                //subscribeOn 用于指定 subscribe() 时所发生的线程，从源码角度可以看出，内部线程调度是通过 ObservableSubscribeOn来实现的。
                //ObservableSubscribeOn 的核心源码在 subscribeActual 方法中，通过代理的方式使用 SubscribeOnObserver 包装 Observer 后，
                // 设置 Disposable 来将 subscribe 切换到 Scheduler 线程中。

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //observeOn 方法用于指定下游 Observer 回调发生的线程。
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());
                    }
                });


    }/*简单地说，subscribeOn() 指定的就是发射事件的线程，observerOn 指定的就是订阅者接收事件的线程。
        多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。
        但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。

        实例代码中，分别用 Schedulers.newThread() 和 Schedulers.io() 对发射线程进行切换，并采用
         observeOn(AndroidSchedulers.mainThread() 和 Schedulers.io() 进行了接收线程的切换。可以看到输出中
         发射线程仅仅响应了第一个 newThread，但每调用一次 observeOn() ，线程便会切换一次，因此如果我们有类似的需求时，便知道如何处理了。

        RxJava 中，已经内置了很多线程选项供我们选择，例如有：

        Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作；
        Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作；
        Schedulers.newThread() 代表一个常规的新线程；
        AndroidSchedulers.mainThread() 代表Android的主线程

        作者：nanchen2251
        链接：https://www.jianshu.com/p/0cd258eecf60
        来源：简书
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */


    //map 可以用于数据的多次的操作 , 比如你要先解析 , 再存储 , 再展示等等
    private void test3() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> e) throws Exception {
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder()
                        .url("https://api.apiopen.top/getJoke")
                        .get()
                        .build();

                Call call = client.newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, JokeEntity>() {
            @Override
            public JokeEntity apply(Response response) throws Exception {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.e(TAG, "map:转换前:" + response.body());
                        return new Gson().fromJson(body.string(), JokeEntity.class);
                    }

                }

                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<JokeEntity>() {
                    @Override
                    public void accept(JokeEntity jokeEntity) throws Exception {
                        Log.e(TAG, "doOnNext: 保存成功：" + jokeEntity.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JokeEntity>() {
                    @Override
                    public void accept(JokeEntity jokeEntity) throws Exception {
                        Log.e(TAG, "成功:" + jokeEntity.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    }
                });

            /*
            想必大家都知道，很多时候我们在使用 RxJava 的时候总是和 Retrofit 进行结合使用，而为了方便演示，这里我们就暂且采用 OkHttp3
            进行演示，配合 map，doOnNext ，线程切换进行简单的网络请求：
            1）通过 Observable.create() 方法，调用 OkHttp 网络请求；
            2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
            3）通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作；
            4）调度线程，在子线程中进行耗时操作任务，在主线程中更新 UI ；
            5）通过 subscribe()，根据请求成功或者失败来更新 UI 。

            作者：nanchen2251
            链接：https://www.jianshu.com/p/0cd258eecf60
            来源：简书
            著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
             */
    }


    // concat  你先读取缓存 , 如果缓存的数据不使用 , 再请求  , 这个是避免浪费资源
    private void test4() {

        /*Observable<JokeEntity> cache = Observable.create(new ObservableOnSubscribe<JokeEntity>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<JokeEntity> e) throws Exception {
                Log.e(TAG, "create当前线程:" + Thread.currentThread().getName());
                //本来在缓存中这个是不删除的
                //  JokeEntity data = CacheManager.getInstance().getFoodListData();

                // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
                if (data != null) { // 如果缓存数据不为空，则直接读取缓存数据，而不读取网络数据
                    isFromNet = false;
                    Log.e(TAG, "\nsubscribe: 读取缓存数据:");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRxOperatorsText.append("\nsubscribe: 读取缓存数据:\n");
                        }
                    });

                    e.onNext(data);
                } else {
                    isFromNet = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRxOperatorsText.append("\nsubscribe: 读取网络数据:\n");
                        }
                    });
                    Log.e(TAG, "\nsubscribe: 读取网络数据:");
                    e.onComplete();
                }


            }
        });


        Observable<FoodList> network = Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
                .addQueryParameter("rows", 10 + "")
                .build()
                .getObjectObservable(FoodList.class);


        // 两个 Observable 的泛型应当保持一致

        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodList>() {
                    @Override
                    public void accept(@NonNull FoodList tngouBeen) throws Exception {
                        Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName());
                        if (isFromNet) {
                            mRxOperatorsText.append("accept : 网络获取数据设置缓存: \n");
                            Log.e(TAG, "accept : 网络获取数据设置缓存: \n" + tngouBeen.toString());
                            CacheManager.getInstance().setFoodListData(tngouBeen);
                        }

                        mRxOperatorsText.append("accept: 读取数据成功:" + tngouBeen.toString() + "\n");
                        Log.e(TAG, "accept: 读取数据成功:" + tngouBeen.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName());
                        Log.e(TAG, "accept: 读取数据失败：" + throwable.getMessage());
                        mRxOperatorsText.append("accept: 读取数据失败：" + throwable.getMessage() + "\n");
                    }
                });*/
    }


    //flatmap 多个网络请求依次依赖  , 比如 注册要一个网络请求 , 登录也要一个网络请求, 可以先注册再登录 , 而且需要注册的数据
    private void test5() {
        /*Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
                .addQueryParameter("rows", 1 + "")
                .build()
                .getObjectObservable(FoodList.class) // 发起获取食品列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取食品列表的请求结果
                .doOnNext(new Consumer<FoodList>() {
                    @Override
                    public void accept(@NonNull FoodList foodList) throws Exception {
                        // 先根据获取食品列表的响应结果做一些操作
                        Log.e(TAG, "accept: doOnNext :" + foodList.toString());
                        mRxOperatorsText.append("accept: doOnNext :" + foodList.toString()+"\n");
                    }
                })
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取食品详情的请求
                .flatMap(new Function<FoodList, ObservableSource<FoodDetail>>() {
                    @Override
                    public ObservableSource<FoodDetail> apply(@NonNull FoodList foodList) throws Exception {
                        if (foodList != null && foodList.getTngou() != null && foodList.getTngou().size() > 0) {
                            return Rx2AndroidNetworking.post("http://www.tngou.net/api/food/show")
                                    .addBodyParameter("id", foodList.getTngou().get(0).getId() + "")
                                    .build()
                                    .getObjectObservable(FoodDetail.class);
                        }
                        return null;

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodDetail>() {
                    @Override
                    public void accept(@NonNull FoodDetail foodDetail) throws Exception {
                        Log.e(TAG, "accept: success ：" + foodDetail.toString());
                        mRxOperatorsText.append("accept: success ：" + foodDetail.toString()+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: error :" + throwable.getMessage());
                        mRxOperatorsText.append("accept: error :" + throwable.getMessage()+"\n");
                    }
                });*/
    }

    // zip 多个请求的合并 来更新一个ui
    private void test6() {
        /*
        Observable<MobileAddress> observable1 = Rx2AndroidNetworking.get("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                .build()
                .getObjectObservable(MobileAddress.class);

        Observable<CategoryResult> observable2 = Network.getGankApi()
                .getCategoryData("Android",1,1);

        Observable.zip(observable1, observable2, new BiFunction<MobileAddress, CategoryResult, String>() {
            @Override
            public String apply(@NonNull MobileAddress mobileAddress, @NonNull CategoryResult categoryResult) throws Exception {
                return "合并后的数据为：手机归属地："+mobileAddress.getResult().getMobilearea()+"人名："+categoryResult.results.get(0).who;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "accept: 成功：" + s+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 失败：" + throwable+"\n");
                    }
                });
         */
    }

    //interval 操作符  , 执行的是心跳的间隔的任务
    private void test7() {
        /*private Disposable mDisposable;
    @Override
    protected void doSomething() {
        mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "accept: doOnNext : "+aLong );
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "accept: 设置文本 ："+aLong );
                        mRxOperatorsText.append("accept: 设置文本 ："+aLong +"\n");
                    }
                });
    }


     // 销毁时停止心跳

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (mDisposable != null){
                mDisposable.dispose();
            }
        }*/
    }

    //doonnext 引发的副作用 https://blog.csdn.net/wangkai0681080/article/details/50772721
    //博客没有说明原因还得再找
    //do系列的作用是side effect,当onNext发生时，它被调用，不改变数据流。
    //doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。
    private void test8() {
        /*Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                Person person = new Person(201);
                subscriber.onNext(person);
            }
        }).doOnNext(new Action1<Person>() {
            @Override
            public void call(Person person) {
                person.age = 301;
            }
        }).subscribe(new Action1<Person>() {
            @Override
            public void call(Person person) {
                Log.d(TAG, "call: " + person.age);//输出301
            }
        });*/
    }


}
