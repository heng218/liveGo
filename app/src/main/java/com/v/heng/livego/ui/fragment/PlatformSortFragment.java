package com.v.heng.livego.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.v.heng.livego.R;
import com.v.heng.livego.adapter.LiveSortAdapter;
import com.v.heng.livego.base.MyAnimation;
import com.v.heng.livego.dao.LiveInfoDao;
import com.v.heng.livego.ui.LiveListActivity;
import com.v.heng.livego.ui.Wait2developActivity;

import java.util.ArrayList;
import java.util.List;

public class PlatformSortFragment extends Fragment implements AdapterView.OnItemClickListener{

	private View rootView;
	private GridView gridView;
	private LiveSortAdapter liveSortAdapter;
	private List<String> sorts = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_platform_sort, null);

		initViews();
		initData();

		return rootView;
	}

	public void initViews() {
		gridView = (GridView) rootView.findViewById(R.id.gridView);
		liveSortAdapter = new LiveSortAdapter(getContext(), sorts);
		gridView.setAdapter(liveSortAdapter);
		gridView.setOnItemClickListener(this);
	}

	public void initData() {
		for ( LiveInfoDao.LivePlatform livePlatform : LiveInfoDao.LivePlatform.values()) {
			sorts.add(livePlatform.toString());
			liveSortAdapter.notifyDataSetChanged();
		}
		liveSortAdapter.setSorts(sorts);
		liveSortAdapter.notifyDataSetChanged();
	}


	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		MyAnimation.scaleOnBtnClick(view);

		TextView textView = (TextView) view.findViewById(R.id.textTv);
		String text = textView.getText().toString().trim();

		if("Twitch".equals(text) || "火猫".equals(text)) {
			Intent intent = new Intent(getContext(), Wait2developActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(getContext(), LiveListActivity.class);
			intent.putExtra("platform", text);
			startActivity(intent);
		}


	}



}
