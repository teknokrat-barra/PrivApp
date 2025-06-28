package com.kliktutor.privapp

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class chatfragment : Fragment() {

    private lateinit var namaTutorView: TextView
    private lateinit var layoutChat: LinearLayout
    private lateinit var editPesan: EditText
    private lateinit var scrollView: ScrollView
    private var namaTutor: String? = null

    companion object {
        private const val ARG_TUTOR = "nama_tutor"

        fun newInstance(namaTutor: String): chatfragment {
            val fragment = chatfragment()
            val bundle = Bundle()
            bundle.putString(ARG_TUTOR, namaTutor)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        namaTutor = arguments?.getString(ARG_TUTOR)

        namaTutorView = view.findViewById(R.id.nama_tutor_chat)
        layoutChat = view.findViewById(R.id.layout_chat)
        editPesan = view.findViewById(R.id.edit_pesan)
        scrollView = view.findViewById(R.id.scroll_pesan)
        val btnKirim = view.findViewById<Button>(R.id.btn_kirim)

        namaTutorView.text = namaTutor

        btnKirim.setOnClickListener {
            val pesan = editPesan.text.toString()
            if (pesan.isNotEmpty()) {
                tampilkanPesan(pesan)
                editPesan.text.clear()
                scrollView.post { scrollView.fullScroll(View.FOCUS_DOWN) }
            }
        }

        return view
    }

    private fun tampilkanPesan(pesan: String) {
        val textView = TextView(requireContext())
        textView.text = pesan
        textView.setPadding(12, 8, 12, 8)
        textView.setBackgroundResource(android.R.color.holo_blue_light)
        textView.setTextColor(resources.getColor(android.R.color.white, null))

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.END
        params.topMargin = 8
        textView.layoutParams = params

        layoutChat.addView(textView)
    }
}