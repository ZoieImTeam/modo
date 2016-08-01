package com.binvshe.binvshe.binvsheui.release;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.subject.SubjectSerialEntity;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.http.model.AddSerialModel;
import com.binvshe.binvshe.http.model.AddSpecialModel;
import com.binvshe.binvshe.http.model.GetUserSerialModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetUserSerialResponse;
import com.binvshe.binvshe.view.PbTopBar;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cainer on 2016/4/21.
 */
public class ChoseSerializeActivity extends BaseActivity implements PbTopBar.MytopbarClick, IViewModelInterface {

    @Bind(R.id.pb_topbar)
    PbTopBar pbTopbar;
    @Bind(R.id.rec_serialize_list)
    XRecyclerView recSerializeList;
    @Bind(R.id.btn_sure)
    TextView btnSure;
    @Bind(R.id.edit_serialize_title)
    EditText editSerializeTitle;
    @Bind(R.id.btn_release_serialize)
    Button btnReleaseSerialize;
    @Bind(R.id.tv_serialize_title)
    TextView tvSerializeTitle;
    private MyAdapter adapter = new MyAdapter();
    private int pageNo = 1;
    SubjectTypeEntity mEntity;


    GetUserSerialModel getUserSerialModel = new GetUserSerialModel();
    AddSerialModel addSerialModel=new AddSerialModel();

    ArrayList<SubjectSerialEntity> mDatas = new ArrayList<SubjectSerialEntity>();

    @Override
    protected void initGetIntent() {
        this.mEntity =getIntent().getParcelableExtra(GlobalConfig.EXTRA_OBJECT);
    }


    public static void newInstance(Activity activity, SubjectTypeEntity entity,int requst) {
        Intent intent = new Intent(activity, ChoseSerializeActivity.class);
        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
        activity.startActivityForResult(intent,requst);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_chose_serialize;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        pbTopbar.settext("", "选择连载", "");
        pbTopbar.setMytopbarClickimp(this);
        getUserSerialModel.setViewModelInterface(this);
        adapter=new MyAdapter();
        adapter.setData(mDatas);
        recSerializeList.setAdapter(adapter);
        recSerializeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void initData() {

        String pid = mEntity.getId() + "";
        getUserSerialModel.start(pid, pageNo + "");
    }

    @Override
    public void refreshData() {

    }



    @Override
    public void leftTexClick() {

        this.finish();
    }

    @Override
    public void rightTexClick() {

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
        if (tag == getUserSerialModel.getTag()) {
            GetUserSerialResponse response = (GetUserSerialResponse) result;
            ArrayList<SubjectSerialEntity> data = response.getData().getDatas();
            mDatas.addAll(data);
            adapter.notifyDataSetChanged();
        }
        else if (tag==addSerialModel.getTag())
        {
            ChoseSerializeActivity.this.finish();
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        if(tag==addSerialModel.getTag())
        {
            Toast.makeText(getApplicationContext(),"创建失败",Toast.LENGTH_SHORT).show();
        }
        else if(tag==getUserSerialModel.getTag())
        {
            Toast.makeText(getApplicationContext(),"获取列表失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    @OnClick({R.id.btn_release_serialize, R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_release_serialize:
                String title=editSerializeTitle.getText().toString();
                String id=mEntity.getId()+"";
                addSerialModel.start(title,id);
                ChoseSerializeActivity.this.finish();
                break;
            case R.id.btn_sure:
                //预留创建连载
                recSerializeList.setVisibility(View.GONE);
                btnSure.setVisibility(View.GONE);
                tvSerializeTitle.setVisibility(View.VISIBLE);
                editSerializeTitle.setVisibility(View.VISIBLE);
                btnReleaseSerialize.setVisibility(View.VISIBLE);
                break;
        }

    }


    public class MyAdapter extends RecyclerViewDataAdapter<SubjectSerialEntity, MyAdapter.VH> {
        @Override
        public void onBindHolder(VH viewHolder, int i, final SubjectSerialEntity entity) {
            String name = entity.getName();
            viewHolder.tvSerialize.setText(name);
            viewHolder.tvSerialize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent();
                    data.putExtra("entity1",entity);
                    ChoseSerializeActivity.this.setResult(RESULT_OK, data);
                    finish();
                }
            });
        }

        @Override
        public VH getViewHolder(View view) {
            return new VH(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_serialize;
        }

        public class VH extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_serialize)
            TextView tvSerialize;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
