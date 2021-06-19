package com.experiment.android.singlechoicelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<LanguageModel> {

    private List<LanguageModel> itemList;
    private Context context;
    private int selectedPosition = 0;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<LanguageModel> itemList) {
        super(context, resource, itemList);

        this.context = context;
        this.itemList = itemList;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

    public Integer getSelectedPosition() {
        return selectedPosition;
    }

    public LanguageModel getSelectedItem() {
        return itemList.get(selectedPosition);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (null == convertView) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_lv_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.rbSelection.setTag(position);
        viewHolder.tvLanguageLabel.setText(getItem(position).getLanguageName());
        viewHolder.rbSelection.setChecked(position == selectedPosition);
        viewHolder.rbSelection.setOnClickListener(onStateChangeListener(viewHolder.rbSelection, position));

        return convertView;
    }

    private View.OnClickListener onStateChangeListener(final RadioButton radioButton, int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton.isChecked()) {
                    selectedPosition = position;
                } else {
                    selectedPosition = 0;
                }
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {
        private TextView tvLanguageLabel;
        private RadioButton rbSelection;

        public ViewHolder(View view) {
            tvLanguageLabel = view.findViewById(R.id.tv_label);
            rbSelection = view.findViewById(R.id.rb_selection);
        }
    }
}
