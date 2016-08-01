package com.binvshe.binvshe.binvsheui.usercenter;

import android.accounts.Account;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.example.chenjy.personal.login.SelectSexPop;
import com.binvshe.binvshe.binvsheui.release.SelectAlbumActivity;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostUpdateUserDataModel;
import com.binvshe.binvshe.http.model.UpdateHeadModel;
import com.binvshe.binvshe.http.response.UpdateHeadResponse;
import com.binvshe.binvshe.util.DialogUtil;

import org.srr.dev.util.ToastUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.util.choosepic.FileManager;
import org.srr.dev.view.CircleImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 编辑个人资料的Activity
 * Created by Administrator on 2016/3/17.
 */
public class EditInfoActivity extends AbsFragmentActivity implements IViewModelInterface{

    private CircleImageView civ_head;
    private EditText et_name;
    private TextView tv_user_sex, tv_sex, et_sign;
    private TextView tvTitile, tvLeft, tvRight;

    private SelectSexPop selectPhoto, selectSex;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;

    private String cameraPath = "";
    private String mSex = "";
    private String mName = "";
    private String mSign = "";
    private String mHead = "";

    private UpdateHeadModel mUpdateHeadModel;
    private PostUpdateUserDataModel mPostUpdateUserDataModel;

    private boolean mIsEdited = false;

    /**
     * 用于拍照时获取输出的Uri
     */
    protected Uri getOutputMediaFileUri() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(FileManager.getImagePath(this) + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
    }

    private void initSetSex(){
        selectSex = new SelectSexPop();
        selectSex.setOnSelectLayout(new SelectSexPop.OnSelectLayout() {
            @Override
            public void onClickFirst(String str1, String tag) {
                mIsEdited = true;
                mSex = User.SEX_MAN;
                tv_user_sex.setText(R.string.user_sex_man);
            }

            @Override
            public void onClickSecond(String str2, String tag) {
                mIsEdited = true;
                mSex = User.SEX_WOMAN;
                tv_user_sex.setText(R.string.user_sex_woman);
            }

            @Override
            public void onClickThird(String tag) {
                selectSex.dismiss();
            }
        });
    }

    private void initSetHead() {
        selectPhoto = new SelectSexPop("拍照", "相册", "取消");
        selectPhoto.setOnSelectLayout(new SelectSexPop.OnSelectLayout() {
            @Override
            public void onClickFirst(String str1, String tag) {
                //拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoUri = getOutputMediaFileUri();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }

            @Override
            public void onClickSecond(String str2, String tag) {
                //从相册选取
                Intent intent = new Intent(EditInfoActivity.this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                startActivityForResult(intent, REQUEST_PICK_PHOTO);
            }

            @Override
            public void onClickThird(String tag) {
                selectPhoto.dismiss();
            }
        });
    }

    /**
     * 更新个人信息
     */
    private void uploadUserInfo(){
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        mName = et_name.getText().toString();
        mSign = et_sign.getText().toString();
        mPostUpdateUserDataModel.start(id, mHead, mName, mSex, mSign);

    }


    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.edit_info_layout;
    }

    @Override
    public void initView() {
        civ_head = (CircleImageView) findViewById(R.id.civ_head);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_user_sex = (TextView) findViewById(R.id.tv_user_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        et_sign = (EditText) findViewById(R.id.et_sign);

        findViewById(R.id.tv_head_tip).setOnClickListener(this);
        findViewById(R.id.tv_sex).setOnClickListener(this);

        tvTitile = (TextView) findViewById(R.id.tv_title);
        tvTitile.setText(R.string.title_user_center_edit_info);
        tvLeft = (TextView) findViewById(R.id.tv_title_left);
        tvLeft.setVisibility(View.GONE);
        findViewById(R.id.iv_title_left).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_title_left).setOnClickListener(this);
        tvRight = (TextView) findViewById(R.id.tv_title_right);
        tvRight.setText(R.string.finish);
        tvRight.setOnClickListener(this);

        initSetHead();
        initSetSex();
    }

    @Override
    public void initData() {
        mUpdateHeadModel = new UpdateHeadModel();
        mPostUpdateUserDataModel = new PostUpdateUserDataModel();

        mUpdateHeadModel.setViewModelInterface(this);
        mPostUpdateUserDataModel.setViewModelInterface(this);

        User user = AccountManager.getInstance().getUserInfo();
        if(user != null){
            String name = user.getName();
            String sign = user.getSign();
            String headUrl = user.getHead();
            String sex = user.getSex() + "";

            UIL.load(civ_head, headUrl);
            et_name.setText(name);
            et_sign.setText(sign);
            if(sex.equals(User.SEX_MAN)){
                tv_user_sex.setText(R.string.user_sex_man);
            }else if(sex.equals(User.SEX_WOMAN)){
                tv_user_sex.setText(R.string.user_sex_woman);
            }else{
                tv_user_sex.setText(R.string.user_sex_unknow);
            }
        }

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.tv_head_tip:
                selectPhoto.show(getSupportFragmentManager(), "photo");
                break;
            case R.id.tv_sex:
                selectSex.show(getSupportFragmentManager(), "sex");
                break;
            case R.id.iv_title_left:
                if(mIsEdited){
                    DialogUtil.commonConfirmDialog(this, "", getString(R.string.dialog_titile_kindly_reminder), -1, -1, new DialogUtil.ConfirmListener() {
                        @Override
                        public void onClick(View v) {
                            if(!TextUtils.isEmpty(cameraPath)){
                                File file = new File(cameraPath);
                                String uid = AccountManager.getInstance().getUserInfo().getId() + "";
                                mUpdateHeadModel.start(file, uid);
                            }else{
                                uploadUserInfo();
                            }
                        }
                    }, new DialogUtil.CancelListener() {
                        @Override
                        public void onClick(View v) {
                            EditInfoActivity.this.finish();
                        }
                    });
                }else{
                    this.finish();
                }
                break;
            case R.id.tv_title_right:
                if(!TextUtils.isEmpty(cameraPath)){
                    File file = new File(cameraPath);
                    String uid = AccountManager.getInstance().getUserInfo().getId() + "";
                    mUpdateHeadModel.start(file, uid);
                }else{
                    uploadUserInfo();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != this.RESULT_OK) {
            cameraPath = "";
            return;
        }
        if (requestCode == REQUEST_TAKE_PHOTO) {
            mIsEdited = true;
            UIL.load(civ_head, "file://" + cameraPath);
            return;
        }
        if (requestCode == REQUEST_PICK_PHOTO) {
            mIsEdited = true;
            ArrayList<String> imgs_z = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            cameraPath = imgs_z.get(0);
            UIL.load(civ_head, "file://" + cameraPath);
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showLoadingDialog();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        dismissLoadingDialog();
        if(tag == mUpdateHeadModel.getTag()){
            UpdateHeadResponse response = (UpdateHeadResponse) result;
            String headUrl = response.getData().getPhotoUrl();
            User user = AccountManager.getInstance().getUserInfo();
            user.setHead(headUrl);
            mHead = headUrl;
            uploadUserInfo();

        }else if(tag == mPostUpdateUserDataModel.getTag()){
            User user = AccountManager.getInstance().getUserInfo();
            user.setSign(mSign);
            user.setName(mName);
            if(!TextUtils.isEmpty(mSex)){
                user.setSex(Integer.parseInt(mSex));
            }
            ToastUtil.showToast(this, R.string.user_edit_success_tip);
            this.finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }
}
