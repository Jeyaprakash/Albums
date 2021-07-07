package com.cognizant.android.evaluation.instrumentation

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import  androidx.test.ext.junit.runners.AndroidJUnit4
import com.cognizant.android.evaluation.R
import com.cognizant.android.evaluation.view.AlbumsActivity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest

class AlbumActivityTesting{

    @Rule
    @JvmField
    var mActivityTestRule : ActivityTestRule<AlbumsActivity> = ActivityTestRule(AlbumsActivity::class.java, true, false)

    @Before
    fun setUp(){
        val intent = Intent()
        mActivityTestRule.launchActivity(intent)
    }
    @Test
    @Throws(Exception::class)
    fun ensureRecyclerViewIsPresent() {
        val activity: AlbumsActivity = mActivityTestRule.getActivity()
        val viewById: View = activity.findViewById(R.id.albums_list)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(RecyclerView::class.java))
        val listView: RecyclerView = viewById as RecyclerView
        val adapter = listView.adapter
        assertThat(adapter, instanceOf(RecyclerView.Adapter::class.java))

    }
}