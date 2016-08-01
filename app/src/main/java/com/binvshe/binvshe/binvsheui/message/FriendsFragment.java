package com.binvshe.binvshe.binvsheui.message;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.binvsheui.chen.Utils.EditPersonDatas;
import com.binvshe.binvshe.binvsheui.dialog.DeleteDynamicDialog;
import com.binvshe.binvshe.http.model.IViewModelInterface;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.view.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends BaseFragment implements IViewModelInterface {

    public static final String CURRENT_USER_FRIENDS = "com.zhishenghuoguan.zanplusapp.friends";

    private AppCompatActivity mActivity;
    private FriendAdapter friendAdapter;
    private SwipeRefreshLayout srl;
    private RecyclerView list;
    private int toID;
    private String deleteId;
    private String userID = EditPersonDatas.getEditPersonDatas().getUserID();
    private String toName;

    @Override
    public int getLayoutId() {
        return R.layout.fr_friends;
    }

    @Override
    protected void initView(View v) {
        mActivity = (AppCompatActivity) getActivity();
        v.findViewById(R.id.fr_friends_search_friend).setOnClickListener(this);
        srl = (SwipeRefreshLayout) v.findViewById(R.id.fr_friends_srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        list = (RecyclerView) v.findViewById(R.id.fr_friends_list);
        list.setLayoutManager(new LinearLayoutManager(mActivity));
        refreshAdapter();
    }

    private void toLiaotian() {
        Intent intent = new Intent(mActivity, ConversationActivity.class);
        intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_ID, toID + "");//对方id
        intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_NAME, toName);//对方名字
        startActivity(intent);
    }

    private void refreshAdapter() {
        friendAdapter = new FriendAdapter();
        friendAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {
                Intent intent = new Intent(mActivity, ConversationActivity.class);
                intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_ID, "id");
                intent.putExtra(ConversationActivity.CURRENT_USER_FRIEND_NAME, "name");
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View views, int position) {
//                if (position == friends_lock.size() + 2 + friends_unlock.size()) {
//                    return false; //最后一个点击无效
//                }
//                if (position < friends_lock.size()) {
//                    deleteId = "lock," + friends_lock.get(position).getId();
//                    deleteFriend(friends_lock.get(position).getId() + "");
//                } else if (position > friends_lock.size()) {
//                    int i = position - friends_lock.size() - 1;
//                    deleteId = "unlock," + friends_unlock.get(i).getId();
//                    deleteFriend(friends_unlock.get(i).getId() + "");
//                }
                return false;
            }
        });
        list.setAdapter(friendAdapter);
    }

    private void deleteFriend(final String toId) {
        DeleteDynamicDialog delete =DeleteDynamicDialog.newInstance("是否删除好友", "取消", "确认");
        delete.setOnDialogEnterCancelLisetener(new DeleteDynamicDialog.OnDialogEnterCancelLisetener() {
            @Override
            public void cancel() {

            }

            @Override
            public void center() {
//                deleteFriend.start(userID, toId);
            }
        });
        delete.show(getChildFragmentManager(), "delete_friend");
    }

    @Override
    protected void initData() {
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        srl.setRefreshing(true);
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {

        srl.setRefreshing(false);
    }


    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        srl.setRefreshing(false);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        srl.setRefreshing(false);
    }

    class FriendAdapter extends RecyclerViewAdapter {

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView icon;
            TextView name, sign;
            ImageView status;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.icon = (CircleImageView) v.findViewById(R.id.item_friend_list_icon);
            holder.name = (TextView) v.findViewById(R.id.item_friend_list_name);
            holder.sign = (TextView) v.findViewById(R.id.item_friend_list_sign);
            holder.status = (ImageView) v.findViewById(R.id.item_friend_list_status);

            return holder;
        }


        @Override
        public int getItemCount() {
            return 0;
        }
    }

    @Override
    protected void onClickView(View view, int id) {
        switch (id) {
            case R.id.fr_friends_search_friend:
                Toast.makeText(mActivity, "暂无开放", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void doAfterReConnectNewWork() {

    }
}
