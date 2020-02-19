package com.example.myproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.model.menu.MenuX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_newfeeds.view.*


class MenuListAdapter(var menuList:List<MenuX> = ArrayList()): RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuListAdapter.MenuViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.food_newfeeds,parent,false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
    fun updateList(menu:List<MenuX>){
        this.menuList = menu
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MenuListAdapter.MenuViewHolder, position: Int) {
        holder.bindMenu(menuList.get(position))
    }

    inner class MenuViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        private var view: View = itemView
        private lateinit var menuResult: MenuX

        fun bindMenu(menu: MenuX){
            this.menuResult = menu

            Picasso.get().load(menuResult.menu_photo)
                .placeholder(R.drawable.buger) ///tempo
                .into(view.post_img)

            view.food_name.text = menuResult.menu_name
            view.food_price.text = menuResult.menu_price
            view.deli_phone.text = menuResult.user_detail_id.phone_no
            view.deli_location.text = menuResult.user_detail_id.address
            Picasso.get().load(menuResult.user_detail_id.photo)
                .placeholder(R.drawable.useruser) ///tempo
                .into(view.profile_post_photo)

        }
    }

}