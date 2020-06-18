package com.whapj.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whapj.myproject.R
import com.whapj.myproject.model.NewFeed

class NewfeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var postImg = itemView.findViewById<ImageView>(R.id.post_img)
    var profilePostPhoto = itemView.findViewById<ImageView>(R.id.profile_post_photo)
    var foodType = itemView.findViewById<TextView>(R.id.food_type)
    var deliAddress = itemView.findViewById<TextView>(R.id.deli_location)
    var foodPrice = itemView.findViewById<TextView>(R.id.food_price)
    var deliPhone = itemView.findViewById<TextView>(R.id.deli_phone)
    var btnPhone = itemView.findViewById<Button>(R.id.btn_call)
}

class NewfeedAdapter(var newfeedList: ArrayList<NewFeed>) :
    RecyclerView.Adapter<NewfeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewfeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_newfeeds, parent, false) //always parent,false
        return NewfeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newfeedList.size
    }

    override fun onBindViewHolder(holder: NewfeedViewHolder, position: Int) {
        holder.postImg.setImageResource(newfeedList[position].postimg)
        holder.profilePostPhoto.setImageResource(newfeedList[position].profile)
        holder.foodType.text = newfeedList[position].foodtype
        holder.deliAddress.text = newfeedList[position].deliaddress
        holder.foodPrice.text = newfeedList[position].foodprice.toString()
        holder.deliPhone.text = newfeedList[position].deliphone.toString()
        holder.btnPhone.setOnClickListener {
            var phone: String = holder.deliPhone.text.toString()

//                val call = Intent(Intent.ACTION_CALL)
//                call.data = Uri.parse("tel:" + phone)
//                startActivity(call)
//            Toast.makeText(MainActivity, "$phone", Toast.LENGTH_LONG).show()

        }
    }

}