package com.binvshe.binvshe.binvsheui.release;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePopupWindow;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.location.SelectPicDialogFragment;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddSpecialModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.view.PbTopBar;
import com.binvshe.binvshe.view.PbTopBar_;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.util.PhotoUtils;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 发布插画
 */
public class IkonActivity extends AbsFragmentActivity implements PbTopBar.MytopbarClick, IViewModelInterface {

    public static final String RELEASE_IMGS_NUM = "release.imgs.num";
    public static final int PICK_UP_IMAGE = 201;
    public static final String EDIT_IMAGE_INTENT = "release.imgs.edit";
    public int edit_img_click_item = -1;
    public static final int TAKE_PHOTO_IMAGE = 203;
    public static final int EDIT_IMAGE = 202;
    public static final int PICK_COVER_IMAGE = 204;//获取封面的请求码


    Context context;
    PbTopBar mytopbar;
    EditText edit_title, edit_author, edit_dec;
    TextView edit_create_time;
    ImageView select_image;
    RadioGroup radio_type;

    String time, type, author, dec, user_id, title;
    public ArrayList<String> imgs;
    public ArrayList<String> coverImage=new ArrayList<>();
    public ArrayList<String> totalimage = new ArrayList<String>();


    MyRecAdapter simpadapter;
    SubjectTypeEntity entity;
    AddSpecialModel addSpecialModel;


    @Override
    protected void initGetIntent() {
        entity = getIntent().getParcelableExtra(GlobalConfig.EXTRA_OBJECT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_release3;
    }

    @Override
    public void initView() {
        context = getApplicationContext();

        mytopbar = (PbTopBar_) findViewById(R.id.pb_topbar);
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_author = (EditText) findViewById(R.id.edit_author);
        edit_dec = (EditText) findViewById(R.id.edit_dec);
        edit_create_time = (TextView) findViewById(R.id.edit_create_time);
        select_image = (ImageView) findViewById(R.id.select_image);
        radio_type = (RadioGroup) findViewById(R.id.radio_type);
        RecyclerView rec_select_image = (RecyclerView) findViewById(R.id.rec_select_image);//选择上传多图的recyclerview

        simpadapter = new MyRecAdapter();
        //设置adapter，manager，animation
        rec_select_image.setAdapter(simpadapter);
        rec_select_image.setLayoutManager(new LinearLayoutManager(this));
        rec_select_image.setItemAnimator(new DefaultItemAnimator());


        String title = getString(R.string.title_release, entity.getName());
        mytopbar.settext("", title, getString(R.string.dialog_btn_text_confirm));
        mytopbar.setMytopbarClickimp(this);
        edit_create_time.setOnClickListener(this);
        select_image.setOnClickListener(this);
        imgs = new ArrayList<String>();

        if (AccountManager.getInstance().isLogin()) {
            user_id = AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        } else {
            String message=getString(R.string.not_login);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            Intent ii=new Intent(this, ChooseActivity.class);
            startActivity(ii);
        }
    }

    @Override
    public void initData() {
        radio_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获取变更后的选中项的ID
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) IkonActivity.this.findViewById(radioButtonId);
                type = rb.getText().toString();
                //     Toast.makeText(context,rb.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.edit_create_time: {
                TimePopupWindow timep = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
                timep.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        time = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        // Toast.makeText(context,time,Toast.LENGTH_LONG).show();
                        edit_create_time.setText(time);
                    }
                });
                timep.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            }

            case R.id.select_image: {
                //封面选择
                Intent intent = new Intent(IkonActivity.this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                intent.putExtra(RELEASE_IMGS_NUM, 0);
                startActivityForResult(intent, PICK_COVER_IMAGE);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PICK_UP_IMAGE && null != data) {
            imgs.addAll((ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE));
//           for(int position=0;position<imgs.size();position++) {
//              UIL.load(selectImageView, "file://" + imgs.get(position));
            simpadapter.notifyDataSetChanged();
//           }
            return;
        }
        if (requestCode == PICK_COVER_IMAGE && null != data) {
            coverImage = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            UIL.load(select_image, "file://" + coverImage.get(0));
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void leftTexClick() {
        IkonActivity.this.finish();
    }

    @Override
    public void rightTexClick() {

        addSpecialModel = new AddSpecialModel();
        addSpecialModel.setViewModelInterface(this);
        ArrayList<File> files = new ArrayList<File>();

        dec = edit_dec.getText().toString();
        author = edit_author.getText().toString();
        title = edit_title.getText().toString();

        if (coverImage.size() > 0 && !TextUtils.isEmpty(dec) && !TextUtils.isEmpty(author) && !TextUtils.isEmpty(title)
                &&!TextUtils.isEmpty(type)) {
            totalimage.addAll(coverImage);
            totalimage.addAll(imgs);
            List<String> strings = PhotoUtils.saveBitmapFileList(totalimage, com.binvshe.binvshe.constants.Constants.FILE.FILE_CACHE, 720, 720);

            for (int ttt = 0; ttt < strings.size(); ttt++) {
                File imageFile = new File(strings.get(ttt));
                files.add(imageFile);
            }
            addSpecialModel.start("", title, user_id, type, entity.getId(), dec, author, "", "", "0", files, "", "1", "");
            Toast.makeText(this, "发布中.....", Toast.LENGTH_SHORT).show();
            showLoadingDialog();
        } else {
            Toast.makeText(context, R.string.have_null, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (tag == addSpecialModel.getTag()) {
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            dismissLoadingDialog();
            finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if (tag == addSpecialModel.getTag()) {
            Toast.makeText(this, "发布失败..", Toast.LENGTH_SHORT).show();
            dismissLoadingDialog();
        }
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(this, "发布失败..", Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }


    private class MyRecAdapter extends RecyclerViewAdapter<MyRecAdapter.ViewHolder> {
        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_pb_imagerec, parent, false
            );
            ViewHolder holder = new ViewHolder(view);
            holder.image = (ImageView) view.findViewById(R.id.selected_image);
            return holder;
        }

        @Override
        public int getItemCount() {
            //return imgs.size() <= 8 ? imgs.size() + 1 : 9;
            return imgs.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

            ViewHolder holder = (ViewHolder) viewHolder;
            if (position == imgs.size()) {
                holder.image.setImageResource(R.drawable.release_add_img);
                // holder.delete_img.setVisibility(View.GONE);
            } else {
                UIL.load(holder.image, "file://" + imgs.get(position));
                //holder.delete_img.setVisibility(View.VISIBLE);
            }

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == imgs.size()) {

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
                                Intent intent = new Intent(IkonActivity.this, SelectAlbumActivity.class);
                                intent.putExtra(RELEASE_IMGS_NUM, imgs.size());
                                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 9);
                                intent.putExtra(RELEASE_IMGS_NUM, imgs.size());
                                startActivityForResult(intent, PICK_UP_IMAGE);
                            }
                        });
                        dialog.show(getSupportFragmentManager(), "photo_dialog");

                    } else {
                        Intent intent = new Intent(IkonActivity.this, EditPhotoActivity.class);
                        intent.putExtra(EDIT_IMAGE_INTENT, imgs.get(position));
                        edit_img_click_item = position;
                        startActivityForResult(intent, EDIT_IMAGE);
                    }

                }
            });
        }


        //holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;

            public ViewHolder(View v) {
                super(v);
            }
        }
    }
}
