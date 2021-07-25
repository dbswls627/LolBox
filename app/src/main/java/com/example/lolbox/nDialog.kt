package com.example.lolbox
import  android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.room.Room
import kotlinx.android.synthetic.main.dialog.*

class nDialog(context :Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        np.minValue=0
        np.maxValue=3
        np.value=mainFragment.n!!
        ok.setOnClickListener {
            mainFragment.n=np.value
            db.boxDao().upadte(Box(0, mainFragment.n!!, mainFragment.dday,mainFragment.checkB))
            this.dismiss()
        }
        cancel_button.setOnClickListener {
            this.dismiss()
        }
    }
}
