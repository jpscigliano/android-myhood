package co.test.myhood.presentation.hoodList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.test.myhood.R
import co.test.myhood.domain.Hood
import co.test.myhood.presentation.hoodList.HoodsListAdapter.HoodsViewHolder
import coil.api.load
import kotlinx.android.synthetic.main.row_hood.view.hood_imageView
import kotlinx.android.synthetic.main.row_hood.view.title_textView

class HoodsListAdapter : ListAdapter<Hood, HoodsViewHolder>(HoodItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoodsViewHolder {
        return HoodsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_hood, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HoodsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class HoodsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindTo(hood: Hood) = with(view) {
            view.title_textView.text = hood.name
            view.hood_imageView.load(hood.imageUrl)
        }
    }

    class HoodItemDiffCallback : DiffUtil.ItemCallback<Hood>() {
        override fun areItemsTheSame(oldItem: Hood, newItem: Hood): Boolean = oldItem.name==newItem.name

        override fun areContentsTheSame(oldItem: Hood, newItem: Hood): Boolean = oldItem == newItem
    }
}