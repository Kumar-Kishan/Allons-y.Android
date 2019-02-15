package net.zeta.eye

import android.content.Context
import android.view.TextureView
import java.lang.ref.WeakReference

class Eye (context: Context) {
    val mContext: WeakReference<Context> = WeakReference(context)
}