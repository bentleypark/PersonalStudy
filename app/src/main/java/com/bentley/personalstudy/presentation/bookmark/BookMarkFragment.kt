package com.bentley.personalstudy.presentation.bookmark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bentley.personalstudy.R

class BookMarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookMarkFragment()
    }

    private lateinit var viewModel: BookMarkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_mark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookMarkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}