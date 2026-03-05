package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message

class incrementService : Service() {

    private inner class IncrementHandler(looper: Looper): Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)


            val valorAtual = msg.arg1

            val intent = Intent("INCREMENT_VALUE_ACTION")
            intent.putExtra("VALUE", valorAtual + 1)

            intent.setPackage(packageName)
            sendBroadcast(intent)

            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra("VALUE", -1)?.also { value ->
            val handlerThread = HandlerThread("IncrementThread")
            handlerThread.start()

            val handler = IncrementHandler(handlerThread.looper)
            val msg = handler.obtainMessage()


            msg.arg1 = value
            handler.sendMessage(msg)
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null
}