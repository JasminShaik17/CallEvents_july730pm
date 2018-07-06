package cubex.mahesh.callevents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var filter = IntentFilter( )
        filter.addAction("android.intent.action.PHONE_STATE")
        filter.addAction("button_clicked_broadcast_event")
        registerReceiver(MyReceiver(),filter)

        clickme.setOnClickListener({
                    var i = Intent( )
                    i.setAction("button_clicked_broadcast_event")
                    sendBroadcast(i)
        })
    }

    class MyReceiver : BroadcastReceiver( ) {
        override fun onReceive(p0: Context?, p1: Intent?) {
            var mActivity:MainActivity = p0 as MainActivity
        var tview =     mActivity.findViewById<TextView>(R.id.tview)
            tview.text = p1!!.action

           tview.text = "${tview.text} \n  ${p1.getStringExtra(TelephonyManager.EXTRA_STATE)}"

        }
    }
}
