package com.binvshe.binvshe.binvsheui.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity1;
import com.binvshe.binvshe.binvsheui.find.SubjectListActivity;
import com.binvshe.binvshe.binvsheui.usercenter.AnotherUserInfoActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserInfoActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.Banner;
import com.binvshe.binvshe.entity.HomeRec.HomeRec;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.entity.subject.SysTypeEntitiy;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetHomeRecModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.HomeRecResponse;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.TextUtils;
import org.srr.dev.util.UIL;
import org.srr.dev.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/25.
 */
public class HomeRecommendFragment extends LinearListFragment implements IViewModelInterface {

    private ArrayList<Banner> banners = new ArrayList<>();
    private FragmentActivity mActivity;
    private HomeListAdapter adapter;
    private boolean isDrag;
    private GetHomeRecModel homeRecModel;
    private HomeRec homeRecs = new HomeRec();
    private ArrayList<Banner> mBannerList;
    private ArrayList<SubjectEntity> mHotList;
    private ArrayList<SysTypeEntitiy> mList;
    private Boolean mStatBool = true;
    String id;

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        mBannerList = new ArrayList<Banner>();
        mHotList = new ArrayList<SubjectEntity>();
        mList = new ArrayList<SysTypeEntitiy>();
        adapter = new HomeListAdapter(mBannerList, mHotList, mList);
        getmList().setLoadingMoreEnabled(false);
        mActivity = getActivity();
        return adapter;
    }

    @Override
    public void onPullRefresh() {
        String id = AccountManager.getInstance().getUserInfo().getId() + "";
        homeRecModel.start(id);
    }


    @Override
    protected void initData() {

        if (AccountManager.getInstance().getUserInfo() != null) {
            id = AccountManager.getInstance().getUserInfo().getId() + "";
        } else id = "1";
        homeRecModel = new GetHomeRecModel();
        homeRecModel.setViewModelInterface(this);
        homeRecModel.start(id);

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showSwiRefreshLayout();
        showLoadingDialog();
    }


    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (tag == homeRecModel.getTag()) {
            HomeRecResponse response = (HomeRecResponse) result;
            mList.clear();
            mHotList.clear();
            mBannerList.clear();
            ArrayList<Banner> bannerList = response.getData().getBanner();
            ArrayList<SubjectEntity> subjectEntityList = response.getData().getHot();
            ArrayList<SysTypeEntitiy> sysTypeEntitiys = response.getData().getDatas();
            banners.addAll(bannerList);
            mHotList.addAll(subjectEntityList);
            mList.addAll(sysTypeEntitiys);
            adapter.notifyDataSetChanged();
        }
        dismissSwiRefreshLayout();
        dismissLoadingDialog();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissSwiRefreshLayout();
        dismissLoadingDialog();
        Log.e("onFailLoad", "codeint:" + code + ":" + codeMsg);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissSwiRefreshLayout();
        dismissLoadingDialog();
    }

    class HomeListAdapter extends RecyclerViewAdapter<HomeListAdapter.ViewHolder> {

        private static final int VIEW_TYPE_BANNER = 0;
        private static final int VIEW_TYPE_HOT = 1;
        private static final int VIEW_TYPE_LIST = 2;

        private ArrayList<Banner> mBannerList;
        private ArrayList<SubjectEntity> mHotList;
        private ArrayList<SysTypeEntitiy> mList;

        protected class ViewHolder extends RecyclerView.ViewHolder {
            ViewPager banner;
            TextView type_name, more, name1, name2, name3, name4, content1, content2, content3, content4, look1, look2, look3, look4, like1, like2, like3, like4;
            ImageView img1, img2, img3, img4;
            CircleImageView icon1, icon2, icon3, icon4;
            FrameLayout card1, card2, card3, card4;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        public HomeListAdapter(ArrayList<Banner> banners, ArrayList<SubjectEntity> hots, ArrayList<SysTypeEntitiy> list) {
            this.mBannerList = banners;
            this.mHotList = hots;
            this.mList = list;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            ViewHolder holder;
            if (viewType == VIEW_TYPE_BANNER) {
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.banner_viewpager, parent, false);
                holder = new ViewHolder(v);
                holder.banner = (ViewPager) v.findViewById(R.id.banner_viewpager);
                return holder;
            } else if (viewType == VIEW_TYPE_HOT) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
                holder = new ViewHolder(v);
                holder.type_name = (TextView) v.findViewById(R.id.tv_item_type_name);
                holder.more = (TextView) v.findViewById(R.id.tv_item_home_more);
                holder.name1 = (TextView) v.findViewById(R.id.tv_item_home_name1);
                holder.name2 = (TextView) v.findViewById(R.id.tv_item_home_name2);
                holder.name3 = (TextView) v.findViewById(R.id.tv_item_home_name3);
                holder.name4 = (TextView) v.findViewById(R.id.tv_item_home_name4);
                holder.content1 = (TextView) v.findViewById(R.id.tv_item_home_content1);
                holder.content2 = (TextView) v.findViewById(R.id.tv_item_home_content2);
                holder.content3 = (TextView) v.findViewById(R.id.tv_item_home_content3);
                holder.content4 = (TextView) v.findViewById(R.id.tv_item_home_content4);
                holder.icon1 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon1);
                holder.icon2 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon2);
                holder.icon3 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon3);
                holder.icon4 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon4);
                holder.look1 = (TextView) v.findViewById(R.id.tv_item_home_look1);
                holder.look2 = (TextView) v.findViewById(R.id.tv_item_home_look2);
                holder.look3 = (TextView) v.findViewById(R.id.tv_item_home_look3);
                holder.look4 = (TextView) v.findViewById(R.id.tv_item_home_look4);
                holder.like1 = (TextView) v.findViewById(R.id.tv_item_home_like1);
                holder.like2 = (TextView) v.findViewById(R.id.tv_item_home_like2);
                holder.like3 = (TextView) v.findViewById(R.id.tv_item_home_like3);
                holder.like4 = (TextView) v.findViewById(R.id.tv_item_home_like4);
                holder.img1 = (ImageView) v.findViewById(R.id.iv_item_home_img1);
                holder.img2 = (ImageView) v.findViewById(R.id.iv_item_home_img2);
                holder.img3 = (ImageView) v.findViewById(R.id.iv_item_home_img3);
                holder.img4 = (ImageView) v.findViewById(R.id.iv_item_home_img4);
                holder.card1 = (FrameLayout) v.findViewById(R.id.cv_item_home1);
                holder.card2 = (FrameLayout) v.findViewById(R.id.cv_item_home2);
                holder.card3 = (FrameLayout) v.findViewById(R.id.cv_item_home3);
                holder.card4 = (FrameLayout) v.findViewById(R.id.cv_item_home4);
                return holder;
            } else {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
                holder = new ViewHolder(v);
                holder.type_name = (TextView) v.findViewById(R.id.tv_item_type_name);
                holder.more = (TextView) v.findViewById(R.id.tv_item_home_more);
                holder.name1 = (TextView) v.findViewById(R.id.tv_item_home_name1);
                holder.name2 = (TextView) v.findViewById(R.id.tv_item_home_name2);
                holder.name3 = (TextView) v.findViewById(R.id.tv_item_home_name3);
                holder.name4 = (TextView) v.findViewById(R.id.tv_item_home_name4);
                holder.content1 = (TextView) v.findViewById(R.id.tv_item_home_content1);
                holder.content2 = (TextView) v.findViewById(R.id.tv_item_home_content2);
                holder.content3 = (TextView) v.findViewById(R.id.tv_item_home_content3);
                holder.content4 = (TextView) v.findViewById(R.id.tv_item_home_content4);
                holder.icon1 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon1);
                holder.icon2 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon2);
                holder.icon3 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon3);
                holder.icon4 = (CircleImageView) v.findViewById(R.id.civ_item_home_icon4);
                holder.look1 = (TextView) v.findViewById(R.id.tv_item_home_look1);
                holder.look2 = (TextView) v.findViewById(R.id.tv_item_home_look2);
                holder.look3 = (TextView) v.findViewById(R.id.tv_item_home_look3);
                holder.look4 = (TextView) v.findViewById(R.id.tv_item_home_look4);
                holder.like1 = (TextView) v.findViewById(R.id.tv_item_home_like1);
                holder.like2 = (TextView) v.findViewById(R.id.tv_item_home_like2);
                holder.like3 = (TextView) v.findViewById(R.id.tv_item_home_like3);
                holder.like4 = (TextView) v.findViewById(R.id.tv_item_home_like4);
                holder.img1 = (ImageView) v.findViewById(R.id.iv_item_home_img1);
                holder.img2 = (ImageView) v.findViewById(R.id.iv_item_home_img2);
                holder.img3 = (ImageView) v.findViewById(R.id.iv_item_home_img3);
                holder.img4 = (ImageView) v.findViewById(R.id.iv_item_home_img4);
                holder.card1 = (FrameLayout) v.findViewById(R.id.cv_item_home1);
                holder.card2 = (FrameLayout) v.findViewById(R.id.cv_item_home2);
                holder.card3 = (FrameLayout) v.findViewById(R.id.cv_item_home3);
                holder.card4 = (FrameLayout) v.findViewById(R.id.cv_item_home4);

//                cardItemClick(holder.card1, list.get(viewType).getList(), 0);
//                cardItemClick(holder.card2, list.get(viewType).getList(), 1);
//                cardItemClick(holder.card3, list.get(viewType).getList(), 2);
//                cardItemClick(holder.card4, list.get(viewType).getList(), 3);

                //设置点击事件
                switch (mList.get(viewType - 2).getList().size()) {
                    case 4:
                        cardItemClick(holder.card4, mList.get(viewType - 2).getList().get(3));
                    case 3:
                        cardItemClick(holder.card3, mList.get(viewType - 2).getList().get(2));
                    case 2:
                        cardItemClick(holder.card2, mList.get(viewType - 2).getList().get(1));
                    case 1:
                        cardItemClick(holder.card1, mList.get(viewType - 2).getList().get(0));
                }


                return holder;
            }
        }

        private void cardItemClick(FrameLayout card, final SubjectEntity entity) {
            if (entity == null) {
                return;
            }
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mActivity, ContentDetailActivity.class);
//                    intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
//                    startActivity(intent);
                    ContentDetailActivity1.newInstance(mActivity, entity);
                }
            });
        }


        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            if (i == 0) {
                //填充广告Banner数据
                if (banners != null && banners.size() > 0) {
                    startBanner(holder.banner);
                }
            } else if (i == 1) {
                //填充热门数据
                holder.type_name.setText(R.string.home_recommend_hot_tip);
                loadDynamic(this.mHotList, holder, getString(R.string.home_recommend_hot_tip));
                switch (mHotList.size()) {
                    // TODO: 2016/6/6 热门card点击事件
                    case 4:
                        cardItemClick(holder.card4, this.mHotList.get(3));
                    case 3:
                        cardItemClick(holder.card3, this.mHotList.get(2));
                    case 2:
                        cardItemClick(holder.card2, this.mHotList.get(1));
                    case 1:
                        cardItemClick(holder.card1, this.mHotList.get(0));
                }

            } else {
                //填充推荐列表数据
                int dataIndex = i - 2;
                final SysTypeEntitiy object = mList.get(dataIndex);
                ArrayList<SubjectEntity> list = object.getList();
                String name = object.getName();
                if (!android.text.TextUtils.isEmpty(name)) {
                    holder.type_name.setText(name);
                }

                loadDynamic(list, holder, name);
            }


        }

        private void loadDynamic(final List<SubjectEntity> data, final ViewHolder holder, final String name) {
            holder.icon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String iconId = data.get(0).getUser();
                    if (iconId.equals(id)) {
                        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AnotherUserInfoActivity.class);
                        intent.putExtra("userid", iconId);
                        startActivity(intent);
                    }
                }
            });
            holder.icon2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String iconId = data.get(1).getUser();
                    if (iconId.equals(id)) {
                        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AnotherUserInfoActivity.class);
                        intent.putExtra("userid", iconId);
                        startActivity(intent);
                    }
                }
            });
            holder.icon3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String iconId = data.get(2).getUser();
                    if (iconId.equals(id)) {
                        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AnotherUserInfoActivity.class);
                        intent.putExtra("userid", iconId);
                        startActivity(intent);
                    }
                }
            });
            holder.icon4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String iconId = data.get(3).getUser();
                    if (iconId.equals(id)) {
                        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AnotherUserInfoActivity.class);
                        intent.putExtra("userid", iconId);
                        startActivity(intent);
                    }
                }
            });
            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 预留更多的点击事件
                     */
                    SubjectTypeEntity entity = new SubjectTypeEntity();
                    int position = holder.getAdapterPosition();

                    try {
                        entity.setPid(data.get(0).getSystype());
                        entity.setName(name);
                        entity.setId(data.get(0).getSystype());
                        Intent intent = new Intent(getActivity(), SubjectListActivity.class);
                        intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                        startActivity(intent);
                    } catch (IndexOutOfBoundsException e) {
                        Toast.makeText(getContext(), "No datas to show!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            switch (data.size()) {
                case 4:
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    initItemDynamic(data.get(2), holder.name3, holder.content3, holder.look3, holder.like3, holder.icon3, holder.img3);
                    initItemDynamic(data.get(3), holder.name4, holder.content4, holder.look4, holder.like4, holder.icon4, holder.img4);
                    break;
                case 3:
                    holder.card1.setVisibility(View.VISIBLE);
                    holder.card2.setVisibility(View.VISIBLE);
                    holder.card3.setVisibility(View.VISIBLE);
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    initItemDynamic(data.get(2), holder.name3, holder.content3, holder.look3, holder.like3, holder.icon3, holder.img3);
                    holder.card4.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.card1.setVisibility(View.VISIBLE);
                    holder.card2.setVisibility(View.VISIBLE);
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    initItemDynamic(data.get(1), holder.name2, holder.content2, holder.look2, holder.like2, holder.icon2, holder.img2);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
                case 1:
                    holder.card1.setVisibility(View.VISIBLE);
                    initItemDynamic(data.get(0), holder.name1, holder.content1, holder.look1, holder.like1, holder.icon1, holder.img1);
                    holder.card2.setVisibility(View.GONE);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
                default:
                    holder.card1.setVisibility(View.GONE);
                    holder.card2.setVisibility(View.GONE);
                    holder.card3.setVisibility(View.GONE);
                    holder.card4.setVisibility(View.GONE);
                    break;
            }


        }

        private void initItemDynamic(SubjectEntity data, TextView name, TextView content, TextView look, TextView like, ImageView icon, ImageView img) {
            name.setText(TextUtils.isEmptyString(data.getUsername()));
            content.setText(TextUtils.isEmptyString(data.getName()));
            look.setText(TextUtils.isEmptyString(data.getBrowsenumber() + ""));
            like.setText(TextUtils.isEmptyString(data.getLikeCount() + ""));
            UIL.load(icon, data.getHead());
            UIL.load(img, data.getPhotos());
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            int size = 0;
            if (mList != null) {
                size = 2 + mList.size();
            }
            return size;
        }
    }

    private void startBanner(final ViewPager banner) {
        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isDrag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        isDrag = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isDrag = false;
                        break;
                    default:
                        break;
                }
            }
        });

        banner.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new BannerItemFragment(banners.get(position % banners.size()));
            }

            @Override
            public int getCount() {
                return banners.size() * 10000;
            }
        });
        banner.setCurrentItem(banners.size() * 5000);
        if (mStatBool) {
            banner.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isDrag) {
                        banner.setCurrentItem(banner.getCurrentItem() + 1);
                    }
                    banner.postDelayed(this, 3000);
                }
            }, 3000);
            mStatBool = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isDrag = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isDrag = true;
    }
}
