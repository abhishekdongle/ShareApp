package com.shareapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.shareapp.R
import com.shareapp.base.BaseFragment
import com.shareapp.data.model.Post
import com.shareapp.di.component.FragmentComponent
import com.shareapp.ui.home.post.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    @Inject
    lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        var Tag: String = HomeFragment::class.simpleName.toString()

        @JvmStatic
        fun newInstance(): HomeFragment =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun getDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.refreshPosts.observe(this, Observer {
            it.data?.run {
                postAdapter.updateDataList(this)
                rvPosts.scrollToPosition(0)
            }
        })
    }

    override fun setupView(view: View) {
        viewModel.getDBList(databaseService) as ArrayList<Post>

        viewModel.refreshPosts.observe(this, Observer {
            it.data?.run {
                postAdapter.appendList(this as ArrayList<Post>)
            }
        })


        rvPosts.apply {
            layoutManager = linearLayoutManager
            adapter = postAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if (this is LinearLayoutManager
                            && itemCount > 0
                            && itemCount == findLastVisibleItemPosition() + 1
                        ) viewModel.onLoadMore()
                    }
                }
            })
        }
    }
}