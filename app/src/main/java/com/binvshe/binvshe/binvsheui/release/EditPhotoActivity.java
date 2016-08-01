package com.binvshe.binvshe.binvsheui.release;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.MyUtils;
import com.binvshe.binvshe.binvsheui.dialog.DeleteDynamicDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.util.UIL;
import org.srr.dev.util.imagefilter.HslModifyFilter;
import org.srr.dev.util.imagefilter.IImageFilter;
import org.srr.dev.util.imagefilter.Image;
import org.srr.dev.util.imagefilter.LightFilter;
import org.srr.dev.util.imagefilter.RainBowFilter;
import org.srr.dev.util.imagefilter.SepiaFilter;
import org.srr.dev.util.imagefilter.SoftGlowFilter;
import org.srr.dev.util.imagefilter.TileReflectionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditPhotoActivity extends AbsFragmentActivity {

    private List<IImageFilter> filterArray = new ArrayList<>();
    private String effectName[] = new String[]{
            "原图",
            "玻璃框",
            "高光",
            "复古",
            "炫彩",
            "灯光",
            "蓝调",
            "绿萝",
            "青草",
            "黄昏",
            "玫瑰"
    };
    public static final String IMAGE_URL = "editphotoactivity.imgs.edit.img_url";
    private ImageView img;
    private boolean ischange;
    private DeleteDynamicDialog dialog;
    private FragmentManager manager;
    private String img_url;
    private String img_url_min;
    private Bitmap edit_bitmap;
    private HashMap<Integer, Bitmap> thumbnails = new HashMap<>();
    private EffectAdapter effect_adapter;

    @Override
    protected void initGetIntent() {
        Intent intent = getIntent();
        String img_url = intent.getStringExtra(ReleaseActivity.EDIT_IMAGE_INTENT);
        this.img_url = MyUtils.saveBitmapFile(img_url, 720, 720);
        this.img_url_min = MyUtils.saveBitmapFile(img_url, 140, 200);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_edit_photo;
    }

    @Override
    public void initView() {
        initImageFilter();
        initViews();
    }

    @Override
    public void initData() {
        for (int i = 0; i < filterArray.size(); i++) {
            new ThumbnailsImageTask(filterArray.get(i), i).execute();
        }
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.ac_edit_photo_title_back:
                if (ischange) {
                    if (dialog == null) {
                        dialog = DeleteDynamicDialog.newInstance("是否放弃编辑", "否", "是");
                        manager = getSupportFragmentManager();
                        dialog.setOnDialogEnterCancelLisetener(new DeleteDynamicDialog.OnDialogEnterCancelLisetener() {
                            @Override
                            public void cancel() {
                                dialog.dismiss();
                            }

                            @Override
                            public void center() {
                                dialog.dismiss();
                                EditPhotoActivity.this.finish();
                            }
                        });
                    }
                    dialog.show(manager, "dialog");
                } else {
                    finish();
                }
                break;
            case R.id.ac_edit_photo_title_save:
                if (ischange) {
                    saveBitmap();
                }
                Intent intent = getIntent();
                intent.putExtra(IMAGE_URL, img_url);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void initImageFilter() {
        //注：在android系统上，手机图片尺寸尽量控制在480*480范围内,否则在高斯运算时可以造成内存溢出的问题
        filterArray.add(new TileReflectionFilter(20, 8, 45, (byte) 2));
        filterArray.add(new SoftGlowFilter(10, 0.1f, 0.1f));
        filterArray.add(new SepiaFilter());
        filterArray.add(new RainBowFilter());
        filterArray.add(new LightFilter());
        filterArray.add(new HslModifyFilter(20f));
        filterArray.add(new HslModifyFilter(100f));
        filterArray.add(new HslModifyFilter(150f));
        filterArray.add(new HslModifyFilter(200f));
        filterArray.add(new HslModifyFilter(250f));

    }

    private void initViews() {
        findViewById(R.id.ac_edit_photo_title_back).setOnClickListener(this);
        findViewById(R.id.ac_edit_photo_title_save).setOnClickListener(this);
        RecyclerView effect_list = (RecyclerView) findViewById(R.id.ac_edit_photo_effect_list);
        img = (ImageView) findViewById(R.id.ac_edit_photo_img);
        UIL.load(img, "file://" + img_url); //加载图片
        Log.e("img_url", img_url + "");
        effect_adapter = new EffectAdapter();
        effect_list.setAdapter(effect_adapter);
        effect_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        effect_list.setItemAnimator(new DefaultItemAnimator());
        effect_adapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                if (position == 0) {
                    ischange = false;
                    UIL.load(img, "file://" + img_url); //加载图片
                    return;
                }
                Toast.makeText(EditPhotoActivity.this, "开始滤镜...", Toast.LENGTH_SHORT).show();
                new processImageTask(EditPhotoActivity.this, filterArray.get(position - 1)).execute();
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
    }

    @Override
    public void refreshData() {

    }


    /**
     * 滤镜后 替换源文件 同时消除 imageloader的缓存
     */
    public void saveBitmap() {
        File f = new File(img_url);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            edit_bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            DiskCacheUtils.removeFromCache("file://" + img_url, ImageLoader.getInstance().getDiskCache());
            MemoryCacheUtils.removeFromCache("file://" + img_url, ImageLoader.getInstance().getMemoryCache());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public class EffectAdapter extends RecyclerViewAdapter<EffectAdapter.ViewHolder> {


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView effect_text;
            ImageView effect_img;

            public ViewHolder(View v) {
                super(v);
            }
        }

        @Override
        public EffectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_edit_photo_effect, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.effect_img = (ImageView) v.findViewById(R.id.item_edit_photo_effect_img);
            holder.effect_text = (TextView) v.findViewById(R.id.item_edit_photo_effect_text);

            return holder;
        }

        @Override
        public int getItemCount() {
            return thumbnails.size() + 1;
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
            if (position == 0) {
                UIL.load(holder.effect_img, "file://" + img_url_min); //加载图片
            } else {
                holder.effect_img.setImageBitmap(thumbnails.get(position - 1));
            }
            holder.effect_text.setText(effectName[position]);
            holder.effect_text.setTextColor(0xffff0000);
        }

    }

    public class processImageTask extends AsyncTask<Void, Void, Bitmap> {
        private IImageFilter filter;
        private Activity activity = null;

        public processImageTask(Activity activity, IImageFilter imageFilter) {
            this.filter = imageFilter;
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }


        public Bitmap doInBackground(Void... params) {
            Image img = null;
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(img_url);
                img = new Image(bitmap);
                if (filter != null) {
                    img = filter.process(img);
                    img.copyPixelsFromBuffer();
                }
                return img.getImage();
            } catch (Exception e) {
                if (img != null && img.destImage.isRecycled()) {
                    img.destImage.recycle();
                    img.destImage = null;
                    System.gc(); // 提醒系统及时回收
                }
            } finally {
                if (img != null && img.image.isRecycled()) {
                    img.image.recycle();
                    img.image = null;
                    System.gc(); // 提醒系统及时回收
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                super.onPostExecute(result);
                img.setImageBitmap(result);
                EditPhotoActivity.this.edit_bitmap = result;
                ischange = true;

            }
        }
    }

    public class ThumbnailsImageTask extends AsyncTask<Void, Void, Bitmap> {
        private IImageFilter filter;
        private int position;

        public ThumbnailsImageTask(IImageFilter imageFilter, int position) {
            this.filter = imageFilter;
            this.position = position;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }


        public Bitmap doInBackground(Void... params) {
            Image img = null;
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(img_url_min);
                img = new Image(bitmap);
                if (filter != null) {
                    img = filter.process(img);
                    img.copyPixelsFromBuffer();
                }
                return img.getImage();
            } catch (Exception e) {
                if (img != null && img.destImage.isRecycled()) {
                    img.destImage.recycle();
                    img.destImage = null;
                    System.gc(); // 提醒系统及时回收
                }
            } finally {
                if (img != null && img.image.isRecycled()) {
                    img.image.recycle();
                    img.image = null;
                    System.gc(); // 提醒系统及时回收
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                super.onPostExecute(result);
                thumbnails.put(position, result);
                if (position == (thumbnails.size() - 1)) {
                    effect_adapter.notifyDataSetChanged();
                }
            }
        }
    }

}
