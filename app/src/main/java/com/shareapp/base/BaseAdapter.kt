package com.shareapp.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//
// Created by Abhishek.dongle on 01-Jun-20.
//
abstract class BaseAdapter<T : Any, VH : BaseItemViewHolder<T, out BaseItemViewModel<T>>>(
    parentLifecycle: Lifecycle, val dataList: ArrayList<T>
) : RecyclerView.Adapter<VH>() {

    var recyclerView: RecyclerView? = null

    init {
        parentLifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onParentStop() {
                recyclerView?.run {
                    for (i in 0..childCount) {
                        getChildAt(i)?.let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).onStop()
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onParentDestroy() {
                recyclerView?.run {
                    for (i in 0..childCount) {
                        getChildAt(i).let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).run {
                                onDestroy()
                            }
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onParentStart() {
                recyclerView?.run {
                    if (layoutManager is LinearLayoutManager) {
                        var first =
                            (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        var last =
                            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (first in 0..last)
                            for (i in first..last) {
                                findViewHolderForAdapterPosition(i).let {
                                    (it as BaseItemViewHolder<*, *>).onStart()
                                }
                            }
                    }
                }
            }
        })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onStop()
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList.get(position))
    }

    fun appendList(dataList: ArrayList<T>) {
        var oldCount = itemCount
        this.dataList.addAll(dataList)
        var currentCount = itemCount
        if (oldCount == 0 && currentCount > 0) notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    fun updateDataList(list: List<T>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}