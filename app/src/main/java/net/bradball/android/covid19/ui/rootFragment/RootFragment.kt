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
import dagger.android.support.DaggerFragment

import net.bradball.android.covid19.R
import net.bradball.android.covid19.databinding.FragmentRootBinding
import net.bradball.android.covid19.di.ViewModelFactory
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
        return views.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
