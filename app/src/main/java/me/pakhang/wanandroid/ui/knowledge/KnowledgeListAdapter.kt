package me.pakhang.wanandroid.ui.knowledge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.navigation.findNavController
import me.pakhang.wanandroid.databinding.ItemListKnowledgeBinding
import me.pakhang.wanandroid.databinding.ItemListKnowledgeChildBinding
import me.pakhang.wanandroid.model.Knowledge
import me.pakhang.wanandroid.model.KnowledgeChild

class KnowledgeListAdapter(val data: List<Knowledge>) : BaseExpandableListAdapter() {

    override fun getGroupCount() = data.size
    override fun getChildrenCount(groupPosition: Int) = data[groupPosition].children.size

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()
    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getGroup(groupPosition: Int) = data[groupPosition].name
    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        data[groupPosition].children[childPosition].name

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true
    override fun hasStableIds() = false

    // 记录展开的一级目录，用于改变颜色
    private val expandedGroupIds = mutableSetOf<Int>()

    override fun onGroupCollapsed(groupPosition: Int) {
        expandedGroupIds.remove(groupPosition)
    }

    override fun onGroupExpanded(groupPosition: Int) {
        expandedGroupIds.add(groupPosition)
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: View
        val holder: KnowledgeViewHolder
        if (convertView == null) {
            val binding = ItemListKnowledgeBinding
                .inflate(LayoutInflater.from(parent!!.context), parent, false)
            view = binding.root
            holder = KnowledgeViewHolder(binding)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as KnowledgeViewHolder
        }
        holder.bind(data[groupPosition], groupPosition)
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: View
        val holder: KnowledgeChildViewHolder
        if (convertView == null) {
            val binding = ItemListKnowledgeChildBinding
                .inflate(LayoutInflater.from(parent!!.context), parent, false)
            view = binding.root
            holder = KnowledgeChildViewHolder(binding)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as KnowledgeChildViewHolder
        }
        holder.bind(data[groupPosition].children[childPosition])
        return view
    }


    inner class KnowledgeViewHolder(val binding: ItemListKnowledgeBinding) {

        fun bind(data: Knowledge, groupPosition: Int) {
            binding.apply {
                knowledge = data
                isExpanded = expandedGroupIds.contains(groupPosition)
            }
        }
    }

    inner class KnowledgeChildViewHolder(val binding: ItemListKnowledgeChildBinding) {

        fun bind(data: KnowledgeChild) {
            binding.apply {
                child = data
                clickListener = View.OnClickListener {
                    val directions =
                        KnowledgeFragmentDirections.actionKnowledgeFragmentToKnowledgeArticleFragment(
                            data.id
                        )
                    it.findNavController().navigate(directions)
                }
            }
        }
    }
}

