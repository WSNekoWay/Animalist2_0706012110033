package Adapter

import Interface.cardListener
import Model.Animal
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animalist2_0706012110033.R
import com.example.animalist2_0706012110033.databinding.CardAnimalBinding

class ListDataRVAdapter (val ListAnimal: ArrayList<Animal>, val cardListener: cardListener):RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {
    class viewHolder (val itemView: View, val cardListeners: cardListener): RecyclerView.ViewHolder(itemView){
        val binding = CardAnimalBinding.bind(itemView)
        fun setData(data: Animal){
            binding.textNama.text = data.id.toString()
            binding.textHewan.text = data.jenis
            binding.textUsia.text = data.usia

            if (data.imageUri!!.isNotEmpty()) {
                binding.imageView.setImageURI(Uri.parse(data.imageUri))
            }else{
                binding.imageView.setImageResource(R.drawable.squid)
            }

            binding.editButton.setOnClickListener {
                cardListeners.onCardClick("edit",data.id)
            }

            binding.deleteButton.setOnClickListener {
                cardListeners.onCardClick("delete",data.id)
            }
            binding.suaraButton.setOnClickListener{
                cardListeners.onCardClick("suara",data.id)
            }
            binding.makanButton.setOnClickListener{
                cardListeners.onCardClick("makan",data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_animal, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(ListAnimal[position])
    }

    override fun getItemCount(): Int {
        return ListAnimal.size
    }
}