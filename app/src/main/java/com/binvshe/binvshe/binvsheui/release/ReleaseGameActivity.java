package com.binvshe.binvshe.binvsheui.release;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddSpecialModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.sdsmdg.tastytoast.TastyToast;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;
import org.srr.dev.util.PhotoUtils;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/18
 */
public class ReleaseGameActivity extends BaseActivity implements IViewModelInterface {

    public static final String RELEASE_IMGS_NUM = "intent_selected_picture";
    private static final String KEY_SYSTEM_TYPE = "SYSTEM_TYPE";
    public static final int PICK_UP_IMAGE = 201;
    private static final int PICK_UP_COVER = 123;
    @Bind(R.id.btn_title_back)
    ImageView mBtnTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_title_more)
    TextView mTvTitleMore;
    @Bind(R.id.ibtnSelectCover)
    ImageButton mIbtnSelectCover;
    @Bind(R.id.etTitle)
    EditText mEtTitle;
    @Bind(R.id.radio_serial)
    RadioGroup mRadioSerial;
    @Bind(R.id.etDerivation)
    EditText mEtDerivation;
    @Bind(R.id.etContent)
    EditText mEtContent;
    @Bind(R.id.rcvProduction)
    RecyclerView mRcvProduction;


    AddSpecialModel mAddSpecialModel;
    String mUserID, mName, mType, mDesc, mOriginal;

    @InjectExtra(KEY_SYSTEM_TYPE)
    String mSystemType;

    public ArrayList<String> mImgs = new ArrayList<>();
    public ArrayList<String> coverImage = new ArrayList<>();
    public ArrayList<String> totalimage = new ArrayList<String>();
    private PhotoAdapter mPhotoAdapter;

    public static void start(Context context, String systemType) {
        Intent starter = new Intent(context, ReleaseGameActivity.class);
        starter.putExtra(KEY_SYSTEM_TYPE, systemType);
//        SubjectTypeEntity
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        Dart.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_upload;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTvTitle.setText("上传");
        mTvTitleMore.setText("发布");
        mTvTitleMore.setVisibility(View.VISIBLE);
        if (AccountManager.getInstance().isLogin()) {
            mUserID = AccountManager.getInstance().getUserLogin().getUser().getId() + "";
        } else {
            String message = getString(R.string.not_login);
            TastyToast.makeText(this, message, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
            Intent ii = new Intent(this, ChooseActivity.class);
            startActivity(ii);
        }
        initRadio();
    }

    private void initRadio() {
        mRadioSerial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mOriginal = null;
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.rbtnColleagues:
                        mEtDerivation.setVisibility(View.VISIBLE);
                        mType = "1";
                        break;
                    default:
                        mEtDerivation.setText("");
                        mEtDerivation.setVisibility(View.GONE);
                        mType = "3";
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        mAddSpecialModel = new AddSpecialModel();
        mPhotoAdapter = new PhotoAdapter();
        mRcvProduction.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPhotoAdapter.setData(mImgs);
        mRcvProduction.setAdapter(mPhotoAdapter);

        mAddSpecialModel.setViewModelInterface(this);
    }

    @Override
    public void refreshData() {

    }


    @OnClick({R.id.btn_title_back, R.id.tv_title_more, R.id.ibtnSelectCover})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.tv_title_more:
                if (judgePara()) {
                    totalimage.clear();
                    totalimage.addAll(coverImage);
                    totalimage.addAll(mImgs);
                    ArrayList<File> files = new ArrayList<File>();
                    List<String> filesString = PhotoUtils.saveBitmapFileList(totalimage, com.binvshe.binvshe.constants.Constants.FILE.FILE_CACHE, 600, 600);
                    for (String temp : filesString) {
                        File imageFile = new File(temp);
                        files.add(imageFile);
                    }
//                    luBanBitmap(files);
                    mAddSpecialModel.start("", mName, mUserID, mType, mSystemType, mDesc, mOriginal,
                            "", "", "0", files, "", "1", "");
                    TastyToast.makeText(this, getString(R.string.releaseing), TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
                    showLoadingDialog();
                }
                break;
            case R.id.ibtnSelectCover:
                //封面选择
                Intent intent = new Intent(this, SelectAlbumActivity.class);
                intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 1);
                intent.putExtra(RELEASE_IMGS_NUM, 0);
                startActivityForResult(intent, PICK_UP_COVER);
                break;
        }
    }

    private boolean judgePara() {
        mName = mEtTitle.getText().toString();
        mOriginal = mEtDerivation.getText().toString();
        mDesc = mEtContent.getText().toString();


        if (TextUtils.isEmpty(mName)) {
            TastyToast.makeText(this, "请输入标题", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            return false;
        } else if (TextUtils.isEmpty(mType)) {
            TastyToast.makeText(this, "请选择类型", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            return false;
        } else if (TextUtils.isEmpty(mDesc)) {
            TastyToast.makeText(this, "介绍下你的作品呗", TastyToast.LENGTH_SHORT, TastyToast.INFO);
            return false;
        } else if (android.text.TextUtils.equals(mType, "1") && android.text.TextUtils.isEmpty(mOriginal)) {
            TastyToast.makeText(this, "原著是谁？", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
            return false;
        } else if (mImgs.size() < 1) {
            TastyToast.makeText(this, "作品呢？", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            return false;
        } else if (coverImage.size() <= 0) {
            TastyToast.makeText(this, "封面没传", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else if (requestCode == PICK_UP_COVER && null != data) {
            coverImage = (ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE);
            UIL.load(mIbtnSelectCover, "file://" + coverImage.get(0));
        } else if (requestCode == PICK_UP_IMAGE && null != data) {
            mImgs.addAll((ArrayList<String>) data.getSerializableExtra(SelectAlbumActivity.INTENT_SELECTED_PICTURE));
            mPhotoAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        dismissLoadingDialog();
        TastyToast.makeText(this, "发布成功", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
        this.finish();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        TastyToast.makeText(this, codeMsg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        TastyToast.makeText(this, exception.toString(), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    List<File> upFiles = new ArrayList<>();

    public void luBanBitmap(List<File> files) {
        upFiles.clear();
        for (File file : files) {
            Luban.get(this)
                    .load(file)                   //传人要压缩的图片
                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                    .setCompressListener(new OnCompressListener() { //设置回调

                        @Override
                        public void onStart() {
                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //TODO 压缩成功后调用，返回压缩后的图片文件
                            upFiles.add(file);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //TODO 当压缩过去出现问题时调用
                        }
                    }).launch();    //启动压缩
        }
    }


    public class PhotoAdapter extends RecyclerViewDataAdapter<String, PhotoAdapter.VH> {


        @Override
        public int getItemCount() {
            return getData().size() + 1;
        }

        @Override
        public void onBindHolder(VH viewHolder, final int i, String url) {
            if (i == getData().size()) {
                viewHolder.mItemReleaseImg.setImageResource(R.drawable.release_add_img);
                viewHolder.mItemReleaseDeleteImg.setVisibility(View.GONE);
                viewHolder.mItemReleaseImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), SelectAlbumActivity.class);
                        intent.putExtra(SelectAlbumActivity.INTENT_MAX_NUM, 200);
                        intent.putExtra(RELEASE_IMGS_NUM, getData().size());
                        startActivityForResult(intent, PICK_UP_IMAGE);
                    }
                });
            } else {
                UIL.load(viewHolder.mItemReleaseImg, "file://" + url);
                viewHolder.mItemReleaseDeleteImg.setVisibility(View.VISIBLE);
                viewHolder.mItemReleaseDeleteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData().remove(i);
                        notifyDataSetChanged();
                    }
                });
            }
        }

        @Override
        public VH getViewHolder(View view) {
            return new VH(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_release_img_list;
        }

        class VH extends RecyclerView.ViewHolder {
            @Bind(R.id.item_release_img)
            ImageView mItemReleaseImg;
            @Bind(R.id.item_release_delete_img)
            ImageView mItemReleaseDeleteImg;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
