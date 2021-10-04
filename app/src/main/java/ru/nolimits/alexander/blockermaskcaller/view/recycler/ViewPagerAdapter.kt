package ru.nolimits.alexander.blockermaskcaller.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.ItemEducationPageBinding

class ViewPagerAdapter(
    val callBack: CallBackViewPager
) : RecyclerView.Adapter<ViewPagerAdapter.PagerHolder>() {

    private val listEducationText = listOf(
        """У Вас пока нет ни одного префикса телефонного номера для блокирования вызовов.
            | Добавьте первую "маску" для блокировки вызовов по совпадению первых 7
            | цифр телефонного номера""".trimMargin(),
        """Номер телефона, начало которого будет соответсвовать первым 7ми цифрам маски,
            | будет заблокирован. Пример: Вы добавили "маску" +7 999 888. Вам звонит абонент с номером
            | +7 999 888 77 66 - Такой вызов будет заблокирован""".trimMargin()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {

        val binding = ItemEducationPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagerHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerHolder, position: Int) {
        with(holder) {
            binding.apply {
                educationText.text = listEducationText[position]

                if (position == listEducationText.lastIndex) {
                    buttonNext.apply {
                        setText(R.string.create_new)
                        setOnClickListener {
                            callBack.onButtonNextClicked()
                        }
                    }
                } else {
                    buttonNext.setOnClickListener {
                        //TODO переключить viewPager на следующий экран
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listEducationText.size

    inner class PagerHolder(val binding: ItemEducationPageBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface CallBackViewPager {
        fun onButtonNextClicked()
    }
}

