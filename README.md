## 操作步骤 ##
将code目录里面的文件复制项目的eclipse项目的根目录中或者对应目录中
## 使用说明 ##
PullToRefreshScrollView暂时不能在布局中使用

1.目录com.dou361.ui PullToRefreshListView.java相当于一个listview，哪里需要使用，就在哪里布局设置即可如

    <com.dou361.ui.PullToRefreshListView
    android:id="@+id/phl_catalogue"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/white"/>

2.然后在代码中

    mPullToRefreshListView = new PullToRefreshListView(context);
	mPullToRefreshListView.setPullDownDamp(true);
	mPullToRefreshListView.setPullUpDamp(true);
	mPullToRefreshListView.setOnHeaderRefreshListener(this);
	mPullToRefreshListView.setOnFooterRefreshListener(this);
	mListView = mPullToRefreshListView.getContentView();
	mListReplyVideoAdapter = new ListCollectReplyVideoAdapter(context,
			mHandler, list, user);
	mListView.setAdapter(mListReplyVideoAdapter);
	mListView.setOnItemClickListener(this);


3.实现两个刷新监听

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		mPullToRefreshListView.postDelayed(new Runnable() {

			@Override
			public void run() {
				if (!isLoadDowning) {
					refreshListview();
				}
			}
		}, mPullToRefreshListView.delay_DURATION);
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		mPullToRefreshListView.postDelayed(new Runnable() {

			@Override
			public void run() {
				if (!isLoadUping) {
					loadListview();
				}
			}
		}, mPullToRefreshListView.delay_DURATION);
	}

4。刷新一般是访问网络，需要在子线程中执行refreshListview();和loadListview();方法中加载网络，最后通过handler发送完成访问，通知完成刷新。
上拉刷新完成
	mPullToRefreshListView.onHeaderRefreshCompleteAndTime();
下拉加载完成
	mPullToRefreshListView.onFooterRefreshComplete();

# PS: #
1.文件复制到项目中时，只要是有涉及到引用布局文件都会有报错的，重新导下包，将资源文件导包替换为自己项目中的即可。