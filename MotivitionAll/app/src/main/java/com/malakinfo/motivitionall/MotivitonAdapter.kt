package com.malakinfo.motivitionall

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.malakinfo.motivitionall.Supplaray.listMovti
import kotlinx.android.synthetic.main.raw_item.view.*

class MotivitonAdapter(
    val context: Context,
    val rawItem: List<RawItem>
) : RecyclerView.Adapter<MotivitonAdapter.MotiviHolder>() {
    class MotiviHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun dataBind(rawItem: RawItem){

            itemView.tvText.text = rawItem.mtitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotiviHolder {
        val m = LayoutInflater.from(context).inflate(R.layout.raw_item,parent,false)
        return MotiviHolder(m)
    }

    override fun getItemCount(): Int {
        return listMovti.size
    }

    override fun onBindViewHolder(holder: MotiviHolder, position: Int) {
        holder.dataBind(listMovti[position])

        holder.itemView.shareImage.setOnClickListener {
            val modo = listMovti.get(position)
            val msg:String = modo.mtitle
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,msg)
            intent.type = "text/plain"
            context.startActivities(arrayOf(Intent.createChooser(intent,"Please select app: ")))
            Toast.makeText(context,modo.mtitle,Toast.LENGTH_SHORT).show()
        }

    }

}