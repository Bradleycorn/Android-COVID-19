package net.bradball.android.covid19.ui.rootFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.scichart.charting.numerics.labelProviders.ILabelProvider
import com.scichart.charting.themes.ThemeColorProvider
import com.scichart.charting.themes.ThemeManager
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint
import com.scichart.charting.visuals.axes.IAxis
import com.scichart.drawing.utility.ColorUtil
import com.scichart.extensions.builders.SciChartBuilder
import dagger.android.support.DaggerFragment
import net.bradball.android.covid19.R
import net.bradball.android.covid19.databinding.FragmentRootBinding
import net.bradball.android.covid19.di.ViewModelFactory
import net.bradball.android.covid19.extensions.XyDataSeries
import net.bradball.android.covid19.extensions.getColorFromAttr
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RootFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<RootViewModel> { viewModelFactory }

    private var binding: FragmentRootBinding? = null
    // Convenience property to get non-nullable view binding object
    private val views get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRootBinding.inflate(inflater, container, false)

        setupChart()
        setupSciChart()
        return views.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupSciChart() {
        // Initialize the SciChartBuilder
        SciChartBuilder.init(requireContext())
        //ThemeManager.applyTheme(views.scichart, , requireContext())
        views.scichart.setBackgroundColor(requireContext().getColorFromAttr(R.attr.colorSurface))
        // Obtain the SciChartBuilder instance
        val sciChartBuilder = SciChartBuilder.instance()
        // Create a numeric X axis
        val xAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withAxisTitle("Kentucky")
            .withDrawMinorGridLines(false)
            .withDrawMinorTicks(false)
            .withDrawMajorBands(false)
            .build()
        // Create a numeric Y axis
        val yAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withDrawLabels(false)
            .withDrawMajorGridLines(false)
            .withDrawMinorGridLines(false)
            .withDrawMajorTicks(false)
            .withDrawMinorTicks(false)
            .withDrawMajorBands(false)
//            .withAxisTitle("Y Axis Title")
//            .withVisibleRange(0.0, 100.0)
            .build()
        // Create a TextAnnotation and specify the inscription and position for it
        val textAnnotation = sciChartBuilder.newTextAnnotation()
            .withX1(5.0)
            .withY1(55.0)
            .withText("Hello World!")
            .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
            .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
            .withFontStyle(20f, ColorUtil.White)
            .build()
        // Create interactivity modifiers
        val chartModifiers = sciChartBuilder.newModifierGroup()
            .withPinchZoomModifier().withReceiveHandledEvents(true).build()
            .withZoomPanModifier().withReceiveHandledEvents(true).build()
            .build()

        // Create and configure a CursorModifier
        val cursorModifier = sciChartBuilder.newModifierGroup()
            .withCursorModifier()
            .withShowTooltip(true).build()
            .build() // 2 builds? WTF?

        // Add the Y axis to the YAxes collection of the surface
        Collections.addAll(views.scichart.getYAxes(), yAxis)
        // Add the X axis to the XAxes collection of the surface
        Collections.addAll(views.scichart.getXAxes(), xAxis)
        // Add the annotation to the Annotations collection of the surface
        Collections.addAll(views.scichart.getAnnotations(), textAnnotation)
        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(views.scichart.getChartModifiers(), chartModifiers)
        Collections.addAll(views.scichart.getChartModifiers(), cursorModifier)

        val chartData = XyDataSeries<Int,Int>()
        chartData.append(0, 0)
        chartData.append(1, 1)
        chartData.append(2, 3)
        chartData.append(3, 6)
        chartData.append(4, 12)
        chartData.append(5, 15)
        chartData.append(6, 35)
        chartData.append(7, 63)
        chartData.append(8, 63)
        chartData.append(9, 107)
        chartData.append(10, 143)

        val lineSeries = sciChartBuilder.newLineSeries()
            .withDataSeries(chartData)
            .withStrokeStyle(requireContext().getColorFromAttr(R.attr.colorPrimary))
            .build()

        views.scichart.renderableSeries.add(lineSeries)
        views.scichart.zoomExtents()
    }

    private fun setupChart() {
        val chartData = mutableListOf<Entry>()
        chartData.add(Entry(0F, 0F))
        chartData.add(Entry(1F, 1F))
        chartData.add(Entry(2F, 3F))
        chartData.add(Entry(3F, 6F))
        chartData.add(Entry(4F, 12F))
        chartData.add(Entry(5F, 15F))
        chartData.add(Entry(6F, 35F))
        chartData.add(Entry(7F, 63F))
        chartData.add(Entry(8F, 63F))
        chartData.add(Entry(9F, 107F))
        chartData.add(Entry(10F, 143F))

        val formatter = object: ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val cal = Calendar.getInstance()
                cal.time = Date()
                cal.set(Calendar.DATE, value.toInt() + 1)

                return SimpleDateFormat("MM/dd/yyyy", Locale.US).format(cal.time)
            }
        }

        val dataSet = LineDataSet(chartData, "Kentucky")
        dataSet.axisDependency = YAxis.AxisDependency.LEFT
        val lineData = LineData(dataSet)
        views.chart.apply {
            data = lineData
            axisLeft.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
            }

            axisRight.apply {
                setDrawLabels(false)
                setDrawGridLines(false)
            }

            xAxis.apply {
                granularity = 1f
                setDrawGridLines(false)
                valueFormatter = formatter
                this.position = XAxis.XAxisPosition.BOTTOM
            }
            description.isEnabled = false
            setDrawGridBackground(true)

            invalidate()
        }
    }

}
