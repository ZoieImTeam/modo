package com.binvshe.binvshe.binvsheui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.dialog.ShareDialog;
import com.binvshe.binvshe.entity.Comment;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetCommentModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.model.PostAddCommentModel;
import com.binvshe.binvshe.http.response.CommentResponse;
import com.binvshe.binvshe.util.LoginUtils;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;
import org.srr.dev.util.KeyboardUtils;
import org.srr.dev.util.TimeUtil;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity implements IViewModelInterface {
    private static final String RESOURCES_ID = "com.binvshe.binvshe.comment.id";
    private static final String RESOURCES_ZAN = "com.binvshe.binvshe.comment.zanNum";

    XRecyclerView mList;
    SwipeRefreshLayout srl;
    private TextView tvTitle;
    private TextView tvComment;
    private TextView ivShare;
    private TextView tvZan;
    private EditText etContent;
    private TextView tvSend;
    private CommentAdapter commentAdapter;
    private ArrayList<Comment.CommentData> commentDatas = new ArrayList<>();
    private GetCommentModel commentModel;
    private PostAddCommentModel addCommentModel;
    private Comment mComment;
    private String mResourcesId = "108";
    private String content;
    private String mZanNum="0";

    @Override
    protected void initGetIntent() {
        mResourcesId = getIntent().getStringExtra(RESOURCES_ID);
        mZanNum=getIntent().getStringExtra(RESOURCES_ZAN);
    }

    public static void newInstance(Activity activity, String rid,String zan_num) {
        final Intent intent = new Intent(activity, CommentActivity.class);
        intent.putExtra(RESOURCES_ID, rid);
        intent.putExtra(RESOURCES_ZAN,zan_num);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_comment;
    }

    @Override
    public void initView() {
        tvComment = (TextView) findViewById(R.id.tv_ac_comment_comment);
        ivShare = (TextView) findViewById(R.id.tv_ac_comment_share);
        tvZan = (TextView) findViewById(R.id.tv_ac_comment_zan);
        etContent = (EditText) findViewById(R.id.et_ac_comment_review);
        mList = (XRecyclerView) findViewById(R.id.xrv_ac_comment_list);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ImageView tvTitleMore = (ImageView) findViewById(R.id.iv_title_more);
        tvTitleMore.setVisibility(View.VISIBLE);
        findViewById(R.id.btn_title_back).setOnClickListener(this);
        tvTitleMore.setOnClickListener(this);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl_ac_comment_list);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                commentModel.start(mResourcesId, 1 + "");
            }
        });
        mList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                if (mComment != null && mComment.isNext()) {
                    commentModel.start(mResourcesId, mComment.getCurPage() + 1 + "");
                }
            }
        });
        mList.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter();
        commentAdapter.setData(commentDatas);
        mList.setAdapter(commentAdapter);
        tvZan.setText(mZanNum);
//        mList.addOnScrollListener(UIL.getScrollListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ac_comment_share:
                ShareDialog dialog=new ShareDialog();
                dialog.setOnDialogLisetener(new ShareDialog.OnDialogLisetener() {
                    @Override
                    public void shareStutas(String message) {
                        // TODO
                    }

                    @Override
                    public void startShare() {
                        // TODO
                    }
                });
                dialog.show(getSupportFragmentManager(),"share");
                break;
            case R.id.btn_title_back:
                finish();
                break;
            case R.id.iv_title_more:
                break;
        }
    }





    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
            if (LoginUtils.isLogin(this, this)){
                return true;
            }
            content = etContent.getText().toString().trim();
            if ("".equals(content)){
                toast("输入内容不能为空");
                return true;
            }
            addCommentModel.start(mResourcesId, content,null);
            KeyboardUtils.collapseKeyboard(this);
            etContent.setText("");
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void initData() {
        tvTitle.setText(R.string.text_comment_space);
        commentModel = new GetCommentModel();
        commentModel.setViewModelInterface(this);
        addCommentModel = new PostAddCommentModel();
        addCommentModel.setViewModelInterface(this);
        commentModel.start(mResourcesId);
    }

    @Override
    public void refreshData() {

    }

    private void toast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public void loadMoreComplete() {
        mList.loadMoreComplete();
    }

    public void refreshComplete() {
        mList.refreshComplete();
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
       if (srl != null) {
            srl.setRefreshing(false);
        }
        if (tag == commentModel.getTag()) {
            CommentResponse response = (CommentResponse) result;
            mComment = response.getData();
            if (mComment.getCurPage() == 1) {
                commentDatas.clear();
            }
            commentDatas.addAll(mComment.getDatas());
            tvComment.setText("评论" + commentDatas.size());
            commentAdapter.notifyDataSetChanged();
        }
        if (tag == addCommentModel.getTag()) {

            final Comment.CommentData commentData = new Comment.CommentData();
            commentData.setContent(content);
            commentData.setUsera_img(AccountManager.getInstance().getUserInfo().getHead());
            commentData.setUsera_name(AccountManager.getInstance().getUserInfo().getName());
            commentData.setCreatedate(TimeUtil.getCurrentDate());
            commentDatas.add(commentData);
            commentAdapter.notifyDataSetChanged();
            toast(getString(R.string.text_comment_success));
        }

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
        if (tag == commentModel.getTag()) {
            toast("获取评论数据失败");
        }
        if (tag == addCommentModel.getTag()) {
            toast("评论失败");
        }
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
        if (tag == commentModel.getTag()) {
            toast("获取评论数据异常:"+exception.getMessage());
        }
        if (tag == addCommentModel.getTag()) {
            toast("评论异常:"+exception.getMessage());
        }
    }

    public class CommentAdapter extends RecyclerViewDataAdapter<Comment.CommentData, CommentAdapter.VH> {


        @Override
        public void onBindHolder(VH holder, int i, Comment.CommentData commentData) {
            UIL.load(holder.civIcon, commentData.getUsera_img());
            holder.tvContent.setText(commentData.getContent());
            holder.tvName.setText(commentData.getUsera_name());
            holder.tvTime.setText(TimeUtil.getStandardDate(commentData.getCreatedate()));
        }

        @Override
        public VH getViewHolder(View view) {
            return new VH(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_comment;
        }

        protected class VH extends RecyclerView.ViewHolder {
            @Bind(R.id.civ_item_comment)
            CircleImageView civIcon;
            @Bind(R.id.tv_item_comment)
            TextView tvName;
            @Bind(R.id.tv_item_comment_content)
            TextView tvContent;
            @Bind(R.id.tv_item_comment_time)
            TextView tvTime;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


}
