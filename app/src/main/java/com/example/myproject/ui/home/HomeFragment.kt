package com.example.myproject.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.adapter.NewfeedAdapter
import com.example.myproject.model.NewFeed
import com.example.myproject.model.menu.Menu
import com.example.myproject.ui.adapter.MenuListAdapter
import kotlinx.android.synthetic.main.food_newfeeds.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var menuListAdapter: MenuListAdapter
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var btnPost=root.findViewById<Button>(R.id.btn_post)
        btnPost.setOnClickListener { view:View->
            view.findNavController().navigate(R.id.action_nav_home_to_postFragment)
        }


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(activity) // can use context instead of activity
        menuListAdapter = MenuListAdapter()
        food_newfeed.adapter = menuListAdapter
        food_newfeed.layoutManager = viewManager
        observeViewModel()
    }
    fun observeViewModel(){
        homeViewModel = ViewModelProviders
            .of(this)/*lifecycler owner*/
            .get(homeViewModel::class.java)

        homeViewModel.getResults().observe(
            viewLifecycleOwner, Observer <Menu>{ result ->
                food_newfeed.visibility = View.VISIBLE
                menuListAdapter.updateList(result.menus)//ArticlesResult.articles
            }
        )

        homeViewModel.getError().observe(
            viewLifecycleOwner, Observer <Boolean>{isError ->
                if(isError){
                    txt_error.visibility = View.VISIBLE
                    food_newfeed.visibility = View.GONE
                }else{
                    txt_error.visibility = View.GONE
                }
            }
        )

        homeViewModel.getLoading().observe(
            viewLifecycleOwner, Observer <Boolean>{ isLoading ->
                loadingView.visibility=if (isLoading)View.VISIBLE else View.INVISIBLE

                if(isLoading){
                    txt_error.visibility = View.GONE
                    food_newfeed.visibility = View.GONE
                }
            }
        )
    }
    override fun onResume() {
        super.onResume()
        homeViewModel.loadResults()
    }

}