package com.gmail.aaronsmith.mvp_kotlin.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gmail.aaronsmith.mvp_kotlin.R
import com.gmail.aaronsmith.mvp_kotlin.durationFormat
import com.gmail.aaronsmith.mvp_kotlin.glide.GlideApp
import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import io.reactivex.Observable
import view.CommonAdapter

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
// 
/**
 * @author Aaron Smith
 * @date 2018/10/24.
 */
class HomeAdapter(context: Context, data: ArrayList<HomeBean.Issue.Item>)
    : CommonAdapter<HomeBean.Issue.Item>(context, data, -1) {

    //banner 作为 RecyclerView 的第一项
    var bannerItemSize = 0

    companion object {
        private const val ITEM_TYPE_BANNER = 1      //Banner 类型
        private const val ITEM_TYPE_TEXT_HEADER = 2     //textHeader
        private const val ITEM_TYPE_CONTENT = 3     //item
    }

    /**
     * 设置 Banner 大小
     */
    fun setBannerSize(count: Int) {
        bannerItemSize = count
    }

    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }

    /**
     * 得到 Item 类型
     */
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 ->
                ITEM_TYPE_BANNER
            mData[position + bannerItemSize - 1].type == "textHeader" ->
                ITEM_TYPE_TEXT_HEADER
            else ->
                ITEM_TYPE_CONTENT
        }
    }

    /**
     * 得到 RecyclerView Item 数量（Banner 作为一个 item）
     */
    override fun getItemCount(): Int {
        return when {
            mData.size > bannerItemSize -> mData.size - bannerItemSize + 1
            mData.isEmpty() -> 0
            else -> 1
        }
    }

    /**
     * 创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER ->
                ViewHolder(inflaterView(R.layout.item_home_banner, parent))
            ITEM_TYPE_TEXT_HEADER ->
                ViewHolder(inflaterView(R.layout.item_home_header, parent))
            else ->
                ViewHolder(inflaterView(R.layout.item_home_content, parent))

        }
    }

    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建View
        val view = mInflater?.inflate(mLayoutId, parent)
        return view ?: View(parent.context)
    }


    /**
     * 绑定布局
     */
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        when (getItemViewType(position)) {
            //Banner
            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<HomeBean.Issue.Item> = mData.take(bannerItemSize).toCollection(ArrayList())
                val bannerFeedList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()
                //取出 banner 显示的 img 和 title
                Observable.fromIterable(bannerItemData)
                        .subscribe { list ->
                            bannerFeedList.add(list.data?.cover?.feed ?: "")
                            bannerTitleList.add(list.data?.title ?: "")
                        }
                //设置 banner
                with(holder) {
                    getView<BGABanner>(R.id.banner).run {
                        setAutoPlayAble(bannerFeedList.size > 1)
                        setData(bannerFeedList, bannerTitleList)
                        setAdapter { banner, _, feedImageUrl, position ->
                            GlideApp.with(mContext)
                                    .load(feedImageUrl)
                                    .transition(DrawableTransitionOptions().crossFade())
                                    .placeholder(R.drawable.placeholder_banner)
                                    .into(banner.getItemImageView(position))
                        }
                    }
                }

                //没有使用到的参数在 kotlin 只能够用"_"代替
                holder.getView<BGABanner>(R.id.banner).setDelegate { _, imageView, _, i ->
                    goToVideoPlayer(mContext as Activity, imageView, bannerItemData[i])
                }
            }
            //TextHeader
            ITEM_TYPE_TEXT_HEADER -> {
                holder.setText(R.id.tvHeader, mData[position + bannerItemSize - 1].data?.text ?: "")
            }
            //content
            ITEM_TYPE_CONTENT -> {

            }
        }
    }

    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: ViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data
        val defAvatar = R.mipmap.default_avatar
        val cover = itemData?.cover?.feed
        var avatar = itemData?.author?.icon
        var tagText: String? = "#"

        // 作者出处为空，就显获取提供者的信息
        if (avatar.isNullOrEmpty()) {
            avatar = itemData?.provider?.icon
        }
        //加载封面图
        GlideApp.with(mContext)
                .load(cover)
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_cover_feed))
        // 如果提供者信息为空，就显示默认
        if (avatar.isNullOrEmpty()) {
            GlideApp.with(mContext)
                    .load(defAvatar)
                    .placeholder(R.mipmap.default_avatar).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))
        } else {
            GlideApp.with(mContext)
                    .load(avatar)
                    .placeholder(R.mipmap.default_avatar).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))
        }
        holder.setText(R.id.tv_title, itemData?.title ?: "")

        //遍历标签
        itemData?.tags?.take(4)?.forEach {
            tagText += (it.name + "/")
        }
        //格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        tagText += timeFormat

        holder.setText(R.id.tv_tag, tagText!!)

        holder.setText(R.id.tv_category, "#" + itemData?.category)

        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), item)
        })

    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {

    }

}