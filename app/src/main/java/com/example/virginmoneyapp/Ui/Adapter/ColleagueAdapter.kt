package com.example.virginmoneyapp.Ui.Adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.virginmoneyapp.databinding.ColleaguesListBinding
import com.example.virginmoneyapp.model.people.PeopleItem

class ColleagueAdapter (
    private val peopleList: MutableList<PeopleItem> = mutableListOf(),
    private val openColleaguesDetails: (PeopleItem) -> Unit
):RecyclerView.Adapter<ColleagueAdapter.PeopleListViewHolder>(){



    @SuppressLint("NotifyDataChanged")
    fun setPeopleList(newList: List<PeopleItem>, shouldUpdateList: Boolean){
        if (shouldUpdateList) {
            peopleList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        } else {
            peopleList.clear()
            peopleList.addAll(newList)
            // works like notifySetChanged, but without the warning
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class PeopleListViewHolder(
        private val binding: ColleaguesListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PeopleItem, openColleaguesDetails: (PeopleItem) -> Unit){
            binding.tvFirstName.text = item.firstName
            binding.tvLastName.text = item.lastName
            binding.tvJobTittle.text = item.jobtitle
            Glide.with(binding.ivUserLogo)
                .load(item.avatar)
                .into(binding.ivUserLogo)
            binding.btnDetails.setOnClickListener {
                openColleaguesDetails(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        return PeopleListViewHolder(
            ColleaguesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        holder.onBind(peopleList[position], openColleaguesDetails)
    }

    override fun getItemCount(): Int = peopleList.size


}