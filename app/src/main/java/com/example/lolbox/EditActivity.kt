package com.example.lolbox

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        var boollist = arrayListOf<Bool>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        rv2.layoutManager = LinearLayoutManager(this)
        rv2.adapter=adapter2(boollist,this)
        for (index in mainFragment.list.indices){
            boollist.add(Bool(mainFragment.list[index].save))
        }
        edit.setOnClickListener {
            for (index in boollist.indices){
                mainFragment.list[index].save=boollist[index].save
                db.userDao().upadte(User(mainFragment.list[index].img,mainFragment.list[index].name,mainFragment.list[index].box,mainFragment.list[index].save))
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}