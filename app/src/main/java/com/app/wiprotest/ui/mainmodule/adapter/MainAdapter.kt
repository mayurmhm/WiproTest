package com.app.wiprotest.ui.mainmodule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wiprotest.R
import com.app.wiprotest.model.api.RowInformationModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_main.view.*
import java.util.*

/**
 * Main Adapter for displaying contents.
 */
class MainAdapter(
    private val context: Context,
    private var rowInformationList: ArrayList<RowInformationModel>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_main,
                parent,
                false
            )
        )
    }

    /**
     * View Holder for Main Activity
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.txtTitle
        val txtDesc: TextView = view.txtDesc
        val imageView: ImageView = view.imageView
    }

    override fun getItemCount(): Int {
        return rowInformationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text =
            if (rowInformationList[position].title.isNullOrBlank())
                context.getString(R.string.data_unavailable)
            else
                rowInformationList[position].title

        holder.txtDesc.text =
            if (rowInformationList[position].description.isNullOrBlank())
                context.getString(R.string.data_unavailable)
            else
                rowInformationList[position].description

        Glide.with(context)
            .load(rowInformationList[position].imageHref)
            .placeholder(R.drawable.ic_palceholder)
            .into(holder.imageView)
    }
}