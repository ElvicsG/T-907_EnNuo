package net.kehui.www.t_907_origin.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.kehui.www.t_907_origin.R;
import net.kehui.www.t_907_origin.application.AppConfig;
import net.kehui.www.t_907_origin.application.Constant;
import net.kehui.www.t_907_origin.base.BaseActivity;
import net.kehui.www.t_907_origin.entity.Data;
import net.kehui.www.t_907_origin.entity.ParamInfo;
import net.kehui.www.t_907_origin.util.ScreenUtils;
import net.kehui.www.t_907_origin.util.StateUtils;
import net.kehui.www.t_907_origin.util.UnitUtils;
import net.kehui.www.t_907_origin.view.MainActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static net.kehui.www.t_907_origin.application.Constant.CurrentUnit;
import static net.kehui.www.t_907_origin.application.Constant.FtUnit;
import static net.kehui.www.t_907_origin.application.Constant.LastUnit;
import static net.kehui.www.t_907_origin.application.Constant.MiUnit;
import static net.kehui.www.t_907_origin.base.BaseActivity.DECAY;
import static net.kehui.www.t_907_origin.base.BaseActivity.ICM;
import static net.kehui.www.t_907_origin.base.BaseActivity.ICM_DECAY;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_16_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_1_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_250;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_2_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_32_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_4_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_500;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_64_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.RANGE_8_KM;
import static net.kehui.www.t_907_origin.base.BaseActivity.SIM;
import static net.kehui.www.t_907_origin.base.BaseActivity.TDR;


/**
 * Create by jwj on 2019/11/26
 */
public class SaveRecordsDialog extends BaseDialog implements View.OnClickListener {
    String date;
    String time;
    Date date1 = new Date(System.currentTimeMillis());
    ImageView ivClose;
    TextView tvCableIdText;
    EditText tvCableId;
    TextView tvDateText;
    EditText tvDate;
    TextView tvModeText;
    EditText tvMode;
    TextView tvRangeText;
    EditText tvRange;
    TextView tvCableLengthText;
    EditText tvCableLength;
    TextView tvFaultLocationText;
    EditText tvFaultLocation;
    TextView tvPhaseText;
    EditText tvPhase;
    TextView tvOperatorText;
    EditText tvOperator;
    TextView tvTestSiteText;
    EditText tvTestSite;
    TextView tvFalutLocationUnit;
    TextView tvCableLengthUnit;
    TextView tvSave;
    RelativeLayout rlInfo;
    Spinner spPhase;
    private List<String> phaseList = new ArrayList<>();
    private String[] line = new String[100];
    private String[] tester = new String[100];
    private String[] location = new String[100];
    private View view;
    private ParamInfo paramInfo;
    private int positionVirtual;
    private int positionReal;


    public void setPositionReal(int positionReal) {
        this.positionReal = positionReal;
    }

    public void setPositionVirtual(int positionVirtual) {
        this.positionVirtual = positionVirtual;
    }

    public SaveRecordsDialog(@NonNull Context context) {
        super(context);
    }

    public SaveRecordsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SaveRecordsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_save_records_dialog, null, false);

        setContentView(view);
        initView();
        initUnit();
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) ( ScreenUtils.getScreenWidth(getContext()) * 0.8);
        params.height = (int) (ScreenUtils.getScreenHeight(getContext()) * 0.7);
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        initData();

    }

    private void initView() {
        ivClose = view.findViewById(R.id.iv_close);
        tvCableId = view.findViewById(R.id.tv_cable_id);
        tvDate = view.findViewById(R.id.tv_date);
        tvMode = view.findViewById(R.id.tv_mode);
        tvRange = view.findViewById(R.id.tv_range);
        tvCableLength = view.findViewById(R.id.tv_cable_length);
        tvFaultLocation = view.findViewById(R.id.tv_fault_location);
        tvPhase = view.findViewById(R.id.tv_phase);
        tvOperator = view.findViewById(R.id.tv_operator);
        tvTestSite = view.findViewById(R.id.tv_test_site);
        tvSave = view.findViewById(R.id.tv_save);
        spPhase = view.findViewById(R.id.sp_phase);
        tvFalutLocationUnit = view.findViewById(R.id.tv_fault_location_unit);
        tvCableLengthUnit = view.findViewById(R.id.tv_cable_length_unit);
        tvSave.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    private void initData() {
        getMainParamInfo();
        setEtDate();
        setEtMode();
        setEtRange();
        //线缆编号
        setEtLine();
        //延长线长度
        setCableLength();
        setCableId();
        setSpPhase();
        setEtLocation();
    }

    private void setCableId() {
        if (paramInfo != null)
            tvCableId.setText(paramInfo.getCableId());
    }

    private void setCableLength() {
        if (paramInfo != null) {
            if (CurrentUnit == MiUnit) {
                if (Constant.CurrentSaveUnit == MiUnit)
                    if (paramInfo.getCableLength().equals("0") || paramInfo.getCableLength().equals("0.0"))
                        tvCableLength.setText("");
                    else
                        tvCableLength.setText(paramInfo.getCableLength());
                else
                if (paramInfo.getCableLength().equals("0") || paramInfo.getCableLength().equals("0.0"))
                    tvCableLength.setText("");
                else
                    tvCableLength.setText(UnitUtils.ftToMi(Double.valueOf(paramInfo.getCableLength())));
            } else {
                if (Constant.CurrentSaveUnit == FtUnit)
                    if (paramInfo.getCableLength().equals("0") || paramInfo.getCableLength().equals("0.0"))
                        tvCableLength.setText("");
                    else
                    tvCableLength.setText(paramInfo.getCableLength());
                else
                if (paramInfo.getCableLength().equals("0") || paramInfo.getCableLength().equals("0.0"))
                    tvCableLength.setText("");
                else
                    tvCableLength.setText(UnitUtils.miToFt(Double.valueOf(paramInfo.getCableLength())));

            }
        }

    }

    private void getMainParamInfo() {
        paramInfo = (ParamInfo) StateUtils.getObject(getContext(), Constant.PARAM_INFO_KEY);
    }

    private void initUnit() {
        CurrentUnit = StateUtils.getInt(getContext(), AppConfig.CURRENT_UNIT, MiUnit);
        changeUnitView(CurrentUnit);

    }

    private void changeUnitView(int currentUnit) {

        if (currentUnit == MiUnit) {
            tvFalutLocationUnit.setText(R.string.mi);
            tvCableLengthUnit.setText(R.string.mi);

        } else {
            tvCableLengthUnit.setText(R.string.ft);
            tvFalutLocationUnit.setText(R.string.ft);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_save:
                final Data data = formatData(new Data());
                Flowable.create((FlowableOnSubscribe<List>) e -> {
                    dao.insertData(data);
                    List list = Arrays.asList(dao.query());
                    e.onNext(list);
                    e.onComplete();
                }, BackpressureStrategy.BUFFER)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List list) {
                        Toast.makeText(getContext(), list.size() + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                dismiss();
                break;

        }
    }

    private void setEtDate() {
        SimpleDateFormat dateSdf = new SimpleDateFormat("yy/MM/dd", Locale.US);
        SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss", Locale.US);
        date = dateSdf.format(date1);
        time = timeSdf.format(date1);
        tvDate.setText(this.date + " " + this.time);
        Constant.Date = this.date;
        Constant.Time = time;
        tvDate.setEnabled(false);

    }

    private void setEtMode() {
        int mode = Constant.ModeValue;
        switch (mode) {
            case TDR:
                tvMode.setText(getContext().getResources().getString(R.string.btn_tdr));
                Constant.Mode = TDR;
                break;
            case ICM:
                tvMode.setText(getContext().getResources().getString(R.string.btn_icm));
                Constant.Mode = ICM;
                break;
            case ICM_DECAY:
                tvMode.setText(getContext().getResources().getString(R.string.btn_icm_decay));
                Constant.Mode = ICM_DECAY;
                break;
            case SIM:
                tvMode.setText(getContext().getResources().getString(R.string.btn_sim));
                Constant.Mode = SIM;
                break;
            case DECAY:
                tvMode.setText(getContext().getResources().getString(R.string.btn_decay));
                Constant.Mode = DECAY;
                break;
            default:
                break;
        }
        tvMode.setEnabled(false);
    }

    private void setEtRange() {
        int range = Constant.RangeValue;
        switch (range) {
            case RANGE_250:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_250m_to_ft));
                    Constant.Range = RANGE_250;
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_250m));
                    Constant.Range = RANGE_250;
                }
                break;
            case RANGE_500:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_500m_to_ft));
                    Constant.Range = RANGE_500;
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_500m));
                    Constant.Range = RANGE_500;

                }
                break;
            case RANGE_1_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_1km_to_yingli));
                    Constant.Range = RANGE_1_KM;
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_1km));
                    Constant.Range = RANGE_1_KM;

                }
                break;
            case RANGE_2_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_2km_to_yingli));
                    Constant.Range = RANGE_2_KM;
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_2km));
                    Constant.Range = RANGE_2_KM;

                }
                break;
            case RANGE_4_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_4km_to_yingli));
                    Constant.Range = RANGE_4_KM;
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_4km));
                    Constant.Range = RANGE_4_KM;

                }
                break;
            case RANGE_8_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_8km_to_yingli));
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_8km));
                }
                Constant.Range = RANGE_8_KM;
                break;
            case RANGE_16_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_16km_to_yingli));
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_16km));
                }
                Constant.Range = RANGE_16_KM;
                break;
            case RANGE_32_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_32km_to_yingli));
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_32km));
                }
                Constant.Range = RANGE_32_KM;
                break;
            case RANGE_64_KM:
                if (CurrentUnit == FtUnit) {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_32km_to_yingli));
                } else {
                    tvRange.setText(getContext().getResources().getString(R.string.btn_64km));
                }
                Constant.Range = RANGE_64_KM;
                break;
            default:
                break;
        }
        tvRange.setEnabled(false);

    }


    private void setEtLine() {
        if (paramInfo != null) {
//            Constant.Line = paramInfo.getCableId();
            tvCableId.setText(paramInfo.getCableId());
        }
    }


    private void setSpPhase() {
        phaseList.add(getContext().getResources().getString(R.string.phaseA));
        phaseList.add(getContext().getResources().getString(R.string.phaseB));
        phaseList.add(getContext().getResources().getString(R.string.phaseC));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.spinner_item, phaseList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPhase.setAdapter(adapter);
        spPhase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constant.Phase = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setEtTester() {

    }

    private void setEtLocation() {
        Constant.SaveLocation = Constant.CurrentLocation;
        if (Constant.CurrentUnit == Constant.MiUnit) {
            tvFaultLocation.setText(new DecimalFormat("0.00").format(Constant.SaveLocation));
        } else {
            tvFaultLocation.setText(UnitUtils.miToFt(Constant.SaveLocation));
        }
    }

    private Data formatData(Data data) {

        data.date = Constant.Date.trim();
        data.cableId = tvCableId.getText().toString();
        data.time = Constant.Time.trim();
        data.mode = Constant.Mode + "";
        data.range = Constant.Range;
        if (Constant.CurrentSaveUnit == MiUnit)
            data.location = Constant.SaveLocation;
        else
            data.location = Double.valueOf(UnitUtils.miToFt(Constant.SaveLocation));

        if (data.location == 0) {
            if (!TextUtils.isEmpty(tvFaultLocation.getText().toString())) {
                data.location = Double.parseDouble(tvFaultLocation.getText().toString());
            }
        }

        data.line = tvCableLength.getText().toString().trim();
        if (CurrentUnit == FtUnit) {
            if (!TextUtils.isEmpty(data.line)) {
                data.line = UnitUtils.ftToMi(Double.valueOf(data.line));
            }
            //data.location = Double.parseDouble(UnitUtils.ftToMi(data.location));
        }
        data.phase = Constant.Phase + "";
        data.tester = tvOperator.getText().toString().trim();
        data.testsite = tvTestSite.getText().toString().trim();
        data.waveData = Constant.WaveData;
        data.waveDataSim = Constant.SimData;

        //TODO 20191226 存储zero 和poinitDistance
        data.positionReal = positionReal;
        data.positionVirtual = positionVirtual;
        //参数数据 方式  范围 增益 波速度
        data.para = new int[]{Constant.ModeValue, Constant.RangeValue, Constant.SaveToDBGain,
                (int) Constant.Velocity};
        return data;
    }
}
