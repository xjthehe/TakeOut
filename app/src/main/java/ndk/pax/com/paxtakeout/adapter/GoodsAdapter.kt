package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.*
import com.heima.takeout.utils.PriceFormater
import com.squareup.picasso.Picasso
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.ui.activity.BusinessActivity
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * User：Rowen
 * Description:商品右侧列表适配器
 * 时间: 2020/4/7:23:13
 *
 */

class GoodsAdapter(val context:Context?,val goodFragment:GoodsFragment):BaseAdapter(), StickyListHeadersAdapter{
    var goodInfoList:List<GoodInfo.ListBeanX.ListBean> = ArrayList()
     //添加数据，刷新适配器
    fun setData(arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>){
        this.goodInfoList=arrayTypeGoodLists
        notifyDataSetChanged()
    }

    companion object{
        val DURATION:Long=1000
    }
    //内部类 holder
    inner class GoodsItemHolder(itemView:View) : View.OnClickListener{
        lateinit var goodInfo: GoodInfo.ListBeanX.ListBean

        override fun onClick(v: View?){
            var isAdd:Boolean=false
            when(v?.id){
                R.id.ib_add->{
                    isAdd=true
                    doAddOperation()
                    
                }
                R.id.ib_minus->{
                    isAdd=false
                    doMinusOperation()
                }
            }
            processRedDotCount(isAdd)
            updateCartUi(isAdd)
        }

         //更新购物车UI
        private fun updateCartUi(isAdd: Boolean) {
             (goodFragment.activity as BusinessActivity).updateCart()
        }

        private fun  processRedDotCount(isAdd: Boolean) {
            //1.找到此商品属于哪个类别
            val typeId = goodInfo.typeId

            //2.此类别在左侧位置
            val typePosition = goodFragment.goodsFragmentPresenter.getTypePositionByTypeId(typeId)

            //3最后找出tvReddotCount
            val goodsTypeInfo = goodFragment.goodsFragmentPresenter.allGoodTypeList.get(typePosition)

            var redDotCount = goodsTypeInfo.redDotCount
            if(isAdd){
                redDotCount++
            }else{
                redDotCount--
            }
            goodsTypeInfo.redDotCount=redDotCount
            //刷新适配器
            goodFragment.goodTypeAdapter.notifyDataSetChanged()
        }
        //减号按钮
        private fun doMinusOperation(){
            var count=goodInfo.count
            if(count==1){
                //点击加号 平移+apha动画
                var hindAnimationSet=getHindAnimationSet()
                tvCount.startAnimation(hindAnimationSet)
                btnMinuss.startAnimation(hindAnimationSet)
            }
            count--
            goodInfo.count=count
            notifyDataSetChanged()
        }

        //点击数量添加按钮
        private fun doAddOperation(){
            var count=goodInfo.count
            if(count==0){
                //点击加号 平移+apha动画
                var animationSet=getShowAnimationSet()
                tvCount.startAnimation(animationSet)
                btnMinuss.startAnimation(animationSet)
            }
            count++
            goodInfo.count=count
            notifyDataSetChanged()
            //抛物线
            //1.克隆加号按钮
            val ib=ImageButton(context)
            ib.setBackgroundResource(R.drawable.button_add)

            //2.添加到activity
            var srcLocation:IntArray= IntArray(2) //加号按钮的位置
            var cartLocation:IntArray= IntArray(2)//购物车位置

            btnAdd.getLocationInWindow(srcLocation)  //获取view绝对位置方法
            ib.x= srcLocation[0].toFloat()
            ib.y= srcLocation[1].toFloat()

            val businessActivity = goodFragment.activity as BusinessActivity
            businessActivity.addImageButton(ib,btnAdd.width,btnAdd.height)
            cartLocation=businessActivity.getCartLocation()

            //3. 抛物线动画（水平位移+垂直加速度）
            val animationSet:AnimationSet= getParabolaAnimation(ib,srcLocation,cartLocation)
            // 回收克隆加号
            ib.startAnimation(animationSet)
        }

        //抛物线运动
        private fun getParabolaAnimation(ib: ImageButton, srcLocation: IntArray, desLocation: IntArray): AnimationSet {
            val paraboAnimationSet: AnimationSet = AnimationSet(false)
            paraboAnimationSet.duration = DURATION

            val translateAnimationX = TranslateAnimation(
                    Animation.ABSOLUTE, 0f,
                    Animation.ABSOLUTE, desLocation[0].toFloat()-srcLocation[0].toFloat(),
                    Animation.ABSOLUTE, 0f,
                    Animation.ABSOLUTE, 0f)
            translateAnimationX.duration = DURATION
            paraboAnimationSet.addAnimation(translateAnimationX)


            val translateAnimationY = TranslateAnimation(
                    Animation.ABSOLUTE,0f,
                    Animation.ABSOLUTE, 0f,
                    Animation.ABSOLUTE, 0f,
                    Animation.ABSOLUTE, desLocation[1].toFloat()-srcLocation[1].toFloat())
            translateAnimationY.duration = DURATION
            translateAnimationY.setInterpolator(AccelerateInterpolator())//Y轴加速度
            paraboAnimationSet.addAnimation(translateAnimationY)

            //动画监听
            paraboAnimationSet.setAnimationListener(object :Animation.AnimationListener{
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    val parent = ib.parent
                    if(parent!=null){
                        (parent as ViewGroup).removeView(ib)
                    }
                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })

             return paraboAnimationSet;
        }


        //隐藏动画   animationSet.addAnimation(translateAnimationX)
        private fun getHindAnimationSet():AnimationSet{
            val showAnimationSet=AnimationSet(false)
            showAnimationSet.duration= DURATION
            //透明度动画
            val alphaAnim=AlphaAnimation(1f,0.0f)
            alphaAnim.duration= DURATION
            showAnimationSet.addAnimation(alphaAnim)

            //旋转动画
            val rotateAnim:Animation=RotateAnimation(720f,0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f)
            rotateAnim.duration= DURATION
            showAnimationSet.addAnimation(rotateAnim)

            //平移
            val translateAnim:Animation=TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,0f,
                    Animation.RELATIVE_TO_SELF,2f,
                    Animation.RELATIVE_TO_SELF,0f,
                    Animation.RELATIVE_TO_SELF,0f)
            translateAnim.duration= DURATION
            showAnimationSet.addAnimation(translateAnim)
            return showAnimationSet
        }
        //显示动画
        private fun  getShowAnimationSet(): AnimationSet{
            val showAnimationSet=AnimationSet(false)
            showAnimationSet.duration= DURATION
            //透明度动画
            val alphaAnim=AlphaAnimation(0.0f,1.0f)
            alphaAnim.duration= DURATION
            showAnimationSet.addAnimation(alphaAnim)

            //旋转动画
            val rotateAnim:Animation=RotateAnimation(0f,720f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f)
            rotateAnim.duration= DURATION
            showAnimationSet.addAnimation(rotateAnim)

            //平移
            val translateAnim:Animation=TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,2f,
                    Animation.RELATIVE_TO_SELF,0f,
                    Animation.RELATIVE_TO_SELF,0f,
                    Animation.RELATIVE_TO_SELF,0f)
            translateAnim.duration= DURATION
            showAnimationSet.addAnimation(translateAnim)
            return showAnimationSet
        }

        fun bindData(goodInfo: GoodInfo.ListBeanX.ListBean) {
            this.goodInfo=goodInfo
            Picasso.with(context).load(goodInfo.icon).into(ivIcon)
            tvName.text=goodInfo.name
            //商品条目赋值
            tvForm.text=goodInfo.form//
            tvMonthSale.text="月售${goodInfo.monthSaleNum}份"
            tvNewPrice.text=PriceFormater.format(goodInfo.newPrice!!.toFloat())
            tvOldPrice.text=PriceFormater.format(goodInfo.oldPrice!!.toFloat())
            tvOldPrice.paint.flags=Paint.STRIKE_THRU_TEXT_FLAG

            if(goodInfo.oldPrice>0){
                tvOldPrice.visibility=View.VISIBLE
            }else{
                tvOldPrice.visibility=View.GONE
            }
            tvCount.text=goodInfo.count.toString()
            //商品笔数 加减按钮
            if(goodInfo.count>0){
                tvCount.visibility=View.VISIBLE
                btnMinuss.visibility=View.VISIBLE
            }else{
                tvCount.visibility=View.INVISIBLE
                btnMinuss.visibility=View.INVISIBLE
            }


        }

        lateinit var ivIcon:ImageView
        lateinit var tvName:TextView
        lateinit var tvForm:TextView
        lateinit var tvMonthSale:TextView
        lateinit var tvNewPrice:TextView
        lateinit var tvOldPrice:TextView
        lateinit var tvCount:TextView
        lateinit var btnAdd: ImageButton
        lateinit var btnMinuss:ImageButton

        init {
            ivIcon= itemView.findViewById<ImageView>(R.id.iv_icon)
            tvName= itemView.findViewById<TextView>(R.id.tv_name)
            tvForm= itemView.findViewById<TextView>(R.id.tv_form)
            tvMonthSale= itemView.findViewById<TextView>(R.id.tv_month_sale)

            tvOldPrice= itemView.findViewById<TextView>(R.id.tv_oldprice)
            tvNewPrice= itemView.findViewById<TextView>(R.id.tv_newprice)

            tvCount= itemView.findViewById<TextView>(R.id.tv_count)

            btnAdd= itemView.findViewById<ImageButton>(R.id.ib_add)
            btnMinuss= itemView.findViewById<ImageButton>(R.id.ib_minus)
            btnAdd.setOnClickListener(this)
            btnMinuss.setOnClickListener(this)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView:View
        val goodHolder:GoodsItemHolder
        if(convertView==null){
            itemView=LayoutInflater.from(context).inflate(R.layout.item_goods,parent,false)
            goodHolder=GoodsItemHolder(itemView)
            itemView.tag=goodHolder
        }else{
            itemView=convertView
            goodHolder=itemView.tag as GoodsItemHolder
        }
        //绑定数据
        goodHolder.bindData(goodInfoList.get(position))
        return itemView
    }

    override fun getItem(position: Int): Any {
        return goodInfoList.get(position)
    }

    override fun getItemId(position: Int): Long =position.toLong()

    override fun getCount(): Int =goodInfoList.size

    override fun getHeaderId(position: Int): Long {
        val goodInfo = goodInfoList.get(position)
        return goodInfo.typeId.toLong()
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val goodInfo = goodInfoList.get(position)
        val typeName = goodInfo.typeName
        val textView:TextView= LayoutInflater.from(context).inflate(R.layout.item_type_header,parent,false) as TextView
        textView.text=typeName
        return textView
    }

}