package com.binvshe.binvshe.binvsheui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.example.chenjy.personal.login.SelectSexPop;
import com.binvshe.binvshe.binvsheui.release.SelectAlbumActivity;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostUpdateUserDataModel;
import com.binvshe.binvshe.http.model.UpdateHeadModel;
import com.binvshe.binvshe.http.response.UpdateHeadResponse;

import org.srr.dev.util.ToastUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.util.choosepic.FileManager;
import org.srr.dev.view.CircleImageView;
import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 完善个人资料Activity
 * Created by Administrator on 2016/3/15.
 */
public class CompleteInformationActivity extends AbsFragmentActivity implements IViewModelInterface{

    private EditText et_nick;
    private RadioButton rb_man, rb_woman;
    private CircleImageView civ_head;
    private TextView tvTitile, tvLeft, tvRight;

    private SelectSexPop selectPhoto;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;

    private String cameraPath = "";

    private String mNickName;
    private String mSex;

    private UpdateHeadModel mUpdateHeadModel;
    private PostUpdateUserDataModel mPostUpdateUserDataModel;


    /**
     * 用于拍照时获取输出的Uri
     */
    protected Uri getOutputMediaFileUri() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(FileManager.getImagePath(this) + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
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
                Intent intent = new Intent(CompleteInformationActivity.this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                startActivityForResult(intent, REQUEST_PICK_PHOTO);
            }

            @Override
            public void onClickThird(String tag) {
                selectPhoto.dismiss();
            }
        });
    }


    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.account_complete_information_layout;
    }

    @Override
    public void initView() {
        et_nick = (EditText) findViewById(R.id.et_nick);

        rb_man = (RadioButton) findViewById(R.id.rb_man);
        rb_woman = (RadioButton) findViewById(R.id.rb_woman);
        civ_head = (CircleImageView) findViewById(R.id.civ_head);
        civ_head.setOnClickListener(this);

        findViewById(R.id.tv_login).setOnClickListener(this);
        tvTitile = (TextView) findViewById(R.id.tv_title);
        tvTitile.setText(R.string.account_complete_information);
        tvLeft = (TextView) findViewById(R.id.tv_title_left);
        tvLeft.setText(R.string.cancel);
        tvRight = (TextView) findViewById(R.id.tv_title_right);
        tvRight.setText(R.string.pass);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        initSetHead();

    }

    @Override
    public void initData() {
        mUpdateHeadModel = new UpdateHeadModel();
        mPostUpdateUserDataModel = new PostUpdateUserDataModel();

        mUpdateHeadModel.setViewModelInterface(this);
        mPostUpdateUserDataModel.setViewModelInterface(this);

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.civ_head:
                selectPhoto.show(getSupportFragmentManager(), "photo");
                break;
            case R.id.tv_login:
                mNickName = et_nick.getText().toString();
                if(TextUtils.isEmpty(mNickName)){
                    ToastUtil.showToast(this, R.string.account_nick_input_hint);
                    return;
                }
                boolean rbMan = rb_man.isChecked();
                if(rbMan){
                    mSex = User.SEX_MAN;
                }else{
                    mSex = User.SEX_WOMAN;
                }

                if(TextUtils.isEmpty(cameraPath)){
                    ToastUtil.showToast(this, R.string.account_upload_head_tip);
                    return;
                }

                File file = new File(cameraPath);
                String uid = AccountManager.getInstance().getUserInfo().getId() + "";
                mUpdateHeadModel.start(file, uid);

                break;
            case R.id.tv_title_left:
                this.finish();
                break;
            case R.id.tv_title_right:
                this.finish();
                break;
        }

    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != this.RESULT_OK) {
            cameraPath = "";
            return;
        }
        if (requestCode == REQUEST_TAKE_PHOTO) {
            UIL.load(civ_head, "file://" + cameraPath);
            return;
        }
        if (requestCode == REQUEST_PICK_PHOTO) {
            ArrayList<String> imgs_z = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            cameraPath = imgs_z.get(0);
            UIL.load(civ_head, "file://" + cameraPath);
        }
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

            String uid = AccountManager.getInstance().getUserInfo().getId() + "";
            mPostUpdateUserDataModel.start(uid, headUrl, mNickName, mSex, "");

        }else if(tag == mPostUpdateUserDataModel.getTag()){

            this.finish();
        }

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        ToastUtil.showToast(this, codeMsg);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
    }
}
