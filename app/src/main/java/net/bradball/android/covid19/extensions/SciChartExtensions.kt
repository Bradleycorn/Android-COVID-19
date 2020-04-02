package net.bradball.android.covid19.extensions

import com.scichart.charting.model.dataSeries.XyDataSeries

inline fun <reified TX : Comparable<TX>, reified TY : Comparable<TY>> XyDataSeries(): XyDataSeries<TX, TY> {
    return XyDataSeries(TX::class.javaObjectType, TY::class.javaObjectType)
}