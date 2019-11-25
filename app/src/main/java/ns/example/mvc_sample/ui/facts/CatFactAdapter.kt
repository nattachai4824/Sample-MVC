package ns.example.mvc_sample.ui.facts

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ns.example.mvc_sample.data.entity.Fact

class CatFactAdapter(private val catFacts: List<Fact>) : RecyclerView.Adapter<CatFactAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as? TextView)?.text = catFacts.getOrNull(position)?.text ?: ""
    }

    override fun getItemCount() = catFacts.size

}