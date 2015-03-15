package com.costbear.android.thesexydriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentchan on 15-03-14.
 */

public class ItemArrayAdapter extends ArrayAdapter<Car> {
    private List<Car> scoreList = new ArrayList<Car>();

    static class ItemViewHolder {
        TextView model;

    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Car object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public Car getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_models, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.model = (TextView) row.findViewById(R.id.message);

            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        Car stat = getItem(position);
        viewHolder.model.setText(stat.getModel() + " Cylinders: " + stat.getCylinders() + " Tran: " + stat.getTransmission());
        return row;
    }

//    List<String> models;
//
//
//    public ItemArrayAdapter(Context context, List<String> data) {
//        super(context, 0);
//        models = data;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view;
//
//        LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = vi.inflate(R.layout.row_models, null);
//
//        String messageToShow = models.get(position);
//        TextView messageToShowTextView = (TextView) view.findViewById(R.id.message);
//        messageToShowTextView.setText(messageToShow);
//
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return models.size();
//    }

}
