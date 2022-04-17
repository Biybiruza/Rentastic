package com.example.rentastic.ui.poster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentastic.R
import com.example.rentastic.data.Users
import com.example.rentastic.databinding.ItemViewBinding

class PosterListAdapter : RecyclerView.Adapter<PosterListAdapter.PosterListViewHolder> () {



    inner class PosterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemViewBinding.bind(itemView)
        fun populateModel(users: Users){

            binding.address.text = users.address
            binding.money.text = users.money
            itemView.setOnClickListener {
                onClick.invoke(users.imageUrl!!,users.address!!,users.money!!)
            }
        }
    }

    private var onClick:(imageUrl:String, address:String, money:String) -> Unit = { s1: String, s2: String, s3: String -> }

    fun setOnClickItemListener(onClick:(imageUrl:String, address:String, money:String)->Unit){
        this.onClick = onClick
    }

    var list = listOf<Users>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return PosterListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PosterListViewHolder, position: Int) {
        holder.populateModel(list[position])
    }

    override fun getItemCount(): Int = list.size
}