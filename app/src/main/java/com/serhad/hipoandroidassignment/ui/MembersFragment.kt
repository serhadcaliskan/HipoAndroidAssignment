package com.serhad.hipoandroidassignment.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.serhad.hipoandroidassignment.R
import com.serhad.hipoandroidassignment.data.Member
import com.serhad.hipoandroidassignment.databinding.FragmentMembersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_members.*

@AndroidEntryPoint
class MembersFragment : Fragment(R.layout.fragment_members) {

    private val viewModel by viewModels<MembersViewModel>()
    private lateinit var binding: FragmentMembersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMembersBinding.bind(view)
        val adapter = MembersAdapter(ArrayList())
        binding.apply {
            recyclerViewMembers.adapter = adapter
        }


        viewModel.dataFetch.observe(viewLifecycleOwner, Observer<List<Member>> {
            viewModel.updateMutableList(ArrayList<Member>(it))
        })

        viewModel.membersData.observe(viewLifecycleOwner, Observer<ArrayList<Member>> {

            binding.apply {
                recyclerViewMembers.adapter = MembersAdapter(ArrayList(it))
                (recyclerViewMembers.adapter as MembersAdapter).applyFilter(binding.searchMemberText.text)
            }
        })

        binding.addUser.setOnClickListener {

            viewModel.addUser()

        }
        binding.searchMemberText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                (recyclerViewMembers.adapter as MembersAdapter).applyFilter(s)

            }
        })
    }
}