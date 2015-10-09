package lb.library.cursor;

import android.content.Context;
import android.database.Cursor;
import android.widget.AlphabetIndexer;
import android.widget.FilterQueryProvider;
import android.widget.SectionIndexer;

public abstract class SearchablePinnedHeaderCursorListViewAdapter extends IndexedPinnedHeaderCursorListViewAdapter {

	public SearchablePinnedHeaderCursorListViewAdapter(Context context, Cursor c, int columnIndex, boolean autoRequery) {
		super(context, c, columnIndex, autoRequery);

		setFilterQueryProvider(new FilterQueryProvider() {
			@Override
			public Cursor runQuery(CharSequence constraint) {

				return getFilterCursor(constraint);
			}
		});
	}

	@Override
	public void changeCursor(Cursor cursor) {
		super.changeCursor(cursor);
		SectionIndexer indexer = getSectionIndexer();
		if (indexer instanceof AlphabetIndexer) {
			((AlphabetIndexer) indexer).setCursor(cursor);
		}
	}

	protected abstract Cursor getFilterCursor(CharSequence constraint);

}
