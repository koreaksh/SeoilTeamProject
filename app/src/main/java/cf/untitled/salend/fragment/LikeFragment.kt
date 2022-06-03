package cf.untitled.salend.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.untitled.salend.MyApplication
import cf.untitled.salend.R
import cf.untitled.salend.databinding.FragmentLikeBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the cf.untitled.salend.fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var favArray: ArrayList<String?>
private var initFlag: Boolean = false;

/**
 * A simple [Fragment] subclass.
 * Use the [LikeFragment.newInstance] factory method to
 * create an instance of this cf.untitled.salend.fragment.
 */
class LikeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        initFlag = true;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val TAG = "asdf"
        val f: FragmentLikeBinding = FragmentLikeBinding.bind(view)
        val thread = Thread {
            run {
                Log.e(TAG, "LF: " + 1 + f.favoritePageStoreButton.text)
                f.favoritePageStoreButton.text = "A"
                Log.e(TAG, "LF: " + MyApplication.getStatus())
                if (MyApplication.setStatus()) {
                    favArray = MyApplication.getStoreFavorite()
                    Log.e(TAG, "LF: " + MyApplication.getStoreFavorite())
                }
            }
        }

        f.favoritePageItemButton.setOnClickListener {
            f.favoritePageStoreButton.text = "asdf"
        }

        Log.e(TAG, "LF: " + thread.state)
        if (thread.state == Thread.State.NEW)
            thread.start()
    }

    public fun refreshList() {
        if (MyApplication.setStatus()) {
            favArray = MyApplication.getStoreFavorite()
            Log.e("asdf", "LF: " + MyApplication.getStoreFavorite())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this cf.untitled.salend.fragment
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (initFlag) {
            Thread {
                run() {
                    refreshList()
                    Log.e("asdf", "setUserVisibleHint: " + favArray)
                }
            }.start()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this cf.untitled.salend.fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of cf.untitled.salend.fragment LikeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LikeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}