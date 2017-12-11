package br.gbizotto.pomodoro.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.history.view.HistoryFragment
import br.gbizotto.pomodoro.timer.view.TimerFragment
import butterknife.BindView
import butterknife.ButterKnife
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout
    @BindView(R.id.viewpager)
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setupViewPager(viewPager)
        setupTabLayout()

        Realm.init(this)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(TimerFragment(), getString(R.string.new_tab))
        adapter.addFragment(HistoryFragment(), getString(R.string.history_tab))
        viewPager.adapter = adapter
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }
}
