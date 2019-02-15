package net.zeta.allonsy.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import net.zeta.allonsy.R
import net.zeta.allonsy.entity.Post
import net.zeta.allonsy.ui.main.adapters.PostsAdapter
import timber.log.Timber

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = PostsAdapter()
        viewModel.allPosts.observeForever {
            pagedList: PagedList<Post>? -> adapter.submitList(pagedList)
        }

        allPostsRecyclerView.layoutManager = LinearLayoutManager(context)
        allPostsRecyclerView.addItemDecoration(DividerItemDecoration(allPostsRecyclerView.context,LinearLayoutManager.VERTICAL))
        allPostsRecyclerView.adapter = adapter
        viewModel.loadMore()

    }
}
