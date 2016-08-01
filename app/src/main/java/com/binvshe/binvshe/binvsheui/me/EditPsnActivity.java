package com.binvshe.binvshe.binvsheui.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.chen.Utils.MyUtils;
import com.binvshe.binvshe.binvsheui.chen.example.chenjy.personal.login.SelectSexPop;
import com.binvshe.binvshe.binvsheui.location.FileManager;
import com.binvshe.binvshe.binvsheui.release.SelectAlbumActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.db.dao.UserDao;
import com.binvshe.binvshe.entity.psnhomedata.Userinfo;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostUpdateUserDataModel;
import com.binvshe.binvshe.http.model.UpImageModel;
import com.binvshe.binvshe.http.response.ImageUrlResponse;

import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditPsnActivity extends AbsFragmentActivity implements IViewModelInterface{

    public static final int REQUEST_TAKE_PHOTO = 11;
    public static final int REQUEST_PICK_PHOTO = 12;

    private EditText edit_nickname, edit_sign, edit_job, edit_city, edit_university, edit_company;
    private TextView tv_birthday, tv_sex;
    private PostUpdateUserDataModel updateUserModel;
    private String headURL = "";
    private String userID;
    private Userinfo userInfo;
    private CircleImageView image_head;
    private SelectSexPop selectPhoto;
    private String cameraPath;
    private String cameraPath_s;
    private UpImageModel imageModel;
    private UserDao userDao;

    @Override
    protected void initGetIntent() {
        userID = SpUtils.getUserID();

        userInfo = (Userinfo) getIntent().getSerializableExtra(Constants.INTENT_KEY.EDITUSERDATA);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_psn;
    }

    @Override
    public void initView() {
        findView(R.id.edit_psn_tv_save).setOnClickListener(this);
        findView(R.id.edit_psn_layout_head).setOnClickListener(this);

        edit_nickname = findView(R.id.edit_psn_edit_nickname);
        edit_sign = findView(R.id.edit_psn_edit_sign);
        edit_job = findView(R.id.edit_psn_edit_job);
        edit_company = findView(R.id.edit_psn_edit_company);
        edit_university = findView(R.id.edit_psn_edit_university);
        edit_city = findView(R.id.edit_psn_edit_city);

        tv_sex = findView(R.id.edit_psn_tv_sex);
        tv_birthday = findView(R.id.edit_psn_tv_birthday);

        image_head = findView(R.id.edit_psn_image_head);
    }

    @Override
    public void initData() {
        userDao = new UserDao(this);

        String sex = "保密";
        headURL = userInfo.getHead();
        edit_nickname.setText(userInfo.getName());
        edit_sign.setText(userInfo.getSign());
        edit_job.setText(userInfo.getJob());
        edit_company.setText(userInfo.getCompany());
        edit_university.setText(userInfo.getSchool());
        edit_city.setText(userInfo.getAddress());
        UIL.load(image_head, userInfo.getHead());
        if (userInfo.getSex() == 0){
            sex = "男";
        }else if (userInfo.getSex() == 1){
            sex = "女";
        }
        tv_sex.setText(sex);
        tv_birthday.setText(userInfo.getBirthday());

        updateUserModel = new PostUpdateUserDataModel();
        updateUserModel.setViewModelInterface(this);

        imageModel = new UpImageModel();
        imageModel.setViewModelInterface(this);

        initSelectPhoto();
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id){
            case R.id.edit_psn_tv_save:
                String name = edit_nickname.getText().toString();
                String sex = "1";
                String birthday = tv_birthday.getText().toString();
                String sign = edit_sign.getText().toString();
                String job = edit_job.getText().toString();
                String company = edit_company.getText().toString();
                String university = edit_university.getText().toString();
                String city = edit_city.getText().toString();
                userInfo.setName(name);
                userInfo.setSex(3);
                userInfo.setBirthday(birthday);
                userInfo.setSign(sign);
                userInfo.setJob(job);
                userInfo.setCompany(company);
                userInfo.setSchool(university);
                userInfo.setAddress(city);
                userInfo.setHead(headURL);
                updateUserModel.start(userID, headURL, name, sex, birthday, sign, job, company, university, city);
                break;

            case R.id.edit_psn_layout_head:
                selectPhoto.show(getSupportFragmentManager(), "photo");
                break;
        }
    }

    private void initSelectPhoto() {
        selectPhoto = new SelectSexPop("拍照", "相册", "取消");
        selectPhoto.setOnSelectLayout(new SelectSexPop.OnSelectLayout() {
            @Override
            public void onClickFirst(String str1, String tag) {
                // TODO: 2016/2/19 拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = getOutputMediaFileUri();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }

            @Override
            public void onClickSecond(String str2, String tag) {
                // TODO: 2016/2/19 相册
                Intent intent = new Intent(EditPsnActivity.this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                startActivityForResult(intent, REQUEST_PICK_PHOTO);
            }

            @Override
            public void onClickThird(String tag) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_PHOTO && null != data) {
            ArrayList<String> imgs = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            cameraPath = imgs.get(0);
            UIL.load(image_head, "file://" + cameraPath);
            upImageHead();
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO && cameraPath != null) {
            UIL.load(image_head, "file://" + cameraPath);
            upImageHead();
        }
    }

    private void upImageHead() {
        cameraPath_s = MyUtils.saveBitmapFile(cameraPath, 300, 300);
        if (cameraPath.equals(cameraPath_s)) {
            Log.e("upImageHead:", "压缩失败");
        }
        File file = new File(cameraPath_s);
        showLoadingDialog();
        imageModel.start(file, "", "", "");
    }

    /**
     * 用于拍照时获取输出的Uri
     *
     * @return
     * @version 1.0
     * @author zyh
     */
    protected Uri getOutputMediaFileUri() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(FileManager.getImagePath(this) + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
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

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (imageModel.getTag() == tag){
            ImageUrlResponse response = (ImageUrlResponse) result;
            headURL = HttpConstanst.BASE_URL + response.getData();
            Log.e("url", headURL);
            dismissLoadingDialog();
            return;
        }
        if (updateUserModel.getTag() == tag){
            userDao.update("head", headURL);
            Toast.makeText(EditPsnActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Constants.RECEIVER_KEY.EDITUSER);
            intent.putExtra(Constants.INTENT_KEY.EDIT_NICKNAME, userInfo);
            sendBroadcast(intent);
            finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if (imageModel.getTag() == tag){
            dismissLoadingDialog();
        }
        Toast.makeText(EditPsnActivity.this, codeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(EditPsnActivity.this, getString(R.string.exception), Toast.LENGTH_SHORT).show();
        dismissLoadingDialog();
    }
}
