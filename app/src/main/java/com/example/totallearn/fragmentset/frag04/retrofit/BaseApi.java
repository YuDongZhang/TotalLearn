package com.example.totallearn.fragmentset.frag04.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface BaseApi {

    /**
     * 下载文件
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Header("Range") String range, @Url String url);
    //注意到请求里有个请求头“Range”，这个是为了实现断点续传。简单说就是可以从服务器下载文件的指定“部分”。
    //
    //Range的传值规则如下：bytes=startPos-endPos，其中endPos是可以省略的，即结束位置为文件的末尾。比如从36985byte开始断点下载，则传值为："bytes=36985-"。

}