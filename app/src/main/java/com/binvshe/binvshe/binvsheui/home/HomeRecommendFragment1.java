package com.binvshe.binvshe.binvsheui.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.find.ContentDetailActivity1;
import com.binvshe.binvshe.binvsheui.find.SubjectListActivity;
import com.binvshe.binvshe.binvsheui.usercenter.AnotherUserInfoActivity;
import com.binvshe.binvshe.binvsheui.usercenter.UserInfoActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.Banner;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.entity.subject.SysTypeEntitiy;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.model.GetHomeRecModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.HomeRecResponse;

import org.srr.dev.adapter.QuickDeAdapter.BaseAdapterHelper;
import org.srr.dev.adapter.QuickDeAdapter.MultiItemTypeSupport;
import org.srr.dev.adapter.QuickDeAdapter.QuickAdapter;
import org.srr.dev.base.BaseFragment;
import org.srr.dev.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/4
 * 首页推荐fragment
 */
public class HomeRecommendFragment1 extends BaseFragment implements IViewModelInterface {

    @Bind(R.id.rv_fr_linear_list)
    XRecyclerView mRvFrLinearList;
    @Bind(R.id.srl_fr_linear_list)
    SwipeRefreshLayout mSrlFrLinearList;
    private boolean isDrag;
    private Boolean mStatBool = true;

    private ArrayList<HomeBean> mAdapterData;
    private ArrayList<Banner> mBanners = new ArrayList<>();
    private GetHomeRecModel mHomeRecModel;
    private QuickAdapter<HomeBean> mQuickAdapter; //创建一个快速开发的adapter
    String id;

    // 区分adapter中的viewtype的枚举
    enum ViewType {
        title,
        content,
        header
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_linear_list;
    }

    @Override
    protected void initView(View contentView) {
        ButterKnife.bind(this, contentView);
    }


    /**
     * recyclerview 的初始化以及 adapter 的初始化
     */
    private void initRec() {
        mSrlFrLinearList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullRefresh();
            }
        });

        MultiItemTypeSupport<HomeBean> multiItemTypeSupport = new MultiItemTypeSupport<HomeBean>() {
            @Override
            public int getLayoutId(int viewType) {
                if (viewType == ViewType.content.ordinal()) {
                    return R.layout.item_home_work_h;
                } else if (viewType == ViewType.header.ordinal()) {
                    return R.layout.banner_viewpager;
                } else {
                    return R.layout.item_home_title;
                }
            }

            @Override
            public int getItemViewType(int position, HomeBean homeBean) {
                return homeBean.type;
            }
        };
        mQuickAdapter = new QuickAdapter<HomeBean>(getContext(), multiItemTypeSupport) {
            @Override
            protected void convert(BaseAdapterHelper helper, final HomeBean item) {
                if (helper.getItemViewType() == ViewType.title.ordinal()) {
                    final SubjectTypeEntity entity = new SubjectTypeEntity();
                    entity.setName(item.getTitleEntly().getName());
                    entity.setId(item.getTitleEntly().getId());
                    entity.setPid(item.getTitleEntly().getPid());
                    helper.setText(R.id.tv_item_type_name, item.getTitleEntly().getName());
                    helper.setOnClickListener(R.id.tv_item_home_more, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SubjectListActivity.class);
                            intent.putExtra(GlobalConfig.EXTRA_OBJECT, entity);
                            getContext().startActivity(intent);
                        }
                    });
                } else if (helper.getItemViewType() == ViewType.content.ordinal()) {
                    final String iconId=item.getSubjectEntity().getUser();
                    helper
                            .setImage(R.id.iv_cover, item.getSubjectEntity().getPhotos())
                            .setOnClickListener(R.id.iv_cover, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ContentDetailActivity1.newInstance(getContext(), item.getSubjectEntity());
                                }
                            })
                            .setText(R.id.tv_see_num, item.getSubjectEntity().getBrowsenumber())
                            .setText(R.id.tv_zan_num, item.getSubjectEntity().getLikeCount())
                            .setText(R.id.tv_content, item.getSubjectEntity().getName())
                            .setImage(R.id.civ_home, item.getSubjectEntity().getHead())
                            .setOnClickListener(R.id.civ_home, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
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
                } else {
                    ViewPager banner = helper.getView(R.id.banner_viewpager);
                    startBanner(banner);
                }
            }
        };

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                      @Override
                                      public int getSpanSize(int position) {
                                          try {
                                              if (mQuickAdapter.getItem(position).getType() == ViewType.content.ordinal()) {
                                                  return 1;
                                              } else {
                                                  return 2;
                                              }
                                          } catch (NullPointerException e) {
                                              return 2;
                                          }
                                      }
                                  }
        );

        mRvFrLinearList.setHasFixedSize(true);
        mRvFrLinearList.setAdapter(mQuickAdapter);
        mRvFrLinearList.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        mAdapterData = new ArrayList<>();
        initRec();
        if (AccountManager.getInstance().getUserInfo() != null) {
            id = AccountManager.getInstance().getUserInfo().getId() + "";
        } else {
            id = "1";
        }
        mHomeRecModel = new GetHomeRecModel();
        mHomeRecModel.setViewModelInterface(this);
        mHomeRecModel.start(id);
    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    private void onPullRefresh() {
        mHomeRecModel.start(id);
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
        if (tag == mHomeRecModel.getTag()) {
            HomeRecResponse response = (HomeRecResponse) result;
            mAdapterData.clear();
            mBanners.clear();
            mQuickAdapter.clear();
            ArrayList<Banner> bannerList = response.getData().getBanner();
            ArrayList<SysTypeEntitiy> sysTypeEntitiys = response.getData().getDatas();
            mBanners.addAll(bannerList);
            setHomeBeanList(sysTypeEntitiys);
            mQuickAdapter.addAll(mAdapterData);
        }
        mSrlFrLinearList.setRefreshing(false);

    }

    /**
     * 数据类型转成adapter 需要的类型
     *
     * @param sysTypeEntitiys
     */
    private void setHomeBeanList(ArrayList<SysTypeEntitiy> sysTypeEntitiys) {
        HomeBean bennerbean = new HomeBean(mBanners, ViewType.header.ordinal());
        mAdapterData.add(bennerbean);
        for (SysTypeEntitiy entity : sysTypeEntitiys) {
            HomeBean bean = new HomeBean(entity, ViewType.title.ordinal());
            mAdapterData.add(bean);
            for (SubjectEntity data : entity.getList()) {
                HomeBean temp = new HomeBean(data, ViewType.content.ordinal());
                mAdapterData.add(temp);
            }
        }
        Log.d("kyluzoi", "mAdapterData.size():" + mAdapterData.size());
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }


    /**
     * banner 启动
     *
     * @param banner
     */
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
                return new BannerItemFragment(mBanners.get(position % mBanners.size()));
            }

            @Override
            public int getCount() {
                return mBanners.size() * 10000;
            }
        });
        banner.setCurrentItem(mBanners.size() * 5000);
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

    /**
     * 首页所需要的bean
     */
    public class HomeBean {
        SubjectEntity mSubjectEntity;
        SysTypeEntitiy mTitleEntly;
        private ArrayList<Banner> mBanners;
        int type;

        public HomeBean(SubjectEntity subjectEntity, int type) {
            this.mSubjectEntity = subjectEntity;
            this.type = type;
        }

        public HomeBean(ArrayList<Banner> mBanners, int type) {
            this.mBanners = mBanners;
            this.type = type;
        }

        public HomeBean(SysTypeEntitiy titleentity, int type) {
            this.mTitleEntly = titleentity;
            this.type = type;
        }

        public SubjectEntity getSubjectEntity() {
            return mSubjectEntity;
        }

        public void setSubjectEntity(SubjectEntity subjectEntity) {
            mSubjectEntity = subjectEntity;
        }

        public SysTypeEntitiy getTitleEntly() {
            return mTitleEntly;
        }

        public void setTitleEntly(SysTypeEntitiy titleEntly) {
            mTitleEntly = titleEntly;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
