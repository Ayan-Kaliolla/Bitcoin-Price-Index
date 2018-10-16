package kz.kaliolla.bitcoinpriceindex.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.R;

public class SelectableChart extends FrameLayout implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup buttons;
    private LineChart chart;
    private Filter filter;
    private LineDataSet dataSet = new LineDataSet(new ArrayList<Entry>(), "Label");
    private Map<String, Double> data;
    private String label;
    private Disposable disposable;

    public SelectableChart(Context context) {
        super(context);
        init();
    }

    public SelectableChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SelectableChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setData(@NonNull Map<String, Double> data) {
        this.data = data;
        buttons.clearCheck();
        buttons.check(R.id.week);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private void refreshChart() {
        final List<Entry> entries = new LinkedList<>();
        if (data == null) return;
        Observable.fromIterable(data.entrySet())
                .filter(new Predicate<Map.Entry<String, Double>>() {
                    @Override
                    public boolean test(Map.Entry<String, Double> stringDoubleEntry) throws Exception {
                        long time = getDateByFormatting(stringDoubleEntry.getKey(), "yyyy-MM-dd").getTime();
                        switch (filter) {
                            case WEEK: {
                                if (time > new Date().getTime() - (1000 * 60 * 60 * 24 * 7)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                            case MONTH: {
                                if (time > new Date().getTime() - (1000 * 60 * 60 * 24 * 7 * 30)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                            default: {
                                return true;
                            }
                        }
                    }
                })
                .map(new Function<Map.Entry<String, Double>, Entry>() {
                    @Override
                    public Entry apply(Map.Entry<String, Double> item) {
                        return new Entry(getXLabel(getDateByFormatting(item.getKey(), "yyyy-MM-dd")), item.getValue().floatValue());
                    }
                })
                .sorted(new Comparator<Entry>() {
                    @Override
                    public int compare(Entry entry1, Entry entry2) {
                        if (entry1.getX() > entry2.getX()) {
                            return 1;
                        } else if (entry1.getX() < entry2.getX()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Entry>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Entry entry) {
                        entries.add(entry);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        dataSet.setValues(entries);
                        dataSet.setLabel(label != null ? label : "");
                        LineData lineData = new LineData(dataSet);
                        chart.setData(lineData);
                        chart.invalidate(); // refresh
                    }
                });
    }

    private void init() {
        inflate(getContext(), R.layout.layout_chart_view, this);
        viewBind();
        initChartConfig();
        setListener();
    }

    private void setListener() {
        buttons.setOnCheckedChangeListener(this);
    }

    private void initChartConfig() {
        dataSet.setColor(Color.WHITE);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setLineWidth(3f);
        dataSet.setValueTextSize(10f);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
    }

    private void viewBind() {
        buttons = findViewById(R.id.buttons);
        chart = findViewById(R.id.chart);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        dispose();
        switch (id) {
            case R.id.week:
                filter = Filter.WEEK;
                refreshChart();
                break;
            case R.id.month:
                filter = Filter.MONTH;
                refreshChart();
                break;
            case R.id.year:
                filter = Filter.YEAR;
                refreshChart();
                break;
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private float getXLabel(Date date) {
        return date.getDay();
    }

    private Date getDateByFormatting(@NonNull String date, @NonNull String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private enum Filter {
        WEEK, MONTH, YEAR
    }
}
