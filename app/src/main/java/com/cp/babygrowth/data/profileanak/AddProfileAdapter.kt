package com.cp.babygrowth.data.profileanak

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cp.babygrowth.databinding.ItemProfileAnakBinding
import com.cp.babygrowth.ui.profile.profileanak.detail.DetailProfileAnak

class AddProfileAdapter (private val profileAnakList: List<AddProfileItem>) :
    RecyclerView.Adapter<AddProfileAdapter.AddProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProfileViewHolder {
        val view =
            ItemProfileAnakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddProfileViewHolder, position: Int) {
        val add = profileAnakList[position]
        holder.bind(add)
    }

    override fun getItemCount(): Int {
        return profileAnakList.size
    }

    class AddProfileViewHolder(private val binding: ItemProfileAnakBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(add: AddProfileItem) {
            with(binding) {
                namaAnakTitle.text = add.name
                tanggalLahirAnak.text = add.date_birth
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailProfileAnak::class.java)
                    intent.putExtra(DetailProfileAnak.EXTRA_NAME, add.name)
                    intent.putExtra(DetailProfileAnak.EXTRA_TEMPAT, add.place)
                    intent.putExtra(DetailProfileAnak.EXTRA_TANGGAL, add.date_birth)
                    intent.putExtra(DetailProfileAnak.EXTRA_GENDER, add.gender)
                    intent.putExtra(DetailProfileAnak.EXTRA_BERAT, add.berat)
                    intent.putExtra(DetailProfileAnak.EXTRA_TINGGI, add.tinggi)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}