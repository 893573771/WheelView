package com.example.sanjay.wheelviewlib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 数据需要完成toString方法
 */
public class TextWheelAdapter<T> extends RecyclerView.Adapter<TextWheelAdapter.tvViewHolder> {
    List<T> mTextList;
    //字体颜色
    int mTextColor;
    //字体padding
    int mTextPadding;
    //字体大小
    float mTextSize;
    //上下文
    Context mContext;

    public TextWheelAdapter(Context context) {
        mContext = context;

        mTextSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 20, mContext.getResources().getDisplayMetrics());

        mTextPadding = dp2px(mContext, 5);

    }

    public void setTextSize(int unit, float size) {
        mTextSize = TypedValue.applyDimension(
                unit, size, mContext.getResources().getDisplayMetrics());
    }

    public void setTextPadding(int padding) {
        mTextPadding = padding;
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setData(List<T> dataList) {
        mTextList = dataList;
        notifyDataSetChanged();
    }

    public void addData(List<T> dataList) {
        mTextList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public tvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_date, null);

        return new tvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextWheelAdapter.tvViewHolder holder, int position) {

        if (mTextSize != 0 && holder.contentTv.getTextSize() != mTextSize) {
            holder.contentTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        if (mTextColor != 0 && holder.contentTv.getCurrentTextColor() != mTextColor) {
            holder.contentTv.setTextColor(mTextColor);
        }
        if (holder.contentTv.getPaddingTop() != mTextPadding) {
            holder.contentTv.setPadding(0, mTextPadding, 0, mTextPadding);
        }
        holder.contentTv.setText(mTextList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mTextList == null ? 0 : mTextList.size();
    }

    class tvViewHolder extends RecyclerView.ViewHolder {
        TextView contentTv;

        public tvViewHolder(View view) {
            super(view);
            contentTv = (TextView) view.findViewById(R.id.content_textview);
        }
    }

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
