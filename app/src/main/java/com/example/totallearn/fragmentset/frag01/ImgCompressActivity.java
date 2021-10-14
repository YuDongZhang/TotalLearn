package com.example.totallearn.fragmentset.frag01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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


    @BindView(R.id.iv_view)
    ImageView ivView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    private Bitmap mBitmap;
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_compress);
        ButterKnife.bind(this);

        imgDaXiao();
    }


    private void imgDaXiao() {
        mBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()
                + "/11.jpg");//看能否拿到路径

//         LogUtils.d("路径->"+Environment.getExternalStorageDirectory().getAbsolutePath());
        LogUtils.d(TAG, "压缩图片大小" + (mBitmap.getAllocationByteCount() / 1024 / 1024) + "M  宽度:" + mBitmap.getWidth() + " 高度:" + mBitmap.getHeight());
        File file = new File(Environment.getExternalStorageDirectory() + "/11.jpg");
        if (file.exists() && file.isFile()) {
            LogUtils.d("文件大小 " + file.length());
        }

        ivView.setImageBitmap(mBitmap);

    }


    /**
     * 1.质量压缩
     * 对于bitmap大小并不会改变    图片的大小是没有变的，因为质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度等，
     * <p>
     * 来达到压缩图片的目的，这也是为什么该方法叫质量压缩方法。那么，图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
     * 将压缩后的图片上传到服务器，或者保存到本地。具体结合实际需求。
     */
    private void test1() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100; //0-100 手动测试
        mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos); //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

//        byte[] bytes = baos.toByteArray();

        //循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 100) {//循环判断如果压缩后的图片
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);//这里压缩options%，把压缩后的数据存放到baos中
            quality -= 10;//每次都减少10
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        //把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(bais, null, null);


        LogUtils.d("wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                + "M   图片质量大小" + baos.toByteArray().length / 1024
        );

        tv1.setText("1,质量压缩  bitmap大小 " + (bitmap.getByteCount() / 1024 / 1024) + "M  图片质量大小:" + baos.toByteArray().length / 1024);

    }


    /**
     * 2.采样率压缩
     * <p>
     * 为什么这个压缩方法叫采样率压缩，是因为配合inJustDecodeBounds，先获取图片的宽、高【这个过程就是取样】，
     * 然后通过获取的宽高，动态的设置inSampleSize的值。
     */
    private void test2() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //开始读入图片,就是不读入内存,只是获取边框的信息
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/11.jpg", options);
        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;

        //现在主流手机比较多是1280*720分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为1280f
        float ww = 720f;//这里设置宽度为720f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (options.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (options.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/11.jpg", options);

        if (bitmap != null) {
            LogUtils.i("wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024) + "M  宽度:" + bitmap.getWidth() + " 高度:" + bitmap.getHeight());
        }

        tv2.setText("2,采样率压缩  bitmap:" + bitmap.getByteCount() / 1024 / 1024 + "M");

    }


    /**
     * 3.缩放法压缩
     * <p>
     * 可以看出来，bitmap的长度和宽度分别缩小了一半，图片大小缩小了四分之一。关于martix更多信息，文末会有一个参考文章。
     */
    private void test3() {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        bm = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());

    }

    /**
     * 缩放压缩二
     *
     * @param bmp
     * @param file 写入到文件
     */
    public static void compressBitmapToFile(Bitmap bmp, File file) {
        // 尺寸压缩倍数,值越大，图片尺寸越小
        int ratio = 2;
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //我们看到图片大小直接缩小了一半，长度和宽度也没有变，相比argb_8888减少了一半的内存。
    //注意：由于ARGB_4444的画质惨不忍睹，一般假如对图片没有透明度要求的话，可以改成RGB_565，相比ARGB_8888将节省一半的内存开销。
    private void test4() {
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;

        bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/test.jpg", options2);

        LogUtils.i("wechat", "压缩后图片的大小" + (bm.getByteCount() / 1024 / 1024)
                + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
    }

    //其内部的方法 应该没有改变
    private void test5() {
        bm = Bitmap.createScaledBitmap(mBitmap, 150, 150, true);
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
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i(TAG, file.getAbsolutePath());
                        //  showResult(originPhotos, file);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();

    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                test1();
                break;

            case R.id.tv2:
                test2();
                break;

            case R.id.tv3:
                test3();
                break;

            case R.id.tv4:
                compressBitmapToFile(mBitmap, new File(Environment.getExternalStorageDirectory() + "/12.jpg"));
                break;
        }
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
