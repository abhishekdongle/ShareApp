package com.shareapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.shareapp.R
import com.shareapp.base.BaseActivity
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.data.model.Post
import com.shareapp.di.component.ActivityComponent
import com.shareapp.ui.addPhotos.AddPhotosFragment
import com.shareapp.ui.home.HomeFragment
import com.shareapp.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var databaseService: DatabaseService

    private var activeFragment: Fragment? = null

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun getDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        viewModel.onHomeSelected()
        viewModel.doDBOperations(databaseService)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        bottomNavigation.run {
            itemIconTintList = null
            viewModel.onHomeSelected()

            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemHome -> {
                        true
                    }
                    R.id.itemAddPhotos -> {
                        viewModel.onPhotoSelected()
                        true
                    }
                    R.id.itemProfile -> {
                        viewModel.getDBList(databaseService)
                        viewModel.onProfileSelected()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.homeNavigation.observe(this, Observer {
            it.getIsNotHandled()?.run { showHomeFragment() }
        })

        viewModel.photoNavigation.observe(this, Observer {
            it.getIsNotHandled()?.run { showPhotosFragment() }
        })

        viewModel.profileNavigation.observe(this, Observer {
            it.getIsNotHandled()?.run { showProfileFragment() }
        })
    }

    private fun showProfileFragment() {
        if (activeFragment is ProfileFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment =
            supportFragmentManager.findFragmentByTag(ProfileFragment.Tag) as ProfileFragment?

        if (fragment == null) {
            fragment = ProfileFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, ProfileFragment.Tag)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()
        activeFragment = fragment
    }

    private fun showHomeFragment() {
        if (activeFragment is HomeFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.Tag) as HomeFragment?

        if (fragment == null) {
            fragment = HomeFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, HomeFragment.Tag)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()
        activeFragment = fragment
    }

    private fun showPhotosFragment() {
        if (activeFragment is AddPhotosFragment) return

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment =
            supportFragmentManager.findFragmentByTag(AddPhotosFragment.Tag) as AddPhotosFragment?

        if (fragment == null) {
            fragment = AddPhotosFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, AddPhotosFragment.Tag)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()
        activeFragment = fragment
    }
}