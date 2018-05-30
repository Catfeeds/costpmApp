package com.fudan.cosmosapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.ui.home.WebView_show_news;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LoadListView extends ListView implements OnScrollListener ,AdapterView.OnItemClickListener{
	View footer;// ????????
	int totalItemCount;// ????????
	int lastVisibleItem;// ???????????item??
	boolean isLoading;// ????????
	ILoadListener iLoadListener;


	View header;// ?????????????????
	int headerHeight;// ????????????????????????
	int firstVisibleItem;// ?????????????????????item?????????
	int scrollState;// listview ?????????????????
	boolean isRemark;// ???????????????????listview??????????????
	int startY;// ?????????Y?????

	int state;// ???????????????
	final int NONE = 0;// ?????????????
	final int PULL = 1;// ?????????????????
	final int RELESE = 2;// ?????????????????
	final int REFLASHING = 3;// ???C????????
	IReflashListener iReflashListener;//???C?????????????
	Context context;

	HiddenTop	hiddenTop;
	private int mTouchShop;//????????????
	protected float mFirstY;//?????????????
	protected float mCurrentY;//?????Y??????
	protected int direction;//?????????????????????
	protected boolean mShow;//???????????????????



	public LoadListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
		this.context=context;
	}

	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context);
		this.context=context;
	}

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initView(context);

		this.context=context;
	}

	/**
	 * ?????????????????listview
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.footer_layout, null);
		footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
		this.addFooterView(footer);
		this.setOnScrollListener(this);


		header = inflater.inflate(R.layout.header_layout, null);
		measureView(header);
		headerHeight = header.getMeasuredHeight();
		Log.i("tag", "headerHeight = " + headerHeight);
		topPadding(-headerHeight);
		this.addHeaderView(header);
		this.setOnScrollListener(this);
		this.setOnItemClickListener(this);
	}


	private void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	private void topPadding(int topPadding) {
		header.setPadding(header.getPaddingLeft(), topPadding,
				header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.lastVisibleItem = firstVisibleItem + visibleItemCount;
		this.totalItemCount = totalItemCount;
		this.firstVisibleItem = firstVisibleItem;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (totalItemCount == lastVisibleItem
				&& scrollState == SCROLL_STATE_IDLE) {
			if (!isLoading) {
				isLoading = true;
				footer.findViewById(R.id.load_layout).setVisibility(
						View.VISIBLE);
				// ???????
				iLoadListener.onLoad();
			}
		}

		this.scrollState = scrollState;
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (firstVisibleItem == 0) {
					isRemark = true;

				}
				startY = (int) ev.getY();
				break;

			case MotionEvent.ACTION_MOVE:
				onMove(ev);
				break;
			case MotionEvent.ACTION_UP:
				if (state == RELESE) {
					state = REFLASHING;
					// ?????????C???????
					reflashViewByState();
					iReflashListener.onReflash();
				} else if (state == PULL) {
					state = NONE;
					isRemark = false;
					reflashViewByState();
				}

				//????top???
				mCurrentY = ev.getY();//?????????????
				if(mCurrentY - startY > mTouchShop){//?????????????????????????????????????  ???????????
					direction = 0;//down
				}else if(startY - mCurrentY > mTouchShop){//??????????
					direction = 1;//up
				}

				if(direction == 1){//???????????????? ?????????????????
					if(mShow){//???????????????  ???????????
						//???????????????
						hiddenTop.changeTop(1);
						//tolbarAnim(1);
						mShow = !mShow;
					}
				}else if(direction == 0){//???????????????? ?????????????????
					if(!mShow){//???????????????  ???????????
						//???????????????
						hiddenTop.changeTop(0);
						//tolbarAnim(0);
						mShow = !mShow;
					}
				}

				break;
		}
		return super.onTouchEvent(ev);
	}


	/**
	 * ??C????????????????????
	 *
	 * @param ev
	 */
	private void onMove(MotionEvent ev) {
		if (!isRemark) {
			return;
		}
		int tempY = (int) ev.getY();
		int space = tempY - startY;
		int topPadding = space - headerHeight;
		switch (state) {
			case NONE:
				if (space > 0) {
					state = PULL;
					reflashViewByState();
				}
				break;
			case PULL:
				topPadding(topPadding);
				if (space > headerHeight + 30
						&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
					state = RELESE;
					reflashViewByState();
				}
				break;
			case RELESE:
				topPadding(topPadding);
				if (space < headerHeight + 30) {
					state = PULL;
					reflashViewByState();
				} else if (space <= 0) {
					state = NONE;
					isRemark = false;
					reflashViewByState();
				}
				break;
		}
	}

	/**
	 * ?????????????????????????????????????
	 */
	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
		ProgressBar progress = (ProgressBar) header.findViewById(R.id.progress);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		switch (state) {
			case NONE:
				arrow.clearAnimation();
				topPadding(-headerHeight);
				break;

			case PULL:
				arrow.setVisibility(View.VISIBLE);
				progress.setVisibility(View.GONE);
				tip.setText("下拉刷新");
				arrow.clearAnimation();
				arrow.setAnimation(anim1);
				break;
			case RELESE:
				arrow.setVisibility(View.VISIBLE);
				progress.setVisibility(View.GONE);
				tip.setText("释放刷新");
				arrow.clearAnimation();
				arrow.setAnimation(anim);
				break;
			case REFLASHING:
				topPadding(50);
				arrow.setVisibility(View.GONE);
				progress.setVisibility(View.VISIBLE);
				tip.setText("刷新中..");
				arrow.clearAnimation();
				break;
		}
	}

	public void reflashComplete() {
		state = NONE;
		isRemark = false;
		reflashViewByState();
		TextView lastupdatetime = (TextView) header
				.findViewById(R.id.lastupdate_time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		lastupdatetime.setText(time);
	}


	/**
	 * ???????
	 */
	public void loadComplete(){
		isLoading = false;
		footer.findViewById(R.id.load_layout).setVisibility(
				View.GONE);
	}
	
	public void setInterface(ILoadListener iLoadListener,IReflashListener iReflashListener,HiddenTop	hiddenTop){

		this.iLoadListener = iLoadListener;
		this.iReflashListener = iReflashListener;
		this.hiddenTop=hiddenTop;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Intent	intent= new Intent(context,WebView_show_news.class);
		context.startActivity(intent);

	}

	public interface ILoadListener{
		public void onLoad();
	}

	public interface IReflashListener{
		public void onReflash();
	}

	public interface HiddenTop{
		public void	changeTop(int	d);
	}
}
