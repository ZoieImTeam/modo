package com.binvshe.binvshe.binvsheui.release;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.binvsheui.dialog.ReleaseDialog;
import com.binvshe.binvshe.binvsheui.location.SelectPicDialogFragment;
import com.binvshe.binvshe.entity.SysTypeEntitiy;
import com.binvshe.binvshe.http.model.GetSysTypeModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.SysTypeResponse;

import org.srr.dev.util.KeyboardUtils;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

import java.util.ArrayList;
import java.util.List;

public class CreateResActivity extends AbsFragmentActivity implements IViewModelInterface {

    public static final int PICK_UP_IMAGE = 201;
    public static final int TAKE_PHOTO_IMAGE = 203;

    private TextView catetory;
    private String mType;
    private EditText editTitle;
    private EditText editDesc;
    private GetSysTypeModel model;
    private List<SysTypeEntitiy> sysList = new ArrayList<>();
    private OptionsPopupWindow mSpecialType;
    private String categroyId;
    private String cameraPath;
    private ImageView mSysImg;
    private SelectPicDialogFragment dialog;

    @Override
    protected void initGetIntent() {
        mType = getIntent().getStringExtra(ReleaseDialog.UP_TYPE);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        TakePhoto.getInstance().initTakePhoto(arg0);
        super.onCreate(arg0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_createres;
    }

    @Override
    public void initView() {
        Toolbar toolbar = findView(R.id.ac_createres_toolbar);
        setSupportActionBar(toolbar);
        setTitle("创建新连载");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPhotoDialog();
        editTitle = findView(R.id.et_ac_createres_title);
        editDesc = findView(R.id.et_ac_createres_desc);
        catetory = findView(R.id.tv_ac_release_edit_catetory);
        mSysImg = findView(R.id.iv_ac_release_img);
        mSysImg.setOnClickListener(this);
        catetory.setOnClickListener(this);
        findView(R.id.b_ac_createres_next).setOnClickListener(this);

    }

    @Override
    public void initData() {
        model = new GetSysTypeModel();
        model.setViewModelInterface(this);
        model.start(mType);
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
        if (tag == model.getTag()) {
            SysTypeResponse response = (SysTypeResponse) result;
            sysList.clear();
            sysList.addAll(response.getData());
            initTopicsCategory();
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.iv_ac_release_img:
                dialog.show(getSupportFragmentManager(), "photo_dialog");
                break;
            case R.id.tv_ac_release_edit_catetory:
                if (sysList.size() > 0) {
                    KeyboardUtils.collapseKeyboard(this);
                    mSpecialType.showAsDropDown(view);
                } else {
                    Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.b_ac_createres_next:
                String title = editTitle.getText().toString().trim();
                String desc = editDesc.getText().toString().trim();
                if (TextUtils.isEmpty(cameraPath)) {
                    Toast.makeText(this, "请选择封面", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(desc)) {
                    Toast.makeText(this, "请输入简介", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(categroyId) || "0".equals(categroyId)) {
                    Toast.makeText(this, "请选择类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, ReleaseActivity.class);
                intent.putExtra(ReleaseActivity.SYS_TITLE, title);
                intent.putExtra(ReleaseActivity.SYS_DESC, desc);
                intent.putExtra(ReleaseActivity.SYS_PHOTO, cameraPath);
                intent.putExtra(ReleaseActivity.SYS_TYPE, categroyId);
                intent.putExtra(MyResActivity.TYPE, getIntent().getStringExtra(MyResActivity.TYPE));
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addPhotoDialog() {

        dialog = new SelectPicDialogFragment();
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
                Intent intent = new Intent(CreateResActivity.this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                intent.putExtra(ReleaseActivity.RELEASE_IMGS_NUM, 0);
                startActivityForResult(intent, PICK_UP_IMAGE);
            }
        });
    }

    private void initTopicsCategory() {
        final ArrayList<String> type = new ArrayList<>();
        for (SysTypeEntitiy specialType : sysList) {
            type.add(specialType.getName());
        }
        mSpecialType = new OptionsPopupWindow(this);
        mSpecialType.setPicker(type);
        mSpecialType.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2) {
                catetory.setText(sysList.get(i).getName());
                categroyId = sysList.get(i).getId() + "";
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == PICK_UP_IMAGE && null != data) {
            ArrayList<String> imgs = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            if (imgs != null && imgs.size() > 0) {
                cameraPath = imgs.get(0);
                UIL.load(mSysImg, "file://" + imgs.get(0));
            }
        }

        if (requestCode == TAKE_PHOTO_IMAGE) {
            String cameraPath = TakePhoto.getInstance().getCameraPath();
            if (cameraPath != null) {
                this.cameraPath = cameraPath;
                UIL.load(mSysImg, "file://" + cameraPath);
                TakePhoto.getInstance().clear();
            }
        }

    }
}