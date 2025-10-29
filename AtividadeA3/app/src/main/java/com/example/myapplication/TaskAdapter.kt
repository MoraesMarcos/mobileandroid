package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTaskBinding

class TaskAdapter(
    private val onTaskClicked: (Task) -> Unit,
    private val onDeleteClicked: (Task) -> Unit,
    private val onEditClicked: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskVH>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem
        }
    }

    inner class TaskVH(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) = with(binding) {
            tvTitle.text = item.title
            cbDone.setOnCheckedChangeListener(null)
            cbDone.isChecked = item.isCompleted

            tvTitle.paint.isStrikeThruText = item.isCompleted
            root.alpha = if (item.isCompleted) 0.6f else 1f

            cbDone.setOnCheckedChangeListener { _, _ -> onTaskClicked(item) }
            btnDelete.setOnClickListener { onDeleteClicked(item) }
            tvTitle.setOnClickListener { onEditClicked(item) }
            root.setOnClickListener { onEditClicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskVH(binding)
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        holder.bind(getItem(position))
    }
}
