
package com.example.nadini_3tib.pertemuan6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.R

class TigaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Menampilkan layout fragment_tiga.xml
        return inflater.inflate(
            R.layout.fragment_tiga,
            container,
            false
        )
    }
}
