package com.yuge.gallery

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.swipeIndicator -> {
                swipeLayoutGallery.isRefreshing=true
                Handler().postDelayed({galleryViewModel.resetQuery()},1000)//set Delay
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)//setHasOptionsMenu(true)
        val galleryAdapter=GalleryAdapter()
        recycleView.apply {
            adapter=galleryAdapter
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }

        galleryViewModel=ViewModelProvider
            .AndroidViewModelFactory(requireActivity().application).create(GalleryViewModel::class.java)

        galleryViewModel.photoListLive.observe(viewLifecycleOwner, Observer {
            if(galleryViewModel.needToScrollTop){
                recycleView.scrollToPosition(0)
                galleryViewModel.needToScrollTop=false
            }
            galleryAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing=false
        })
        galleryViewModel.dataStateLive.observe(viewLifecycleOwner, Observer {
            galleryAdapter.footViewState=it
            if(it== DATA_NET_WORK_ERROR) swipeLayoutGallery.isRefreshing=false
        })

        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)//dy<0   上页内容
                if(dy<0) return
                val layoutManager=recyclerView.layoutManager as StaggeredGridLayoutManager
                val intArray=IntArray(2)
                layoutManager.findLastVisibleItemPositions(intArray)
                if(intArray[0]==galleryAdapter.itemCount-1){//判断是不是页脚
                    galleryViewModel.fetchData()
                }
            }
        })
    }
}
