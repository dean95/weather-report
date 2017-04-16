package com.example.dean.weatherreport.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dean.weatherreport.R;
import com.example.dean.weatherreport.model.WeatherData;
import com.example.dean.weatherreport.utilities.DateUtils;
import com.squareup.picasso.Picasso;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final String BASE_ICON_URL = "http://openweathermap.org/img/w/";

    private final ListItemClickListener mOnClickListener;
    private WeatherData mWeatherData;
    private Context mContext;

    public ForecastAdapter(Context context, WeatherData weatherData,
                           ListItemClickListener clickListener) {
        mWeatherData = weatherData;
        mContext = context;
        mOnClickListener = clickListener;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        String iconId = mWeatherData.getList().get(position).getWeather().get(0).getIcon();
        Log.i("ForecastAdapter", iconId);
        Picasso.with(mContext)
                .load(BASE_ICON_URL + iconId + ".png")
                .into(holder.weatherIconImageView);

        Long unixTimestampDate = mWeatherData.getList().get(position).getDt();
        holder.dateTextView
                .setText(DateUtils.formatDate(unixTimestampDate).toString());
        holder.descriptionTextView.setText(mWeatherData.getList().get(position)
                .getWeather().get(0).getDescription());
        holder.highTempTextView
                .setText(String.valueOf(mWeatherData.getList().get(position).getTemp().getMax()));
        holder.lowTempTextView
                .setText(String.valueOf(mWeatherData.getList().get(position).getTemp().getMin()));
    }

    @Override
    public int getItemCount() {
        int numberOfItems = mWeatherData.getList().size();

        return numberOfItems;
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        ImageView weatherIconImageView;
        TextView dateTextView;
        TextView descriptionTextView;
        TextView highTempTextView;
        TextView lowTempTextView;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            weatherIconImageView = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
            dateTextView = (TextView) itemView.findViewById(R.id.tv_date);
            descriptionTextView = (TextView) itemView.findViewById(R.id.tv_weather_description);
            highTempTextView = (TextView) itemView.findViewById(R.id.tv_high_temp);
            lowTempTextView = (TextView) itemView.findViewById(R.id.tv_low_temp);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
