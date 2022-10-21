package com.example.shyariapp.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shyariapp.AllShyariActivity
import com.example.shyariapp.MainActivity
import com.example.shyariapp.databinding.ItemCatBinding
import com.example.shyariapp.model.CategoryModel

class CategoryAdapter(val mainActivity: MainActivity, val lists: ArrayList<CategoryModel>) :RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorsList = arrayListOf<String>("#FF3D3D","#FF9E3D","#3DFF7E","#BEFF3D","#FFFF3D")
     class CatViewHolder(val binding: ItemCatBinding):RecyclerView.ViewHolder(binding.root) {
         

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
       return CatViewHolder(
           ItemCatBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position% 5==0){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorsList[0]))
        }else if (position% 5==1){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorsList[1]))
        }else if (position% 5==2){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorsList[2]))
        }else if (position% 5==3){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorsList[3]))
        }else if (position% 5==4){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorsList[4]))
        }



        holder.binding.itemTxt.text =lists[position].name.toString()
        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity,AllShyariActivity::class.java)
            intent.putExtra("id",lists[position].id)
            intent.putExtra("name",lists[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount()=lists.size

}