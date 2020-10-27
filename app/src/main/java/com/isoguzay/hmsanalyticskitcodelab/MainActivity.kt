package com.isoguzay.hmsanalyticskitcodelab

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.analytics.HiAnalyticsTools
import com.huawei.hms.analytics.type.HAEventType
import com.huawei.hms.analytics.type.HAParamType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var instance: HiAnalyticsInstance
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //enable debug log for HiAnalytics
        HiAnalyticsTools.enableLog()

        //get HiAnalytics instances
        instance = HiAnalytics.getInstance(this)

        //set enable data collection based on predefined tracing points
        instance.setAnalyticsEnabled(true)

        button_custom_event_yes.setOnClickListener {
            reportCustomEventLikeAppFeedback("Yes")
            showFeedbackToast()
        }

        button_custom_event_no.setOnClickListener {
            reportCustomEventLikeAppFeedback("No")
            showFeedbackToast()
        }

        button_add_to_cart.setOnClickListener {
            reportPredefinedEvent("1", "P40 Pro", "Huawei", "Phone", "Istanbul", "1", "400")
            showAddToCartToast()
        }

    }

    private fun reportCustomEventLikeAppFeedback(feedback: String) {
        bundle.putString(CUSTOM_EVENT_FEEDBACK_PARAM_RESULT, feedback)
        instance.onEvent(CUSTOM_EVENT_FEEDBACK, bundle)
    }

    private fun reportPredefinedEvent(
        productId: String,
        productName: String,
        brand: String,
        category: String,
        storeName: String,
        quantity: String,
        price: String
    ) {
        bundle.putString(HAParamType.PRODUCTID, productId)
        bundle.putString(HAParamType.PRODUCTNAME, productName)
        bundle.putString(HAParamType.BRAND, brand)
        bundle.putString(HAParamType.CATEGORY, category)
        bundle.putString(HAParamType.STORENAME, storeName)
        bundle.putString(HAParamType.QUANTITY, quantity)
        bundle.putString(HAParamType.PRICE, price)
        instance.onEvent(HAEventType.ADDPRODUCT2CART, bundle)
    }

    private fun showFeedbackToast() {
        Toast.makeText(this, "Thanks for feedback!", Toast.LENGTH_SHORT).show()
    }

    private fun showAddToCartToast() {
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
    }
}