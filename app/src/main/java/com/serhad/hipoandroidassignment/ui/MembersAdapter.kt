package com.serhad.hipoandroidassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serhad.hipoandroidassignment.R
import com.serhad.hipoandroidassignment.data.Member
import java.util.*
import kotlin.collections.ArrayList

class MembersAdapter(var membersList: ArrayList<Member>) :

        RecyclerView.Adapter<MembersAdapter.memberViewHolder>(), Filterable {

    var workerFilterList: ArrayList<Member>

    init {
        workerFilterList = ArrayList<Member>(membersList)
    }

    class memberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.memberNameText)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, loadState: Int): memberViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_member, viewGroup, false)

        return memberViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: memberViewHolder, position: Int) {


        viewHolder.textView.text = workerFilterList.get(position).name
    }


    override fun getItemCount() = workerFilterList.size;


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint != null) {
                    val charSearch = constraint.toString()

                    if (charSearch.isEmpty()) {
                        workerFilterList = ArrayList<Member>(membersList)
                    } else {
                        val resultList = ArrayList<Member>()
                        for (member in membersList) {
                            if (member.name.toLowerCase(Locale.ROOT)
                                            .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(member)
                            }
                        }
                        workerFilterList = resultList
                    }
                } else {
                    workerFilterList = membersList
                }

                val filterResults = FilterResults()
                filterResults.values = workerFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                workerFilterList = results?.values as ArrayList<Member>
                notifyDataSetChanged()
            }
        }
    }

    fun applyFilter(charSequence: CharSequence?) {
        filter.filter(charSequence)
    }

}