package com.jetpack.vpmc;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 private ActivityMainBinding binding;

 @Override
 protected ViewBinding getBinding() {
 binding = ActivityMainBinding.inflate(getLayoutInflater());
 return binding;//R.layout.activity_main
 }

 */
public abstract class VPMCActivity<P extends VPMCPresenter> extends AppCompatActivity implements View.OnClickListener {
    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
        mPresenter = getmPresenterInstance();
        if(mPresenter!=null)mPresenter.bindView(this);
        if(mPresenter == null)mPresenter = null;
        initViews();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        /**
         * 2021/01/19 change this place
         */
        if(mPresenter!=null)mPresenter.unBindView();
        super.onDestroy();
    }

    protected abstract ViewBinding getBinding();
    protected abstract void initViews();
    protected abstract void initEvents();
    protected abstract P getmPresenterInstance();
}

/**
 *
 要求在build.gradle加入
 android {
    ...
     buildFeatures{
        viewBinding= true
     }
 }
 之后重新同步，即可使用viewBinding

 在Activity中使用
 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(ActivityDescriptionBinding.inflate(getLayoutInflater()).getRoot());
 }
 在Fragment中使用：
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
    return ActivityDescriptionBinding.inflate(inflater, container, false).getRoot();
 }
 在RecyclerView的ViewHolder中使用
 static class BeautyViewHolder extends RecyclerView.ViewHolder{
     private final ItemBeautyBinding binding;
     public BeautyViewHolder(@NonNull View itemView) {
         super(itemView);
         binding= ItemBeautyBinding.bind(itemView);
    }
 }

 在View中使用
 LayoutInflater.from(context).inflate(R.layout.clip_report_doubleslow, this, true);
 binding = ClipReportDoubleslowBinding.bind(this);

 */