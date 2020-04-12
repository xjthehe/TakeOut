package ndk.pax.com.paxtakeout.ui.activity

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.heima.takeout.utils.PriceFormater
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart_list.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.BusinessFragmentPagerAdapter
import ndk.pax.com.paxtakeout.adapter.CardRvAdapter
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.model.SellerListItem
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.model.bean.goodInfo
import ndk.pax.com.paxtakeout.ui.fragment.CommentsFragment
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment
import ndk.pax.com.paxtakeout.ui.fragment.SellerFragment
import java.util.*

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/6:19:26
 *
 */

class BusinessActivity : BaseActivity(), View.OnClickListener {
    var bottomSheetView: View? = null
    lateinit var cartRv: RecyclerView
    lateinit var tvClear: TextView
    lateinit var cardAdapter: CardRvAdapter
    lateinit var goodsFragment: GoodsFragment
    lateinit var goodInfos: GoodInfo.ListBeanX.ListBean
    var hasSelectInfo = false
    lateinit var seller: SellerListItem
    lateinit var dialoge: Dialog


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bottom -> {
                showOrHindCart()
            }
        }
    }

    //弹出购物车
    fun showOrHindCart() {
        if (bottomSheetView == null) {
            bottomSheetView = LayoutInflater.from(this).inflate(R.layout.cart_list, window.decorView as ViewGroup, false)
            cartRv = bottomSheetView!!.findViewById<RecyclerView>(R.id.rvCart)
            tvClear = bottomSheetView!!.findViewById<TextView>(R.id.tvClear)//清空购物车
            //清空购物车
            tvClear.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("确认不吃了么")
                builder.setPositiveButton("是，不吃了", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val cartGoodList = goodsFragment.goodsFragmentPresenter.getCartGoodList()
                        for (i in 0 until cartGoodList.size) {
                            //清空购物车
                            goodInfos = cartGoodList.get(i)
                            goodInfos.count = 0

                            //清除左侧红点
                            val typeId = goodInfos.typeId
                            //2.此类别在左侧位置
                            val typePosition = goodsFragment.goodsFragmentPresenter.getTypePositionByTypeId(typeId)
                            //3最后找出tvReddotCount
                            val goodsTypeInfo = goodsFragment.goodsFragmentPresenter.allGoodTypeList.get(typePosition)
                            var redDotCount = 0
                            goodsTypeInfo.redDotCount = redDotCount

                        }
                        //购物车适配器刷新
                        cardAdapter.notifyDataSetChanged()

                        //右侧商品列表刷新
                        goodsFragment.goodInfoAdapter.notifyDataSetChanged()

                        //左侧 类型刷新
                        goodsFragment.goodTypeAdapter.notifyDataSetChanged()

                        //再次调用购物车隐藏
                        showOrHindCart()

                        //更新购物车底部金额  购物车里面数量
                        updateCart()
                    }
                })
                builder.setNegativeButton("不，我还要吃", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialoge.dismiss()
                    }
                })
                dialoge = builder.show()
            }



            cartRv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
            cardAdapter = CardRvAdapter(this)
            cartRv.adapter = cardAdapter
        }


        //判断BottomSheetLayout内容是否显示
        if (bottomSheetLayout.isSheetShowing()) {
            //关闭内容显示
            bottomSheetLayout.dismissSheet();
        } else {
            //获取底部适配器数据源
            val goodFragment: GoodsFragment = fragments.get(0) as GoodsFragment
            val cartGoodList = goodFragment.goodsFragmentPresenter.getCartGoodList()
            cardAdapter.setData(cartGoodList)
            if (cartGoodList.size > 0) {
                bottomSheetLayout.showWithSheetView(bottomSheetView);
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_business
    }

    override fun init() {
        processIntent()
        //获取是否存在NavigationBar
        if (checkDeviceHasNavigationBar(this)) {
            //dp转换为px
            ll_main_activity.setPadding(0, 0, 0, 48.dip2px(this))
        }
        //viewpager
        vp.adapter = BusinessFragmentPagerAdapter(fragments, titles, supportFragmentManager)
        //tablayout+viewpager关联
        tabs.setupWithViewPager(vp)

        //弹出购物车,底部点击弹出监听
        bottom.setOnClickListener(this)
    }

    private fun processIntent() {
        if (intent.hasExtra("hasSelectInfo")) {
            hasSelectInfo = intent.getBooleanExtra("hasSelectInfo", false)
            //获取当前店的商家信息
            seller = intent.getSerializableExtra("seller") as SellerListItem
            tvDeliveryFee.text = "另需配送费" + PriceFormater.format(seller.deliveryFee.toFloat())
            tvSendPrice.text = PriceFormater.format(seller.sendPrice.toFloat()) + "元起送"
            //缓存数据已经添加，可以刷新底部购物车
            Log.e("processIntent--->","updateCart")

        }
    }

    val titles = listOf<String>("商品", "商家", "评论")
    val fragments = listOf<Fragment>(GoodsFragment(), SellerFragment(), CommentsFragment())


    //是否有虚拟按键
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar: Boolean = false;
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = resources.getBoolean(id);
        }
        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties");
            val m = systemPropertiesClass.getMethod("get", String::class.java);
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String;
            if ("1".equals(navBarOverride)) {
                //不存在虚拟按键
                hasNavigationBar = false
            } else if ("0".equals(navBarOverride)) {
                //存在虚拟按键
                hasNavigationBar = true
            }
        } catch (e: Exception) {
        }
        return hasNavigationBar;
    }

    fun addImageButton(ib: ImageButton, width: Int, height: Int) {
        //克隆加号添加到activity 控件自身宽高
        fl_Container.addView(ib, width, height)
    }

    fun getCartLocation(): IntArray {
        val desLocation = IntArray(2)
        imgCart.getLocationInWindow(desLocation)
        return desLocation
    }

    //更新底部购物篮UI
    fun updateCart() {
        //更新数量 更新总价
        var count = 0;
        var countPrice = 0.0f

        goodsFragment = fragments.get(0) as GoodsFragment
        val cartGoodList = goodsFragment.goodsFragmentPresenter.getCartGoodList()

        for (i in 0 until cartGoodList.size) {
            var goodInfo = cartGoodList[i]
            count += goodInfo.count
            countPrice += goodInfo.count * goodInfo.newPrice!!.toFloat()
        }

        Log.e("count------","count+"+count)
        //购物车数量
        if (count > 0) {
            tvSelectNum.visibility = View.VISIBLE
        } else {
            tvSelectNum.visibility = View.GONE
        }

        tvSelectNum.text = count.toString()
        //总共价格
        tvCountPrice.text = PriceFormater.format(countPrice)
    }

}