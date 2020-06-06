package com.shareapp.ui.home

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shareapp.base.BaseViewModel
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.data.local.db.entity.DummyEntity
import com.shareapp.data.model.Post
import com.shareapp.ui.main.MainViewModel
import com.shareapp.utils.common.Resource
import com.shareapp.utils.network.NetworkHelper
import javax.inject.Inject

class HomeViewModel(
    val networkHelper: NetworkHelper,
    private val allPostList: ArrayList<Post>
) : BaseViewModel(networkHelper) {

    @Inject
    lateinit var databaseService: DatabaseService

    val refreshPosts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

//    override fun onCreate() {
//        TODO("Not yet implemented")
//    }

    fun onLoadMore() {
//        if (loading.value !== null && loading.value == false) loadMorePosts()
    }

    fun onNewPost(dummy: List<DummyEntity>) {//post: Post
//        allPostList.add(0, post)
        var temp = ArrayList<Post>()
        for (i in 0..dummy.size - 1) {
            temp.add(
                Post(
                    dummy.get(i).id, dummy.get(i).name,
                    dummy.get(i).location, dummy.get(i).caption,
                    dummy.get(i).imageUrl, false
                )
            )
        }
        refreshPosts.postValue(Resource.success(mutableListOf<Post>().apply { addAll(temp) })) //allPostList
    }

    fun getDBList(databaseService: DatabaseService): ArrayList<DummyEntity> {
        var dataList = ArrayList<DummyEntity>()

        class DBAsync2 : AsyncTask<Void, Void, List<DummyEntity>>() {
            override fun doInBackground(vararg params: Void?): List<DummyEntity> {
                var list: List<DummyEntity> = databaseService.dummyDao().getDummyList()

                return list
            }

            override fun onPostExecute(result: List<DummyEntity>?) {
                super.onPostExecute(result)
                result.let {
                    if (result!!.size > 0) {
                        dataList.addAll(result)
                        onNewPost(result)
                        for (i in 0..result!!.size - 1) {
                            Log.v(
                                "databaseService",
                                result?.get(i)?.name + "   " + result?.get(i)?.id
                            )
                        }
                    }
                }
            }
        }
        DBAsync2().execute()

        return dataList
    }
}