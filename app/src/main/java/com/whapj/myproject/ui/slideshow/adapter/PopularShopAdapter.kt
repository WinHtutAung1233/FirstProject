package com.whapj.myproject.ui.slideshow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.whapj.myproject.R
import com.whapj.myproject.model.PopularShop

class PopularShopAdapter(val mCtx: Context, val layoutResId: Int, val popularList: List<PopularShop>) :
    ArrayAdapter<PopularShop>(mCtx, layoutResId, popularList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View =layoutInflater.inflate(layoutResId,null)

        val textShopName=view.findViewById<TextView>(R.id.textShopName)
        val textShopAddress=view.findViewById<TextView>(R.id.textShopAddress)

        val users=popularList[position]

        textShopName.text=users.name
        textShopAddress.text=users.address
        return view
    }
}