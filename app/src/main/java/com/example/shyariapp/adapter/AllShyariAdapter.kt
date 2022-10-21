package com.example.shyariapp.adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shyariapp.AllShyariActivity
import com.example.shyariapp.BuildConfig
import com.example.shyariapp.databinding.ItemsShyariBinding
import com.example.shyariapp.model.ShyariModel

class AllShyariAdapter(val allShyariActivity: AllShyariActivity,val shyariList:ArrayList<ShyariModel>)
    :RecyclerView.Adapter<AllShyariAdapter.ShyariViewHolder>() {

    val colorsList = arrayListOf<String>("#FF3D3D","#FF9E3D","#3DFF7E","#BEFF3D","#FFFF3D")
    class ShyariViewHolder(val binding: ItemsShyariBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShyariViewHolder {
        return ShyariViewHolder(ItemsShyariBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ShyariViewHolder, position: Int) {

        if (position% 5==0){
            holder.binding.itemShyaris.setBackgroundColor(Color.parseColor(colorsList[0]))
        }else if (position% 5==1){
            holder.binding.itemShyaris.setBackgroundColor(Color.parseColor(colorsList[1]))
        }else if (position% 5==2){
            holder.binding.itemShyaris.setBackgroundColor(Color.parseColor(colorsList[2]))
        }else if (position% 5==3){
            holder.binding.itemShyaris.setBackgroundColor(Color.parseColor(colorsList[3]))
        }else if (position% 5==4){
            holder.binding.itemShyaris.setBackgroundColor(Color.parseColor(colorsList[4]))
        }

       holder.binding.itemShyaris.text = shyariList[position].data.toString()
        
         holder.binding.btnCopy.setOnClickListener {

            val clipboard:android.content.ClipboardManager? = allShyariActivity.getSystemService(
                CLIPBOARD_SERVICE) as ClipboardManager?
            val clipData = ClipData.newPlainText("lable", shyariList[position].data)

            clipboard?.setPrimaryClip(clipData)
             Toast.makeText(allShyariActivity,"Text Copied",Toast.LENGTH_SHORT).show()
        }



        holder.binding.btnWhatsapp.setOnClickListener {

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT,  shyariList[position].data)
            try {
                allShyariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(allShyariActivity,"Whatsapp have not been installed.",Toast.LENGTH_SHORT).show()
            }
        }
        holder.binding.btnShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n ${shyariList[position].data} \n\n"
                shareMessage =
                    """
                        ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                        
                        
                        """.trimIndent()
                 shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    allShyariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }
    }

    override fun getItemCount() =shyariList.size
}