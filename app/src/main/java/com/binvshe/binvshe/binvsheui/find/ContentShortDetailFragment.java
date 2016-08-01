package com.binvshe.binvshe.binvsheui.find;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.ImageAdapter;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.dynamic.DynamicDetail;
import com.binvshe.binvshe.entity.dynamic.DynamicRes;
import com.binvshe.binvshe.entity.dynamic.DynamicResources;
import com.binvshe.binvshe.entity.dynamic.DynamicSpe;
import com.binvshe.binvshe.entity.dynamic.Photo;
import com.binvshe.binvshe.http.model.GetDynamicModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.BasePageResponse;
import com.binvshe.binvshe.http.response.DynamicResponse;
import com.bumptech.glide.Glide;


import org.srr.dev.adapter.FullyLinearLayoutManager;
import org.srr.dev.util.ToastUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.util.ViewSizeUtils;
import org.srr.dev.view.xrecyclerview.RecyclerViewHeader;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 漫画/Coser详情Fragment
 */
public class ContentShortDetailFragment extends BaseFragment implements IViewModelInterface{

    private ImageView iv_cover;
    private TextView tv_content, tv_roleinfo, tv_cameraman, tv_original,tv_attention;

    private GetDynamicModel dynamicModel;

    private ImageAdapter imgadpter;

    private String mId;

    private XRecyclerView recyclerView;

    private ArrayList<Photo> mPhotoList;


    private TextView tv_page, tv_last_page, tv_next_page;

    private BasePageResponse mPage;

    private String showtype = "0";



    private RefreshCallBackImpl mCallBack;
//    private RecyclerViewHeader mHeader;
//    public int w;


    public void setmCallBack(RefreshCallBackImpl mCallBack) {
        this.mCallBack = mCallBack;
    }
//    public ContentShortDetailFragment(String showtype, String id, ContentDetailActivity activity){
//        this.contentDetailActivity = activity;
//        this.mId = id;
//        this.showtype=showtype;
//    }

    public static ContentShortDetailFragment newInstance(String showtype, String id, TextView tv_attention) {
        Bundle args = new Bundle();
        ContentShortDetailFragment fragment = new ContentShortDetailFragment();
        fragment.tv_attention=tv_attention;
//        fragment.contentDetailActivity=activity;
        args.putString("showtype",showtype);
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(getArguments()!=null)
        {
            showtype=getArguments().getString("showtype");
            mId=getArguments().getString("id");
        }
        super.onCreate(savedInstanceState);
    }

    //    public static void newInstance(){
//        ContentShortDetailFragment fr = new ContentShortDetailFragment();
//        fr.get
//
//    }
//

    private void refreshSpecial(DynamicSpe dynamicSpe){
        String original = dynamicSpe.getOriginal();
        String cameraman = dynamicSpe.getCameraman();
        String role = dynamicSpe.getRoleinfo();


        if(!TextUtils.isEmpty(original)){
            tv_original.setText(getString(R.string.subject_original, original));
        }else{
            tv_original.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(cameraman)){
            tv_cameraman.setText(getString(R.string.subject_cameraman, cameraman));
        }else{
            tv_cameraman.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(role)){
            tv_roleinfo.setText(getString(R.string.subject_roleinfo, role));
        }else{
            tv_roleinfo.setVisibility(View.GONE);
        }
        String cover = dynamicSpe.getPhotos();
       //UIL.loadAutoHeight(cover,w,iv_cover);
        UIL.load(iv_cover,cover);
    }

    @Override
    public int getLayoutId() {
        if(showtype.equals("0")){
            //here set story text value;  通过调用获取详情的接口返回小说对象。
            return R.layout.content_story_detail_layout;
        }else{
            return R.layout.content_short_detail_layout;
        }

    }



    @Override
    protected void initView(View contentView) {

        View v= LayoutInflater.from(getContext()).inflate(R.layout.fragment_content_short_layout,
                (ViewGroup) contentView,false);

        tv_content = (TextView) v.findViewById(R.id.tv_content);
        tv_cameraman = (TextView) v.findViewById(R.id.tv_cameraman);
        tv_roleinfo = (TextView) v.findViewById(R.id.tv_roleinfo);
        tv_original = (TextView) v.findViewById(R.id.tv_original);
        iv_cover= (ImageView) v.findViewById(R.id.iv_cover);
//        XRecyclerView recyclerView;
//        recyclerView.addHeaderView();
        tv_page = (TextView) contentView.findViewById(R.id.tv_page);
        tv_last_page = (TextView) contentView.findViewById(R.id.tv_last_page);
        tv_next_page = (TextView) contentView.findViewById(R.id.tv_next_page);
        tv_last_page.setOnClickListener(this);
        tv_next_page.setOnClickListener(this);

//        recyclerView = (XRecyclerView) contentView.findViewById(R.id.rv_images);
        //recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
       // mHeader.attachTo(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addHeaderView(v);

       // w=contentView.getWidth();
//        w=getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
    }



    @Override
    protected void initData() {
       // Log.e("!!!!!!!!!!!!!!!!!!!","进入log！！！！！！！！！！！！！！:"+mId);
        mPhotoList = new ArrayList<Photo>();
//        imgadpter = new ImageAdapter(mPhotoList);
        imgadpter=new ImageAdapter();
        imgadpter.setData(mPhotoList);
       // recyclerView.setLayoutManager();
        recyclerView.setAdapter(imgadpter);

        dynamicModel = new GetDynamicModel();
        dynamicModel.setViewModelInterface(this);
        dynamicModel.start(mId);
    }

    @Override
    protected void onClickView(View view, int id) {
        switch (id){
            case R.id.tv_next_page:
                boolean isNext = mPage.getNext();
                if(isNext){
                    String page = mPage.getCurPage() + 1 + "";
                    dynamicModel.start(mId, page);
                }else{
                    ToastUtil.showToast(getContext(), R.string.next_page_tip);
                }

                break;
            case R.id.tv_last_page:
                boolean isPre = mPage.isPre();
                if(isPre){
                    String page = mPage.getCurPage() - 1 + "";
                    dynamicModel.start(mId, page);
                }else{
                    ToastUtil.showToast(getContext(), R.string.last_page_tip);
                }
                break;
        }

    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showLoadingDialog();
    }

    /**
    @Override
    public void onSuccessLoad(int tag, Object result) {
        if(tag == dynamicModel.getTag()){
            DynamicResponse response = (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            refreshSpecial(spec);
            mPage = detail.getResources();
            int currentPage = mPage.getCurPage();
            int pageCount = mPage.getPageCount();
            boolean isNext = mPage.getNext();
            if(!detail.getIsAttention().equals("0"))
            {
                tv_attention.setText(R.string.user_attention_true);
            }
            if(currentPage == 1 && !isNext){
                //只有一页
                findView(R.id.page_layout).setVisibility(View.GONE);
            }else{
                findView(R.id.page_layout).setVisibility(View.VISIBLE);
                tv_page.setText(getString(R.string.content_page_num, currentPage + "", pageCount + ""));
            }


            String replyNum = detail.getReplyCount();
            contentDetailActivity.refreshUserInfo(spec, replyNum);
            mCallBack.refreshCallback(spec, replyNum);
            List<DynamicResources> list = detail.getResources().getDatas();
            if(list != null && list.size() > 0){
                DynamicResources resource = list.get(0);
                String text;
                if(resource.getType()!=0)
                {
                    text=spec.getDesc();
                }
                else {
                    text=resource.getText();
                }

                tv_content.setText(text);

                ArrayList<Photo> imageList = resource.getPhotos();
                mPhotoList.clear();
                mPhotoList.addAll(imageList);
                imgadpter.notifyDataSetChanged();
//                if(mPhotoList.size() == 0){
//                    recyclerView.setVisibility(View.GONE);
//                }
            }
        }
        dismissLoadingDialog();

    }
     **/
    @Override
    public void onSuccessLoad(int tag, Object result)
    {
        if(tag==dynamicModel.getTag())
        {
            DynamicResponse response= (DynamicResponse) result;
            DynamicDetail detail = response.getData();
            DynamicSpe spec = detail.getSpecial();
            List<DynamicResources> list = detail.getResources().getDatas();
            mPhotoList.clear();
            mPhotoList.addAll(list.get(0).getPhotos());
            mPhotoList.addAll(list.get(0).getPhotos());
            mPhotoList.addAll(list.get(0).getPhotos());
            mPhotoList.addAll(list.get(0).getPhotos());
            mPhotoList.addAll(list.get(0).getPhotos());
            imgadpter.notifyDataSetChanged();
        }
        dismissLoadingDialog();
    }


    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        ToastUtil.showToast(getContext(), codeMsg);

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        ToastUtil.showToast(getContext(), exception.getMessage());
        exception.printStackTrace();

    }

    @Override
    public void onDestroy() {
//        mPhotoList.clear();
//        imgadpter.notifyDataSetChanged();
        super.onDestroy();

    }

    public interface RefreshCallBackImpl
    {
        public void refreshCallback(DynamicSpe spe, String replyNum);
    }
}
