package com.yuge.gallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.gallery_cell.view.*

class GalleryAdapter: ListAdapter<PhotoItem, MyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder=MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell,parent,false))
        holder.itemView.setOnClickListener{
            Bundle().apply {
                putParcelableArrayList("photo_list",ArrayList(currentList))//传列表
                putInt("current_photo",holder.bindingAdapterPosition)//bindingAdapterPosition is  current position
                holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_pagerPhotoFragment,this)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photoItem = getItem(position)
        with(holder.itemView) {
            shimmerLayoutCell.apply {
                setShimmerColor(0X55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            textViewUser.text=photoItem.photoUser
            textViewLike.text=photoItem.photoLikes.toString()
            textViewFavorites.text=photoItem.photoFavorites.toString()

            imageView.layoutParams.height = photoItem.photoHeight

            Glide.with(this)
                .load(getItem(position).previewUrl)
                .placeholder(R.drawable.photo_placeholder)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false.also { shimmerLayoutCell?.stopShimmerAnimation() }//未加载完离开下一次可能为空
                    }

                })
                .into(imageView)
        }
    }
    object DiffCallback:DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem===newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId==newItem.photoId
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)