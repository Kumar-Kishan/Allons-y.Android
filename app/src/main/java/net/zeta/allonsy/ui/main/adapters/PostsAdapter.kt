package net.zeta.allonsy.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import net.zeta.allonsy.R
import net.zeta.allonsy.entity.Post

class PostsAdapter : PagedListAdapter<Post, PostsAdapter.PostViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        if(post != null){
            holder.bindTo(post)
        }else{
            holder.clear()
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val authorTextView = itemView.findViewById<TextView>(R.id.authorTextView)!!
        //val textTxtView = itemView.findViewById<TextView>(R.id.textTxtView)!!
        val urlImageView = itemView.findViewById<SimpleDraweeView>(R.id.urlImageView)!!
        fun clear(){
            authorTextView.text = ""
            //textTxtView.text = ""
        }
        fun bindTo(post:  Post) {
            authorTextView.text = post.author
            //textTxtView.text = post.text
            urlImageView.setImageURI(post.url + "?width=320")
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldPost: Post, newPost: Post): Boolean = oldPost.id == newPost.id
            override fun areContentsTheSame(oldPost: Post, newPost: Post): Boolean = oldPost == newPost
        }
    }
}