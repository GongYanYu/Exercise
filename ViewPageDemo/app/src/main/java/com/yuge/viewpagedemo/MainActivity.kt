package com.yuge.viewpagedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.adapter=object :FragmentStateAdapter(this){
            /*override fun getItemCount(): Int {//有几个页面
                return  3
            }*/
            override fun getItemCount()=3

           /* override fun createFragment(position: Int): Fragment {//哪个页面
                return when(position){
                    0->ScaleFragment()
                    1->RotateFragment()
                    else ->TranslateFragment()
                }
            }*/
           override fun createFragment(position: Int)=
               when(position){
                   0->ScaleFragment()
                   1->RotateFragment()
                   else ->TranslateFragment()
               }

        }
        //如何一个函数的最后一个参数是lambda表达式 可以把表达式放在外边
        TabLayoutMediator(tabLayout,viewPager2){tab, position ->
                when(position){
                    0->tab.text="缩放"
                    2->tab.text="移动"
                    else->tab.text="旋转"
                }
        }.attach()
    }
}
