package com.example.totallearn.fragmentset.frag01;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.MainActivity;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;
/*

https://www.jianshu.com/p/0b4854aae105
1 . 质量压缩  bitmap.compress     bitmap.decodestream

2 . 采样率的压缩 bitmap.docefile    option.inJustDecodeBounds

3 . 缩放压缩  matrix


 */

//图片压缩的实测
public class ImgCompressActivity extends BaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.img1)
    ImageView img1;
    private Bitmap bit;
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_compress);
        ButterKnife.bind(this);

       imgDaXiao();
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.img1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;

            case R.id.tv2:
                break;

            case R.id.tv3:
                break;
            case R.id.tv4:
                break;
            case R.id.tv5:
                break;
            case R.id.tv6:
                break;
            case R.id.tv7:
                test7();
                break;
            case R.id.tv8:
                break;
            case R.id.img1:
                break;
        }
    }


    private void imgDaXiao() {
                bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()
                        + "/11.jpg");//看能否拿到路径

//         LogUtils.d("路径->"+Environment.getExternalStorageDirectory().getAbsolutePath());
//        LogUtils.d(TAG, "压缩图片大小" + (bit.getByteCount() / 1024 / 1024) + "M 宽度:" + bit.getWidth() + " 高度:" + bit.getHeight());
    }



    //质量压缩  对于bitmap大小并不会改变    图片的大小是没有变的，因为质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度等，
    // 来达到压缩图片的目的，这也是为什么该方法叫质量压缩方法。那么，图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
    private void test1() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 0; //0-100 手动测试

        bit.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        LogUtils.d("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight()
                + "bytes.length=  " + (bytes.length / 1024) + "KB"
                + "quality=" + quality);

    }


    //2.采样率压缩    为什么这个压缩方法叫采样率压缩，是因为配合inJustDecodeBounds，先获取图片的宽、高【这个过程就是取样】，
    // 然后通过获取的宽高，动态的设置inSampleSize的值。
    private void test2() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        bm = BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory().getAbsolutePath()
                + "/DCIM/Camera/test.jpg", options);
        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
    }

    //3.缩放法压缩  可以看出来，bitmap的长度和宽度分别缩小了一半，图片大小缩小了四分之一。关于martix更多信息，文末会有一个参考文章。
    private void test3() {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        bm = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),
                bit.getHeight(), matrix, true);
        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());

    }


    //我们看到图片大小直接缩小了一半，长度和宽度也没有变，相比argb_8888减少了一半的内存。
    //注意：由于ARGB_4444的画质惨不忍睹，一般假如对图片没有透明度要求的话，可以改成RGB_565，相比ARGB_8888将节省一半的内存开销。
    private void test4() {
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;

        bm = BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory().getAbsolutePath()
                + "/DCIM/Camera/test.jpg", options2);
        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
    }

    //其内部的方法 应该没有改变
    private void test5() {
        bm = Bitmap.createScaledBitmap(bit, 150, 150, true);
        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024) + "KB宽度为"
                + bm.getWidth() + "高度为" + bm.getHeight());
    }

    //https://www.jianshu.com/p/0b4854aae105
    private void test6() {
        //一般是把 采样率 结合起来使用的
    }


    //luban 框架  单个图片压缩测试
    private void test7() {
        //异步的压缩 , 通过测试 , 5M 图片压缩到100多K
        Luban.with(this)
                .load(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/IMG_20181115_100832.jpg")//传入原图 ,
                .ignoreBy(100)//不压缩的阈值单位为k
                .setTargetDir(getPath())//缓存路径
                .filter(new CompressionPredicate() {//filter 设置开启压缩的条件
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();

         /*.setRenameListener(new OnRenameListener() { //该名字用的接口可以用在上面
                    @Override
                    public String rename(String filePath) {
                        return null;
                    }
                })*/


         //同步压缩代码暂时不研究 , 损耗性能

    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    //luban 批量的加载 可以结合 demo去看
    private <T> void withLs(final List<T> photos) {
        Luban.with(this)
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setFocusAlpha(false)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(filePath.getBytes());
                            return new BigInteger(1, md.digest()).toString(32);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        return "";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() { }

                    @Override
                    public void onSuccess(File file) {
                        Log.i(TAG, file.getAbsolutePath());
                      //  showResult(originPhotos, file);
                    }

                    @Override
                    public void onError(Throwable e) { }
                }).launch();

    }


    /*private void showResult(List<File> photos, File file) {
        int[] originSize = computeSize(photos.get(mAdapter.getItemCount()));
        int[] thumbSize = computeSize(file);
        String originArg = String.format(Locale.CHINA, "原图参数：%d*%d, %dk", originSize[0], originSize[1], photos.get(mAdapter.getItemCount()).length() >> 10);
        String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);

        ImageBean imageBean = new ImageBean(originArg, thumbArg, file.getAbsolutePath());
        mImageList.add(imageBean);
        mAdapter.notifyDataSetChanged();
    }*/

    //https://blog.csdn.net/ljx1400052550/article/details/79575201  多张图片加载






}
