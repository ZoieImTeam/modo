package com.binvshe.binvshe.helper;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/20.
 */
public class SubjectTypeHelper {

    private static final String ID_TYPE_CARTON = "9";
    private static final String ID_TYPE_XIAOSHUO = "7";
    private static final String ID_TYPE_COS = "5";

    private static SubjectTypeHelper mInstance;

    private SubjectTypeHelper() {

    }

    private static synchronized void initSync() {
        if (mInstance == null) {
            mInstance = new SubjectTypeHelper();
        }
    }

    public static SubjectTypeHelper getInstance() {
        if (mInstance == null) {
            initSync();
        }
        return mInstance;
    }

    /**
     * 获取二级类别的数据
     * @param id
     * @return
     */
    public static ArrayList<SubjectTypeEntity> getSecondTypeListById(String id){
        if(id.equals(ID_TYPE_CARTON)){
            return getCartonList();
        }else if(id.equals(ID_TYPE_COS)){
            return getCosTypeList();
        }else if(id.equals(ID_TYPE_XIAOSHUO)){
            return getXiaoShuoTypeList();
        }
        return null;
    }


    /**
     * 获取一级分类的List
     * @return
     */
    public static ArrayList<SubjectTypeEntity> getFirstTypeList(){
        ArrayList<SubjectTypeEntity> list = new ArrayList<SubjectTypeEntity>();

        SubjectTypeEntity carton = new SubjectTypeEntity();
        carton.setName("漫画");
        carton.setId(ID_TYPE_CARTON);
        carton.setPid("0");
        carton.setIcon(R.mipmap.icon_manga);

        SubjectTypeEntity xiaoshuo = new SubjectTypeEntity();
        xiaoshuo.setName("轻小说");
        xiaoshuo.setId(ID_TYPE_XIAOSHUO);
        xiaoshuo.setPid("0");
        xiaoshuo.setIcon(R.mipmap.icon_novel);

        SubjectTypeEntity cos = new SubjectTypeEntity();
        cos.setName("cos");
        cos.setId(ID_TYPE_COS);
        cos.setPid("0");
        cos.setIcon(R.mipmap.icon_cos);

        list.add(carton);
        list.add(xiaoshuo);
        list.add(cos);
        return list;

    }

    /**
     * 获取漫画的二级分类
     * @return
     */
    public static ArrayList<SubjectTypeEntity> getCartonList() {
        ArrayList<SubjectTypeEntity> list = new ArrayList<SubjectTypeEntity>();

        SubjectTypeEntity carton1 = new SubjectTypeEntity();
        carton1.setName("插画");
        carton1.setId("14");
        carton1.setPid(ID_TYPE_CARTON);
        carton1.setIcon(R.mipmap.icon_bg_green);
        list.add(carton1);

        SubjectTypeEntity carton2 = new SubjectTypeEntity();
        carton2.setName("原画");
        carton2.setId("15");
        carton2.setPid(ID_TYPE_CARTON);
        carton2.setIcon(R.mipmap.icon_bg_blue);
        list.add(carton2);

        SubjectTypeEntity carton3 = new SubjectTypeEntity();
        carton3.setName("场景");
        carton3.setId("16");
        carton3.setPid(ID_TYPE_CARTON);
        carton3.setIcon(R.mipmap.icon_bg_pink);
        list.add(carton3);

        SubjectTypeEntity carton4 = new SubjectTypeEntity();
        carton4.setName("条漫");
        carton4.setId("17");
        carton4.setPid(ID_TYPE_CARTON);
        carton4.setIcon(R.mipmap.icon_bg_orange);
        list.add(carton4);

        SubjectTypeEntity carton5 = new SubjectTypeEntity();
        carton5.setName("四格");
        carton5.setId("18");
        carton5.setPid(ID_TYPE_CARTON);
        carton5.setIcon(R.mipmap.icon_bg_red);
        list.add(carton5);

        SubjectTypeEntity carton6 = new SubjectTypeEntity();
        carton6.setName("短篇");
        carton6.setId("28");
        carton6.setPid(ID_TYPE_CARTON);
        carton6.setIcon(R.mipmap.icon_bg_yellow);
        list.add(carton6);

        SubjectTypeEntity carton7 = new SubjectTypeEntity();
        carton7.setName("连载");
        carton7.setId("29");
        carton7.setPid(ID_TYPE_CARTON);
        carton7.setIcon(R.mipmap.icon_bg_black);
        list.add(carton7);

        return list;
    }

    /**
     * 获取小说的二级分类
     * @return
     */
    public static ArrayList<SubjectTypeEntity> getXiaoShuoTypeList() {
        ArrayList<SubjectTypeEntity> list = new ArrayList<SubjectTypeEntity>();

        SubjectTypeEntity xiaoshuo1 = new SubjectTypeEntity();
        xiaoshuo1.setName("百合");
        xiaoshuo1.setId("22");
        xiaoshuo1.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo1.setIcon(R.mipmap.icon_bg_green);
        list.add(xiaoshuo1);

        SubjectTypeEntity xiaoshuo2 = new SubjectTypeEntity();
        xiaoshuo2.setName("基腐");
        xiaoshuo2.setId("23");
        xiaoshuo2.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo2.setIcon(R.mipmap.icon_bg_blue);
        list.add(xiaoshuo2);

        SubjectTypeEntity xiaoshuo3 = new SubjectTypeEntity();
        xiaoshuo3.setName("鬼畜");
        xiaoshuo3.setId("24");
        xiaoshuo3.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo3.setIcon(R.mipmap.icon_bg_pink);
        list.add(xiaoshuo3);

        SubjectTypeEntity xiaoshuo4 = new SubjectTypeEntity();
        xiaoshuo4.setName("治愈");
        xiaoshuo4.setId("25");
        xiaoshuo4.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo4.setIcon(R.mipmap.icon_bg_orange);
        list.add(xiaoshuo4);

        SubjectTypeEntity xiaoshuo5 = new SubjectTypeEntity();
        xiaoshuo5.setName("电波");
        xiaoshuo5.setId("26");
        xiaoshuo5.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo5.setIcon(R.mipmap.icon_bg_red);
        list.add(xiaoshuo5);

        SubjectTypeEntity xiaoshuo6 = new SubjectTypeEntity();
        xiaoshuo6.setName("玄幻");
        xiaoshuo6.setId("27");
        xiaoshuo6.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo6.setIcon(R.mipmap.icon_bg_yellow);
        list.add(xiaoshuo6);

        SubjectTypeEntity xiaoshuo7 = new SubjectTypeEntity();
        xiaoshuo7.setName("都市");
        xiaoshuo7.setId("31");
        xiaoshuo7.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo7.setIcon(R.mipmap.icon_bg_black);
        list.add(xiaoshuo7);

        SubjectTypeEntity xiaoshuo8 = new SubjectTypeEntity();
        xiaoshuo8.setName("仙侠");
        xiaoshuo8.setId("32");
        xiaoshuo8.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo8.setIcon(R.mipmap.icon_bg_purple);
        list.add(xiaoshuo8);

        SubjectTypeEntity xiaoshuo9 = new SubjectTypeEntity();
        xiaoshuo9.setName("悬疑");
        xiaoshuo9.setId("33");
        xiaoshuo9.setPid(ID_TYPE_XIAOSHUO);
        xiaoshuo9.setIcon(R.mipmap.icon_bg_brown);
        list.add(xiaoshuo9);

        return list;
    }

    /**
     * 获取Cosplay的二级分类
     * @return
     */
    public static ArrayList<SubjectTypeEntity> getCosTypeList() {
        ArrayList<SubjectTypeEntity> list = new ArrayList<SubjectTypeEntity>();

        SubjectTypeEntity coser1 = new SubjectTypeEntity();
        coser1.setName("coser");
        coser1.setId("5");
        coser1.setPid(ID_TYPE_COS);
        coser1.setIcon(R.mipmap.icon_bg_green);
        list.add(coser1);

        SubjectTypeEntity coser2 = new SubjectTypeEntity();
        coser2.setName("摄影师");
        coser2.setId("20");
        coser2.setPid(ID_TYPE_COS);
        coser2.setIcon(R.mipmap.icon_bg_blue);
        list.add(coser2);

        SubjectTypeEntity coser3 = new SubjectTypeEntity();
        coser3.setName("真人漫画");
        coser3.setId("21");
        coser3.setPid(ID_TYPE_COS);
        coser3.setIcon(R.mipmap.icon_bg_pink);
        list.add(coser3);

        SubjectTypeEntity coser4 = new SubjectTypeEntity();
        coser4.setName("cp");
        coser4.setId("30");
        coser4.setPid(ID_TYPE_COS);
        coser4.setIcon(R.mipmap.icon_bg_orange);
        list.add(coser4);



        return list;
    }

}
