package atrue.newssearch.adapter;

import android.support.v7.widget.RecyclerView;
import atrue.newssearch.databinding.LayoutArticleItemBinding;
import atrue.newssearch.handler.SourceActionHandler;
import atrue.newssearch.models.Hits;

/**
 * Created by Jayanth on 15/06/18.
 */

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {
  private LayoutArticleItemBinding binding;

  public SearchResultsViewHolder(LayoutArticleItemBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bindData(Hits article) {
    binding.setArticle(article);
    binding.setHandler(new SourceActionHandler());
    binding.executePendingBindings();
  }
}
