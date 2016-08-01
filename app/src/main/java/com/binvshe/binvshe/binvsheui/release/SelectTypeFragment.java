package com.binvshe.binvshe.binvsheui.release;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.adapter.ReleaseTypeAdapter;
import com.binvshe.binvshe.binvsheui.find.SubjectListActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.http.model.IViewModelInterface;

import org.srr.dev.adapter.GridSpacingItemDecoration;
import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/20.
 */
public class SelectTypeFragment extends LinearListFragment implements IViewModelInterface {

    private final static String LIST = "list";
    private final static String TYPE = "type";
    public static final String EXTRA_TYPE = "extra_type";
    public final static int FIRST = 0;
    public final static int SECOND = 1;
    public final static int TYPE_RELEASE = 3;
    public final static int TYPE_CHANNEL = 4;
    private ReleaseTypeAdapter mAdapter;

    private ArrayList<SubjectTypeEntity> mList;
    private int type;
    private int extra_type;
    private FragmentActivity mActivity;

    public SelectTypeFragment() {

    }

    public static SelectTypeFragment newInstance(ArrayList<SubjectTypeEntity> aList, int type, int extra_type) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(LIST, aList);
        args.putInt(TYPE, type);
        args.putInt(EXTRA_TYPE, extra_type);
        SelectTypeFragment fragment = new SelectTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        final Bundle arg = getArguments();
        if (arg != null) {
            mList = arg.getParcelableArrayList(LIST);
            type = arg.getInt(TYPE);
            extra_type = arg.getInt(EXTRA_TYPE);
        }
        mAdapter = new ReleaseTypeAdapter(mList, type, extra_type);
        if (type == FIRST) {
            mAdapter.setOnItemClickLitener(new FirstOnItemClickLitener());
        } else {
            mAdapter.setOnItemClickLitener(new SecondOnItemClickLitener());
        }
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    protected void initData() {
        mActivity = getActivity();
        int space = (int) getResources().getDimension(R.dimen.spacing_lv2);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 3);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3, space, true);
        this.setLayoutManager(layout);
        this.getmList().addItemDecoration(itemDecoration);
        setSrlayoutEnable(false);
        mAdapter.notifyDataSetChanged();
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

    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    private class FirstOnItemClickLitener implements RecyclerViewDataAdapter.OnItemClickLitener {
        @Override
        public void onItemClick(View views, int position) {
            SubjectTypeEntity entity = mList.get(position);
            Intent intent = new Intent(mActivity, SelectSecondTypeActivity.class);
            intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
            intent.putExtra(EXTRA_TYPE, extra_type);
            intent.putExtra(TYPE, SECOND);
            startActivity(intent);
            if (extra_type == TYPE_RELEASE) {
                mActivity.finish();
            }
        }

        @Override
        public boolean onItemLongClick(View views, int position) {
            return false;
        }
    }

    private class SecondOnItemClickLitener implements RecyclerViewDataAdapter.OnItemClickLitener {
        @Override
        public void onItemClick(View views, int position) {
            SubjectTypeEntity entity = mList.get(position);
            //TODO: 跳转到其他
            if (extra_type == TYPE_CHANNEL) {
                Intent intent = new Intent(mActivity, SubjectListActivity.class);
                intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                startActivity(intent);
            } else if (extra_type == TYPE_RELEASE) {
//                    Toast.makeText(SelectSecondTypeActivity.this, entity.getName(), Toast.LENGTH_SHORT).show();
                //判断二级菜单btn
                switch (entity.getName()) {
                    case "coser":
                    case "摄影师":
                    case "cp":
                    case "真人漫画": {
                        Intent intent = new Intent(mActivity, CoserActivity.class);
                        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                        startActivity(intent);
                        break;
                    }
//                    case "cp": {
//                        Intent intent = new Intent(mActivity, CpActivity.class);
//                        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
//                        startActivity(intent);
//                        break;
//                    }
                    case "插画":
                    case "原画":
                    case "场景":
                    case "条漫":
                    case "四格":
                    case "短篇":
                    case "连载": {
                        Intent intent = new Intent(mActivity, IkonActivity.class);
                        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                        startActivity(intent);
                        break;
                    }
                    case "百合":
                    case "基腐":
                    case "鬼畜":
                    case "治愈":
                    case "电波":
                    case "玄幻":
                    case "都市":
                    case "仙侠":
                    case "悬疑": {
                        Intent intent = new Intent(mActivity, NoStoryActivity.class);
                        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                        startActivity(intent);
                        break;
                    }
                }

            }

        }

        @Override
        public boolean onItemLongClick(View views, int position) {
            return false;
        }
    }
}
