package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.dialog.ShareDialog;
import com.binvshe.binvshe.binvsheui.home.GamePopupwindow;
import com.binvshe.binvshe.binvsheui.login.ChooseActivity;
import com.binvshe.binvshe.entity.ActivityList.ActivityData;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetActivityDetailModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetActivityDetailResponse;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.sdsmdg.tastytoast.TastyToast;

import org.srr.dev.base.BaseActivity;
import org.srr.dev.util.UIL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/18
 */
public class ActivityGameActivity extends BaseActivity implements IViewModelInterface {

    private static final String KEY_ACTIVITY_ID = "ACTIVITY_ID";

    @Bind(R.id.ivActivityImage)
    ImageView mIvActivityImage;
    @Bind(R.id.tvActivityTitle)
    TextView mTvActivityTitle;
    @Bind(R.id.tvActivityTime)
    TextView mTvActivityTime;
    @Bind(R.id.btnUpload)
    TextView mBtnUpload;
    @Bind(R.id.btn_title_back)
    ImageView mBtnTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_title_more)
    ImageView mIvTitleMore;
    @Bind(R.id.tv_title_more)
    TextView mTvTitleMore;
    @Bind(R.id.rl_ac_navi_title)
    View mRlBackTitle;

    DetailWebFr mWebFr;

    @InjectExtra(KEY_ACTIVITY_ID)
    int activityID = 0;

    private String userID;

    private String mTitle = null;


    private GetActivityDetailModel getActivityDetailModel;
    private ArrayList<String> listBanner = new ArrayList<>();

    public static void start(Context context, int activityID) {
        Intent starter = new Intent(context, ActivityGameActivity.class);
        starter.putExtra(KEY_ACTIVITY_ID, activityID);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        Dart.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_activity_game;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mRlBackTitle.setAlpha(0f);
        mIvTitleMore.setVisibility(View.VISIBLE);
        mIvTitleMore.setImageResource(R.mipmap.icon_share);
        mTvTitle.setText("活动详情");

        if (AccountManager.getInstance().isLogin()) {
            userID = AccountManager.getInstance().getUserLogin().getUser().getId() + "";
        } else {
            String message = getString(R.string.not_login);
            TastyToast.makeText(this, message, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
            Intent ii = new Intent(this, ChooseActivity.class);
            startActivity(ii);
        }

        getActivityDetailModel = new GetActivityDetailModel();
        getActivityDetailModel.setViewModelInterface(this);
        getActivityDetailModel.start(userID, activityID + "");
        mWebFr = DetailWebFr.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.wvActivityContent, mWebFr).commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }

    @OnClick({R.id.btnUpload, R.id.btn_title_back, R.id.iv_title_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpload:
                GamePopupwindow popupwindow = new GamePopupwindow(this, view);
                popupwindow.show();
                break;
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.iv_title_more:
                DynamicSpe spe = new DynamicSpe();
                spe.setName(mTitle);
                spe.setDesc("");
                spe.setPhotos(listBanner.get(0));
                ShareDialog dialog = new ShareDialog();
                dialog.setOnDialogLisetener(new ShareDialog.OnDialogLisetener() {
                    @Override
                    public void shareStutas(String message) {
                        TastyToast.makeText(ActivityGameActivity.this, message, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }

                    @Override
                    public void startShare() {

                    }
                });
                dialog.show(getSupportFragmentManager(), "share");
                break;
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
        if (tag == getActivityDetailModel.getTag()) {
            GetActivityDetailResponse response = (GetActivityDetailResponse) result;
            ActivityData data = response.getData();
            final String html = data.getIntroduces();
            mWebFr.setHtml(html);
            String photos = data.getPhotos();
            mTitle = data.getName();
            mTvActivityTitle.setText(mTitle);
            String date=getString(R.string.activityTime,StringParDate(data.getStartdate()),StringParDate(data.getEnddate()));
            Log.d("kyluzoi", date);
            mTvActivityTime.setText(date);
            if (photos != null) {
                String[] split = photos.split(",");
//                listBanner.toArray(split);
                for (int i = 0; i < split.length; i++) {
                    listBanner.add(split[i]);
                }
            }
            if (listBanner.size() > 0) {
                UIL.load(mIvActivityImage, listBanner.get(0));
            }
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        TastyToast.makeText(this, codeMsg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
    }

    public String StringParDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = fmt.parse(date);
            DateFormat fmt1 = new SimpleDateFormat("MM-dd");
            String dateString = fmt1.format(date1);
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
