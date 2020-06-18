package com.whapj.myproject.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.whapj.myproject.R
import com.whapj.myproject.model.PopularShop
import com.whapj.myproject.ui.slideshow.adapter.PopularShopAdapter
import com.google.firebase.database.*

class SlideshowFragment : Fragment() {

    //For Show
    lateinit var listView: ListView
    lateinit var popularList: MutableList<PopularShop>
    lateinit var ref: DatabaseReference

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        //For Show
        listView=root.findViewById(R.id.listView)
        //
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //For Show
        popularList= mutableListOf()
        ///
        ref = FirebaseDatabase.getInstance().getReference("Popular")

        //For Show
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    popularList.clear()

                    for (u in p0.children){
                        val users=u.getValue(PopularShop::class.java)
                        popularList.add(users!!)
                    }

                    val adapter=PopularShopAdapter(context!!,R.layout.news_layout,popularList)
                    listView.adapter=adapter


                }
            }

        })
    }
}