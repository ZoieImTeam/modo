package org.srr.dev.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.srr.dev.R;
import org.srr.dev.base.BaseApplication;

import java.net.URI;
import java.net.URISyntaxException;

public class UIL {

    private static DisplayImageOptions options;

    //    private static PauseOnScrollListener mPauseOnScrollListener;  ListView 图片加载优化
    private static PauseOnRVScrollListener mPauseOnRVScrollListener; //Recyclerview图片加载优化

    public static void init(Context context) {
        initImageLoader(context);
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.mipmap.app_icon)
//                .showImageOnLoading(R.mipmap.uil_ic_stub)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .showImageForEmptyUri(R.mipmap.uil_ic_empty)
//                .cacheInMemory(false)
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();

//        mPauseOnScrollListener = new PauseOnScrollListener(imageLoader, true,
//                true);
        mPauseOnRVScrollListener = new PauseOnRVScrollListener(imageLoader, true,
                true);

    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                // 50 Mb
                .threadPoolSize(3)//线程池内加载的数量
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param defaultId
     * @param url
     */
    public static void load(ImageView imageView, int defaultId, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
//        ImageLoader.getInstance().displayImage(url, imageView, options);
        Glide.with(BaseApplication.mContext)
                .load(url)
                .placeholder(R.mipmap.uil_loading_icon)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }
    public static void load(ImageView imageView,String url,Context context)
    {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.mipmap.uil_loading_icon)
                    .into(imageView);


    }
    public static void loadwh(ImageView imageView, String url,Context context,int w,int h)
    {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
//        ImageLoader.getInstance().displayImage(url, imageView, options);

        imageView.getLayoutParams().height = h;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(R.mipmap.uil_loading_icon)
                .override(w,h)
                .into(imageView);
    }


    public static void load(ImageView imageView, String url, int type) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        // DisplayImageOptions options =
        // ImageOptionFactory.createImageOption(type);
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param defaultId
     * @param url
     */
    public static void load(ImageView imageView, int defaultId, String url,
                            Object object) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (imageView == null) {
            return;
        }
        ImageLoader.getInstance().displayImage(url, imageView, options);


        //ImageLoader.getInstance().loadImageSync()
    }

    /**
     * 同步加载图片
     *
     * @param url
     * @return 位图资源
     */
    public static Bitmap loadSync(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Bitmap bm = ImageLoader.getInstance().loadImageSync(url, options);
        return bm;
    }

    // /**
    // * 自定义图片监听回调的加载图片
    // * @param defaultId
    // * @param url
    // * @param imageListener
    // */
    // public static void load(int defaultId, String url,ImageListener
    // imageListener){
    // HttpRequestManager.getInstance().getImageLoader().get(url,
    // imageListener);
    // }

    /**
     * 是否已有图片缓存
     *
     * @param key 图片缓存的键，一般为url
     * @return
     */
    public static boolean hasImageCache(String key) {
        return ImageLoader.getInstance().getMemoryCache().keys().contains(key);
    }

    /*
    通过宽度，自动适应高度
     */
    public static void loadAutoHeight(String url, int w, ImageView imageView) {
        Bitmap bp = loadSync(url);
        if (bp != null) {
            float bili = (float) ((bp.getHeight() * 1.0) / bp.getWidth());
            imageView.getLayoutParams().height = (int) (w * bili);
            load(imageView, url);
            bp.recycle();
            bp = null;
        }
    }

    /**
     * 获取缓存对象
     *
     * @return
     */
    public static MemoryCache getMemoryCache() {
        return ImageLoader.getInstance().getMemoryCache();
    }

    public static PauseOnRVScrollListener getImageLoaderPauseScrollListener() {
        return mPauseOnRVScrollListener;
    }

    public static DisplayImageOptions getOptions() {
        return options;
    }

    public static void setOptions(DisplayImageOptions options) {
        UIL.options = options;
    }


    //Recyclerview 的图片加载优化
    public static class PauseOnRVScrollListener extends RecyclerView.OnScrollListener {
        private ImageLoader imageLoader;
        private final boolean pauseOnScroll;
        private final boolean pauseOnFling;
        private final RecyclerView.OnScrollListener externalListener;

        public PauseOnRVScrollListener(ImageLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling) {
            this(imageLoader, pauseOnScroll, pauseOnFling, (RecyclerView.OnScrollListener) null);
        }

        public PauseOnRVScrollListener(ImageLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling, RecyclerView.OnScrollListener customListener) {
            this.imageLoader = imageLoader;
            this.pauseOnScroll = pauseOnScroll;
            this.pauseOnFling = pauseOnFling;
            this.externalListener = customListener;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case 0:
                    this.imageLoader.resume();
                    break;
                case 1:
                    if (this.pauseOnScroll) {
                        this.imageLoader.pause();
                    }
                    break;
                case 2:
                    if (this.pauseOnFling) {
                        this.imageLoader.pause();
                    }
            }

            if (this.externalListener != null) {
                this.externalListener.onScrollStateChanged(recyclerView, newState);
            }

        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (this.externalListener != null) {
                this.externalListener.onScrolled(recyclerView, dx, dy);
            }

        }
    }
}
