package org.hyperskill.aquarium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.hyperskill.aquarium.databinding.ActivityMainBinding
import java.lang.Thread.State


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageAnimals: List<String> =
            intent?.getSerializableExtra("imageAnimals") as? List<String> ?: listOf(
                "https://ucarecdn.com/42045846-b968-4a88-81ec-df73bec4fcb7/",
                "https://ucarecdn.com/5aa10eb3-fc49-4304-9057-adf1d29a9b4c/",
                "https://ucarecdn.com/c5fd39b9-7690-4616-b7dc-d3f8da883146/"
            )
        val nameAnimals: List<String> =
            intent?.getSerializableExtra("nameAnimals") as? List<String> ?: listOf(
                "Koi Carp",
                "Spiny Dogfish",
                "Kaluga"
            )
        val descriptionAnimal: List<String> =
            intent?.getSerializableExtra("descriptionAnimals") as? List<String> ?: listOf(

                //Koi Carp
                "These colorful, ornamental fish are a variety of the Amur carp. " +
                        "They were originally found in Central Europe and Asia, " +
                        "but they’ve spread to many other parts of the world. " +
                        "Koi carp are popular with breeders, and there are currently over 100 varieties " +
                        "created through breeding.\n" +
                        "\n" +
                        "The average age of a koi carp can vary based on the part of the world it’s bred in. " +
                        "Carps bred outside of Japan have an average lifespan of around 15 years," +
                        " while carps bred in Japan can live 40 years or more. The oldest koi carp on record," +
                        " which was a fish named Hanako, reportedly lived for 226 years!",

                // Spiny dogfish
                "The spiny dogfish is a type of shark with venomous spines in front of its dorsal fins." +
                        " Not only is it an aggressive hunter, but these fish are known to hunt in packs!" +
                        " Like many shark species, these fish grow slowly, " +
                        "and some females don’t reach full maturity until they’re over 30 years old.\n" +
                        "\n" +
                        "While the lifespan of the spiny dogfish is already impressive, " +
                        "some fish live for far longer than average. " +
                        "Spiny dogfish in the Pacific Ocean tend to live longer than fish in the Atlantic," +
                        " with some fish living longer than 80 years. " +
                        "Females tend to mature later than males, and they usually live longer too.",

                //Kaluga
                "Sometimes called the river beluga, the kaluga is a type of predatory sturgeon." +
                        " While these fish spend the majority of their time in freshwater, " +
                        "they’re also able to survive in salt water. " +
                        "The kaluga is one of the world’s largest freshwater fish species and can grow to be more than 18 feet long, " +
                        "with a weight of over 2,200 pounds.\n" +
                        "\n" +
                        "Kalugas are overfished, which has left the species vulnerable to extinction. " +
                        "Although many kaluga are killed before they fully mature, " +
                        "these fish have the potential to live very long lives. " +
                        "One kaluga that was caught in China is estimated to be over 100 years old."
            )

        val viewPager2 = binding.viewpager2
        val myAdapter = viewPagerAdapter(
            imageAnimals = imageAnimals,
            nameAnimals = nameAnimals,
            descriptionAnimals = descriptionAnimal
        )
        viewPager2.adapter = myAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = nameAnimals[position]
        }.attach()
    }
    
    class viewPagerAdapter(
        var imageAnimals: List<String>,
        var nameAnimals: List<String>,
        var descriptionAnimals: List<String>
    ) : RecyclerView.Adapter<PageViewHolder>() {

        override fun getItemCount(): Int {
            return nameAnimals.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder =
            PageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
            )


        override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
            holder.itemView.run {
                holder.name.text = nameAnimals[position]
                holder.description.text = descriptionAnimals[position]
            }

            if (imageAnimals.isNotEmpty()) {
                Picasso.get()
                    .load(imageAnimals[position])
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(holder.image)
            }
        }
    }

    class PageViewHolder(iV: View) : RecyclerView.ViewHolder(iV) {
        var image = iV.findViewById<ImageView>(R.id.image_view)
        val name = iV.findViewById<TextView>(R.id.tv_name)
        val description = iV.findViewById<TextView>(R.id.tv_description)
    }
}

