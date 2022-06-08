package apps.plinqdevelopers.testapp.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import apps.plinqdevelopers.testapp.data.Questions
import apps.plinqdevelopers.testapp.databinding.FragmentSurveyBinding
import apps.plinqdevelopers.testapp.survey.adapter.SurveyAdapter


class SurveyFragment : Fragment() {
    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!

    private val surveyAdapter: SurveyAdapter = SurveyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.surveyList.apply {
            hasFixedSize()
            adapter = surveyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.apply {
            surveyName.text = "This is a sample survey campaign title."
        }
        loadSampleQuestions()
        setupOnclickListener()
    }

    private fun loadSampleQuestions() {
        val q1: Questions = Questions(question = "Hello?")
        val q2: Questions = Questions(question = "What is your name?")
        val q3: Questions = Questions(question = "What is your age?")
        val q4: Questions = Questions(question = "Are you disabled?")

        val questionsList = mutableListOf<Questions>()
        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)

        surveyAdapter.submitList(questionsList)
    }


    private fun setupOnclickListener() {
        binding.apply {
            completeButton.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    surveyAdapter.getResponses().toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}