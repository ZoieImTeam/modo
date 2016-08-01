package com.binvshe.binvshe.binvsheui.release;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.MyUtils;
import com.binvshe.binvshe.binvsheui.dialog.DeleteDynamicDialog;
import com.binvshe.binvshe.binvsheui.location.SelectPicDialogFragment;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostAddSpecialModel;
import com.binvshe.binvshe.util.StartActivity;

import org.srr.dev.adapter.DividerGridItemDecoration;
import org.srr.dev.adapter.FullyGridLayoutManager;
import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

import java.io.File;
import java.util.ArrayList;

public class ReleaseActivity extends AbsFragmentActivity implements IViewModelInterface {


    private EditText release_edit;
    public static final int PICK_UP_IMAGE = 201;
    public static final int TAKE_PHOTO_IMAGE = 203;
    public static final int EDIT_IMAGE = 202;
    private ArrayList<String> img_list = new ArrayList<>();
    private ImageAdapter img_adapter;
    public static final String RELEASE_IMGS_NUM = "release.imgs.num";
    public static final String EDIT_IMAGE_INTENT = "release.imgs.edit";
    public static final String SYS_TITLE = "com.binvshe.binvshe.sys.title";
    public static final String RES_ID = "com.binvshe.binvshe.res.id";
    public static final String SYS_PHOTO = "com.binvshe.binvshe.sys.photo";
    public static final String SYS_DESC = "com.binvshe.binvshe.sys.desc";
    public static final String SYS_TYPE = "com.binvshe.binvshe.sys.type";
    public int edit_img_click_item = -1;

    private boolean isLoading;
    private String mType;
    private EditText editDesc;
    private PostAddSpecialModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TakePhoto.getInstance().initTakePhoto(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initGetIntent() {
        mType = getIntent().getStringExtra(MyResActivity.TYPE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_release;
    }

    @Override
    public void initView() {
        release_edit = (EditText) findViewById(R.id.ac_release_edit);
        editDesc = (EditText) findViewById(R.id.et_ac_release_edit);
        if ("1".equals(mType)) {
            editDesc.setVisibility(View.GONE);
        } else {
            editDesc.setVisibility(View.VISIBLE);
        }
        final TextView release_edit_num = (TextView) findViewById(R.id.ac_release_edit_num);
        release_edit_num.setText(String.format(getResources().getString(R.string.ac_release_edit_num), 0));
        findViewById(R.id.ac_release_title_release).setOnClickListener(this);
        findViewById(R.id.ac_release_title_cancel).setOnClickListener(this);

        RecyclerView img_list = (RecyclerView) findViewById(R.id.ac_release_img_list);

        img_adapter = new ImageAdapter();
        img_list.setAdapter(img_adapter);
//
        img_list.addItemDecoration(new DividerGridItemDecoration(getResources().getDrawable(R.drawable.grid_recyclerview_white_8dp)));
//        //获得间隔图片的高度
        img_list.setLayoutManager(new FullyGridLayoutManager(this, 4, getResources().getDrawable(R.drawable.grid_recyclerview_white_8dp)));
        img_list.setItemAnimator(new DefaultItemAnimator());
        release_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = release_edit.getText().toString().length();
                release_edit_num.setText(String.format(getResources().getString(R.string.ac_release_edit_num), num));
            }
        });

    }

    @Override
    public void initData() {
        model = new PostAddSpecialModel();
        model.setViewModelInterface(this);
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.ac_release_title_release:
                showReleaseDialog();
                return;
            case R.id.ac_release_title_cancel:

                if (img_list != null && img_list.size() > 1) {
                    showBackDialog();
                    return;
                }
                if (!"".equals(release_edit.getText().toString())) {
                    showBackDialog();
                    return;
                }
                // TODO 判断是否编辑过
                finish();
                return;
            default:
                break;

        }
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }


    @Override
    public void refreshData() {

    }

    private void releaseDynamic() {
        if (isLoading) {
            toast(getString(R.string.release_loading_tips));
        } else {
            isLoading = true;

            String descr = release_edit.getText().toString();
            if (descr.length() > 600) {
                toast(getString(R.string.release_dynamic_text_exceed));
                return;
            }

            Intent intent = getIntent();
            String photo = intent.getStringExtra(SYS_PHOTO);
            if (editDesc.getVisibility() == View.VISIBLE) {
                if (!TextUtils.isEmpty(photo)) {
                    img_list.add(0, photo);
                }
            } else {
                if (!TextUtils.isEmpty(photo)) {
                    img_list.add(0, photo);
                    if (img_list == null || img_list.size() < 2) {
                        toast(getString(R.string.release_dynamic_image_tips));
                        return;
                    }
                } else {
                    if (img_list == null || img_list.size() < 1) {
                        toast(getString(R.string.release_dynamic_image_tips));
                        return;
                    }
                }
            }

            ArrayList<String> imgList = (ArrayList<String>) MyUtils.saveBitmapFileList(img_list, 720, 720);
            img_list.clear();
            assert imgList != null;
            img_list.addAll(imgList);

            ArrayList<File> files = new ArrayList<>();
            for (int i = 0; i < img_list.size(); i++) {
                files.add(new File(img_list.get(i)));
            }


            if (editDesc.getVisibility() == View.VISIBLE) {
                String text = editDesc.getText().toString();
                if ("".equals(text)) {
                    toast(getString(R.string.release_dynamic_text_tips));
                    return;
                }
                String ids = intent.getStringExtra(RES_ID);
                model.start(null, null, null, null, descr, text, mType, ids, img_list);
            } else {
                String sysId = intent.getStringExtra(SYS_TYPE);
                String sysDesc = intent.getStringExtra(SYS_DESC);
                String sysTitle = intent.getStringExtra(SYS_TITLE);
                String userId = SpUtils.getUserID();
                model.start(userId, sysId, sysDesc, sysTitle, descr, null, mType, null, files);
            }
        }
    }

    @Override
    public Handler getHandler() {
        Log.e("getHandler", "getHandler");
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        Log.e("onPreLoad", "onPreLoad");
        showLoadingDialog();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        dismissLoadingDialog();
        isLoading = false;
        if (tag == model.getTag()) {
            MyUtils.deleteFolderFile(Environment.getExternalStorageDirectory().getPath() + com.binvshe.binvshe.binvsheui.chen.Utils.Constants.ZANPLUSAPP_PATH_DOWNLOAD, true);
            StartActivity.startNaviActivity(this, this);
            BaseApp.destoryActivity("com.binvshe.binvshe.binvsheui.release.MyResActivity");
            ReleaseActivity.this.finish();
            toast(getString(R.string.release_success));
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Log.e("onFailLoad", codeMsg + "");
        Toast.makeText(ReleaseActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
        isLoading = false;
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Log.e("onExceptionLoad", exception.toString() + "");
        Toast.makeText(ReleaseActivity.this, "网络或者服务器异常！", Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
        isLoading = false;
    }

    private void showBackDialog() {
        final DeleteDynamicDialog dialog = DeleteDynamicDialog.newInstance("是否确定要放弃发布", "取消", "确定放弃");
        dialog.setOnDialogEnterCancelLisetener(new DeleteDynamicDialog.OnDialogEnterCancelLisetener() {
            @Override
            public void cancel() {
                dialog.dismiss();
            }

            @Override
            public void center() {
                ReleaseActivity.this.finish();
            }
        });
        dialog.show(getSupportFragmentManager(), "edit_dialog");
    }

    private void showReleaseDialog() {
        final DeleteDynamicDialog dialog = DeleteDynamicDialog.newInstance("是否确定要发布", "取消", "确定");
        dialog.setOnDialogEnterCancelLisetener(new DeleteDynamicDialog.OnDialogEnterCancelLisetener() {
            @Override
            public void cancel() {
                dialog.dismiss();
            }

            @Override
            public void center() {
                releaseDynamic();
            }
        });
        dialog.show(getSupportFragmentManager(), "edit_dialog");
    }

    /**
     * 图片适配器
     */
    public class ImageAdapter extends RecyclerViewAdapter<ImageAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView img, delete_img;

            public ViewHolder(View v) {
                super(v);
            }
        }


        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          final int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_release_img_list, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.img = (ImageView) v.findViewById(R.id.item_release_img);
            holder.delete_img = (ImageView) v.findViewById(R.id.item_release_delete_img);
            holder.delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_list.remove(viewType);
                    notifyDataSetChanged();
                }
            });


            return holder;
        }

        @Override
        public int getItemCount() {
            return img_list.size() <= 8 ? img_list.size() + 1 : 9;
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder,
                                 final int position) {
            ViewHolder holder = (ViewHolder) viewHolder;
            if (position == img_list.size()) {
                holder.img.setImageResource(R.drawable.release_add_img);
                holder.delete_img.setVisibility(View.GONE);
            } else {
                UIL.load(holder.img, "file://" + img_list.get(position));
                holder.delete_img.setVisibility(View.VISIBLE);
            }

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == img_list.size()) {

                        SelectPicDialogFragment dialog = new SelectPicDialogFragment();
                        dialog.setOnClickListener(new SelectPicDialogFragment.OnClickListener() {
                            @Override
                            public void takePhoto() {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                Uri imageUri = TakePhoto.getInstance().getOutputMediaFileUri();
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent, TAKE_PHOTO_IMAGE);
                            }

                            @Override
                            public void pickPhoto() {
                                Intent intent = new Intent(ReleaseActivity.this, SelectAlbumActivity.class);
                                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 9);
                                intent.putExtra(RELEASE_IMGS_NUM, img_list.size());
                                startActivityForResult(intent, PICK_UP_IMAGE);
                            }
                        });
                        dialog.show(getSupportFragmentManager(), "photo_dialog");

                    } else {
                        Intent intent = new Intent(ReleaseActivity.this, EditPhotoActivity.class);
                        intent.putExtra(EDIT_IMAGE_INTENT, img_list.get(position));
                        edit_img_click_item = position;
                        startActivityForResult(intent, EDIT_IMAGE);
                    }

                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PICK_UP_IMAGE && null != data) {
            ArrayList<String> imgs = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            img_list.addAll(imgs);
            img_adapter.notifyDataSetChanged();
        }

        if (requestCode == TAKE_PHOTO_IMAGE) {
            String cameraPath = TakePhoto.getInstance().getCameraPath();
            if (cameraPath != null) {
                img_list.add(cameraPath);
                img_adapter.notifyDataSetChanged();
                TakePhoto.getInstance().clear();
            }
        }

        if (requestCode == EDIT_IMAGE && null != data) {
            String img_url = data.getStringExtra(EditPhotoActivity.IMAGE_URL);
            img_list.set(edit_img_click_item, img_url);
            img_adapter.notifyDataSetChanged();
        }
    }
}