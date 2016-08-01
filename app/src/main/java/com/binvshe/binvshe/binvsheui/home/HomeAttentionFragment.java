package com.binvshe.binvshe.binvsheui.home;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity1;
import com.binvshe.binvshe.binvsheui.message.ConversationActivity;
import com.binvshe.binvshe.binvsheui.usercenter.AnotherUserInfoActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserCenterActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.GetAttentionListData;
import com.binvshe.binvshe.entity.attention.AttentionDatas;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.AddAttentionUserModel;
import com.binvshe.binvshe.http.model.CancelAttentionUserModel;
import com.binvshe.binvshe.http.model.GetAttentionListModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetAttentionListResponse;
import com.binvshe.binvshe.util.LoginUtils;
import com.binvshe.binvshe.view.AnimationUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;
import org.srr.dev.view.xrecyclerview.LoadingMoreFooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HomeAttentionFragment extends LinearListFragment implements IViewModelInterface{

    private AttentionAdapter mAdapter;

    protected ArrayList<AttentionDatas> datas=new ArrayList<AttentionDatas>();
    protected GetAttentionListModel getAttentionListModel;
    protected GetAttentionListData attentionListDatas;
    protected CancelAttentionUserModel cancelAttentionUserModel=new CancelAttentionUserModel();
    protected AddAttentionUserModel addAttentionUserModel=new AddAttentionUserModel();
    protected String name;
    protected int page=1;
    private String mId;
    private List<SubjectEntity> mSubjectEntity;
    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        mAdapter = new AttentionAdapter();
        setSrlayoutEnable(true);
        return mAdapter;
    }

    @Override
    public void onLoadingMore() {
        page++;
        start(mId, page + "");

    }

    @Override
    public void onPullRefresh() {
        page=1;
        start(mId, "1");
        //((LoadingMoreFooter)this.getmList().getFootView()).setState(LoadingMoreFooter.STATE_COMPLETE);
        this.getmList().resetStatus();
    }

    protected void start(String id,String pageNo)
    {
        getAttentionListModel.start(id, pageNo);
    }

    @Override
    protected void initData() {
        mId=AccountManager.getInstance().getUserInfo().getId()+"";

        getAttentionListModel=new GetAttentionListModel();
        start(mId, "1");
        getAttentionListModel.setViewModelInterface(this);

        //getAttentionListModel.start(AccountManager.getInstance().getUserInfo().getId(),"1");
    }



    class AttentionAdapter extends RecyclerViewAdapter<AttentionAdapter.ViewHolder> {

        protected class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView icon;
            TextView attention, user, fans, send, title1, title2, look;
            ImageView bg1,bg2;
            RecyclerView tag;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }



        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, final int position) {
            final ViewHolder holder = (ViewHolder) viewHolder;
            final AttentionDatas data = datas.get(position);
            holder.attention.setText("取消关注");
            holder.user.setText(data.getName());
            holder.fans.setText("粉丝数：" + datas.get(position).getFans());
            final String uid=data.getUid()+"";
            final String uname=data.getName();
            mSubjectEntity=data.getList();
            UIL.load(holder.icon, datas.get(position).getHead());
            //头像点击
            holder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mId.equals(uid))
                    {
                        startActivity(new Intent(getActivity(), UserCenterActivity.class));
                    }
                    else
                    {
                        Intent ii=new Intent(getActivity(), AnotherUserInfoActivity.class);
                        ii.putExtra("userid", uid);
                        startActivity(ii);
                    }
                }
            });

            //取消关注按钮
            holder.attention.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.attention.getText().equals("取消关注"))
                    {
                        holder.attention.setText("关注");
                        cancelAttentionUserModel.setViewModelInterface(HomeAttentionFragment.this);
                        cancelAttentionUserModel.start(mId, data.getUid()+"");
                    }
                    else if(holder.attention.getText().equals("关注"))
                    {
                        holder.attention.setText("取消关注");
                        addAttentionUserModel.setViewModelInterface(HomeAttentionFragment.this);
                        addAttentionUserModel.start(mId, data.getUid()+"");
                    }
                    AnimationUtils.shake(holder.attention,3,200,false);
                }

            });


            //私信按钮
            holder.send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConversationActivity.newInstance(getActivity(), uid, uname);
                    boolean login = LoginUtils.isLogin(HomeAttentionFragment.this, getActivity());
                }
            });

            //专题详情
            if(datas.get(position).getList().size()>0) {
                UIL.load(holder.bg1, datas.get(position).getList().get(0).getPhotos());
                holder.title1.setText(datas.get(position).getList().get(0).getName());
                holder.bg1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent=new Intent(getActivity(), ContentDetailActivity.class);
//                        intent.putExtra(GlobalConfig.EXTRA_OBJECT,datas.get(position).getList().get(0));
//                        startActivity(intent);
                        ContentDetailActivity1.newInstance(getActivity(),datas.get(position).getList().get(0));
                    }
                });
            }
            if(datas.get(position).getList().size()>1) {
                UIL.load(holder.bg2, datas.get(position).getList().get(1).getPhotos());
                holder.title2.setText(datas.get(position).getList().get(1).getName());
                holder.bg2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent=new Intent(getActivity(), ContentDetailActivity.class);
//                        intent.putExtra(GlobalConfig.EXTRA_OBJECT,datas.get(position).getList().get(1));
//                        startActivity(intent);
                        ContentDetailActivity1.newInstance(getActivity(),datas.get(position).getList().get(1));
                    }
                });
            }


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_attention, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.icon = (CircleImageView) v.findViewById(R.id.civ_home_attention_user);
            holder.user = (TextView) v.findViewById(R.id.tv_home_attention_user);
            holder.fans = (TextView) v.findViewById(R.id.tv_home_attention_usersign);
            holder.attention = (TextView) v.findViewById(R.id.tv_home_attention_attention);
            holder.send = (TextView) v.findViewById(R.id.tv_home_attention_send);
            holder.title1= (TextView) v.findViewById(R.id.tv_home_attention_tx1);
            holder.title2= (TextView) v.findViewById(R.id.tv_home_attention_tx2);
            holder.bg1 = (ImageView) v.findViewById(R.id.iv_home_attention_bg1);
            holder.bg2= (ImageView) v.findViewById(R.id.iv_home_attention_bg2);
            return holder;
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }



    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showSwiRefreshLayout();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {

        if(tag==getAttentionListModel.getTag())
        {
            GetAttentionListResponse response= (GetAttentionListResponse) result;
            attentionListDatas=response.getData();
            if(attentionListDatas.getCurPage()==1)
            {
                datas.clear();
            }
            datas.addAll(attentionListDatas.getDatas());
            mAdapter.notifyDataSetChanged();
            this.dismissSwiRefreshLayout();
            this.getmList().loadMoreComplete();

//            if(page==attentionListDatas.getPageCount()) {
//
//            }
            if(tag==addAttentionUserModel.getTag())
            {
                Toast.makeText(getContext(),"关注成功",Toast.LENGTH_SHORT).show();
            }
            if(tag==cancelAttentionUserModel.getTag())
            {
                Toast.makeText(getContext(),"取消关注",Toast.LENGTH_SHORT).show();
            }
        }
        this.dismissSwiRefreshLayout();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if (tag==getAttentionListModel.getTag())
        {
            this.dismissSwiRefreshLayout();
            Toast.makeText(getContext(),"异常...",Toast.LENGTH_SHORT).show();
        }
        this.dismissSwiRefreshLayout();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        this.dismissSwiRefreshLayout();
    }

}
