package net.kehui.www.t_907_origin.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;

import androidx.percentlayout.widget.PercentRelativeLayout;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnViewClickListener;

import net.kehui.www.t_907_origin.ConnectService;
import net.kehui.www.t_907_origin.R;
import net.kehui.www.t_907_origin.adpter.MyChartAdapterBase;
import net.kehui.www.t_907_origin.application.AppConfig;
import net.kehui.www.t_907_origin.application.Constant;
import net.kehui.www.t_907_origin.base.BaseActivity;
import net.kehui.www.t_907_origin.entity.ParamInfo;
import net.kehui.www.t_907_origin.thread.ConnectThread;
import net.kehui.www.t_907_origin.ui.MoveView;
import net.kehui.www.t_907_origin.ui.MoveWaveView;
import net.kehui.www.t_907_origin.ui.SaveRecordsDialog;
import net.kehui.www.t_907_origin.ui.ShowRecordsDialog;
import net.kehui.www.t_907_origin.ui.SparkView.SparkView;
import net.kehui.www.t_907_origin.util.StateUtils;
import net.kehui.www.t_907_origin.util.UnitUtils;

import java.text.DecimalFormat;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.icu.lang.UCharacter.JoiningGroup.MIM;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DEVICE_CONNECTED;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DEVICE_CONNECT_FAILURE;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DOWIFI_COMMAND;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DOWIFI_WAVE;
import static net.kehui.www.t_907_origin.ConnectService.INTENT_KEY_COMMAND;
import static net.kehui.www.t_907_origin.ConnectService.INTENT_KEY_WAVA;
import static net.kehui.www.t_907_origin.application.Constant.CurrentUnit;
import static net.kehui.www.t_907_origin.application.Constant.FtUnit;
import static net.kehui.www.t_907_origin.application.Constant.MiUnit;
import static net.kehui.www.t_907_origin.view.ListActivity.DISPLAY_ACTION;

public class ModeActivity extends BaseActivity {
    @BindView(R.id.tv_gain_add)
    ImageView tvGainAdd;
    @BindView(R.id.tv_gain_min)
    ImageView tvGainMin;
    @BindView(R.id.tv_distance)
    TextView tvDistance;

    @BindView(R.id.tv_pulse_width)
    ImageView tvPulseWidth;
    @BindView(R.id.tv_compare)
    ImageView tvCompare;
    @BindView(R.id.tv_cal)
    ImageView tvCal;
    @BindView(R.id.tv_range)
    ImageView tvRange;
    @BindView(R.id.tv_file)
    ImageView tvFile;
    @BindView(R.id.ll_adjust)
    LinearLayout llAdjust;
    @BindView(R.id.tv_home)
    ImageView tvHome;
    @BindView(R.id.tv_zero)
    ImageView tvZero;
    @BindView(R.id.tv_cursor_plus)
    ImageView tvCursorPlus;
    @BindView(R.id.tv_cursor_min)
    ImageView tvCursorMin;
    @BindView(R.id.tv_zoom_plus)
    ImageView tvZoomPlus;
    @BindView(R.id.tv_zoom_min)
    ImageView tvZoomMin;
    @BindView(R.id.tv_test)
    ImageView tvTest;
    @BindView(R.id.tv_help)
    ImageView tvHelp;
    @BindView(R.id.rl_feature)
    LinearLayout rlFeature;
    @BindView(R.id.fullWave)
    SparkView fullWave;
    @BindView(R.id.tv_mode)
    TextView tvMode;
    @BindView(R.id.tv_range_value)
    TextView tvRangeValue;
    @BindView(R.id.tv_gain_value)
    TextView tvGainValue;
    @BindView(R.id.tv_vop_value)
    TextView tvVopValue;
    @BindView(R.id.tv_balance_value)
    TextView tvBalanceValue;
    @BindView(R.id.tv_zoom_value)
    TextView tvZoomValue;
    @BindView(R.id.ll_info)
    PercentRelativeLayout llInfo;
    @BindView(R.id.tv_balance_plus)
    ImageView tvBalancePlus;
    @BindView(R.id.tv_balance_min)
    ImageView tvBalanceMin;

    @BindView(R.id.layout_tv_memory)
    ImageView layoutTvMemory;
    @BindView(R.id.layout_tv_both)
    ImageView layoutTvBoth;
    @BindView(R.id.ll_compare)
    LinearLayout llCompare;

    @BindView(R.id.tv_vop_plus)
    ImageView tvVopPlus;
    @BindView(R.id.tv_vop_min)
    ImageView tvVopMin;
    @BindView(R.id.tv_vop_save)
    ImageView tvSave;
    @BindView(R.id.ll_cal)
    LinearLayout llCal;

    @BindView(R.id.tv_250m)
    TextView tv250m;
    @BindView(R.id.tv_500m)
    TextView tv500m;
    @BindView(R.id.tv_1km)
    TextView tv1km;
    @BindView(R.id.tv_2km)
    TextView tv2km;
    @BindView(R.id.tv_4km)
    TextView tv4km;
    @BindView(R.id.tv_8km)
    TextView tv8km;
    @BindView(R.id.tv_16km)
    TextView tv16km;
    @BindView(R.id.tv_32km)
    TextView tv32km;
    @BindView(R.id.tv_64km)
    TextView tv64km;
    @BindView(R.id.ll_range)
    LinearLayout llRange;
    @BindView(R.id.tv_file_records)
    ImageView tvFileRecords;
    @BindView(R.id.ll_records)
    LinearLayout llRecords;
    @BindView(R.id.iv_compare_close)
    ImageView ivCompareClose;
    @BindView(R.id.iv_cal_close)
    ImageView ivGainClose;
    @BindView(R.id.iv_range_close)
    ImageView ivRangeClose;
    @BindView(R.id.iv_records_close)
    ImageView ivRecordsClose;
    @BindView(R.id.tv_decay_value)
    TextView tvDelayValue;

    @BindView(R.id.tv_delay_text)
    TextView tvDelayText;
    @BindView(R.id.tv_origin)
    ImageView tvOrigin;
    @BindView(R.id.tv_trigger_delay)
    ImageView tvTriggerDelay;
    @BindView(R.id.tv_records_save)
    ImageView tvRecordsSave;
    @BindView(R.id.tv_delay_plus)
    ImageView tvDelayPlus;
    @BindView(R.id.tv_delay_min)
    ImageView tvDelayMin;
    @BindView(R.id.ll_trigger_delay)
    LinearLayout llTriggerDelay;

    //TODO wdx 20191218 波宽度添加保存信息
    /**
     * wdx 20191218 波宽度添加保存信息
     */
    @BindView(R.id.ll_pulse_width)
    LinearLayout llPulseWidth;
    @BindView(R.id.iv_pulse_width_close)
    ImageView ivPulseWidthClose;

    @BindView(R.id.et_pulse_width_id)
    EditText etPulseWidth;
    @BindView(R.id.iv_wifi_status)
    ImageView ivWifiStatus;
    @BindView(R.id.iv_battery_status)
    ImageView ivBatteryStatus;


    /**
     * 全局的handler对象用来执行UI更新
     */
    public static final int DEVICE_CONNECTED = 1;
    public static final int DEVICE_DISCONNECTED = 2;
    public static final int SEND_SUCCESS = 3;
    public static final int SEND_ERROR = 4;
    public static final int GET_COMMAND = 5;
    public static final int GET_WAVE = 6;
    public static final int VIEW_REFRESH = 7;
    public static final int DISPLAY_DATABASE = 8;
    public static final int DEVICE_CONNECT = 9;
    public static final int DO_WAVE = 10;
    public static final int ORGNIZE_WAVE = 11;

    public static final String MODE_KEY = "mode_key";
    public static final String DATA_TRANSFER_KEY = "dataTransfer";
    //定义intent bundle的key
    public static final String BUNDLE_PARAM_KEY = "bundle_param_key";
    //定义bundle 里参数command 的key
    public static final String BUNDLE_COMMAND_KEY = "bundle_command_key";

    public Handler handler = new Handler(msg -> {
        switch (msg.what) {
            //设备连接
            case DEVICE_CONNECT:
                ivWifiStatus.setImageResource(R.drawable.ic_wifi_connected);
                break;

            case DO_WAVE:
                int[] wifiStreamNew = msg.getData().getIntArray("WAVE");
                assert wifiStream != null;
                doWifiWave(wifiStreamNew);

                break;
            case ORGNIZE_WAVE:
                if (density < densityMax) {
                    organizeNormalWaveData();

                } else {
                    organizeWaveData();
                }
                break;
            //显示波形
            case VIEW_REFRESH:
                resetWhatNeed();
                //Log.e("【时效测试】", "开始组织波形数据");

                if (density < densityMax) {
                    //setDensity(density);
                    organizeNormalWaveData();
                    // Log.e("【时效测试】", "组织波形数据完毕");
                    displayWave();
                } else {
                    organizeWaveData();
                    // Log.e("【时效测试】", "组织波形数据完毕");
                    displayWave();
                }

                //TODO 20200407 波形绘制完毕，恢复测试按钮可用性，允许请求电量
                Constant.isTesting = false;
                ConnectService.canAskPower = true;
                AllowSetRange = true;
                tvTest.setEnabled(true);

                Log.e("【请求电量时机控制】", "波形绘制完毕，允许请求电量。");

                //Log.e("【时效测试】", "绘制完毕");

                break;
            //显示本地存储的数据
            case DISPLAY_DATABASE:
                //数据库波形显示   //GC20190713
                setDateBaseParameter();
                try {
                    organizeWaveData();
                    displayWave();
                } catch (Exception l_ex) {
                }
                break;
            default:
                break;
        }
        return false;
    });
    private boolean AlreadDisplayWave;
    private boolean AllowSetRange = true;


    /**
     * 相同范围下点测试获取新波形数据
     */
    private void organizeNormalWaveData() {

        for (int i = currentMoverPosition510 * dataLength / 510, j = 0; j < 510; i = i + density, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            waveDraw[j] = Constant.WaveData[i + index];
            //组织SIM的第二条波形的数据
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
    }

    @BindView(R.id.tv_balance_text)
    TextView tvBalanceText;
    @BindView(R.id.tv_balance_space)
    TextView tvBalanceSpace;
    @BindView(R.id.iv_close_trigger_delay)
    ImageView ivCloseTriggerDelay;
    @BindView(R.id.tv_delay_space)
    TextView tvDelaySpace;
    @BindView(R.id.tv_wave_text)
    TextView tvWaveText;
    @BindView(R.id.tv_wave_value)
    TextView tvWaveValue;
    @BindView(R.id.tv_wave_space)
    TextView tvWaveSpace;
    @BindView(R.id.view_move_vertical_wave)
    MoveWaveView viewMoveVerticalWave;
    @BindView(R.id.tv_wave_pre)
    ImageView tvWavePre;
    @BindView(R.id.tv_wave_next)
    ImageView tvWaveNext;
    @BindView(R.id.tv_icm)
    TextView tvIcm;
    @BindView(R.id.tv_distance_unit)
    TextView tvDistanceUnit;
    @BindView(R.id.rl_wave)
    RelativeLayout rlWave;
    @BindView(R.id.tv_vop_unit)
    TextView tvVopUnit;
    @BindView(R.id.mv_wave)
    MoveView mvWave;
    @BindView(R.id.tv_cable_vop_unit)
    TextView tvCableVopUnit;
    @BindView(R.id.tv_pulse_width_save)
    ImageView tvPulseWidthSave;
    @BindView(R.id.ll_horizontal_view)
    LinearLayout llHorizontalView;
    //设置是否需要进入页面接收数据，此处是为了适配从主页面展示波形时重复接收数据
    private boolean isReceiveData = true;
    //判断读取本地数据，网络连接后是否已经发送了初始化命令
    private boolean readLocalDataSendCmd = false;
    private int localRangeCommend;

    private int index;
    private int modeIntent;
    //计算滑动时的基数
    private int fenzi1;
    //初始化滑块位置
    private int fenzi2;

    private int CurrentActionDownValue = 0;


    public boolean getReceiveData() {
        return isReceiveData;
    }

    public void setReceiveData(boolean receiveData) {
        isReceiveData = receiveData;
    }

    /**
     * 初始化界面框架
     */
    public void initFrame() {
        Constant.ModeValue = TDR;
        //Constant.RangeValue = 0x11;
        //setRange(0x11);
        tvGainValue.setText(String.valueOf(gain));
        Constant.Gain = gain;
        velocity = getLocalValue();
        setVelocity(velocity);
        Constant.Velocity = velocity;
        tvBalanceValue.setText(String.valueOf(balance));
        tvZoomValue.setText("1 : " + density);
        tvDelayValue.setText(delay + "μs");
        //初始化距离显示
        calculateDistance(Math.abs(pointDistance - zero));
        //SIM光标位置初始化    //GC20190712
        simZero = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_CURSOR_POSITION, 12);

    }

    //TODO wdx 20191218 波宽度属性值初始化

    /**
     * 波宽度属性值初始化
     */
    private void initPulseWidth() {


        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PULSE_WIDTH_INFO_KEY);
        if (paramInfo != null) {
            pulseWidth = paramInfo.getPulseWidth();
            etPulseWidth.setText(String.valueOf(pulseWidth));
        }
        etPulseWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int pulsewidth = Integer.parseInt(s.toString());
                    int maxPulsewidth = 7000;
                    if (pulsewidth > maxPulsewidth) {
                        Toast.makeText(ModeActivity.this, getResources().getString(R.string
                                .maxpulsewidth), Toast.LENGTH_SHORT).show();
                        etPulseWidth.setText(maxPulsewidth + "");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //英尺状态下范围按钮文字初始化
    private void initBtnRangeView() {
        if (Constant.CurrentUnit == Constant.FtUnit) {
            tv250m.setText(getResources().getString(R.string.btn_250m_to_ft));
            tv500m.setText(getResources().getString(R.string.btn_500m_to_ft));
            tv1km.setText(getResources().getString(R.string.btn_1km_to_yingli));
            tv2km.setText(getResources().getString(R.string.btn_2km_to_yingli));
            tv4km.setText(getResources().getString(R.string.btn_4km_to_yingli));
            tv8km.setText(getResources().getString(R.string.btn_8km_to_yingli));
            tv16km.setText(getResources().getString(R.string.btn_16km_to_yingli));
            tv32km.setText(getResources().getString(R.string.btn_32km_to_yingli));
            tv64km.setText(getResources().getString(R.string.btn_64km_to_yingli));
        }
    }

    //读取主页设置中存储的范围数据，并初始化
    private void initRange() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        double localRange = 500;
        if (paramInfo != null && paramInfo.getCableLength() != null && !TextUtils.isEmpty(paramInfo.getCableLength())) {
            localRange = Double.valueOf(paramInfo.getCableLength());
            //如果数据库里存的是英尺值则转换为米
            if (Constant.CurrentSaveUnit == FtUnit)
                localRange = Double.valueOf(UnitUtils.ftToMi(localRange));

            if (localRange == 0.0 || localRange == 0) {
                range = (0x11);
                gain = 13;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
            } else if (localRange <= 250) {
                range = (0x99);
                gain = 13;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
            } else if (localRange > 250 && localRange <= 500) {
                range = (0x11);
                gain = 13;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
            } else if (localRange > 500 && localRange <= 1000) {
                range = (0x22);
                gain = 13;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
            } else if (localRange > 1000 && localRange <= 2000) {
                range = (0x33);
                gain = 10;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
            } else if (localRange > 2000 && localRange <= 4000) {
                range = (0x44);
                gain = 10;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
            } else if (localRange > 4000 && localRange <= 8000) {
                range = (0x55);
                gain = 10;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
            } else if (localRange > 8000 && localRange <= 16000) {
                range = (0x66);
                gain = 9;
                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
            } else if (localRange > 16000 && localRange <= 32000) {
                range = (0x77);
                gain = 9;

                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
            } else if (localRange > 32000) {
                range = (0x88);
                gain = 9;

                if (Constant.CurrentUnit == Constant.FtUnit)
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                else
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
            }
        } else {
            range = (0x11);
            if (Constant.CurrentUnit == Constant.FtUnit)
                tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
            else
                tvRangeValue.setText(getResources().getString(R.string.btn_500m));
        }
        Constant.RangeValue = range;


    }

    //读取本地存储的波速度信息，默认为172，计算距离时要求是米单位，所以如果数据库里存的是英尺数，应该转换为米数。
    private double getLocalValue() {
        double vopValue = 172;
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        if (paramInfo != null) {
            if (paramInfo.getCableVop() != null && !TextUtils.isEmpty(paramInfo.getCableVop())) {
                if (Constant.CurrentSaveUnit == MiUnit)
                    vopValue = Double.valueOf(paramInfo.getCableVop());
                else
                    vopValue = Double.valueOf(UnitUtils.ftToMi(Double.valueOf(paramInfo.getCableVop())));
            }
        }
        if (vopValue == 0 || vopValue == 0.0)
            vopValue = 172;
        return vopValue;
    }


    /**
     * 处理APP接收的命令
     */
    private void doWifiCommand(int[] wifiArray) {


        //仪器触发时：APP发送接收数据命令
        if ((wifiArray[5] == COMMAND_TRIGGER) && (wifiArray[6] == TRIGGERED)) {
            command = COMMAND_RECEIVE_WAVE;
            dataTransfer = mode;
            //发送指令
            startService();
            // Log.e("【时效测试】", "发送接收波形数据命令");
            ConnectService.canAskPower = false;
            if (tDialog != null) {
                //重新进入时不需要再发取消测试命令
                Constant.isCancelAim = true;
                tDialog.dismiss();
            }

            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.receiving_data)
                    .setScreenWidthAspect(this, 0.25f)
                    .setCancelableOutside(false)
                    .create()
                    .show();
            Log.e("DIA", " 正在接受数据显示" + " ICM/SIM/DECAY");
        } else if (wifiArray[5] == POWERDISPLAY) {
            int batteryValue = wifiArray[6] * 256 + wifiArray[7];
            if (batteryValue <= 2600) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_zero);

            } else if (batteryValue > 2600 && batteryValue <= 2818) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_one);

            } else if (batteryValue > 2818 && batteryValue <= 3018) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_two);

            } else if (batteryValue > 3018 && batteryValue <= 3220) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_three);

            } else if (batteryValue > 3220) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_four);

            }

        }
        //TODO 20200407 普通命令解析完毕，允许请求电量
        if (!Constant.isTesting) {
            ConnectService.canAskPower = true;
            Log.e("【请求电量时机控制】", "触发和电量命令数据处理完毕，允许请求电量。");
        }


    }


    private TDialog tDialog;
    /**
     * 监听网络广播，匹配SSID后开启线程
     * IF190305
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handler.sendEmptyMessage(intent.getIntExtra("display_action", 0));

            String action = intent.getAction();
            assert action != null;
            if (action.equals(BROADCAST_ACTION_DOWIFI_COMMAND)) {
                wifiStream = intent.getIntArrayExtra(INTENT_KEY_COMMAND);
                assert wifiStream != null;
                doWifiCommand(wifiStream);
            } else if (action.equals(BROADCAST_ACTION_DOWIFI_WAVE)) {

                /*wifiStream = intent.getIntArrayExtra(INTENT_KEY_WAVA);
                if (intent.getIntArrayExtra(INTENT_KEY_WAVA) == null) {
                    wifiStream = ConnectService.mExtra;
                }*/
                //64公里波形数据过大，正常的广播无法传递，改成全局变量。
                //int[] wifiStreamNew = ConnectService.mExtra;
                //wifiStream = ConnectService.mExtra;
                //assert wifiStreamNew != null;
                int[] wifiStreamNew = intent.getIntArrayExtra(INTENT_KEY_WAVA);
                if (wifiStreamNew[3] == WAVE_SIM || wifiStreamNew[3] == WAVE_TDR_ICM_DECAY)
                    setWaveParameter();

                Message message = Message.obtain();
                message.what = ModeActivity.DO_WAVE;
                Bundle bundle = new Bundle();
                bundle.putIntArray("WAVE", wifiStreamNew);
                message.setData(bundle);
                handler.sendMessage(message);
            } else if (action.equals(BROADCAST_ACTION_DEVICE_CONNECTED)) {
                Log.e("ModeActivity", "连接成功");
                ivWifiStatus.setImageResource(R.drawable.ic_wifi_connected);

                //如果网络连接后于读取本地波形数据，则再网络连接时设置读出的几个参数。
                if (isReceiveData == false || isDatabase == true) {
                    setModeNoCmd(Constant.Para[0]);
                    setRangeNoCmd(Constant.Para[1]);
                    setGain(Constant.Para[2]);
                    setVelocityNoCmd(Constant.Para[3]);
                } else {

                    //取消测试中状态
                    //已显示波形状态恢复为true
                    Constant.isTesting = false;
                    AllowSetRange = true;
                    AlreadDisplayWave = false;

                    //第一次连接设备初始化方式
                    //模式
                    setMode(mode);
                    handler.postDelayed(() -> {
                        //范围
                        setRange(range);
                    }, 20);
                    handler.postDelayed(() -> {
                        //增益
                        setGain(gain);
                    }, 20);
                    handler.postDelayed(() -> {
                        //脉宽0
                        setPulseWidth(pulseWidth);
                    }, 20);

                    handler.postDelayed(() -> {
                        //脉宽0
                        setDelay(0);
                    }, 20);

                  /*  //范围
                    handler.postDelayed(() -> {
                        setRange(range);
                    }, 20);*/

                    //clickTest();
                    //延时100毫秒发送测试命令，100毫秒是等待设备回复命令信息，如果不延时，有可能设备执行不完命令。
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                }
            } else if (action.equals(BROADCAST_ACTION_DEVICE_CONNECT_FAILURE)) {
                ivWifiStatus.setImageResource(R.drawable.ic_no_wifi_connect);
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_no);
                ConnectService.isConnected = false;
            }

        }

    };
    private SaveRecordsDialog saveRecordsDialog;
    private ShowRecordsDialog showRecordsDialog;

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        AlreadDisplayWave = false;

        modeIntent = getIntent().getIntExtra(MODE_KEY, 0);
        mode = getIntent().getIntExtra(MODE_KEY, 0);
        isReceiveData = getIntent().getBooleanExtra("isReceiveData", true);

        ButterKnife.bind(this);
        initUnit();
        initSparkView();
        initViewMoveWave();
        initBtnRangeView();
        //20191217将获取设置中范围的函数调整到上方，用于读取设置中的位置
        initRange();
        initFrame();
        setChartListener();
        initMode();

        initBroadcastReceiver();

        //从主页中显示的本地数据，发送广播处理。
        if (getIntent().getIntExtra("display_action", 0) == DISPLAY_DATABASE) {
            isReceiveData = false;
            Intent intent = new Intent(DISPLAY_ACTION);
            intent.putExtra("display_action", ModeActivity.DISPLAY_DATABASE);
            sendBroadcast(intent);
        }

        //TODO wdx 20191218 波宽度属性值初始化
        /**
         *  波宽度属性值初始化
         */
        //读取上次保存的脉宽。
        initPulseWidth();


    }

    private void initUnit() {
        Constant.CurrentUnit = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_UNIT, Constant.MiUnit);
        Constant.CurrentSaveUnit = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_SAVE_UNIT, Constant.MiUnit);
        if (Constant.CurrentUnit == Constant.MiUnit) {
            tvDistanceUnit.setText(R.string.mi);
            tvVopUnit.setText(R.string.mius);
        } else {
            tvDistanceUnit.setText(R.string.ft);
            tvVopUnit.setText(R.string.ftus);
        }
    }

    int moverMoveValue = 0;

    //当前滑块左侧在510个点的数值
    int currentMoverPosition510 = 0;

    //初始化监听事件
    private void initViewMoveWave() {


        viewMoveVerticalWave.setViewMoveWaveListener(new MoveWaveView.ViewMoveWaveListener() {
            @Override
            public void onMoved(float x, float y) {

                //上下移动波形
                fullWave.setSparkViewMove(y);
                Log.e("ModeActivity", y + "");

            }

            @Override
            public void onMoveEnded() {

            }
        });
        fenzi1 = (dataLength / (densityMax / density)) - 510;

//        mvWave.setViewMoveSizeChangedListener(new MoveView.ViewMoveSizeChangedListener() {
//            @Override
//            public void onSizeChanged() {
//                fenzi2 = positionVirtual * mvWave.getParentWidth() / 510;
//                //移动滑块位置
//                if (fenzi1 != 0) {
//                    setHorizontalMoveViewPosition(positionVirtual * mvWave.getParentWidth() / fenzi1);
//                }
//            }
//        });

        mvWave.setViewMoveWaveListener(new MoveView.ViewMoveWaveListener() {

            @Override
            public void onMoved(float x) {

                //int moveRange = mvWave.getParentWidth() - mvWave.getWidth();
                //int position = (int) (fenzi1 * x / moveRange);
                Log.e("【滑块】", "当前X：" + x + "/长度：" + mvWave.getParentWidth() + "/510Value:" + get510Value(x, mvWave.getParentWidth()));

                //滑块X在510个点中的位置
                int currentMoverX = get510Value(x, mvWave.getParentWidth());
                //计算滑块移动的数值，在波形框宽度510中的对应数值
                moverMoveValue = currentMoverX - currentMoverPosition510;
                //因当前波形是缩放时从某个点开始取的，因此滑块左侧的位置就是重新抽点的起始点，按照比例可以算出来
                organizeMoveWaveData(currentMoverX * dataLength / 510);
                //比对波形
                try {
                    organizeCompareWaveData(currentMoverX * dataLength / 510);
                } catch (Exception l_Ex) {
                }
                Log.e("【滑块】", "currentMoverX:" + currentMoverX + "moverMoveValue：" + moverMoveValue);
                //重新绘制波形
                displayWave();
/*                int movePositionReal = (zero - (currentMoverX * dataLength / 510)) / density;
                int movePositionVirtual = (pointDistance - (currentMoverX * dataLength / 510)) / density;
                //int movePositionReal = (510 * (zero - (currentMoverX * dataLength / 510)) / (dataLength - (currentMoverX * dataLength / 510))) / density;
                //int movePositionVirtual = (510 * (pointDistance - (currentMoverX * dataLength / 510)) / (dataLength - (currentMoverX * dataLength / 510))) / density;
                if (movePositionReal >= 0)
                    fullWave.setScrubLineReal(movePositionReal);
                else
                    fullWave.setScrubLineRealDisappear();

                if (movePositionVirtual >= 0)
                    fullWave.setScrubLineVirtual(movePositionVirtual);
                else
                    fullWave.setScrubLineVirtualDisappear();*/


                //滑块移动时变换虚光标位置
                positionVirtual = positionVirtual - moverMoveValue * (densityMax / density);
                //滑块移动时变换实光标位置
                positionReal = positionReal - moverMoveValue * (densityMax / density);

                //重新设置当前的滑块左侧位置数值

                //重新定位实光标
                if (positionReal > 0 && positionReal <= 510)
                    fullWave.setScrubLineReal(positionReal);
                else {
                    fullWave.setScrubLineRealDisappear();
                }


                //重新定位虚光标
                if (positionVirtual >= 0 && positionVirtual <= 510)
                    fullWave.setScrubLineVirtual(positionVirtual);
                else {
                    fullWave.setScrubLineVirtualDisappear();
                }

                //如果零点为0，而且当前绘制波形的起点也是0，说明波形在最左侧，需要在0位置显示实光标。
                if (zero == 0 & currentMoverPosition510 == 0)
                    fullWave.setScrubLineReal(0);

                //重新赋值当前波形的绘制起始位置
                currentMoverPosition510 = currentMoverX;
                if (zero == 0 & currentMoverPosition510 == 0)
                    fullWave.setScrubLineReal(0);
                Log.e("【光标参数】", "positionReal：" + positionReal + "/zero:" + zero + "/positionVirtual:" + positionVirtual + "/pointDistance:" + pointDistance + "/MoverPosition:" + (currentMoverX * dataLength / 510));

            }
        });

    }

    //缩放时重新组织记忆的波形数据
    private void organizeCompareWaveData(int start) {

        //按比例抽出510个点
        for (int i = start, j = 0; j < 510; i = i + density, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            waveCompareDraw[j] = waveCompare[i];
        }

        int[] waveDraw250 = new int[255];
        int[] waveCompare250 = new int[255];
        int[] simDraw2501 = new int[255];
        int[] simDraw2502 = new int[255];
        int[] simDraw2503 = new int[255];
        int[] simDraw2504 = new int[255];
        int[] simDraw2505 = new int[255];
        int[] simDraw2506 = new int[255];
        int[] simDraw2507 = new int[255];
        int[] simDraw2508 = new int[255];

        //需要操作的数据长度
        if ((mode == TDR) || (mode == SIM)) {
            //GC20191223
            if (range == RANGE_250) {
                //取出前255个点数
                for (int i = 0, j = 0; i < 255; i++, j++) {
                    waveDraw250[j] = waveCompareDraw[i];
                }
                //算出差值的255个点
                for (int i = 0, j = 1; i < 254; i++, j += 2) {
                    waveCompareDraw[j] = waveDraw250[i] + (waveDraw250[i + 1] - waveDraw250[i]) / 2;
                }
                //将原始255个点分散
                for (int i = 0, j = 0; j < 255; i++, j++) {
                    waveCompareDraw[2 * j] = waveDraw250[i];
                }
            }

        }


    }

    //滑块移动过程中，重新组织需要绘制的波形数据
    private void organizeMoveWaveData(int start) {

        //按比例抽出510个点
        for (int i = start, j = 0; j < 510; i = i + density, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            if (i < Constant.WaveData.length)
                waveDraw[j] = Constant.WaveData[i];
            //组织SIM的第二条波形的数据
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
    }

    /**
     * 根据滑块位置获取在波形框中的位置
     *
     * @param x     滑块左侧在滑条的位置，单位为像素
     * @param right 滑条长度
     * @return 转换好的值
     */
    private int get510Value(float x, float right) {
        return (int) (510 * x / right);
    }


    //初始化模式
    private void initMode() {
        switch (mode) {
            case TDR:
                initTDRView();
                break;
            case ICM:
                initICMSURGEView();
                break;
            case ICM_DECAY:

                initICMDECAYView();
                break;
            case SIM:
                initSIMView();
                break;
            case DECAY:
                initDecayView();
                break;
        }
    }

    private void initDecayView() {
        tvMode.setText(getResources().getText(R.string.btn_decay));
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    //即MIM
    private void initSIMView() {
        tvMode.setText(getResources().getText(R.string.btn_sim));
        viewMoveVerticalWave.setVisibility(View.VISIBLE);
        tvWaveNext.setVisibility(View.VISIBLE);
        tvWavePre.setVisibility(View.VISIBLE);

        tvBalanceMin.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvCompare.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.VISIBLE);
        tvDelayText.setVisibility(View.VISIBLE);
        tvPulseWidth.setVisibility(View.GONE);
    }

    private void initICMDECAYView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);

        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_icm_decay));
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
        //TODO 20191226 为什么又改成了iCM?二话不说先屏蔽再说
        //mode = ICM;
    }

    private void initICMSURGEView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_icm));
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    private void initTDRView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_tdr));
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    /**
     * 初始化wifi监听广播
     */
    private void initBroadcastReceiver() {
        IntentFilter ifDisplay = new IntentFilter();
        ifDisplay.addAction(DISPLAY_ACTION);
        ifDisplay.addAction(BROADCAST_ACTION_DOWIFI_COMMAND);
        ifDisplay.addAction(BROADCAST_ACTION_DEVICE_CONNECT_FAILURE);
        ifDisplay.addAction(BROADCAST_ACTION_DOWIFI_WAVE);
        ifDisplay.addAction(BROADCAST_ACTION_DEVICE_CONNECTED);
        registerReceiver(receiver, ifDisplay);
        //如果跳转之前在MainActivity就已经连接设备需要重新发送连接成功广播
        /*if (ConnectService.isConnected && isReceiveData) {
            Intent intent = new Intent(BROADCAST_ACTION_DEVICE_CONNECTED);
            sendBroadcast(intent);
        }*/
        //TODO 20191222 修改为接收数据和读取本地数据都重发广播
        if (ConnectService.isConnected) {
            Intent intent = new Intent(BROADCAST_ACTION_DEVICE_CONNECTED);
            sendBroadcast(intent);

        }
    }

    /**
     * 初始化sparkView //GC20181227
     */
    public void initSparkView() {
        for (int i = 0; i < 510; i++) {
            waveArray[i] = 128;
            //Constant.WaveData[i] = 128;
        }
        myChartAdapterMainWave = new MyChartAdapterBase(waveArray, null,
                false, 0, false, dataMax);
        myChartAdapterFullWave = new MyChartAdapterBase(waveArray, null,
                false, 0, false, dataMax);
        fullWave.setAdapter(myChartAdapterMainWave);
        setMoveView();
//        fullWave.setAdapter(myChartAdapterFullWave);
        Log.i("Draw", "初始化绘制结束");
        //初始化光标按钮颜色
//        btnCursor.setTextColor(ContextCompat.getColor(ModeActivity.this, R.color.T_purple));
    }

    /**
     * 监听虚光标位置变化
     */

    int currentWaveXValue = 0;

    private void setChartListener() {


        //波形下发区域拿出，做波形的左右滑动
        fullWave.setScrubListener(new SparkView.OnScrubListener() {
            @Override
            public void onActionDown(Object x, float y) {
                Log.e("【ActionDown】", "ActionDown:" + x + "/Y:" + y);
                //手指按下记录位置
                CurrentActionDownValue = (Integer) x;
            }

            @Override
            public void onScrubbed(Object value, float y) {
                //手触摸区域判断，值越小，波形滑动区域越大
                if (y < 400) {

                    if ((Integer) value <= 510) {
                        //画虚光标
                        fullWave.setScrubLineVirtual((Integer) value);
                        //虚光标移动的点数  //GC20191218
                        cusorMoveValue = (int) value - positionVirtual;
                        Log.e("【虚光标】", "滑动开始：value:" + value + "/positionVirtual:" + positionVirtual + "/cusorMoveValue:" + cusorMoveValue + "/pointDistance" + pointDistance + "/zero:" + zero); //GN 数值变化0-509
                        //在原始数据中的位置
                        pointDistance = pointDistance + cusorMoveValue * density;
                        Log.e("【虚光标】", "滑动中：value:" + value + "/positionVirtual:" + positionVirtual + "/cusorMoveValue:" + cusorMoveValue + "/pointDistance" + pointDistance + "/zero:" + zero); //GN 数值变化0-509
                        //记忆移动后虚光标在画布中的位置
                        positionVirtual = (int) value;
                        int WaveDataStart = currentMoverPosition510 * dataLength / 510;
                        Log.e("【虚光标】", "滑动结束：value:" + value + "/positionVirtual:" + positionVirtual + "/cusorMoveValue:" + cusorMoveValue + "/pointDistance" + pointDistance + "/zero:" + zero + "/WaveDataStart:" + WaveDataStart); //GN 数值变化0-509


                        if (positionVirtual == 0 && zero == 0 && WaveDataStart < 510)
                            pointDistance = 0;
                        //距离显示
                        calculateDistance(Math.abs(pointDistance - zero));
                    }
                } else {
                    Log.e("【新滑块】", "value：" + value);
                    Log.e("【新滑块】", "CurrentActionDownValue：" + CurrentActionDownValue);
                    //计算手指滑动的距离
                    moverMoveValue = (Integer) value - CurrentActionDownValue;
                    Log.e("【新滑块】", "moverMoveValue：" + moverMoveValue);
                    Log.e("【新滑块】", "前currentMoverPosition510：" + currentMoverPosition510);

                    //滑块X位置
                    currentMoverPosition510 = currentMoverPosition510 + moverMoveValue;


                    //滑块x位置大于等于0且滑块X位置+滑块宽度不超过波形区域，则允许波形滑动。
                    if (currentMoverPosition510 >= 0 && (currentMoverPosition510 + (mvWave.getWidth() * 510 / mvWave.getParentWidth())) <= 510 && density != densityMax) {
                        Log.e("【新滑块】", "后currentMoverPosition510：" + currentMoverPosition510);

                        //因当前波形是缩放时从某个点开始取的，因此滑块左侧的位置就是重新抽点的起始点，按照比例可以算出来
                        organizeMoveWaveData(currentMoverPosition510 * dataLength / 510);
                        //比对波形
                        try {
                            organizeCompareWaveData(currentMoverPosition510 * dataLength / 510);
                        } catch (Exception l_Ex) {
                        }
                        Log.e("【滑块】", "currentMoverX:" + currentMoverPosition510 + "moverMoveValue：" + moverMoveValue);
                        //重新绘制波形
                        displayWave();
                        //滑块移动时变换虚光标位置
                        positionVirtual = positionVirtual - moverMoveValue * (densityMax / density);
                        //滑块移动时变换实光标位置
                        positionReal = positionReal - moverMoveValue * (densityMax / density);

                        //重新设置当前的滑块左侧位置数值

                        //重新定位实光标
                        if (positionReal > 0 && positionReal <= 510)
                            fullWave.setScrubLineReal(positionReal);
                        else {
                            fullWave.setScrubLineRealDisappear();
                        }


                        //重新定位虚光标
                        if (positionVirtual >= 0 && positionVirtual <= 510)
                            fullWave.setScrubLineVirtual(positionVirtual);
                        else {
                            fullWave.setScrubLineVirtualDisappear();
                        }

                        if (zero == 0 & currentMoverPosition510 == 0)
                            fullWave.setScrubLineReal(0);

                        //手指拖动时关联移动滑块到指定位置
                        int moverPositon;
                        moverPositon = mvWave.getParentWidth() * currentMoverPosition510 / 510;
                        setMoverPostion(moverPositon);
                    } else
                        currentMoverPosition510 = currentMoverPosition510 - moverMoveValue;

                    CurrentActionDownValue = (Integer) value;
                }

            }
        });
       /* fullWave.setScrubListener(value -> {


        });*/
    }

    /**
     * 计算距离  //GC20190709
     */
    private void calculateDistance(int cursorDistance) {
        double distance;
        int k;
        //脉冲电流方式下range=6(32km)和range=7(64km)实时25M采样率，其余方式和范围实时100M采样率，此时相对其它方式采样周期扩大4倍
        if ((mode == ICM || mode == ICM_DECAY) && (rangeState >= 7)) {
            k = 4;
        } else {
            k = 1;
        }

        //DECAY方式距离/2
        if (mode == DECAY) {
            distance = (((double) cursorDistance * velocity / 2) * k) / 2 * 0.01;
        } else if ((mode == TDR) || (mode == SIM)) {
            //GC20191226
            //distance = (((double) cursorDistance * velocity) * k) / 2 * 0.01;

            //暂时屏蔽掉250的处理
            if (range == RANGE_250) {
                distance = (((double) cursorDistance * velocity / 2) * k) / 2 * 0.01;
            } else {
                distance = (((double) cursorDistance * velocity) * k) / 2 * 0.01;
            }
        } else {
            distance = (((double) cursorDistance * velocity) * k) / 2 * 0.01;
        }

        //TODO 2019-1223-0947 显示的距离也存下来，保存波形的时候使用
        Constant.CurrentLocation = distance;

        if (Constant.CurrentUnit == Constant.MiUnit) {
            tvDistance.setText(new DecimalFormat("0.00").format(distance));
        } else {
            tvDistance.setText(UnitUtils.miToFt(distance));
        }
        //距离界面显示

    }

    public double getVelocity() {
        return velocity;
    }

    /**
     * @param delay 需要发送的延时控制命令值 / 响应信息栏延时变化
     */
    public void setDelay(int delay) {
        this.delay = delay;
        command = COMMAND_DELAY;
        dataTransfer = delay;
        tvDelayValue.setText(delay + "μs");
        //发送指令
        startService();
    }

    public int getDelay() {
        return delay;
    }

    /**
     * @param density 响应状态栏波速度变化
     */
    public void setDensity(int density) {
        this.density = density;
        tvZoomValue.setText("1 : " + density);
        Log.e("GT", "density：" + density);
        //GC20191219
        organizeZoomWaveData();
        displayWave();
    }

    public int getDensity() {
        return density;
    }

    /**
     * 组织需要绘制的放大缩小波形数组（抽点510个）  //GC20191219
     */
    public int currentStart = 0;


    //缩放时重新组织绘制的波形数据，重新绘制虚实光标。
    private void organizeZoomWaveData() {

        //抽点的起始位置和虚光标位置
        currentStart = 0;
        //Log.e("【滑块相关】-开始处理缩放数据前", "densityMax:" + densityMax + "/density:" + density + "/pointDistance:" + pointDistance + "/positionVirtual" + positionVirtual + "/positionReal:" + positionReal + "/zero:" + zero + "/datalength:" + dataLength + "/start:" + currentStart);
        if (pointDistance < 255 * density) {                                      //波形靠最右侧
            positionVirtual = pointDistance / density;
            Log.e("GT", "1：start：" + currentStart);
            //根据起始点判断是否画实光标
            if (zero > 510 * density) {
                //不画实光标
                positionReal = zero / density;

                fullWave.setScrubLineRealDisappear();
            } else {
                //画实光标
                positionReal = zero / density;
                Log.e("GT", "1：positionReal：" + positionReal);
                fullWave.setScrubLineReal(positionReal);
            }

        } else if ((pointDistance >= 255 * density) && (pointDistance < dataLength - 255 * density)) {    //波形靠以虚光标原始位置为中心
            Log.e("GT", "2：dataLength：" + dataLength);
            Log.e("GT", "2：pointDistance：" + pointDistance);
            currentStart = pointDistance - 255 * density;
            positionVirtual = 255;
            Log.e("GT", "2：start：" + currentStart);
            //根据虚光标位置判断是否画实光标
            if ((pointDistance - zero) > 255 * density) {
                positionReal = positionVirtual - (pointDistance - zero) / density;

                //不画实光标
                fullWave.setScrubLineRealDisappear();
            } else if ((zero - pointDistance) >= 254 * density) {
                positionReal = positionVirtual - (pointDistance - zero) / density;

                //不画实光标
                fullWave.setScrubLineRealDisappear();
            } else {
                //画实光标
                positionReal = positionVirtual - (pointDistance - zero) / density;
                Log.e("GT", "2：positionReal：" + positionReal);
                fullWave.setScrubLineReal(positionReal);
            }

        } else if (pointDistance >= dataLength - 255 * density) {                             //波形靠最左侧
            currentStart = dataLength - 510 * density;
            positionVirtual = 510 - (dataLength - pointDistance) / density;
            Log.e("GT", "3：positionVirtual：" + positionVirtual);
            Log.e("GT", "3：start：" + currentStart);
            //根据起始点判断是否画实光标
            if (zero > dataLength - 510 * density) {
                //画实光标
                //positionReal = zero / density;
                positionReal = 510 - (dataLength - zero) / density;

                Log.e("GT", "3：positionReal：" + positionReal);
                fullWave.setScrubLineReal(positionReal);
            } else {
                //不画实光标
                positionReal = 510 - (dataLength - zero) / density;

                fullWave.setScrubLineRealDisappear();
            }
        }
        //还原状态
        if (density == densityMax) {
            currentStart = 0;
            positionVirtual = pointDistance / densityMax;
            positionReal = zero / densityMax;
            //画实光标
            fullWave.setScrubLineReal(positionReal);
            Log.e("GT", "4：还原");
        }
        //画虚光标
        fullWave.setScrubLineVirtual(positionVirtual);

        //Log.e("【滑块相关】-开始处理缩放数据后", "densityMax:" + densityMax + "/density:" + density + "/pointDistance:" + pointDistance + "/positionVirtual" + positionVirtual + "/positionReal:" + positionReal + "/zero:" + zero + "/datalength:" + dataLength + "/start:" + currentStart);
        //按比例抽出510个点
        for (int i = currentStart, j = 0; j < 510; i = i + density, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            waveDraw[j] = Constant.WaveData[i + index];
            //组织SIM的第二条波形的数据
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
        //组织比对波形数据
        try {
            organizeCompareWaveData(currentStart);
        } catch (Exception l_ex) {
        }
        //设置水平滑块左侧位置
        int moverXValue = 0;
        //转换为在波形框中的对应数值
        moverXValue = currentStart * 510 / dataLength;
        currentMoverPosition510 = moverXValue;
        //Log.e("【滑块】", "" + moverXValue);

        //转换为屏幕坐标
        moverXValue = mvWave.getParentWidth() * moverXValue / 510;
        //重新设置滑块大小
        setHorizontalMoveView();
        //移动滑块到指定位置
        setMoverPostion(moverXValue);

        // Log.e("【光标参数】", "positionReal：" + positionReal + "/zero:" + zero + "/positionVirtual:" + positionVirtual + "/pointDistance:" + pointDistance);

    }

    /**
     * 在sparkView界面显示波形
     */
    private void displayWave() {
        //画波形
        myChartAdapterMainWave.setmTempArray(waveDraw);
        myChartAdapterFullWave.setmTempArray(waveDrawFull);
        myChartAdapterMainWave.setShowCompareLine(isCom);
        myChartAdapterFullWave.setShowCompareLine(isCom);
        if (mode == SIM) {
            if (isCom) {
                myChartAdapterMainWave.setmCompareArray(waveCompare);
                myChartAdapterFullWave.setmCompareArray(waveCompareFull);

            }
        }
        myChartAdapterMainWave.notifyDataSetChanged();
        myChartAdapterFullWave.notifyDataSetChanged();
        //有对话框消对话框
        if (tDialog != null) {
            tDialog.dismiss();
            Log.e("DIA", "正在接受数据隐藏" + " 波形绘制完成");
        }
        //刷新波形后后显示控制虚光标    //GC20190629
        cursorState = false;
        myChartAdapterMainWave.setCursorState(false);
//        btnCursor.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.T_purple));

        tvZoomMin.setEnabled(true);
        tvZoomPlus.setEnabled(true);

        AlreadDisplayWave = true;

        //TODO 20200401 MIM模式下，读取数据库时，禁用上翻下翻
        if (isDatabase) {
            tvWaveNext.setEnabled(false);
            tvWavePre.setEnabled(false);
        }
    }

    //设置滑块参数
    private void setMoveView() {
        RectF sparkViewRectf = myChartAdapterMainWave.getDataBounds();
        viewMoveVerticalWave.setSparkViewRect(sparkViewRectf);
        //水平滑动view传递父view
        mvWave.setParentView(llHorizontalView);

    }


    /**
     * @param balance 需要发送的平衡控制命令值 / 响应信息栏平衡变化
     */
    public void setBalance(int balance) {
        this.balance = balance;
        command = COMMAND_BALANCE;
        dataTransfer = balance;
        tvBalanceValue.setText(String.valueOf(balance));
        //发送指令
        startService();
    }

    public int getBalance() {
        return balance;
    }


    //TODO 20101219输出命令和数据
    private String getCommandStr(int cmdStr) {
        String returnStr = "波速度";
        switch (cmdStr) {
            case 1:
                returnStr = "1 测试";
                break;
            case 2:
                returnStr = "2 模式";
                break;
            case 3:
                returnStr = "3 范围";
                break;
            case 4:
                returnStr = "4 增益";
                break;
            case 5:
                returnStr = "5 延时";
                break;
            case 7:
                returnStr = "7 平衡";
                break;
            case 9:
                returnStr = "9 接受数据";
                break;

        }
        return returnStr;
    }

    private String getDataTransfer(int cmdStr, int dataStr) {
        String returnStr = "0";
        if (cmdStr == 1) {
            switch (dataStr) {
                case 17:
                    returnStr = "测试 11";
                    break;
                case 34:
                    returnStr = "取消测试 22";
                    break;
            }
        }
        if (cmdStr == 2) {
            switch (dataStr) {
                case 17:
                    returnStr = "TDR 11";
                    break;
                case 34:
                    returnStr = "ICM 22";
                    break;
                case 85:
                    returnStr = "ICM_DECAY 55";
                    break;
                case 51:
                    returnStr = "SIM 33";
                    break;
                case 68:
                    returnStr = "DECAY 44";
                    break;
            }
        }
        if (cmdStr == 3) {
            switch (dataStr) {
                case 153:
                    returnStr = "范围 250";
                    break;
                case 17:
                    returnStr = "范围 500";
                    break;
                case 34:
                    returnStr = "范围 1KM";
                    break;
                case 51:
                    returnStr = "范围 2KM";
                    break;
                case 68:
                    returnStr = "范围 4KM";
                    break;
                case 85:
                    returnStr = "范围 8KM";
                    break;
                case 102:
                    returnStr = "范围 16KM";
                    break;
                case 119:
                    returnStr = "范围 32KM";
                    break;
                case 136:
                    returnStr = "范围 64KM";
                    break;
            }
        }

        if (cmdStr == 4) {
            returnStr = "增益 " + String.valueOf(dataStr);

        }
        if (cmdStr == 5) {
            returnStr = "延迟 " + String.valueOf(dataStr);

        }
        if (cmdStr == 7) {
            returnStr = "平衡 " + String.valueOf(dataStr);

        }
        if (cmdStr == 9) {
            returnStr = "开始接收数据 " + String.valueOf(dataStr);

        }
        return returnStr;
    }

    /**
     * 组织需要绘制的波形数组（抽点510个）——最终得到waveDraw和waveCompare    //GC20191219
     */
    private void organizeWaveData() {
        /*if ((mode == TDR) || (mode == SIM)) {
            dataLength = dataMax - removeTdrSim[rangeState];
        } else if ((mode == ICM) || (mode == DECAY) || (mode == ICM_DECAY)) {
            dataLength = dataMax - removeIcmDecay[rangeState];
        }
        //按最大比例抽出510个点
        for (int i = 0, j = 0; j < 510; i = i + densityMax, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            waveDraw[j] = Constant.WaveData[i];
            //组织SIM的第二条波形的数据
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
        //移动滑块到指定位置
        setHorizontalMoveView();*/
        //按最大比例抽出510个点
        for (int i = 0, j = 0; j < 510; i = i + densityMax, j++) {
            //组织TDR、ICM、ICM_DECAY、DECAY和SIM的第一条波形的数据
            waveDraw[j] = Constant.WaveData[i];
            //组织SIM的第二条波形的数据
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }

        int[] waveDraw250 = new int[255];
        int[] waveCompare250 = new int[255];
        int[] simDraw2501 = new int[255];
        int[] simDraw2502 = new int[255];
        int[] simDraw2503 = new int[255];
        int[] simDraw2504 = new int[255];
        int[] simDraw2505 = new int[255];
        int[] simDraw2506 = new int[255];
        int[] simDraw2507 = new int[255];
        int[] simDraw2508 = new int[255];

        //需要操作的数据长度
        if ((mode == TDR) || (mode == SIM)) {
            dataLength = dataMax - removeTdrSim[rangeState];
            //GC20191223
            if (range == RANGE_250) {
                //取出前255个点数
                for (int i = 0, j = 0; i < 255; i++, j++) {
                    waveDraw250[j] = waveDraw[i];
                    //组织SIM的第二条波形的数据
                    if (mode == SIM) {
                        waveCompare250[j] = waveCompare[i];
                        if (!isDatabase) {
                            simDraw2501[j] = simDraw1[i];
                            simDraw2502[j] = simDraw2[i];
                            simDraw2503[j] = simDraw3[i];
                            simDraw2504[j] = simDraw4[i];
                            simDraw2505[j] = simDraw5[i];
                            simDraw2506[j] = simDraw6[i];
                            simDraw2507[j] = simDraw7[i];
                            simDraw2508[j] = simDraw8[i];
                        }
                    }
                }
                //算出差值的255个点
                for (int i = 0, j = 1; i < 254; i++, j += 2) {
                    waveDraw[j] = waveDraw250[i] + (waveDraw250[i + 1] - waveDraw250[i]) / 2;
                    //组织SIM的第二条波形的数据
                    if (mode == SIM) {
                        waveCompare[j] = waveCompare250[i] + (waveCompare250[i + 1] - waveCompare250[i]) / 2;
                        if (!isDatabase) {
                            simDraw1[j] = simDraw2501[i] + (simDraw2501[i + 1] - simDraw2501[i]) / 2;
                            simDraw2[j] = simDraw2502[i] + (simDraw2502[i + 1] - simDraw2502[i]) / 2;
                            simDraw3[j] = simDraw2503[i] + (simDraw2503[i + 1] - simDraw2503[i]) / 2;
                            simDraw4[j] = simDraw2504[i] + (simDraw2504[i + 1] - simDraw2504[i]) / 2;
                            simDraw5[j] = simDraw2505[i] + (simDraw2505[i + 1] - simDraw2505[i]) / 2;
                            simDraw6[j] = simDraw2506[i] + (simDraw2506[i + 1] - simDraw2506[i]) / 2;
                            simDraw7[j] = simDraw2507[i] + (simDraw2507[i + 1] - simDraw2507[i]) / 2;
                            simDraw8[j] = simDraw2508[i] + (simDraw2508[i + 1] - simDraw2508[i]) / 2;
                        }
                    }
                }
                //将原始255个点分散
                for (int i = 0, j = 0; j < 255; i++, j++) {
                    waveDraw[2 * j] = waveDraw250[i];
                    //组织SIM的第二条波形的数据
                    if (mode == SIM) {
                        waveCompare[2 * j] = waveCompare250[i];
                        if (!isDatabase) {
                            simDraw1[2 * j] = simDraw2501[i];
                            simDraw2[2 * j] = simDraw2502[i];
                            simDraw3[2 * j] = simDraw2503[i];
                            simDraw4[2 * j] = simDraw2504[i];
                            simDraw5[2 * j] = simDraw2505[i];
                            simDraw6[2 * j] = simDraw2506[i];
                            simDraw7[2 * j] = simDraw2507[i];
                            simDraw8[2 * j] = simDraw2508[i];
                        }
                    }
                }
            }

        } else if ((mode == ICM) || (mode == DECAY) || (mode == ICM_DECAY)) {

            dataLength = dataMax - removeIcmDecay[rangeState];
            if (range == RANGE_250)
                dataLength = dataLength / 2;
        }

        //移动滑块到指定位置
        setHorizontalMoveView();
    }

    /**
     * 设置波形绘制参数
     */
    private void setWaveParameter() {
        //记录当前显示波形的参数   //GC20190716 避免切换范围、方式之后存储的波形参数与波形数据不对应
        Constant.ModeValue = mode;
        Constant.RangeValue = range;
        Constant.Gain = gain;
        Constant.Velocity = velocity;
        //GC20190716 规避放大缩小操作的bug
        Constant.DensityMax = densityMax;
        if (density > densityMax) {
            density = densityMax;
            tvZoomValue.setText("1 : " + density);
        }
        //非显示数据库波形状态
        isDatabase = false;
        //擦除比较波形
        isCom = false;
        if (mode == TDR) {
            //需要绘制的波形原始数组初始化
            dataMax = READ_TDR_SIM[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            dataMax = READ_ICM_DECAY[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
        } else if (mode == SIM) {
            dataMax = READ_TDR_SIM[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
            Constant.SimData = new int[dataMax];
            //GC20190702 SIM第二条波形初始化
            simArray1 = new int[dataMax];
            simArray2 = new int[dataMax];
            simArray3 = new int[dataMax];
            simArray4 = new int[dataMax];
            simArray5 = new int[dataMax];
            simArray6 = new int[dataMax];
            simArray7 = new int[dataMax];
            simArray8 = new int[dataMax];
            //利用比较功能绘制SIM的第二条波形数据
            isCom = true;
        }

        //TODO 201912241202 MIM模式下不要重置零点，因为positionReal整除会丢失精度
        if (density == densityMax) {
            if (mode != SIM) {
                zero = positionReal * densityMax;
            }
            pointDistance = positionVirtual * densityMax;
        }

    }

    /**
     * 处理APP接收的波形数据
     */
    private void doWifiWave(int[] wifiArray) {
        if (wifiArray[3] == WAVE_TDR_ICM_DECAY) {
            System.arraycopy(wifiArray, 8, waveArray, 0, dataMax);
            //ConnectService.canAskPower = true;
            Constant.WaveData = waveArray;
            handler.sendEmptyMessage(VIEW_REFRESH);
        } else if (wifiArray[3] == WAVE_SIM || wifiArray[3] == 0x88
                || wifiArray[3] == 0x99
                || wifiArray[3] == 0xAA
                || wifiArray[3] == 0xBB
                || wifiArray[3] == 0xCC
                || wifiArray[3] == 0xDD
                || wifiArray[3] == 0xEE
                || wifiArray[3] == 0xFF) {
          /*  System.arraycopy(wifiArray, 8, waveArray, 0, dataMax);
            System.arraycopy(wifiArray, dataMax + 9 + 8, simArray1, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 2 + 8, simArray2, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 3 + 8, simArray3, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 4 + 8, simArray4, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 5 + 8, simArray5, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 6 + 8, simArray6, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 7 + 8, simArray7, 0, dataMax);
            System.arraycopy(wifiArray, (dataMax + 9) * 8 + 8, simArray8, 0, dataMax);
            Constant.TempData1 = simArray1;
            Constant.TempData2 = simArray2;
            Constant.TempData3 = simArray3;
            Constant.TempData4 = simArray4;
            Constant.TempData5 = simArray5;
            Constant.TempData6 = simArray6;
            Constant.TempData7 = simArray7;
            Constant.TempData8 = simArray8;
            Constant.SimData = Constant.TempData1;*/
            if (wifiArray[3] == 0x77) {
                System.arraycopy(wifiArray, 8, waveArray, 0, dataMax);
                Constant.WaveData = waveArray;
                Log.e("【MIM】", "第1条");

            }


            if (wifiArray[3] == 0x88) {
                System.arraycopy(wifiArray, 8, simArray1, 0, dataMax);
                Constant.TempData1 = simArray1;
                Constant.SimData = Constant.TempData1;
                Log.e("【MIM】", "第2条");

            }
            if (wifiArray[3] == 0x99) {
                System.arraycopy(wifiArray, 8, simArray2, 0, dataMax);
                Constant.TempData2 = simArray2;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第3条");

            }
            if (wifiArray[3] == 0xAA) {
                System.arraycopy(wifiArray, 8, simArray3, 0, dataMax);
                Constant.TempData3 = simArray3;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第4条");

            }
            if (wifiArray[3] == 0xBB) {
                System.arraycopy(wifiArray, 8, simArray4, 0, dataMax);
                Constant.TempData4 = simArray4;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第5条");

            }
            if (wifiArray[3] == 0xCC) {
                System.arraycopy(wifiArray, 8, simArray5, 0, dataMax);
                Constant.TempData5 = simArray5;

                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第6条");
            }
            if (wifiArray[3] == 0xDD) {
                System.arraycopy(wifiArray, 8, simArray6, 0, dataMax);
                Constant.TempData6 = simArray6;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第7条");
            }
            if (wifiArray[3] == 0xEE) {
                System.arraycopy(wifiArray, 8, simArray7, 0, dataMax);
                Constant.TempData7 = simArray7;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                Log.e("【MIM】", "第8条");
            }
            if (wifiArray[3] == 0xFF) {
                System.arraycopy(wifiArray, 8, simArray8, 0, dataMax);
                Constant.TempData8 = simArray8;
                handler.sendEmptyMessage(ORGNIZE_WAVE);
                //ConnectService.canAskPower = true;
                Log.e("【MIM】", "第9条");

            }


            if (wifiArray[3] == 0x88) {


                handler.sendEmptyMessage(VIEW_REFRESH);
            }
        }
        //记录当前显示波形的数据
    }

    /**
     * 根据参数重置显示效果
     */
    public void resetWhatNeed() {
        //二次脉冲显示组数重置    //GC201907052   优化SIM显示
        if (mode == SIM) {
            selectSim = 1;
            setSelectSim(selectSim);
            //20191217下翻有效


            //TODO 20200325 二次脉冲如果是从数据库显示，则禁用上翻下翻按键，测试后恢复。
            if (!isDatabase) {
                tvWaveNext.setEnabled(true);
            } else {
                tvWaveNext.setEnabled(false);
                tvWavePre.setEnabled(false);
            }

        } else {

        }

        if (isDatabase) {
            //GC201907052   优化SIM显示

        }
        //放大缩小按钮显示重置    //GC20190711
        if (density == densityMax) {
            if (density == 1) {

            } else {

            }

        }
    }

    /**
     * 设置数据库波形绘制参数  //GC20190713
     */
    public void setDateBaseParameter() {
        //显示数据库波形状态
        isDatabase = true;
        //读取并设置数据库的参数
        ///20191217屏蔽设置，在网络连接后设置
        //TODO 20191223-1129 模式页面网络正常，打开记录应下发
        setModeNoCmd(Constant.Para[0]);
        setRangeNoCmd(Constant.Para[1]);
        setGain(Constant.Para[2]);
        setVelocityNoCmd(Constant.Para[3]);
        Constant.ModeValue = Constant.Para[0];
        Constant.RangeValue = Constant.Para[1];
        Constant.Gain = Constant.Para[2];
        Constant.Velocity = Constant.Para[3];


        //TODO 2019-1222-2256 初始化虚光标、实光标、零点、比例等参数，否则缩放失效
        //读取零点和虚光标在原始数据中的位置，重新绘制光标
        zero = Constant.PositionR;
        pointDistance = Constant.PositonV;
        positionVirtual = pointDistance / densityMax;
        positionReal = zero / densityMax;
        fullWave.setScrubLineVirtual(positionVirtual);
        fullWave.setScrubLineReal(positionReal);

        //zero = positionReal * densityMax;
        Constant.DensityMax = densityMax;
        //pointDistance = positionVirtual * densityMax;

        //显示数据库中存储的位置
        if (Constant.CurrentUnit == Constant.MiUnit) {
            if (Constant.CurrentSaveUnit == Constant.MiUnit)
                tvDistance.setText(new DecimalFormat("0.00").format(Constant.SaveLocation));
            else
                tvDistance.setText(UnitUtils.ftToMi(Constant.SaveLocation));
        } else {
            if (Constant.CurrentSaveUnit == Constant.FtUnit)
                tvDistance.setText(new DecimalFormat("0.00").format(Constant.SaveLocation));
            else
                tvDistance.setText(UnitUtils.miToFt(Constant.SaveLocation));

        }


        //擦除比较波形
        isCom = false;
        //需要绘制的波形原始数组初始化
        if (mode == TDR) {
            dataMax = READ_TDR_SIM[rangeState];
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            dataMax = READ_ICM_DECAY[rangeState];
        } else if (mode == SIM) {
            dataMax = READ_TDR_SIM[rangeState];
            //利用比较功能绘制SIM的第二条波形数据
            isCom = true;
        }
        initMode();
    }

    /**
     * @param mode 需要发送的方式控制命令值 / 响应信息栏方式变化
     */
    public void setMode(int mode) {
        this.mode = mode;
        command = COMMAND_MODE;
        dataTransfer = mode;
        switch (mode) {
            case TDR:
                tvMode.setText(getResources().getString(R.string.btn_tdr));
                //GC20190709
                switchDensity();
                initCursor();
                break;
            case ICM:
               /* if (modeIntent == ICM_DECAY) {
                    tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                } else {
                    tvMode.setText(getResources().getString(R.string.btn_icm));
                }*/
                tvMode.setText(getResources().getString(R.string.btn_icm));
                switchDensity();
                initCursor();
                break;
            case ICM_DECAY:
                tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                //GC20190709
                switchDensity();
                initCursor();
                break;
            case SIM:
                tvMode.setText(getResources().getString(R.string.btn_sim));
                switchDensity();
                initCursor();
                break;
            case DECAY:
                tvMode.setText(getResources().getString(R.string.btn_decay));
                switchDensity();
                initCursor();
                break;
            default:
                break;
        }
        startService();
    }

    public void setModeNoCmd(int mode) {
        this.mode = mode;
        command = COMMAND_MODE;
        dataTransfer = mode;
        startService();
        switch (mode) {
            case TDR:
                tvMode.setText(getResources().getString(R.string.btn_tdr));
                switchDensity();
                //GC20190709
                break;
            case ICM:
                switchDensity();
                /*if (modeIntent == ICM_DECAY) {
                    tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                } else {
                    tvMode.setText(getResources().getString(R.string.btn_icm));
                }*/
                tvMode.setText(getResources().getString(R.string.btn_icm));

                break;
            case ICM_DECAY:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                break;
            case SIM:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_sim));
                break;
            case DECAY:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_decay));
                break;
            default:
                break;
        }
    }

    /**
     * 比例选择 //GC20190709
     */
    private void switchDensity() {
        if ((mode == TDR) || (mode == SIM)) {
            densityMax = densityMaxTdrSim[rangeState];
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            densityMax = densityMaxIcmDecay[rangeState];
            //GC20191223
            if (range == RANGE_250) {
                densityMax = densityMax / 2;
            }
        }
        density = densityMax;
        tvZoomValue.setText("1 : " + density);
        density = getDensity();


        //TODO 默认显示滚动条 wdx 20191218
        tvZoomMin.setEnabled(true);
        llHorizontalView.setVisibility(View.VISIBLE);

        //TODO 默认显示滚动条，以下代码注释 wdx 20191218
//        if (density > 1) {
//            tvZoomMin.setEnabled(true);
//            viewMoveHorizontalWave.setVisibility(View.VISIBLE);
//        } else {
//            tvZoomMin.setEnabled(false);
//            viewMoveHorizontalWave.setVisibility(View.GONE);
//        }
//        if (density < Constant.DensityMax) {
        tvZoomPlus.setEnabled(true);
        tvZoomMin.setEnabled(true);
//        //设置滑动块的宽度
        setHorizontalMoveViewOnlyHeight();
//        //移动滑块位置
//        if (fenzi1 != 0) {
//            setHorizontalMoveViewPosition(positionVirtual * mvWave.getParentWidth() / fenzi1);
//        }
    }

    /**
     * 设置滑动块的宽度
     */
    private void setHorizontalMoveView() {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(rlWave.getWidth() / density, 30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mvWave.getParentWidth() * 510 * density / dataLength, getResources().getDimensionPixelSize(R.dimen.dp_20));
        mvWave.setLayoutParams(layoutParams);
    }

    private void setHorizontalMoveViewOnlyHeight() {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(rlWave.getWidth() / density, 30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mvWave.getWidth(), getResources().getDimensionPixelSize(R.dimen.dp_20));
        mvWave.setLayoutParams(layoutParams);
    }

    /**
     * 设置滑动块的宽度
     */
    private void setHorizontalMoveViewPosition(int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mvWave.getLayoutParams();
        layoutParams.leftMargin = fenzi2 - mvWave.getWidth() / 2;
        mvWave.setLayoutParams(layoutParams);
    }

    private void setMoverPostion(int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mvWave.getLayoutParams();
        layoutParams.leftMargin = position;
        mvWave.setLayoutParams(layoutParams);
    }

    /**
     * 光标位置和距离显示初始化 //GC20190709
     */
    private void initCursor() {
        //光标距离
        if (mode == SIM) {
            //GC20190712
            zero = simZero;
            if (range == RANGE_250)
                zero = zero * 2;
            Log.e("TEST", "位置" + positionReal);
        } else {
            zero = 0;
        }
        pointDistance = 255 * densityMax;
        //计算并在界面显示距离
        calculateDistance(Math.abs(pointDistance - zero));
        //界面定位
        positionReal = zero / densityMax;
        if (mode == SIM && range == RANGE_250) {
            // positionReal = positionReal * 2;
        }
        positionVirtual = pointDistance / densityMax;
        if (positionReal >= 0)
            fullWave.setScrubLineReal(positionReal);
        fullWave.setScrubLineVirtual(positionVirtual);

    }

    /**
     * @param range 需要发送的范围控制命令值 / 响应信息栏范围变化
     */
    public void setRange(int range) {

        if (AllowSetRange == false)
            return;

        AllowSetRange = false;
        this.range = range;

        switch (range) {
            case RANGE_250:
                range = 0x99;
                rangeState = 0;
                //GC20190709
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
                } else if (Constant.CurrentUnit == Constant.FtUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                }

                gain = 13;
                tvGainValue.setText("13");
                break;
            case RANGE_500:
                rangeState = 1;
                //GC20190709
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                }
                gain = 13;
                tvGainValue.setText("13");
                break;
            case RANGE_1_KM:
                rangeState = 2;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                }
                gain = 13;
                tvGainValue.setText("10");
                break;
            case RANGE_2_KM:
                rangeState = 3;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("10");
                break;
            case RANGE_4_KM:
                rangeState = 4;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("10");
                break;
            case RANGE_8_KM:
                rangeState = 5;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("10");
                break;
            case RANGE_16_KM:
                rangeState = 6;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("9");
                break;
            case RANGE_32_KM:
                rangeState = 7;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("9");
                break;
            case RANGE_64_KM:
                rangeState = 8;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("9");
                break;
            default:
                break;
        }

//发送指令
        command = COMMAND_RANGE;
        dataTransfer = range;
        startService();
    }

    public void setRangeNoCmd(int range) {
        this.range = range;

        switch (range) {
            case RANGE_250:
                rangeState = 0;
                switchDensity();
                //GC20190709
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
                } else if (Constant.CurrentUnit == Constant.FtUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                }
                break;
            case RANGE_500:
                rangeState = 1;
                switchDensity();
                //GC20190709
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                }
                break;
            case RANGE_1_KM:
                rangeState = 2;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                }
                break;
            case RANGE_2_KM:
                rangeState = 3;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                }
                break;
            case RANGE_4_KM:
                rangeState = 4;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                }
                break;
            case RANGE_8_KM:
                rangeState = 5;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                }
                break;
            case RANGE_16_KM:
                rangeState = 6;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                }
                break;
            case RANGE_32_KM:
                rangeState = 7;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                }
                break;
            case RANGE_64_KM:
                rangeState = 8;
                switchDensity();
                if (Constant.CurrentUnit == Constant.MiUnit) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                }
                break;
            default:
                break;
        }
        command = COMMAND_RANGE;
        //TODO 选择250，发送500命令，显示距离250 wdx 20191218
        if (range == RANGE_250) {
            dataTransfer = 0x11;
        } else {
            dataTransfer = range;
        }
        startService();

    }

    /**
     * @param gain 需要发送的增益控制命令值 / 响应信息栏增益变化
     */
    public void setGain(int gain) {
        this.gain = gain;
        Constant.Gain = gain;
        command = COMMAND_GAIN;
        dataTransfer = gain;
        tvGainValue.setText(String.valueOf(gain));
        startService();
    }

    public void setGainNoCmd(int gain) {
        this.gain = gain;
        command = COMMAND_GAIN;
        dataTransfer = gain;
        tvGainValue.setText(String.valueOf(gain));
    }


    public int getGain() {
        return gain;
    }

    /**
     * @param velocity 响应状态栏波速度变化
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        if (Constant.CurrentUnit == Constant.MiUnit) {
            tvVopValue.setText(String.valueOf(velocity));
        } else {
            tvVopValue.setText(UnitUtils.miToFt(velocity));
        }

        //GC20190709    //G?
        if (isReceiveData == false || isDatabase == true) {
        } else
            calculateDistance(Math.abs(pointDistance - zero));
    }

    public void setVelocityNoCmd(int velocity) {
        if (Constant.CurrentUnit == Constant.MiUnit) {
            tvVopValue.setText(String.valueOf(velocity));
        } else {
            tvVopValue.setText(UnitUtils.miToFt(velocity));
        }

    }


    @OnClick({R.id.tv_500m, R.id.tv_250m, R.id.tv_1km, R.id.tv_2km, R.id.tv_4km, R.id.tv_8km, R.id.tv_16km, R.id.tv_32km, R.id.tv_64km,
            R.id.tv_gain_add, R.id.tv_gain_min, R.id.layout_tv_both, R.id.layout_tv_memory, R.id.tv_cursor_plus, R.id.tv_balance_plus, R.id.tv_balance_min, R.id.tv_pulse_width, R.id.tv_cal, R.id.tv_range,
            R.id.tv_file, R.id.tv_home, R.id.tv_zero, R.id.tv_cursor_min, R.id.tv_zoom_plus, R.id.tv_zoom_min, R.id.tv_test, R.id.tv_help,
            R.id.tv_compare,
            R.id.ll_adjust,
            R.id.iv_pulse_width_close, R.id.tv_pulse_width_save,
            R.id.tv_vop_save, R.id.iv_compare_close, R.id.iv_cal_close, R.id.iv_range_close, R.id.iv_records_close,
            R.id.tv_vop_min, R.id.tv_vop_plus,
            R.id.tv_records_save, R.id.tv_file_records
            , R.id.tv_origin, R.id.tv_trigger_delay, R.id.tv_delay_plus, R.id.tv_delay_min, R.id.ll_trigger_delay, R.id.iv_close_trigger_delay,
            R.id.tv_wave_next, R.id.tv_wave_pre})
    public void onClick(View view) {

        //TODO 20200416
        //如果weight连接不执行
        if (ConnectService.isConnected != true) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }
        //如果测试中不执行
        if (Constant.isTesting == true)
            return;

        switch (view.getId()) {
            case R.id.ll_adjust:
                break;
            case R.id.tv_wave_next:
                selectSim = getSelectSim();
                if (selectSim < 8) {
                    selectSim++;
                    setSelectSim(selectSim);
                    tvWavePre.setEnabled(true);
                }
                //到第8组波形，上翻按钮点击无效
                if (selectSim == 8) {
                    tvWaveNext.setEnabled(false);
                }
                break;
            case R.id.tv_wave_pre:
                //GC20190702 SIM共8组，从1-8

                //20191217不要重复定义变量
                selectSim = getSelectSim();
                //int selectSim = getSelectSim();
                if (selectSim > 1) {
                    selectSim--;
                    setSelectSim(selectSim);
                    tvWaveNext.setEnabled(true);
                }
                //到第1组波形，上翻按钮点击无效
                if (selectSim == 1) {
                    tvWavePre.setEnabled(false);
                    //20191217波形8时禁用下翻按钮，重新测试时重新生效。
                    tvWaveNext.setEnabled(true);

                }
                break;
            case R.id.iv_close_trigger_delay:
                closeTriggerDelayView();
                break;
            case R.id.tv_origin:
                //20191217 zero修改为实光标位置
                //int simZero = zero;
                int simZero = zero;
                if (mode == SIM && range == RANGE_250) {
                    simZero = simZero / 2;
                }
                setSimZero(simZero);
                break;
            case R.id.tv_trigger_delay:
                showTriggerDelayView();
                break;
            case R.id.tv_delay_plus:
                int delay = getDelay();
                if (delay < 1250) {
                    delay = delay + 5;
                    //GC20190704 延时发送命令修改   (延时从0到1250，点击一次增加5，共250阶)
                    setDelay(delay);
                    tvDelayMin.setEnabled(true);
                }
                if (delay == 1250) {
                    tvDelayPlus.setEnabled(false);
                }
                break;
            case R.id.tv_delay_min:
                delay = getDelay();
                if (delay > 0) {
                    delay = delay - 5;
                    setDelay(delay);
                    tvDelayPlus.setEnabled(true);
                }
                if (delay == 0) {
                    tvDelayMin.setEnabled(false);
                }
                break;
            case R.id.iv_compare_close:
                closeCompareView();
                break;
            case R.id.iv_cal_close:
                closeCalView();
                break;
            case R.id.iv_pulse_width_close:
                closePulseWidthView();
                break;
            case R.id.iv_range_close:
                closeRangeView();
                break;
            case R.id.iv_records_close:
                closeFileView();
                break;
            case R.id.tv_vop_save:
                saveVop();
                llCal.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_pulse_width_save:
                savePulseWidth();
                break;
            case R.id.tv_file_records:
                showRecordsDialog();
                break;
            case R.id.tv_records_save:
                showSaveDialog();
                break;
            case R.id.tv_250m:
                setRange(0x99);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_500m:
                setRange(0x11);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_1km:
                setRange(0x22);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_2km:
                setRange(0x33);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_4km:
                setRange(0x44);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_8km:
                setRange(0x55);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_16km:
                setRange(0x66);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_32km:
                setRange(0x77);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_64km:
                setRange(0x88);
                setGain(gain);
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);

                break;
            case R.id.tv_gain_add:
                int gain = getGain();
                if (gain < 31) {
                    gain++;
                    //GC20190704 增益发送命令修改   (命令范围0-31阶)
                    setGain(gain);
                    tvGainMin.setEnabled(true);
                }

                //增益命令到最大，按钮点击无效
                if (gain == 31) {
                    tvGainAdd.setEnabled(false);

                }
                closeAllView();
                break;
            case R.id.tv_gain_min:
                gain = getGain();
                if (gain > 0) {
                    gain--;
                    setGain(gain);
                    tvGainAdd.setEnabled(true);
                }

                if (gain == 0) {
                    tvGainMin.setEnabled(false);
                }
                closeAllView();
                break;
            case R.id.tv_balance_plus:
                int balance = getBalance();
                if (balance < 15) {
                    balance++;
                    //GC20190704 平衡发送命令修改   (命令范围0-15阶)
                    setBalance(balance);
                    tvBalanceMin.setEnabled(true);
                }

                if (balance == 15) {
                    tvBalancePlus.setEnabled(false);
                }
                closeAllView();
                break;
            case R.id.tv_balance_min:
                balance = getBalance();
                if (balance > 0) {
                    balance--;
                    setBalance(balance);
                    tvBalancePlus.setEnabled(true);
                }

                if (balance == 0) {
                    tvBalanceMin.setEnabled(false);

                }
                closeAllView();
                break;
            case R.id.tv_vop_min:
                velocity = getVelocity();
                if (velocity > 90) {
                    velocity--;
                    setVelocity(velocity);
                    saveVop();

                }
               /* if (Constant.CurrentUnit == Constant.FtUnit) {
                    if (velocity > Double.valueOf(UnitUtils.miToFt(90))) {
                        velocity--;
                        setVelocity(velocity);
                        saveVop();

                    }
                } else {
                    if (velocity > 90) {
                        velocity--;
                        setVelocity(velocity);
                        saveVop();

                    }
                }*/

                break;
            case R.id.tv_vop_plus:
                int velocity = (int) getVelocity();
                if (velocity < 300) {
                    velocity++;
                    setVelocity(velocity);
                    saveVop();

                }
                /*if (Constant.CurrentUnit == Constant.FtUnit) {
                    if (velocity < Double.valueOf(UnitUtils.miToFt(300))) {
                        velocity++;
                        setVelocity(velocity);
                        saveVop();

                    }
                } else {
                    if (velocity < 300) {
                        velocity++;
                        setVelocity(velocity);
                        saveVop();

                    }
                }*/
                break;
            case R.id.tv_pulse_width:
                showPulseWidth();
                break;
            case R.id.layout_tv_memory:
                clickMemory();
                break;
            case R.id.layout_tv_both:
                clickCompare();
                break;
            case R.id.tv_compare:
                showCompareView();
                break;
            case R.id.tv_cal:
                showCalView();
                break;
            case R.id.tv_range:

                showRangeView();
                break;
            case R.id.tv_file:
                showFileView();
                break;
            case R.id.tv_home:
                finish();
                break;
            case R.id.tv_zero:
                closeAllView();

                //20191217重新计算距离
                fullWave.setScrubLineReal(positionVirtual);
                positionReal = positionVirtual;
                //在原始数据中的位置 //GC20191218
                zero = pointDistance;
                calculateDistance(0);

                break;
            case R.id.tv_cursor_min:
                closeAllView();

                if (positionVirtual > 0) {
                    int positionVirtualtemp = positionVirtual;
                    positionVirtualtemp -= 1;
                    fullWave.setScrubLineVirtual(positionVirtualtemp);
                    pointDistance = pointDistance + ((int) positionVirtualtemp - positionVirtual) * density;
                    positionVirtual = positionVirtualtemp;
                    Log.e("【按钮调光标】", "positionVirtual" + positionVirtual);
                    if (positionVirtual == 0)
                        pointDistance = 0;
                    calculateDistance(Math.abs(pointDistance - zero));
                }

                break;
            case R.id.tv_cursor_plus:
                closeAllView();

                if (positionVirtual < 509) {
                    int positionVirtualtemp = positionVirtual;
                    positionVirtualtemp += 1;
                    fullWave.setScrubLineVirtual(positionVirtualtemp);
                    pointDistance = pointDistance + ((int) positionVirtualtemp - positionVirtual) * density;
                    positionVirtual = positionVirtualtemp;
                    calculateDistance(Math.abs(pointDistance - zero));

                }
                break;
            case R.id.tv_zoom_plus:
                closeAllView();
                //GC20190711
                int density = getDensity();
                if (density > 1) {
                    density = density / 2;
                    setDensity(density);
                    tvZoomMin.setEnabled(true);
                }
                //无法放大
                if (density == 1) {
                    tvZoomPlus.setEnabled(false);
                }
                break;
            case R.id.tv_zoom_min:
                closeAllView();

                density = getDensity();
                if (density < Constant.DensityMax) {
                    density = density * 2;
                    setDensity(density);
                    tvZoomPlus.setEnabled(true);
                }

                if (density == Constant.DensityMax) {
                    tvZoomMin.setEnabled(false);

                }
                break;
            case R.id.tv_test:
                isReceiveData = true;
                clickTest();
                break;
            case R.id.tv_help:
                closeAllView();

                break;
        }
    }

    public int getSelectSim() {
        return selectSim;
    }

    /**
     * @param selectSim SIM显示波形的组数  //GC20190705
     */
    public void setSelectSim(int selectSim) {
        tvWaveText.setVisibility(View.VISIBLE);
        this.selectSim = selectSim;
        switch (selectSim) {
            case 1:
                System.arraycopy(simDraw1, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw1Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData1;
                tvWaveValue.setText(R.string.wave_one);
                break;
            case 2:
                System.arraycopy(simDraw2, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw2Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData2;
                tvWaveValue.setText(R.string.wave_two);
                break;
            case 3:
                System.arraycopy(simDraw3, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw3Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData3;
                tvWaveValue.setText(R.string.wave_three);
                break;
            case 4:
                System.arraycopy(simDraw4, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw4Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData4;
                tvWaveValue.setText(R.string.wave_four);
                break;
            case 5:
                System.arraycopy(simDraw5, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw5Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData5;
                tvWaveValue.setText(R.string.wave_five);
                break;
            case 6:
                System.arraycopy(simDraw6, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw6Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData6;
                tvWaveValue.setText(R.string.wave_six);
                break;
            case 7:
                System.arraycopy(simDraw7, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw7Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData7;
                tvWaveValue.setText(R.string.wave_seven);
                break;
            case 8:
                System.arraycopy(simDraw8, 0, waveCompare, 0, 510);
                System.arraycopy(simDraw8Full, 0, waveCompareFull, 0, 510);
                Constant.SimData = Constant.TempData8;
                tvWaveValue.setText(R.string.wave_eight);
                break;
            default:
                break;
        }
        displayWave();
    }

    /**
     * @param simZero 光标零点设置    //GC20190712
     */
    public void setSimZero(int simZero) {
        this.simZero = simZero;
        StateUtils.setInt(ModeActivity.this, AppConfig.CURRENT_CURSOR_POSITION, simZero);
        Toast.makeText(this, getResources().getString(R.string
                .cursor_zero_set_success), Toast.LENGTH_SHORT).show();

    }


    private void saveVop() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        if (paramInfo != null) {
            if (Constant.CurrentSaveUnit == MiUnit)
                paramInfo.setCableVop(String.valueOf(velocity));
            else
                paramInfo.setCableVop(UnitUtils.miToFt(Double.valueOf(String.valueOf(velocity))));
        } else {
            paramInfo = new ParamInfo();
            if (Constant.CurrentSaveUnit == MiUnit)
                paramInfo.setCableVop(String.valueOf(velocity));
            else
                paramInfo.setCableVop(UnitUtils.miToFt(Double.valueOf(String.valueOf(velocity))));
        }
        Constant.Velocity = velocity;
        StateUtils.setObject(ModeActivity.this, paramInfo, Constant.PARAM_INFO_KEY);

    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//
//            if (touchEventInView(llTriggerDelay, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//            if (touchEventInView(llCal, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//            if (touchEventInView(llRange, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//            if (touchEventInView(llCompare, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//            //TODO 波宽度保存按钮触点事件激活
//            /**
//             *  波宽度保存按钮触点事件激活 wdx 20191218
//             */
//            if (touchEventInView(llPulseWidth, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//
//            if (touchEventInView(llRecords, ev.getX(), ev.getY())) {
//                return super.dispatchTouchEvent(ev);
//            }
//            //TODO 波宽度保存按钮触点事件激活
//            /**
//             *  波宽度保存按钮触点事件激活 wdx 20191218
//             */
//            if (llPulseWidth.getVisibility() == View.VISIBLE || llTriggerDelay.getVisibility() == View.VISIBLE || llCal.getVisibility() == View.VISIBLE || llCompare.getVisibility() == View.VISIBLE || llRange.getVisibility() == View.VISIBLE || llRecords.getVisibility() == View.VISIBLE) {
//                closeTriggerDelayView();
//                closeCalView();
//                closeRangeView();
//                closeCompareView();
//                closeFileView();
//                closePulseWidthView();
//                return true;
//            }
//
//
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    /**
     * 该方法检测一个点击事件是否落入在一个View内，换句话说，检测这个点击事件是否发生在该View上。
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private boolean touchEventInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        if (y >= top && y <= bottom && x >= left && x <= right) {
            return true;
        }

        return false;
    }


    private void showFileView() {
        llRecords.setVisibility(View.VISIBLE);
        llRecords.setOnClickListener(null);
        closeCompareView();
        closeRangeView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeFileView() {
        llAdjust.setVisibility(View.VISIBLE);
        llRecords.setVisibility(View.GONE);
    }

    private void showRangeView() {

        //TODO 20200407 点击范围按钮的前提是与设备连接成功，否则吐司，禁止继续执行代码
        if (!ConnectService.isConnected) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }

        llRange.setVisibility(View.VISIBLE);
        llRange.setOnClickListener(null);
        closeFileView();
        closeCompareView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeRangeView() {
        llAdjust.setVisibility(View.VISIBLE);
        llRange.setVisibility(View.GONE);
    }

    private void showTriggerDelayView() {
        llTriggerDelay.setVisibility(View.VISIBLE);
        closeFileView();
        closeRangeView();
        closeCalView();
        closeCompareView();
        closePulseWidthView();
    }

    private void closeTriggerDelayView() {
        llAdjust.setVisibility(View.VISIBLE);
        llTriggerDelay.setVisibility(View.GONE);
    }

    private void showCalView() {
        llCal.setVisibility(View.VISIBLE);
        llCal.setOnClickListener(null);
        closeFileView();
        closeRangeView();
        closeCompareView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeCalView() {
        llAdjust.setVisibility(View.VISIBLE);
        llCal.setVisibility(View.GONE);
    }


    private void closePulseWidthView() {
        llAdjust.setVisibility(View.VISIBLE);
        llPulseWidth.setVisibility(View.GONE);
    }


    private void closeCompareView() {
        llAdjust.setVisibility(View.VISIBLE);
        llCompare.setVisibility(View.GONE);
    }

    private void showCompareView() {
        llCompare.setVisibility(View.VISIBLE);
        llCompare.setOnClickListener(null);
        closeFileView();
        closeRangeView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeAllView() {
        closeCalView();
        closeCompareView();
        closeRangeView();
        closeTriggerDelayView();
        closeFileView();
        closePulseWidthView();
    }


    //TODO wdx 20191218 设置波宽度

    /**
     * 设置波宽度，存储本地保存，wdx 20191218
     */
    private void showPulseWidth() {

        llPulseWidth.setOnClickListener(null);
        llPulseWidth.setVisibility(View.VISIBLE);
        closeFileView();
        closeRangeView();
        closeCompareView();
        closeCalView();
        closeTriggerDelayView();

    }

    //TODO wdx 20191218 界面操作波宽度本地存储保存

    /**
     * 界面操作存储本地保存，wdx 20191218
     */
    private void savePulseWidth() {
        // 01 初始化操作值
        if (etPulseWidth.getText().toString().isEmpty())
            pulseWidth = 0;
        else
            pulseWidth = Integer.valueOf(etPulseWidth.getText().toString());
        // 02 本地保存波宽度信息
        savePulseWidthInfo();
        // 03 指令下达
        //sendPulseWidthCommand();
        setPulseWidth(pulseWidth);
        closePulseWidthView();
    }

    //TODO wdx 20191218 波宽度本地存储保存

    /**
     * 存储本地保存，wdx 20191218
     */
    private void savePulseWidthInfo() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PULSE_WIDTH_INFO_KEY);
        if (paramInfo != null) {
            paramInfo.setPulseWidth(pulseWidth);
        } else {
            paramInfo = new ParamInfo();
            paramInfo.setPulseWidth(pulseWidth);
        }

        StateUtils.setObject(ModeActivity.this, paramInfo, Constant.PULSE_WIDTH_INFO_KEY);


    }

    //TODO wdx 20191218 波宽度指令下发

    /**
     * 波宽度指令下发，wdx 20191218
     */
    private void sendPulseWidthCommand() {
        command = COMMAND_PULSE_WIDTH;
        dataTransfer = calPulseWidth(pulseWidth);

        startService();
    }

    private void setPulseWidth(int pulseWidth) {
        command = COMMAND_PULSE_WIDTH;
        dataTransfer = calPulseWidth(pulseWidth);
        startService();
//        tvGainValue.setText("13");
    }

    //TODO wdx 20191218 计算波宽度数值

    /**
     * 计算波宽度数值，计算公式为255-X/40;X为输入值
     */
    private int calPulseWidth(int pulseWidth) {

        if (pulseWidth < 0 || pulseWidth > 7000) {
            return 0;
        }
        pulseWidth = 255 - pulseWidth / 40;
        return pulseWidth;
    }


    private void showSaveDialog() {
        closeFileView();
        saveRecordsDialog = new SaveRecordsDialog(this);
        Constant.ModeValue = mode;

        //saveRecordsDialog.setPositionReal(positionReal);
        //saveRecordsDialog.setPositionVirtual(positionVirtual);
        //TODO 20191226 存储zero 和poinitDistance
        saveRecordsDialog.setPositionReal(zero);
        saveRecordsDialog.setPositionVirtual(pointDistance);
        if (!saveRecordsDialog.isShowing()) {
            saveRecordsDialog.show();
        }
    }

    private void showRecordsDialog() {
        closeFileView();
        String modeStr = null;
        switch (mode) {
            case TDR:
                modeStr = getResources().getString(R.string.btn_tdr);
                break;
            case ICM:
                modeStr = getResources().getString(R.string.btn_icm);
                break;
            case ICM_DECAY:
                modeStr = getResources().getString(R.string.btn_icm_decay);
                break;
            case SIM:
                modeStr = getResources().getString(R.string.btn_sim);
                break;
            case DECAY:
                modeStr = getResources().getString(R.string.btn_decay);
                break;
            default:
                break;
        }
        showRecordsDialog = new ShowRecordsDialog(this);
        showRecordsDialog.setMode(mode);
        if (!showRecordsDialog.isShowing()) {
            showRecordsDialog.show();
        }

    }

    /**
     * @param samplingPoints 方向脉冲法自动计算-显示故障距离
     */
    private void calculateDistanceAuto(int samplingPoints) {
        double distance;
        int k;
        //脉冲电流方式下range=6(32km)和range=7(64km)实时25M采样率，其余方式和范围实时100M采样率，此时相对其它方式采样周期扩大4倍
        if ((mode == ICM || mode == ICM_DECAY) && (rangeState >= 6)) {
            k = 4;
        } else {
            k = 1;
        }
        //sc
        distance = (((double) samplingPoints * velocity) * k) / 2 * 0.01;
        //自动距离界面显示
//        tvAutoDistance.setText(new DecimalFormat("0.00").format(distance) + "m");
        //距离界面显示
//        tvDistance.setText(new DecimalFormat("0.00").format(distance) + "m");

    }

    /**
     * 测试按钮
     */
    private void clickTest() {

        //TODO 20200407 点击测试按钮的前提是与设备连接成功，否则吐司，禁止继续执行代码
        if (!ConnectService.isConnected) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO 20200415 如果测试中不要再测试
        if (Constant.isTesting == true)
            return;
        //TODO 20200407 点击测试后，禁用测试按钮，等待波形绘制完毕才能继续点。
        Constant.isTesting = true;
        tvTest.setEnabled(false);


        Constant.SaveToDBGain = Constant.Gain;
        closeAllView();
        llRange.setVisibility(View.INVISIBLE);
        //TODO 2019-1222-2224 点击测试后，zoom值恢复默认值
        //switchDensity();
        /*if (isDatabase) {

            initCursor();
        }*/

        if (tDialog != null)
            tDialog.dismiss();

        //初始化距离
        if (mode == TDR) {
            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.receiving_data)
                    .setScreenWidthAspect(this, 0.25f)
                    .setCancelableOutside(false)
                    .create()
                    .show();
            Log.e("DIA", " 正在接受数据显示" + " TDR");
            command = COMMAND_TEST;
            dataTransfer = TESTING;
            startService();
            //Log.e("【时效测试】", "命令发送测试命令");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    command = COMMAND_RECEIVE_WAVE;
                    dataTransfer = 0x11;
                    startService();
                    //Log.e("【时效测试】", "发送接收波形数据命令");

                }

            }, 20);

        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == SIM) || (mode == DECAY)) {
            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.wait_trigger)
                    .setScreenWidthAspect(this, 0.3f)
                    .setCancelableOutside(false)
                    .addOnClickListener(R.id.tv_cancel)
                    .setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

                            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                                Toast.makeText(ModeActivity.this, R.string.ask_cancel, Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    })
                    .setOnViewClickListener(new OnViewClickListener() {
                        @Override
                        public void onViewClick(BindViewHolder viewHolder, View view,
                                                TDialog tDialog) {


                            if (AlreadDisplayWave == false) {
                                tvZoomMin.setEnabled(false);
                                tvZoomPlus.setEnabled(false);
                                tvWaveNext.setEnabled(false);
                                tvWavePre.setEnabled(false);
                            }
                            //设置取消测试标志位true
                            Constant.isCancelAim = true;
                            tDialog.dismiss();
                            //TODO 20200407 取消测试后，恢复测试按钮可用性
                            tvTest.setEnabled(true);
                            Constant.isTesting = false;
                            AlreadDisplayWave = false;
                            AllowSetRange = true;

                            command = COMMAND_TEST;
                            dataTransfer = CANCEL_TEST;
                            //ConnectService.canAskPower = true;

                            startService();

                        }
                    })
                    .create()
                    .show();
            Log.e("DIA", " 等待触发显示");
            command = COMMAND_TEST;
            dataTransfer = TESTING;
            startService();
            AlreadDisplayWave = false;
            ConnectService.canAskPower = false;
            //Log.e("【时效测试】", "命令发送测试命令");
            Constant.isCancelAim = false;
        }
    }

    /**
     * 点击比较按钮执行的方法  //GC20190703
     */
    public void clickCompare() {
        closeCompareView();
        if (isMemory) {
            //GC20190703再优化
            if ((modeBefore == mode) && (rangeBefore == range)) {
                isCom = !isCom;
                myChartAdapterMainWave.setmTempArray(waveDraw);
                myChartAdapterMainWave.setShowCompareLine(isCom);
                //myChartAdapterMainWave.setmCompareArray(waveCompare);
                organizeCompareWaveData(currentStart);
                myChartAdapterMainWave.setmCompareArray(waveCompareDraw);
                myChartAdapterMainWave.notifyDataSetChanged();
            } else {
                Toast.makeText(this, getResources().getString(R.string
                        .You_can_not_compare), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string
                    .You_have_no_memory_data_can_not_compare), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 点击记忆按钮执行的方法  //GC20190703
     */
    public void clickMemory() {
        if (AlreadDisplayWave == true) {
            closeCompareView();
            isMemory = true;
            //TODO 20191224 修改记忆波形数据结构
            //System.arraycopy(waveDraw, 0, waveCompare, 0, 510);
            waveCompare = new int[Constant.WaveData.length];
            System.arraycopy(Constant.WaveData, 0, waveCompare, 0, Constant.WaveData.length);
            //记录记忆数据的方式范围   //GC20190703再优化
            modeBefore = mode;
            rangeBefore = range;
        }
    }

    //去服务发送指令
    public void startService() {
        Intent intent = new Intent(ModeActivity.this, ConnectService.class);
        //发送指令
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_COMMAND_KEY, command);
        bundle.putInt(MODE_KEY, mode);
        bundle.putInt(DATA_TRANSFER_KEY, dataTransfer);
        intent.putExtra(BUNDLE_PARAM_KEY, bundle);
        startService(intent);
    }

    @Override
    protected void onDestroy() {

        if (receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }

}
