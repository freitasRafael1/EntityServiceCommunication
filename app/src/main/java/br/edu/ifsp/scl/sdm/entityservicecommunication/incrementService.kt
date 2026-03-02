package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.app.Service
import android.content.Intent
import android.os.IBinder

class incrementService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra("VALUE", -1)?.also{
            InterEntityCommunication.valueLiveData.postValue(it + 1)
        }
        return START_NOT_STICKY //forma de reinicialização do servico
    }

    override fun onBind(intent: Intent): IBinder? = null


}