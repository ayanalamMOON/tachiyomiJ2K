package eu.kanade.tachiyomi.ui.manga

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.manga.chapter.ChapterHolder
import eu.kanade.tachiyomi.util.system.dpToPx
import eu.kanade.tachiyomi.util.system.getResourceColor

@SuppressLint("UseKtx")
class MangaDetailsDivider(
    context: Context,
    val padding: Int = 12.dpToPx,
) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    private val divider: Drawable

    init {
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        divider = a.getDrawable(0)!!
        a.recycle()
    }

    override fun onDraw(
        c: Canvas,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State,
    ) {
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val holder = parent.getChildViewHolder(child)
            if (holder is ChapterHolder &&
                parent.getChildViewHolder(parent.getChildAt(i + 1)) is ChapterHolder
            ) {
                val params =
                    child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + divider.intrinsicHeight + 1.dpToPx
                val left = parent.paddingStart + padding
                val right = parent.width - parent.paddingEnd - padding

                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
                c.drawColor(parent.context.getResourceColor(R.attr.background))
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State,
    ) {
        outRect.set(0, 0, 0, divider.intrinsicHeight + 1.dpToPx)
    }
}
