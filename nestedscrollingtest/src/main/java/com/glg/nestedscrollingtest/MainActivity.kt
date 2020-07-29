package com.glg.nestedscrollingtest

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    var fruitList = ArrayList<Fruit>()
//    var distanceX1 = 0F
//    var distanceX2 = 0F

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        initFruit()
        var recyclerView = findViewById(R.id.photo) as RecyclerView
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        var fruitAdapter = FruitAdapter(fruitList)
        recyclerView.adapter = fruitAdapter
        recyclerView.isNestedScrollingEnabled = false

        //findViewById<JudgeNestedScrollView>(R.id.nestedScrollView).setNeedScroll(false)

        //recyclerView.isNestedScrollingEnabled = false

//        var nestedScrollView = findViewById(R.id.nestedScrollView) as NestedScrollView
//        val location = IntArray(2)
//        nestedScrollView.getLocationOnScreen(location)
//        Toast.makeText(this, "" + location[1], Toast.LENGTH_SHORT).show()

//        nestedScrollView.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if(event!=null && event.action != null && event.action == MotionEvent.ACTION_DOWN) {
//                    distanceX1 = event.getX()
//                } else if (event!=null && event.action != null && event.action == MotionEvent.ACTION_UP) {
//                    distanceX2 = event.getX()
//                    if(distanceX2 - distanceX1 > 100) {
//                        Toast.makeText(applicationContext, "向右", Toast.LENGTH_LONG).show()
//                        distanceX2 = 0F
//                        distanceX1 = 0F
//                    } else if (distanceX1 - distanceX2 > 100) {
//                        Toast.makeText(applicationContext, "向左", Toast.LENGTH_LONG).show()
//                        distanceX2 = 0F
//                        distanceX1 = 0F
//                    }
//                }
//                return false
//            }
//        })

//        var layoutInflater = LayoutInflater.from(this)
//        var view = layoutInflater.inflate(R.layout.layout1, null)
//        var view1 = layoutInflater.inflate(R.layout.layout2, null)
//        var view2 = layoutInflater.inflate(R.layout.layout3, null)
//        var view3 = layoutInflater.inflate(R.layout.layout4, null)
//        var view4 = layoutInflater.inflate(R.layout.layout5, null)


        var viewPager = findViewById(R.id.viewPager) as ViewPager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewPager.isNestedScrollingEnabled = false
        }
        //viewPager.offscreenPageLimit = 5
        var windowManager = windowManager
        var display = windowManager.defaultDisplay
        var height1 = display.height
        (viewPager as MyViewPager).setHeight1(height1)
        var list = ArrayList<View> ()
//        list.add(view)
//        list.add(view1)
//        list.add(view2)
//        list.add(view3)
//        list.add(view4)
        var tablayout = findViewById(R.id.tab) as TabLayout
        var height2 = tablayout.height
        (viewPager as MyViewPager).setHeight2(height2)
        Toast.makeText(this, "" + tablayout.height, Toast.LENGTH_LONG).show()
        //tablayout.setSelectedTabIndicator(0)

        list = initView(list) as ArrayList<View>
        var viewPagerAdapter = ViewPagerAdapter(list)
        viewPager.adapter = viewPagerAdapter
        //viewPager.currentItem = 0

//        tablayout.addTab(tablayout.newTab().setText("Tab1"))
//        tablayout.addTab(tablayout.newTab().setText("Tab2"))
//        tablayout.addTab(tablayout.newTab().setText("Tab3"))
//        tablayout.addTab(tablayout.newTab().setText("Tab4"))
//        tablayout.addTab(tablayout.newTab().setText("Tab5"))

        /*var listTab = arrayListOf<String>()
        listTab.add("Tab1")
        listTab.add("Tab2")
        listTab.add("Tab3")
        listTab.add("Tab4")
        listTab.add("Tab5")
        viewPagerAdapter.setmListTitle(listTab)
        tablayout.setupWithViewPager(viewPager)*/

//        var fragmentList = ArrayList<Fragment>()
//        fragmentList.add(FirstFragment())
//        fragmentList.add(TwoFragment())
//        fragmentList.add(ThreeFragment())
//        fragmentList.add(FourFragment())
//        fragmentList.add(FiveFragment())
//        var firstFragmentPagerAdapter = FirstFragmentPagerAdapter(supportFragmentManager, fragmentList)
//        viewPager.adapter = firstFragmentPagerAdapter

        tablayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager.currentItem = tablayout.selectedTabPosition
            }
        })
        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                return
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                return
            }

            override fun onPageSelected(position: Int) {
                tablayout.getTabAt(position)?.select()
                //findViewById<JudgeNestedScrollView>(R.id.nestedScrollView).scrollTo(0,0)
                //tablayout.selectedTabPosition = viewPager.currentItem
            }
        })

//        var listView = findViewById(R.id.photo) as ListView
//        var photoAdapter = PhotoAdapter(this, R.layout.photo_layout, fruitList)
//        listView.adapter = photoAdapter
    }

    fun initFruit() {
        for(i in 0..3) {
            var apple = Fruit("apple", R.drawable.apple_pic)
            fruitList.add(apple)
            var orange = Fruit("orange", R.drawable.orange_pic)
            fruitList.add(orange)
            var banana = Fruit("banana", R.drawable.banana_pic)
            fruitList.add(banana)
            var grape = Fruit("grape", R.drawable.grape_pic)
            fruitList.add(grape)
            var watermelon = Fruit("watermelon", R.drawable.watermelon_pic)
            fruitList.add(watermelon)
        }
    }

    fun initView(list : ArrayList<View>) : List<View> {
        var items = arrayOf("全部", "电视", "电影", "新闻")
        var listView = ListView(this)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        //setListViewHeightBasedOnChildren(listView)
        list.add(listView)
        items = arrayOf("美术", "体育", "音乐", "历史", "语文", "数学", "英语", "物理", "化学", "生物", "政治","自然","地理","科学", "化学", "生物", "政治","自然","地理","科学")
        var listView1 = ListView(this)
        var adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView1.adapter = adapter1
        //setListViewHeightBasedOnChildren(listView)
        list.add(listView1)
        var imageView = ImageView(this)
        imageView.setImageResource(R.drawable.orange_pic)
        list.add(imageView)
        var imageView1 = ImageView(this)
        imageView1.setImageResource(R.drawable.watermelon_pic)
        list.add(imageView1)
        var imageView2 = ImageView(this)
        imageView2.setImageResource(R.drawable.banana_pic)
        list.add(imageView2)
        return list
    }

    fun setListViewHeightBasedOnChildren(listView : ListView) {
        var listAdapter = listView.adapter
        if(listAdapter == null) {
            return
        }
        var totalHeight = 0
        for(i in 0 until listAdapter.count) {
            var listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        var params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))

        //(params as ViewGroup.MarginLayoutParams).setMargins(10, 10, 10, 10)

        listView.layoutParams = params
    }
}
