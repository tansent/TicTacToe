package jtli.com.tictactoe_jingtian.pvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import jtli.com.tictactoe_jingtian.R;
import jtli.com.tictactoe_jingtian.pvp.MainActivity;

/**
 * Created by Jingtian(Tansent).
 *
 * This adapter is to fill the board gridview
 */

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private int[][] positions;
    public BoardAdapter(Context context, int[][] positions) {
        this.context = context;
        this.positions = positions;
    }

    @Override
    public int getCount() {
        return positions.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.list_item_board, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_item);
        return view;
    }

    @Override
    public Object getItem(int position) {
        return positions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
