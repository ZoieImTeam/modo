package com.binvshe.binvshe.binvsheui.message;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.http.model.IViewModelInterface;

import org.srr.dev.util.DimensionsUtil;

/**
 * Created by Administrator on 2015/11/16.
 */
public class MessageFragment extends BaseFragment implements IViewModelInterface {

    public Fragment conversationlistFragment;
//    private Fragment FriendsFragment;
    private ViewPager pager;
//    private TextView message;
//    private TextView friend;
//    private ObjectAnimator y;

    @Override
    public int getLayoutId() {

        return R.layout.fr_message;
    }


    @Override
    public void onClickView(View view, int id) {
//        switch (id) {
//            case R.id.fr_message_message:
//                if (0 != pager.getCurrentItem()) {
//                    pager.setCurrentItem(0);
//                    y.setCurrentPlayTime(0);
//                    checkItem(0);
//                }
//                break;
//            case R.id.fr_message_friend:
//                if (1 != pager.getCurrentItem()) {
//                    pager.setCurrentItem(1);
//                    y.start();
//                    checkItem(1);
//                }
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void doAfterReConnectNewWork() {

    }
//
//    private void checkItem(int item) {
//        if (item == 0) {
//            message.setTextColor(getResources().getColor(R.color.app_main_color));
//            message.setTextSize(17);
//            friend.setTextColor(getResources().getColor(R.color.app_main_color_other));
//            friend.setTextSize(15);
//        } else {
//            friend.setTextColor(getResources().getColor(R.color.app_main_color));
//            friend.setTextSize(17);
//            message.setTextColor(getResources().getColor(R.color.app_main_color_other));
//            message.setTextSize(15);
//        }
//    }

    @Override
    protected void initView(View contentView) {
//        message = findView(R.id.fr_message_message);
//        message.setOnClickListener(this);
//        friend = findView(R.id.fr_message_friend);
//        friend.setOnClickListener(this);
//        ImageView indicator = findView(R.id.fr_message_indicator);
        pager = findView(R.id.fr_message_pager);
        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        if (conversationlistFragment == null) {
                            conversationlistFragment = new ConversationlistFragment();
                        }
                        return conversationlistFragment;
//                    case 1:
//                        if (FriendsFragment == null) {
//                            FriendsFragment = new FriendsFragment();
//                        }
//                        return FriendsFragment;
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (positionOffset > 0.02f) {
//                    y.setCurrentPlayTime((long) (positionOffset * 500));
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                checkItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
//        y = ObjectAnimator.ofFloat(indicator, "x", DimensionsUtil.dip2px(90, getActivity()), width - DimensionsUtil.dip2px(155, getActivity()));
//        y.setDuration(500);
    }

    @Override
    public void initData() {
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

//        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String userid) {
//                for (Friends.Friend friend : friends) {
//                    if (userid.equals(friend.getId() + "")) {
//                        return new UserInfo(userid, friend.getName(), Uri.parse(friend.getHead()));
//                    }
//                }
//                return null;
//            }
//        }, true);  提供好友列表
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

}
