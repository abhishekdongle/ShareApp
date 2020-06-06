package com.shareapp.ui.main

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shareapp.base.BaseViewModel
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.data.local.db.entity.DummyEntity
import com.shareapp.utils.common.Event
import com.shareapp.utils.network.NetworkHelper
import javax.inject.Inject

class MainViewModel(val networkHelper: NetworkHelper) : BaseViewModel(networkHelper) {

    val profileNavigation = MutableLiveData<Event<Boolean>>()
    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val photoNavigation = MutableLiveData<Event<Boolean>>()

//    override fun onCreate() {
//        TODO("Not yet implemented")
//    }

    fun onProfileSelected() {
        profileNavigation.postValue(Event(true))
    }

    fun onHomeSelected() {
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoSelected() {
        photoNavigation.postValue(Event(true))
    }


    fun doDBOperations(databaseService: DatabaseService) {
        class DBAsync1 : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String {
                var list: List<DummyEntity> = databaseService.dummyDao().getDummyList()
                list.let {
                    if (list.isEmpty()) {
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                1,
                                "Robert Downey",
                                "Houton",
                                "I am IRON MAN",
                                "https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                2,
                                "Chris Evan",
                                "Dallas Texas",
                                "Captain America",
                                "https://b1.pngbarn.com/png/267/48/robert-downey-jr-png-clip-art.png"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                3,
                                "Scarlett Johanson",
                                "Newyork City",
                                "Here's the Black Widow",
                                "https://upload.wikimedia.org/wikipedia/commons/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                4,
                                "Chris Hemsworth",
                                "Asgardian",
                                "Where's my Hammer",
                                "https://toppng.com/uploads/preview/robertdowneyjr-sticker-png-transparent-star-actor-robert-downey-jr-11563246849td2bwrw23w.png"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                5,
                                "Peter Dinklage",
                                "Throne House",
                                "Who will be the KING OF THE IRON THRONE",
                                "https://w0.pngwave.com/png/1011/450/iron-man-hollywood-robert-downey-jr-actor-desktop-robert-downey-jr-png-clip-art.png"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                6,
                                "Tom Cruze",
                                "Seven Height",
                                "Adventure is my passion",
                                "https://www.freepngimg.com/thumb/robert_downey_jr/21196-9-robert-downey-jr-file.png"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                7,
                                "Gal Gadot",
                                "Heaven",
                                "Me Wonder Women",
                                "https://i.dlpng.com/static/png/6762342_preview.png"
                            )
                        )
                        databaseService.dummyDao().insertData(
                            DummyEntity(
                                8,
                                "Will Smith",
                                "Dark Place",
                                "Men In Black but with Bright Heart",
                                "https://toppng.com/uploads/preview/robert-downey-jr-11550712149gwdlhi1fgx.png"
                            )
                        )

                        Log.v("databaseService", "sucess")
                    }
                }

                return ""
            }
        }
        DBAsync1().execute()
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