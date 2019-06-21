package com.ice.shamim.programmingguide.MainMenu.Fragment.Course;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.LessonAndTest.LessonAndTestMain;
import com.ice.shamim.programmingguide.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    public static String title;


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        final Product product = productList.get(position);

        //binding the data with the viewholder views

        holder.textView1.setText(product.getTitle());
        holder.textView2.setText(product.getShortdesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSelectedItem(product.getTitle());
                title = product.getTitle();
                mCtx.startActivity(new Intent(mCtx, LessonAndTestMain.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textView1,textView2;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView)itemView.findViewById(R.id.topic_name);
            textView2 = (TextView)itemView.findViewById(R.id.course_name);
        }


    }

    public String getSelectedItem(String value) {

        return value;
    }

}
