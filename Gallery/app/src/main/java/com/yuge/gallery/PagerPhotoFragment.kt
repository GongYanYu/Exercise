package com.yuge.gallery

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.RequestQueue
import kotlinx.android.synthetic.main.fragment_pager_photo.*
import kotlinx.android.synthetic.main.pager_photo_view.view.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
const val Request_Write_External_Storage = 1

class PagerPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val photoList = arguments?.getParcelableArrayList<PhotoItem>("photo_list")
        PagerPhotoListAdapter().apply {
            viewPager2.adapter = this
            submitList(photoList)
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //photoTag.text="${position+1}/${photoList?.size}"
                photoTag.text = getString(R.string.photo_tag, position + 1, photoList?.size)
            }
        })
        arguments?.getInt("current_photo")?.let { viewPager2.setCurrentItem(it, false) }

        //垂直滚动
        viewPager2.orientation=ViewPager2.ORIENTATION_VERTICAL

        saveButton.setOnClickListener {
            if (Build.VERSION.SDK_INT < 29 && ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    Request_Write_External_Storage
                )
            } else {
                viewLifecycleOwner.lifecycleScope.launch{//主线程关掉 副线程也挂掉
                    savePhoto()
                }
            }
        }
    }

    /**
     * 保存图片
     */
    private suspend fun savePhoto() {//副线程
        withContext(Dispatchers.IO){
            val holder =
                (viewPager2[0] as RecyclerView).findViewHolderForAdapterPosition(viewPager2.currentItem)
                        as PagerPhotoViewHolder
            var bitmap=holder.itemView.pagerPhoto.drawable.toBitmap()

            //delay(5000)//副线程可用 不应该阻碍主线程
            //29 之前
            /*if(MediaStore.Images.Media.insertImage(requireContext().contentResolver,bitmap,"","")==null){
                Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "存储成功", Toast.LENGTH_SHORT).show()
            }*/
            /**
             * 以下方法29以下都可用
             */
            val saveUri=requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues()
            )?: kotlin.run {
                //Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()//不允许在副线程运行 必须在UI主线程里
                MainScope().launch { Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show() }
                return@withContext
            }
            requireContext().contentResolver.openOutputStream(saveUri).use { //use 自动关闭 不用手动关闭
                if(bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)){
                    //不允许在副线程运行 必须在UI主线程里
                    MainScope().launch {Toast.makeText(requireContext(), "存储成功", Toast.LENGTH_SHORT).show()}
                }else{
                    //不允许在副线程运行 必须在UI主线程里
                    MainScope().launch {Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()}
                }
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Request_Write_External_Storage -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewLifecycleOwner.lifecycleScope.launch{//主线程关掉 副线程也挂掉
                        savePhoto()
                    }
                } else {
                    Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
