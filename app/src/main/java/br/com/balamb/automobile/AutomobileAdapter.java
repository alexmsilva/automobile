package br.com.balamb.automobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutomobileAdapter extends BaseAdapter {

    private List<Automobile> automobiles = new ArrayList<>();

    public void addAutomobile(Automobile automobile) {
        automobiles.add(automobile);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return automobiles.size();
    }

    @Override
    public Automobile getItem(int position) {
        return automobiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        Automobile automobile = getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(automobile.getMake() + " - " + automobile.getModel());

        return view;
    }
}
