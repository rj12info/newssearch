package atrue.newssearch.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import atrue.newssearch.databinding.LayoutArticleItemBinding;
import java.util.ArrayList;

import atrue.newssearch.R;
import atrue.newssearch.models.Hits;

/**
 * Created by Jayanth on 15/06/18.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsViewHolder> {
  private ArrayList<Hits> hitsArrayList;

  public SearchResultsAdapter(ArrayList<Hits> hitsArrayList) {
    this.hitsArrayList = hitsArrayList;
  }

  @Override
  public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutArticleItemBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.layout_article_item, parent, false);
    return new SearchResultsViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(SearchResultsViewHolder holder, int position) {
    holder.bindData(hitsArrayList.get(position));
  }

  @Override
  public int getItemCount() {
    if (hitsArrayList == null) {
      return 0;
    }
    return hitsArrayList.size();
  }
}
