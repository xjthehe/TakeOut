# TakeOut
## 1.1项目介绍
这个项目就是一个外卖项目
## 1.2整体布局
<?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/ll_main_activity"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <FrameLayout
            android:id="@+id/main_fragment_container"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1" />

        <LinearLayout
            android:id="@+id/main_bottome_switcher_container"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:orientation="horizontal">
            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="30dp"
                    android:src="@drawable/main_home_selector" />

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="20dp"
                    android:layout_gravity="bottom"
                    android:gravity="center"
                    android:text="首页"
                    android:textColor="@color/main_bottom_tv_color" />

            </FrameLayout>
            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="30dp"
                    android:src="@drawable/main_order_selector" />

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="20dp"
                    android:layout_gravity="bottom"
                    android:gravity="center"
                    android:text="订单"
                    android:textColor="@color/main_bottom_tv_color" />
            </FrameLayout>
            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="30dp"
                    android:src="@drawable/main_me_selector" />

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="20dp"
                    android:layout_gravity="bottom"
                    android:gravity="center"
                    android:text="个人"
                    android:textColor="@color/main_bottom_tv_color" />
            </FrameLayout>
            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="30dp"
                    android:src="@drawable/main_more_selector" />

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="20dp"
                    android:layout_gravity="bottom"
                    android:gravity="center"
                    android:text="更多"
                    android:textColor="@color/main_bottom_tv_color" />
            </FrameLayout>
        </LinearLayout>
    </LinearLayout>

## 1.3底部选项
private fun initBottomBar() {
        for (i in 0 until main_bottome_switcher_container.childCount) {
            main_bottome_switcher_container.getChildAt(i).setOnClickListener { view ->
                changeIndex(i)
            }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until main_bottome_switcher_container.childCount) {
            //4个framelayout
            val child = main_bottome_switcher_container.getChildAt(i)
            if (i == index) {
                //选中禁用
                setEnable(child, false)
            } else {
                setEnable(child, true)
            }
        }

        //切换fragment
        val beginTransaction = supportFragmentManager.beginTransaction()
        FragmentFactory.instance.getInstance(index)?.let { beginTransaction.replace(R.id.main_fragment_container, it) }
        beginTransaction.commit()
    }

    //默认enable选择true 所有点击时候，控制为false
    private fun setEnable(child: View, isEnabled: Boolean) {
        child.isEnabled = isEnabled
        var temp = child
        if (temp is ViewGroup) {
            //image textview
            for (i in 0 until temp.childCount) {
                temp.getChildAt(i).isEnabled = isEnabled
            }
        }
    }

## 1.4fragment切换
        //切换fragment
        val beginTransaction = supportFragmentManager.beginTransaction()
        FragmentFactory.instance.getInstance(index)?.let { beginTransaction.replace(R.id.main_fragment_container, it) }
        beginTransaction.commit()

## 1.5沉浸式状态栏以及虚拟按键处理
    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
            <!-- Customize your theme here. -->
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>

            <!--状态栏半透明 底部虚拟按键导航-->
            <item name="android:windowTranslucentStatus">false</item>
            <item name="android:windowTranslucentNavigation">true</item>
            <!--状态栏全透明 实现沉浸式状态栏-->
            <item name="android:statusBarColor">@android:color/transparent</item>
        </style>
    </resources>


        //判断是否有虚拟按键
        //获取是否存在NavigationBar
        if(checkDeviceHasNavigationBar(this)){
            //dp转换为px
          ll_main_activity.setPadding(0,0,0, 50.dip2px(this))
        }

## 1.6扩展函数使用
    fun Int.dip2px(context:Context):Int{
        val scale = context.getResources().getDisplayMetrics().density;
        return(toFloat() * scale + 0.5f)as Int;
    }

## 1.7列表视图实现
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:background="#fff"
              android:orientation="horizontal">

    <FrameLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/seller_logo"
            android:layout_width="90dp"
            android:layout_height="90dp"
            android:layout_margin="8dp"
            android:src="@drawable/item_kfc"/>

        <TextView
            android:id="@+id/tvCount"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:background="@drawable/circle_red"
            android:gravity="center"
            android:text="1"
            android:textColor="#fff"
            android:textSize="12sp"
            android:visibility="gone"
            />

    </FrameLayout>


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="8dp"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:background="@drawable/shape_background_solid"
                android:paddingLeft="4dp"
                android:paddingRight="4dp"
                android:text="品牌"
                android:textColor="@android:color/black"/>

            <TextView
                android:id="@+id/tv_title"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="5dp"
                android:layout_weight="1"
                android:singleLine="true"
                android:text="肯德基宅急送（文化路店）"
                android:textColor="#000"
                android:textSize="24sp"/>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:background="@drawable/shape_background_stroke"
                android:paddingLeft="2dp"
                android:paddingRight="2dp"
                android:text="票"/>

        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="5dp"
            android:layout_marginTop="5dp"
            android:gravity="center_vertical">

            <RatingBar
                android:id="@+id/ratingBar"
                style="?android:attr/ratingBarStyleSmall"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:rating="2.8"/>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="5dp"
                android:layout_marginRight="5dp"
                android:text="2.8"
                android:textColor="#FFDE3D"
                android:textSize="12sp"/>

            <TextView
                android:id="@+id/tv_home_sale"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="月售243单"
                android:textSize="12sp"/>

        </LinearLayout>

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_vertical">

            <TextView
                android:id="@+id/tv_home_send_price"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:text="￥0起送/配送费￥9"
                android:textSize="12sp"/>

            <TextView
                android:id="@+id/tv_home_distance"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:text="556米/33分钟"
                android:textSize="12sp"/>

        </RelativeLayout>

        <View
            android:layout_width="match_parent"
            android:layout_height="2dp"
            android:layout_marginBottom="5dp"
            android:layout_marginTop="5dp"
            android:background="@drawable/shape_background_division"/>


        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="5dp"
            android:layout_marginTop="5dp"
            android:gravity="center_vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:background="@drawable/shape_background_solid"
                android:paddingLeft="2dp"
                android:paddingRight="2dp"
                android:text="减"
                android:textColor="#fff"/>

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="在线支付满49元减10元，满89元减16"
                android:textSize="12sp"/>

        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="5dp"
            android:layout_marginTop="5dp"
            android:gravity="center_vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:background="@drawable/shape_background_solid"
                android:paddingLeft="2dp"
                android:paddingRight="2dp"
                android:text="赠"
                android:textColor="#fff"/>

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="满45元赠九珍果汁饮料"
                android:textSize="12sp"/>

        </LinearLayout>

    </LinearLayout>


</LinearLayout>


## 1.8多类型条目支持
 class HomeListAdapter(val context:Context,val mdatas:ArrayList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

     companion object {
         val ITEM_TYPE_HEAD_VIEW=0
         val ITEM_TYPE_ITEM_VIEW=1
     }

     override fun getItemViewType(position: Int): Int {
         if(position==0){
             return ITEM_TYPE_HEAD_VIEW
         }else{
             return ITEM_TYPE_ITEM_VIEW
         }
     }

     override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         if(viewType==ITEM_TYPE_HEAD_VIEW){
             return HomeHeadViewHolder(HomeHeadView(context))
         }else {
             return HomeItemViewHolder(HomeListItemView(context))
         }
     }



    override fun getItemCount(): Int {
        if(mdatas.size>0){
           return mdatas.size+1
        }else{
            return 0
        }
    }

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         if(getItemViewType(position)== ITEM_TYPE_HEAD_VIEW){
             val homeHeadView= holder.itemView as HomeHeadView
             homeHeadView.bindView("我是大哥。。。。。。。。。。。。。。")
         }else{
             val homeListItemView= holder.itemView as HomeListItemView
             homeListItemView.bindView(mdatas[position-1])
         }

     }

     class HomeItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

     }
     class HomeHeadViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

     }
 }
## 1.9图片适配 drawable-hdpi里面



## 2.功能需求
###
###
