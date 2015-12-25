package com.dou361.third_im;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.note.view.ViewUtils;
import com.dou361.note.view.annotation.ViewInject;
import com.dou361.ui.PullToRefreshListView;
import com.dou361.ui.PullToRefreshView;
import com.dou361.utils.EmojiUtil;
import com.dou361.utils.LogUtils;
import com.dou361.utils.UIUtils;
import com.jingwang.eluxue_online.R;
import com.jingwang.eluxue_online.activity.BaseActivity;
import com.jingwang.eluxue_online.adapter.ChatMsgListAdapter;
import com.jingwang.eluxue_online.adapter.EmojiAdapter;
import com.jingwang.eluxue_online.adapter.EmojiItemAdapter;
import com.jingwang.eluxue_online.bean.ChatEntity;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMFileElem;
import com.tencent.TIMGroupTipsElem;
import com.tencent.TIMGroupTipsType;
import com.tencent.TIMImageElem;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;
import com.tencent.TIMMessageStatus;
import com.tencent.TIMSoundElem;
import com.tencent.TIMTextElem;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatMsgActivity extends BaseActivity implements OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, OnItemClickListener {

    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_center)
    private TextView tv_center;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.lv_msg_items)
    private PullToRefreshListView mPullToRefreshListView;
    @ViewInject(R.id.btn_emoji)
    private ImageButton mImgBtnEmoji;
    @ViewInject(R.id.btn_media_pls)
    private ImageButton mImgBtnMedioPlus;
    @ViewInject(R.id.et_msg_input)
    private EditText mETMsgInput;
    @ViewInject(R.id.iv_voice_arrow)
    private ImageButton mImgBtnVoiceArrow;
    @ViewInject(R.id.btn_send_msg)
    private Button mBtnSendMsg;
    @ViewInject(R.id.btn_voice)
    private ImageButton mBtnVoice;
    @ViewInject(R.id.rl_voice)
    private RelativeLayout mRLVoice;
    @ViewInject(R.id.btn_send_voice)
    private ImageView mBtnSendVoice;
    @ViewInject(R.id.chronometer)
    private Chronometer chronometer;
    @ViewInject(R.id.ll_media)
    private LinearLayout mLLMedia;
    @ViewInject(R.id.btn_send_photo)
    private Button mBtnSendPhoto;
    @ViewInject(R.id.btn_camera)
    private Button mBtnToolCamera;
    @ViewInject(R.id.btn_send_file)
    private Button mBtnSendFile;
    @ViewInject(R.id.btn_video)
    private Button mBtnSendVideo;
    @ViewInject(R.id.ll_emojis)
    private LinearLayout mLLemojis;
    @ViewInject(R.id.pb_load_more)
    private ProgressBar mPBLoadData;
    //    @ViewInject(R.id.btn_msg_remove)
//    private Button mBtnMsgRemove;
//    @ViewInject(R.id.btn_group_members)
//    private Button mBtnGroupMember;
    /**
     * 聊天信息ListView
     */
    private ListView mListView;
    /**
     * 聊天信息适配器
     */
    private ChatMsgListAdapter adapter;
    /**
     * 聊天信息集合
     */
    private List<ChatEntity> listChatEntity;
    /**
     * emoji的viewpager
     */
    private ViewPager vpEmoji;
    /**
     * emoji的viewpager的view集合
     */
    private ArrayList<View> pageViews;
    /**
     * emoji的viewpager的适配器集合
     */
    private List<EmojiAdapter> emojiAdapters;
    /**
     * emoji的viewpager的集合
     */
    private List<List<String>> emojis;
    private InputMethodManager inputKeyBoard;
    private TIMConversation conversation;
    private String mStrGroupName;
    /**
     * 打开类型为点对点的交流
     */
    public static int CHATTYPE_C2C = 0;
    /**
     * 打开类型为组的交流
     */
    public static int CHATTYPE_GROUP = 1;
    /**
     * 打开类型为系统的交流
     */
    public static int CHATTYPE_GROUP_SYSTEM = 2;
    private int mChatType;

    private File mPhotoFile;
    private String mStrPhotoPath;
    private File mPttFile;
    private long mPttRecordTime;
    public static boolean bFromOrgPic = false;

    private final static int FOR_SELECT_PHOTO = 1;
    private final static int FOR_START_CAMERA = 2;
    private final static int FOR_SELECT_FILE = 3;
    private final static int FOR_PHOTO_PREVIEW = 4;
    public final static int FOR_CHAT_TEXT_MENU = 5;
    public final static int FOR_CHAT_IMAGE_MENU = 6;

    public final static int RESULT_CHAT_MENU_COPY = 1;
    public final static int RESULT_CHAT_MENU_DELETE = 2;
    public final static int RESULT_CHAT_MENU_RESEND = 3;
    public final static int RESULT_CHAT_MENU_SAVE = 4;
    private MediaRecorder mRecorder = null;


    private int current = 0;
    private boolean mIsLoading = false;
    private boolean mBMore = true;
    private final int MAX_PAGE_NUM = 20;
    private int mLoadMsgNum = MAX_PAGE_NUM;
    private boolean mBNerverLoadMore = true;
    private String mStrPeerName;
    private int mPicLevel = 1;
    private ClipboardManager mClipboard;
    private int itemPos;
//	private Uri photoUri;


    private Intent intent;

    String userID;
    String userSig;
    TIMUser user = new TIMUser();

    /**
     * 后续删除
     */
    private void tem() {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra("chatType", ChatMsgActivity.CHATTYPE_GROUP);
        intent.putExtra("groupID", "@TGS#3GW6RPAE4" /*entity.getID()*/);
        intent.putExtra("groupName", "m10000" /*entity.getName()*/);
//        intent.putExtra("groupID", "@TGS#3KWSRPAE6" /*entity.getID()*/);
//        intent.putExtra("groupName", "聊天室id" /*entity.getName()*/);
        intent.putExtra("groupType", QQIMParamsConfig.TYPE_CHAT_ROOM /*mStrGroupType*/);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_chatmsg);
        ViewUtils.inject(this);
        intent = getIntent();
        tem();
        onInit();
        getMessage();

    }

    protected void onInit() {
        itemPos = intent.getIntExtra("itemPos", 0);
        ll_back.setOnClickListener(this);
        tv_title.setText("返回");

        mPullToRefreshListView.setPullDownDamp(true);
        mPullToRefreshListView.setPullUpDamp(true);
        mPullToRefreshListView.setOnHeaderRefreshListener(this);
        mPullToRefreshListView.setOnFooterRefreshListener(this);
        mListView = mPullToRefreshListView.getContentView();
//        mListReplyVideoAdapter = new DirectAdapter(context, list, user);
//        mListView.setAdapter(mListReplyVideoAdapter);
        mListView.setOnItemClickListener(this);

        mImgBtnEmoji.setOnClickListener(this);
        mImgBtnMedioPlus.setOnClickListener(this);
        mETMsgInput.setOnClickListener(this);
        mImgBtnVoiceArrow.setOnClickListener(this);
        mBtnSendMsg.setOnClickListener(this);
        mBtnVoice.setOnClickListener(this);
        mBtnSendPhoto.setOnClickListener(this);
        mBtnToolCamera.setOnClickListener(this);
        mBtnSendFile.setOnClickListener(this);
        mBtnSendVideo.setOnClickListener(this);
        inputKeyBoard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mChatType = intent.getIntExtra("chatType", CHATTYPE_C2C);

        if (mChatType == CHATTYPE_C2C) {
//            mBtnGroupMember.setVisibility(View.GONE);
            mStrPeerName = intent.getStringExtra("userName");
            UserInfo user = UserInfoManagerNew.getInstance().getContactsList().get(mStrPeerName);
            tv_center.setText(user != null ? user.getDisplayUserName() : mStrPeerName);
            conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, mStrPeerName);

//            Button btUserInfo = (Button) findViewById(R.id.btn_user_info);
//            btUserInfo.setVisibility(View.VISIBLE);
//            btUserInfo.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(ChatNewActivity.this, UserInfoActivity.class);
//                    intent.putExtra("userID", mStrPeerName);
//                    startActivity(intent);
//                }
//
//            });
        } else {
            findViewById(R.id.video_chat).setVisibility(View.GONE);
//            mBtnMsgRemove.setVisibility(View.GONE);
//            mBtnGroupMember.setVisibility(View.VISIBLE);
            mStrPeerName = intent.getStringExtra("groupID");
            mStrGroupName = intent.getStringExtra("groupName");
            //	mStrGroupType = intent.getStringExtra("groupType");
            conversation = TIMManager.getInstance().getConversation(TIMConversationType.Group, mStrPeerName);
            Log.e("dou361", "--------group chat, id:" + mStrGroupName + ":" + mStrPeerName + ":" + conversation);
            tv_center.setText(mStrGroupName);
        }
        listChatEntity = new ArrayList<ChatEntity>();
        adapter = new ChatMsgListAdapter(this, listChatEntity);
        mListView.setAdapter(adapter);
        if (mListView.getCount() > 1) {
            mListView.setSelection(mListView.getCount() - 1);
        }


        SetMessageListener();
        InitViewPager();

        mETMsgInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    mBtnVoice.setVisibility(View.GONE);
                    mBtnSendMsg.setVisibility(View.VISIBLE);

                } else {
                    mBtnVoice.setVisibility(View.VISIBLE);
                    mBtnSendMsg.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtnSendVoice.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mBtnSendVoice.setBackgroundResource(R.mipmap.chat_send_voice_small);
                        startRecording();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        return true;
                    case MotionEvent.ACTION_UP:
                        mBtnSendVoice.setBackgroundResource(R.mipmap.chat_send_voice_big);
                        LogUtils.tag(tag).log("stop record");
                        if (stopRecording() == false) {
                            LogUtils.tag(tag).log("recording ret false");
                            return true;
                        }
                        sendFile(mPttFile.getAbsolutePath(), TIMElemType.Sound);
                        return true;
                    default:
                        return true;
                }
            }
        });
        mListView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideMsgIputKeyboard();
                mImgBtnVoiceArrow.setVisibility(View.GONE);
                mRLVoice.setVisibility(View.GONE);
                mLLMedia.setVisibility(View.GONE);
                mETMsgInput.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                switch (scrollState) {
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        LogUtils.tag(tag).log(view.getFirstVisiblePosition() + ":" + mIsLoading + ":" + mBMore);
                        if (view.getFirstVisiblePosition() == 0 && !mIsLoading && mBMore) {
                            mPBLoadData.setVisibility(View.VISIBLE);
                            mBNerverLoadMore = false;
                            mIsLoading = true;
                            mLoadMsgNum += MAX_PAGE_NUM;
                            LogUtils.tag(tag).log("num:" + mLoadMsgNum);
                            getMessage();

//						mIsLoading = false;

                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub

            }

        });

//        mBtnGroupMember.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(ChatNewActivity.this, GroupInfoActivity.class);
//                intent.putExtra("groupID", mStrPeerName);
//                intent.putExtra("groupName", mStrGroupName);
//                startActivity(intent);
//            }
//        });
        //	getMessage();
    }

    private void InitViewPager() {
        EmojiUtil.getInstace().initData();
        emojis = EmojiUtil.getInstace().mEmojiPageList;
        vpEmoji = (ViewPager) findViewById(R.id.vPagerEmoji);

        pageViews = new ArrayList<View>();
        View nullView = new View(getBaseContext());
        nullView.setBackgroundColor(Color.TRANSPARENT);
        pageViews.add(nullView);

        emojiAdapters = new ArrayList<EmojiAdapter>();
        for (int i = 0; i < emojis.size(); i++) {
            GridView view = new GridView(getBaseContext());
            EmojiAdapter adapter = new EmojiAdapter(getBaseContext(), emojis.get(i));
            //	LogUtils.tag(tag).log("InitViewPager:" + emojis.get(i).size());
            view.setAdapter(adapter);
            emojiAdapters.add(adapter);
            view.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    String strEmoji = (String) emojiAdapters.get(current).getItem(position);
                    if (strEmoji.equals(EmojiUtil.EMOJI_DELETE_NAME)) {
                        if (!TextUtils.isEmpty(mETMsgInput.getText())) {
                            int selection = mETMsgInput.getSelectionStart();
                            String strInputText = mETMsgInput.getText().toString();
                            if (selection > 0) {
                                String strText = strInputText.substring(selection - 1);
                                if ("]".equals(strText)) {
                                    int start = strInputText.lastIndexOf("[");
                                    int end = selection;
                                    mETMsgInput.getText().delete(start, end);
                                    return;
                                }
                                mETMsgInput.getText().delete(selection - 1, selection);
                            }
                        }
                    } else {
                        SpannableString spannableString = EmojiUtil.getInstace().addEmoji(getBaseContext(), strEmoji);
                        if (spannableString == null) {
                            return;
                        }
                        mETMsgInput.append(spannableString);
                    }
                }
            });
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setNumColumns(7);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            pageViews.add(view);
        }

        View nullView2 = new View(getBaseContext());
        nullView2.setBackgroundColor(Color.TRANSPARENT);
        pageViews.add(nullView2);


        vpEmoji.setAdapter(new EmojiItemAdapter(pageViews));
        vpEmoji.setCurrentItem(1);
        vpEmoji.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                current = arg0 - 1;
                if (arg0 == pageViews.size() - 1 || arg0 == 0) {
                    if (arg0 == 0) {
                        vpEmoji.setCurrentItem(arg0 + 1);
                    } else {
                        vpEmoji.setCurrentItem(arg0 - 1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

        });


    }

    private boolean isTopActivity() {
        boolean isTop = false;
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        if (cn.getClassName().contains(tag)) {
            isTop = true;
        }
        LogUtils.tag(tag).log("is Top Activity:" + isTop);
        return isTop;
    }

    private TIMMessageListener msgListener = new TIMMessageListener() {

        @Override
        public boolean onNewMessages(List<TIMMessage> arg0) {
            // TODO Auto-generated method stub
            LogUtils.tag(tag).log("new messge listnener:" + arg0.size());

            if (isTopActivity()) {
                for (TIMMessage msg : arg0) {
                    if (mStrPeerName.equals(msg.getConversation().getPeer())) {
                        //如果是群名称修改通知，改标题名称
                        for (int i = 0; i < msg.getElementCount(); i++) {
                            TIMElem elem = msg.getElement(i);
                            if (elem.getType() == TIMElemType.GroupTips) {
                                TIMGroupTipsElem tipsElem = (TIMGroupTipsElem) elem;
                                if (tipsElem.getTipsType() == TIMGroupTipsType.ModifyGroupInfo) {
                                    mStrGroupName = tipsElem.getGroupName();
                                    tv_center.setText(mStrGroupName);
                                }
                            }
                        }
                        getMessage();
                        //继续传递，mainactivity处理一些本地缓存的数据
                        return false;
                        //	return true;
                    }
                }
            }
            return false;
        }

    };

    private void SetMessageListener() {
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(msgListener);
    }

    public void onBack(View view) {
        LogUtils.tag(tag).log("finish:" + mStrPeerName);
        setResult(0, new Intent().putExtra("itemPos", itemPos));
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        //群名称可能已经修改
        if (mChatType == CHATTYPE_GROUP) {
            if (UserInfoManagerNew.getInstance().getGroupID2Info().containsKey(mStrPeerName)) {
                tv_center.setText(UserInfoManagerNew.getInstance().getGroupID2Info().get(mStrPeerName).getName());
            }

        }
        //getMessage();

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        mBtnVoice.setVisibility(View.VISIBLE);
        mBtnSendMsg.setVisibility(View.GONE);

        if (id == R.id.ll_back) {
            onBackPressed();
        } else if (id == R.id.btn_emoji) {
            hideMsgIputKeyboard();
            if (mLLemojis.getVisibility() == View.GONE) {
                mLLemojis.setVisibility(View.VISIBLE);
                mImgBtnEmoji.setImageResource(R.drawable.aio_keyboard);
            } else {
                mLLemojis.setVisibility(View.GONE);
                mImgBtnEmoji.setImageResource(R.drawable.aio_emoji);
            }
            mImgBtnMedioPlus.setImageResource(R.drawable.aio_fold);
            //	mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            mETMsgInput.setVisibility(View.VISIBLE);
            mRLVoice.setVisibility(View.GONE);
            mLLMedia.setVisibility(View.GONE);
            mImgBtnVoiceArrow.setVisibility(View.GONE);
            if (mETMsgInput.getText().toString().isEmpty()) {
                mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            } else {
                mBtnVoice.setVisibility(View.GONE);
                mBtnSendMsg.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.btn_media_pls) {
            hideMsgIputKeyboard();
            mImgBtnVoiceArrow.setVisibility(View.GONE);
            mLLemojis.setVisibility(View.GONE);
            mRLVoice.setVisibility(View.GONE);
            mETMsgInput.setVisibility(View.VISIBLE);
            if (mLLMedia.getVisibility() == View.GONE) {
                mLLMedia.setVisibility(View.VISIBLE);
                mImgBtnMedioPlus.setImageResource(R.drawable.aio_keyboard);
            } else {
                mLLMedia.setVisibility(View.GONE);
                mImgBtnMedioPlus.setImageResource(R.drawable.aio_fold);
            }
            mImgBtnEmoji.setImageResource(R.drawable.aio_emoji);
            //	mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            if (mETMsgInput.getText().toString().isEmpty()) {
                mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            } else {
                mBtnVoice.setVisibility(View.GONE);
                mBtnSendMsg.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.btn_voice) {
            UIUtils.showToastShort("请输入内容！");
//            hideMsgIputKeyboard();
//            mLLemojis.setVisibility(View.GONE);
//            mBtnVoice.setVisibility(View.VISIBLE);
//            if (mRLVoice.getVisibility() == View.GONE) {
//                mRLVoice.setVisibility(View.VISIBLE);
//                mBtnVoice.setBackgroundResource(R.drawable.aio_keyboard);
//                mImgBtnVoiceArrow.setVisibility(View.VISIBLE);
//                mImgBtnVoiceArrow.setImageResource(R.drawable.aio_audio_button_down_selector);
//                mETMsgInput.setVisibility(View.GONE);
//            } else {
//                mRLVoice.setVisibility(View.GONE);
//                mETMsgInput.setVisibility(View.VISIBLE);
//                mImgBtnVoiceArrow.setVisibility(View.GONE);
//                mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
//            }
//            mLLMedia.setVisibility(View.GONE);
//            mImgBtnEmoji.setImageResource(R.drawable.aio_emoji);
//            mImgBtnMedioPlus.setImageResource(R.drawable.aio_fold);
        } else if (id == R.id.et_msg_input) {
            mETMsgInput.setVisibility(View.VISIBLE);
            mLLemojis.setVisibility(View.GONE);
            mRLVoice.setVisibility(View.GONE);
            mLLMedia.setVisibility(View.GONE);
            mImgBtnVoiceArrow.setVisibility(View.GONE);
            mImgBtnEmoji.setImageResource(R.drawable.aio_emoji);
            mImgBtnMedioPlus.setImageResource(R.drawable.aio_fold);
            //	mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            if (mETMsgInput.getText().toString().isEmpty()) {
                mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            } else {
                mBtnVoice.setVisibility(View.GONE);
                mBtnSendMsg.setVisibility(View.VISIBLE);
            }

        } else if (id == R.id.btn_send_msg) {
            mImgBtnEmoji.setImageResource(R.drawable.aio_emoji);
            mImgBtnMedioPlus.setImageResource(R.drawable.aio_fold);
            mBtnVoice.setBackgroundResource(R.drawable.aio_voice);
            mLLemojis.setVisibility(View.GONE);
            mRLVoice.setVisibility(View.GONE);
            mLLMedia.setVisibility(View.GONE);
            mImgBtnVoiceArrow.setVisibility(View.GONE);
            String msg = mETMsgInput.getText().toString();
            sendText(msg);
            mETMsgInput.setText("");
        } else if (id == R.id.iv_voice_arrow) {
            if (mRLVoice.getVisibility() == View.VISIBLE) {
                mImgBtnVoiceArrow.setImageResource(R.drawable.aio_audio_button_up_selector);
                mRLVoice.setVisibility(View.GONE);
            } else {
                mImgBtnVoiceArrow.setImageResource(R.drawable.aio_audio_button_down_selector);
                mRLVoice.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.btn_camera) {
            startCamera();
            //startCameranew();
        } else if (id == R.id.btn_send_photo) {
            selectPhoto();
        } else if (id == R.id.btn_send_file) {
            selectFile();
        }

    }

    private void hideMsgIputKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputKeyBoard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void getMessage() {
        LogUtils.tag(tag).log("getMessage begin");
        if (conversation == null) {
            LogUtils.tag(tag).log("conversation null");
            return;
        }
        conversation.getMessage(mLoadMsgNum, null, new TIMValueCallBack<List<TIMMessage>>() {
            @Override
            public void onError(int code, String desc) {
                LogUtils.tag(tag).log("get msgs failed:" + code + ":" + desc);
                mPBLoadData.setVisibility(View.GONE);
                mIsLoading = false;
            }

            @Override
            public void onSuccess(List<TIMMessage> msgs) {

                final List<TIMMessage> tmpMsgs = msgs;
                LogUtils.tag(tag).log("getMessage success:" + msgs.size() + "|" + mLoadMsgNum + "|mIsLoading:" + mIsLoading);
                if (msgs.size() > 0) {
                    conversation.setReadMessage(msgs.get(0));
                }
                if (!mBNerverLoadMore && (msgs.size() < mLoadMsgNum)) {
                    mBMore = false;
                }

                listChatEntity.clear();
                for (int i = 0; i < tmpMsgs.size(); i++) {
                    TIMMessage msg = tmpMsgs.get(i);
                    for (int j = 0; j < msg.getElementCount(); j++) {
                        if (msg.getElement(j) == null) {
                            continue;
                        }
                        if (msg.status() == TIMMessageStatus.HasDeleted) {
                            //	LogUtils.tag(tag).log("deleted msg:" + msg.getSender() +":" + msg.getMsgId() +":" );
                            continue;
                        }
                        ChatEntity entity = new ChatEntity();
                        entity.setMessage(msg);
                        entity.setElem(msg.getElement(j));
                        entity.setIsSelf(msg.isSelf());
                        entity.setTime(msg.timestamp());
                        entity.setType(msg.getConversation().getType());
                        entity.setSenderName(msg.getSender());
                        entity.setStatus(msg.status());
                        listChatEntity.add(entity);
                    }
                }

                Collections.reverse(listChatEntity);
                adapter.notifyDataSetChanged();
                mListView.setVisibility(View.VISIBLE);
                if (mListView.getCount() > 1) {
                    if (mIsLoading) {
                        mListView.setSelection(0);
                    } else {
                        mListView.setSelection(mListView.getCount() - 1);
                    }
                }
                mIsLoading = false;
            }
        });

        mPBLoadData.setVisibility(View.GONE);
    }

    private void sendMsgContent(TIMMessage msg) {
        LogUtils.tag(tag).log("ready send  msg");
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //服务器返回了错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见附录
                if (code == QQIMParamsConfig.TEXT_MSG_FAILED_FOR_TOO_LOGNG) {
                    desc = "消息太长";
                } else if (code == QQIMParamsConfig.SEND_MSG_FAILED_FOR_PEER_NOT_LOGIN) {
                    desc = "对方账号不存在或未登陆过！";
                }
                LogUtils.tag(tag).log("send message failed. code: " + code + " errmsg: " + desc);
                Toast.makeText(getBaseContext(), "发送消息失败. code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
                getMessage();
            }

            @Override
            public void onSuccess(TIMMessage arg0) {
                LogUtils.tag(tag).log("Send text Msg ok");
                getMessage();

            }
        });
        getMessage();
    }

    private void sendText(String str) {
        if (str.length() == 0) {
            return;
        }

        try {
            byte[] byte_num = str.getBytes("utf8");
            if (byte_num.length > QQIMParamsConfig.MAX_TEXT_MSG_LENGTH) {
                Toast.makeText(getBaseContext(), "消息太长，最多" + QQIMParamsConfig.MAX_TEXT_MSG_LENGTH + "个字符", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        TIMMessage msg = new TIMMessage();
        //	msg.addTextElement(str);
        TIMTextElem elem = new TIMTextElem();
        elem.setText(str);
        int iRet = msg.addElement(elem);
        if (iRet != 0) {
            LogUtils.tag(tag).log("add element error:" + iRet);
            return;
        }
        sendMsgContent(msg);
    }

    private String getPicPathFromData(Intent data) {
        Uri uri = data.getData();
        if (uri == null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));

        }
        ContentResolver resolver = getContentResolver();
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            //	cursor = resolver.query(originalUri, proj, null, null, null);
            cursor = resolver.query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            return path;
        } catch (Exception e) {
            LogUtils.tag(tag).log("FOR_SELECT_PHOTO Exception:" + e.toString());
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        //   }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.tag(tag).log("WL_DEBUG onActivityResult requestCode " + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == FOR_START_CAMERA) {
                if (mStrPhotoPath == null || mStrPhotoPath.length() == 0) {
                    LogUtils.tag(tag).log("mStrPhotoPath null");
                    return;
                }
                File file = new File(mStrPhotoPath);
                if (file == null || !file.exists()) {
                    LogUtils.tag(tag).log("mStrPhotoPath file not exists");
                    return;
                }
                Intent intent = new Intent(ChatMsgActivity.this, PhotoPreviewActivity.class);
                intent.putExtra("photo_url", mStrPhotoPath);
                startActivityForResult(intent, FOR_PHOTO_PREVIEW);


//				String[] pojo = {MediaStore.Images.Media.DATA};
//				String picPath = null;
//				Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
//				if (cursor != null) {
//					int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
//					cursor.moveToFirst();
//					picPath = cursor.getString(columnIndex);
//					if(VERSION.SDK_INT < 14) {  
//					    cursor.close();  
//					 }  
//				}
//				if (picPath != null
//						&& (picPath.endsWith(".png") || picPath.endsWith(".PNG")
//								|| picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
//					Intent intent = new Intent(ChatNewActivity.this,PhotoPreviewActivity.class);
//	               intent.putExtra("photo_url",picPath);
//               startActivityForResult(intent, FOR_PHOTO_PREVIEW);	

//				} else {
//					Toast.makeText(getApplicationContext(), "选择图片文件不正确",
//							Toast.LENGTH_SHORT).show();
//				}

            } else if (requestCode == FOR_PHOTO_PREVIEW) {
                if (data != null) {
                    boolean bOrg = data.getBooleanExtra("pic_org", false);
                    String filePath = data.getStringExtra("filePath");
                    LogUtils.tag(tag).log("pic org:" + bOrg + ":" + filePath);
                    if (filePath == null) {
                        return;
                    }
                    if (bOrg) {
                        mPicLevel = 0;
                    }
                    sendFile(filePath, TIMElemType.Image);
                }
            } else if (requestCode == FOR_SELECT_PHOTO) {
                String filePath = getPicPathFromData(data);
                if (filePath != null) {
                    Intent intent = new Intent(ChatMsgActivity.this, PhotoPreviewActivity.class);
                    intent.putExtra("photo_url", filePath);
                    startActivityForResult(intent, FOR_PHOTO_PREVIEW);
                }
            } else if (requestCode == FOR_SELECT_FILE) {
                String filePath = null;
                ContentResolver resolver = getContentResolver();
                Cursor cursor = null;
                Uri originalUri = data.getData();
                if ("content".equalsIgnoreCase(originalUri.getScheme())) {
                    String[] projection = {"_data"};
                    try {
                        //  Cursor cursor = null;
                        cursor = resolver.query(originalUri, projection, null, null, null);
                        int column_index = cursor.getColumnIndexOrThrow("_data");
                        if (cursor.moveToFirst()) {
                            filePath = cursor.getString(column_index);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }

                } else if ("file".equalsIgnoreCase(originalUri.getScheme())) {
                    filePath = originalUri.getPath();
                }
                if (filePath != null) {
                    LogUtils.tag(tag).log("ready send file:" + filePath);
                    sendFile(filePath, TIMElemType.File);
                } else {
                    LogUtils.tag(tag).log("file name null");
                }
            }

        }
        if (requestCode == FOR_CHAT_TEXT_MENU) {
            if (data == null)
                return;
            final int pos = data.getIntExtra("item", -1);
            ChatEntity entity = (ChatEntity) adapter.getItem(pos);
            if (entity == null) {
                LogUtils.tag(tag).log("get msg null:" + pos);
                return;
            }
            if (resultCode == RESULT_CHAT_MENU_COPY) {
                LogUtils.tag(tag).log("copy msg:" + pos);
                TIMTextElem elem = (TIMTextElem) entity.getElem();
                if (elem != null) {
                    LogUtils.tag(tag).log("get msg:" + elem.getText());
                    mClipboard.setText(elem.getText());
                }

                adapter.notifyDataSetChanged();
                //mlistView.setSelection(data.getIntExtra("position", adapter.getCount()) - 1);
                return;
            } else if (resultCode == RESULT_CHAT_MENU_DELETE) {
                TIMMessage msg = entity.getMessage();
                if (msg.remove()) {
                    listChatEntity.remove(pos);
                    LogUtils.tag(tag).log("delete msg succ:" + msg.getSender() + ":" + msg.getMsgId());
                } else {
                    LogUtils.tag(tag).log("delete msg error:" + msg.getSender() + ":" + msg.getMsgId());
                }
                adapter.notifyDataSetChanged();
                mListView.requestFocusFromTouch();
                mListView.setSelection(pos - 1);
                LogUtils.tag(tag).log("delete msg:" + pos);
            } else if (resultCode == RESULT_CHAT_MENU_RESEND) {
                TIMMessage msg = entity.getMessage();
                sendMsgContent(msg);
            }
        } else if (requestCode == FOR_CHAT_IMAGE_MENU) {
            if (data == null)
                return;
            int pos = data.getIntExtra("item", -1);
            ChatEntity entity = (ChatEntity) adapter.getItem(pos);
            if (resultCode == RESULT_CHAT_MENU_COPY) {
                return;
            } else if (resultCode == RESULT_CHAT_MENU_DELETE) {
                TIMMessage msg = entity.getMessage();
                if (msg.remove()) {
                    listChatEntity.remove(pos);
                    LogUtils.tag(tag).log("delete msg succ:" + msg.getSender() + ":" + msg.getMsgId());
                } else {
                    LogUtils.tag(tag).log("delete msg error:" + msg.getSender() + ":" + msg.getMsgId());
                }
                adapter.notifyDataSetChanged();
                mListView.requestFocusFromTouch();
                mListView.setSelection(pos - 1);
                LogUtils.tag(tag).log("delete msg:" + pos);
            } else if (resultCode == RESULT_CHAT_MENU_RESEND) {
                TIMMessage msg = entity.getMessage();
                sendMsgContent(msg);
            } else if (resultCode == RESULT_CHAT_MENU_SAVE) {
                TIMFileElem elem = (TIMFileElem) entity.getElem();
                saveFile(elem);
            }
        }
    }

    private void saveFile(final TIMFileElem elem) {

        elem.getFile(new TIMValueCallBack<byte[]>() {

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                LogUtils.tag(tag).log("save file error:" + arg0 + ":" + arg1);
            }

            @Override
            public void onSuccess(byte[] arg0) {
                // TODO Auto-generated method stub
                try {

                    File tmpeFile = new File(QQIMParamsConfig.FILE_DIR);
                    if (!tmpeFile.exists()) {
                        tmpeFile.mkdirs();
                    }
//					File newFile = new File(logPath + elem.getFileName());
//					if(!newFile.exists()){
//						newFile.createNewFile();
//					}

                    String fileName = QQIMParamsConfig.FILE_DIR + elem.getUuid();
                    FileOutputStream out = new FileOutputStream(fileName);
                    out.write(arg0);
                    out.close();
                    LogUtils.tag(tag).log("save file ok:" + fileName + ":" + arg0.length);
                    getMessage();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });


    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, FOR_SELECT_PHOTO);
    }

    private String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyyMMdd_HHmmss");
        return dateFormat.format(date);
    }

    private void startCamera() {
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                File mPhotoFile = new File(QQIMParamsConfig.IMAG_DIR);
                if (!mPhotoFile.exists()) {
                    mPhotoFile.mkdirs();
                }
                mStrPhotoPath = QQIMParamsConfig.IMAG_DIR + getFileName() + ".jgp";
                LogUtils.tag(tag).log("pic file path:" + mStrPhotoPath);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mStrPhotoPath)));
//            	  ContentValues values = new ContentValues(); 
//            	  photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values); 
//            	  intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, FOR_START_CAMERA);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "启动失败：" + e.toString(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "sd卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    private void startCameranew() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(getImageByCamera, FOR_START_CAMERA);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    private void selectFile() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        if (VERSION.SDK_INT < 19) {
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, FOR_SELECT_FILE);
    }

    // level 0: 原图发送  1: 高压缩率图发送(图片较小，默认值)   2:高清图发送(图片较大)
    private void sendFile(String path, TIMElemType type) {
        if (path.length() == 0) {
            return;
        }
        //从文件读取数据
        File f = new File(path);
        LogUtils.tag(tag).log("file len:" + f.length());
        if (f.length() == 0) {
            LogUtils.tag(tag).log("file empty!");
            return;
        }
        byte[] fileData = new byte[(int) f.length()];
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(f));
            dis.readFully(fileData);
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TIMMessage msg = new TIMMessage();
        try {
            if (type == TIMElemType.Image) {
                TIMImageElem elem = new TIMImageElem();
                LogUtils.tag(tag).log("pic level:" + mPicLevel);
                elem.setLevel(mPicLevel);
                elem.setPath(path);
                if (0 != msg.addElement(elem)) {
                    LogUtils.tag(tag).log("add image element error");
                    return;
                }
            } else if (type == TIMElemType.Sound) {
                TIMSoundElem elem = new TIMSoundElem();
                elem.setData(fileData);
                elem.setDuration(mPttRecordTime);
                Log.d("TAG", "sound  size:" + fileData.length);
                if (0 != msg.addElement(elem)) {
                    LogUtils.tag(tag).log("add sound element error");
                    return;
                }
            } else if (type == TIMElemType.File) {
                TIMFileElem elem = new TIMFileElem();
                elem.setFileName(path.substring(path.lastIndexOf("/") + 1));
                elem.setData(fileData);
                if (fileData.length > QQIMParamsConfig.MAX_SEND_FILE_LEN) {
                    Toast.makeText(getBaseContext(), "文件超过28M,请选择其他文件!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("TAG", "file size:" + fileData.length);
                if (0 != msg.addElement(elem)) {
                    LogUtils.tag(tag).log("add file element error");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.tag(tag).log("ready send rich msg:" + type);
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
            //发送消息回调
            @Override
            public void onError(int code, String desc) {
                //发送消息失败
                //服务器返回了错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见附录

                LogUtils.tag(tag).log("send message failed. code: " + code + " errmsg: " + desc);
                if (code == QQIMParamsConfig.SEND_MSG_FAILED_FOR_PEER_NOT_LOGIN) {
                    desc = "对方账号不存在或未登陆过！";
                }
                final int err_code = code;
                final String str_desc = desc;
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getBaseContext(), "发送消息失败. code: " + err_code + " errmsg: " + str_desc, Toast.LENGTH_SHORT).show();
                        getMessage();
                    }
                });

            }

            @Override
            public void onSuccess(TIMMessage msg) {
                //发送消息成功
                LogUtils.tag(tag).log("SendMsg ok");
                getMessage();
            }
        });

        getMessage();
    }

    /**
     * 开始录音
     */
    private void startRecording() {
        try {
            File file = new File("record_tmp.mp3");
            if (file.exists()) {
                LogUtils.tag(tag).log("file exist");
                file.delete();
            }
            mPttFile = File.createTempFile("record_tmp", ".mp3");
            if (mRecorder == null) {
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setOutputFile(mPttFile.getAbsolutePath());
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mRecorder.setPreviewDisplay(null);
                mRecorder.prepare();
            }
            chronometer.setVisibility(View.VISIBLE);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            mPttRecordTime = System.currentTimeMillis();
            mRecorder.setOnErrorListener(new OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    stopRecording();
                    Toast.makeText(getBaseContext(), "录音发生错误:" + what, Toast.LENGTH_SHORT).show();
                }

            });
            mRecorder.start();
        } catch (IOException e) {
            LogUtils.tag(tag).log("start record error" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LogUtils.tag(tag).log("start record error2" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 停止录音
     */
    private boolean stopRecording() {
        chronometer.setVisibility(View.GONE);
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        if (mRecorder != null) {
            mRecorder.setOnErrorListener(null);
            try {
                mRecorder.stop();
            } catch (IllegalStateException e) {
                LogUtils.tag(tag).log("stop Record error:" + e.getMessage());
                mRecorder.release();
                mRecorder = null;
                return false;
            } catch (Exception e) {
                LogUtils.tag(tag).log("stop Record Exception:" + e.getMessage());
                mRecorder.release();
                mRecorder = null;
                return false;
            }
            mRecorder.release();
            mRecorder = null;
        }
        mPttRecordTime = System.currentTimeMillis() - mPttRecordTime;
        if (mPttRecordTime < QQIMParamsConfig.MIN_VOICE_RECORD_TIME) {
            Toast.makeText(this, "录音时间太短!", Toast.LENGTH_SHORT).show();
            return false;
        }
        LogUtils.tag(tag).log("time:" + SystemClock.elapsedRealtime());
        mPttRecordTime = mPttRecordTime / 1000;
        return true;
    }

    //不在当前界面，播放器释放
    @Override
    protected void onPause() {
        super.onPause();
        if (ChatMsgListAdapter.mPlayer != null) {
            ChatMsgListAdapter.mPlayer.stop();
            ChatMsgListAdapter.mPlayer.release();
            ChatMsgListAdapter.mPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        LogUtils.tag(tag).log("onDestroy:" + this.mStrPeerName);
        TIMManager.getInstance().removeMessageListener(msgListener);
        super.onDestroy();
    }


    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
