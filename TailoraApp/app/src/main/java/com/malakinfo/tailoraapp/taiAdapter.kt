package com.malakinfo.tailoraapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class taiAdapter : RecyclerView.Adapter<taiAdapter.HolderRecord> {

    private var context : Context? = null
    private var recordList:ArrayList<ModelRecord>? = null

    constructor(context: Context?,recordList: ArrayList<ModelRecord>):this(){
        this.context = context
        this.recordList = recordList

    }
    constructor()


    inner class HolderRecord(itemView : View) : RecyclerView.ViewHolder(itemView){

        var profileIv : ImageView = itemView.findViewById(R.id.profileIv)
        var nameTv : TextView = itemView.findViewById(R.id.nameTv)
        var phoneTv : TextView = itemView.findViewById(R.id.phoneTv)
        var dobEnterTv : TextView = itemView.findViewById(R.id.dobEnterTv)
        var dobReligajTv : TextView = itemView.findViewById(R.id.dobReligaTv)
        var moreBtnTv : ImageButton = itemView.findViewById(R.id.moreBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.raw_record,parent, false)
        )

    }

    override fun getItemCount(): Int {
        return recordList!!.size

    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val model = recordList!!.get(position)
        val id = model.id
        val name = model.name
        val image = model.image
        val phone = model.phone
        val bio = model.bio
        val dobEnter = model.dobEnter
        val dobReligaj = model.dobReligaj
        val addedTime = model.addedTime
        val updatedTime = model.updatedTime

        holder.nameTv.text = name
        holder.phoneTv.text = phone
        holder.dobEnterTv.text = dobEnter
        holder.dobReligajTv.text = dobReligaj
        if (image == "null"){
            holder.profileIv.setImageResource(R.drawable.ic_action_person)
        }
        else{
            holder.profileIv.setImageURI(Uri.parse(image))
        }

    }


}