package com.bigkoo.pickerview.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.lib.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.OnWheelChangedListener;
import com.bigkoo.pickerview.lib.WheelView;

/**
 * 
 * @author zhy
 * 
 */
public class WheelCity implements OnWheelChangedListener
{
	private JSONObject mJsonObj;
	private WheelView mProvince;
	private WheelView mCity;
	private WheelView mArea;

	private ArrayList<String> mProvinceDatas;
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
	private String mCurrentProviceName;
	private String mCurrentCityName;
	private String mCurrentAreaName ="";
	private Context mContext;


	public WheelCity(View view ,int type ,int screenheight,String mapKey){

		mContext = view.getContext();
		initJsonData();

		mProvince = (WheelView) view.findViewById(R.id.province);
		mCity = (WheelView) view.findViewById(R.id.city);
		mArea = (WheelView) view.findViewById(R.id.area);

		initDatas();
		mProvince.TEXT_SIZE = (screenheight / 100) * 4;
		mCity.TEXT_SIZE = (screenheight / 100) * 4;
		mArea.TEXT_SIZE = (screenheight / 100) * 4;
		mProvince.setAdapter(new ArrayWheelAdapter<String>(mProvinceDatas));
		mProvince.addChangingListener(this);
		mCity.addChangingListener(this);
		mArea.addChangingListener(this);
		mProvince.setCurrentItem(0);
		mProvince.setVisibleItems(5);
		mCity.setVisibleItems(5);
		mArea.setVisibleItems(5);
		updateCities();
		updateAreas();

	}

	public WheelCity(View view,boolean showArea,int screenheight){

		mContext = view.getContext();
		initJsonData();

		mProvince = (WheelView) view.findViewById(R.id.province);
		mCity = (WheelView) view.findViewById(R.id.city);
		mArea = (WheelView) view.findViewById(R.id.area);
		if(!showArea){
			mArea.setVisibility(View.GONE);
		}

		initDatas();
		mProvince.TEXT_SIZE = (screenheight / 100) * 3;
		mCity.TEXT_SIZE = (screenheight / 100) * 3;
		mArea.TEXT_SIZE = (screenheight / 100) * 3;
		mProvince.setAdapter(new ArrayWheelAdapter<String>(mProvinceDatas));
		mProvince.addChangingListener(this);
		mCity.addChangingListener(this);
		mArea.addChangingListener(this);
		mProvince.setCurrentItem(0);
		mProvince.setVisibleItems(5);
		mCity.setVisibleItems(5);
		mArea.setVisibleItems(5);
		updateCities();
		updateAreas();
	}

	private void updateAreas(String areaMapKey){

		String[] areas = mAreaDatasMap.get(areaMapKey);
		ArrayList<String> areaList = new ArrayList<String>();
		if (areas == null)
		{
			areas = new String[] { "" };
		}
		for (String area : areas){

			areaList.add(area);
		}
		mArea.setAdapter(new ArrayWheelAdapter<String>(areaList));
		mArea.setCurrentItem(0);
		mCurrentAreaName = areas[0];
	}
	private void updateAreas()
	{
		int pCurrent = mCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		updateAreas(mCurrentCityName);
	}
	private void updateCities(String cityMapKey){

		String[] cities = mCitisDatasMap.get(cityMapKey);
		if (cities == null)
		{
			cities = new String[] { "" };
		}
		ArrayList<String> cityList = new ArrayList<String>();
		for (String city : cities){
			cityList.add(city);
		}
		mCity.setAdapter(new ArrayWheelAdapter<String>( cityList));
		mCity.setCurrentItem(0);
		updateAreas();
	}
	private void updateCities()
	{
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas.get(pCurrent);
		updateCities(mCurrentProviceName);
	}

	private void initDatas()
	{
		try
		{
			JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
			mProvinceDatas = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonP = jsonArray.getJSONObject(i);//
				String province = jsonP.getString("p");//
				Log.d("citypop","province --- >" + province);
				mProvinceDatas.add( province);
				JSONArray jsonCs = null;
				try
				{
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("c");
				} catch (Exception e1)
				{
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++)
				{
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("n");//
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try
					{
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("a");
					} catch (Exception e)
					{
						continue;
					}

					String[] mAreasDatas = new String[jsonAreas.length()];//
					for (int k = 0; k < jsonAreas.length(); k++)
					{
						String area = jsonAreas.getJSONObject(k).getString("s");//
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}

				mCitisDatasMap.put(province, mCitiesDatas);
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	private void initJsonData()
	{
		try
		{
			StringBuffer sb = new StringBuffer();
			InputStream is = mContext. getAssets().open("city.json");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1)
			{
				sb.append(new String(buf, 0, len, "gbk"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue)
	{
		if (wheel == mProvince)
		{
			updateCities();
		} else if (wheel == mCity)
		{
			updateAreas();
		} else if (wheel == mArea)
		{
			String[] array = mAreaDatasMap.get(mCurrentCityName);
			if(array != null && array.length > newValue){
				mCurrentAreaName = array[newValue];
			}

		}
	}
	public String getResult(){

		String result = "";

		if(mProvince.getVisibility() == View.VISIBLE){
			result += mCurrentProviceName;
		}
		if(mCity.getVisibility() == View.VISIBLE){
			result += mCurrentCityName;
		}
		if(mArea .getVisibility() == View.VISIBLE){
			result += mCurrentAreaName;
		}
		Log.d("wheel","result --- >" + mCurrentAreaName);

		return result;
	}

	public int getProvinceCount() {

		return mProvinceDatas.size();
	}

//	public void showChoose(View view)
//	{
//		Toast.makeText(this, mCurrentProviceName + mCurrentCityName + mCurrentAreaName, 1).show();
//	}
}
