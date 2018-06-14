package atrue.newssearch.ui.base;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;


/**
 * Created by Jayanth on 15/06/18.
 */

public abstract class BaseActivity<T extends ViewModel, V extends ViewDataBinding> extends
    AppCompatActivity{

  private T viewModel;
  private V dataBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = getViewModel();
    dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    dataBinding.setVariable(getDataBindingVariable(), viewModel);
    dataBinding.executePendingBindings();
  }

  public abstract T getViewModel();
  public abstract int getDataBindingVariable();
  public abstract int getLayoutId();

  public V getDataBinding() {
    return dataBinding;
  }
}
