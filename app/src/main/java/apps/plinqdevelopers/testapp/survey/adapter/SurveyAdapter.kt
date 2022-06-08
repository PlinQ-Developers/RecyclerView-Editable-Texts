package apps.plinqdevelopers.testapp.survey.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apps.plinqdevelopers.testapp.data.Questions
import apps.plinqdevelopers.testapp.databinding.ItemSurveyBinding

class SurveyAdapter : ListAdapter<Questions, SurveyAdapter.SurveyAdapterViewHolder> (QuestionsComparator()) {

    public var answersList = mutableListOf<String>()

    inner class SurveyAdapterViewHolder(private val binding : ItemSurveyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question : Questions) {
            binding.apply {
                surveyQuestion.text = question.question

                surveyAnswer.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        val answerText : String = p0.toString()
                        answersList.add(answerText)
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyAdapterViewHolder {
        return SurveyAdapterViewHolder(
            ItemSurveyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SurveyAdapterViewHolder, position: Int) {
        val questionItem : Questions = getItem(position)
        holder.bind(question = questionItem)
    }

    class QuestionsComparator : DiffUtil.ItemCallback<Questions> () {
        override fun areItemsTheSame(oldItem: Questions, newItem: Questions): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Questions, newItem: Questions): Boolean {
            return oldItem == newItem
        }
    }

    public fun getResponses() : MutableList<String> {
        return answersList
    }
}